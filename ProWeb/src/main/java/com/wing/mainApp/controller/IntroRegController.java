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
	private IntroInfoDAO	iDao;//�뙆�씪 �젙蹂댁� 湲고� �젙蹂�
		
	@RequestMapping("/IntroRegManager/IntroRegForm")
	public ModelAndView	introRegForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
	    /*
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		*/
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
		/*
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		*/
		String	id = (String) session.getAttribute("loginId");//Joon
		data.setMem_id(id);//媛뺤궗 ID �엯�젰
		
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
			RedirectView	rv = new RedirectView("../Member/member_form.do");
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
		
		System.out.println("IntroList");
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	占쎈솁占쎌뵬筌롫�苑ｏ옙�뮉 獄쏆꼶諭띰옙�뻻 占쎌젎野껓옙占쎌뱽 占쎈퉸占쎄퐣 占쎈씨占쎈뮉 野껋럩�뒭 筌ｌ꼶�봺�몴占� 占쎈퉸占쎈튊占쎈립占쎈뼄.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	占쎈읂占쎌뵠筌욑옙 占쎌젟癰귨옙 筌띾슢諭얏�⑨옙
		int	total = iDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	筌뤴뫖以� �뤃�뗫릭�⑨옙
		//		占쎌뜚占쎈릭占쎈뮉 占쎌맄燁살꼶占쏙옙 雅뚯눊由경에占� 占쎈튋占쎈꺗占쎈뻥占쎈뼄.
		//									start		end
		//		nowPage	1占쎈읂占쎌뵠筌욑옙		1 ~ 		5
		//					2占쎈읂占쎌뵠筌욑옙		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙占쎈뮉 5揶쏆뮄占� 占쎈툧占쎈쭍 占쎈땾占쎈즲 占쎌뿳占쎌몵沃섓옙嚥∽옙.....
		//	�룯占� 占쎈쑓占쎌뵠占쎄숲 揶쏆뮇�땾癰귣��뼄 占쎌삂占쎌몵筌롳옙 �룯占� 占쎈쑓占쎌뵠占쎄숲揶쏆뮇�땾筌띾슦寃� �댆�눖沅∽쭖占� 占쎈쭆占쎈뼄.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = iDao.selectIntroList(map);
		
		//	�뀎怨뺧옙占� �겫占썹몴紐껊뼄.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	@RequestMapping("/IntroRegManager/IntroAList")
	public ModelAndView		introAList(HttpServletRequest req, HttpSession session,IntroInfoData data) {
		ModelAndView		mv = new ModelAndView();
		
		System.out.println("IntroList");
		
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	占쎈솁占쎌뵬筌롫�苑ｏ옙�뮉 獄쏆꼶諭띰옙�뻻 占쎌젎野껓옙占쎌뱽 占쎈퉸占쎄퐣 占쎈씨占쎈뮉 野껋럩�뒭 筌ｌ꼶�봺�몴占� 占쎈퉸占쎈튊占쎈립占쎈뼄.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	占쎈읂占쎌뵠筌욑옙 占쎌젟癰귨옙 筌띾슢諭얏�⑨옙
		int	total = iDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	筌뤴뫖以� �뤃�뗫릭�⑨옙
		//		占쎌뜚占쎈릭占쎈뮉 占쎌맄燁살꼶占쏙옙 雅뚯눊由경에占� 占쎈튋占쎈꺗占쎈뻥占쎈뼄.
		//									start		end
		//		nowPage	1占쎈읂占쎌뵠筌욑옙		1 ~ 		5
		//					2占쎈읂占쎌뵠筌욑옙		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙占쎈뮉 5揶쏆뮄占� 占쎈툧占쎈쭍 占쎈땾占쎈즲 占쎌뿳占쎌몵沃섓옙嚥∽옙.....
		//	�룯占� 占쎈쑓占쎌뵠占쎄숲 揶쏆뮇�땾癰귣��뼄 占쎌삂占쎌몵筌롳옙 �룯占� 占쎈쑓占쎌뵠占쎄숲揶쏆뮇�땾筌띾슦寃� �댆�눖沅∽쭖占� 占쎈쭆占쎈뼄.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = iDao.selectIntroList(map);
		
		//	�뀎怨뺧옙占� �겫占썹몴紐껊뼄.
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
		//	占쎈솁占쎌뵬筌롫�苑ｏ옙�뮉 獄쏆꼶諭띰옙�뻻 占쎌젎野껓옙占쎌뱽 占쎈퉸占쎄퐣 占쎈씨占쎈뮉 野껋럩�뒭 筌ｌ꼶�봺�몴占� 占쎈퉸占쎈튊占쎈립占쎈뼄.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	占쎈읂占쎌뵠筌욑옙 占쎌젟癰귨옙 筌띾슢諭얏�⑨옙
		int	total = iDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	筌뤴뫖以� �뤃�뗫릭�⑨옙
		//		占쎌뜚占쎈릭占쎈뮉 占쎌맄燁살꼶占쏙옙 雅뚯눊由경에占� 占쎈튋占쎈꺗占쎈뻥占쎈뼄.
		//									start		end
		//		nowPage	1占쎈읂占쎌뵠筌욑옙		1 ~ 		5
		//					2占쎈읂占쎌뵠筌욑옙		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	筌띾뜆占쏙쭕占� 占쎈읂占쎌뵠筌욑옙占쎈뮉 5揶쏆뮄占� 占쎈툧占쎈쭍 占쎈땾占쎈즲 占쎌뿳占쎌몵沃섓옙嚥∽옙.....
		//	�룯占� 占쎈쑓占쎌뵠占쎄숲 揶쏆뮇�땾癰귣��뼄 占쎌삂占쎌몵筌롳옙 �룯占� 占쎈쑓占쎌뵠占쎄숲揶쏆뮇�땾筌띾슦寃� �댆�눖沅∽쭖占� 占쎈쭆占쎈뼄.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = iDao.selectIntroList(map);
		
		//	�뀎怨뺧옙占� �겫占썹몴紐껊뼄.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("IntroRegManager/IntroList");
		return mv;
	}
	
}

