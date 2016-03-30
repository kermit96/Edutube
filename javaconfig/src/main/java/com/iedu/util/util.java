package com.iedu.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.iedu.config.Globalconfig;

public class util {
	
	   public static  String  GetSha512(String str) {

			return GetSha(str,"SHA-512");			   
	   }
	    
	 
	   public static  void  SendMail(String to,String subject,String body ) throws Exception	   
	   {
		   MailSendInfo info = new MailSendInfo();
		   info.to = to;
		   info.subject = subject;
		   info.body = body;
		    
		   Globalconfig config = new Globalconfig();
		   info.smtpserver = config.getSmtphost();
		   info.from  = config.getSmtpemail();
		   info.fromname = config.getSmtpemail();
		   info.userid = config.getSmtpuserid();
		   info.password = config.getSmtppassword();
		   info.port = config.getSmtpport();
		   
		   switch(config.getEncryptedmethod())
		   {
		     // tls 
		     case 1:
		       info.istls = true;
		       break;
		   
		     case 2:
			       info.isssl = true;
			       break;
			   		   
		   }
		   
		   SendMail(info);
		   
	   }
	   
	     	  
	  public static  void  SendMail( MailSendInfo info ) throws Exception
	     { 
		  
		  
	    	 String to = info.to;

	         // Sender's email ID needs to be mentioned
	         String from = info.from;
	         String username = info.userid;
	         String password = info.password;

	      
	         // Assuming you are sending email through relay.jangosmtp.net
	         String host = info.smtpserver;

	         Properties props = new Properties();
	         
	         boolean isauth = true;
	         if (username.isEmpty())
	        	 isauth = false;
	         
	         props.put("mail.smtp.auth", isauth);
	         if (info.istls == true)
	           props.put("mail.smtp.starttls.enable", info.istls);
	         if (info.isssl == true)
	           props.put("mail.smtp.ssl.enable", info.isssl);
	         
	         props.put("mail.smtp.host", host);
	         props.put("mail.smtp.port", info.port);
	 

	         // Get the Session object.
	         Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	         
	            // Create a default MimeMessage object.
	            Message message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from,info.fromname,"utf-8"));

	            // Set To: header field of the header.
	            message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	            // Set Subject: header field
	            message.setSubject(info.subject);

	            // Now set the actual message
	            message.setText(info.body);
	            
	            

	            message.setHeader("Content-Type", "text/html; charset=\"" + "utf-8" + "\"");

	            // Send message
	            Transport.send(message);

	     }

	   
	   
	   public static String GetSha(String str,String shatype ) 
	   {
		   String SHA = ""; 

			try{

//				MessageDigest sh = MessageDigest.getInstance("SHA-512"); 
				MessageDigest sh = MessageDigest.getInstance(shatype);
				
				sh.update(str.getBytes()); 

				byte byteData[] = sh.digest();

				StringBuffer sb = new StringBuffer(); 

				for(int i = 0 ; i < byteData.length ; i++){

					sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));

				}
				SHA = sb.toString();				

			}catch(NoSuchAlgorithmException e){

				e.printStackTrace(); 

				SHA = null; 

			}

			return SHA;

		   
		   
	   }
	   
	   
	   
	   public static boolean IsMyIp(String ip) throws SocketException
	   {

		   Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();

	        while (nienum.hasMoreElements()) {

	            NetworkInterface ni = nienum.nextElement();

	            Enumeration<InetAddress> kk = ni.getInetAddresses();

	            while (kk.hasMoreElements()) {

	    			InetAddress inetAddress = (InetAddress) kk.nextElement();

	    			if (inetAddress.getHostAddress().equals(ip) )
	    				return true;	    				    		
	    		}

	        }

	        return false;
		   
	   }
	   
	   
	   public static void main(String[] args) throws SocketException{

		   /*
			Enumeration<NetworkInterface> nienum = NetworkInterface.getNetworkInterfaces();

		        while (nienum.hasMoreElements()) {
		            NetworkInterface ni = nienum.nextElement();
		            Enumeration<InetAddress> kk = ni.getInetAddresses();		            

		            while (kk.hasMoreElements()) {

		    			InetAddress inetAddress = (InetAddress) kk.nextElement();

		    			System.out.println(inetAddress.getHostName()+" : "+inetAddress.getHostAddress());

		    		}

		        }
		        */
		   		   
		        System.out.println("is my ip check=="+IsMyIp("127.0.0.1"));
		        		        
		       	 

		        
		}
	   
	   
   public static  String  GetSha256(String str) {
			
			return GetSha(str,"SHA-256");
							   
   }
   
    
   
}
