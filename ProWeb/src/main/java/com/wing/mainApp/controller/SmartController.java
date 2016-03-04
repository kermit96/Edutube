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
	//	�� ��Ʈ�ѷ����� ����� DAO�� �޾ƿ´�.
	@Autowired
	SmartBoardDAO	sDao;
	
	
	
	/*
	 * 	�۾��� �� ��û ó�� �Լ�
	 */
	@RequestMapping("/Smart/BoardWriteForm")
	public ModelAndView	boardWriteForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	���� �˻縦 �Ѵ�.
		//		��å	�α����� ���� ���� ����� �۾��⸦ �Ҽ� ������ �ϰ��� �Ѵ�.
		if(!SessionUtil.isSession(session)) {
			//	�α����� ���� ���� ����̴�.
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		
		mv.setViewName("Smart/BoardWriteForm");
		return mv;
	}
	
	/*
	 * 	��Ϻ��� ��û ó�� �Լ�
	 */
	@RequestMapping("/Smart/BoardList")
	public ModelAndView	 boardList(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	���� ������ �Ѵ�.
		//		����� �ƹ��� �� �� �ֵ��� �Ѵ�.
		//	�Ķ���� �޴´�.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	�Ķ���ʹ� �ݵ�� ������ �ؼ� ���� ��� ó���� �ؾ��Ѵ�.
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	������ ���� �����
		int	total = sDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	��� ���ϰ�
		//		���ϴ� ��ġ�� �ֱ�� ����ߴ�.
		//									start		end
		//		nowPage	1������		1 ~ 		5
		//					2������		6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	������ �������� 5���� �ȵ� ���� �����Ƿ�.....
		//	�� ������ �������� ������ �� �����Ͱ�����ŭ ������ �ȴ�.
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = sDao.selectList(map);
		
		//	�並 �θ���.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("Smart/BoardList");
		return mv;
	}
	
	/*
	 * 	�۾��� ��û
	 */
	@RequestMapping("/Smart/BoardWrite")
	public ModelAndView	boardWrite(HttpSession session, SmartBoardData data) {
		ModelAndView		mv = new ModelAndView();
		//	����		���Ѱ˻�
		if(!SessionUtil.isSession(session)) {
			//	�α����� ���� ���� ����̴�.
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		//				������ �ޱ�
		//	�� �߿� �ʼ� ����� �ݵ�� �˻��ؼ� ���� �ʾ����� ��ġ�� ���ؾ� �Ѵ�.
		if(StringUtil.isNull(data.title) || StringUtil.isNull(data.body) || StringUtil.isNull(data.pw)) {
			RedirectView	rv = new RedirectView("../Smart/BoardWriteForm.dol");
			mv.setView(rv);
			return mv;
		}
		//				������ ���� ���ε� �ϱ�
		//				���� ���ε尡 ���� ������ ���....
		//				������ ���ε� ���� �ʴ� ��츦 �����ؼ� �۾��Ѵ�.
		String	path = "E:\\FileUpload";
		//	�����ͺ��̽��� �����ϱ� ���ؼ� ÷�� ������ ������ �˾Ƴ��´�.
		//	������ ���� �غ��ϰ�
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		//	Ȥ�� �߰��� �������� ���� �� �����Ƿ�..... ī��Ʈ ���Ѻ���.
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
					//	��������.
					oriname[count] = data.upfile[i].getOriginalFilename();
					savename[count] = FileUtil.rename(path, oriname[count]);
					len[count] = data.upfile[i].getSize();
					try {
						File	file = new File(path, savename[count]);
						data.upfile[i].transferTo(file);
					}
					catch(Exception e) {
						System.out.println("���ε� ���� " + e);
					}
					count = count + 1;
				}
			}
		}
		//				�����ͺ��̽��� ����ϱ�
		//				1.	�Խù� ������ �����ϱ�
		//	������ ������ �ޱ�
		String	id = (String) session.getAttribute("ID");
		data.id = id;
		
		int	no = sDao.selectMax(); 
		data.no = no;
		
		sDao.insertBoard(data);
		
		if(count != 0) {
			//		2.	÷�� ���� ������ �����ϱ�
			//	������ ������ �� Ŭ������ �ֱ�� ����ߴ�.
			SmartBoardData	temp = new SmartBoardData();
			//	��� ������ �������� ���Ǵ� ���� �̸� �־����.
			temp.no = no;
			temp.path = path;
			for(int i = 0; i < count; i++) {
				temp.oriname = oriname[i];
				temp.savename = savename[i];
				temp.len = len[i];
				sDao.insertFile(temp);
			}
		}
		
		//				��
		RedirectView	rv = new RedirectView("../Smart/BoardList.dol");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	�󼼺��� ��û ó�� �Լ�
	 */
	@RequestMapping("/Smart/BoardView")
	public ModelAndView boardView(HttpSession session, SmartBoardData data) {
//	public String boardView(Model model) {
		//	�츮�� ����� ���� MVC ����� �̿��� ���
		//	��,	���� �̸��� �˷��ִ� ���
		//		����	req.setAttribute()�� �̿��ؼ� ó���ϴ� ���
		//		���� model.addObject("", "");
		ModelAndView		mv = new ModelAndView();
		//	���� �˻�
		if(!SessionUtil.isSession(session)) {
			//	�α����� ���� ���� ����̴�.
			RedirectView	rv = new RedirectView("../Member/LoginForm.dol");
			mv.setView(rv);
			return mv;
		}
		
		//	�Ķ���� �ް�
		//		�Ķ���Ͱ� Ȥ�� ���޵��� �ʾ��� ��츦 �ݵ�� ó���ض�.
		
		//	�ʿ��� ������ ������
		//		1.	�󼼺���
		SmartBoardData	rData = sDao.selectView(data.oriNo);
		//		2.	����
		ArrayList			fList = sDao.selectFile(data.oriNo);
		//		3.	������, ������
		HashMap			map = sDao.selectPreNext(data.oriNo);
		
		//	�並 �θ���.
		mv.addObject("VIEW", rData);
		mv.addObject("FILES", fList);
		mv.addObject("MAP", map);
		//	���� �� ��Ʈ�ѷ��� �������� ���� �Ķ���Ͱ� �����Ѵ�.
		//	������ �Ķ���ʹ� ��Ʈ�ѷ����� ����� �ʿ�� ������ �����̸�
		//	��Ű�� ���ؼ� �信�� �˷��־�� �Ѵ�.
		mv.addObject("NOWPAGE", data.nowPage);
		mv.addObject("KIND", data.kind);
		mv.setViewName("Smart/BoardView");
		return mv;
	}
	
	/*	
	 * ��ȸ�� ���� ��û ó�� �Լ�
	 */
	@RequestMapping("/Smart/BoardHit")
	public ModelAndView boardHit(SmartBoardData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�ʿ��� ���� ��ġ��
		
		//	��ȸ�� �����Ѵ�.
		//	���� �� ����� ���̵� ���Ѵ�.
		String	id = (String) session.getAttribute("ID");
		//	�� ����� �̹̺� �۹�ȣ�� �˾Ƴ���.
		String	preHit = sDao.selectHit(id);
		boolean	isHit = false;			//	��ȸ���� �������� ���θ� �Ǵ��� ����
		HashMap	map = new HashMap();
		if(StringUtil.isNull(preHit)) {
			//	�� ����� ó�� �Խ��ǿ� ���� ����̴�.
			isHit = true;
			//	�� ����� ���� �� �۹�ȣ�� ���Ӱ� ������ش�.
			map.put("ID", id);
			map.put("HIT", ":" + data.oriNo + ":");
			sDao.insertHit(map);
		}
		else {
			//	�� ����� �ѹ� �̻� �Խ����� ����ߴ� ����̴�.
			//	�� ����� �� ���� �ô����� �˻�����.
			int		isShow = preHit.indexOf(":" + data.oriNo + ":");
			if(isShow == -1) {
				//	�� �۹�ȣ�� ����.
				isHit = true;
				//	���� �������� �������� ���ϰ� �� ��ȣ�� ������ ���´�.
				map.put("ID", id);
				map.put("HIT", preHit + ":" + data.oriNo + ":");
				sDao.updateHit(map);
			}
			else {
				//	�� ���� �ôµ���
				isHit = false;
			}
		}
		
		//	��ȸ���� ������ �ʿ䰡 ������ �����Ѵ�.
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
	 * 	���� �ٿ�ε� ��û ó�� �Լ�
	 */
	@RequestMapping("/Smart/BoardDown")
	public ModelAndView		boardDown(HttpServletRequest req) {
		ModelAndView		mv = null;
		//	Ư���並 �ҷ��;� �ϹǷ� new ��Ű�� ����� �ٸ���.
		//	����
		//		���Ѽ���
		//		�Ķ���� �ޱ�
		String	strNo = req.getParameter("no");
		//		�Ķ���Ͱ� Ȥ�� ���� ��� ó���� ���ش�.
		int		no = Integer.parseInt(strNo);
		
		//	�ٿ�ε� ȸ���� �������Ѻ���
		//	���� ����� ���ǿ� ������� ������ ����������� �Ѵ�.
		//	����
		
		
		//		���� ���� ���
		HashMap	rMap = sDao.selectFileInfo(no);
		
		//		Ư���� �θ���(Ư���䰡 �˾Ƽ� �ٿ�ε� ���ش�.)
		//		Ư����� ����ڰ� ����� ����ϴ� �����̴�.
		//		�츮��		�����̸�			String		oirname
		//					�ٿ�ε��� ����	File			downloadFile
		HashMap	map = new HashMap();
		map.put("oirname", rMap.get("ORINAME"));
		
		File			file = new File((String)rMap.get("PATH"), 
												(String)rMap.get("SAVENAME"));
		map.put("downloadFile", file);
		
		mv = new ModelAndView("download", map);
		//	���̸���	xml ���Ͽ� ����� Ư������ id���� �ȴ�.
		return mv;
	}
	
	/*
	 * 	������ ��û ó�� �Լ�
	 */
	@RequestMapping("/Smart/BoardModifyForm")
	public ModelAndView	boardModifyForm(SmartBoardData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	����
		//		���Ѽ���
		//		�Ķ���� �޴´�.
		
		//		����� ������ Ȯ���Ѵ�.
		String	id = (String) session.getAttribute("ID");
		
		SmartBoardData rData = sDao.selectPwInfo(data.oriNo);
		if(rData.id.equals(id) && rData.pw.equals(data.pw)) {
			//	������ �� �ִ�.
			//		���ϴ� �並 �ҷ��ְ�
			//	�����ϱ� ���� ����(��������, ��������)�� ������ �˷��ش�.
			SmartBoardData 	dData = sDao.selectView(data.oriNo);
			ArrayList			fList = sDao.selectFile(data.oriNo);
			
			//	�����̸� ���� �Ķ����� �Ѱ��ְ�
			mv.addObject("NOWPAGE", data.nowPage);
			mv.addObject("KIND", data.kind);
			mv.addObject("ORINO", data.oriNo);
			mv.addObject("DATA", dData);
			mv.addObject("FILES", fList);
			mv.setViewName("Smart/BoardModifyForm");
			return mv;
		}
		else {
			//	���� ������ ����.
			//		�󼼺���� ������.
			RedirectView	rv = new RedirectView("../Smart/BoardView.dol");
			rv.addStaticAttribute("nowPage", data.nowPage);
			rv.addStaticAttribute("kind", data.kind);
			rv.addStaticAttribute("oriNo", data.oriNo);
			mv.setView(rv);
			return mv;
		}
	}
	
	/*
	 * 	���� ���� ��û
	 */
	@RequestMapping("/Smart/BoardFileDelete")
	public ModelAndView	boardFileDelete(@RequestParam("no") int no) {
		ModelAndView		mv = new ModelAndView();
		//	����
		//		��������
		//		�Ķ����
		//		�����ϱ� ���� ���� ������ �˾Ƴ���.
		//		���߿� �� ������ �־ ������ �ִ� ������ ������ �� �ֱ� �����̴�.
		HashMap	data = sDao.selectFileInfo(no);
		
		//		�����ͺ��̽����� ����
		sDao.fileDelete(no);
		
		//		���� ������ �ִ� ���ϵ� ����
		//		�Ϲ� java API�� �̿��ؼ� �����Ѵ�.
		File		file = new File((String)data.get("PATH"), (String)data.get("SAVENAME"));
		file.delete();
		
		//		�並 �θ���.
		mv.setViewName("Smart/BoardFileDelete");
		return mv;
	}
	
	/*
	 * 	�����ϱ� ��û ó�� �Լ�
	 */
	@RequestMapping("/Smart/BoardModify")
	public ModelAndView		boardModify(SmartBoardData data) {
		ModelAndView		mv = new ModelAndView();
		//	����
		//		���Ѱ˻�
		//		�Ķ���� �ޱ�(�˻���� ����)
		//		÷�������� �����ϸ� ������ ������ �����ϱ�
		//	÷�������� ������ ���߿� �����ͺ��̽��� �����ؾ� �ϹǷ�.....
		//	�� ������ ����� ����
		
		//	�迭�� ��� ������ ���� �𸣹Ƿ�.....
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		int			count = 0;			//	÷�������� ������ ����� ���� 
		String	path = "E:\\FileUpload";
		if(data.upfile != null && data.upfile.length != 0) {
			//	���� ������ �迭�� ������
			oriname = new String[data.upfile.length];
			savename = new String[data.upfile.length];
			len = new long[data.upfile.length];
			
			for(int i = 0; i < data.upfile.length; i++) {
				if(data.upfile[i].isEmpty()) {
					continue;
				}
				//	���� �˾Ƴ���
				//		count�� ����ϴ� ������ ������ �ö�� ������ ���� ������ �ٸ� �� �ִ�.
				oriname[count] = data.upfile[i].getOriginalFilename();
				savename[count] = FileUtil.rename(path, oriname[count]);
				len[count] = data.upfile[i].getSize();
				//	������ ��������
				try {
					File		file = new File(path, savename[count]);
					data.upfile[i].transferTo(file);
				}
				catch(Exception e) {
					
				}
				count++;
			}
		}
		
		//		�����ͺ��̽��� ���� �����ϱ�
		sDao.updateBoard(data);
		//		÷�������� �����ϸ� ������ ������ �����ͺ��̽��� ����ϱ�
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
		//		�並 �θ���
		RedirectView	rv = new RedirectView("../Smart/BoardView.dol");
		rv.addStaticAttribute("nowPage", data.nowPage);
		rv.addStaticAttribute("kind", data.kind);
		rv.addStaticAttribute("oriNo", data.oriNo);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * �˻��ϱ� ��û
	 */
	@RequestMapping("/Smart/BoardSearch")
	public ModelAndView boardSearch(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�Ķ����
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
		//	����(�������� ������ ó���� ���ƾ� �Ѵ�.)
		//		������ �̵������ �̿��ϰ� �Ǹ� kind, content�� �ȳѾ�´�.
		//		���� ������ ������� �Ѱܵ� ������......
		//		����
		//			GET ������� ó���ϴ� ���� �ѱ��� �� �� ������
		//			������� �ʴ´�.
		//	���� �������� ������ ���ǿ��� �޾Ƽ� ����ϵ��� �Ѵ�.
		if(StringUtil.isNull(kind)) {
			kind = 		(String)session.getAttribute("kind");
			content = 	(String)session.getAttribute("content");
		}
		//	Ȥ�� ���� �Ѿ�� �����͸� �������� ���ǿ��� ã�� �ʿ䰡 ���� ���̹Ƿ�
		//	���ǿ� ������ ���´�.
		session.setAttribute("kind", kind);
		session.setAttribute("content", content);
		
		//	���ǿ��� ��� ������ ������ ������ �� �ִ�.
		//	���� ��� ���ǿ� ������ �����Ͱ� ������.....
//		HashMap map = new HashMap();
//		map.put("kind", kind);
//		map.put("content", content);
//		session.setAttribute("SEARCH", map);
		
//		HashMap	temp = (HashMap) session.getAttribute("SEARCH");
		
		//	������ ������ ����� ���� �۾��� ���������Ƿ� ������ �غ���.
		
		//	�˻��ϱ�
		HashMap map = new HashMap();
		map.put("kind", kind);
		map.put("CONTENT", content);
		ArrayList	list = sDao.selectSearch(map);
		//	�������� �ʿ��� �����͸� ��󳻴� �۾��� �ʿ��ϰ�.....
		
		//	�� �θ���
		mv.addObject("LIST", list);
		mv.setViewName("Smart/BoardSearch");
		return mv;
	}
}






