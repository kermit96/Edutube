package com.wing.mainApp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.dao.ScheduleDAO;

@Controller
public class ScheduleController {
	
	@Autowired
	ScheduleDAO schdao;
	
	@RequestMapping("/Sch/SchList")
	public ModelAndView schduleList(){
		ModelAndView mv = new ModelAndView();
		
		// �޷� �ִ� ������ ȣ��
		mv.setViewName("/Sch/SchList");		
		return mv;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/Sch/loadEvents")
	public ModelAndView eventsList(HttpServletRequest req){
		
		ModelAndView mv = new ModelAndView(); 
		String id = req.getParameter("lecMid");			
		// ���� ȣ��	�ѵ�	
		ArrayList list = schdao.selectEvents(id);			
				
		// events.jsp (xml ����) �� ����
		mv.addObject("eventL",list);
		mv.setViewName("/Sch/events");
		
		return mv;
	}
	
	

}
