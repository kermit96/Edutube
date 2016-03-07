<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
<!--	�˻��� -->
<!--	��� ��� -->
		<table border="1" align="center" width="80%">
				<tr>
					<td>��ȣ</td>
					<td>����</td>
					<td>����</td>
					<td>�۾���</td>
					<td>�ۼ���</td>
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
					<a href="../CustomerBoard/QABoardList.do?nowPage=1">[ó  ��]</a>
					<c:if test="${PINFO.startPage eq 1}">
						[�� ��]
					</c:if>
					<c:if test="${PINFO.startPage ne 1}">
						<a href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.startPage - 1}">[�� ��]</a>
					</c:if>
					<c:forEach var="temp" begin="${PINFO.startPage}" end="${PINFO.endPage}">
						<a href="../CustomerBoard/QABoardList.do?nowPage=${temp}">[ ${temp} ]</a>
					</c:forEach>
					<c:if test="${PINFO.endPage eq PINFO.totalPage}">
						[�� ��]
					</c:if>
					<c:if test="${PINFO.endPage ne PINFO.totalPage}">
						<a href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.endPage + 1}">[�� ��]</a>
					</c:if>
					<a href="../CustomerBoard/QABoardList.do?nowPage=${PINFO.totalPage}">[������]</a>
				</td>
			</tr>
		</table>
	</body>
</html>