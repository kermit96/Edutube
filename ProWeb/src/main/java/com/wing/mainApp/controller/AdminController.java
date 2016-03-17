package com.wing.mainApp.controller;

import com.wing.mainApp.util.SessionUtil;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	/*@Autowired*/
	
	@RequestMapping("/AdminPage/AdminMain")
	public ModelAndView AdminMain(HttpSession session){
		
		ModelAndView mv = new ModelAndView();
		
		/**
		 * 관리자 검사		  
		 * */
		if(!SessionUtil.isAdmin(session)){
			mv.setViewName("/main.do");
			System.out.println("관리자 아니라서 메인으로 보냄");
			return mv;
		}		
		
		mv.setViewName("/AdminPage/AdminMain");
		
		return mv;
	}
	
}



