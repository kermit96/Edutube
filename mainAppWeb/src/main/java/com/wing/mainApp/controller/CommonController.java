package com.wing.mainApp.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.util.FileUtil;

@Controller
public class CommonController {

	/*
	 * 	�씠誘몄� �뾽濡쒕뱶 �뤌 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/Common/ImageUploadForm") 
	public ModelAndView	imageUploadForm() {
		ModelAndView		mv = new ModelAndView();
		mv.setViewName("Common/ImageUploadForm");
		return mv;
	}
	
	/*
	 * 	�씠誘몄� �뾽濡쒕뱶 �슂珥�
	 */
	@RequestMapping("/Common/ImageUpload")
	public ModelAndView imageUpload(@RequestParam("upfile") MultipartFile file, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�쟾�젣議곌굔	諛섎뱶�떆 �븘�닔�씪 寃쎌슦�뿉留� �궗�슜�븷 �닔 �엳�떎.
		//	留뚯빟 null�씠 �릺硫� �뿉�윭媛� 諛쒖깮�븳�떎.
		//	�씠�젣 �엫�떆濡� ���옣�맂 �뙆�씪�쓣 �썝�븯�뒗 �쐞移섎줈 �씠�룞�빐 蹂댁옄.
		//	���옣�맆 �쐞移섎뒗 諛섎뱶�떆 �봽濡쒖젥�듃 �븞�뿉 �엳�뒗 �뤃�뜑�씠�뼱�빞 �븳�떎.
		String	path = session.getServletContext().getRealPath("images");
		String	oriname = file.getOriginalFilename();
		String	savename = FileUtil.rename(path, oriname);
		try {
			File		upfile = new File(path, savename);
			file.transferTo(upfile);
		}
		catch(Exception e) {
			System.out.println("�뙆�씪 �뾽濡쒕뱶 �뿉�윭 " + e);
		}
		//	酉곗뿉寃� ���옣�맂 �뙆�씪�쓽 �젙蹂대�� �븣�젮以��떎.
		mv.addObject("path", "images");
		mv.addObject("name", savename);
		//	酉곕�� 遺�瑜몃떎.
		mv.setViewName("Common/ImageUpload");
		return mv;
	}
}