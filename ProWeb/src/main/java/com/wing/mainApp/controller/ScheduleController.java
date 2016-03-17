package com.wing.mainApp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.ScheduleDAO;
import com.wing.mainApp.util.SessionUtil;

@Controller
public class ScheduleController {
	
	@Autowired
	ScheduleDAO schdao;
	
	@RequestMapping("/Sch/SchList")
	public ModelAndView schduleList(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		
		String id = req.getParameter("id");
		System.out.println(id);
		mv.addObject("ID",id);
		
		mv.setViewName("/Sch/SchList");		
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/Sch/loadEvents")
	public ModelAndView eventsList(HttpServletRequest req){
		
		ModelAndView mv = new ModelAndView(); 
		String id = req.getParameter("lecMid");			
		// 	
		ArrayList list = schdao.selectEvents(id);			
				
		// events.jsp 
		mv.addObject("eventL",list);
		mv.setViewName("/Sch/events");
		
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/Sch/SchModiForm")
	public ModelAndView modiSch(HttpServletRequest req,HttpSession session){
		ModelAndView mv = new  ModelAndView();
				
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
		String mid = req.getParameter("mid");
		ArrayList list = schdao.selectEvents(mid);	
		
		mv.addObject("eventList",list);
		mv.setViewName("Sch/SchModiForm");
		
		return mv;
	}
	
	

}
