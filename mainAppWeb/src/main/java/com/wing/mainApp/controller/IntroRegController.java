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
	private IntroInfoDAO	iDao;//파일 정보와 기타 정보
		
	@RequestMapping("/IntroRegManager/IntroRegForm")
	public ModelAndView	introRegForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
	
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		
		//ArrayList	list = gmDao.getCate(null, 1);	
		//		
		//mv.addObject("LCATE", list);
		mv.setViewName("IntroRegManager/IntroRegForm");
		return mv;
	}
	/*
	 * 	
	 */
	@RequestMapping("/IntroRegManager/IntroReg")
	public ModelAndView	introWrite(HttpSession session, IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		String	id = (String) session.getAttribute("loginId");//Joon
		data.setMem_id(id);//강사 ID 입력
		
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
		
		data.setGimg2(gimg2);
		
		iDao.insertIntroInfo(data);
		//
		RedirectView	rv = new RedirectView("../IntroRegManager/IntroRegForm.do");
		mv.setView(rv);
		return mv;
	}
	
	@RequestMapping("/IntroRegManager/IntroLec")
	public ModelAndView		introLec(HttpServletRequest req, HttpSession session,IntroInfoData data){
		ModelAndView		mv = new ModelAndView();
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}		
		//mv.setViewName("IntroRegManager/IntroLec");
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroList")
	public ModelAndView		introList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		int Curr_Page=0;
		Curr_Page=2;
		System.out.println("IntroList");
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	�뙆�씪硫뷀꽣�뒗 諛섎뱶�떆 �젏寃��쓣 �빐�꽌 �뾾�뒗 寃쎌슦 泥섎━瑜� �빐�빞�븳�떎.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	�럹�씠吏� �젙蹂� 留뚮뱾怨�
		int	total = iDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	紐⑸줉 援ы븯怨�
		//		�썝�븯�뒗 �쐞移섎�� 二쇨린濡� �빟�냽�뻽�떎.
		//									start		end
		//		nowPage	1�럹�씠吏�		1 ~ 		5
		//					2�럹�씠吏�		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	留덉�留� �럹�씠吏��뒗 5媛쒓� �븞�맆 �닔�룄 �엳�쑝誘�濡�.....
		//	珥� �뜲�씠�꽣 媛쒖닔蹂대떎 �옉�쑝硫� 珥� �뜲�씠�꽣媛쒖닔留뚰겮 爰쇰궡硫� �맂�떎.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = iDao.selectIntroList(map);
		
		//	酉곕�� 遺�瑜몃떎.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.addObject("NOTICE",Curr_Page);
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroAList")
	public ModelAndView		introAList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		System.out.println("IntroList");
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	�뙆�씪硫뷀꽣�뒗 諛섎뱶�떆 �젏寃��쓣 �빐�꽌 �뾾�뒗 寃쎌슦 泥섎━瑜� �빐�빞�븳�떎.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	�럹�씠吏� �젙蹂� 留뚮뱾怨�
		int	total = iDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	紐⑸줉 援ы븯怨�
		//		�썝�븯�뒗 �쐞移섎�� 二쇨린濡� �빟�냽�뻽�떎.
		//									start		end
		//		nowPage	1�럹�씠吏�		1 ~ 		5
		//					2�럹�씠吏�		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	留덉�留� �럹�씠吏��뒗 5媛쒓� �븞�맆 �닔�룄 �엳�쑝誘�濡�.....
		//	珥� �뜲�씠�꽣 媛쒖닔蹂대떎 �옉�쑝硫� 珥� �뜲�씠�꽣媛쒖닔留뚰겮 爰쇰궡硫� �맂�떎.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = iDao.selectIntroList(map);
		
		//	酉곕�� 遺�瑜몃떎.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroBList")
	public ModelAndView		introBList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		System.out.println("IntroList");
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	�뙆�씪硫뷀꽣�뒗 諛섎뱶�떆 �젏寃��쓣 �빐�꽌 �뾾�뒗 寃쎌슦 泥섎━瑜� �빐�빞�븳�떎.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	�럹�씠吏� �젙蹂� 留뚮뱾怨�
		int	total = iDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	紐⑸줉 援ы븯怨�
		//		�썝�븯�뒗 �쐞移섎�� 二쇨린濡� �빟�냽�뻽�떎.
		//									start		end
		//		nowPage	1�럹�씠吏�		1 ~ 		5
		//					2�럹�씠吏�		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	留덉�留� �럹�씠吏��뒗 5媛쒓� �븞�맆 �닔�룄 �엳�쑝誘�濡�.....
		//	珥� �뜲�씠�꽣 媛쒖닔蹂대떎 �옉�쑝硫� 珥� �뜲�씠�꽣媛쒖닔留뚰겮 爰쇰궡硫� �맂�떎.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = iDao.selectIntroList(map);
		
		//	酉곕�� 遺�瑜몃떎.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	
}

