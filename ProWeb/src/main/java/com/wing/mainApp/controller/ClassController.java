package com.wing.mainApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassController {
	/*
	 	강의목록 메인
	 */
	@RequestMapping("/classlist/classmain.do")
	public ModelAndView classmain(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/classmain");
		return mv;
	}
	/*
		인기별 목록
	 */
	@RequestMapping("/classlist/bestlist.do")
	public ModelAndView bestlist(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/bestlist");
		return mv;
	}
	/*
	 	강사별 목록
	 */
	@RequestMapping("/classlist/teacherlist.do")
	public ModelAndView teacherlist(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/teacherlist");
		return mv;
	}
	/*
	 	강의별 목록
	 */
	@RequestMapping("/classlist/classlist.do")
	public ModelAndView classlist(HttpServletRequest req, HttpSession session){
		ModelAndView 	mv = new ModelAndView();
		
		mv.setViewName("classlist/classlist");
		return mv;
	}
	/*
	 * 	검색 요청 처리 함수
	 */
	/*@RequestMapping("/classlist/classSearch")
	public ModelAndView	classSearch(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		넘어온 파라메터 받는다.
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
		//	위 2개는 검색을 위해서 필수 데이터인데....
		//	결과가 많으면 페이지 이동 기능이 있을 것이고
		//	그러면 2개의 데이터는 넘어오지 않는다.
		//	문제점		페이지 이동기능에 의해서 다른 페이지를 보고자 하면?
		//	해결 방법		현재 받은 데이터를 세션에 기억해 놓고 사용하자.
		if(StringUtil.isNull(kind)) {
			//	이건 보나마나 페이지 이동으로 넘어와서 받지 못한 것이다.
			//	그래서 이 경우는 세션에서 받도록 하자.
			kind = (String) session.getAttribute("kind");
			content = (String) session.getAttribute("content");
			//	그래도 못받았으면...	다른 곳으로 내보내야 한다.
			if(StringUtil.isNull(kind)) {
				//	Redirect 시킨다.
			}
		}
		//	이제 어디서 받았던지 간에 검색할 데이터가 완성되었다.
		//	그럼 다음번을 위해서 세션에 기록해 놓아야 하겠다.
		session.setAttribute("kind", kind);
		session.setAttribute("content", content);
		
		//		페이지 정보를 구한다.(총 데이터 개수를 구한다.)
		HashMap	map = new HashMap();
		map.put("kind", kind);
		map.put("CONTENT", content);
		int	count = rDao.getSearchCount(map);
		PageUtil	pInfo = new PageUtil(nowPage, count, 5, 5);
//		pInfo.calcInfo2();
		
		//		검색 데이터를 구한다.
		ArrayList	list = rDao.getSearch(map);
		//	여기는 페이지 기능에 관계없이 모든 데이터를 다 구한것이므로
		//	그 페이지에서 보여줄 갯수만 꺼내야 한다.
		ArrayList	result = new ArrayList();
		if(list.size() != 0) {
			//	꺼낼 시작위치와 꺼낼 마지막 위치를 구한다.
			int		start = (pInfo.nowPage - 1) * pInfo.pageList;
			int		end = start + pInfo.pageList - 1;
			//	마지막 페이지인 경우를 처리한다.
			if(end >= list.size()) {
				end = list.size() - 1;
			}
			for(int i = start; i <= end; i++) {
				result.add(list.get(i));
			}
		}
		//		뷰에게 알려준다.
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", result);
		mv.setViewName("classlist/classSearch");
		return mv;
	}*/
}