<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</head>
	<body>
		<table width="80%" border="1" align="center">
			<tr>
				<td align="right">
					<a href="../Shopping/CartView.do?cate=${CATE}">Test01</a>
				</td>
			</tr>
		</table>
	
<%--	카테고리 보여주기 	--%>
		<table width="80%" border="1" align="center">
			<tr>
				<td align="center">
<c:forEach var="temp" items="${LCATE}" varStatus="status">
	<c:if test="${status.last}">
				<a href="../Shopping/GoodsList.do?cate=${temp.code}">${temp.name}</a>
	</c:if>				
	<c:if test="${not status.last}">
				<a href="../Shopping/GoodsList.do?cate=${temp.code}">${temp.name}</a>&nbsp;&nbsp;
	</c:if>				
</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="center">
<c:forEach var="temp" items="${MCATE}" varStatus="status">
	<c:if test="${status.last}">
				<a href="../Shopping/GoodsList.do?cate=${temp.code}">${temp.name}</a>
	</c:if>				
	<c:if test="${not status.last}">
				<a href="../Shopping/GoodsList.do?cate=${temp.code}">${temp.name}</a>&nbsp;&nbsp;
	</c:if>				
</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="center">
<c:forEach var="temp" items="${SCATE}" varStatus="status">
	<c:if test="${status.last}">
				<a href="../Shopping/GoodsList.do?cate=${temp.code}">${temp.name}</a>
	</c:if>				
	<c:if test="${not status.last}">
				<a href="../Shopping/GoodsList.do?cate=${temp.code}">${temp.name}</a>&nbsp;&nbsp;
	</c:if>				
</c:forEach>
				</td>
			</tr>
		</table>
<%--	상품 목록 보여주기 	--%>
		<table width="80%" border="1" align="center">
			<tr>
				<th>강사 사진</th>
				<th>닉네임</th>
				<th>고향</th>
				<th>기타</th>
			</tr>
<c:if test="${empty GOODS}">
			<tr>
				<td colspan="4" align="center">
					아직 등록이 되지 않았습니다.
				</td>
			</tr>
</c:if>			
<c:if test="${not empty GOODS}">
	<c:forEach var="temp" items="${GOODS}" varStatus="status">
			<tr>
				<td>
					<img src="../gimgs/${temp.gimg2}" width="100" height="100">
				</td>
				<td>
					<a href="../Shopping/GoodsView.do?no=${temp.no}&cate=${CATE}">${temp.name}</a>
				</td>
				<td>${temp.maker}</td>
				<td>${temp.price}</td>
			</tr>
	</c:forEach>
</c:if>			
		</table>
	</body>
</html>
