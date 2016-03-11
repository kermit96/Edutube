<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/edutube/resources/smarteditor/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script>
	$(document).ready(function() {
		$count = 0;
		$("#aBtn").click(function(){ 
			$html = "<tr id='fpos"+ ($count + 1) +"'>";
			$html += "<td>파일첨부</td>";
			$html += "<td><input type='file' name='upfile'></td>";
			$html += "</tr>";
			$("#fpos" + $count).after($html);
			$count = $count + 1;
		});
		// 글 수정해줘
		$("#mBtn").click(function() {
			oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
			$("#sfrm").attr("action", "../DownLoad/downModifyProc.do");
			$("#sfrm").submit();
		});
		// 글 취소할래
		$("#dBtn").click(function() {
			$("#sfrm").attr("action", "../DownLoad/downBack.do");
			$("#sfrm").submit();
		});
	});
</script>
<script>
//삭제해줘
function DeleteFile(no) {
	$.ajax({
		url : "../DownLoad/fileDelete.do",
		data : "no=" + no + "&temp=" + new Date(),
		dataType : "json",
		type : "GET",
		success : function(data) {
			//	할일
			//		그 파일이 표시된 줄을 지운다.
			$("#sex" + no).remove();
		},
		error : function(){
			alert("에러다");
		}
	});				
}
</script>

<!--Never Delete "EduContainer" style tag-->
<style>
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}
</style>

</head>
<body>

	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
			<!-- This area is Body Part -->
			<form method="POST" id="sfrm">
			<table width="80%" border="1" align="center">
				<tr>
					<td>id</td>
					<td><input type="text" id="id"
						value="${sessionScope.NICKNAME}" name="id" readonly="readonly"></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="title" name="title"
						value="${DATA.title}"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea id="body" name="body">${DATA.body}</textarea></td>
				</tr>
				<c:if test="${not empty FILE}">
					<c:forEach var="temp" items="${FILE}">
						<!-- 	나중에 이 줄을 삭제할 목적으로 id값을 부여한다. -->
						<tr id="sex${temp.no}">
							<td colspan="2">${temp.oriname}<input
										type="button" value="삭제"
										OnClick="JavaScript:DeleteFile(${temp.no})">
								</td>
						</tr>
					</c:forEach>
				</c:if>
				<tr>
					<td align="center"><c:if test="${DATA.id eq sessionScope.ID}">
							<input type="button" value="완료" id="mBtn">
							<input type="button" value="취소" id="dBtn">
						</c:if></td>
				</tr>
			</table>
				<input type="hidden" name="no" value="${ORINO}">
				<input type="hidden" name="nowPage" value="${NOWPAGE}">
			</form>
		</div>
	</div>
</body>
<script>
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors,
		elPlaceHolder : "body",
		sSkinURI : "/edutube/resources/smarteditor/SmartEditor2Skin.html",
		fCreator : "createSEditor2"
	});
</script>
</html>