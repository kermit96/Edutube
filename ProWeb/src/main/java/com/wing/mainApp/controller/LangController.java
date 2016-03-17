package com.wing.mainApp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.wing.mainApp.util.StringUtil;

@Controller
public class LangController {
	@RequestMapping("/lang/changelang")
	public void changelang(HttpServletRequest request,HttpServletResponse response  ,HttpSession session){
		
		
		
		String lang =  request.getParameter("lang");
		

		
		if (StringUtil.isNull(lang)) 
			return;
		
		session.setAttribute("LANG", lang);
		
		response.addCookie(new Cookie("LANG",lang));
		
		try 
		{
	       response.getWriter().print("");   		
			
		} catch (Exception ex)
		{
			
			
		}
		

	}
		
}
