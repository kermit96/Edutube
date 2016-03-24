package com.wing.mainApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IntroduceController {

	
	
	@RequestMapping("/Intro/IntroMain")
	public ModelAndView IntroMain(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Intro/IntroMain");
		
		return mv;
	}
	
	@RequestMapping("/Intro/IntroDetail")
	public ModelAndView IntroDetail(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/Intro/IntroDetail");
		
		return mv;
	}
	
}
