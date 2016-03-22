package com.wing.mainApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.dao.IntroInfoDAO;
import com.wing.mainApp.dao.MainDAO;
import com.wing.mainApp.data.IntroInfoData;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class MainController {

	@Autowired
	IntroInfoDAO	iDao;
	@Autowired
	MainDAO maDao;
	/** 
	 *  메인페이지 (index.jsp 호출)
	 * */
	@RequestMapping("/main.do")
	public ModelAndView mainPage(HttpServletRequest req, IntroInfoData data, HttpSession session){
		ModelAndView mv  = new ModelAndView();
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;

		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//
		int	total = iDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = iDao.getGood(map);
		ArrayList nolist = maDao.getNotice();
		ArrayList cllist = maDao.getClassd();
		ArrayList qalist = maDao.getQaList();
		ArrayList downlist = maDao.getDown();
		
		mv.addObject("DOWNLIST",downlist);
		mv.addObject("QALIST",qalist);
		mv.addObject("CLLIST",cllist);
		mv.addObject("NOLIST",nolist);
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		
		mv.setViewName("/index");
		return mv;
	}
	
	
}