<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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
	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;								
		}
	</style>
	<style>
		#Table{
			background-color: MistyRose;<!--Joon -->
		}
	</style>	
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
				$("#rBtn").click(function(){
					//	무결성 검사(소카테고리가 선택, 내용이 입력)
					$("#frm").attr("action", "../IntroRegManager/IntroReg.do");
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
	<div id='EduContainer'>
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>	
	
	<div id="Main">
		<h3 align="center" >강사 소개 등록&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="../IntroRegManager/IntroList.do">목록</a></h3>
	<!--  강사 소개글 등록 폼 -->
		<form method="POST" id="frm" enctype="multipart/form-data">


			<table width="50%" border="1" align="center">
				<tr>
					<td>강사 ID</td>
					<td><input type="text" id="mem_id" name="mem_id" value="${sessionScope.loginId}" disabled></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="intro_title" name="intro_title"></td>
				</tr>
				<tr>
					<td>소개 본문</td>
					<td><textarea id="intro_body" name="intro_body"></textarea></td>
				</tr>
				<tr>
					<td>사진</td>
					<td><input type="file" id="gimg" name="gimg"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="소개 글 등록" id="rBtn">
					</td>
				</tr>
			</table>
		</form>
		</div>
	</div>
	</body>
</html>



