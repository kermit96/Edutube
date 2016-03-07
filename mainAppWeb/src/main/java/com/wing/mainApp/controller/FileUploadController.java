package com.wing.mainApp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import 	org.springframework.stereotype.Controller;
import 	org.springframework.web.bind.annotation.RequestMapping;
import 	org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.wing.mainApp.dao.FileUploadDAO;
import com.wing.mainApp.data.FileUploadData;
import com.wing.mainApp.util.FileUtil;

@Controller
public class FileUploadController {

	@Autowired
	FileUploadDAO		fDao;
	
	@RequestMapping("/FileUpload/SingleForm")
	public ModelAndView	SingleForm() {
		ModelAndView		mv = new ModelAndView();
		
		mv.setViewName("FileUpload/SingleForm");
		return mv;
	}
	
	
	/*
	@RequestMapping("/FileUpload/SingleUpload")
	public ModelAndView	SingleUpload(FileUploadData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		
		String	path = "D:\\FileUpload";
		//							�꽌踰� �쐞移�		�쎒�뿉�꽌 �궗�슜�븷 �닔 �엳�떎.
		//											(�긽�뭹 �씠誘몄�瑜� �뾽濡쒕뱶�븯怨� �긽�뭹 �씠誘몄�瑜� �쎒�뿉�꽌 蹂댁뿬二쇨퀬 �떢�쑝硫�...)
//		String path = session.getServletContext().getRealPath("images");
		
		String	filename = data.upfile.getOriginalFilename();
		filename = FileUtil.rename(path, filename);//�뙆�씪 �씠由꾩씠 �룞�씪�븷 寃쎌슦 �뙆�씪 �씠由꾩쓣 蹂�寃쏀븿
		
		//		getOriginalFilename();		�뾽濡쒕뱶�맂 �뙆�씪 �씠由꾩쓣 援ы븳�떎.
		//		getSize();					�뾽濡쒕뱶�맂 �뙆�씪�쓽 �겕湲곕�� 援ы븳�떎.
		//		isEmpty();					�뾽濡쒕뱶�맂 �뙆�씪�쓽 議댁옱 �뿬遺�瑜� 援ы븳�떎.
		//	3.	�씠 �몢媛�吏� �젙蹂대�� �씠�슜�빐�꽌 File �겢�옒�뒪瑜� 留뚮뱺�떎.
		File		file = new File(path, filename);
		try {
			//	4.	�썝�븯�뒗 �쐞移섎줈 �뙆�씪�쓣 ���옣�븳�떎.
			data.upfile.transferTo(file);
		}
		catch(Exception e) {
			System.out.println("�뙆�씪 �뾽濡쒕뱶 �떎�뙣 " + e);
		}
		//	5.	�뾽濡쒕뱶�맂 �뙆�씪�쓣 �뜲�씠�꽣踰좎씠�뒪�뿉 洹� �젙蹂대�� 湲곗뼲�븳�떎.
		
		RedirectView	rv = new RedirectView("../FileUpload/SingleForm.dol");
		mv.setView(rv);
		return mv;
	}
	*/
	/*
	 * 	�씪諛� �뙆�씪 �뾽濡쒕뱶 �슂泥�
	 */
	@RequestMapping("/FileUpload/SingleUpload")
	public ModelAndView	SingleUpload(FileUploadData data, HttpSession session) {
		ModelAndView		mv = new ModelAndView();
		//	�쟾�넚�릺�뼱�삩 �뙆�씪硫뷀꽣 諛쏄퀬(�엫�떆 硫붾え由ъ뿉 ���옣�씠 �릺�뿀�떎.)
		//	臾몄젣�뒗 �븘吏� �궗�슜�옄媛� �썝�븯�뒗 �쐞移섏뿉 �뙆�씪�씠 ���옣�릺吏��뒗 �븡�븯�떎.
		//	�떒�젏	�궗�슜�옄媛� 吏곸젒 �엫�떆 硫붾え由ъ뿉 ���옣�맂 �뙆�씪�쓣
		//			�썝�븯�뒗 �쐞移섏뿉 �꽔�뼱二쇱뼱�빞 �븳�떎.
		//	1.	���옣�븷 �쐞移섎�� �젙�븳�떎.
		//		���옣�븷 �쐞移섎뒗		�씪諛� �쐞移�		�뙆�씪 �떎�슫濡쒕뱶留� 媛��뒫�븯怨�
		String	path = "D:\\FileUpload";
		//							�꽌踰� �쐞移�		�쎒�뿉�꽌 �궗�슜�븷 �닔 �엳�떎.
		//											(�긽�뭹 �씠誘몄�瑜� �뾽濡쒕뱶�븯怨� �긽�뭹 �씠誘몄�瑜� �쎒�뿉�꽌 蹂댁뿬二쇨퀬 �떢�쑝硫�...)
//		String path = session.getServletContext().getRealPath("images");
		//	2.	���옣�븷 �뙆�씪�쓽 �씠由꾩쓣 援ы븳�떎.
		//		�씠 �씠由꾩� 留덉쓬��濡� �젙�븯硫� �맂�떎.
		//		�븯吏�留� ��遺�遺� �뾽濡쒕뱶�맂 �뙆�씪 �씠由꾩쓣 洹몃�濡� �궗�슜�븯湲곕�� �썝�븯誘�濡�.......
		String		oriName = "";
		String		saveName = "";
		long		length = 0;
		if(!data.upfile.isEmpty()) {
			oriName 	= data.upfile.getOriginalFilename();
			saveName = FileUtil.rename(path, oriName);
			length = data.upfile.getSize();
		//	李멸퀬
		//		getOriginalFilename();		�뾽濡쒕뱶�맂 �뙆�씪 �씠由꾩쓣 援ы븳�떎.
		//		getSize();					�뾽濡쒕뱶�맂 �뙆�씪�쓽 �겕湲곕�� 援ы븳�떎.
		//		isEmpty();					�뾽濡쒕뱶�맂 �뙆�씪�쓽 議댁옱 �뿬遺�瑜� 援ы븳�떎.
		//	3.	�씠 �몢媛�吏� �젙蹂대�� �씠�슜�빐�꽌 File �겢�옒�뒪瑜� 留뚮뱺�떎.
			File		file = new File(path, saveName);
			try {
				//	4.	�썝�븯�뒗 �쐞移섎줈 �뙆�씪�쓣 ���옣�븳�떎.
				data.upfile.transferTo(file);
			}
			catch(Exception e) {
				System.out.println("�뙆�씪 �뾽濡쒕뱶 �떎�뙣 " + e);
			}
		}
		//	5.	�뾽濡쒕뱶�맂 �뙆�씪�쓣 �뜲�씠�꽣踰좎씠�뒪�뿉 洹� �젙蹂대�� 湲곗뼲�븳�떎.
		data.len = length;
		data.oriname = oriName;
		data.savename = saveName;
		data.path = path;
		fDao.insertBoard(data);
		//	酉곕�� 遺�瑜몃떎.
		RedirectView	rv = new RedirectView("../FileUpload/SingleForm.do");
		mv.setView(rv);
		return mv;
	}
	/*
	 * 	�떎以� �뙆�씪 �뾽濡쒕뱶 �뤌 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/FileUpload/MultiForm")
	public ModelAndView	MultiForm() {
		ModelAndView		mv = new ModelAndView();
		
		mv.setViewName("FileUpload/MultiForm");
		return mv;
	}
	/*
	 * 	�떎以� �뙆�씪 �뾽濡쒕뱶 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/FileUpload/MultiUpload")
	public ModelAndView	MultiUpload(FileUploadData data) {
		ModelAndView		mv = new ModelAndView();
		//	�꽆�뼱�삩 �뙆�씪�쓣 �썝�븯�뒗 �쐞移섏뿉 �삷湲대떎.
		String	path = "D:\\FileUpload";
		for(int i = 0; i < data.files.length; i++) {
			//	留뚯빟 �뙆�씪�씠 泥⑤��릺吏� �븡�븯�쑝硫� �뙆�씪�쓣 �삷湲곕뒗 �옉�뾽�쓣 �븯吏� 留먯븘�빞 �븳�떎.
			if(data.files[i].isEmpty()) {
				//	洹� 泥⑤��뙆�씪�� 議댁옱�븯吏� �븡�땲?
				continue;
			}
			//	�븯�굹�뵫 �뙆�씪�쓽 �씠由꾩쓣 援ы븯�옄.
			String	filename = data.files[i].getOriginalFilename();
			filename = FileUtil.rename(path, filename);
			File		file = new File(path, filename);
			try {
				data.files[i].transferTo(file);
			}
			catch(Exception e) {
				System.out.println("�뙆�씪 �뾽濡쒕뱶 �뿉�윭 = " + e);
			}
		}
		
		RedirectView	rv = new RedirectView("../FileUpload/MultiForm.do");
		mv.setView(rv);
		return mv;
	}
	/*
	 * 紐⑸줉 蹂닿린 �슂泥� 泥섎━ �븿�닔
	 * 
	 */
	@RequestMapping("/FileUpload/BoardList")
	public ModelAndView boardList(){
		ModelAndView mv = new ModelAndView();
		//�븷�씪
		//�뜲�씠�꽣 踰좎씠�뒪�뿉 �엳�뒗紐⑸줉 爰쇰궡�꽌 酉곗뿉寃� �븣�젮以��떎.
		ArrayList list = fDao.selectBoard();
		//酉곗뿉寃� �븣�젮以��떎.
		mv.addObject("LIST",list);
		mv.setViewName("FileUpload/BoardList");
		return mv;
	}
	/*
	 * 	�뙆�씪 �떎�슫濡쒕뱶 �슂泥� 泥섎━ �븿�닔
	 */
	@RequestMapping("/FileUpload/FileDownload")
	public ModelAndView		fileDownload(HttpServletRequest req) {
		ModelAndView		mv = null;
		//	�븷�씪
		//		�꽆�뼱�삩 �뙆�씪硫뷀꽣 諛쏅뒗�떎
		String	strNo = req.getParameter("oriNo");
		int		oriNo = Integer.parseInt(strNo);
		//�떎�슫濡쒕뱶 �슏�닔瑜� 利앷��떆�궓�떎.
		fDao.updateDownHit(oriNo);
		//�젙蹂대�� 爰쇰궦�떎.
		FileUploadData	data = fDao.selectDownInfo(oriNo);
		//		�뙆�씪 �떎�슫濡쒕뱶 酉곕�� 遺�瑜몃떎.
		//	�뙆�씪 �떎�슫濡쒕뱶 酉곗뿉寃� �떎�슫濡쒕뱶�븷 �젙蹂대뒗
		//	諛섎뱶�떆 Map�쑝濡� 留뚮뱾�뼱�꽌 �꽆寃⑥＜湲곕줈 �빟�냽�뻽�떎.
		Map	map = new HashMap();
		map.put("oriname", data.oriname);
		File	file = new File("D:\\FileUpload", data.savename);
		map.put("downloadFile",file);
		
		//�뙆�씪 �떎�슫濡쒕뱶 酉곕�� 遺�瑜몃떎.
		//�떎�슫濡쒕뱶�쓽 酉곗씠由꾩� DI濡� �벑濡앸맂 id媛믪씠�떎.
		mv = new ModelAndView("download",map);
		return mv;
	}
}








