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

import com.wing.mainApp.dao.cDAO;
import com.wing.mainApp.data.cListData;
import com.wing.mainApp.util.PagingUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class cListController {

	@Autowired
	cDAO lDao;
	
	// 목록
	@RequestMapping("/cList/classList.do")
	public ModelAndView classList(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		String strPage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strPage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
			
		int	total = lDao.getTotal(1);
		PagingUtil	pInfo = new PagingUtil(nowPage, total, 5, 5);
		pInfo.pagingProc();
		
		ArrayList	list = lDao.getClassList();
		
		int	start = (pInfo.nowPage -1) * pInfo.onePageCount;
		int	end = start + pInfo.onePageCount -1;
		if(end >= list.size()) {
			end = list.size() -1;
		}
		
		ArrayList	result = new ArrayList();
		for(int i = start; i <= end; i++) {
			cListData	temp = (cListData)list.get(i);
			result.add(temp);
		}
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		
		mv.setViewName("cList/classList");
		return mv;
	}
	// 글쓰기 폼
	@RequestMapping("/cList/classWriteForm.do")
	public ModelAndView classWriteForm(HttpServletRequest req, HttpSession session){
		ModelAndView	mv = new ModelAndView();
		
		mv.setViewName("cList/classWriteForm");
		return mv;
	}
	
	// 글쓰기
	@RequestMapping("/cList/classWrite")
	public ModelAndView classWrite(HttpSession session, cListData data){
		ModelAndView	mv = new ModelAndView();
		
		data.id = (String) session.getAttribute("ID");
		lDao.insertclass(data);
		
		RedirectView	rv = new RedirectView("../cList/classList.do");
		mv.setView(rv);
		return mv;
	}
	// 상세
	@RequestMapping("/cList/classView")
	public ModelAndView classView(HttpServletRequest req, cListData data){
		ModelAndView	mv = new ModelAndView();
		
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
		String	strPage = req.getParameter("nowPage");
		int	nowPage = Integer.parseInt(strPage);
		String	kind = req.getParameter("flag");
		
		cListData map = lDao.selectView(oriNo);
		
		mv.addObject("DATA", map);
		mv.addObject("NOWPAGE", nowPage);
		mv.addObject("KIND", kind);
		mv.setViewName("cList/classView");
		return mv;
	}
	// 삭제
	@RequestMapping("/cList/classDelete.do")
	public ModelAndView classDelete(HttpServletRequest req){
		ModelAndView	mv = new ModelAndView();
		
		String strPage = req.getParameter("nowPage");
		String	strNo = req.getParameter("oriNo");
		int	nowPage = Integer.parseInt(strPage);
		int	oriNo = Integer.parseInt(strNo);
		
		HashMap	map = new HashMap();
		map.put("NO",  oriNo);
		int cnt	= lDao.isUpdate(map);
		if(cnt == 0) {
			RedirectView rv = new RedirectView("../cList/classView.do");
			rv.addStaticAttribute("oriNo", oriNo);
			rv.addStaticAttribute("nowPage", nowPage);
			return mv;
		}
		else {
			lDao.deleteclass(oriNo);
		}
		RedirectView	rv = new RedirectView("../cList/classList.do");
		mv.setView(rv);
		
		return mv;
	}
	// 수정하기 폼 요청
	@RequestMapping("/cList/classModifyForm.do")
	public ModelAndView classModifyForm(HttpServletRequest req, cListData data){
		ModelAndView	mv = new ModelAndView();
		
//		String strPage = req.getParameter("nowPage");
		String strNo = req.getParameter("oriNo");
//		System.out.println(strPage);
//		
//		int nowPage = Integer.parseInt(strPage);
		int	oriNo = Integer.parseInt(strNo);
		data.no = oriNo;
		System.out.println(oriNo);
		System.out.println(data.oriNo);
		System.out.println(data.no);
		
		
		HashMap	map = new HashMap();
		map.put("NO", oriNo);
		int cnt = lDao.isUpdate(map);
		cListData	result = new cListData();
		if(cnt == 0) {
			RedirectView	rv = new RedirectView("../cList/classView.do");
			rv.addStaticAttribute("oriNo", oriNo);
//			rv.addStaticAttribute("nowPage", nowPage);
			mv.setView(rv);
			return mv;
		}
		else {
			result = lDao.selectView(oriNo);
		}
		
		mv.addObject("DATA", result);
//		mv.addObject("NOWPAGE", nowPage);
		mv.setViewName("cList/classModifyForm");
		return mv;
	}
	// 수정
	@RequestMapping("/cList/classModify")
	public ModelAndView	classModify(cListData data) {
		ModelAndView	mv = new ModelAndView();
		
		lDao.updateclass(data);
		
		RedirectView	rv = new RedirectView("../cList/classView.do");
		rv.addStaticAttribute("oriNo", data.no);
//		rv.addStaticAttribute("nowPage", data.nowPage);
		mv.setView(rv);
		return mv;
	}
}
