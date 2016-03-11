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
			RedirectView	rv = new RedirectView("../Member/login.do");
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
			RedirectView	rv = new RedirectView("../Member/login.do");
			mv.setView(rv);
			return mv;
		}
		String	id = (String) session.getAttribute("ID");
		data.setMem_id(id);//
						
		nDao.insertNotice(data);
		//
		RedirectView	rv = new RedirectView("../Notice/NoticeList.do");
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/Notice/NoticeList")
	public ModelAndView		noticeList(HttpServletRequest req, HttpSession session,NoticeData data) {
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
			//	
			if(StringUtil.isNull(strPage)) {
				nowPage = 1;
			}
			else {
				nowPage = Integer.parseInt(strPage);
			}
			//
			int max_no = nDao.noticeMax();
			System.out.println("max_no="+max_no);
						
			int	total = nDao.getTotal(1);
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
			
			ArrayList	list = nDao.selectNoticeList(map);
			NoticeData  data1= nDao.selectFinalList(max_no);			
			//	뷰를 선택한다.
			//	뷰에게 전달할 내용을 준다.
			mv.addObject("PINFO", pInfo);
			mv.addObject("LIST", list);
			mv.addObject("DATA", data1);
		    mv.setViewName("Notice/NoticeList");
		    return mv;
	}
	@RequestMapping("/Notice/NoticeView")
	public ModelAndView	noticeView(HttpServletRequest req, HttpSession session,NoticeData data) {
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
			int	oriNo = Integer.parseInt(strNo);
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
			
			int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
			int	end = start + pInfo.pageList - 1;
			
			if(end > pInfo.totalCount) {
				end = pInfo.totalCount;
			}
			
			HashMap	map = new HashMap();
			map.put("start", start);
			map.put("end", end);
			
			NoticeData	temp= nDao.selectView(oriNo);	
			//System.out.println("temp="+temp.getNotice_title());
			//	뷰를 선택한다.
			//	뷰에게 전달할 내용을 준다.
			mv.addObject("PINFO", pInfo);
			mv.addObject("DATA", temp);
			mv.addObject("oriNo", oriNo);
			mv.addObject("NOWPAGE", nowPage);
			System.out.println("NowPage=" +nowPage);
		mv.setViewName("Notice/NoticeView");
		return mv;
	}
	/*
	 * 	
	 */
	@RequestMapping("/Notice/NoticeDelete")
	public ModelAndView	noticeDelete(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//로그인한 사람만 목록을 보여주고 싶으면.....
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/login.do");
			mv.setView(rv);
			return mv;
		}
//		할일
			//		넘어온 파라메터 받는다.
			String	strPage = req.getParameter("nowPage");
			System.out.println("test ="+strPage);
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
			int	cnt = nDao.isUpdate(map);
			if(cnt == 0) {
				//	삭제하면 안되는 경우
				System.out.println("isUpdate");
				RedirectView 	rv = new RedirectView("../Notice/NoticeView.do");
				rv.addStaticAttribute("oriNo", oriNo);
				rv.addStaticAttribute("nowPage", nowPage);
				mv.setView(rv);
				return mv;
			}
			else {
				//		삭제한다.
				System.out.println("삭제한다");
				nDao.deleteNotice(oriNo);
			}
			RedirectView	rv = new RedirectView("../Notice/NoticeList.do");
			mv.setView(rv);
			//		뷰를 선택한다.
			return mv;
	}
	@RequestMapping("/Notice/NoticeModifyForm")
	public ModelAndView	noticeModifyForm(NoticeData data,HttpServletRequest req,HttpSession session) {
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
		int	cnt = nDao.isUpdate(map);
		NoticeData	result = new NoticeData();
		if(cnt == 0) {
			//				
			RedirectView 	rv = new RedirectView("../Notice/NoticeView.do");
			rv.addStaticAttribute("oriNo", oriNo);
			rv.addStaticAttribute("nowPage", nowPage);
			mv.setView(rv);
			return mv;
		}
		else {
			//	�닔�젙�쓣 �븯�뒗 寃쎌슦�씠�떎.
			//	�닔�젙�븷 �뜲�씠�꽣瑜� 爰쇰궡�꽌 酉곗뿉寃� �븣�젮以��떎.
			result = nDao.selectView(oriNo);
		}

		mv.addObject("DATA", result);
		System.out.println("mem_id="+result.getMem_id());
		mv.addObject("NOWPAGE", nowPage);
		mv.setViewName("Notice/NoticeModifyForm");
		return mv;
	}
	@RequestMapping("/Notice/NoticeModify")
	public ModelAndView	noticeModify(HttpSession session,NoticeData data,HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/Login.do");
			mv.setView(rv);
			return mv;
		}
		System.out.println("mem_id="+data.getMem_id());
		System.out.println("notice_body="+data.getNotice_body());
		nDao.updateNotice(data);
		System.out.println("Modifyfff");
		
		//	酉곕뒗 �긽�꽭蹂닿린瑜� �떎�떆 遺덈윭以��떎.
		RedirectView	rv = new RedirectView("../Notice/NoticeList.do");
		rv.addStaticAttribute("oriNo", data.getNotice_no());
		rv.addStaticAttribute("nowPage", data.getNowPage());
		mv.setView(rv);
		return mv;		
	}
	
	@RequestMapping("/Notice/NoticeHit")
	public ModelAndView noticeHit(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		
		String	strPage = req.getParameter("nowPage");
		
		if(StringUtil.isNull(strPage)) {
			RedirectView	rv = new RedirectView("../Notice/Noticeist.do");
			mv.setView(rv);
			return mv;
		}
		int	nowPage = Integer.parseInt(strPage);
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
		String kind = req.getParameter("flag");
	
		String	id = (String) session.getAttribute("ID");
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
		System.out.println("NoticeHit");
		RedirectView	rv = new RedirectView("../Notice/NoticeView.do");
		rv.addStaticAttribute("oriNo", oriNo);
		rv.addStaticAttribute("nowPage", nowPage);
		rv.addStaticAttribute("flag", kind);
		mv.setView(rv);
		return mv;
	}
		
	@RequestMapping("/Notice/NoticeSearch")
	public ModelAndView	noticeSearch(HttpServletRequest req, HttpSession session) {
		System.out.println("NoticeSearchfdfd");
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
		int	count = nDao.getSearchCount(map);
		System.out.println("fdfdfcount ="+count);
		PageUtil	pInfo = new PageUtil(nowPage, count, 5, 5);
//		pInfo.calcInfo2();
		System.out.println("Notic rchfdfd");
		//	
		ArrayList	list = nDao.getSearch(map);
		
		ArrayList	result = new ArrayList();
		if(list.size() != 0) {
			//	
			int		start = (pInfo.nowPage - 1) * pInfo.pageList;
			int		end = start + pInfo.pageList - 1;
			//	
			if(end >= list.size()) {
				end = list.size() - 1;
			}
			for(int i = start; i <= end; i++) {
				result.add(list.get(i));
			}
		}
		//	
		System.out.println("NoticeSearch");
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		mv.setViewName("Notice/NoticeSearch");
		return mv;
	}
	
}

