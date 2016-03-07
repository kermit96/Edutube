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
			$(document).ready(function(){
				//	대카테코리 콤보상자에서 선택하면 중 카테고리를 불러오자
				$("#lcode").change(function(){
					//	Ajax 기법에 의해서 불러오도록 한다.
					$item = $("#lcode").val();
					if($item == "0") {
						return;
					}
					$.ajax({
						url : "../GoodsManager/GoodsMCateSelect.do",
						data : "lcode=" + $item + "&temp=" + new Date(),
						dataType : "xml",
						type : "GET",
						success : function(data){
							//	할일
							//	먼저있던 내용 지우고
							$("#mcode").find("option").remove();
							//	기본 내용 하나 넣고
							$("#mcode").append("<option value='0'>=중 카테고리=</option>");
							//	응답받은 내용을 반복하면서 나머지 내용을 채운다.
							$cates = $(data).find("cate");
							$cates.each(function(){
								$code = $(this).find("code").text();
								$name = $(this).find("name").text();
								$html = "<option value='"+$code+"'>"+$name+"</option>";
								$("#mcode").append($html);
							});
						},
						error : function(){
							alert("에러다");
						}
					});
				});
				$("#mcode").change(function(){
					//	Ajax 기법에 의해서 불러오도록 한다.
					$item = $("#mcode").val();
					if($item == "0") {
						return;
					}
					$.ajax({
						url : "../GoodsManager/GoodsSCateSelect.do",
						data : "mcode=" + $item + "&temp=" + new Date(),
						dataType : "xml",
						type : "GET",
						success : function(data){
							//	할일
							//	먼저있던 내용 지우고
							$("#scode").find("option").remove();
							//	기본 내용 하나 넣고
							$("#scode").append("<option value='0'>=소 카테고리=</option>");
							//	응답받은 내용을 반복하면서 나머지 내용을 채운다.
							$cates = $(data).find("cate");
							$cates.each(function(){
								$code = $(this).find("code").text();
								$name = $(this).find("name").text();
								$html = "<option value='"+$code+"'>"+$name+"</option>";
								$("#scode").append($html);
							});
						},
						error : function(){
							alert("에러다");
						}
					});
				});
				$("#gBtn").click(function(){
					//	무결성 검사(소카테고리가 선택, 내용이 입력)
					$("#frm").attr("action", "../GoodsManager/GoodsReg.do");
					$("#frm").submit();
				});
				$("#lBtn").click(function(){
					//	무결성 검사(대카고리 내용이 입력되었는지)
					$("#frm").attr("action", "../GoodsManager/GoodsLCateGeg.do");
					$("#frm").submit();
				});
				$("#mBtn").click(function(){
					//	무결성 검사(대카고리 선택이 되었는지, 중카테고리 내용이 입력되었는지)
					$("#frm").attr("action", "../GoodsManager/GoodsMCateGeg.do");
					$("#frm").submit();
				});
				$("#sBtn").click(function(){
					//	무결성 검사(대카고리 선택이 되었는지, 중카테고리 내용이 입력되었는지)
					$("#frm").attr("action", "../GoodsManager/GoodsSCateGeg.do");
					$("#frm").submit();
				});
			});
		</script>		
	</head>
	<body>
<!-- 	카테고리 보여주기 폼 -->
		<form method="POST" id="frm" enctype="multipart/form-data">
			<table width="50%" border="1" align="center">
				<tr>
					<td align="center">
						<select id="lcode" name="lcode">
							<option value="0">=대 카테고리=</option>
<c:if test="${not empty LCATE}">
	<c:forEach var="temp" items="${LCATE}">
							<option value="${temp.code}">${temp.name}</option>
	</c:forEach>
</c:if>
						</select>
						<select id="mcode" name="mcode">
							<option value="0">=중 카테고리=</option>
						</select>
						<select id="scode" name="scode">
							<option value="0">=소 카테고리=</option>
						</select>
					</td>
				</tr>
			</table>		
<!-- 	카테고리 등록 폼 -->
			<table width="50%" align="center" border="1">
				<tr>
					<td>대카테고리</td>
					<td><input type="text" name="lname" id="lname"></td>
					<td><input type="button" id="lBtn" value="대카테고리 등록"></td>
				</tr>
				<tr>
					<td>중카테고리</td>
					<td><input type="text" name="mname" id="mname"></td>
					<td><input type="button" id="mBtn" value="중카테고리 등록"></td>
				</tr>
				<tr>
					<td>소카테고리</td>
					<td><input type="text" name="sname" id="sname"></td>
					<td><input type="button" id="sBtn" value="소카테고리 등록"></td>
				</tr>
			</table>
<!-- 	상품 등록 폼 -->
			<table width="50%" border="1" align="center">
				<tr>
					<td>상품명</td>
					<td><input type="text" id="name" name="name"></td>
				</tr>
				<tr>
					<td>제조사</td>
					<td><input type="text" id="maker" name="maker"></td>
				</tr>
				<tr>
					<td>가격</td>
					<td><input type="text" id="price" name="price"></td>
				</tr>
				<tr>
					<td>원산지</td>
					<td><input type="text" id="origin" name="origin"></td>
				</tr>
				<tr>
					<td>상품이미지</td>
					<td><input type="file" id="gimg" name="gimg"></td>
				</tr>
				<tr>
					<td>정보이미지</td>
					<td><input type="file" id="iimg" name="iimg"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="상품등록" id="gBtn">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>



