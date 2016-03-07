<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#cBtn").click(function(){
					//	무결성 검사하고(숫자로만 개수가 입력되어야 한다.)
					$("#frm").attr("action", "../Shopping/CartInsert.do");
					$("#frm").submit();
				});
				$("#lBtn").click(function(){
					$(location).attr("href", "../Shopping/GoodsList.do?cate=${CATE}");
				});
			});
		</script>
	</head>
	<body>
<!-- 	상품 정보 보여주기 
		폼을 만드는 이유는 장바구니 담기를 할 때 수량을 사용자가 입력해야 한다.
		이처럼 폼을 이용하는 경우는 당근 <form> 태그를 이용해서 처리해야한다.
-->
	<form method="POST" id="frm">
		<table width="50%" border="1" align="center">
			<tr>
				<td rowspan="6">
<!-- 	이미지 출력 위치 
		혹시 이미지가 없을 경우는 대신 적당한 이미지로 보여주도록 한다.
-->
<c:if test="${empty DATA.gimg2}">
					<img src="../gimgs/noimgae.png" width="250" height="250">
</c:if>
<c:if test="${not empty DATA.gimg2}">
					<img src="../gimgs/${DATA.gimg2}" width="250" height="250">
</c:if>
				</td>
				<td>상품 카테고리</td>
				<td>${DATA.scode}</td>
			</tr>
			<tr>
				<td>상품명</td>
				<td>${DATA.name}</td>
			</tr>
			<tr>
				<td>제조사</td>
				<td>${DATA.maker}</td>
			</tr>
			<tr>
				<td>원산지</td>
				<td>${DATA.origin}</td>
			</tr>
			<tr>
				<td>가격</td>
				<td>${DATA.price}</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="hidden" name="no" value="${DATA.no}">
					<input type="hidden" name="cate" value="${CATE}">
					<input type="text" name="count" id="count" size="2" value="1">
					<input type="button" id="cBtn" value="장바구니 담기">
				</td>
			</tr>
		</table>
	</form>
	<table width="50%" border="1" align="center">
		<tr>
			<td align="center">상품 상세 정보</td>
		</tr>
		<tr>
			<td align="center">
<c:if test="${empty DATA.iimg2}">
				<img src="../iimgs/noimgae.png" width="100%" height="500">
</c:if>			
<c:if test="${not empty DATA.iimg2}">
				<img src="../iimgs/${DATA.iimg2}" width="100%" >
</c:if>			
			</td>
		</tr>
	</table>
<!-- 	부가 기능 만들기	-->
	<table width="50%" border="1" align="center">
		<tr>
			<td align="center">
				<input type="button" id="lBtn" value="목록보기">
			</td>
		</tr>
	</table>
	</body>
</html>





