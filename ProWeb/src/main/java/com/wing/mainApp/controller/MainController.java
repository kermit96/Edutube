package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	
	@RequestMapping("/main.do")
	public ModelAndView mainPage(HttpServletRequest req){
		ModelAndView mv  = new ModelAndView();
		mv.setViewName("/index");		
		
		return mv;
	}
}
