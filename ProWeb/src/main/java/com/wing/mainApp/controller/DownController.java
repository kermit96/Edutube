package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.wing.mainApp.dao.DownDAO;
import com.wing.mainApp.data.DownLoadData;
import com.wing.mainApp.util.FileUtil;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class DownController {
	@Autowired
	DownDAO dDao;
	
	@RequestMapping("/DownLoad/DownMain")
	public ModelAndView down(HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		
		String	strPage = req.getParameter("nowPage");
		int nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		
		int total = dDao.getTotal(1);
		PageUtil pInfo = new PageUtil(nowPage, total, 5 , 5);
		pInfo.calcInfo();
		
		ArrayList list = dDao.getDownList();
		
		int		start = (pInfo.nowPage - 1) * pInfo.pageList;
		int		end = start + pInfo.pageList - 1;
		if(end >= list.size()) {
			end = list.size() - 1;
		}
		
		ArrayList result = new ArrayList();
		
		for(int i=start; i <= end; i++){
			DownLoadData temp = (DownLoadData)list.get(i);
			result.add(temp);
		}
		
		
//		mv.addObject("PINFO",pInfo);
		mv.addObject("LIST",result);
		mv.addObject("PINFO",pInfo);
		mv.setViewName("/DownLoad/DownMain");
		return mv;
	}
	@RequestMapping("/DownLoad/DownWrite")
	public ModelAndView DownWrite(){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/DownLoad/DownWrite");
		return mv;
	}
	@RequestMapping("/DownLoad/DownWriteProc")
	public ModelAndView DownWriteProc(DownLoadData data){
		ModelAndView mv = new ModelAndView();
		
		String path="E:\\FileUpload";
		String oriName ="";
		String saveName = "";
		long length= 0;
		if(!data.upfile.isEmpty()){
			oriName = data.upfile.getOriginalFilename();
			saveName = FileUtil.rename(path, oriName);
			length = data.upfile.getSize();
			File file = new File(path, saveName);
			try{
				data.upfile.transferTo(file);
			}
			catch(Exception e) {
				System.out.println("파일 업로드 실패 = " +e);
			}
		}
		data.len = length;
		data.oriname = oriName;
		data.savename = saveName;
		data.path = path;
		
		
			dDao.insertDown(data);
			dDao.insertUpload(data);
		
		RedirectView rv = new RedirectView("../DownLoad/DownMain.do");
		mv.setView(rv);
		return mv;
	}
	@RequestMapping("/DownLoad/UploadWrite")
	public ModelAndView UpLoadWrite(){
	
		ModelAndView mv = new ModelAndView();
		
		
		return mv;
	}
	
	
}
