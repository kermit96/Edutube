package com.wing.mainApp.controller;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class email {
		String recipient = "rewg3@naver.com";
    public void emailsend() throws MessagingException{
        // 메일 관련 정보
        String host = "smtp.naver.com";
        final String username = "rewg92";
        final String password = "qudwndla1";
        int port=587;
         
        // 메일 내용
        String subject = "강사 등록이 완료되었습니다.";
        String body = "축하드립니다 강사등록이 완료되었습니다. 좋은 정보 많이 올려주세요 :)";
         
        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", "rewg92@naver.com");
        props.put("mail.smtp.auth", "true");
         
        /*props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);*/
          
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=username;
            String pw=password;
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
        session.setDebug(true); //for debug
          
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress("rewg92@naver.com"));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(body);
        Transport.send(mimeMessage);
        
        System.out.println("ddsdfsa");
    }
}