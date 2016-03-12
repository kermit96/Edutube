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

import com.wing.mainApp.dao.ClassListDAO;
import com.wing.mainApp.data.ClassListData;
import com.wing.mainApp.util.PagingUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class ClassListController {

	@Autowired
	ClassListDAO lDao;
	
	// 목록보기 요청
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/ClassList/ClassList.do")
	public ModelAndView classList(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
				String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		String code = req.getParameter("code");
		if(StringUtil.isNull(code)){
			code = "default";
		}	
		
		int	total = lDao.getTotal(code);
		PagingUtil	pInfo = new PagingUtil(nowPage, total, 10, 5);
		pInfo.pagingProc();
		
		ArrayList	list = lDao.getClassList(code);
		
		int	start = (pInfo.nowPage -1) * pInfo.onePageCount;
		int	end = start + pInfo.onePageCount -1;
		if(end >= list.size()) {
			end = list.size() -1;
		}
		
		ArrayList	result = new ArrayList();
		for(int i = start; i <= end; i++) {
			ClassListData	temp = (ClassListData)list.get(i);
			result.add(temp);
		}
		
		mv.addObject("CODE",code);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		
		mv.setViewName("ClassList/ClassList");
		return mv;
	}
	/**
	 * 
	 * 03-10 완료
	 * */
	
	
	@SuppressWarnings("rawtypes")
	// 글쓰기 폼 요청
	@RequestMapping("/ClassList/ClassWriteForm.do")
	public ModelAndView classWriteForm(HttpServletRequest req, HttpSession session){
		ModelAndView	mv = new ModelAndView();
	
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		mv.addObject("CODE",req.getParameter("code"));
		mv.addObject("nowPage",nowPage);
		
		ArrayList result = lDao.getSubList();		
		
		mv.addObject("SUBLIST",result);
		
		mv.setViewName("ClassList/ClassWriteForm");
		return mv;
	}
	/**
	 * 
	 * 03-10 완료
	 * */
	
	// 글쓰기 요청
	@RequestMapping("/ClassList/ClassWrite")
	public ModelAndView classWrite(HttpSession session, ClassListData data,HttpServletRequest req ){
		ModelAndView	 mv = new ModelAndView();
		
		// 동영상 주소가 있는 경우
		int kind = 1;
		String url = data.mediaURL.replaceAll("\\s", "");
		
		// 동영상 주소가 없는 경우
		if(url == null || url.equals("")){			
			kind = 0;
		}
		
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		mv.addObject("CODE",req.getParameter("code"));
		mv.addObject("nowPage",nowPage);
		
		data.id = (String) session.getAttribute("ID");
		data.nick = (String) session.getAttribute("NICKNAME");
				
		String[] temp = data.code.split(",");
		if(temp[0].equals(req.getParameter("code"))) {
			data.code = temp[1];
		}
		else {
			data.code = temp[0];
		}
		
		lDao.insertclass(data,kind);
		
		
		mv.setViewName("/ClassList/ClassWrite");
		
		return mv;
	}
	/**
	 * 
	 * 03/11 대충완료(더작업해야함)
	 * */
	
	// 상세보기 요청
	@SuppressWarnings("rawtypes")
	@RequestMapping("/ClassList/ClassView")
	public ModelAndView classView(HttpServletRequest req, ClassListData data){
		ModelAndView	mv = new ModelAndView();
		
		mv.addObject("CODE",req.getParameter("code"));
		
		String	strNo = req.getParameter("oriNO");
		int	oriNo = Integer.parseInt(strNo);		
		
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		ClassListData map = lDao.selectView(oriNo);
		
		ArrayList mList = lDao.selectMediaList(oriNo);
		
		boolean isExist = true;
		if(mList == null){
			isExist = false;
		}
		
		mv.addObject("isExist",isExist);
		mv.addObject("mList",mList);
		mv.addObject("DATA", map);
		mv.addObject("nowPage", nowPage);
		
		mv.setViewName("ClassList/ClassView");
		return mv;
	}
	/**
	 *  03/12 완료
	 * 
	 * */
	
	// 삭제 요청
	@RequestMapping("/ClassList/ClassDelete.do")
	public ModelAndView classDelete(HttpServletRequest req,HttpSession session){
		
		ModelAndView	mv = new ModelAndView();
		
		String subcode = req.getParameter("code");
				
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);		
		
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		
		lDao.deleteclass(oriNo);
				
		mv.setViewName("ClassList/ClassDelete");
		mv.addObject("CODE",subcode);
		mv.addObject("nowPage",nowPage);
		
		return mv;
	}
	/**
	 *  03/12 완료
	 * 
	 * */
	
	@SuppressWarnings("rawtypes")
	// 수정하기 폼 요청
	@RequestMapping("/ClassList/ClassModifyForm.do")
	public ModelAndView classModifyForm(HttpServletRequest req, HttpSession session){
		ModelAndView	mv = new ModelAndView();
		
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
		
		String subcode = req.getParameter("code");
		
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		mv.addObject("listCode",subcode);
		mv.addObject("nowPage",nowPage);
		
		
		ClassListData map = lDao.selectModi(oriNo);
		ArrayList result = lDao.getSubList();		
		
		mv.addObject("oriNO",oriNo);
		mv.addObject("DATA", map);
		mv.addObject("SUBLIST",result);
		
		mv.setViewName("ClassList/ClassModifyForm");
		return mv;
	}
	/**
	 *  03/12 완료
	 * 
	 * */
	
	
	
	// 수정 요청
	@RequestMapping("/ClassList/ClassModify")
	public ModelAndView	 classModify(ClassListData data,HttpServletRequest req, HttpSession session) {
		ModelAndView	mv = new ModelAndView();
	
		String listcode = req.getParameter("listCode");
		
		String	strNo = req.getParameter("oriNO");
		int	oriNo = Integer.parseInt(strNo);
		data.no = oriNo;
		
		String strpage = req.getParameter("nowPage");
		int	nowPage = 0;
		if(StringUtil.isNull(strpage)){
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strpage);
		}
		
		// 동영상 주소가 있는 경우
		int kind = 1;
		String url = data.mediaURL.replaceAll("\\s", "");
		
		// 동영상 주소가 없는 경우
		if(url == null || url.equals("")){			
			kind = 0;
		}
		
		lDao.updateclass(data);
		
		mv.addObject("code",listcode);
		mv.addObject("oriNO",oriNo);
		mv.addObject("nowPage",nowPage);
		
		return mv;
	}
	
	
}
