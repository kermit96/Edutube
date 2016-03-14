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
		function goDetail(orino) {
			$(location).attr("href", "../CustomerBoard/FAQBoardView.do?oriNo="+ orino);
		}
	</script>
<style>
.table13_12 table {
	width:100%;
	margin:15px 0;
	border:0;
}
.table13_12 th {
	font-weight:bold;
	background-color:#fbe6d7;
	color:#e65220
}
.table13_12,.table13_12 th,.table13_12 td {
	font-size:0.95em;
	text-align:center;
	padding:4px;
	border-collapse:collapse;
}
.table13_12 th,.table13_12 td {
	border: 1px solid #ffffff;
	border-width:1px 0 1px 0
}
.table13_12 tr {
	border: 1px solid #ffffff;
}
.table13_12 tr:nth-child(odd){
	background-color:#f7f7f7;
}
.table13_12 tr:nth-child(even){
	background-color:#ffffff;
}
</style>

</head>
	<body>

		<table class=table13_12>
			<tr>
				<th>질문유형</th>
				<th>제목</th>
				<th>본문</th>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<c:if test="${temp.qno eq 1}">
				<tr>
					<td>${temp.qname}</td>
					<td>
						<c:if test="${sessionScope.NAL eq 'A'}">
							<a href="JavaScript:goDetail(${temp.no})">
						</c:if>
							${temp.title}</a>
					</td>
					<td>${temp.body}</td>
				</tr>
				</c:if>
			</c:forEach>
		</table>
		
		<table class=table13_12>
			<tr>
				<th>질문유형</th>
				<th>제목</th>
				<th>본문</th>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<c:if test="${temp.qno eq 2}">
				<tr>
					<td>${temp.qname}</td>
					<td>
						<c:if test="${sessionScope.NAL eq 'A'}">
							<a href="JavaScript:goDetail(${temp.no})">
						</c:if>
							${temp.title}</a>
					</td>
					<td>${temp.body}</td>
				</tr>
				</c:if>
			</c:forEach>
		</table>
		
		<table class=table13_12>
			<tr>
				<th>질문유형</th>
				<th>제목</th>
				<th>본문</th>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<c:if test="${temp.qno eq 3}">
				<tr>
					<td>${temp.qname}</td>
					<td>
						<c:if test="${sessionScope.NAL eq 'A'}">
							<a href="JavaScript:goDetail(${temp.no})">
						</c:if>
							${temp.title}</a>
						</td>
					<td>${temp.body}</td>
				</tr>
				</c:if>
			</c:forEach>
		</table>
	</body>
</html>