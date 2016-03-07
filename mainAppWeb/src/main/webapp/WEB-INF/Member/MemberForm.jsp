<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<SCRIPT language="javascript" SRC="../include/script.js"></SCRIPT>
		<title>회원 가입</title>
		<script>
		function SendData() {
			
			var	UserName = document.getElementById("UserName").value;
			if(UserName == "") {
				alert("이름을 입력해라");
				document.getElementById("UserName").focus();
				return;
			}
			var	id = document.getElementById("UserId").value;
			if(id == "") {
				alert("Id를 입력해라");
				document.getElementById("UserId").focus();
				return;
			}
			var	pw = document.getElementById("UserPass").value;
			if(pw == "") {
				alert("PassWord를  입력해라");
				document.getElementById("UserPass").focus();
				return;
			}
			var	repw = document.getElementById("ReUserPass").value;
			if(repw == "") {
				alert("PassWord를 재 입력해라");
				document.getElementById("ReUserPass").focus();
				return;
			}
			else{
				if(!(document.getElementById("UserPass").value==document.getElementById("ReUserPass").value)){
					alert("입력된 패스워드가 서로 다릅니다.");
					form.ReUserPass.focus();
					return ;
				}
			}
			var	email = document.getElementById("email").value;
			var regex=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
			if(email == "") {
				alert("email를  입력해라");
				document.getElementById("email").focus();
				return;
			}
			else if((regex.test(email) === false)){
			 	alert("잘못된 이메일 형식입니다.");
			 	return;
			}
			
			var	zip = document.getElementById("ZipCode1").value;
			if(zip == ""){
				alert("우편 번호를  입력해라");
				document.getElementById("ZipCode1").focus();
				return;
			}
			var	zip1 = document.getElementById("ZipCode2").value;
			if(zip1 == ""){
			 	alert("우편 번호를  입력해라");
			 	document.getElementById("ZipCode2").focus();
			 	return;
			}
			var	add1 = document.getElementById("UserAddress1").value;
			if(add1 == ""){
			 	alert("주소를  입력해라");
			 	document.getElementById("UserAddress1").focus();
			 	return;
			}			
			var	add = document.getElementById("UserAddress2").value;
			if(add == ""){
			 	alert("주소를  입력해라");
			 	document.getElementById("UserAddress2").focus();
			 	return;
			}
			/*
			if(!(hp1.selectedIndex==0)){
				alert("휴대폰의 통신사를 선택하세요");
				form.UserHp1.focus();
				return;
			}
			*/
			var	first = document.getElementById("UserHp2").value;
			if(!first){
				alert("휴대폰 번호 앞자리를 입력하세요");
				document.getElementById("UserHp2").focus();
				return ;
			}
			var	second = document.getElementById("UserHp3").value;
			if(!second){
				alert("휴대폰 번호 뒷자리를 입력하세요.");
				document.getElementById("UserHp3").focus();
				return;
			}
	
			var	frm = document.getElementById("frm");
			alert("회원 가입 처리 해야");
			frm.action = "../Member/MemberProc.dol";
			frm.submit();
		}
		function Reset() {
			//	폼전체에 입력한 내용을 초기화 시키는 방법
			//	형식>		폼.reset();
			var	frm = document.getElementById("frm");
			frm.reset();
		}
			
		
		</SCRIPT>
	</head>
<body>
	<Table WIDTH=620 HEIGHT=40 BORDER=0 CELLSPACING=1 CELLPADDING=1 ALIGN=CENTER>
		<TR BGCOLOR=#A0A0A0>
			<TD ALIGN=CENTER><FONT SIZE=4><B>회원가입</B></FONT></TD>
		</TR>
	</Table>
	
	<form method="post" id="frm" name="frm">
		<Table WIDTH=620 BORDER=1 CELLSPACING=0 CELLPADDING=2 ALIGN=CENTER>
		<TR>
			<TD WIDTH=120 ALIGN=CENTER><B>이름</B></TD>
			<TD WIDTH=500>
				<INPUT type=text name="name" id ="UserName" size=20 maxlength=20 style="ime-mode:active">
			</TD>
		</TR>
		
		<TR>
			<TD WIDTH=120 align=CENTER><B>아이디</B></TD>
			<TD WIDTH=500>
				<INPUT type=text name="id" id ="UserId" size=20 maxlength=10 style="ime-mode:active">
				<IMG src="../images/bt_confirm.gif" border=0 align=absmiddle style=cursor:hand>
			</TD>
		</TR>	
		
		<TR>
			<TD WIDTH=120 align=CENTER><B>패스워드</B></TD>
			<TD WIDTH=500>
				<INPUT type=password name="password" Id= "UserPass"size=20 maxlength=10 >
			</TD>
		</TR>
		
		<TR>
			<TD WIDTH=120 align=CENTER><B>패스워드 재입력</B></TD>
			<TD WIDTH=500>
				<INPUT type=password name="repassword" Id="ReUserPass" size=20 maxlength=10 >
			</TD>
		</TR>
		
		<TR>
			<TD WIDTH=120 align=CENTER><B>e-mail address</B></TD>
			<TD WIDTH=500>
<!-- 			<INPUT type= name="UserMail" size=60 maxlength=90 style="ime-mode:inactive"> -->	
				<INPUT type=email name="email" Id= "email" size=60 maxlength=90 style="ime-mode:inactive">
			</TD>
		</TR>
		
		<TR>
			<TD WIDTH=120 align=CENTER><B>주 소</B></TD>
			<TD WIDTH=500>
				<INPUT type=text name="ZipCode1" Id = "ZipCode1" size=3 maxlength=3>
				<INPUT type=text name="ZipCode2" Id = "ZipCode2" size=3 maxlength=3>
				<INPUT type=text name="UserAddress1" Id = "UserAddress1" size=45 maxlength=110>
				<IMG src="../images/bt_confirm.gif" border=0 align=absmiddle style=cursor:hand><BR>&nbsp;
				나머지 주소
				<INPUT type=text name="address" Id = "UserAddress2" size=45 maxlength=110>
			</TD>
		</TR>
				
		<TR>
			<TD WIDTH=120 align=CENTER><B>휴대전화</B></TD>
			<TD WIDTH=500>
				<Select name="UserHp1" size=1>
					<option value="0" selected>통신사
					<option value="010">010
					<option value="011">011
				</Select>
				<INPUT type=text name="UserHp2" Id ="UserHp2" size=4 maxlength=4>
				<INPUT type=text name="phone" Id ="UserHp3" size=4  maxlength=4>
			</TD>
		</TR>
		
		</Table>
	
	</FORM>
	<Table width=620 height=50 border=0 cellspacing=10 cellpadding=10 align=CENTER>
		<TR ALIGN=CENTER>
			<TD>
				<button type="button"  onclick="JavaScript:SendData()">가입하기</button>&nbsp;&nbsp;
				<button type="button">취소하기</button>
			</TD>	
		</TR>	
	</Table>
</body>
</html>