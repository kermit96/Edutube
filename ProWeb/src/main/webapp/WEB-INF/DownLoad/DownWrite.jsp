<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Write</title>
		<!--CSS-->
		<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
		<!--  파피콘 넣기 -->
		<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
		<!--  검색바 -->
		
		<!--JS-->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 		<script src="/edutube/resources/JS/bootstrap.min.js"></script>
		<script type="text/javascript" src="/edutube/resources/smarteditor/js/HuskyEZCreator.js" charset="UTF-8"></script>

	</head>  
	<body>
		<form method="POST" id="wfrm" name="wfrm"  enctype="multipart/form-data">
			<table width="70%" border="1" align="center">
				<tr>
					<td>id</td>
					<td><input type="text" id="id" value="${sessionScope.NICKNAME}" name="id" readonly="readonly"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="title" name="title"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea id="body" name="body"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="upfile" id="upfile"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="button" value="write" id="write"></td>
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
			<script>
			$(document).ready(function(){
				$("#write").click(function(){
					oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					$title = $("#title").val();
					if($title==""){
						alert("제목을 입력해 주세요.");
						return;
					}
					$body = $("#body").val();
					if($body==""){
						alert("본문을 입력해 주세요.");
						return;
					}
					var body = $("#body").val();
					$frm = $("#wfrm");
					$frm.attr("action","../DownLoad/DownWriteProc.do");
					$frm.submit();
				});
			});
		</script>
</html>