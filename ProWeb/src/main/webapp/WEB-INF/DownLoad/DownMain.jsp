<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>자료실</title>
		<!--CSS-->
		<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
		<!--  파피콘 넣기 -->
		<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<!--  검색바 -->
		
		<!--JS-->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 		<script src="/edutube/resources/JS/bootstrap.min.js"></script>
		<script>
		function goDetail(orino){
			$(location).attr("href","../DownLoad/DownView.do?nowPage=${PINFO.nowPage}&oriNo="+orino);
		}
			$(document).ready(function(){
				$("#lBtn").click(function(){
					$(location).attr("href","../../edutube");
				});
			});
			 $(document).ready(function(){
				$("#wBtn").click(function(){
					$(location).attr("href", "./DownWrite.do");
				});
			}); 
		</script>
			<style>
	
			#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}	
		#asd{
		
		}
	</style>
	</head>
	<body>
		<div id='EduContainer'>
			<div id='top'>	
				<jsp:include page="/MenuBar/Top.jsp" flush="false" />
			</div>
			<div>
				<h1>자료실 입니다</h1>
			</div>
			<div id="main">
			<table width="80%" border="1" align="center">
					<tr>
						<th width="10%">번호</th>
						<th width="45%">제목</th>
						<th width="15%">글쓴이</th>
						<th width="15%">날짜</th>
						<th width="15%">첨부파일</th>
					</tr>
				<c:forEach var="temp" items="${LIST}">
					<tr align="center">
						<td>${temp.no}</td>
						<td>
							<a href="JavaScript:goDetail(${temp.no})">${temp.title}</a>
						</td>
						<td>${temp.id}</td>
						<td>${temp.wdate}</td>
						<td align="center">
	<c:if test="${temp.len ne 0}">
				<a href="../DownLoad/FileDownLoad.do?oriNo=${temp.no}"><img src="/resources/img/down.gif"></a>	
	</c:if>			
				</td>
					</tr>
				</c:forEach>
					<tr>
						<td colspan="6" align="center"><input type="button" value="write" id="wBtn"><input type="button" value="back" id="lBtn"></td>	
					</tr>
				</table>	
				<div>
				<!-- sideBar -->
				</div>
				<div id="asd">
				
				</div>
			</div>
		</div>
		
	</body>

</html>