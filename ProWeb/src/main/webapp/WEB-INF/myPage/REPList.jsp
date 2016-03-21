<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.NAL ne 'A'}">
 	<c:redirect url="../Member/login.do" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

	<script>
	function Sex(no){
		location.href="../myPage/REPUserstop.do?no="+no;
	}
	</script>
	
	<style>
	#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

#main {
	width: 1200px;
}
	</style>
	
</head>
	<body>
	
	<div id='EduContainer'>
		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>
	
	<div id="main">
	<form method="POST" id="sfrm">
		<table border="1" align="center" width="80%">
			<tr>
				<td>글쓴이</td>
				<td>신고자아이디</td>
				<td>작성일</td>
				<td>신고사유</td>
				<td>신고게시판</td>
				<td>신고글 번호</td>
				<td>회원정지</td>
			</tr>
			<c:forEach var="temp" items="${LIST}">
				<tr>
				<td>${temp.id}</td>
				<td>${temp.repid}</td>
				<td>${temp.wdate}</td>
				<td>${temp.code}</td>
				<td>${temp.bcode}</td>
				<td>${temp.bno}</td>
				<td>
					<input type="button" value="정지" id="sBtn"  onClick="JavaScript:Sex(${temp.no})"/>
				</td>
				</tr>
			</c:forEach>
		</table>
	</form>
	</div>
	</div>
	</body>
</html>