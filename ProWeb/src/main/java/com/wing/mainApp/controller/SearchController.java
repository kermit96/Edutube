package com.wing.mainApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.dao.ClassListDAO;
import com.wing.mainApp.dao.SearchDAO;
import com.wing.mainApp.data.BoardData;
import com.wing.mainApp.data.ClassListData;
import com.wing.mainApp.util.PagingUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class SearchController {
	@Autowired
	SearchDAO	sDao;
	@Autowired
	ClassListDAO	lDao;
	/*@Autowired*/
	
	/*
	 * 검색질의
	 */
	@RequestMapping("/Search/SearchForm")
	public ModelAndView SearchForm(HttpServletRequest req, HttpSession session, ClassListData data){
		
		ModelAndView mv = new ModelAndView();
		
		/*String url = req.getRequestURL().toString();*/			
		
		String searchBox = req.getParameter("searchBox");				
		String strpage = req.getParameter("nowPage");
		
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		if(StringUtil.isNull(searchBox)) {	
			searchBox = (String) session.getAttribute("searchBox");
			if(StringUtil.isNull(searchBox)) {
			}
		}
		
		session.setAttribute("searchBox", searchBox);
		
		HashMap map = new HashMap();
		map.put("SEARCHBOX", searchBox);
		
		int total = sDao.searchCount(map);
		PagingUtil	pInfo = new PagingUtil(nowPage, total, 10, 5);
		pInfo.pagingProc();
		
		ArrayList list = sDao.getSearch(map);
		int	start = (pInfo.nowPage -1) * pInfo.onePageCount;
		int	end = start + pInfo.onePageCount -1;
		if(end >= list.size()) {
			end = list.size() -1;
		}
		
		ArrayList result = new ArrayList();
		for(int i = start; i <= end; i++) {
			ClassListData		temp = (ClassListData)list.get(i);
			result.add(temp);
		}
		
		if(searchBox==null){			
			mv.setViewName("/main.do");
			return mv;
		}
		
		mv.addObject("CODE",req.getParameter("code"));
		ArrayList code = lDao.getSubList();	
		
		mv.addObject("searchBox", searchBox);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST",result);	
		mv.addObject("SUBLIST",code);
		mv.setViewName("/Search/SearchForm");
		
		return mv;
	}
	
	/*
	 * 상세검색
	 */
	@RequestMapping("/Search/ReSearchForm")
	public ModelAndView ReSearchForm(HttpServletRequest req, HttpSession session, ClassListData data){
		
		ModelAndView mv = new ModelAndView();
		
		String researchBox = req.getParameter("researchBox");
		String subcode= req.getParameter("code");
		String strpage = req.getParameter("nowPage");
		String kind = req.getParameter("kind");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		if(StringUtil.isNull(kind)) {
			kind = (String) session.getAttribute("kind");
			researchBox = (String) session.getAttribute("researchBox");
			if(StringUtil.isNull(kind)) {
			}
		}
		/*session.setAttribute("kind", kind);*/
		session.setAttribute("researchBox", researchBox);
		session.setAttribute("code", subcode);
		
		HashMap map = new HashMap();
		map.put("RESEARCHBOX", researchBox);
		map.put("code", subcode);
		
		int total = sDao.researchCount(map,kind);
		
		PagingUtil	pInfo = new PagingUtil(nowPage, total, 10, 5);
		
		pInfo.pagingProc();
		
		ArrayList list = sDao.getreSearch(map,kind);
		int	start = (pInfo.nowPage -1) * pInfo.onePageCount;
		int	end = start + pInfo.onePageCount -1;
		if(end >= list.size()) {
			end = list.size() -1;
		}
		
		ArrayList result = new ArrayList();
		for(int i = start; i <= end; i++) {
			ClassListData		temp = (ClassListData)list.get(i);
			result.add(temp);
		}
		
		if(researchBox==null){			
			mv.setViewName("/main.do");
			return mv;
		}
		
		mv.addObject("CODE",req.getParameter("code"));
		ArrayList code = lDao.getSubList();	
		
		mv.addObject("researchBox", researchBox);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST",result);	
		mv.addObject("SUBLIST",code);
		mv.setViewName("/Search/ReSearchForm");
		
		return mv;
	}
}
