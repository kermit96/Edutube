<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<!--  검색바 -->
	
	<!--JS-->
	 <script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 	<script src="/edutube/resources/JS/bootstrap.min.js"></script>
 	
	<!--스크립트-->
	<script>
	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;
									
		}
				
	</style>
	<style>
		#Table{
			background-color: MistyRose;<!--Joon -->
		}
	</style>	
		<script type="text/javascript" src="../se2/js/HuskyEZCreator.js" charset="utf-8">
		</script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					
					//	이제 무결성 검사하고...
									
					$("#frm").attr("action", "../Notice/NoticeReg.do");
					$("#frm").submit();
				});
				$("#lBtn").click(function(){
					//	먼저 스킨에 내용을 textarea로 옮긴다.
					//oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	이제 무결성 검사하고...
										
					$("#frm").attr("action", "../Notice/NoticeList.do");
					$("#frm").submit();
				});
			});
		</script>
	</head>
	<body>
	<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
		<p><h3 align="center" >공지 사항 등록</h3></p>
		<form method="POST" id="frm" action="" enctype="multipart/form-data">
			<table width="80%" border="1" align="center" id='Table'>
				<tr>
					<td>글쓴이</td>
					<td colspan="2"><input type="text" name="mem_id" id="writer" value="${sessionScope.loginId}" disabled>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="2"><input type="text" name="notice_title" id="title">
				</tr>
				<tr>
				    <td>공지 글</td>
					<td colspan="3">
						<textarea id="body" name="notice_body" style="width:100%; height:200px"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" value="글쓰기" id="wBtn">
						<input type="button" value="목록" id="lBtn">
					</td>
				</tr>
			</table>		
		</form>
		</div>
	</div>
	</body>
</html>
