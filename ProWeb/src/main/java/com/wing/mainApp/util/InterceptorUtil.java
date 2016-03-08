package com.wing.mainApp.util;

import java.io.File;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wing.mainApp.util.config.ConfigFileHandler;

public class InterceptorUtil extends HandlerInterceptorAdapter  {
  
	@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub
				
		  HttpSession ses =  request.getSession();
	
		  Cookie[] cookes = request.getCookies();
		  		  
		  return super.preHandle(request, response, handler);
		  
		}

	
	public static synchronized String getInitDirectory() 
	{

		String sPath="";

		sPath = ConfigFileHandler.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath();
		// WEB-INF 위치

		int nFindInx = sPath.indexOf("WEB-INF");

		if (nFindInx > 0)
			sPath = sPath.substring(0, nFindInx + 7);

  
		String sConfPath="";
		sConfPath = sPath + File.separator + "conf";		
		
		File file = new File(sConfPath);
		if (file.exists() == false ) {
			
			file.mkdir();
		}
		
		return sConfPath;
	
		
	
	}
	
	
}
