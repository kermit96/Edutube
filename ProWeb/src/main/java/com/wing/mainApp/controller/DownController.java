package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.wing.mainApp.dao.DownDAO;
import com.wing.mainApp.data.DownLoadData;
import com.wing.mainApp.data.DownLoadData;
import com.wing.mainApp.util.FileUtil;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.SessionUtil;
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
		
		int total = dDao.getTotal();
		PageUtil pInfo = new PageUtil(nowPage, total, 5 , 5);
		pInfo.calcInfo();
		
		ArrayList list = dDao.getList();
		
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
	// 글쓰기 요청
	
	@RequestMapping("/DownLoad/DownWriteProc")
	public ModelAndView DownWriteProc(DownLoadData data, HttpSession session){
		ModelAndView		mv = new ModelAndView();
		if(StringUtil.isNull(data.title) || StringUtil.isNull(data.body)) {
			RedirectView	rv = new RedirectView("../DownLoad/DownMain.do");
			mv.setView(rv);
			return mv;
		}
		
		String	path = "E:\\FileUpload";
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		int			count = 0;

		if(data.upfile != null) {
			oriname 	= new String[data.upfile.length];
			savename = new String[data.upfile.length];
			len 		= new long[data.upfile.length];
			
			for(int i = 0; i < data.upfile.length; i++) {
				if(data.upfile[i].isEmpty()) {
					continue;
				}
				else {
					oriname[count] = data.upfile[i].getOriginalFilename();
					savename[count] = FileUtil.rename(path, oriname[count]);
					len[count] = data.upfile[i].getSize();
					try {
						File	file = new File(path, savename[count]);
						data.upfile[i].transferTo(file);
					}
					catch(Exception e) {
						System.out.println("파일 업로드 에러=" + e);
					}
					count = count + 1;
				}
			}
		}
		String	id = (String) session.getAttribute("ID");
		data.id = id;
		
		int	no = dDao.selectMax(); 
		data.no = no;
		System.out.println("---------insertBoard-----------");
		System.out.println(data.no);
		System.out.println(data.id);
		System.out.println(data.title);
		System.out.println(data.body);
		System.out.println(data.wdate);
		System.out.println("---------insertBoard-----------");
		
		dDao.insertBoard(data);
		
		if(count != 0) {
			DownLoadData	temp = new DownLoadData();
			temp.no = no;
			temp.path = path;
			System.out.println("---------insertFile-------");
			System.out.println(data.no);
			System.out.println(data.path);
			System.out.println(data.oriname);
			System.out.println(data.savename);
			System.out.println(data.len);
			System.out.println(data.wdate);
			System.out.println("---------insertFile-------");
			for(int i = 0; i < count; i++) {
				temp.oriname = oriname[i];
				temp.savename = savename[i];
				temp.len = len[i];
				dDao.insertFile(temp);
			}
		}
		
		RedirectView	rv = new RedirectView("../DownLoad/DownMain.do");
		mv.setView(rv);
		return mv;
	}
	// 상세보기 함수
	
	@RequestMapping("/DownLoad/DownView")
	public ModelAndView boardView(HttpSession session, DownLoadData data, HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
//		if(!SessionUtil.isSession(session)) {
//			RedirectView	rv = new RedirectView(""); // 로그인폼으로 보내야함
//			mv.setView(rv);
//			return mv;
//		}		
		
		System.out.println(data.oriNo);
		DownLoadData	rData = dDao.selectView(data.oriNo);
		ArrayList			fList = dDao.selectFile(data.oriNo);
		for(int i =0; i < fList.size(); i++){
			System.out.println(fList.get(i));
		}
		
		mv.addObject("VIEW", rData);
		mv.addObject("FILES", fList);
		mv.addObject("NOWPAGE", data.nowPage);
		mv.setViewName("DownLoad/DownView");
		return mv;
	}
	// 게시글 수정함수
	@RequestMapping("/DownLoad/DownModify")
	public ModelAndView downModify(DownLoadData data, HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		String id= (String)session.getAttribute("ID");
		if(id.equals(null)||id==""){
			mv.setViewName("DownLoad/DownView.do");
			return mv;
		}
		
		
		return mv;
	}
	@RequestMapping("/DownLoad/DownDelete")
	public ModelAndView downDelete(){
		ModelAndView mv = new ModelAndView();
		
		return mv;
	}
}
