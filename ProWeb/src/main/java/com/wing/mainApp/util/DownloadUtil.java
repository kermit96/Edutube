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
	//	이 클래스를 new 시키는 순간 응답 방식(contentType)을 다운로드 방식으로 설정한다.
    public DownloadUtil() {
        setContentType("applicaiton/download;charset=EUC-KR");
    }
    
    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//	컨트롤러에서 알려주는 다운로드 파일을 받는다.
    	//	이 파일 다운로드 뷰의 문제점
    	//		클라이언트가 올린 파일의 이름이 아니고 저장된 이름으로 다운로드해준다.
    	//	만약 클라이언트가 올린 파일의 이름으로 다운로드 해주고자 하면......
    	//===========
    	String	oriName = (String)model.get("oirname");
    	//===========
    	File file = (File) model.get("downloadFile");
        response.setContentType(getContentType());
        response.setContentLength((int)file.length());
        //	혹시 파일의 이름이 한글로 되어 있는 경우 다운로드 시키면
        //	한글 파일 이름이 깨지는 현상이 생긴다.
        //	한글 파일 이름을 깨지지 않도록 Encoding 시킨다.
//        String fileName = java.net.URLEncoder.encode(file.getName(), "UTF-8");
      //===========
        String fileName = java.net.URLEncoder.encode(oriName, "UTF-8");
      //===========
        //	파일 다운로드를 할때 파일에 헤더 부분을 등록한다.
        response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
        //	이 헤더가 다운로드할 파일의 이름을 등록하는 부분이다.
        response.setHeader("Content-Transfer-Encoding", "binary");
        //	스트림 방식으로 파일을 클라이언트에게 전송한다.	
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
            if (fis != null) { 
            	try { 
            		fis.close(); 
            	} 
            	catch (Exception e2) {}
            }
        }
        out.flush();
    }
}
