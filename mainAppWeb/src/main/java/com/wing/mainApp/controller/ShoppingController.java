package com.wing.mainApp.controller;

import java.util.ArrayList;
import java.util.HashMap;

import 	javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import 	org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import 	org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.GoodsCartDAO;
import com.wing.mainApp.dao.GoodsManagerDAO;
import com.wing.mainApp.data.GoodsCartData;
import com.wing.mainApp.data.GoodsInfoData;
import com.wing.mainApp.util.StringUtil;

@Controller
public class ShoppingController {
	//	�궗�슜 DAO瑜� 留뚮뱺�떎.
	@Autowired
	GoodsManagerDAO	gmDao;
	//	�떎瑜� DAO�룄 媛숈씠 �궗�슜�빐�빞 �븯誘�濡� �븳踰덈뜑 �꽑�뼵�븳�떎.
	@Autowired
	GoodsCartDAO		cDao;
	
	
	/*
	 * 	�긽�뭹 紐⑸줉 蹂닿린 �슂泥� 
	 */
	@RequestMapping("/Shopping/GoodsList")
	public ModelAndView		goodsList(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	�뙆�씪硫뷀꽣 諛쏄린
		String	cate = req.getParameter("cate");
		//	�븘�닔媛� �븘�땲�떎.
		
		//		蹂댁뿬以� �� 移댄뀒怨좊━, 以� 移댄뀒怨좊━瑜� �븣�븘�빞 �빐�떦 移댄뀒怨좊━瑜�
		//		�븣�븘�궪 �닔 �엳�떎.
		//	�궗�슜�옄媛� 蹂대궡以� 移댄뀒怨좊━瑜� �씠�슜�빐�꽌 蹂댁뿬以� ��, 以� 移댄뀒怨좊━瑜� �븣�븘�빞 �븳�떎.
		String	lCate = "";
		String	mCate = "";		//	�떎�쓬 移댄뀒怨좊━瑜� �꽑�깮�븷 �쁽�옱 移댄뀒怨좊━瑜�
									//	湲곗뼲�븷 蹂��닔
		
		//	�긽�뭹 紐⑸줉 寃��깋�쓣 �쐞�빐�꽌�뒗 �쁽�옱 �긽�깭媛� �뼱�뼡 �긽�깭�씤吏�
		//	(�뼱�뼡 �젅踰⑥쓽 移댄뀒怨좊━瑜� �꽑�깮�뻽�뒗吏�)瑜� �븣�븘�빞 �븳�떎.
		HashMap	map = new HashMap();
		//	1.	�궗�슜�옄媛� 移댄뀒怨좊━瑜� 蹂대궡二쇱� �븡�� 寃쎌슦
		if(StringUtil.isNull(cate)) {
			//	�젙梨�
			//		媛��옣 泥ル쾲吏� 移댄뀒怨좊━瑜� �씠�슜�빐�꽌 泥섎━�븷 �삁�젙�씠�떎.
			lCate = "L001";
			mCate = "L001M001";
			
			map.put("KIND", "LARGE");
			map.put("CODE", lCate);
		}
		//	2.	�궗�슜�옄媛� �� 移댄뀒怨좊━瑜� �꽑�깮�븳 寃쎌슦
		else if(cate.length() == 4) {
			lCate = cate;
			//	�젙梨�
			//		�궗�슜�옄媛� �꽑�깮�븳 �� 移댄뀒怨좊━�쓽 泥ル쾲吏� 以� 移댄뀒怨좊━濡� �젙�븷 �삁�젙�씠�떎.
			mCate = cate + "M001";
			map.put("KIND", "LARGE");
			map.put("CODE", lCate);
		}
		//	3.	�궗�슜�옄媛� 以� 移댄뀒怨좊━瑜� �꽑�깮�븳 寃쎌슦
		else if(cate.length() == 8) {
			lCate = cate.substring(0, 4);
			mCate = cate;
			map.put("KIND", "MIDDLE");
			map.put("CODE", mCate);
		}
		//	4.	�궗�슜�옄媛� �냼 移댄뀒怨좊━瑜� �꽑�깮�븳 寃쎌슦
		else if(cate.length() == 12) {
			//	�궗�슜�옄媛� 蹂대궡以� �냼 移댄뀒怨좊━瑜� �씠�슜�빐�꽌 ��, 以� 移댄뀒怨좊━瑜� �꽑�깮�븳�떎.
			lCate = cate.substring(0, 4);
			mCate = cate.substring(0, 8);
			map.put("KIND", "SMALL");
			map.put("CODE", cate);
		}

		//	�긽�뭹 移댄뀒怨좊━ 蹂댁뿬二쇨린	
		ArrayList	lList = gmDao.getCate("", 1);
		ArrayList	mList = gmDao.getCate(lCate, 2);
		ArrayList	sList = gmDao.getCate(mCate, 3);
		
		//	�긽�뭹紐⑸줉 蹂댁뿬二쇨린
		ArrayList	gList = gmDao.selectGoodsList(map);
		
		mv.addObject("CATE", cate);
		mv.addObject("LCATE", lList);
		mv.addObject("MCATE", mList);
		mv.addObject("SCATE", sList);
		mv.addObject("GOODS", gList);
		mv.setViewName("Shopping/GoodsList");
		return mv;
	}
	
	/*
	 * 	�긽�뭹 �긽�꽭蹂닿린
	 */
	@RequestMapping("/Shopping/GoodsView")
	public ModelAndView	goodsView(HttpServletRequest req) {
		ModelAndView		mv = new ModelAndView();
		//	�븷�씪
		//	�뙆�씪硫뷀꽣 諛쏄린
		String	strNo = req.getParameter("no");
		int		no = Integer.parseInt(strNo);
		String	cate = req.getParameter("cate");
		
		//		�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 �젙蹂� 爰쇰궡湲�
		GoodsInfoData	data = gmDao.selectGoodsInfo(no);

		//		酉곗뿉寃� �븣�젮二쇨린
		mv.addObject("DATA", data);
		mv.addObject("CATE",  cate);
		mv.setViewName("Shopping/GoodsView");
		return mv;
	}
	
	/*
	 * 	�옣諛붽뎄�땲 �떞湲� �슂泥� �떎�뻾 �븿�닔
	 */
	@RequestMapping("/Shopping/CartInsert")
	public ModelAndView		cartInsert(GoodsCartData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�븷�씪
		//		�뙆�씪硫뷀꽣 諛쏄퀬
		//		鍮꾪쉶�썝�젣 �씠誘�濡� �꽭�뀡id 援ы븯怨�
		String	sessionID = session.getId();
		//	�뜲�씠�꽣 鍮� �겢�옒�뒪�뿉 �꽔�뼱�넃�옄
		data.setId(sessionID);
		
		//		�뜲�씠�꽣踰좎씠�뒪 泥섎━�븳�떎.
		//		媛숈� �궗�엺�씠 媛숈� �긽�뭹�쓣 �떎�떆 �옣諛붽뎄�땲�뿉 �떞�쑝硫�
		//		insert媛� �븘�땶 update瑜� �븯�룄濡� �븯怨� �떢�떎.
		//	�썝由�
		//		1.		癒쇱�	洹� �궗�엺�씠 洹� 臾쇨굔�쓣 �옣諛붽뎄�땲�뿉 �떞�� �궡�뿭�씠 �엳�뒗吏� �솗�씤�븳�떎.
		int	count = cDao.selectCart(data);
		if(count != 0) {
			//		2.		�궡�뿭�씠 �엳�쑝硫� update
			cDao.cartInfoChange(data, GoodsCartDAO.UPDATE);
		}
		else {
			//		3.		�궡�뿭�씠 �뾾�쑝硫� insert
			cDao.cartInfoChange(data, GoodsCartDAO.INSERT);
		}
		
		//		酉곕�� 遺�瑜몃떎.
		RedirectView	rv = new RedirectView("../Shopping/GoodsView.do");
		rv.addStaticAttribute("no", data.getNo());
		rv.addStaticAttribute("cate", data.getCate());
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	�옣諛붽뎄�땲 蹂닿린 �슂泥� �떎�뻾 �븿�닔
	 */
	@RequestMapping("/Shopping/CartView")
	public ModelAndView		cartView(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�뙆�씪硫뷀꽣 諛쏄퀬
		String	cate = req.getParameter("cate");
		
		String	sessionID = session.getId();
		
		//	�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 爰쇰궡�꽌
		ArrayList	list = cDao.selectCartList(sessionID);
		
		//	酉곗뿉寃� �븣�젮以��떎.
		mv.addObject("LIST", list);
		mv.addObject("CATE", cate);
		mv.setViewName("Shopping/CartView");
		return mv;
	}
	/*
	 * 	�옣諛붽뎄�땲 �닔�웾 �닔�젙 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/Shopping/CartCountUpdate")
	public ModelAndView	cartCountUpdate(GoodsCartData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�뙆�씪硫뷀꽣 諛쏅뒗�떎.
		String	sessionID = session.getId();
		
		//	�뜲�씠�꽣踰좎씠�뒪瑜� �닔�젙�븳�떎.
		//	癒쇱� �뜲�씠�꽣 鍮� �겢�옒�뒪�뿉 �닔�젙�쓣 �쐞�븳 �뜲�씠�꽣瑜� 以�鍮꾪븳�떎.
		//	�긽�뭹踰덊샇, �닔�웾�� �씠誘� �겢�씪�씠�뼵�듃媛� 蹂대궡二쇱뼱�꽌 媛�吏�怨� �엳�떎.
		//	�늻媛�(sessionID)�뒗 �븘吏� �뾾�떎.
		data.setId(sessionID);
		
		cDao.updateCartCount(data);
		
		//	酉곕�� 遺�瑜몃떎.
		RedirectView	rv = new RedirectView("../Shopping/CartView.do");
		rv.addStaticAttribute("cate", data.getCate());
		mv.setView(rv);
		return mv;
	}
	/*
	 * 	移댄듃 �궘�젣 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/Shopping/CartDelete")
	public ModelAndView		cartDelete(GoodsCartData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�븷�씪
		//		�뙆�씪硫뷀꽣 諛쏅뒗�떎
		String	sessionID = session.getId();
		
		//		�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 �궘�젣�븳�떎.
		data.setId(sessionID);
		cDao.deleteCart(data);
		
		//		酉곕�� 遺�瑜몃떎.
		RedirectView		rv = new RedirectView("../Shopping/CartView.do");
		rv.addStaticAttribute("cate", data.getCate());
		mv.setView(rv);
		return mv;
	}
}





