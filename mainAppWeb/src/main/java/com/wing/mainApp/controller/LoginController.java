package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/Login/login")
	public ModelAndView login(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		System.out.println("login...");
		//session.invalidate();
		mv.setViewName("Login/login");
		return mv;
	}
	@RequestMapping("/Login/logout")
	public void logout(HttpSession session,HttpServletResponse response) {
		//ModelAndView		mv = new ModelAndView();
		System.out.println("logout...");
		session.invalidate();
		try{
			System.out.println("homefdfd");
		response.sendRedirect("../index.jsp");
		}
		catch(Exception e){
		System.out.println("logout"+e);
		}
	}
}
