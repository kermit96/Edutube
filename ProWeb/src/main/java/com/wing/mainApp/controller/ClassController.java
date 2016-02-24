package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {
	@RequestMapping("/classlist/classmain.do")
	public ModelAndView classmain(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/classmain");
		return mv;
	}
}