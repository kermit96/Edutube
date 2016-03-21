<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>EduTube</title>
		<script type="text/javascript" src="/edutube/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		
		<script>
			$(document).ready(function(){
				$("#wBtn").click(function(){
					oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					var	body = $("#body").val();
					alert(body);
					$("#frm").attr("action", "../Smart/BoardWrite.do");
					$("#frm").submit();
				});
			});
		</script>
	</head>
	<body>
	<%
		session.setAttribute("ID","rewg3");
		session.setAttribute("NICK","nasundol");
		session.setAttribute("NAME","나선돌");
		session.setAttribute("PASSWORD","asd123");
	%>
		<form method="POST" id="frm" action="" enctype="multipart/form-data">
			<table width="70%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td colspan="2"><input type="text" name="writer" id="writer" value="${sessionScope.NICK}" disabled></td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="2"><input type="text" name="title" id="title"></td>
				</tr>
				<tr>
					<td colspan="3">
						<textarea id="body" name="body" style="width:100%; height:200px"></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td colspan="2"><input type="password" name="pw" id="pw"></td>
				</tr>			
				
				<tr id="upload">
					<td>첨부파일</td>
					<td><input type="file" name="upfile" id="up0"></td>
					<td>
						<input type="button" id="aBtn" value="추가">
						<input type="button" id="dBtn" value="삭제">
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" value="글쓰기" id="wBtn">
					</td>
				</tr>
			</table>		
		</form>
	</body>
	
	<script>
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "body",
			sSkinURI: "/edutube/resources/smarteditor/SmartEditor2Skin.html",
			fCreator: "createSEditor2"
		});
	</script>
</html>
