package com.wing.mainApp.controller;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.wing.mainApp.util.util;
 
public class email {
	
	 public String recipient = "rewg3@naver.com";
	 
    public void emailsend() throws Exception{
        // 메일 관련 정보
        
        // 메일 내용
        String subject = "강사 등록이 완료되었습니다.";
        String body = "축하드립니다 강사등록이 완료되었습니다. 좋은 정보 많이 올려주세요 :)";
         
    
        
        util.SendMail(recipient,subject,body);

        System.out.println("ddsdfsa");
    }
    public void emailno() throws Exception{
    	 // 메일 관련 정보
        String host = "smtp.naver.com";
        final String username = "rewg92";
        final String password = "qudwndla1";
        int port=587;
         
        // 메일 내용
        String subject = "강사 등록이 거절되었습니다.";
        String body = "죄송합니다 강사등록이 거절되었습니다. <br> 이유가 몇가지 있습니다."
        		+ "	<br> 1. 자격이 부족합니다 "
        		+ " <br> => 다시한번 자신이 강사로써 적합한지 확인해주세요"
        		+ " <br> 2. 인원이 너무 많습니다."
        		+ " <br> => 현재 강사인원이 너무 많습니다. 다른분야를 지원해주세요"
        		+ " <br> 3. 경력이 너무 짧습니다."
        		+ " <br> => 가르칠 수 있는 능력을 배양해주세요"
        		+ " <br> 4. 신고를 당한적이 있습니다."
        		+ " <br> => 누군가 당신을 신고한 기록이 있습니다. 확인해주세요."
        		+ " <br> 위의 사유가 적합하지 않거나 해당하는 사항이 없는 경우 다시한번 신청해주시길 바랍니다.";
       
        util.SendMail(recipient,subject,body);
    }
}