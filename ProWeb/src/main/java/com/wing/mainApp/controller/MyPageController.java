package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.dao.ApplyDAO;
import com.wing.mainApp.data.ApplyData;
import com.wing.mainApp.data.DownLoadData;
import com.wing.mainApp.data.Member;
import com.wing.mainApp.util.FileUtil;

@Controller
public class MyPageController {
	@Autowired
	ApplyDAO aDao;

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
		mv.setViewName("Apply/waitApply");
		return mv;
	}
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
		mv.setViewName("Apply/ApplyList");		
		return mv;
	}
}
