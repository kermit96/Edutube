package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {
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
	@RequestMapping("/classlist/classlist.do")
	public ModelAndView classlist(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/classlist");
		return mv;
	}
	/*
	 * 	�˻� ��û ó�� �Լ�
	 */
	/*@RequestMapping("/classlist/classSearch")
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
		//	�ذ� ���		���� ���� �����͸� ���ǿ� ����� ���� �������.
		if(StringUtil.isNull(kind)) {
			//	�̰� �������� ������ �̵����� �Ѿ�ͼ� ���� ���� ���̴�.
			//	�׷��� �� ���� ���ǿ��� �޵��� ����.
			kind = (String) session.getAttribute("kind");
			content = (String) session.getAttribute("content");
			//	�׷��� ���޾�����...	�ٸ� ������ �������� �Ѵ�.
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
		int	count = rDao.getSearchCount(map);
		PageUtil	pInfo = new PageUtil(nowPage, count, 5, 5);
//		pInfo.calcInfo2();
		
		//		�˻� �����͸� ���Ѵ�.
		ArrayList	list = rDao.getSearch(map);
		//	����� ������ ��ɿ� ������� ��� �����͸� �� ���Ѱ��̹Ƿ�
		//	�� ���������� ������ ������ ������ �Ѵ�.
		ArrayList	result = new ArrayList();
		if(list.size() != 0) {
			//	���� ������ġ�� ���� ������ ��ġ�� ���Ѵ�.
			int		start = (pInfo.nowPage - 1) * pInfo.pageList;
			int		end = start + pInfo.pageList - 1;
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
	}*/
}