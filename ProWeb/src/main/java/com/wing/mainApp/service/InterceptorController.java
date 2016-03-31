package com.wing.mainApp.service;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wing.mainApp.util.Langxml;
import com.wing.mainApp.util.StringUtil;
import com.wing.mainApp.util.config.ConfigFileHandler;

public class InterceptorController extends HandlerInterceptorAdapter  {
  
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub						
		  HttpSession ses =  request.getSession();		  
		  String lang = (String)ses.getAttribute("LANG");
		  
		  if (StringUtil.isNull(lang)) {

			  Cookie[] cookes = request.getCookies();			  
			// cookes 에서 값을 가졍 온다 .
			  // 값이 없으면   lang 을 ko 로 하고  cookie 에 넣은다 .
			  
			  if(cookes != null ) { 			  
	              for(Cookie cookie :cookes) {
	            	  if (cookie.getName().equals("LANG"))
	            	  {            		  
	            		  lang = cookie.getValue();
	            		  break;            		  
	            	  }            	  
	                }              
			  }
			  
              if (StringUtil.isNull(lang)) {
            	  lang = "ko";
            	  response.addCookie(new Cookie("LANG",lang));             	  
              }              
              ses.setAttribute("LANG", lang);                                          			  
		  }
		  
		   HashMap map = Langxml.load(lang);    	
		    request.setAttribute("LANGDATA", map);		  		  
		  return super.preHandle(request, response, handler);	  
		}

	
}
