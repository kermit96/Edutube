<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	function goDetail(orino) {
		$(location).attr("href", "../CustomerBoard/QABoardView.do?nowPage=${PINFO.nowPage}&oriNo="+ orino + "&flag=S");
	}
	$(document).ready(function(){
		$("#sBtn").click(function(){
			$("#sfrm").attr("action", "../CustomerBoard/QABoardSearch.do");
			$("#sfrm").submit();
		});
		$("#wBtn").click(function(){
			$(location).attr("href", "../CustomerBoard/QABoardWriteForm.do");
		});	
	});
</script>
</head>
	<body>
<!--	검색폼 -->
<!--	목록 출력 -->
		<table border="1" align="center" width="80%">
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>본문</td>
					<td>글쓴이</td>
					<td>작성일</td>
				</tr>
			<c:forEach var="temp" items="${LIST}">
				<tr>
					<td>${temp.no}</td>
	<td>
		<c:if test="${temp.mid ne 0}">
			<c:forEach var="test" begin="1" end="${temp.mid}">
				&nbsp;&nbsp;
			</c:forEach>
				=>
		</c:if>
					<a href="JavaScript:goDetail(${temp.no})">${temp.title}</a></td>
					<td>${temp.body}</td>
					<td>${temp.id}</td>
					<td>${temp.datetime}</td>
				</tr>
			</c:forEach>
		</table>	
	<table border="1" align="center" width="80%">
			<tr>
				<td align="center">
					<a href="../CustomerBoard/QABoardList.do?nowPage=1">[처  음]</a>
					<c:if test="${PINFO.startPage eq 1}">
						[이 전]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.startPage - 1}">[이 전]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="../CustomerBoard/QABoardList.do?nowPage=${temp}">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[다 음]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.endPage + 1}">[다 음]</a>
					</c:if>
					<a href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.totalPage}">[마지막]</a>
				</td>
			</tr>
		</table>
	</body>
</html>