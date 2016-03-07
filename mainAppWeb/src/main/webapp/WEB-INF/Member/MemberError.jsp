<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#aBtn").click(function(){
					$frm = $("#lfrm");
					$frm.attr("action","../Member/MemberForm.dol");
					//alert("테스트");
					$frm.submit();
				});
			});
		</script>
	</head>
<body>
<c:if test="${not empty sessionScope.ID}">
	<table width="50" border="1" align="center">
		<tr>
			<td>${sessionScope.NICK}님 환영합니다.</td>
		</tr>
		<tr>
			<td align="center"><input type="button" value="로그아웃" id="oBtn"></td>
			<td	align="center"><input type="button" value="메인으로" id="mBtn"></td>
		</tr>
	</table>	
</c:if>
<c:if test="${empty sessionScope.ID}">
<!-- 로그인을 하지 않았으면 .. 로그인 폼을 만들어 주고	 -->
<form method="POST" name="lfrm" id="lfrm"  name="lfrm">
	<table width="50" border="1" align="center">
		<tr>
			<td>회원 가입을 해 주세요.</td>
		</tr>
		<tr>
			<td>비밀 번호가 틀립니다.</td>
		</tr>
		<tr>
			<td align="center"><input type="button" value="회원 가입하기" id="aBtn"></td>
		</tr>
	</table>	
</form>
</c:if>
</body>
	
</html>