package com.wing.mainApp.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import 	org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.util.FileUtil;

@Controller
public class CommonContoller {
	
	/*
	 * 	이미지 업로드 폼 요청 함수
	 */
	@RequestMapping("/Common/ImageUploadForm") 
	public ModelAndView	imageUploadForm() {
		ModelAndView		mv = new ModelAndView();
		mv.setViewName("Common/ImageUploadForm");
		return mv;
	}
	
	/*
	 * 	이미지 업로드 요청
	 */
	@RequestMapping("/Common/ImageUpload")
	public ModelAndView imageUpload(@RequestParam("uploadInputBox") MultipartFile file, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	전제조건	반드시 필수일 경우에만 사용할 수 있다.
		//만약 null이 되면 에러가 발생한다.
		//	이제 임시로 저장된 파일을 원하는 위치로 이동해 보자.
		//	저장될 위치는 반드시 프로젝트 안에 있는 폴더이어야 한다.
		
		String	path = session.getServletContext().getRealPath("resources/smartimg");	
		String	oriname = file.getOriginalFilename();
		String	savename = FileUtil.rename(path, oriname);
		try {
			File		uploadInputBox = new File(path, savename);
			file.transferTo(uploadInputBox);
		}
		catch(Exception e) {
			System.out.println("파일 업로드 에러" + e);			
		}
		//		뷰에게 저장된 파일의 정보를 알려준다.
		mv.addObject("path", "resources/smartimg");
		mv.addObject("name", savename);
		//	뷰를 부른다.
		mv.setViewName("Common/ImageUpload");
		return mv;
	}
}





