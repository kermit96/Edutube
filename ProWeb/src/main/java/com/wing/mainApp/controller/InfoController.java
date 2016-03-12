package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {
	
	@RequestMapping("/Info/InfoMain")
	public ModelAndView InfoMain(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("/Info/InfoMain");
		return mv;
	}
	@RequestMapping("/Info/Map")
	public ModelAndView Map(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("/Info/Map");
		return mv;
	}
	@RequestMapping("/Info/Use")
	public ModelAndView Use(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("/Info/Use");
		return mv;
	}
	
}
