package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {
	
	@RequestMapping("/Info/infomain.do")
	public ModelAndView infomain(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("Info/infomain");
		return mv;
	}
	@RequestMapping("/Info/info.do")
	public ModelAndView info(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("Info/info");
		return mv;
	}
	@RequestMapping("/Info/map.do")
	public ModelAndView map(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("Info/map");
		return mv;
	}
	@RequestMapping("/Info/use.do")
	public ModelAndView use(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("Info/use");
		return mv;
	}
	
}
