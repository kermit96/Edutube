<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				$("#uBtn").click(function(){
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
		<p><h3 align="center" >공지 사항</h3></p>
		<form method="POST" id="frm" action="../Common/ImageUpload.do" enctype="multipart/form-data">
			<table width="95%" align="center" border="1">
				<tr>
					<td>이미지</td>
					<td><input type="file" id="upfile" name="upfile"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="업로드" id="uBtn">
					</td>
				</tr>
			</table>
		</form>
			</div>
	</div>
	</body>
</html>
