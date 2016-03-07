package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;

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
	// 글써줄거임
	@RequestMapping("/DownLoad/DownWriteProc")
	public ModelAndView DownWriteProc(DownLoadData data, HttpSession session){
		ModelAndView		mv = new ModelAndView();
		System.out.println("왔다");
		System.out.println(data.title);
		System.out.println(data.body);
		if(StringUtil.isNull(data.title) || StringUtil.isNull(data.body)) {
			RedirectView	rv = new RedirectView("../DownLoad/DownMain.do");
			mv.setView(rv);
			return mv;
		}
		
		
		
		
		
		
		System.out.println("sex");
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
						System.out.println("업로드 실패 " + e);
					}
					count = count + 1;
				}
			}
		}
		String	id = (String) session.getAttribute("ID");
		data.id = id;
		
		int	no = dDao.selectMax(); 
		data.no = no;
		
		dDao.insertBoard(data);
		
		if(count != 0) {
			DownLoadData	temp = new DownLoadData();
			temp.no = no;
			temp.path = path;
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
	@RequestMapping("/DownLoad/UploadWrite")
	public ModelAndView UpLoadWrite(){
	
		ModelAndView mv = new ModelAndView();
		
		
		return mv;
	}
	
	
}
