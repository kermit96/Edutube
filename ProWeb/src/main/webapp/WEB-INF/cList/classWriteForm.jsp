<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
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
	$(document).ready(function(){
		$("#wBtn").click(function(){
			$("#wfrm").attr("action", "../cList/classWrite.do");
			$("#wfrm").submit();
		})
	})
	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
		#nav {
    	line-height:20px;
    	float:left;
    	padding:5px;	      
		}
		tr > th {
			text-align:center;
		}
	</style>
</head>
<body>
<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>
	<form method="POST" id="wfrm">
	<table width="800" border="1" align="center">
		<tr>
			<th>강의번호</th>
			<td><input type="text" ></td>
		</tr>
		<tr>
			<th>강의코드</th>
			<td><input type="text" disabled></td>
		</tr>
		<tr>
			<th>제    목</th>
			<td><input type="text" name="title" id="title"></td>
		</tr>
		<tr>
			<th>본　  문</th>
			<td><textarea name="body" id="body"></textarea></td>
		</tr>
		<tr>
			<th>작 성 자</th>
			<td><input type="text" disabled></td>
		</tr>
		<tr>
			<th>작 성 일</th>
			<td><input type="text" disabled></td>
		</tr>
		<tr>
			<th>추 천 수</th>
			<td><input type="text" disabled></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="글작성" id="wBtn">
			</td>
		</tr>
	</table>
</div>
</form>
</body>
</html>