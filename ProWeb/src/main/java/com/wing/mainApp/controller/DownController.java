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
import org.springframework.web.bind.annotation.RequestParam;
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
	// 글 목록 요청
	@RequestMapping("/DownLoad/DownMain")
	public ModelAndView down(HttpServletRequest req, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		String strPage = req.getParameter("nowPage");
		int nowPage = 0;
		if (StringUtil.isNull(strPage)) {
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(strPage);
		}

		int total = dDao.getTotal();
		PageUtil pInfo = new PageUtil(nowPage, total, 10, 5);
		pInfo.calcInfo();

		ArrayList list = dDao.getList();

		int start = (pInfo.nowPage - 1) * pInfo.pageList;
		int end = start + pInfo.pageList - 1;
		if (end >= list.size()) {
			end = list.size() - 1;
		}
		ArrayList result = new ArrayList();

		for (int i = start; i <= end; i++) {
			DownLoadData temp = (DownLoadData) list.get(i);
			result.add(temp);			
		}
		
		mv.addObject("LIST", result);
		mv.addObject("PINFO", pInfo);
		mv.setViewName("/DownLoad/DownMain");
		return mv;
	}
	// 글쓰기 폼요청
	@RequestMapping("/DownLoad/DownWrite")
	public ModelAndView downWrite() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/DownLoad/DownWrite");
		return mv;
	}
	// 글쓰기 요청
	@RequestMapping("/DownLoad/DownWriteProc")
	public ModelAndView downWriteProc(DownLoadData data, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (StringUtil.isNull(data.title) || StringUtil.isNull(data.body)) {
			RedirectView rv = new RedirectView("../DownLoad/DownMain.do");
			mv.setView(rv);
			return mv;
		}

		String path = "D:\\testimg";
		String[] oriname = null;
		String[] savename = null;
		long[] len = null;
		int count = 0;

		if (data.upfile != null) {
			oriname = new String[data.upfile.length];
			savename = new String[data.upfile.length];
			len = new long[data.upfile.length];

			for (int i = 0; i < data.upfile.length; i++) {
				if (data.upfile[i].isEmpty()) {
					continue;
				} else {
					oriname[count] = data.upfile[i].getOriginalFilename();
					savename[count] = FileUtil.rename(path, oriname[count]);
					len[count] = data.upfile[i].getSize();
					try {
						File file = new File(path, savename[count]);
						data.upfile[i].transferTo(file);
					} catch (Exception e) {
						System.out.println("파일 업로드 에러=" + e);
					}
					count = count + 1;
				}
			}
		}
		String id = (String) session.getAttribute("ID");
		data.id = id;

		int no = dDao.selectMax();
		data.no = no;
		System.out.println("---------insertBoard-----------");
		System.out.println(data.no);
		System.out.println(data.id);
		System.out.println(data.title);
		System.out.println(data.body);
		System.out.println(data.wdate);
		System.out.println("---------insertBoard-----------");

		dDao.insertBoard(data);

		if (count != 0) {
			DownLoadData temp = new DownLoadData();
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
			for (int i = 0; i < count; i++) {
				temp.oriname = oriname[i];
				temp.savename = savename[i];
				temp.len = len[i];
				dDao.insertFile(temp);
			}
		}

		RedirectView rv = new RedirectView("../DownLoad/DownMain.do");
		mv.setView(rv);
		return mv;
	}
	// 상세보기 함수
	@RequestMapping("/DownLoad/DownView")
	public ModelAndView downView(HttpSession session, DownLoadData data, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		// if(!SessionUtil.isSession(session)) {
		// RedirectView rv = new RedirectView(""); // 로그인폼으로 보내야함
		// mv.setView(rv);
		// return mv;
		// }

		System.out.println(data.oriNo);
		DownLoadData rData = dDao.selectView(data.oriNo);
		ArrayList fList = dDao.selectFile(data.oriNo);
		for (int i = 0; i < fList.size(); i++) {
			System.out.println(fList.get(i));
		}

		mv.addObject("VIEW", rData);
		mv.addObject("FILES", fList);
		mv.addObject("NOWPAGE", data.nowPage);
		mv.setViewName("DownLoad/DownView");
		return mv;
	}
	// 파일 삭제 함수
	@RequestMapping("/DownLoad/DownDelete")
	public ModelAndView downDelete(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();

		String strNo = req.getParameter("oriNo");
		int oriNo = Integer.parseInt(strNo);
		if (dDao.isFile(oriNo) == 0) {
			dDao.boardDelete(oriNo);
			System.out.println(oriNo);
		} else {
			dDao.boardDelete(oriNo);
			dDao.fileDelete(oriNo);
			System.out.println(oriNo);
		}
		RedirectView rv = new RedirectView("../DownLoad/DownMain.do");
		mv.setView(rv);
		return mv;
	}
	// 파일 다운로드 함수
	@RequestMapping("/DownLoad/FileDownLoad")
	public ModelAndView fileDown(HttpServletRequest req){
		ModelAndView mv = null;
		
		String strNo = req.getParameter("oriNo");
		int no = Integer.parseInt(strNo);
		
		HashMap dmap = dDao.whatFile(no);
		HashMap map = new HashMap();
		
		map.put("oriName", dmap.get("ORINAME"));
		
		File file = new File((String)dmap.get("PATH"),(String)dmap.get("SAVENAME"));
		
		map.put("fileDownLoad", file);
		
		mv = new ModelAndView("download",map);
		
		return mv;
	}
	// 게시글 수정폼 요청 함수
	@RequestMapping("/DownLoad/DownModify")
	public ModelAndView downModify(DownLoadData data, HttpSession session) {
			ModelAndView mv = new ModelAndView();
			
			DownLoadData sdata = dDao.selectView(data.oriNo);
			ArrayList list = dDao.selectFile(data.oriNo);
//			int isFile = dDao.isFile(data.oriNo);
			
			mv.addObject("NOWPAGE",data.nowPage);
			mv.addObject("KIND",data.kind);
			mv.addObject("ORINO",data.oriNo);
			System.out.println(data.oriNo);
			mv.addObject("DATA",sdata);
			mv.addObject("FILE",list);
//			mv.addObject("ISFILE",isFile);
//			System.out.println(isFile);
			mv.setViewName("DownLoad/DownModify");
			
			return mv;
		}
	// 파일 삭제 
	@RequestMapping("/DownLoad/fileDelete")
	public ModelAndView	fileDelete(@RequestParam("no") int no) {
			ModelAndView		mv = new ModelAndView();
			HashMap	data = dDao.whatFile(no);
			dDao.fileDelete(no);
			File		file = new File((String)data.get("PATH"), (String)data.get("SAVENAME"));
			System.out.println((String)data.get("PATH"));System.out.println((String)data.get("SAVENAME"));
			file.delete();
			mv.setViewName("DownLoad/FileDelete");
			return mv;
		}
	// 게시글 수정
	@RequestMapping("/DownLoad/downModifyProc")
	public ModelAndView downModifyProc(DownLoadData data){
		ModelAndView mv = new ModelAndView();
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		int			count = 0;
		String	path = "D:\\testimg";
		if(data.upfile != null && data.upfile.length != 0) {
			//	먼저 각각의 배열을 만들자
			oriname = new String[data.upfile.length];
			savename = new String[data.upfile.length];
			len = new long[data.upfile.length];
			for(int i = 0; i < data.upfile.length; i++) {
				if(data.upfile[i].isEmpty()) {
					continue;
				}
				//	정보 알아내고
				//		count를 사용하는 이유는 실제로 올라온 개수는 폼의 개수와 다를 수 있다.
				oriname[count] = data.upfile[i].getOriginalFilename();
				savename[count] = FileUtil.rename(path, oriname[count]);
				len[count] = data.upfile[i].getSize();
				//	서버에 저장하자
				try {
					File		file = new File(path, savename[count]);
					data.upfile[i].transferTo(file);
				}
				catch(Exception e) {
					
				}
				count++;
			}
		}
		//		데이터베이스에 내용 수정하기
		dDao.downUpDate(data);
		//		첨부파일이 존재하면 파일의 정보를 데이터베이스에 기록하기
		if(count != 0) {
			DownLoadData	temp = new DownLoadData();
			temp.no = data.oriNo;
			temp.path = path;
			for(int i = 0; i < count; i++) {
				temp.oriname = oriname[i];
				temp.savename = savename[i];
				temp.len = len[i];
				dDao.insertFile(temp);
			}
		}
		//		뷰를 부르기
		RedirectView	rv = new RedirectView("../DownLoad/DownView.do");
		rv.addStaticAttribute("nowPage", data.nowPage);
		rv.addStaticAttribute("kind", data.kind);
		rv.addStaticAttribute("oriNo", data.no);
		mv.setView(rv);
		return mv;
	}
}

