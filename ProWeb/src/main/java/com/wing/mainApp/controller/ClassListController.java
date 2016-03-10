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
import com.wing.mainApp.util.StringUtil;

@Controller
public class ClassListController {

	@Autowired
	ClassListDAO lDao;
	
	// 목록보기 요청
	@RequestMapping("/ClassList/ClassList.do")
	public ModelAndView classList(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
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
		
		mv.setViewName("ClassList/ClassWriteForm");
		return mv;
	}
	
	// 글쓰기 요청
	@RequestMapping("/ClassList/ClassWrite")
	public ModelAndView classWrite(HttpSession session, ClassListData data){
		ModelAndView	mv = new ModelAndView();
		
		data.id = (String) session.getAttribute("ID");
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
}
