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
	
    public DownloadUtil() {
        setContentType("applicaiton/download;charset=EUC-KR");
    }
    
    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	//===========
    	String	oriName = (String)model.get("oirname");
    	//===========
    	File file = (File) model.get("downloadFile");
        response.setContentType(getContentType());
        response.setContentLength((int)file.length());

//        String fileName = java.net.URLEncoder.encode(file.getName(), "UTF-8");
      //===========
        String fileName = java.net.URLEncoder.encode(oriName, "UTF-8");
      //===========
       
        response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
   
        response.setHeader("Content-Transfer-Encoding", "binary");
    
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
