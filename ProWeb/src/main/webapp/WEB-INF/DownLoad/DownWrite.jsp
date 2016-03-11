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
					<td colspan="2"><input type="text" id="id" value="${sessionScope.NICKNAME}" name="id" readonly="readonly"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="2"><input type="text" id="title" name="title"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td colspan="2"><textarea id="body" name="body"></textarea></td>
				</tr>
				<tr id="upload">
					<td>첨부파일</td>
					<td><input type="file" name="upfile" id="upfile"></td>
					<td width="10.25%"><input type="button" id="aBtn" value="추가">
						<input type="button" id="dBtn" value="삭제"></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="button" value="write" id="write"></td>
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
				$count = 0;	//	지금까지 존재하는 첨부 파일의 개수를 지정할 변수
				$("#dBtn").click(function(){
					//	항상 한개는 남겨두어야 하므로
					if($count == 0) {
						return;
					}
					//	삭제는 항상 마지막에 만든 순서대로 지우도록 약속하자.
					$("#upload" + $count).remove();
					//	삭제가 끝났으면 첨부파일의 개수가 줄었으므로
					$count = $count - 1;
				});
				$("#aBtn").click(function(){
					//	할일 	추가할 <tr>을 만든다.
					$count = $count + 1;
					$html = "<tr id='upload"+$count+"'>";
					$html += "<td>첨부파일</td>";
					$html += "<td colspan='2'><input type='file' id='up"+ $count +"' name='upfile'></td>";
					$html += "</tr>";
					
					$("#upload").after($html);
				});
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