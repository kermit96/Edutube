package com.wing.mainApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class testController {

/*@Autowired*/
	
	@RequestMapping("/testLogin")
	public ModelAndView testLogin(){
	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/testLogin");
		return mv;		
	}
	
	@RequestMapping("/Sch/debugList")
	public ModelAndView debugList(){
	
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/Sch/debugList");
		return mv;		
	}
	
}
