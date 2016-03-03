package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.cDAO;
import com.wing.mainApp.data.cListData;
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
}
