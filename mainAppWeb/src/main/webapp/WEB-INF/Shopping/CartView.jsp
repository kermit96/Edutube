<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<script>
			function DeleteCart(no) {
				//	숨겨진 폼에 삭제할 상품의 번호를 알려준다.
				document.getElementById("no").value = no;
				//	서버로 보낸다.
				document.getElementById("frm").action = 
									"../Shopping/CartDelete.do";
				document.getElementById("frm").submit();
			}
			function UpdateCount(no) {
				//	단추가 눌려진 곳의 입력 상자를 알아낸다.
				var	inb = document.getElementById("cnt" + no);
				//	그 입력상자에 입력된 데이터를 알아낸다.
				var	count = inb.value;
				//	임시 폼에 입력된 알아낸 내용을 넣는다.
				document.getElementById("no").value = no;
				document.getElementById("count").value = count;
				//	서버로 보낸다.
				document.getElementById("frm").action = 
												"../Shopping/CartCountUpdate.do";
				document.getElementById("frm").submit();
			}
		</script>

	</head>
	<body>
<!-- 	POST 방식으로 전송하기 위해서 상품 수정, 삭제를 위한 임시 폼을 만든다. -->
	<form method="POST" id="frm">
		<input type="hidden" id="cate" name="cate" value="${CATE}">
		<input type="hidden" id="no" name="no" value="0">
		<input type="hidden" id="count" name="count" value="0">
	</form>
	<table width="80%" border="1" align="center">
		<tr>
			<th>이미지</th>
			<th>상품명</th>
			<th>제조사</th>
			<th>가격</th>
			<th>수량</th>
			<th>총금액</th>
			<th>&nbsp;</th>
		</tr>
<c:forEach var="temp" items="${LIST}">
		<tr>
			<td><img src="../gimgs/${temp.gimg2}" width="100" height="100"></td>
			<td>${temp.name}</td>
			<td>${temp.maker}</td>
			<td>${temp.price}</td>
			<td>
				<input type="text" id="cnt${temp.no}" size="2" value="${temp.count}">
				<input type="button" id="" value="수정" OnClick="JavaScript:UpdateCount(${temp.no})">
			</td>
			<td>${temp.total}</td>
			<td><input type="button" id="" value="삭제" OnClick="JavaScript:DeleteCart(${temp.no})"></td>
		</tr>		
</c:forEach>	
	</table>
	<table width="80%" border="1" align="center">
		<tr>
			<td align="center">
				<a href="../Shopping/GoodsList.do?cate=${CATE}">쇼핑 계속하기</a>
			</td>
		</tr>
	</table>	
</body>
</html>

