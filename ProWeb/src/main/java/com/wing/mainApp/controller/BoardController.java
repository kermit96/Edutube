package com.wing.mainApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.BoardDAO;
import com.wing.mainApp.data.BoardData;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.SessionUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class BoardController {
	@Autowired
	BoardDAO bDao;
	
	/*
	 * FAQ 목록보기
	 */
	@RequestMapping("/CustomerBoard/FAQBoardList")
	public ModelAndView FAQBoardList(HttpServletRequest req) {
		ModelAndView	mv = new ModelAndView();
		// 회원이 아니여도 FAQ를 볼 수 있어야 한다.
		String	strPage = req.getParameter("nowPage");
		int			nowPage = 0;
		
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		int total = bDao.getTotal(1);
		
		ArrayList list = bDao.selectFAQ();
		ArrayList result = new ArrayList();
		for(int i = 0; i < total; i++) {
			BoardData		temp = (BoardData)list.get(i);
			result.add(temp);
		}
		
		mv.addObject("LIST", result);
		mv.setViewName("CustomerBoard/FAQBoardList");
		
		return mv;
	}
	
	/*
	 * FAQ 등록 폼 요청 처리 함수(관리자)
	 */
	@RequestMapping("/CustomerBoard/FAQBoardWriteForm")
	public ModelAndView FAQBoardWriteForm(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		//	관리자만 사용할 수 있다
		
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/LoginForm.do");
			mv.setView(rv);
			return mv;
		}
		
		/*else if(!SessionUtil.isAdmin(session)) {
			RedirectView	rv = new RedirectView("../CustomerBoard/FAQBoardWriteForm.do");
			mv.setView(rv);
			return mv;
		}
		else {
			RedirectView	rv = new RedirectView("/CustomerBoard/FAQBoardList.do");
			mv.setView(rv);
		}*/
		
		mv.setViewName("/CustomerBoard/FAQBoardWriteForm");
		return mv;
	}
 	
	/*
	 * FAQ 등록 요청(관리자)
	 */
	@RequestMapping("/CustomerBoard/FAQBoardWrite")
	public ModelAndView FAQBoardWrite(HttpServletRequest req, BoardData data) {
		ModelAndView mv = new ModelAndView();
		
		//	관리자만 사용할 수 있다.
		/*if(!SessionUtil.isSession(session)) {
		RedirectView	rv = new RedirectView("../Member/LoginForm.mainApp");
		mv.setView(rv);
		return mv;
		}*/
		
		bDao.insertFAQ(data);
		// 뷰를 부른다
		RedirectView rv = new RedirectView("../CustomerBoard/FAQBoardList.do");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * QA목록보기 
	 */
	@RequestMapping("/CustomerBoard/QABoardList")
	public ModelAndView QABoardList(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		//	회원, 관리자만 사용할 수 있다.
		/*if(!SessionUtil.isSession(session)) {
		RedirectView	rv = new RedirectView("../Member/LoginForm.mainApp");
		mv.setView(rv);
		return mv;
		}*/
		String strPage = req.getParameter("nowPage");
		int	nowPage = 0;
		
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		int total = bDao.getTotal(2);
		
		PageUtil		pInfo = new PageUtil(nowPage, total, 10, 10);
		pInfo.calcInfo();
		
		ArrayList list = bDao.selectQA();
		
		int		start = (pInfo.nowPage - 1) * pInfo.pageList;
		int		end = start + pInfo.pageList - 1;
		
		if(end >= list.size()) {
			end = list.size() - 1;
		}
		
		ArrayList result = new ArrayList();
		
		for(int i = start; i <= end; i++) {
			BoardData		temp = (BoardData)list.get(i);
			result.add(temp);
		}
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		mv.setViewName("CustomerBoard/QABoardList");
		
		return mv;
	}
	 
	/*
	  * QA글쓰기 폼 요청
	  */
	@RequestMapping("/CustomerBoard/QABoardWriteForm")  
	public ModelAndView QABBardWriteForm() {
		ModelAndView mv = new ModelAndView();
		
		//	회원, 관리자만 사용할 수 있다.
		/*if(!SessionUtil.isSession(session)) {
		RedirectView	rv = new RedirectView("../Member/LoginForm.mainApp");
		mv.setView(rv);
		return mv;
		}*/
		
		mv.setViewName("/CustomerBoard/QABoardWriteForm");
		return mv;
	}
	
	 /*
	  * QA글쓰기 요청
	  */
	@RequestMapping("/CustomerBoard/QABoardWrite")  
	public ModelAndView QABoardWrite(BoardData data) {
		ModelAndView mv  = new ModelAndView();
		
		int topNo = bDao.searchNO();
		data.top = topNo;
		
		bDao.insertQA(data);
		// 뷰를 부른다
		RedirectView rv = new RedirectView("../CustomerBoard/QABoardList.do");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * QA상세보기 요청
	 */
	@RequestMapping("/CustomerBoard/QABoardView")
	public ModelAndView QABoardView(HttpServletRequest req) {
		ModelAndView	mv = new ModelAndView();
		//	권한검사 해야지
		
		String	strNo = req.getParameter("oriNo");
//		System.out.println("strNO다~~~~~~~~~~~~~"+strNo);
		int			oriNO = Integer.parseInt(strNo);
		String	strPage = req.getParameter("nowPage");
//		System.out.println("strPage다~~~~~~~~~~~~~~"+strPage);
//		int			nowPage = Integer.parseInt(strPage);
		
		BoardData		map = bDao.viewQA(oriNO);
		
		mv.addObject("DATA", map);
//		mv.addObject("NOWPAGE", nowPage);
		mv.setViewName("/CustomerBoard/QABoardView");
		return mv;
	}
	
	/*
	 * QA답글달기폼 요청
	 */
	@RequestMapping("/CustomerBoard/QABoardRepleForm")
	public ModelAndView QABoardRepleForm(HttpServletRequest req) {
		ModelAndView	mv = new ModelAndView();
		
		
		String strNO = req.getParameter("oriNo");
		int oriNO = Integer.parseInt(strNO);
		
		
		mv.addObject("ORINO" , oriNO);
		mv.setViewName("/CustomerBoard/QABoardRepleForm");
		return mv;
	}
	
	/*
	 * QA답글달기 요청
	 */
	@RequestMapping("/CustomerBoard/QABoardReple")
	public ModelAndView QABoardReple(BoardData data) {
		ModelAndView	mv = new ModelAndView();
		
		BoardData temp = bDao.viewQA(data.no);
		data.top = temp.top;
		data.mid = temp.mid +1;
		data.bottom = temp.bottom +1;
		
		bDao.insertQA(data);
		
		// 뷰를 부른다
		RedirectView rv = new RedirectView("../CustomerBoard/QABoardList.do");
	
		mv.setView(rv);
		
		return mv;
	}
	
	/*
	 * 검색 요청 처리 함수
	 */
	@RequestMapping("/CustomerBoard/QABoardSearch")
	public ModelAndView QABoardSearch(HttpServletRequest req, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String 	srtPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(srtPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(srtPage);
		}
		String 	kind = req.getParameter("kind");
		String	content = req.getParameter("content");
		
		if(StringUtil.isNull(kind)) {
			
			kind = (String) session.getAttribute("kind");
			content = (String) session.getAttribute("content");
			if(StringUtil.isNull(kind)) {
			}
		}
		session.setAttribute("kind", kind);
		session.setAttribute("content", content);
		
		HashMap	map = new HashMap();
		map.put("kind", kind);
		map.put("CONTENT", content);
		
		int count = bDao.searchCount(map);
		PageUtil	pInfo = new PageUtil(nowPage, count, 10, 10);
		pInfo.calcInfo();
		
		ArrayList	list = bDao.getSearch(map);
		ArrayList	result = new ArrayList();
		if(list.size() != 0) {
			int		start = (pInfo.nowPage - 1) * pInfo.pageList;
			int		end = start + pInfo.pageList - 1;
			if(end >= list.size()) {
				end = list.size() - 1;
			}
			for(int i = start; i <= end; i++) {
				result.add(list.get(i));
			}
		}
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		mv.setViewName("/CustomerBoard/QABoardSearch");
		
		return mv;
		
	}
}
