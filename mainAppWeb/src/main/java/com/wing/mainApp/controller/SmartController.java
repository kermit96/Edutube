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
	//	
	@Autowired
	SmartBoardDAO	sDao;
	
	
	
	/*
	 * 	
	 */
	@RequestMapping("/Smart/BoardWriteForm")
	public ModelAndView	boardWriteForm(HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	
		//		
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		
		mv.setViewName("Smart/BoardWriteForm");
		return mv;
	}
	
	/*
	 * 
	 */
	@RequestMapping("/Smart/BoardList")
	public ModelAndView	 boardList(HttpServletRequest req,HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		if(!SessionUtil.isSession(session)) {
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		String	strPage = req.getParameter("nowPage");
		int		nowPage = 0;
		//	
		if(StringUtil.isNull(strPage)) {
			nowPage = 1;
		}
		else {
			nowPage = Integer.parseInt(strPage);
		}
		//	
		int	total = sDao.selectTotal();
		PageUtil	pInfo = new PageUtil(nowPage, total, 5, 5);
		pInfo.calcInfo();
		//	
		//		
		//									start		end
		//		nowPage	1	1 ~ 		5
		//					2	6 ~ 		10
		
		int	start = (pInfo.nowPage - 1) * pInfo.pageList + 1;
		int	end = start + pInfo.pageList - 1;
		//	
		//	
		if(end > pInfo.totalCount) {
			end = pInfo.totalCount;
		}
		
		HashMap	map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		
		ArrayList	list = sDao.selectList(map);
		
		//	
		mv.addObject("PINFO", pInfo);
		mv.addObject("LIST", list);
		mv.setViewName("Smart/BoardList");
		return mv;
	}
	
	/*
	 * 	
	 */
	@RequestMapping("/Smart/BoardWrite")
	public ModelAndView	boardWrite(HttpSession session, SmartBoardData data) {
		ModelAndView		mv = new ModelAndView();
		//	
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		//				
		//	
		if(StringUtil.isNull(data.title) || StringUtil.isNull(data.body) || StringUtil.isNull(data.pw)) {
			RedirectView	rv = new RedirectView("../Smart/BoardWriteForm.do");
			mv.setView(rv);
			return mv;
		}
		//				
		//				
		//				
		String	path = "D:\\FileUpload";
		//	
		//	
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		//	
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
					//	
					oriname[count] = data.upfile[i].getOriginalFilename();
					savename[count] = FileUtil.rename(path, oriname[count]);
					len[count] = data.upfile[i].getSize();
					try {
						File	file = new File(path, savename[count]);
						data.upfile[i].transferTo(file);
					}
					catch(Exception e) {
						System.out.println("�뾽濡쒕뱶 �떎�뙣 " + e);
					}
					count = count + 1;
				}
			}
		}
		//				
		//				1.	
		//	
		String	id = (String) session.getAttribute("loginId");//Joon
		data.id = id;
		
		int	no = sDao.selectMax(); 
		data.no = no;
		
		sDao.insertBoard(data);
		
		if(count != 0) {
			//		2.	
			//	
			SmartBoardData	temp = new SmartBoardData();
			//	
			temp.no = no;
			temp.path = path;
			for(int i = 0; i < count; i++) {
				temp.oriname = oriname[i];
				temp.savename = savename[i];
				temp.len = len[i];
				sDao.insertFile(temp);
			}
		}
		
		//				
		RedirectView	rv = new RedirectView("../Smart/BoardList.do");
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	
	 */
	@RequestMapping("/Smart/BoardView")
	public ModelAndView boardView(HttpSession session, SmartBoardData data) {
//	public String boardView(Model model) {
		//	
		//	
		//			req.setAttribute()
		//		model.addObject("", "");
		ModelAndView		mv = new ModelAndView();
		//	
		if(!SessionUtil.isSession(session)) {
			//	
			RedirectView	rv = new RedirectView("../Member/member_form.do");
			mv.setView(rv);
			return mv;
		}
		
		//	
		//		
		
		//	
		//		1.	
		SmartBoardData	rData = sDao.selectView(data.oriNo);
		//		2.	
		ArrayList			fList = sDao.selectFile(data.oriNo);
		//		3.	
		HashMap			map = sDao.selectPreNext(data.oriNo);
		
		//	
		mv.addObject("VIEW", rData);
		mv.addObject("FILES", fList);
		mv.addObject("MAP", map);
		//	
		//	
		//	
		mv.addObject("NOWPAGE", data.nowPage);
		mv.addObject("KIND", data.kind);
		mv.setViewName("Smart/BoardView");
		return mv;
	}
	
	/*	
	 * 
	 */
	@RequestMapping("/Smart/BoardHit")
	public ModelAndView boardHit(SmartBoardData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	
		
		//	
		//	
		String	id = (String) session.getAttribute("loginId");//Joon
		//	
		String	preHit = sDao.selectHit(id);
		boolean	isHit = false;			//	
		HashMap	map = new HashMap();
		if(StringUtil.isNull(preHit)) {
			//	�씠 �궗�엺�� 泥섏쓬 寃뚯떆�뙋�뿉 �뱾�뼱�삩 �궗�엺�씠�떎.
			isHit = true;
			//	�씠 �궗�엺�씠 吏�湲� 蹂� 湲�踰덊샇瑜� �깉濡�寃� 留뚮뱾�뼱以��떎.
			map.put("ID", id);
			map.put("HIT", ":" + data.oriNo + ":");
			sDao.insertHit(map);
		}
		else {
			//	�씠 �궗�엺�� �븳踰� �씠�긽 寃뚯떆�뙋�쓣 �궗�슜�뻽�뜕 �궗�엺�씠�떎.
			//	�씠 �궗�엺�씠 �씠 湲��쓣 遊ㅻ뒗吏�瑜� 寃��궗�븯�옄.
			int		isShow = preHit.indexOf(":" + data.oriNo + ":");
			if(isShow == -1) {
				//	洹� 湲�踰덊샇�뒗 �뾾�떎.
				isHit = true;
				//	�씠�젣 �떎�쓬�뿉�뒗 利앷��븯吏� 紐삵븯寃� �씠 踰덊샇瑜� �닔�젙�빐 �넃�뒗�떎.
				map.put("ID", id);
				map.put("HIT", preHit + ":" + data.oriNo + ":");
				sDao.updateHit(map);
			}
			else {
				//	洹� 湲��� 遊ㅻ뒗�뜲�슂
				isHit = false;
			}
		}
		
		//	議고쉶�닔瑜� 利앷��븷 �븘�슂媛� �엳�쑝硫� 利앷��븳�떎.
		if(isHit == true) {
			sDao.updateBoardHit(data.oriNo);
		}
		
		RedirectView		rv = new RedirectView("../Smart/BoardView.do");
		rv.addStaticAttribute("nowPage", data.nowPage);
		rv.addStaticAttribute("kind", data.kind);
		rv.addStaticAttribute("oriNo", data.oriNo);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 	�뙆�씪 �떎�슫濡쒕뱶 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/Smart/BoardDown")
	public ModelAndView		boardDown(HttpServletRequest req) {
		ModelAndView		mv = null;
		//	�듅�닔酉곕�� 遺덈윭���빞 �븯誘�濡� new �떆�궎�뒗 諛⑹떇�씠 �떎瑜대떎.
		//	�븷�씪
		//		沅뚰븳�꽕�젙
		//		�뙆�씪硫뷀꽣 諛쏄린
		String	strNo = req.getParameter("no");
		//		�뙆�씪硫뷀꽣媛� �샊�떆 �뾾�뒗 寃쎌슦 泥섎━瑜� �빐以��떎.
		int		no = Integer.parseInt(strNo);
		
		//	�떎�슫濡쒕뱶 �쉶�닔瑜� 利앷��떆耳쒕낫�옄
		//	利앷� 諛⑹떇�� �꽭�뀡�뿉 愿�怨꾩뾾�씠 臾댁“嫄� 利앷�諛⑹떇�쑝濡� �븳�떎.
		//	�닕�젣
		
		
		//		�뙆�씪 �젙蹂� �뼸湲�
		HashMap	rMap = sDao.selectFileInfo(no);
		
		//		�듅�닔酉� 遺�瑜닿린(�듅�닔酉곌� �븣�븘�꽌 �떎�슫濡쒕뱶 �빐以��떎.)
		//		�듅�닔酉곕뒗 �궗�슜�옄媛� 留뚮뱾�뼱 �궗�슜�븯�뒗 媛쒕뀗�씠�떎.
		//		�슦由щ뒗		�썝�옒�씠由�			String		oirname
		//					�떎�슫濡쒕뱶�븷 �뙆�씪	File			downloadFile
		HashMap	map = new HashMap();
		map.put("oirname", rMap.get("ORINAME"));
		
		File			file = new File((String)rMap.get("PATH"), 
												(String)rMap.get("SAVENAME"));
		map.put("downloadFile", file);
		
		mv = new ModelAndView("download", map);
		//	酉곗씠由꾩�	xml �뙆�씪�뿉 �벑濡앺븳 �듅�닔酉곗쓽 id媛믪씠 �맂�떎.
		return mv;
	}
	
	/*
	 * 	�닔�젙�뤌 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/Smart/BoardModifyForm")
	public ModelAndView	boardModifyForm(SmartBoardData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�븷�씪
		//		沅뚰븳�꽕�젙
		//		�뙆�씪硫뷀꽣 諛쏅뒗�떎.
		
		//		鍮꾨쾲�씠 媛숈�吏� �솗�씤�븳�떎.
		String	id = (String) session.getAttribute("ID");
		
		SmartBoardData rData = sDao.selectPwInfo(data.oriNo);
		if(rData.id.equals(id) && rData.pw.equals(data.pw)) {
			//	�닔�젙�븷 �닔 �엳�떎.
			//		�썝�븯�뒗 酉곕�� 遺덈윭二쇨퀬
			//	�닔�젙�븯湲� �쐞�븳 �젙蹂�(�궡�슜�젙蹂�, �뙆�씪�젙蹂�)瑜� 爰쇰궡�꽌 �븣�젮以��떎.
			SmartBoardData 	dData = sDao.selectView(data.oriNo);
			ArrayList			fList = sDao.selectFile(data.oriNo);
			
			//	由대젅�씠瑜� �쐞�븳 �뙆�씪硫뷀넗�뒗 �꽆寃⑥＜怨�
			mv.addObject("NOWPAGE", data.nowPage);
			mv.addObject("KIND", data.kind);
			mv.addObject("ORINO", data.oriNo);
			mv.addObject("DATA", dData);
			mv.addObject("FILES", fList);
			mv.setViewName("Smart/BoardModifyForm");
			return mv;
		}
		else {
			//	�닔�젙 沅뚰븳�씠 �뾾�떎.
			//		�긽�꽭蹂닿린濡� 蹂대궦�떎.
			RedirectView	rv = new RedirectView("../Smart/BoardView.do");
			rv.addStaticAttribute("nowPage", data.nowPage);
			rv.addStaticAttribute("kind", data.kind);
			rv.addStaticAttribute("oriNo", data.oriNo);
			mv.setView(rv);
			return mv;
		}
	}
	
	/*
	 * 	�뙆�씪 �궘�젣 �슂泥�
	 */
	@RequestMapping("/Smart/BoardFileDelete")
	public ModelAndView	boardFileDelete(@RequestParam("no") int no) {
		ModelAndView		mv = new ModelAndView();
		//	�븷�씪
		//		沅뚰븳�젏嫄�
		//		�뙆�씪硫뷀꽣
		//		�궘�젣�븯湲� �쟾�뿉 癒쇱� �젙蹂대�� �븣�븘�궦�떎.
		//		�굹以묒뿉 �씠 �젙蹂닿� �엳�뼱�꽌 �꽌踰꾩뿉 �엳�뒗 �뙆�씪�쓣 �궘�젣�븷 �닔 �엳湲� �븣臾몄씠�떎.
		HashMap	data = sDao.selectFileInfo(no);
		
		//		�뜲�씠�꽣踰좎씠�뒪�뿉�꽌 �궘�젣
		sDao.fileDelete(no);
		
		//		�떎�젣 �꽌踰꾩뿉 �엳�뒗 �뙆�씪�룄 �궘�젣
		//		�씪諛� java API瑜� �씠�슜�빐�꽌 �궘�젣�븳�떎.
		File		file = new File((String)data.get("PATH"), (String)data.get("SAVENAME"));
		file.delete();
		
		//		酉곕�� 遺�瑜몃떎.
		mv.setViewName("Smart/BoardFileDelete");
		return mv;
	}
	
	/*
	 * 	�닔�젙�븯湲� �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/Smart/BoardModify")
	public ModelAndView		boardModify(SmartBoardData data) {
		ModelAndView		mv = new ModelAndView();
		//	�븷�씪
		//		沅뚰븳寃��궗
		//		�뙆�씪硫뷀꽣 諛쏄린(寃��궗源뚯� �룷�븿)
		//		泥⑤��뙆�씪�씠 議댁옱�븯硫� �뙆�씪�쓣 �꽌踰꾩뿉 ���옣�븯湲�
		//	泥⑤��뙆�씪�쓽 �젙蹂대�� �굹以묒뿉 �뜲�씠�꽣踰좎씠�뒪�뿉 ���옣�빐�빞 �븯誘�濡�.....
		//	洹� �젙蹂대�� 湲곕줉�빐 �넃�옄
		
		//	諛곗뿴�쓣 紐뉕컻瑜� 留뚮뱾�뼱�빞 �븷吏� 紐⑤Ⅴ誘�濡�.....
		String[]	oriname = null;
		String[]	savename = null;
		long[]		len = null;
		int			count = 0;			//	泥⑤��뙆�씪�쓽 媛쒖닔瑜� 湲곗뼲�븷 蹂��닔 
		String	path = "D:\\FileUpload";
		if(data.upfile != null && data.upfile.length != 0) {
			//	癒쇱� 媛곴컖�쓽 諛곗뿴�쓣 留뚮뱾�옄
			oriname = new String[data.upfile.length];
			savename = new String[data.upfile.length];
			len = new long[data.upfile.length];
			
			for(int i = 0; i < data.upfile.length; i++) {
				if(data.upfile[i].isEmpty()) {
					continue;
				}
				//	�젙蹂� �븣�븘�궡怨�
				//		count瑜� �궗�슜�븯�뒗 �씠�쑀�뒗 �떎�젣濡� �삱�씪�삩 媛쒖닔�뒗 �뤌�쓽 媛쒖닔�� �떎瑜� �닔 �엳�떎.
				oriname[count] = data.upfile[i].getOriginalFilename();
				savename[count] = FileUtil.rename(path, oriname[count]);
				len[count] = data.upfile[i].getSize();
				//	�꽌踰꾩뿉 ���옣�븯�옄
				try {
					File		file = new File(path, savename[count]);
					data.upfile[i].transferTo(file);
				}
				catch(Exception e) {
					
				}
				count++;
			}
		}
		
		//		�뜲�씠�꽣踰좎씠�뒪�뿉 �궡�슜 �닔�젙�븯湲�
		sDao.updateBoard(data);
		//		泥⑤��뙆�씪�씠 議댁옱�븯硫� �뙆�씪�쓽 �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉 湲곕줉�븯湲�
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
		//		酉곕�� 遺�瑜닿린
		RedirectView	rv = new RedirectView("../Smart/BoardView.do");
		rv.addStaticAttribute("nowPage", data.nowPage);
		rv.addStaticAttribute("kind", data.kind);
		rv.addStaticAttribute("oriNo", data.oriNo);
		mv.setView(rv);
		return mv;
	}
	
	/*
	 * 寃��깋�븯湲� �슂泥�
	 */
	@RequestMapping("/Smart/BoardSearch")
	public ModelAndView boardSearch(HttpServletRequest req, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�뙆�씪硫뷀꽣
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
		//	臾몄젣(議댁옱�븯吏� �븡�쑝硫� 泥섎━�빐 �넃�븘�빞 �븳�떎.)
		//		�럹�씠吏� �씠�룞湲곕뒫�쓣 �씠�슜�븯寃� �릺硫� kind, content媛� �븞�꽆�뼱�삩�떎.
		//		臾쇰줎 由대젅�씠 諛⑹떇�쑝濡� �꽆寃⑤룄 �릺吏�留�......
		//		�씠�쑀
		//			GET 諛⑹떇�쑝濡� 泥섎━�븯�뒗 寃쎌슦�뒗 �븳湲��� �맆 �닔 �엳�쑝硫�
		//			�궗�슜�븯吏� �븡�뒗�떎.
		//	留뚯빟 議댁옱�븯吏� �븡�쑝硫� �꽭�뀡�뿉�꽌 諛쏆븘�꽌 �궗�슜�븯�룄濡� �븳�떎.
		if(StringUtil.isNull(kind)) {
			kind = 		(String)session.getAttribute("kind");
			content = 	(String)session.getAttribute("content");
		}
		//	�샊�떆 吏�湲� �꽆�뼱�삩 �뜲�씠�꽣瑜� �떎�쓬�뿉�뒗 �꽭�뀡�뿉�꽌 李얠쓣 �븘�슂媛� �깮湲� 寃껋씠誘�濡�
		//	�꽭�뀡�뿉 ���옣�빐 �넃�뒗�떎.
		session.setAttribute("kind", kind);
		session.setAttribute("content", content);
		
		//	�꽭�뀡�뿉�뒗 紐⑤뱺 �삎�깭�쓽 �젙蹂대�� 蹂닿��븷 �닔 �엳�떎.
		//	�삁瑜� �뱾�뼱�뼱�꽌 �꽭�뀡�뿉 蹂닿��븷 �뜲�씠�꽣媛� 留롮쑝硫�.....
//		HashMap map = new HashMap();
//		map.put("kind", kind);
//		map.put("content", content);
//		session.setAttribute("SEARCH", map);
		
//		HashMap	temp = (HashMap) session.getAttribute("SEARCH");
		
		//	�럹�씠吏� �굹�닎�쓣 留뚮뱾湲� �쐞�븳 �옉�뾽�� �깮�왂�뻽�쑝誘�濡� �닕�젣濡� �빐蹂몃떎.
		
		//	寃��깋�븯湲�
		HashMap map = new HashMap();
		map.put("kind", kind);
		map.put("CONTENT", content);
		ArrayList	list = sDao.selectSearch(map);
		//	�럹�씠吏��뿉 �븘�슂�븳 �뜲�씠�꽣留� 怨⑤씪�궡�뒗 �옉�뾽�씠 �븘�슂�븯怨�.....
		
		//	酉� 遺�瑜닿린
		mv.addObject("LIST", list);
		mv.setViewName("Smart/BoardSearch");
		return mv;
	}
}






