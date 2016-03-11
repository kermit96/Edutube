package com.wing.mainApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyPageController {
	
	@RequestMapping("/myPage/myPageMain")
	public ModelAndView myPageMain(){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("myPage/myPageMain");
		return mv;
	}

}
