<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<!--  ������ �ֱ� -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  �˻��� -->

<!--JS-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>
<script type="text/javascript"
	src="/edutube/resources/smarteditor/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script>
	function Stop(no){
		location.href="../Apply/acceptApply.do?no="+no;
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
			<table width="80%" border="1" align="center">
				<tr>
					<td>아이디</td>
					<td>강의코드</td>
					<td>내용</td>
					<td>파일</td>
					<td>승인</td>
				</tr>
				<form method="POST" action="">
					<c:forEach var="temp" items="${LISTS}">
						<tr>
							<td>${temp.id}</td>
							<td>${temp.code}</td>
							<td>${temp.body}</td>
							<c:if test="${temp.savename ne nulls}">
								<td>${temp.savename}</td>
							</c:if>
							<c:if test="${temp.savename eq nulls}">
								<td>없음</td>
							</c:if>
							<td><input type="button" id="oBtn" value="승인" Onclick="JavaScript:Stop(${temp.no})"> <input
								type="button" id="nBtn" value="거절">
								</td>
						</tr>
					</c:forEach>
				</form>
			</table>
		</div>

	</div>

</body>
</html>



