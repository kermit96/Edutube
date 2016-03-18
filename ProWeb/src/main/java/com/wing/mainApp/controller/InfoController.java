package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {
	
	@RequestMapping("/Info/InfoMain")
	public ModelAndView infomain(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();

		mv.setViewName("/Info/InfoMain");
		return mv;
	}
	@RequestMapping("/Info/info")
	public ModelAndView info(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("/Info/info");
		return mv;
	}
	@RequestMapping("/Info/Map")
	public ModelAndView map(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("/Info/Map");
		return mv;
	}
	@RequestMapping("/Info/Use")
	public ModelAndView use(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("/Info/Use");
		return mv;
	}
	
}
