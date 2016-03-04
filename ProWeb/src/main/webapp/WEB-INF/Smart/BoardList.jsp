<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
<!-- 	jquery 라이브러리 설치 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>		
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					$(location).attr("href", "../Smart/BoardWriteForm.dol");
				});
				$("#sBtn").click(function(){
					//	서버로 보내면 된다.
					$("#sFrm").attr("action", "../Smart/BoardSearch.dol");
					$("#sFrm").submit();
				});
			});
		</script>
	</head>
	<body>
<!-- 	검색 기능 폼 -->
		<form method="POST" id="sFrm">
			<table border="1" width="80%" align="center">
				<tr>
					<td>
						<select id="kind" name="kind">
							<option value="title">제목</option>
							<option value="body">본문</option>
							<option value="writer">글쓴이</option>
							<option value="all">제목 + 본문</option>
						</select>
						<input type="text" id="content" name="content">
						<input type="button" value="검색" id="sBtn">
					</td>
				</tr>
			</table>
		</form>
<!-- 	목록 보여주기 -->
		<table border="1" width="80%" align="center">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
<c:forEach		var="temp" items="${LIST}">
			<tr>
				<td>${temp.no}</td>
				<td>
					<a href="../Smart/BoardHit.dol?nowPage=${PINFO.nowPage}&kind=L&oriNo=${temp.no}">${temp.title}</a>
				</td>
				<td>${temp.id}</td>
				<td>${temp.wdate}</td>
				<td>${temp.hit}</td>
			</tr>
</c:forEach>
		</table>
<!-- 	페이지 이동 기능 -->
<!-- 	기타 부가 기능 -->
		<table border="1" width="80%" align="center">
			<tr>
				<td align="center">
					<input type="button" id="wBtn" value="글쓰기">
				</td>
			</tr>
		</table>
	</body>
</html>
