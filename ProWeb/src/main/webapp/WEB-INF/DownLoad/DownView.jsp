<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/edutube/resources/smarteditor/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script>
	$(document).ready(function() {
		$("#mBtn").click(function() {
			$("#sfrm").attr("action", "../DownLoad/DownModify.do");
			$("#sfrm").submit();
		});
		$("#dBtn").click(function() {
			$("#sfrm").attr("action", "../DownLoad/DownDelete.do");
			$("#sfrm").submit();
		});
		$("aBtn").click(function(){
			$("#sfrm").attr("action","../DownLoad/DownMain.do");
			$("#sfrm").submit();
		});
	});
</script>

<!--Never Delete "EduContainer" style tag-->
<style>
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}
</style>

</head>
<body>

	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
			<!-- This area is Body Part -->
			<!-- 	상세보기 결과 -->
			<table width="80%" border="1" align="center">
				<tr>
					<td>글번호</td>
					<td>${VIEW.no}</td>
					<td>글쓴이</td>
					<td colspan="3">${VIEW.id}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${VIEW.wdate}</td>
					<td>수정일</td>
					<td>${VIEW.cdate}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3">${VIEW.title}</td>
				</tr>
				<tr>
					<td>본문</td>
					<td colspan="4">${VIEW.body}</td>
				</tr>
			</table>
			<!-- 	파일 목록 
		첨부 파일이 없을 수도 있다.
-->
			<c:if test="${not empty FILES}">
				<table width="80%" border="1" align="center">
					<c:forEach var="temp" items="${FILES}">
						<tr>
							<td><a href="../DownLoad/FileDownLoad.do?oriNo=${temp.no}">${temp.oriname}</a> ( <fmt:formatNumber value="${temp.len}"
									pattern="#,###" /> Bytes)
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<!-- 	기타기능 -->
			<table border="1" width="80%" align="center">
				<tr>
					<td align="center"><c:if test="${VIEW.id eq sessionScope.ID}">
							<input type="button" value="수정하기" id="mBtn">
							<input type="button" value="삭제하기" id="dBtn">
						</c:if> <input type="button" value="목록으로" id="aBtn"></td>
				</tr>
			</table>
			<!-- 	POST 방식으로 서버에 보내기 위해서는 폼을 이용해야 한다. -->
			<form method="POST" id="sfrm">
				<input type="hidden" name="oriNo" value="${VIEW.no}"> 
				<input type="hidden" name="nowPage" value="${NOWPAGE}">
			</form>
		</div>

	</div>

</body>
</html>