package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	/** 
	 *  메인페이지 (index.jsp 호출)
	 * */
	@RequestMapping("/main.do")
	public ModelAndView mainPage(HttpServletRequest req, HttpSession session){
		ModelAndView mv  = new ModelAndView();
		
		String id =(String) session.getAttribute("ID");
		StringBuffer buff = new StringBuffer();
		if(id==null || id.length()==0){
			
		}
		else{
			buff.append(id);
			mv.addObject("ID",buff);
			System.out.println(id);
		}
		mv.setViewName("/index");		
		return mv;
	}
}
