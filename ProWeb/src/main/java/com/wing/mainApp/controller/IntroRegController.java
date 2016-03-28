package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.IntroInfoDAO;
import com.wing.mainApp.data.IntroInfoData;
import com.wing.mainApp.util.FileUtil;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.SessionUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class IntroRegController {
	@Autowired
	private IntroInfoDAO	iDao;//
	
	/**
	 * 03/18 (이 주석이 있어야 완료 된것
	 * */
		
	@RequestMapping("/IntroRegManager/IntroRegForm")
	public ModelAndView	introRegForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		/*
	  	if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/login.do");
			mv.setView(rv);
			return mv;
		}
		*/
		mv.setViewName("IntroRegManager/IntroRegForm");
		return mv;
	}
	/*
	 * 강사 소개글 등록하기	
	 */
	@RequestMapping("/IntroRegManager/IntroReg")
	public ModelAndView	introWrite(HttpSession session, IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../member/login.do");
			mv.setView(rv);
			return mv;
		}
		
		String	id = (String) session.getAttribute("ID");
		data.setMem_id(id);//		
		String	gpath = session.getServletContext().getRealPath("gimgs");
				
		String	gimg1 = data.getGimg().getOriginalFilename();		
		String	gimg2 = "";		
		if(StringUtil.isNull(gimg1)) {
			gimg2 = "";			//	
								//	
		}
		else {
			gimg2 = FileUtil.rename(gpath, gimg1);
			//	
			File temp = new File(gpath, gimg2);
			try {
				data.getGimg().transferTo(temp);
			}
			catch(Exception e) {}
		}		
		//System.out.println("gimg2=" +gimg2);
		data.setGimg2(gimg2);
		
		
		iDao.insertIntroInfo(data);
		//
		RedirectView	rv = new RedirectView("../IntroRegManager/IntroList.do");
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping("/IntroRegManager/IntroLec")
	public ModelAndView		introLec(HttpServletRequest req, HttpSession session,IntroInfoData data){
		ModelAndView		mv = new ModelAndView();
		/*
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/login.do");
			mv.setView(rv);
			return mv;
		}
		*/	
		//mv.setViewName("IntroRegManager/IntroLec");
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroList")
	public ModelAndView		introList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		/*
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/login.do");
			mv.setView(rv);
			return mv;
		}
		*/	
		System.out.println("IntroList");
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		int	total = iDao.getTotal(1);
		System.out.println("total="+total);
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
		
		ArrayList	list = iDao.selectIntroList(map);
		//ArrayList	list = iDao.selectIntroList(map);
	
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroKorLang")
	public ModelAndView		introKorList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "korlang";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroJapLang")
	public ModelAndView		introJapList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "japlang";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroEngLang")
	public ModelAndView		introEngList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		System.out.println("IntroEngLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "englang";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroChnLang")
	public ModelAndView		introChnList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "chnlang";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroJavaLang")
	public ModelAndView		introJavaList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "progjava";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroCLang")
	public ModelAndView		introCLangList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "progclang";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroPhpLang")
	public ModelAndView		introPhpLangList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "progphp";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroKorCook")
	public ModelAndView		introKorCookList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "cookkor";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroJapCook")
	public ModelAndView		introJapCookList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "cookjap";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroChnCook")
	public ModelAndView		introChnCookList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "cookchn";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroWestCook")
	public ModelAndView		introWestCookList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		//System.out.println("IntroKorLang");		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		int	total = iDao.getTotal(1);
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
				
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		/*
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		*/
		String lang = "cookwest";
		HashMap	map = new HashMap();
		map.put("lang",lang );
		ArrayList	list = iDao.selectIntroSub(map);
		
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroSmallList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroView")
	public ModelAndView	introView(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		//로그인한 사람만 목록을 보여주고 싶으면.....
			/*
			if(!SessionUtil.isSession(session)) {
				RedirectView	rv = new RedirectView("../Member/login.do");
				mv.setView(rv);
				return mv;
			}
            */
			String	strPage = req.getParameter("nowPage");
			int		nowPage = 0;
			String	strNo = req.getParameter("oriNo");
			System.out.println("strNo=" +strNo);
			int	oriNo = Integer.parseInt(strNo);
			//	
			if(StringUtil.isNull(strPage)) {
				nowPage = 1;
			}
			else {
				nowPage = Integer.parseInt(strPage);
			}
			//	
			int	total = iDao.getTotal(1);
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
			
			IntroInfoData	temp= iDao.selectView(oriNo);	
			//System.out.println("temp="+temp.getNotice_title());
			//	뷰를 선택한다.
			//	뷰에게 전달할 내용을 준다.
			mv.addObject("PINFO", pInfo);
			mv.addObject("DATA", temp);
			mv.addObject("oriNo", oriNo);
			mv.addObject("NOWPAGE", nowPage);
			//System.out.println("NowPage=" +nowPage);
		mv.setViewName("IntroRegManager/IntroView");
		return mv;
	}
	/*
	 * 	
	 */
	@RequestMapping("/IntroRegManager/IntroDelete")
	public ModelAndView	introDelete(HttpServletRequest req, HttpSession session) {
			ModelAndView		mv = new ModelAndView();
			//로그인한 사람만 목록을 보여주고 싶으면.....
			if(!SessionUtil.isSession(session)) {
				RedirectView	rv = new RedirectView("../Member/login.do");
				mv.setView(rv);
				return mv;
			}
			//할일
			//넘어온 파라메터 받는다.
			String	strPage = req.getParameter("nowPage");
			//System.out.println("test ="+strPage);
			//String	pw = req.getParameter("pw");
			String	strNo = req.getParameter("oriNo");
			int		nowPage = Integer.parseInt(strPage);
			int		oriNo = Integer.parseInt(strNo);
			//System.out.println("nowPage");
			//System.out.println("oriNo");
			//삭제 할 수 있는지 확인한다.
			HashMap	map = new HashMap();
			map.put("NO", oriNo);
			//map.put("PW", pw);
			int	cnt = iDao.isUpdate(map);
			if(cnt == 0) {
				//	삭제하면 안되는 경우
				System.out.println("isUpdate");
				RedirectView 	rv = new RedirectView("../IntroRegManager/IntroView.do");
				rv.addStaticAttribute("oriNo", oriNo);
				rv.addStaticAttribute("nowPage", nowPage);
				mv.setView(rv);
				return mv;
			}
			else {
				//		삭제한다.
				System.out.println("삭제한다");
				iDao.deleteIntro(oriNo);
			}
			RedirectView	rv = new RedirectView("../IntroRegManager/IntroList.do");
			mv.setView(rv);
			//		뷰를 선택한다.
			return mv;
	}
	@RequestMapping("/IntroRegManager/IntroModifyForm")
	public ModelAndView	introModifyForm(IntroInfoData data,HttpServletRequest req,HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//로그인한 사람만 목록을 보여주고 싶으면.....
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/login.do");
			mv.setView(rv);
			return mv;
		}
		String	strPage = req.getParameter("nowPage");
		//String	pw = req.getParameter("pw");
		String	strNo = req.getParameter("oriNo");
		int		nowPage = Integer.parseInt(strPage);
		int		oriNo = Integer.parseInt(strNo);

		//
		HashMap	map = new HashMap();
		map.put("NO", oriNo);
		//map.put("PW", pw);
		int	cnt = iDao.isUpdate(map);
		IntroInfoData result = new IntroInfoData();
		if(cnt == 0) {
			//				
			RedirectView 	rv = new RedirectView("../IntroRegManager/IntroView.do");
			rv.addStaticAttribute("oriNo", oriNo);
			rv.addStaticAttribute("nowPage", nowPage);
			mv.setView(rv);
			return mv;
		}
		else {
			result = iDao.selectView(oriNo);
		}

		mv.addObject("DATA", result);
		System.out.println("mem_id="+result.getMem_id());
		mv.addObject("NOWPAGE", nowPage);
		mv.setViewName("IntroRegManager/IntroModifyForm");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroModify")
	public ModelAndView	introModify(HttpSession session,IntroInfoData data,HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		System.out.println("Modifyfffdfdfdfd");
		/*
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/Login.do");
			mv.setView(rv);
			return mv;
		}
		*/		
		//String	id = (String) session.getAttribute("ID");
		//data.setMem_id(id);//		
		// 추가할 파일 이름 변경
		String	gpath = session.getServletContext().getRealPath("gimgs");
		String	gimg1 = data.getGimg().getOriginalFilename();		
		String	gimg2 = "";		
		if(StringUtil.isNull(gimg1)) {
			gimg2 = "";			//	
								//	
		}
		else {
			gimg2 = FileUtil.rename(gpath, gimg1);
			//	
			File temp = new File(gpath, gimg2);
			try {
				data.getGimg().transferTo(temp);
			}
			catch(Exception e) {}
		}		
		//System.out.println("gimg2=" +gimg2);
		data.setGimg2(gimg2);		
	    
		//
		String kind = req.getParameter("flag");
		int	flag = Integer.parseInt(kind);
		System.out.println("flag="+flag);
		if(flag==1){
			iDao.updateIntroPhoto(data,1);
		}
		else {
			iDao.updateIntroPhoto(data,0); 			
		}
		
		RedirectView	rv = new RedirectView("../IntroRegManager/IntroList.do");
		rv.addStaticAttribute("oriNo", data.getIntro_no());
		rv.addStaticAttribute("nowPage", data.getNowPage());
		mv.setView(rv);
		return mv;		
	}
	
	@RequestMapping("/IntroRegManager/IntroHit")
	public ModelAndView introHit(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		
		String	strPage = req.getParameter("nowPage");
		
		if(StringUtil.isNull(strPage)) {
			RedirectView	rv = new RedirectView("../IntroRegManager/IntroList.do");
			mv.setView(rv);
			return mv;
		}
		int	nowPage = Integer.parseInt(strPage);
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
		//String kind = req.getParameter("flag");
	
		//String	id = (String) session.getAttribute("ID");
		//	1.
		/*
		HashMap	showMap = nDao.getShowno(id);
		boolean	isHit = false;		//	
		if(showMap == null || showMap.isEmpty()) {
			//	
			//	
			isHit = true;
			//	
			//	
			HashMap 	temp = new HashMap();
			temp.put("ID", id);
			temp.put("NO", ":" + oriNo + ":");
			nDao.updateShowno(temp, 2);
		}
		else {
			
			String	tempNo = ":" + oriNo + ":";
			String	dbNo = (String) showMap.get("SHOWNO");
			int index = dbNo.indexOf(tempNo);
			if(index == -1) {
				
				isHit = true;
				
				HashMap	temp = new HashMap();
				temp.put("ID", id);
				temp.put("NO", dbNo + tempNo);
				nDao.updateShowno(temp, 1);
			}
			else {
				isHit = false;
			}
		}
	
		if(isHit == true) {
			
			nDao.updateHit(oriNo);
		}
			*/
		//
		System.out.println("IntroHit");
		RedirectView	rv = new RedirectView("../IntroRegManager/IntroView.do");
		rv.addStaticAttribute("oriNo", oriNo);
		rv.addStaticAttribute("nowPage", nowPage);
		//rv.addStaticAttribute("flag", kind);
		mv.setView(rv);
		return mv;
	}
		
	@RequestMapping("/IntroRegManager/IntroSearch")
	public ModelAndView	introSearch(HttpServletRequest req, HttpSession session) {
		System.out.println("Searchfdfd");
		ModelAndView		mv = new ModelAndView();
		//	
		//System.out.println("NoticeSearchfdfd");
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		String	kind = req.getParameter("kind");
		String	content = req.getParameter("content");
		
		if(StringUtil.isNull(kind)) {
			
			kind = (String) session.getAttribute("kind");
			content = (String) session.getAttribute("content");
			
			if(StringUtil.isNull(kind)) {
				//	Redirect
				System.out.println("Redirect");
			}
		}
		//	
		session.setAttribute("kind", kind);
		session.setAttribute("content", content);
		
		//		
		HashMap	map = new HashMap();
		map.put("kind", kind);
		map.put("CONTENT", content);
		int	count = iDao.getSearchCount(map);
		//System.out.println("S="+count);
		PageUtil	pInfo = new PageUtil(nowPage, count, 5, 5);
		pInfo.calcInfo();
		//pInfo.calcInfo2();
		//System.out.println("IntroSearch");
		//	
		ArrayList	list = iDao.getSearch(map);
		
		for(Object obj :list  ) {
			
			IntroInfoData map2 = (IntroInfoData)obj;
			System.out.println(map2.getMem_nick());			
		}
			
		ArrayList	result = new ArrayList();		
		
		if(list.size() != 0) {
			//	
			int		start = (pInfo.nowPage - 1) * pInfo.pageList;
			int		end = start + pInfo.pageList - 1;
			System.out.println("endpage="+pInfo.endPage);
			System.out.println("end="+end);
			//	
			if(end >= list.size()) {
				end = list.size() - 1;
			}			
			
			for(int i = start; i <= end; i++) {
				result.add(list.get(i));
			}
		}
		//	
		//System.out.println("IntroSearch");
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		mv.setViewName("/IntroRegManager/IntroSearch");
		return mv;
	}
	
	/**
	 * 좋아요 기능
	 * */
	/**
	 * 좋아요 기능
	 * */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/IntroRegManager/IntroGood")
	public ModelAndView	 classGood(HttpServletRequest req, HttpSession session) {
		ModelAndView	mv = new ModelAndView();
						
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
				
		String id = (String) session.getAttribute("ID");
				
		/**
		 * 추천수 검사 시작
		 * */
		HashMap showMap = iDao.getShownoGood(id);
		boolean isGood = false;
		/*글자체를 처음 보는지?*/
		if(showMap == null || showMap.isEmpty()){
			// 이 사람은 가입해서 글을 처음 봄
			isGood = true;
			// 이 사람은 처음 글을 봤으므로 insert 시켜서 
			// 다음번 글을 볼때는 검색이 되도록 해주어야 한다.
			HashMap temp = new HashMap();
			temp.put("ID", id);
			temp.put("NO", ":"+oriNo+":");
			iDao.updateShownoGood(temp, 2);
		}
		/* 한개 이상 글 본적이 있다. */
		else{
			// 뭔가 본 글이 존재한다.
			String tempNo = ":" + oriNo + ":";
			String dbNo = (String)showMap.get("SHOWNO");
			int	index = dbNo.indexOf(tempNo);
			
			/* 이번 글은 안봤다*/
			if(index == -1 ){
				// 그런 글은 없어요
				isGood = true;
				// 이 사람이 다시 그 글을 볼때는 조회수 증가를 못하도록
				// 현재 글 번호도 다시 넣어 줘야 겠당
				HashMap temp = new HashMap();
				temp.put("ID", id);
				temp.put("NO", dbNo+tempNo);
				iDao.updateShownoGood(temp, 1);
			}
			/*봤던 글이다*/
			else{
				// 그런 글 있음 (봤던 글)
				isGood = false;
			}
		} // 점검 끝
		
		if(isGood){
			iDao.updateGood(oriNo);
		}
		/**
		 *  추천수 증가 검사 끝
		 * */
		int good = iDao.selectGood(oriNo);
		
		mv.addObject("GOOD",good);	
		mv.setViewName("IntroRegManager/IntroGood");
		
		return mv;
	}
	/**
	 * 03/18 (이 주석이 있어야 완료 된것
	 * */
	
}

