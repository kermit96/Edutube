<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		</head>
		</head>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script src="../se2/js/HuskyEZCreator.js" charset="utf-8"></script>
		<script>
			$(document).ready(function(){
				$count = 0;		//	추가될 업로드 폼의 일련번호를 부여할 변수
				$("#aBtn").click(function(){
					//	할일
					//		강제로 파일 업로드 폼을 만들어서 
					$html = "<tr id='fpos"+ ($count + 1) +"'>";
					$html += "<td>파일첨부</td>";
					$html += "<td><input type='file' name='upfile'></td>";
					$html += "</tr>";
					//		폼에 추가한다.
					$("#fpos" + $count).after($html);
					//		아이디로 사용할 일련번호를 증가한다.
					$count = $count + 1;
				});
				$("#dBtn").click(function(){
					if($count == 0) {
						return;
					}
					$("#fpos" + $count).remove();
					$count = $count - 1;
				});
				$("#mBtn").click(function(){
					//	스킨에 있는 내용을 텍스트에리어에 반영해야 하고.....				
					oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
					//	무결성 검사하고
					$("#frm").attr("action", "../Smart/BoardModify.do");
					$("#frm").submit();
				});
			});
		</script>
		<script>
			function DeleteFile(no) {
				//	Ajax를 통해서 삭제를 요청한다.
				$.ajax({
					url : "../Smart/BoardFileDelete.do",
					data : "no=" + no + "&temp=" + new Date(),
					dataType : "json",
					type : "GET",
					success : function(data) {
						//	할일
						//		그 파일이 표시된 줄을 지운다.
						$("#files" + no).remove();
					},
					error : function(){
						alert("에러다");
					}
				});				
			}
		</script>
	<body>
<!-- 	스마트 에디터를 적용하는 폼도 처음에는 원래 만드는 폼처럼 제작한다. 
		다만 textarea 부분에 스마트 에디터 스킨을 적용하면 된다.
		1.	네이버 스마트 에디터 라이브러리를 부른다.
		2.	textarea에 스킨을 입힌다.
-->
		<form method="POST" id="frm" action="" enctype="multipart/form-data">
			<input type="hidden" name="nowPage" value="${NOWPAGE}">
			<input type="hidden" name="kind" value="${KIND}">
			<input type="hidden" name="oriNo" value="${ORINO}">
			<table width="80%" border="1" align="center">
				<tr>
					<td>글쓴이</td>
					<td><input type="text" id="id" name="id" value="${sessionScope.ID}" disabled></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="title" name="title" value="${DATA.title}"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea id="body" name="body" style="width:100%; height:200px;">${DATA.body}</textarea></td>
				</tr>
<c:if test="${not empty FILES}">
	<c:forEach var="temp" items="${FILES}">
	<!-- 	나중에 이 줄을 삭제할 목적으로 id값을 부여한다. -->		
				<tr id="files${temp.no}">
					<td colspan="2">
						${temp.oriname} <input type="button" value="삭제" OnClick="JavaScript:DeleteFile(${temp.no})">
					</td>
				</tr>
	</c:forEach>
</c:if>
<!-- 	새로운 파일 첨부를 위한 제작 
		수정이므로 파일을 업로드할지 모르기 때문에 처음에는 단추만 보여주고
		필요하면 파일 업로드 폼을 추가해서 업로드가 가능하도록 하겠다.
-->
				<tr id="fpos0">
					<td colspan="2" align="right">
						<input type="button" value="추가" id="aBtn">
						<input type="button" value="삭제" id="dBtn">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정하기" id="mBtn">
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
			sSkinURI: "../se2/SmartEditor2Skin.html",
			fCreator: "createSEditor2"
		});
	</script>
</html>