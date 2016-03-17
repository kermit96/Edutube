package com.wing.mainApp.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.MessageDAO;
import com.wing.mainApp.data.MessageData;

@Controller
public class MessageContoller {
	@Autowired
	MessageDAO mDao;
	
	// 메세지 폼 요청
	@RequestMapping("Message/MsgForm")
	public ModelAndView WhatMessageForm(MessageData data, HttpServletRequest req){
		ModelAndView mv= new ModelAndView();
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		String toid = mDao.selectId(no);
		System.out.println(no+":"+toid);
		mv.addObject("toid",toid);
		mv.setViewName("/Message/MsgForm");
		return mv;
	}
	// 메세지 보내는 기능함수
	@RequestMapping("Message/Messagego")
	public ModelAndView Messagego(MessageData data){
		ModelAndView mv = new ModelAndView();
		System.out.println(data.getToid());
		System.out.println(data.getFromid());
		mDao.sendMessage(data);
		System.out.println("메세지 전송 완료");
		mv.setViewName("Message/Messagego");
		return mv;
	}
	// 메세지 온거 확인 폼
	@RequestMapping("Message/MsgList")
	public ModelAndView MsgReForm(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		String id = req.getParameter("id");
		ArrayList list = mDao.selectMsgList(id);
		mv.addObject("LIST",list);
		mv.setViewName("Message/MsgList");
		return mv;
	}
	// 메세지 상세보기
	@RequestMapping("Message/MsgView")
	public ModelAndView msgView(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		String strNo = req.getParameter("no");
		System.out.println(strNo);
		int no = Integer.parseInt(strNo);
		MessageData data=mDao.msgView(no);
		System.out.println("msgView 실행됫다");
		// 확인여부 Y로 바꾸기
		mDao.msgCom(no);
		mv.addObject("no",no);
		mv.addObject("LIST",data);
		mv.setViewName("Message/MsgView");
		return mv;
	}
	// 메세지 보낸거 확인폼
	@RequestMapping("Message/MsgSeList")
	public ModelAndView MsgSeForm(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		String id = req.getParameter("id");
		ArrayList list = mDao.selectSeMsgList(id);
		mv.addObject("LIST",list);
		mv.setViewName("Message/MsgSeList");
		return mv;
	}
	// 메세지 답장
	@RequestMapping("Message/NewForm")
	public ModelAndView NewForm(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		String fromid=req.getParameter("fromid");
		String toid = req.getParameter("toid");
		System.out.println(fromid);
		System.out.println(toid);		
		mv.addObject("toid", toid);
		mv.setViewName("Message/NewForm");
		return mv;
	}
	// 메세지 삭제
	@RequestMapping("Message/delM")
	public ModelAndView delM(HttpServletRequest req){
		String Sno = req.getParameter("no");
		ModelAndView mv = new ModelAndView();
		int no = Integer.parseInt(Sno);
		mDao.delM(no);
		String fromid = req.getParameter("fromid");
		RedirectView view = new RedirectView("../Message/MsgList.do?id="+fromid);
		mv.setView(view);
		return mv;
	}
}
