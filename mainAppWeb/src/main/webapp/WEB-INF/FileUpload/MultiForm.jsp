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
					//	무결성 검사하고
					//	전송한다.
					$("#ufrm").submit();
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
		<form method="POST" id="ufrm" action="../FileUpload/MultiUpload.do" enctype="multipart/form-data">
			<table width="50%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" name="writer" id="writer"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="body" id="body"></textarea></td>
				</tr>
<!-- 첨부 파일 폼을 만든다.
	다중 업로드가 가능하도록 하는 방법
	1.Input type="file"의 name 값을 다 다르게 한다. 이때는 데이터 빈 클래스에 각각의 name 값에 따른 get,set 함수를 만들어 놓는다.
	 장점  지금까지 배운 작업을 그대로 진행하면 된다.
	 
	2.input type="file"의 name 값을 동일하게 한다.
	 이때는 데이터 빈 클래스에 해당 name값을 배열로 처리한다.
	 장점 배열 처리가 되었으므로 반복이 손쉽다.
	 단점 배열 처리 기법을 사용해야 한다.
 -->
				<tr>
					<td>첨부파일1</td>
					<td><input type="file" name="files" id="upfile1"></td>
				</tr>
				<tr>
					<td>첨부파일2</td>
					<td><input type="file" name="files" id="upfile2"></td>
				</tr>
				<tr>
					<td>첨부파일3</td>
					<td><input type="file" name="files" id="upfile3"></td>
				</tr>
				<tr>
					<td>첨부파일4</td>
					<td><input type="file" name="files" id="upfile4"></td>
				</tr>
				<tr>
					<td>첨부파일5</td>
					<td><input type="file" name="files" id="upfile5"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="올리기" id="uBtn">
					</td>
				</tr>
			</table>		
		</form>
		</div>
	</div>
	</body>
</html>