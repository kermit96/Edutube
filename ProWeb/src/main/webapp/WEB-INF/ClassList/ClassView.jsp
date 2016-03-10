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
			$("#lBtn").click(function(){
				$(location).attr("href", "../ClassList/ClassList.do?nowPage=${NOWPAGE}");
			})
		})
		$(document).ready(function(){
		$("#dBtn").click(function(){
			$("#mfrm").attr("action", "../ClassList/ClassDelete.do");
				$("#mfrm").submit();
			})
		})
		$(document).ready(function(){
		$("#mBtn").click(function(){
			$("#mfrm").attr("action", "../ClassList/ClassModifyForm.do?oriNo=${DATA.no}");
			$("#mfrm").submit();
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
<form method="POST" id="mfrm">
	<table width="800" border="1" align="center">
		<tr>
			<th>번호</th>
			<td align="center">${DATA.no}</td>
			<th>강의코드</th>
			<td align="center">${DATA.code}</td>
			<th>작 성 자</th>
			<td align="center">${sessionScope.NICKNAME}</td>
			<th>작 성 일</th>
			<td align="center">${DATA.cdate}</td>
			<th>조 회 수</th>
			<td align="center">${DATA.hit}</td>
			<th>추 천 수</th>
			<td align="center">${DATA.good}</td>
		</tr>
		<tr>
			<th colspan="12">제     목</th>
		</tr>
		<tr>
			<td colspan="12">${DATA.title}</td>	
		</tr>
		<tr>
			<th colspan="12">본     문</th>
		</tr>
		<tr>
			<td colspan="12">${DATA.body}</td>
		</tr>
	</table>
	<br>
	<table width="800" align="center">
		<tr>
			<td><input type="button" value="목록" id="lBtn"></td>
			<td><input type="button" value="수정" id="mBtn"></td>
			<td><input type="button" value="삭제" id="dBtn"></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>