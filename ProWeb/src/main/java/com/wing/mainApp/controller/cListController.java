package com.wing.mainApp.controller;

import java.util.ArrayList;

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
}
