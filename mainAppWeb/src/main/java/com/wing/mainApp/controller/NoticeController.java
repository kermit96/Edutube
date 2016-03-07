package com.wing.mainApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.NoticeDAO;
import com.wing.mainApp.data.NoticeData;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.SessionUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class NoticeController {
	@Autowired
	private NoticeDAO	nDao;//파일 정보와 기타 정보
		
	@RequestMapping("/Notice/NoticeRegForm")
	public ModelAndView	noticeRegForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
	    System.out.println("NoticeRegForm");
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}		
		
		mv.setViewName("Notice/NoticeRegForm");
		return mv;
	}
	/*
	 * 	
	 */
	@RequestMapping("/Notice/NoticeReg")
	public ModelAndView	noticeWrite(HttpSession session, NoticeData data) {
		ModelAndView		mv = new ModelAndView();
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		String	id = (String) session.getAttribute("loginId");//Joon
		data.setMem_id(id);//강사 ID 입력
						
		nDao.insertNotice(data);
		//
		RedirectView	rv = new RedirectView("../Notice/NoticeList.do");
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/Notice/NoticeList")
	public ModelAndView		noticeList(HttpServletRequest req, HttpSession session,NoticeData data) {
		ModelAndView		mv = new ModelAndView();
		int Curr_Page=0;
		Curr_Page=1;
		//로그인한 사람만 목록을 보여주고 싶으면.....
			if(!SessionUtil.isSession(session)) {
				RedirectView	rv = new RedirectView("../Member/member_form.do");
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
			//	
			int	total = nDao.getTotal(1);
			PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
			pInfo.calcInfo();
			//	
			//		
			//									start		end
			//		nowPage	1�럹�씠吏�		1 ~ 		5
			//					2�럹�씠吏�		6 ~ 		10
			
			int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
			int	end = start + pInfo.pageList - 1;
			
			if(end > pInfo.totalCount) {
				end = pInfo.totalCount;
			}
			
			HashMap	map = new HashMap();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList	list = nDao.selectNoticeList(map);
			
			for(Object data2 :list) {
			  NoticeData notice = (NoticeData)data2;
			  System.out.println("is notice==>"+notice);
			  System.out.println("notice==>"+notice.getNotice_title());
				
			}
			System.out.println("list-size"+list.size());
			//		뷰를 선택한다.
			//	뷰에게 전달할 내용을 준다.
			mv.addObject("PINFO", pInfo);
			mv.addObject("LIST", list);
			mv.addObject("NOTICE",Curr_Page);
		mv.setViewName("Notice/NoticeList");
		return mv;
	}
	@RequestMapping("/Notice/NoticeView")
	public ModelAndView	noticeView(HttpServletRequest req, HttpSession session,NoticeData data) {
		ModelAndView		mv = new ModelAndView();
		int Curr_Page=0;
		Curr_Page=1;
		//로그인한 사람만 목록을 보여주고 싶으면.....
			if(!SessionUtil.isSession(session)) {
				RedirectView	rv = new RedirectView("../Member/member_form.do");
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
			//	
			int	total = nDao.getTotal(1);
			PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
			pInfo.calcInfo();
			//	
			//		
			//									start		end
			//		nowPage	1�럹�씠吏�		1 ~ 		5
			//					2�럹�씠吏�		6 ~ 		10
			
			int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
			int	end = start + pInfo.pageList - 1;
			
			if(end > pInfo.totalCount) {
				end = pInfo.totalCount;
			}
			
			HashMap	map = new HashMap();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList	list = nDao.selectNoticeList(map);
			
			for(Object data2 :list) {
			  NoticeData notice = (NoticeData)data2;
			  System.out.println("is notice==>"+notice);
			  System.out.println("notice==>"+notice.getNotice_title());
				
			}
			System.out.println("list-size"+list.size());
			//		뷰를 선택한다.
			//	뷰에게 전달할 내용을 준다.
			mv.addObject("PINFO", pInfo);
			mv.addObject("LIST", list);
			mv.addObject("NOTICE",Curr_Page);
		mv.setViewName("Notice/NoticeView");
		return mv;
	}
	
	
}

