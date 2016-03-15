package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.dao.ClassListDAO;
import com.wing.mainApp.dao.SearchDAO;

@Controller
public class SearchController {
	@Autowired
	SearchDAO sDao;
	@Autowired
	ClassListDAO cDao;
	
	/*@Autowired*/
	
	/*
	 * 검색요청
	 */
	@RequestMapping("/Search/SearchForm")
	public ModelAndView SearchForm(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		
		/*String url = req.getRequestURL().toString();*/			
		
		String keyword = req.getParameter("searchBox");				
		
		if(keyword==null){			
			mv.setViewName("/main.do");
			return mv;
		}
		
		mv.addObject("keyword",keyword);		
		mv.setViewName("/Search/SearchForm");
		
		return mv;
	}

}
