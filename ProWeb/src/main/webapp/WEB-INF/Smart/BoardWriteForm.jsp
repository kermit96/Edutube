<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
		<title>Insert title here</title>
		<script type="text/javascript" src="../se2/js/HuskyEZCreator.js" charset="utf-8">
		</script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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
				$("#wBtn").click(function(){
					//	먼저 스킨에 내용을 textarea로 옮긴다.
					oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	이제 무결성 검사하고...
					var	body = $("#body").val();
					
					$("#frm").attr("action", "../Smart/BoardWrite.dol");
					$("#frm").submit();
				});
			});
		</script>
	</head>
	<body>
		<form method="POST" id="frm" action="" enctype="multipart/form-data">
			<table width="70%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td colspan="2"><input type="text" name="writer" id="writer" value="${sessionScope.NICK}" disabled>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="2"><input type="text" name="title" id="title">
				</tr>
				<tr>
					<td colspan="3">
						<textarea id="body" name="body" style="width:100%; height:200px"></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td colspan="2"><input type="password" name="pw" id="pw">
				</tr>			
<!-- 	동적으로 첨부파일의 개수를 조절할 수 있도록 하고자 한다. 
		그래서 동적으로 첨부파일을 입력할 input를 만들 위치를 잡아주어야한다.
-->
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
<!-- 	body라는 textarea가 만들어지기 전에 이 스크립트가 실행되면
		스킨이 입혀지지 않을 수 있다.
 -->
	<script>
		var oEditors = [];
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "body",
			sSkinURI: "../se2/SmartEditor2Skin.html",
			fCreator: "createSEditor2"
		});
	</script>
</html>
