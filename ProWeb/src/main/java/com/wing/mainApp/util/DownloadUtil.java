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
	//	�� Ŭ������ new ��Ű�� ���� ���� ���(contentType)�� �ٿ�ε� ������� �����Ѵ�.
    public DownloadUtil() {
        setContentType("applicaiton/download;charset=EUC-KR");
    }
    
    @Override
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//	��Ʈ�ѷ����� �˷��ִ� �ٿ�ε� ������ �޴´�.
    	//	�� ���� �ٿ�ε� ���� ������
    	//		Ŭ���̾�Ʈ�� �ø� ������ �̸��� �ƴϰ� ����� �̸����� �ٿ�ε����ش�.
    	//	���� Ŭ���̾�Ʈ�� �ø� ������ �̸����� �ٿ�ε� ���ְ��� �ϸ�......
    	//===========
    	String	oriName = (String)model.get("oirname");
    	//===========
    	File file = (File) model.get("downloadFile");
        response.setContentType(getContentType());
        response.setContentLength((int)file.length());
        //	Ȥ�� ������ �̸��� �ѱ۷� �Ǿ� �ִ� ��� �ٿ�ε� ��Ű��
        //	�ѱ� ���� �̸��� ������ ������ �����.
        //	�ѱ� ���� �̸��� ������ �ʵ��� Encoding ��Ų��.
//        String fileName = java.net.URLEncoder.encode(file.getName(), "UTF-8");
      //===========
        String fileName = java.net.URLEncoder.encode(oriName, "UTF-8");
      //===========
        //	���� �ٿ�ε带 �Ҷ� ���Ͽ� ��� �κ��� ����Ѵ�.
        response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
        //	�� ����� �ٿ�ε��� ������ �̸��� ����ϴ� �κ��̴�.
        response.setHeader("Content-Transfer-Encoding", "binary");
        //	��Ʈ�� ������� ������ Ŭ���̾�Ʈ���� �����Ѵ�.	
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
