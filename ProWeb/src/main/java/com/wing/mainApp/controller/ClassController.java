package com.wing.mainApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wing.mainApp.dao.ClassDAO;
import com.wing.mainApp.data.ClassListData;
import com.wing.mainApp.util.PagingUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class ClassController {
	
	@Autowired
	ClassDAO cDao;
	/*
	 	���Ǹ�� ����
	 */
	@RequestMapping("/classlist/classmain.do")
	public ModelAndView classmain(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/classmain");
		return mv;
	}
	/*
		�α⺰ ���
	 */
	@RequestMapping("/classlist/bestlist.do")
	public ModelAndView bestlist(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/bestlist");
		return mv;
	}
	/*
	 	���纰 ���
	 */
	@RequestMapping("/classlist/teacherlist.do")
	public ModelAndView teacherlist(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/teacherlist");
		return mv;
	}
	/*
	 	���Ǻ� ���
	 */
	@RequestMapping("/classlist/classList.do")
	public ModelAndView classlist(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/classlist");
		return mv;
	}
	/*
	 	�󼼺��� ��û
	 */
	@RequestMapping("/classlist/classview.do")
	public ModelAndView classview(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		String strNo = req.getParameter("oriNo");
		int oriNo = Integer.parseInt(strNo);
		String strPage = req.getParameter("nowPage");
		int nowPage = Integer.parseInt(strPage);
		
		ClassListData map = cDao.selectView(oriNo);
		
		mv.addObject("DATA", map);
		mv.addObject("NOWPAGE", nowPage);
			
		mv.setViewName("classlist/classview");
		return mv;
	}
	/*
	 * 	�˻� ��û ó�� �Լ�
	 */
	@RequestMapping("/classlist/classSearch")
	public ModelAndView	classSearch(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	����
		//		�Ѿ�� �Ķ���� �޴´�.
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		String	kind = req.getParameter("kind");
		String	content = req.getParameter("content");
		//	�� 2���� �˻��� ���ؼ� �ʼ� �������ε�....
		//	����� ������ ������ �̵� ����� ���� ���̰�
		//	�׷��� 2���� �����ʹ� �Ѿ���� �ʴ´�.
		//	������		������ �̵���ɿ� ���ؼ� �ٸ� �������� ������ �ϸ�?
		//	�ذ� ���	���� ���� �����͸� ���ǿ� ����� ���� �������.
		if(StringUtil.isNull(kind)) {
			//	�̰� �������� ������ �̵����� �Ѿ�ͼ� ���� ���� ���̴�.
			//	�׷��� �� ���� ���ǿ��� �޵��� ����.
			kind = (String) session.getAttribute("kind");
			content = (String) session.getAttribute("content");
			//	�׷��� ���޾�����	�ٸ� ������ �������� �Ѵ�.
			if(StringUtil.isNull(kind)) {
				//	Redirect ��Ų��.
			}
		}
		//	���� ��� �޾Ҵ��� ���� �˻��� �����Ͱ� �ϼ��Ǿ���.
		//	�׷� �������� ���ؼ� ���ǿ� ����� ���ƾ� �ϰڴ�.
		session.setAttribute("kind", kind);
		session.setAttribute("content", content);
		
		//		������ ������ ���Ѵ�.(�� ������ ������ ���Ѵ�.)
		HashMap	map = new HashMap();
		map.put("kind", kind);
		map.put("CONTENT", content);
		int	count = cDao.getSearchCount(map);
		PagingUtil	pInfo = new PagingUtil(nowPage, count, 5, 5);
//		pInfo.calcInfo2();
		
		//		�˻� �����͸� ���Ѵ�.
		ArrayList	list = cDao.getSearch(map);
		//	����� ������ ��ɿ� ������� ��� �����͸� �� ���Ѱ��̹Ƿ�
		//	�� ���������� ������ ������ ������ �Ѵ�.
		ArrayList	result = new ArrayList();
		if(list.size() != 0) {
			//	���� ������ġ�� ���� ������ ��ġ�� ���Ѵ�.
			int		start = (pInfo.nowPage - 1) * pInfo.onePageCount;
			int		end = start + pInfo.onePageCount - 1;
			//	������ �������� ��츦 ó���Ѵ�.
			if(end >= list.size()) {
				end = list.size() - 1;
			}
			for(int i = start; i <= end; i++) {
				result.add(list.get(i));
			}
		}
		//		�信�� �˷��ش�.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		mv.setViewName("classlist/classSearch");
		return mv;
	}
}