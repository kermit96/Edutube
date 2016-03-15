package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.ApplyDAO;
import com.wing.mainApp.dao.MyPageDAO;
import com.wing.mainApp.data.ApplyData;
import com.wing.mainApp.data.BoardData;
import com.wing.mainApp.data.DownLoadData;
import com.wing.mainApp.data.Member;
import com.wing.mainApp.data.MyPageData;
import com.wing.mainApp.util.FileUtil;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.SessionUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class MyPageController {
	@Autowired
	ApplyDAO 		aDao;
	@Autowired
	MyPageDAO 	mDao;

	// 마이페이지 메인
	@RequestMapping("myPage/myPageMain")
	public ModelAndView myPageMain() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("myPage/myPageMain");
		return mv;
	}

	// 강사 신청 폼
	@RequestMapping("Apply/ApplyForm")
	public ModelAndView myPageHostsForm(Member mem, HttpSession session, ApplyData data) {
		ModelAndView mv = new ModelAndView();

		String id = (String) session.getAttribute("ID");
		String nal = aDao.whatNal(id);
		data.setNal(nal);
		if (nal.equals("A")) {
			// 관리자
			System.out.println("관리자");
			ArrayList list = aDao.selectTeacher();
			
			mv.addObject("LISTS",list);
			mv.setViewName("Apply/ApplyList");
			return mv;
		} else if (nal.equals("L")) {
			// 강사
			System.out.println("강사");
			mv.setViewName("Apply/endApply");
			return mv;
		} else {
			// 학생
			System.out.println("학생");
			// 강사 신청했는지 확인
			String exist = aDao.exist(id);
			System.out.println(exist);
			if (exist == null || exist.length() == 0) {
				// 신청 안했어
				mv.setViewName("Apply/ApplyForm");
				return mv;
			} else {
				// 신청했네
				mv.setViewName("Apply/waitApply");
				return mv;
			}
		}
	}

	// 강사 신청하는 기능(글작성)
	@RequestMapping("Apply/ApplyProc")
	public ModelAndView myPageHosts(ApplyData data){
		ModelAndView mv = new ModelAndView();
		String path = "E:\\Filesex";
		data.setPath(path);
	      String oriname = "nulls";
	      String savename = "nulls";
	      long len = 0;
	        
	            if (!data.getAfile().isEmpty()) {
	            	oriname = data.getAfile().getOriginalFilename();
	            	System.out.println(data.getOriname());
		               savename = FileUtil.rename(path, oriname);
		               System.out.println(data.getSavename());
		               len = data.getAfile().getSize();
		               try {
		                  File file = new File(path, savename);
		                  data.getAfile().transferTo(file);
		               } catch (Exception e) {
		                  System.out.println("파일 업로드 에러=" + e);
		               }
	            }
	            data.setOriname(oriname);
	            data.setSavename(savename);
	            data.setLen(len);
	      System.out.println("---------insertBoard-----------");
	      System.out.println(data.getCode());
	      System.out.println(data.getId());
	      System.out.println(data.getBody());
	      System.out.println(data.getPath());
	      System.out.println(data.getOriname());
	      System.out.println(data.getSavename());
	      System.out.println(data.getLen());
	      System.out.println(data.getCdate());
	      System.out.println(data.getSdate());
	      System.out.println("---------insertBoard-----------");

	      aDao.insertApply(data);
		mv.setViewName("Apply/waitApply.do");
		return mv;
	}
	// 강사 승인
	@RequestMapping("Apply/acceptApply")
	public ModelAndView acceptApply(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		
		String id= aDao.selectApply(no);
		// 강사로 변경해주자
		aDao.acceptApply(id);
		// 강사로 변경끝 그리고 승인된 항목을 지우자
		aDao.deleteApply(id);
		String emadd = aDao.email(id);
		email email = new email();
		email.recipient = emadd;
		try {
			email.emailsend();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mv.setViewName("Apply/ApplyList.do");		
		return mv;
	}
	// 강사 거절
	@RequestMapping("Apply/noApply")
	public ModelAndView noApply(HttpServletRequest req){
		ModelAndView mv= new ModelAndView();
		
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		
		// 거절 메일을 보낸다.
		String id= aDao.selectApply(no);
		email email = new email();
		String emadd = aDao.email(id);
		
		email.recipient = emadd;
		try {
			email.emailno();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		// 신청되어있는 데이터를 지운다.
		aDao.deleterec(no);
		mv.setViewName("Apply/ApplyList.do");		
		return mv;
	}
	// 첨부파일 다운로드 
	@RequestMapping("Apply/Down")
	public ModelAndView Down(HttpServletRequest req){
		ModelAndView mv = null;
		
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		System.out.println(no);
		HashMap smap = aDao.isFile(no);
		HashMap map = new HashMap();
		map.put("oriName", smap.get("ORINAME"));
		File file = new File((String)smap.get("PATH"),(String)smap.get("SAVENAME"));
		map.put("fileDownLoad", file);
		mv = new ModelAndView("download",map);
		return mv;
	}
	
	/*
	 * 신고하기 폼 요청
	 */
	@RequestMapping("/myPage/REPWriteForm")
	public ModelAndView FAQBoardWriteForm() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("myPage/REPWriteForm");
		return mv;
	}
	
	/*
	 * 신고하기 요청 함수
	 */
	@RequestMapping("/myPage/REPWrite")
	public ModelAndView REPWrite(MyPageData data) {
		ModelAndView mv = new ModelAndView();

		System.out.println(data.repid);
		mDao.insertREP(data);
		System.out.println(data.repid);
		
		// 뷰를 부른다
		RedirectView rv = new RedirectView("myPageMain.do");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 신고목록보기 
	 */
	@RequestMapping("/myPage/REPList")
	public ModelAndView REPList(HttpServletRequest req, Member data, HttpSession session) {
		ModelAndView	mv = new ModelAndView();
		String	strPage = req.getParameter("nowPage");
		int			nowPage = 0;
		
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		int total = mDao.getTotal(1);
		
		ArrayList list = mDao.selectREP();
		ArrayList result = new ArrayList();
		for(int i = 0; i < total; i++) {
			MyPageData		temp = (MyPageData)list.get(i);
			System.out.println("adfasdfas"+temp.block);
			result.add(temp);
		}
		
		
		mv.addObject("LIST", result);
		mv.setViewName("myPage/REPList");
		
		return mv;
	}
	
	/*
	 * 회원정지 요청 함수
	 */
	@RequestMapping("/myPage/REPUserstop")
	public ModelAndView REPUserStop(MyPageData data, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		
		String strNo = req.getParameter("no");
		int no = Integer.parseInt(strNo);
		String repId = mDao.selectRepid(no);
		mDao.stopREP(repId);
		mDao.visionREP(data);
		
		RedirectView	rv = new RedirectView("../myPage/REPList.do");
		mv.setView(rv);
		
		return mv;
	}
}
