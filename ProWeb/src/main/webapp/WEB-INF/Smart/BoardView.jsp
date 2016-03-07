<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
		<script>
			$(document).ready(function(){
				$("#mBtn").click(function(){
					//	할일
					//		1.	비밀번호를 받는다.
					$pw = window.prompt("비밀번호를 입력해 주세여");
					if($pw == "") {
						alert("비밀번호를 입력하지 않으면 수정할 수 없어요");
						return;
					}
					//		2.	받은 비밀번호를 숨김에 넣는다.
					$("#pw").val($pw);
					
					//		3.	서버로 보낸다.
					$("#imsiFrm").attr("action", "../Smart/BoardModifyForm.do");
					$("#imsiFrm").submit();
				});
			});
		</script>				
	</head>
	<body>
<!-- 	상세보기 결과 -->
		<table width="80%" border="1" align="center">
			<tr>
				<td>글번호</td>
				<td>${VIEW.no}</td>
				<td>조회수</td>
				<td>${VIEW.hit}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${VIEW.wdate}</td>
				<td>수정일</td>
				<td>${VIEW.mdate}</td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td colspan="3">${VIEW.id}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3">${VIEW.title}</td>
			</tr>
			<tr>
				<td colspan="4">${VIEW.body}</td>
			</tr>
		</table>
<!-- 	파일 목록 
		첨부 파일이 없을 수도 있다.
-->
		<c:if test="${not empty FILES}">
		<table width="80%" border="1" align="center">
			<c:forEach var="temp" items="${FILES}">
				<tr>
					<td>
						<a href="../Smart/BoardDown.do?no=${temp.no}">${temp.oriname}</a> 
	( <fmt:formatNumber value="${temp.len}" pattern="#,###"  /> Bytes)
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>	
<!-- 	이전글 다음글 
		이전글이나 다음글이 없을 수도 있다.
-->
		<table width="80%" border="1" align="center">
			<tr>
				<td>이전글</td>
				<td>
					<c:if test="${MAP.PRENO eq 0}">
						이전글 없음
					</c:if>
					<c:if test="${MAP.PRENO ne 0}">
						<a href="../Smart/BoardHit.do?nowPage=${NOWPAGE}&kind=${KIND}&oriNo=${MAP.PRENO}">${MAP.PRETITLE}</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>다음글</td>
				<td>
					<c:if test="${MAP.NEXTNO eq 0}">
						다음글 없음
					</c:if>
					<c:if test="${MAP.NEXTNO ne 0}">
						<a href="../Smart/BoardHit.do?nowPage=${NOWPAGE}&kind=${KIND}&oriNo=${MAP.NEXTNO}">${MAP.NEXTTITLE}</a>
					</c:if>
				</td>
			</tr>
		</table>
<!-- 	기타기능 -->
		<table border="1" width="80%" align="center">
			<tr>
				<td>
<c:if test="${VIEW.id eq sessionScope.ID}">
					<input type="button" value="수정하기" id="mBtn">
</c:if>			
				</td>
			</tr>
		</table>
<!-- 	POST 방식으로 서버에 보내기 위해서는 폼을 이용해야 한다. -->
		<form method="POST" id="imsiFrm">
			<input type="hidden" name="oriNo" value="${VIEW.no}">
			<input type="hidden" name="nowPage" value="${NOWPAGE}">
			<input type="hidden" name="kind" value="${KIND}">
			<input type="hidden" name="pw" id="pw">
		</form>
	</body>
</html>
