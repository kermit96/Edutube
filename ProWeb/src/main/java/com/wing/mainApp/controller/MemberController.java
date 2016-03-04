package com.wing.mainApp.controller;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.wing.mainApp.dao.MemberDao;
import com.wing.mainApp.data.*;
import com.wing.mainApp.util.StringUtil;
import com.wing.mainApp.util.LoginUtil;

@Controller
public class MemberController {

	@Autowired
	MemberDao dao;LoginUtil LoUtil;
	
   @RequestMapping("/member/login")
   ModelAndView Login(HttpServletRequest req, HttpServletResponse res) 
   {
	   ModelAndView mv = new ModelAndView();

	   // login È£­ˆ 
	   String returl = req.getParameter("returnurl");
	   
	   mv.setViewName("/Member/login");
	   mv.addObject("returnurl", returl);
	   
	   return mv;
	     
   }
   

   
   //  »ç¿ëÀÚ id ¸¦ Ã£´Â´Ù.
   @RequestMapping("/member/ajax/findid")
   void findId(HttpServletRequest req, HttpServletResponse res)
  {	   
	   res.setCharacterEncoding("UTF-8");
	   String name=req.getParameter("name");
	   String email=req.getParameter("email");
	   
	   String id  = dao.findid(name, email);
	   
	   String str = "";
	   if (StringUtil.isNull(id)) {
		   
		   str = "id´Â " +id +" ÀÔ´Ï´Ù";				   
	   } else {
		   str = "id °¡ ¾ø½À´Ï´Ù. ";
	   }
 	   PrintWriter write;
 	   try {
 		     write =  res.getWriter();	   
 	    	 write.print(str); 		   
 		     write.flush(); 		     	   
 	   } catch (Exception ex) { 		   
 		   ex.printStackTrace();		   
 	   }	
	   
   }


   // »ç¿ëÀÚ password ¸¦ Ã£´Â´Ù.
   //  parameter ´Â userid ,name,email ÀÌ´Ù.
   //  e-mail ·Î  password ¸¦ Àü´ÞÇÔ
   @RequestMapping("/member/ajax/findpassword")
   void findpassword(HttpServletRequest req, HttpServletResponse res)
  {	   
	   res.setCharacterEncoding("UTF-8");
	   String userid=req.getParameter("userid");
	   String name=req.getParameter("name");
	   String email=req.getParameter("email");
	   
	   if (StringUtil.isNull(userid)) {		   		   
		   return;
	   }

	   if (StringUtil.isNull(name)) {   		   
		   return;
	   }

	   if (StringUtil.isNull(email)) {   		   
		   return;
	   }

	   String id  = dao.findid(name, email);
	   
	   String str = "";
	   
	   int random = (int)Math.random()*100000;
	   String password = Integer.toBinaryString(random);
	   
	   String sha256password = LoginUtil.GetSha256(password);
	   
	  int count  = dao.changepassword(userid,name,email,sha256password);
	   	      
	   if (count== 0) {
		  str = "ÀÌ¸§ÀÌ³ª  e-mail ÀÌ Àß¸ø µÇ¾ú½À´Ï´Ù";    
		   
	   } else {
    	  str = "password ¸¦  e-mail ·Î Àü´Þ Çß½À´Ï´Ù."  ;
    	  String body = name +" ´×ÀÇ ºñ¹Ð ¹øÈ£´Â  "+password + " ÀÔ´Ï´Ù";
    	   try {
    		   LoginUtil.SendMail(email, "Edutube ºñ¹Ð¹øÈ£ ¾Ë¸²", body);
    	   } catch(Exception ex) {
    		   ex.printStackTrace();    		   
    	   }
   	   
	   }

 	   PrintWriter write;
 	   try {
 		     write =  res.getWriter();	   
 	    	 write.print(str); 		   
 		     write.flush(); 		     	   
 	   } catch (Exception ex) { 		   
 		   ex.printStackTrace();		   
 	   }	
	   
   }



   @RequestMapping("/member/joinmember")
   ModelAndView joinMember(HttpServletRequest req, HttpServletResponse res)
  {
	   
	   ModelAndView vm = new ModelAndView();
	   vm.setViewName("/member/joinmember");	   
	   return vm;
	   
   }

   
   @RequestMapping("/member/joinmemberprocess")
   ModelAndView joinMemberProcess(Member member, HttpServletResponse res)
  {
	   ModelAndView vm = new ModelAndView();
	   
	   dao.joinmember(member);

	   vm.setViewName("/Member/resultjoin");	   
	   return vm;
   }
   
   
   
   @RequestMapping("/member/changeuserinfo")
   ModelAndView updateMember(HttpServletRequest req, HttpServletResponse res, HttpSession ses )
  {	   
	   ModelAndView vm = new ModelAndView();
	   
	   String userid = (String)ses.getAttribute("id");
	   if (userid == null) 
		   return null;
	   
	   Member member = dao.getMember(userid);
	   
	   vm.addObject("member", member);
	         
	   vm.setViewName("/Member/changemember");	   
	   return vm;	   
   }
   
   
   @RequestMapping("/member/ajax/changepassword")
   void changepassword(HttpServletRequest req, HttpServletResponse res,HttpSession session)
  {	   
        String userid = (String)session.getAttribute("id");
   	   	String oldpassword = req.getParameter("oldpassword");
   	    String newpassword = req.getParameter("newpassword");
   	
   	     dao.changepassword(userid, oldpassword,newpassword);   	    
   }

   
   @RequestMapping("/member/ajax/updatemember")
   void updateMember(Member member, HttpServletResponse res)
   { 	   

 	   dao.updatemember(member);
 	   res.setCharacterEncoding("UTF-8");
 	    
 	   PrintWriter write;
 	   try {
 		     write =  res.getWriter();	   
 	    	 write.print("»ç¿ëÀÚ Á¤º¸¸¦ ¼öÁ¤Çß½À´Ï´Ù."); 		   
 		     write.flush(); 		     	   
 	   } catch (Exception ex) { 		   
 		   ex.printStackTrace();		   
 	   }	
 	   
   }
  
   
   @RequestMapping("/member/ajax/login")
   void LoginProcess(HttpServletRequest req, HttpServletResponse res,HttpSession session) throws Exception 
   {
	   	   
	   // login È£­ˆ 
	   String userid = req.getParameter("userid");
	   String password = req.getParameter("password");
	 
	   res.setCharacterEncoding("UTF-8");
	   
	   PrintWriter write =  res.getWriter();	   
	   Member member = dao.isLogin(userid, password);

	   /*LoginResulttData data= new LoginResulttData();
	   
	   if (member == null ) {
		       data.isSuccess = false;
	           data.failreason = "id °¡ ¾ø°Å³ª password °¡ Æ²·È½À´Ï´Ù.";
	   }
	   else { 
		   data.isSuccess = true;
		   session.setAttribute("id", member.getMem_id());
		   session.setAttribute("name", member.getMem_name());
		   session.setAttribute("nickname", member.getMem_nick());
	   }
	   	          
  	    Gson gson = new Gson();

       String jsonstr = gson.toJson(data);
       write.print(jsonstr);		   
       write.flush();*/
   }
}