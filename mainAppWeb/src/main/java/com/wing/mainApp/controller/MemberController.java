package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	/*
	 * ï¿½ï¿½ï¿½ï¿½ ï¿½Ô½ï¿½ï¿½ï¿½ ï¿½ï¿½ ï¿½ï¿½Ã» ï¿½Ô¼ï¿½
	 */
	@RequestMapping("/Member/member_form")//../Member/member_form.dol
	public ModelAndView memberform() {
		ModelAndView		mv = new ModelAndView();
		System.out.println("member_form");
		mv.setViewName("Member/member_form");
		return mv;
	}
	@RequestMapping("/Member/member_check")
	public ModelAndView membercheck(HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView		mv = new ModelAndView();
		String id="charmvit";
		String pwd="1234";
		String name="Â÷ÁØÈ£";

		if(id.equals(request.getParameter("id")) &&
				pwd.equals(request.getParameter("pwd")) ){
			
			session.setAttribute("loginUser",name);
			session.setAttribute("loginId",id);
			System.out.println("session saved");
			mv.setViewName("../home");
			
		}
		else{
			
			System.out.println("session not equal..");
			//response.sendRedirect("index.jsp");
			
		}

		System.out.println("member_check");
		mv.setViewName("Member/member_check");
		return mv;
	}
	
	@RequestMapping("home")
	public void home(HttpServletResponse response) {
		//ModelAndView		mv = new ModelAndView();
		System.out.println("home");
		//mv.setViewName("../home");
		try{
			System.out.println("homefdfd");
		response.sendRedirect("../index.jsp");
		}
		catch(Exception e){
		System.out.println("home"+e);
		}
		//return mv;
	}

}