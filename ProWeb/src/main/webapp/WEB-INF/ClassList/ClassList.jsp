<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	function Detail(orino) {
		$(location).attr("href", "../ClassList/ClassView.do?nowPage=${PINFO.nowPage}&oriNo="+ orino + "&flag=L");
	}
	$(document).ready(function(){
		$("#wBtn").click(function(){
			$(location).attr("href", "../ClassList/ClassWriteForm.do");
		});
	});
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

</head>
<body>
<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>
	<table width="800" border="1" align="center">
		<tr>
			<th width="5%">번호</th>
			<th width="10%">강의코드</th>
			<th width="20%">작 성 자</th>
			<th width="30%">제     목</th>
			<th width="10%">작 성 일</th>	
			<th width="10%">추 천 수</th>
			<th width="10%">조 회 수</th>
			<th width="15%">언     어</th>
		</tr>
		<c:forEach var="temp" items="${LIST}">
			<tr>
				<td>${temp.no}</td>
				<td>${temp.code}</td>
				<td>${temp.nick}</td>
				<td>
				<a href="JavaScript:Detail(${temp.no})" >${temp.title}</a>
				</td>
				<td>${temp.cdate}</td>
				<td>${temp.good}</td>
				<td>${temp.hit}</td>
				<td>${temp.lang}</td>
			</tr>
		</c:forEach>
		<!-- <tr>
			<td colspan="8" align="center">
				<input type="button" value="글작성" id="wBtn">
			</td>
		</tr> -->
		<tr>
			<td colspan="8" align="center">
				<select id="kind" name="kind">
					<option value="title">제목</option>
					<option value="body">내용</option>
					<option value="writer">글쓴이</option>
					<option value="both">제목+내용</option>
				</select>
				<input type="text" id="content" name="content">
				<input type="button" value="검색" id="sBtn">
			</td>
		</tr>
	</table>
	<center><input type="button" value="글작성" id="wBtn"></center>
	<!-- 	페이지 이동 기능 -->
	<table align="center" width="800">
		<tr>
			<td align="center">
				<a href="../ClassList/ClassList.do?nowpage=1">[◀◀]</a>
				<c:if test="${pInfo.startPage eq 1}">
					[◀]
				</c:if>
				<c:if test="${pInfo.startPage ne 1}">
					<a href="../ClassList/ClassList.do?nowpage=${pInfo.startPage - 1}">[◀]</a>
				</c:if>
				<c:forEach var="temp" begin="${pInfo.startPage}" end="${pInfo.endPage}">
					<a href="../ClassList/ClassList.do?nowpage=${temp}">[ ${temp} ]</a>
				</c:forEach>
				<c:if test="${pInfo.endPage eq pInfo.pageNum}">
					[▶]
				</c:if>
				<c:if test="${pInfo.endPage ne pInfo.pageNum}">
					<a href="../ClassList/ClassList.do?nowpage=${pInfo.endPage + 1}">[▶]</a>
				</c:if>
				<a href="../ClassList/ClassList.do?nowpage=${pInfo.pageNum}">[▶▶]</a>
			</td>
		</tr>
	</table>
</div>
</body>
</html>