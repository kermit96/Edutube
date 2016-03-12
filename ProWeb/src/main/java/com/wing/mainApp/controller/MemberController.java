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
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wing.mainApp.dao.MemberDAO;
import com.wing.mainApp.data.*;
import com.wing.mainApp.util.StringUtil;
import com.wing.mainApp.util.util;

@Controller
public class MemberController {

	@Autowired
	MemberDAO dao;
   @RequestMapping("/member/login")
   ModelAndView Login(HttpServletRequest req, HttpServletResponse res) 
   {
	   ModelAndView mv = new ModelAndView();

	   // login 호출
	   String returl = req.getParameter("returnurl");
	   
	   mv.setViewName("/Member/login");
	   mv.addObject("returnurl", returl);
	   
	   return mv;
	     
   }
   
@RequestMapping("/member/logout")
ModelAndView   logout(HttpSession ses) {
	  ses.invalidate();
	  return new ModelAndView("redirect:/index.jsp");
}

@RequestMapping("/member/ajax/samenickname")
void CheckNickName(HttpServletRequest req, HttpServletResponse res)
{
	res.setCharacterEncoding("UTF-8");
  	String nickname = req.getParameter("nickname");

	 boolean ret = true;
	 
	 if (dao.isSamenick(nickname) == 0) {
		 
       ret = false;
	 } 
	  
	JsonObject json =new  JsonObject();
      
	json.addProperty("result",ret); 
	
	  PrintWriter write;
	   try {
		     write =  res.getWriter();	   
	    	 write.print(json.toString()); 		   
		     write.flush(); 		     	   
	   } catch (Exception ex) { 		   
		   ex.printStackTrace();		   
	   }	
	
}



@RequestMapping("/member/ajax/sameuserid")
void CheckUserid(HttpServletRequest req, HttpServletResponse res)
{
	   res.setCharacterEncoding("UTF-8");
	String userid = req.getParameter("userid");
	
	 String str = "사용할수 있는 ID 입니다.";
	 
	 boolean ret = true;
	 
	 
	 
	 System.out.println("userid="+userid);
	 if (dao.isSameuserid(userid) == 0) {
		 
       ret = false;
	 } 
	  
	JsonObject json =new  JsonObject();
      
	json.addProperty("result",ret); 
	
	  PrintWriter write;
	   try {
		     write =  res.getWriter();	   
	    	 write.print(json.toString()); 		   
		     write.flush(); 		     	   
	   } catch (Exception ex) { 		   
		   ex.printStackTrace();		   
	   }	
	
}

   //  사용자 id 를 찾는다.
   @RequestMapping("/member/ajax/findid")
   void findId(HttpServletRequest req, HttpServletResponse res)
  {	   
	   res.setCharacterEncoding("UTF-8");
	   String name=req.getParameter("name");
	   String email=req.getParameter("email");
	   
	   String id  = dao.findid(name, email);
	   	   	   	   
	   String str = "";
	   
	   if (StringUtil.isNull(id)) {
		   str = "id 가 없습니다. ";		   				   
	   } else {		   
		   str = "id는 " +id +" 입니다";
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


   // 사용자 password 를 찾는다.
   //  parameter 는 userid ,name,email 이다.
   //  e-mail 로  password 를 전달함
   @RequestMapping("/member/ajax/findpassword")
   void findpassword(HttpServletRequest req, HttpServletResponse res)
  {	   
	   res.setCharacterEncoding("UTF-8");
	   String userid=req.getParameter("userid");
	   String name=req.getParameter("name");
	   String email=req.getParameter("email");
	   
	   System.out.println("userid"+userid);
	   
	   if (StringUtil.isNull(userid)) {		   		   
		   return;
	   }

	   if (StringUtil.isNull(name)) {   		   
		   return;
	   }

	   if (StringUtil.isNull(email)) {   		   
		   return;
	   }
	
	   
	   String str = "";
	   
	   int random = (int)(Math.random()*10000);
	   String password = Integer.toBinaryString(random);
	   
	   String sha256password = util.GetSha256(password);
	   
	   int count  = dao.changepassword(userid,name,email,sha256password);	  
	  
	   if (count== 0) {
		  str = "이름이나  e-mail 이 잘못 되었습니다";    
		   
	   } else {
    	  str = "password 를  e-mail 로 전달 했습니다."  ;
    	  String body = name +" 님의 비밀 번호는  "+password + " 입니다";
    	   try {
   	         util.SendMail(email, "Edutube 비밀번호 알림", body);
    	   } catch(Exception ex) {
    		   ex.printStackTrace();
    		   str = ex.toString();
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
	   vm.setViewName("/Member/joinmember");	   
	   return vm;
	   
   }
   
   @RequestMapping("/member/joinmemberprocess")
   ModelAndView joinMemberProcess(Member member, HttpServletResponse res)
  {
	   ModelAndView vm = new ModelAndView();
	   
	   System.out.println(member.getMem_addrCode());
	   System.out.println(member.getMem_id());
	   dao.joinmember(member);

	   vm.setViewName("/Member/resultjoin");	   
	   return vm;
   }
   

   @RequestMapping("/member/findmember")
   ModelAndView joinMemberProcess(HttpServletResponse res)
  {
	   ModelAndView vm = new ModelAndView();
	   
       
	   
	   vm.setViewName("/Member/findmember");	   
	   return vm;
   }
   
   
   
   
   @RequestMapping("/member/changeuserinfo")
   ModelAndView updateMember(HttpServletRequest req, HttpServletResponse res, HttpSession ses )
  {	   
	   ModelAndView vm = new ModelAndView();
	   
	   String userid = (String)ses.getAttribute("ID");
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
	   
        String userid = (String)session.getAttribute("ID");
        
 	    if (userid == null)
		   return;

   	   	String oldpassword = req.getParameter("oldpassword");
   	    String newpassword = req.getParameter("newpassword");
   	
   	    dao.changepassword(userid, oldpassword,newpassword);
   	     
   	   res.setCharacterEncoding("UTF-8");
	    
 	   PrintWriter write;
 	   try {
 		     write =  res.getWriter();	   
 	    	 write.print("암호를 수정했습니다."); 		   
 		     write.flush(); 		     	   
 	   } catch (Exception ex) { 		   
 		   ex.printStackTrace();		   
 	   }	
   	     
   }

   
   @RequestMapping("/member/ajax/updatemember")
   void updateMember(Member member, HttpServletResponse res,HttpSession session)
   { 	   
	   String userid = (String)session.getAttribute("ID");
	   
	   if (userid == null)
		   return;

	   member.setMem_id(userid);
 	   dao.updatemember(member);
 	   res.setCharacterEncoding("UTF-8");
 	    
 	   PrintWriter write;
 	   try {
 		     write =  res.getWriter();	   
 	    	 write.print("사용자 정보를 수정했습니다."); 		   
 		     write.flush(); 		     	   
 	   } catch (Exception ex) { 		   
 		   ex.printStackTrace();		   
 	   }	
 	   
   }
  
   
   @RequestMapping("/member/ajax/login")
   void LoginProcess(HttpServletRequest req, HttpServletResponse res,HttpSession session) throws Exception 
   {
	   	   
	   // login 호춫 
	   String userid = req.getParameter("userid");
	   String password = req.getParameter("password");
	 
	   res.setCharacterEncoding("UTF-8");
	   
	   PrintWriter write =  res.getWriter();	   

	   Member member = dao.isLogin(userid, password);

	   LoginResulttData data= new LoginResulttData();
	   
	   if (member == null ) {
		       data.isSuccess = false;
	           data.failreason = "id 가 없거나 password 가 틀렸습니다.";
	   }
	   else { 		   
		   data.isSuccess = true;		   
		   session.setAttribute("ID", member.getMem_id());
		   session.setAttribute("NAME", member.getMem_name());
		   session.setAttribute("NICKNAME", member.getMem_nick());
		   session.setAttribute("NAL", member.getMem_Nal());
		   session.setAttribute("TEL", member.getMem_tel());
	   }
	   	          
  	  Gson gson = new Gson();

       String jsonstr = gson.toJson(data);
       write.print(jsonstr);		   
       write.flush();
   }
}