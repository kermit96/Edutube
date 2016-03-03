package com.wing.mainApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScheduleController {
	/*@Autowired*/
	
	@RequestMapping("/Sch/SchList")
	public ModelAndView schduleForm(){
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("/Sch/SchList");
		
		return mv;
	}

}
