//package com.wing.mainApp.controller;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
//
//import com.wing.mainApp.dao.SmartBoardDAO;
//import com.wing.mainApp.data.SmartBoardData;
//import com.wing.mainApp.util.FileUtil;
//import com.wing.mainApp.util.PageUtil;
//import com.wing.mainApp.util.SessionUtil;
//import com.wing.mainApp.util.StringUtil;
//
//@Controller
//public class SmartController {
//	@Autowired
//	SmartBoardDAO	sDao;
//	
//	
//	
//	/*
//	 * 	글쓰기 폼 요청 처리 함수
//	 */
//	@RequestMapping("/Smart/BoardWriteForm")
//	public ModelAndView	boardWriteForm(HttpSession session) {
//		ModelAndView		mv = new ModelAndView();
//		
//		if(!SessionUtil.isSession(session)) {
//			RedirectView	rv = new RedirectView("../Member/LoginForm.do");
//			mv.setView(rv);
//			return mv;
//		}
//		
//		mv.setViewName("Smart/BoardWriteForm");
//		return mv;
//	}
//	
//	/*
//	 * 	목록보기 요청 처리 함수
//	 */
//	@RequestMapping("/Smart/BoardList")
//	public ModelAndView	 boardList(HttpServletRequest req) {
//		ModelAndView		mv = new ModelAndView();
//		String	strPage = req.getParameter("nowPage");
//		int		nowPage = 0;
//		if(StringUtil.isNull(strPage)) {
//			nowPage = 1;
//		}
//		else {
//			nowPage = Integer.parseInt(strPage);
//		}
//		int	total = sDao.selectTotal();
//		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
//		pInfo.calcInfo();
//		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
//		int	end = start + pInfo.pageList - 1;
//		if(end > pInfo.totalCount) {
//			end = pInfo.totalCount;
//		}
//		
//		HashMap	map = new HashMap();
//		map.put("start", start);
//		map.put("end", end);
//		
//		ArrayList	list = sDao.selectList(map);
//		
//		mv.addObject("PINFO", pInfo);
//		mv.addObject("LIST", list);
//		mv.setViewName("Smart/BoardList");
//		return mv;
//	}
//	
//	/*
//	 * 	글쓰기 요청
//	 */
//	@RequestMapping("/Smart/BoardWrite")
//	public ModelAndView	boardWrite(HttpSession session, SmartBoardData data) {
//		ModelAndView		mv = new ModelAndView();
//		System.out.println("왔다");
//		System.out.println(data.title);
//		System.out.println(data.body);
//		System.out.println(data.pw);
//		if(StringUtil.isNull(data.title) || StringUtil.isNull(data.body) || StringUtil.isNull(data.pw)) {
//			RedirectView	rv = new RedirectView("../Smart/BoardWriteForm.do");
//			mv.setView(rv);
//			return mv;
//		}
//		System.out.println("sex");
//		String	path = "E:\\FileUpload";
//		String[]	oriname = null;
//		String[]	savename = null;
//		long[]		len = null;
//		int			count = 0;
//
//		if(data.upfile != null) {
//			oriname 	= new String[data.upfile.length];
//			savename = new String[data.upfile.length];
//			len 		= new long[data.upfile.length];
//			
//			for(int i = 0; i < data.upfile.length; i++) {
//				if(data.upfile[i].isEmpty()) {
//					continue;
//				}
//				else {
//					oriname[count] = data.upfile[i].getOriginalFilename();
//					savename[count] = FileUtil.rename(path, oriname[count]);
//					len[count] = data.upfile[i].getSize();
//					try {
//						File	file = new File(path, savename[count]);
//						data.upfile[i].transferTo(file);
//					}
//					catch(Exception e) {
//						System.out.println("업로드 실패 " + e);
//					}
//					count = count + 1;
//				}
//			}
//		}
//		String	id = (String) session.getAttribute("ID");
//		data.id = id;
//		
//		int	no = sDao.selectMax(); 
//		data.no = no;
//		
//		sDao.insertBoard(data);
//		
//		if(count != 0) {
//			SmartBoardData	temp = new SmartBoardData();
//			temp.no = no;
//			temp.path = path;
//			for(int i = 0; i < count; i++) {
//				temp.oriname = oriname[i];
//				temp.savename = savename[i];
//				temp.len = len[i];
//				sDao.insertFile(temp);
//			}
//		}
//		
//		RedirectView	rv = new RedirectView("../Smart/BoardList.do");
//		mv.setView(rv);
//		return mv;
//	}
//	
//	/*
//	 * 	상세보기
//	 */
//	@RequestMapping("/Smart/BoardView")
//	public ModelAndView boardView(HttpSession session, SmartBoardData data) {
//		ModelAndView		mv = new ModelAndView();
//		if(!SessionUtil.isSession(session)) {
//			RedirectView	rv = new RedirectView("../Member/LoginForm.do");
//			mv.setView(rv);
//			return mv;
//		}
//		SmartBoardData	rData = sDao.selectView(data.oriNo);
//		ArrayList			fList = sDao.selectFile(data.oriNo);
//		HashMap			map = sDao.selectPreNext(data.oriNo);
//		mv.addObject("VIEW", rData);
//		mv.addObject("FILES", fList);
//		mv.addObject("MAP", map);
//		mv.addObject("NOWPAGE", data.nowPage);
//		mv.addObject("KIND", data.kind);
//		mv.setViewName("Smart/BoardView");
//		return mv;
//	}
//	
//	/*	
//	 * 조회수 증가
//	 */
//	@RequestMapping("/Smart/BoardHit")
//	public ModelAndView boardHit(SmartBoardData data, HttpSession session) {
//		ModelAndView		mv = new ModelAndView();
//		String	id = (String) session.getAttribute("ID");
//		String	preHit = sDao.selectHit(id);
//		boolean	isHit = false;
//		HashMap	map = new HashMap();
//		if(StringUtil.isNull(preHit)) {
//			isHit = true;
//			map.put("ID", id);
//			map.put("HIT", ":" + data.oriNo + ":");
//			sDao.insertHit(map);
//		}
//		else {
//			int		isShow = preHit.indexOf(":" + data.oriNo + ":");
//			if(isShow == -1) {
//				isHit = true;
//				map.put("ID", id);
//				map.put("HIT", preHit + ":" + data.oriNo + ":");
//				sDao.updateHit(map);
//			}
//			else {
//				isHit = false;
//			}
//		}
//		
//		if(isHit == true) {
//			sDao.updateBoardHit(data.oriNo);
//		}
//		
//		RedirectView		rv = new RedirectView("../Smart/BoardView.do");
//		rv.addStaticAttribute("nowPage", data.nowPage);
//		rv.addStaticAttribute("kind", data.kind);
//		rv.addStaticAttribute("oriNo", data.oriNo);
//		mv.setView(rv);
//		return mv;
//	}
//	
//	/*
//	 * 	파일 다운로드 
//	 */
//	@RequestMapping("/Smart/BoardDown")
//	public ModelAndView		boardDown(HttpServletRequest req) {
//		ModelAndView		mv = null;
//		String	strNo = req.getParameter("no");
//		int		no = Integer.parseInt(strNo);
//		
//		HashMap	rMap = sDao.selectFileInfo(no);
//		
//		HashMap	map = new HashMap();
//		map.put("oirname", rMap.get("ORINAME"));
//		
//		File			file = new File((String)rMap.get("PATH"), 
//												(String)rMap.get("SAVENAME"));
//		map.put("downloadFile", file);
//		
//		mv = new ModelAndView("download", map);
//		return mv;
//	}
//	
//	/*
//	 * 	수정폼 요청 
//	 */
//	@RequestMapping("/Smart/BoardModifyForm")
//	public ModelAndView	boardModifyForm(SmartBoardData data, HttpSession session) {
//		ModelAndView		mv = new ModelAndView();
//		String	id = (String) session.getAttribute("ID");
//		
//		SmartBoardData rData = sDao.selectPwInfo(data.oriNo);
//		if(rData.id.equals(id) && rData.pw.equals(data.pw)) {
//			SmartBoardData 	dData = sDao.selectView(data.oriNo);
//			ArrayList			fList = sDao.selectFile(data.oriNo);
//			
//			mv.addObject("NOWPAGE", data.nowPage);
//			mv.addObject("KIND", data.kind);
//			mv.addObject("ORINO", data.oriNo);
//			mv.addObject("DATA", dData);
//			mv.addObject("FILES", fList);
//			mv.setViewName("Smart/BoardModifyForm");
//			return mv;
//		}
//		else {
//			RedirectView	rv = new RedirectView("../Smart/BoardView.do");
//			rv.addStaticAttribute("nowPage", data.nowPage);
//			rv.addStaticAttribute("kind", data.kind);
//			rv.addStaticAttribute("oriNo", data.oriNo);
//			mv.setView(rv);
//			return mv;
//		}
//	}
//	
//	/*
//	 * 	파일 삭제 요청
//	 */
//	@RequestMapping("/Smart/BoardFileDelete")
//	public ModelAndView	boardFileDelete(@RequestParam("no") int no) {
//		ModelAndView		mv = new ModelAndView();
//		HashMap	data = sDao.selectFileInfo(no);
//		
//		sDao.fileDelete(no);
//		
//		File		file = new File((String)data.get("PATH"), (String)data.get("SAVENAME"));
//		file.delete();
//		
//		mv.setViewName("Smart/BoardFileDelete");
//		return mv;
//	}
//	
//	/*
//	 * 	수정하기 요청 처리 함수
//	 */
//	@RequestMapping("/Smart/BoardModify")
//	public ModelAndView		boardModify(SmartBoardData data) {
//		ModelAndView		mv = new ModelAndView();
//		String[]	oriname = null;
//		String[]	savename = null;
//		long[]		len = null;
//		int			count = 0;		
//		String	path = "E:\\FileUpload";
//		if(data.upfile != null && data.upfile.length != 0) {
//			oriname = new String[data.upfile.length];
//			savename = new String[data.upfile.length];
//			len = new long[data.upfile.length];
//			
//			for(int i = 0; i < data.upfile.length; i++) {
//				if(data.upfile[i].isEmpty()) {
//					continue;
//				}
//				oriname[count] = data.upfile[i].getOriginalFilename();
//				savename[count] = FileUtil.rename(path, oriname[count]);
//				len[count] = data.upfile[i].getSize();
//				try {
//					File		file = new File(path, savename[count]);
//					data.upfile[i].transferTo(file);
//				}
//				catch(Exception e) {
//					
//				}
//				count++;
//			}
//		}
//		
//		sDao.updateBoard(data);
//		if(count != 0) {
//			SmartBoardData	temp = new SmartBoardData();
//			temp.no = data.oriNo;
//			temp.path = path;
//			for(int i = 0; i < count; i++) {
//				temp.oriname = oriname[i];
//				temp.savename = savename[i];
//				temp.len = len[i];
//				sDao.insertFile(temp);
//			}
//		}
//		RedirectView	rv = new RedirectView("../Smart/BoardView.do");
//		rv.addStaticAttribute("nowPage", data.nowPage);
//		rv.addStaticAttribute("kind", data.kind);
//		rv.addStaticAttribute("oriNo", data.oriNo);
//		mv.setView(rv);
//		return mv;
//	}
//	
//	/*
//	 * 검색하기 요청
//	 */
//	@RequestMapping("/Smart/BoardSearch")
//	public ModelAndView boardSearch(HttpServletRequest req, HttpSession session) {
//		ModelAndView		mv = new ModelAndView();
//		
//		String	kind = req.getParameter("kind");
//		String	content = req.getParameter("content");
//		String	strPage = req.getParameter("nowPage");
//		
//		int		nowPage = 0;
//		if(StringUtil.isNull(strPage)) {
//			nowPage = 1;
//		}
//		else {
//			nowPage = Integer.parseInt(strPage);
//		}
//		if(StringUtil.isNull(kind)) {
//			kind = 		(String)session.getAttribute("kind");
//			content = 	(String)session.getAttribute("content");
//		}
//		session.setAttribute("kind", kind);
//		session.setAttribute("content", content);
//		
//		HashMap map = new HashMap();
//		map.put("kind", kind);
//		map.put("CONTENT", content);
//		ArrayList	list = sDao.selectSearch(map);
//		
//		mv.addObject("LIST", list);
//		mv.setViewName("Smart/BoardSearch");
//		return mv;
//	}
//}
//
//
//
//
//
//
