package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.SmartBoardDAO;
import com.wing.mainApp.data.SmartBoardData;
import com.wing.mainApp.util.FileUtil;
import com.wing.mainApp.util.PageUtil;
import com.wing.mainApp.util.SessionUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class SmartController {
	//	이 컨트롤러에서 사용할 DAO를 받아온다.
	@Autowired
	SmartBoardDAO	sDao;
	
	
	
	/*
	 * 	글쓰기 폼 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardWriteForm")
	public ModelAndView	boardWriteForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	권한 검사를 한다.
		//		정책	로그인을 하지 않은 사람은 글쓰기를 할수 없도록 하고자 한다.
		if(!SessionUtil.isSession(session)) {
			//	로그인을 하지 않은 사람이다.
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		
		mv.setViewName("Smart/BoardWriteForm");
		return mv;
	}
	
	/*
	 * 	목록보기 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardList")
	public ModelAndView	 boardList(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	권한 설정을 한다.
		//		목록은 아무나 볼 수 있도록 한다.
		//	파라메터 받는다.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	파라메터는 반드시 점검을 해서 없는 경우 처리를 해야한다.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	페이지 정보 만들고
		int	total = sDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	목록 구하고
		//		원하는 위치를 주기로 약속했다.
		//									start		end
		//		nowPage	1페이지		1 ~ 		5
		//					2페이지		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	마지막 페이지는 5개가 안될 수도 있으므로.....
		//	총 데이터 개수보다 작으면 총 데이터개수만큼 꺼내면 된다.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = sDao.selectList(map);
		
		//	뷰를 부른다.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("Smart/BoardList");
		return mv;
	}
	
	/*
	 * 	글쓰기 요청
	 */
	@RequestMapping("/Smart/BoardWrite")
	public ModelAndView	boardWrite(HttpSession session, SmartBoardData data) {
		ModelAndView		mv = new ModelAndView();
		//	할일		권한검사
		if(!SessionUtil.isSession(session)) {
			//	로그인을 하지 않은 사람이다.
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		//				데이터 받기
		//	이 중에 필수 요건은 반드시 검사해서 오지 않았으면 조치를 취해야 한다.
		if(StringUtil.isNull(data.title) || StringUtil.isNull(data.body) || StringUtil.isNull(data.pw)) {
			RedirectView	rv = new RedirectView("../Smart/BoardWriteForm.dol");
			mv.setView(rv);
			return mv;
		}
		//				서버에 파일 업로드 하기
		//				파일 업로드가 선택 사항인 경우....
		//				파일이 업로드 되지 않는 경우를 참작해서 작업한다.
		String	path = "E:\\FileUpload";
		//	데이터베이스에 저장하기 위해서 첨부 파일의 정보를 알아놓는다.
		//	저장할 변수 준비하고
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		//	혹시 중간에 빠진것이 있을 수 있으므로..... 카운트 시켜보자.
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
					//	저장하자.
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
		//				데이터베이스에 기록하기
		//				1.	게시물 내용을 저장하기
		//	부족한 데이터 받기
		String	id = (String) session.getAttribute("ID");
		data.id = id;
		
		int	no = sDao.selectMax(); 
		data.no = no;
		
		sDao.insertBoard(data);
		
		if(count != 0) {
			//		2.	첨부 파일 내용을 저장하기
			//	내용은 데이터 빈 클래스로 주기로 약속했다.
			SmartBoardData	temp = new SmartBoardData();
			//	모든 곳에서 공통으로 사용되는 것은 미리 넣어놓자.
			temp.no = no;
			temp.path = path;
			for(int i = 0; i < count; i++) {
				temp.oriname = oriname[i];
				temp.savename = savename[i];
				temp.len = len[i];
				sDao.insertFile(temp);
			}
		}
		
		//				뷰
		RedirectView	rv = new RedirectView("../Smart/BoardList.dol");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	상세보기 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardView")
	public ModelAndView boardView(HttpSession session, SmartBoardData data) {
//	public String boardView(Model model) {
		//	우리가 배웠던 원초 MVC 기법을 이용한 방식
		//	즉,	뷰의 이름만 알려주는 방식
		//		모델은	req.setAttribute()를 이용해서 처리하는 방식
		//		모델은 model.addObject("", "");
		ModelAndView		mv = new ModelAndView();
		//	권한 검사
		if(!SessionUtil.isSession(session)) {
			//	로그인을 하지 않은 사람이다.
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		
		//	파라메터 받고
		//		파라메터가 혹시 전달되지 않았을 경우를 반드시 처리해라.
		
		//	필요한 데이터 꺼내고
		//		1.	상세보기
		SmartBoardData	rData = sDao.selectView(data.oriNo);
		//		2.	파일
		ArrayList			fList = sDao.selectFile(data.oriNo);
		//		3.	이전글, 다음글
		HashMap			map = sDao.selectPreNext(data.oriNo);
		
		//	뷰를 부른다.
		mv.addObject("VIEW", rData);
		mv.addObject("FILES", fList);
		mv.addObject("MAP", map);
		//	지금 이 컨트롤러는 릴레리를 위한 파라메터가 존재한다.
		//	릴레이 파라메터는 컨트롤러에서 사용할 필요는 없지만 릴레이를
		//	시키기 위해서 뷰에게 알려주어야 한다.
		mv.addObject("NOWPAGE", data.nowPage);
		mv.addObject("KIND", data.kind);
		mv.setViewName("Smart/BoardView");
		return mv;
	}
	
	/*	
	 * 조회수 증가 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardHit")
	public ModelAndView boardHit(SmartBoardData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	필요한 절차 거치고
		
		//	조회수 증가한다.
		//	글을 본 사람의 아이디를 구한다.
		String	id = (String) session.getAttribute("ID");
		//	이 사람이 이미본 글번호를 알아낸다.
		String	preHit = sDao.selectHit(id);
		boolean	isHit = false;			//	조회수를 증가할지 여부를 판단할 변수
		HashMap	map = new HashMap();
		if(StringUtil.isNull(preHit)) {
			//	이 사람은 처음 게시판에 들어온 사람이다.
			isHit = true;
			//	이 사람이 지금 본 글번호를 새롭게 만들어준다.
			map.put("ID", id);
			map.put("HIT", ":" + data.oriNo + ":");
			sDao.insertHit(map);
		}
		else {
			//	이 사람은 한번 이상 게시판을 사용했던 사람이다.
			//	이 사람이 이 글을 봤는지를 검사하자.
			int		isShow = preHit.indexOf(":" + data.oriNo + ":");
			if(isShow == -1) {
				//	그 글번호는 없다.
				isHit = true;
				//	이제 다음에는 증가하지 못하게 이 번호를 수정해 놓는다.
				map.put("ID", id);
				map.put("HIT", preHit + ":" + data.oriNo + ":");
				sDao.updateHit(map);
			}
			else {
				//	그 글은 봤는데요
				isHit = false;
			}
		}
		
		//	조회수를 증가할 필요가 있으면 증가한다.
		if(isHit == true) {
			sDao.updateBoardHit(data.oriNo);
		}
		
		RedirectView		rv = new RedirectView("../Smart/BoardView.dol");
		rv.addStaticAttribute("nowPage", data.nowPage);
		rv.addStaticAttribute("kind", data.kind);
		rv.addStaticAttribute("oriNo", data.oriNo);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	파일 다운로드 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardDown")
	public ModelAndView		boardDown(HttpServletRequest req) {
		ModelAndView		mv = null;
		//	특수뷰를 불러와야 하므로 new 시키는 방식이 다르다.
		//	할일
		//		권한설정
		//		파라메터 받기
		String	strNo = req.getParameter("no");
		//		파라메터가 혹시 없는 경우 처리를 해준다.
		int		no = Integer.parseInt(strNo);
		
		//	다운로드 회수를 증가시켜보자
		//	증가 방식은 세션에 관계없이 무조건 증가방식으로 한다.
		//	숙제
		
		
		//		파일 정보 얻기
		HashMap	rMap = sDao.selectFileInfo(no);
		
		//		특수뷰 부르기(특수뷰가 알아서 다운로드 해준다.)
		//		특수뷰는 사용자가 만들어 사용하는 개념이다.
		//		우리는		원래이름			String		oirname
		//					다운로드할 파일	File			downloadFile
		HashMap	map = new HashMap();
		map.put("oirname", rMap.get("ORINAME"));
		
		File			file = new File((String)rMap.get("PATH"), 
												(String)rMap.get("SAVENAME"));
		map.put("downloadFile", file);
		
		mv = new ModelAndView("download", map);
		//	뷰이름은	xml 파일에 등록한 특수뷰의 id값이 된다.
		return mv;
	}
	
	/*
	 * 	수정폼 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardModifyForm")
	public ModelAndView	boardModifyForm(SmartBoardData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		권한설정
		//		파라메터 받는다.
		
		//		비번이 같은지 확인한다.
		String	id = (String) session.getAttribute("ID");
		
		SmartBoardData rData = sDao.selectPwInfo(data.oriNo);
		if(rData.id.equals(id) && rData.pw.equals(data.pw)) {
			//	수정할 수 있다.
			//		원하는 뷰를 불러주고
			//	수정하기 위한 정보(내용정보, 파일정보)를 꺼내서 알려준다.
			SmartBoardData 	dData = sDao.selectView(data.oriNo);
			ArrayList			fList = sDao.selectFile(data.oriNo);
			
			//	릴레이를 위한 파라메토는 넘겨주고
			mv.addObject("NOWPAGE", data.nowPage);
			mv.addObject("KIND", data.kind);
			mv.addObject("ORINO", data.oriNo);
			mv.addObject("DATA", dData);
			mv.addObject("FILES", fList);
			mv.setViewName("Smart/BoardModifyForm");
			return mv;
		}
		else {
			//	수정 권한이 없다.
			//		상세보기로 보낸다.
			RedirectView	rv = new RedirectView("../Smart/BoardView.dol");
			rv.addStaticAttribute("nowPage", data.nowPage);
			rv.addStaticAttribute("kind", data.kind);
			rv.addStaticAttribute("oriNo", data.oriNo);
			mv.setView(rv);
			return mv;
		}
	}
	
	/*
	 * 	파일 삭제 요청
	 */
	@RequestMapping("/Smart/BoardFileDelete")
	public ModelAndView	boardFileDelete(@RequestParam("no") int no) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		권한점거
		//		파라메터
		//		삭제하기 전에 먼저 정보를 알아낸다.
		//		나중에 이 정보가 있어서 서버에 있는 파일을 삭제할 수 있기 때문이다.
		HashMap	data = sDao.selectFileInfo(no);
		
		//		데이터베이스에서 삭제
		sDao.fileDelete(no);
		
		//		실제 서버에 있는 파일도 삭제
		//		일반 java API를 이용해서 삭제한다.
		File		file = new File((String)data.get("PATH"), (String)data.get("SAVENAME"));
		file.delete();
		
		//		뷰를 부른다.
		mv.setViewName("Smart/BoardFileDelete");
		return mv;
	}
	
	/*
	 * 	수정하기 요청 처리 함수
	 */
	@RequestMapping("/Smart/BoardModify")
	public ModelAndView		boardModify(SmartBoardData data) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		권한검사
		//		파라메터 받기(검사까지 포함)
		//		첨부파일이 존재하면 파일을 서버에 저장하기
		//	첨부파일의 정보를 나중에 데이터베이스에 저장해야 하므로.....
		//	그 정보를 기록해 놓자
		
		//	배열을 몇개를 만들어야 할지 모르므로.....
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		int			count = 0;			//	첨부파일의 개수를 기억할 변수 
		String	path = "E:\\FileUpload";
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
		sDao.updateBoard(data);
		//		첨부파일이 존재하면 파일의 정보를 데이터베이스에 기록하기
		if(count != 0) {
			SmartBoardData	temp = new SmartBoardData();
			temp.no = data.oriNo;
			temp.path = path;
			for(int i = 0; i < count; i++) {
				temp.oriname = oriname[i];
				temp.savename = savename[i];
				temp.len = len[i];
				sDao.insertFile(temp);
			}
		}
		//		뷰를 부르기
		RedirectView	rv = new RedirectView("../Smart/BoardView.dol");
		rv.addStaticAttribute("nowPage", data.nowPage);
		rv.addStaticAttribute("kind", data.kind);
		rv.addStaticAttribute("oriNo", data.oriNo);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 검색하기 요청
	 */
	@RequestMapping("/Smart/BoardSearch")
	public ModelAndView boardSearch(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	파라메터
		String	kind = req.getParameter("kind");
		String	content = req.getParameter("content");
		String	strPage = req.getParameter("nowPage");
		
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	문제(존재하지 않으면 처리해 놓아야 한다.)
		//		페이지 이동기능을 이용하게 되면 kind, content가 안넘어온다.
		//		물론 릴레이 방식으로 넘겨도 되지만......
		//		이유
		//			GET 방식으로 처리하는 경우는 한글은 될 수 있으면
		//			사용하지 않는다.
		//	만약 존재하지 않으면 세션에서 받아서 사용하도록 한다.
		if(StringUtil.isNull(kind)) {
			kind = 		(String)session.getAttribute("kind");
			content = 	(String)session.getAttribute("content");
		}
		//	혹시 지금 넘어온 데이터를 다음에는 세션에서 찾을 필요가 생길 것이므로
		//	세션에 저장해 놓는다.
		session.setAttribute("kind", kind);
		session.setAttribute("content", content);
		
		//	세션에는 모든 형태의 정보를 보관할 수 있다.
		//	예를 들어어서 세션에 보관할 데이터가 많으면.....
//		HashMap map = new HashMap();
//		map.put("kind", kind);
//		map.put("content", content);
//		session.setAttribute("SEARCH", map);
		
//		HashMap	temp = (HashMap) session.getAttribute("SEARCH");
		
		//	페이지 나눔을 만들기 위한 작업은 생략했으므로 숙제로 해본다.
		
		//	검색하기
		HashMap map = new HashMap();
		map.put("kind", kind);
		map.put("CONTENT", content);
		ArrayList	list = sDao.selectSearch(map);
		//	페이지에 필요한 데이터만 골라내는 작업이 필요하고.....
		
		//	뷰 부르기
		mv.addObject("LIST", list);
		mv.setViewName("Smart/BoardSearch");
		return mv;
	}
}






