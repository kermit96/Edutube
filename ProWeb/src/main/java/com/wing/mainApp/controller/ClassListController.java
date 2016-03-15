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
import com.wing.mainApp.data.ReplyData;
import com.wing.mainApp.util.PagingUtil;
import com.wing.mainApp.util.SessionUtil;
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
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
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
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
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
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/ClassList/ClassView")
	public ModelAndView classView(HttpServletRequest req, ClassListData data, HttpSession session){
		ModelAndView	mv = new ModelAndView();
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
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
		
		String id = (String) session.getAttribute("ID");
		/**
		 * 조회수 검사 시작
		 * */
		HashMap showMap = lDao.getShowno(id);
		boolean isHit = false;
		/*글자체를 처음 보는지?*/
		if(showMap == null || showMap.isEmpty()){
			// 이 사람은 가입해서 글을 처음 봄
			isHit = true;
			// 이 사람은 처음 글을 봤으므로 insert 시켜서 
			// 다음번 글을 볼때는 검색이 되도록 해주어야 한다.
			HashMap temp = new HashMap();
			temp.put("ID", id);
			temp.put("NO", ":"+oriNo+":");
			lDao.updateShowno(temp, 2);
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
				isHit = true;
				// 이 사람이 다시 그 글을 볼때는 조회수 증가를 못하도록
				// 현재 글 번호도 다시 넣어 줘야 겠당
				HashMap temp = new HashMap();
				temp.put("ID", id);
				temp.put("NO", dbNo+tempNo);
				lDao.updateShowno(temp, 1);
			}
			/*봤던 글이다*/
			else{
				// 그런 글 있음 (봤던 글)
				isHit = false;
			}
		} // 점검 끝
		
		if(isHit){
			lDao.updateHit(oriNo);
		}
		/**
		 *  조회수 증가 검사 끝
		 * */
		
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
	 *  03/12 완료 (조회수까지 완료 /13)
	 * 
	 * */
	
	// 삭제 요청
	@RequestMapping("/ClassList/ClassDelete.do")
	public ModelAndView classDelete(HttpServletRequest req,HttpSession session){
		
		ModelAndView	mv = new ModelAndView();
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
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
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
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
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
	
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
	
	/**
	 * 좋아요 기능
	 * */	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/ClassList/ClassGood")
	public ModelAndView	 classGood(HttpServletRequest req, HttpSession session) {
		ModelAndView	mv = new ModelAndView();
						
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
				
		String id = (String) session.getAttribute("ID");
		
		/**
		 * 추천수 검사 시작
		 * */
		HashMap showMap = lDao.getShownoGood(id);
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
			lDao.updateShownoGood(temp, 2);
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
				lDao.updateShownoGood(temp, 1);
			}
			/*봤던 글이다*/
			else{
				// 그런 글 있음 (봤던 글)
				isGood = false;
			}
		} // 점검 끝
		
		if(isGood){
			lDao.updateGood(oriNo);
		}
		/**
		 *  추천수 증가 검사 끝
		 * */
		int good = lDao.selectGood(oriNo);
		
		mv.addObject("GOOD",good);	
		mv.setViewName("ClassList/ClassGood");
		
		return mv;
	}
	
	/**
	 * 댓글 달기
	 * */
	@RequestMapping("/ClassList/ClassReplyWrite")
	public ModelAndView	 classReplyWrite(HttpServletRequest req, HttpSession session) {
		ModelAndView	 mv = new ModelAndView();
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../member/login.do?returnurl=%2Fedutube%2Fmain.do");
			mv.setView(rv);
			return mv;
		}
		
		/**필요한 값 받아오기*/
		String id = (String) session.getAttribute("ID");
		String nick = (String) session.getAttribute("NICKNAME");
		String	strNo = req.getParameter("oriNo");
		int	oriNo = Integer.parseInt(strNo);
				
		ReplyData data = new ReplyData();
		data.orino = oriNo;
		data.reid = id;
		data.renick = nick;
		data.rebody = req.getParameter("relplybody");
		
		lDao.insertReply(data);
		
		mv.setViewName("ClassList/ClassReplyCommon");
		
		return mv;
	}
	
	/**
	 * 댓글리스트 기능
	 * */		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/ClassList/ReplyList")
	public ModelAndView	 replyList(HttpServletRequest req, HttpSession session) {
		ModelAndView	 mv = new ModelAndView();
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("/");
			mv.setView(rv);
			return mv;
		}
		
		/**필요한 값 받아오기*/
		
		String	strNo = req.getParameter("oriNo");		
		int	oriNo = Integer.parseInt(strNo);
				
		boolean isInit = false;
		
		String strpage = req.getParameter("rePage");
				
		int	rePage = 0;
		if(StringUtil.isNull(strpage) || strpage.equals("last")){
			isInit = true;
			rePage = 1;
		}
		else {
			rePage = Integer.parseInt(strpage);
		}
		
		/*총댓글수 구해서 페이징 정보 구하기*/
		int retotal = lDao.getReTotal(oriNo);
		PagingUtil	pInfo = new PagingUtil(rePage, retotal, 10, 5);
		pInfo.pagingProc();
		
		if(isInit == true){
			rePage = pInfo.pageNum;
			pInfo = new PagingUtil(rePage, retotal,10,5);
			pInfo.pagingProc();
		}
				
		// 댓글 불러오기
		ArrayList	list = lDao.getReplyList(oriNo);
		
		int	start = (pInfo.nowPage -1) * pInfo.onePageCount;
		int	end = start + pInfo.onePageCount -1;
		if(end >= list.size()) {
			end = list.size() -1;
		}
		
		ArrayList	result = new ArrayList();
		for(int i = start; i <= end; i++) {
			ReplyData	temp = (ReplyData)list.get(i);
			result.add(temp);
		}
		
		mv.addObject("PINFO",pInfo);
		mv.addObject("reDATA",result);
		mv.setViewName("ClassList/ReplyData");
		
		return mv;
	}
	
	//댓글 삭제
	@RequestMapping("/ClassList/ReplyDelete")
	public ModelAndView deleteReply(HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		/*비회원 로그인고*/
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("/");
			mv.setView(rv);
			return mv;
		}
		
		String strno = req.getParameter("reno");
		int no = Integer.parseInt(strno);
		
		lDao.deleteReply(no);
				
		mv.setViewName("ClassList/ClassReplyCommon");
		
		return mv;
	}
	
	//댓글 수정
		@RequestMapping("/ClassList/ReplyModi")
		public ModelAndView modiReply(HttpServletRequest req){
			
		return null;
	}
	
}











