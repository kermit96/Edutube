package com.wing.mainApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.ClassListDAO;
import com.wing.mainApp.data.ClassListData;
import com.wing.mainApp.util.PagingUtil;
import com.wing.mainApp.util.SessionUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class ClassListController {

	@Autowired
	ClassListDAO lDao;
	
	// 목록보기 요청
	@RequestMapping("/ClassList/ClassList.do")
	public ModelAndView classList(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/LoginForm.do");
			mv.setView(rv);
			return mv;
		}
		
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
			
		int	total = lDao.getTotal(1);
		PagingUtil	pInfo = new PagingUtil(nowPage, total, 10, 10);
		pInfo.pagingProc();
		
		ArrayList	list = lDao.getClassList();
		
		int	start = (pInfo.nowPage -1) * pInfo.onePageCount;
		int	end = start + pInfo.onePageCount -1;
		if(end >= list.size()) {
			end = list.size() -1;
		}
		
		ArrayList	result = new ArrayList();
		for(int i = start; i <= end; i++) {
			ClassListData	temp = (ClassListData)list.get(i);
			result.add(temp);
		}
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		
		mv.setViewName("ClassList/ClassList");
		return mv;
	}
	// 글쓰기 폼 요청
	@RequestMapping("/ClassList/ClassWriteForm.do")
	public ModelAndView classWriteForm(HttpServletRequest req, HttpSession session){
		ModelAndView	mv = new ModelAndView();
		
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/LoginForm.do");
			mv.setView(rv);
			return mv;
		}
		
		mv.setViewName("ClassList/ClassWriteForm");
		return mv;
	}
	
	// 글쓰기 요청
	@RequestMapping("/ClassList/ClassWrite")
	public ModelAndView classWrite(HttpSession session, ClassListData data){
		ModelAndView	mv = new ModelAndView();
		
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/LoginForm.do");
			mv.setView(rv);
			return mv;
		}
		
		data.id = (String) session.getAttribute("ID");
		System.out.println(data.id);
		System.out.println(data.code);
		System.out.println(data.title);
		System.out.println(data.body);
		System.out.println(data.lang);
		lDao.insertclass(data);
		
		RedirectView	rv = new RedirectView("../ClassList/ClassList.do");
		mv.setView(rv);
		return mv;
	}
	// 상세보기 요청
	@RequestMapping("/ClassList/ClassView")
	public ModelAndView classView(HttpServletRequest req, ClassListData data){
		ModelAndView	mv = new ModelAndView();
		
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
		String	strpage = req.getParameter("nowPage");
		int	nowPage = Integer.parseInt(strpage);
		String	kind = req.getParameter("flag");
		
		ClassListData map = lDao.selectView(oriNo);
		
		mv.addObject("DATA", map);
		mv.addObject("nowPage", nowPage);
		mv.addObject("KIND", kind);
		mv.setViewName("ClassList/ClassView");
		return mv;
	}
	// 삭제 요청
	@RequestMapping("/ClassList/ClassDelete.do")
	public ModelAndView classDelete(HttpServletRequest req){
		ModelAndView	mv = new ModelAndView();
		
		String strpage = req.getParameter("nowPage");
		String	strNo = req.getParameter("oriNo");
		int	nowPage = Integer.parseInt(strpage);
		int	oriNo = Integer.parseInt(strNo);
		
		HashMap	map = new HashMap();
		map.put("NO",  oriNo);
		int cnt	= lDao.isUpdate(map);
		if(cnt == 0) {
			RedirectView rv = new RedirectView("../ClassList/ClassView.do");
			rv.addStaticAttribute("oriNo", oriNo);
			rv.addStaticAttribute("nowPage", nowPage);
			return mv;
		}
		else {
			lDao.deleteclass(oriNo);
		}
		RedirectView	rv = new RedirectView("../ClassList/ClassList.do");
		mv.setView(rv);
		
		return mv;
	}
	// 수정하기 폼 요청
	@RequestMapping("/ClassList/ClassModifyForm.do")
	public ModelAndView classModifyForm(HttpServletRequest req, ClassListData data){
		ModelAndView	mv = new ModelAndView();
		
//		String strpage = req.getParameter("nowPage");
		String strNo = req.getParameter("oriNo");
//		System.out.println(strpage);
//		
//		int nowPage = Integer.parseInt(strpage);
		int	oriNo = Integer.parseInt(strNo);
		data.no = oriNo;
		System.out.println(oriNo);
		System.out.println(data.oriNo);
		System.out.println(data.no);
		
		
		HashMap	map = new HashMap();
		map.put("NO", oriNo);
		int cnt = lDao.isUpdate(map);
		ClassListData	result = new ClassListData();
		if(cnt == 0) {
			RedirectView	rv = new RedirectView("../ClassList/ClassView.do");
			rv.addStaticAttribute("oriNo", oriNo);
//			rv.addStaticAttribute("nowPage", nowPage);
			mv.setView(rv);
			return mv;
		}
		else {
			result = lDao.selectView(oriNo);
		}
		
		mv.addObject("DATA", result);
//		mv.addObject("nowPage", nowPage);
		mv.setViewName("ClassList/ClassModifyForm");
		return mv;
	}
	// 수정 요청
	@RequestMapping("/ClassList/ClassModify")
	public ModelAndView	classModify(ClassListData data) {
		ModelAndView	mv = new ModelAndView();
		
		lDao.updateclass(data);
		
		RedirectView	rv = new RedirectView("../ClassList/ClassView.do");
		rv.addStaticAttribute("oriNo", data.no);
//		rv.addStaticAttribute("nowPage", data.nowPage);
		mv.setView(rv);
		return mv;
	}
	
	// 조회수 증가 요청
	@RequestMapping()
	public ModelAndView classHit(HttpServletRequest req, HttpSession session) {
		ModelAndView	mv = new ModelAndView();
		
		String strPage = req.getParameter("nowPage");
		
		if(StringUtil.isNull(strPage)) {
			RedirectView	rv = new RedirectView("../ClassList/ClassList.do");
			mv.setView(rv);
			return mv;
		}
		
		int	nowPage = Integer.parseInt(strPage);
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
		String	kind = req.getParameter("flag");
		
		String	id = (String) session.getAttribute("ID");
		
		HashMap	showMap = lDao.getShowNo(id);
		boolean	isHit = false;
		if(showMap == null || showMap.isEmpty()) {
			isHit = true;
			
			HashMap	temp = new HashMap();
			temp.put("ID", id);
			temp.put("NO",  ":" + oriNo + ":" );
			lDao.updateShowNo(temp, 2);
		}
		else {
			String	tempNo = ":" + oriNo + ":";
			String	dbNo = (String) showMap.get("SHOWNO");
			int index = dbNo.indexOf(tempNo);
			if(index == -1) {
				isHit = true;
				
				HashMap	temp = new HashMap();
				temp.put("ID", id);
				temp.put("NO", dbNo + tempNo);
				lDao.updateShowNo(temp, 1);
			}
			else {
				isHit = false;
			}
		}
		if(isHit == true) {
			lDao.updateHit(oriNo);
		}
		RedirectView	rv = new RedirectView("../ClassList/ClassView.do");
		rv.addStaticAttribute("oriNo", oriNo);
		rv.addStaticAttribute("nowPage", nowPage);
		rv.addStaticAttribute("flag", kind);
		mv.setView(rv);
		return mv;
	}
	// 좋아요 요청 함수
	@RequestMapping("/ClassList/ClassGood")
	public ModelAndView classGood(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		1	넘어온 파라메터 받는다.
		String	strNo = req.getParameter("oriNo");
		int		oriNo = Integer.parseInt(strNo);
		//		2	추천을 계속할 수 없도록 조치한다.
		//			세션을 이용하던지, 데이터베이스를 이용하던지.........
		
		//		3.	데이터베이스에 추천을 하도록 한다.
		lDao.updateGood(oriNo);
		//		4.	현재 추천 내용을 알아온다.
		//		(아작스로 보냈으므로 결과를 보여주기 위해서는 현재까지 추천 상황을 알아야 한다)
		int	good = lDao.selectGood(oriNo);
		
		mv.addObject("GOOD", good);
		mv.setViewName("ClassList/BoardGood");
		return mv;
	}
}
