package com.wing.mainApp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.dao.ClassListDAO;
import com.wing.mainApp.util.PagingUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class ClassListController2 {

	@Autowired
	ClassListDAO lDao;
	
/*	@RequestMapping("/ClassList/ClassList.do")
	public ModelAndView classList(HttpServletRequest req, HttpSession session){
		
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		String code = req.getParameter("code");
		if(StringUtil.isNull(code)){
			code = "default";
		}	
		
		int	total = lDao.getTotal(code);
		PagingUtil	pInfo = new PagingUtil(nowPage, total, 10, 5);
		pInfo.pagingProc();
		
		ArrayList	list = lDao.getClassList(code);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/ClassList/ClassList");
		
		
		return mv;
	}*/
	
}
