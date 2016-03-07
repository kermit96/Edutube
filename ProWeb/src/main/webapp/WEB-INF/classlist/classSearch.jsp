<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
</head>
<body>
	<table width="80%" border="1" align="center">
		<tr>
			<th>강의번호</th>
			<th>강의코드</th>
			<th>제　  목</th>
			<th>작 성 자</th>
			<th>작 성 일</th>
			<th>추 천 수</th>
		</tr>
		
	</table>
	
	<!-- 	페이지 이동 기능 -->
	<table border="1" align="center" width="80%">
		<tr>
			<td align="center">
			<!-- 	[처음][이전][1][2][3][4][5][다음][마지막] -->
				<a href="../classlist/classSearch.do?nowPage=1">[처  음]</a>
				<c:if test="${PINFO.startPage eq 1}">
					[이 전]
				</c:if>
				<c:if test="${PINFO.startPage ne 1}">
					<a href="../classlist/classSearch.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
				</c:if>
				<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
					<a href="../classlist/classSearch.do?nowPage=${temp}">[ ${temp} ]</a>
				</c:forEach>
				<c:if test="${PINFO.endPage eq PINFO.totalPage}">
					[다 음]
				</c:if>
				<c:if test="${PINFO.endPage ne PINFO.totalPage}">
					<a href="../classlist/classSearch.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
				</c:if>
				<a href="../classlist/classSearch.do?nowPage=${PINFO.totalPage}">[마지막]</a>
			</td>
		</tr>
	</table>
</body>
</html>