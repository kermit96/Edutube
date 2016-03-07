package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.GoodsManagerDAO;
import com.wing.mainApp.data.GoodsCateData;
import com.wing.mainApp.data.GoodsInfoData;
import com.wing.mainApp.util.FileUtil;
import com.wing.mainApp.util.StringUtil;

@Controller
public class GoodsManagerController {
	//	이 컨트롤러에서 사용할 DAO를 DI 기법으로 불러오자.
	@Autowired
	private GoodsManagerDAO	gmDao;
	
	/*
	 * 	상품 등록 폼 요청 처리 함수
	 */
	@RequestMapping("/GoodsManager/GoodsGegForm")
	public ModelAndView	goodsGegForm() {
		ModelAndView		mv = new ModelAndView();
		//	할일
		//		관리자 권한 체크하고
		
		//		상품 카테고리 중에서 대 카테고리를 불러오고
		ArrayList	list = gmDao.getCate(null, 1);	
		//		뷰를 부른다.
		mv.addObject("LCATE", list);
		mv.setViewName("GoodsManager/GoodsGegForm");
		return mv;
	}
	
	/*
	 * 	대카테고리 등록 요청 처리 함수
	 */
	@RequestMapping("/GoodsManager/GoodsLCateGeg")
	public ModelAndView		goodsLCateGeg(GoodsCateData data) {
		ModelAndView		mv = new ModelAndView();
		//	데이터베이스에 기록하자.
		gmDao.insertCate(data, 1);
		//	뷰를 부른다.
		RedirectView	rv = new RedirectView("../GoodsManager/GoodsGegForm.do");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	중카테고리 등록 함수
	 */
	@RequestMapping("/GoodsManager/GoodsMCateGeg")
	public ModelAndView		goodsMCateGeg(GoodsCateData data) {
		ModelAndView		mv = new ModelAndView();
		//	데이터베이스에 기록하자.
		gmDao.insertCate(data, 2);
		//	뷰를 부른다.
		RedirectView	rv = new RedirectView("../GoodsManager/GoodsGegForm.do");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	소카테고리 등록 함수
	 */
	@RequestMapping("/GoodsManager/GoodsSCateGeg")
	public ModelAndView		goodsSCateGeg(GoodsCateData data) {
		ModelAndView		mv = new ModelAndView();
		//	데이터베이스에 기록하자.
		gmDao.insertCate(data, 3);
		//	뷰를 부른다.
		RedirectView	rv = new RedirectView("../GoodsManager/GoodsGegForm.do");
		mv.setView(rv);
		return mv;
	}

	/*
	 * 	중 카테고리 검색 요청 함수
	 */
	@RequestMapping("/GoodsManager/GoodsMCateSelect")
	public ModelAndView	goodsMCateSelect(GoodsCateData data) {
		ModelAndView		mv = new ModelAndView();
		
		//	검색한다.
		ArrayList	list = gmDao.getCate(data.getLcode(), 2);
		//	뷰를 부른다.
		mv.addObject("MCATE", list);
		mv.setViewName("GoodsManager/GoodsMCate");
		return mv;
	}
	/*
	 * 	소 카테고리 검색 요청 함수
	 */
	@RequestMapping("/GoodsManager/GoodsSCateSelect")
	public ModelAndView	goodsSCateSelect(GoodsCateData data) {
		ModelAndView		mv = new ModelAndView();
		
		//	검색한다.
		System.out.println(data.getMcode());
		ArrayList	list = gmDao.getCate(data.getMcode(), 3);
		//	뷰를 부른다.
		mv.addObject("SCATE", list);
		mv.setViewName("GoodsManager/GoodsSCate");
		return mv;
	}

	/*
	 * 상품 등록 요청
	 */
	@RequestMapping("/GoodsManager/GoodsReg")
	public ModelAndView	goodsReg(GoodsInfoData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	권한 점검
		//	파라메터 받기
		//	파일 업로드 하기
		//		반드시 파일이 존재하지 않을 경우를 대비해서 처리하세요.
		//		(혹시라도 아직 상품이미지가 준비되지 않은 경우를 대비해서 처리한다.)
		String	gpath = session.getServletContext().getRealPath("gimgs");
		String	ipath = session.getServletContext().getRealPath("iimgs");
		
		String	gimg1 = data.getGimg().getOriginalFilename();
		String	gimg2 = "";
		
		//	혹시라도 선택적인 내용이 있을 경우
		//	이것을 데이터베이스에 INSERT할때 문제가 생길 수 있다.
		//	null 인상태로 데이터베이스에 INSERT를 시키면 nullPointException이 발생한다.
		//	반드시	null 상태이면 Empty 상태("")로 만들어서 INSERT 시켜야 한다.
		if(StringUtil.isNull(gimg1)) {
			gimg2 = "";			//	null이면 데이터베이스에 기록되지 않으므로
									//	null이 아닌 empty 상태로 준비해야 한다.
		}
		else {
			gimg2 = FileUtil.rename(gpath, gimg1);
			//	서버에 저장한다.
			File temp = new File(gpath, gimg2);
			try {
				data.getGimg().transferTo(temp);
			}
			catch(Exception e) {}
		}
		String	iimg1 = data.getIimg().getOriginalFilename();
		String	iimg2 = "";
		if(StringUtil.isNull(iimg1)) {
			iimg2 = "";
		}
		else {
			iimg2 = FileUtil.rename(ipath, iimg1);
			File	temp = new File(ipath, iimg2);
			try {
				data.getIimg().transferTo(temp);
			}
			catch(Exception e) {}
		}
		
		//	데이터베이스에 기록하기
		//	다른 데이터는 다 준비가 되었는데 저장파일이름은 없다.
		data.setGimg2(gimg2);
		data.setIimg2(iimg2);
		
		gmDao.insertGoodsInfo(data);
		//	뷰 부르기
		RedirectView	rv = new RedirectView("../GoodsManager/GoodsGegForm.do");
		mv.setView(rv);
		return mv;
	}
}
