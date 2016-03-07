package com.wing.mainApp.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadUtil extends AbstractView {
	//�씠 �겢�옒�뒪瑜� new �떆�궎�뒗 �닚媛� �쓳�떟 諛⑹떇(contentType)�쓣 �떎�슫濡쒕뱶 諛⑹떇�쑝濡� �꽕�젙�븳�떎.
	public DownloadUtil() {
        setContentType("applicaiton/download;charset=utf-8");
    }
    
    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //而⑦듃濡ㅻ윭�뿉�꽌 �븣�젮二쇰뒗 �떎�슫濡쒕뱶 �뙆�씪�쓣 諛쏅뒗�떎.
    	//�씠 �뙆�씪 �떎�슫濡쒕뱶 酉곗쓽 臾몄젣�젏
    	// �겢�씪�씠�뼵�듃媛� �삱由� �뙆�씪�쓽 �씠由꾩씠 �븘�땲怨� ���옣�맂 �씠由꾩쑝濡� �떎�슫濡쒕뱶�빐�빞 �븳�떎.
    	//留뚯빟 �겢�씪�씠�뼵�듃媛� �삱由� �뙆�씪�쓽 �씠由꾩쑝濡� �떎�슫濡쒕뱶 �빐二쇨퀬�옄 �븯硫�...
    	String oriName = (String)model.get("oriname");
    	File file = (File) model.get("downloadFile");
        response.setContentType(getContentType());
        response.setContentLength((int)file.length());
        //�샊�떆 �뙆�씪�쓽 �씠由꾩씠 �븳湲�濡� �릺�뼱 �엳�뒗 寃쎌슦 �떎�슫濡쒕뱶 �떆�궎硫� 
        //�븳湲� �뙆�씪 �씠由꾩씠 源⑥��뒗 �쁽�긽�씠 �깮湲대떎.
        //�븳湲� �뙆�씪 �씠由꾩쓣 源⑥�吏� �븡�룄濡� Encoding �떆�궓�떎.
        //String fileName = java.net.URLEncoder.encode(file.getName(), "UTF-8");
        String fileName = java.net.URLEncoder.encode(oriName, "UTF-8");
        //�뙆�씪 �떎�슫濡쒕뱶瑜� �븷 �븣 �뙆�씪�뿉 �뿤�뜑 遺�遺꾩쓣 �벑濡앺븳�떎.
        response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
        //諛붾줈 �쐞�뿉�꽌 �뿭�뒳�옒�뒪 �벖 �씠�쑀�뒗 媛뺤젣濡� �벑濡앺빐�떖�씪�뒗 �쓽誘몄엫
        //�쐞�쓽 �뿤�뱶媛� �떎�슫濡쒕뱶�븷 �뙆�씪�쓽 �씠由꾩쓣 �벑濡앺븯�씪�뒗 
        response.setHeader("Content-Transfer-Encoding", "binary");
        //�뒪�듃由� 諛⑹떇�쑝濡� �뙆�씪�쓣 �겢�씪�씠�뼵�듃�뿉寃� �쟾�넚�븳�떎.
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } 
		catch (Exception e) {
            e.printStackTrace();
        } 
		finally {
            if (fis != null) { try { fis.close(); } catch (Exception e2) {}}
        }
        out.flush();
    }
}

