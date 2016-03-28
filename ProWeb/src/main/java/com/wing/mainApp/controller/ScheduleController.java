package com.wing.mainApp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.ScheduleDAO;
import com.wing.mainApp.data.ScheduleData;
import com.wing.mainApp.util.SessionUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class ScheduleController {
	
	/**
	 * 03/18 (이 주석이 있어야 완료 된것
	 * */
	
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
	
	/**
	 * 
	 * 스케쥴 리스트
	**/
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/Sch/SchModiList")
	public ModelAndView modiSch(HttpServletRequest req,HttpSession session){
		ModelAndView mv = new  ModelAndView();
	
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		String strno = req.getParameter("oriNo");
		int oriNo = Integer.parseInt(strno);
		
		String mid = req.getParameter("mid");
		ArrayList list = schdao.selectEvents(mid);	
		
		mv.addObject("orino",oriNo);
		mv.addObject("nowPage",nowPage);
		mv.addObject("eventList",list);
		mv.setViewName("Sch/SchModiList");
		
		return mv;
	}
	/**
	 *  스케쥴 삭제
	 * */
	@RequestMapping("/Sch/SchDelete")
	public ModelAndView eventDelete(HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
		String streno = req.getParameter("eventNo");
		
		int eventNO = Integer.parseInt(streno);
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		String strno = req.getParameter("oriNo");
		int oriNo = Integer.parseInt(strno);
		// 기본 릴레이
		
		
		schdao.deleteEvent(eventNO);
		
		mv.addObject("nowPage",nowPage);
		mv.addObject("oriNo",oriNo);
				
		mv.setViewName("/Sch/SchDelete");
		
		return mv;
	}
	/**
	 * 스케쥴 추가폼 요청
	 * */
	@RequestMapping("/Sch/SchAddForm")
	public ModelAndView eventAddForm(HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
	
		/*비회원 로그인고*/
		/*if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}*/
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		String strno = req.getParameter("oriNo");
		int oriNo = Integer.parseInt(strno);
		// 기본 릴레이
		String memid=req.getParameter("memid");
		
		mv.addObject("memid",memid);
		mv.addObject("nowPage",nowPage);
		mv.addObject("oriNo",oriNo);
		mv.setViewName("/Sch/SchAddForm");
		
		return mv;
	}
	/**
	 *  스케쥴 실제로 추가
	 * */
	@RequestMapping("/Sch/SchAdd")
	public ModelAndView eventAdd(HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		String strno = req.getParameter("oriNo");
		int oriNo = Integer.parseInt(strno);
		
		ScheduleData data = new ScheduleData();
		data.id = req.getParameter("mid");
		data.name = req.getParameter("eventname");
		String ssDate = req.getParameter("sdate");
		String eeDate = req.getParameter("edate");
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate=null;
		Date edate=null;
		try {
			sdate = transFormat.parse(ssDate);
			edate = transFormat.parse(eeDate);			
		} catch (ParseException e) {
			System.out.println("(일정)날짜변환 실패:" + e);
			e.printStackTrace();
		}
		data.sdate=sdate;
		data.edate=edate;
		
		schdao.insertEvent(data);		
		
		mv.addObject("nowPage",nowPage);
		mv.addObject("oriNo",oriNo);
		mv.setViewName("/Sch/SchAdd");
		
		return mv;
	}
	/**
	 * 03/18 (이 주석이 있어야 완료 된것
	 * */

}
