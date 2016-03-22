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
	$(document).ready(function() {
		$("#result").click(function() {
			alert($("#code").val());
			if($("#code").val()==0){
				alert("코드값 입력해줘")
				return;
			}
			$("#applyfrm").attr("action", "../Apply/ApplyProc.do");
			$("#applyfrm").submit();
		});
	});
</script>

<!--Never Delete "EduContainer" style tag-->
<style>
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: center;
	padding: 8px;
	height : 50px;
}

tr:nth-child(even) {
	background-color: #f3f3f3
}

th {
	background-color: #f96e5b;
	color: white;
}

tr>th {
	text-align: center;
}

#rcorners2 {
	border: 0px solid white;
	padding: 50px;
	width: 1000px;
}

.button {
	border-radius: 15px;
	background-color: #f96e5b;
	border: none;
	color: white;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	margin: 2px 1px;
	cursor: pointer;
}
.button:hover {background-color: #3e8e41}

.button:active {
  background-color: #3e8e41;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}
.page {
	text-align : right;
	background-color: white;
}
 textarea#body{
      width:750px;
      height:400px;
      resize:none;
   }
</style>

</head>
<body>

	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
		<br>
			<!-- This area is Body Part -->
			<form method="POST" action="" id="applyfrm"
				enctype="multipart/form-data">
				<table width="70%" border="1" align="center">
					<tr>
						<th align="center">강의 종류</th>
						<td>
							<select id="code" name="code">
								<option value="langkor" selected>한국어</option>
								<option value="langjap">일본어</option>
								<option value="langchn">중국어</option>
								<option value="langeng">영어</option>
								<option value="progjava">자바</option>
								<option value="progclang">C언어</option>
								<option value="progphp">PHP</option>
								<option value="cookkor">한식</option>
								<option value="cookchn">중식</option>
								<option value="cookjap">일식</option>
								<option value="cookwest">양식</option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="center">이름</th>
						<td><input type="text" id="name" name="clname"
							value="${sessionScope.NAME}" readonly="readonly"></td>
					</tr>
					<tr>
						<th align="center">아이디</th>
						<td><input type="text" id="id" name="id"
							value="${sessionScope.ID}" readonly="readonly"></td>
					</tr>
					<tr>
						<th align="center">닉네임</th>
						<td><input type="text" id="nick" name="nick"
							value="${sessionScope.NICKNAME}" readonly="readonly"></td>
					</tr>
					<tr>
						<th align="center">전화번호</th>
						<td><input type="text" id="tel" name="tel"
							value="${sessionScope.TEL}" readonly="readonly"></td>
					</tr>
					<tr>
						<th>경력 및 신청사유</th>
						<td height="40%"><textarea id="body" name="body"></textarea></td>
					</tr>
					<tr>
						<th>증명 자료</th>
						<td><input type="file" id="afile" name="afile"></td>
					</tr>
					<tr align="center">
						<td colspan="2"><input type="button" value="신청" id="result"
							name="result" class="button"></td>
					</tr>
				</table>
			</form>
		</div>

	</div>

</body>
</html>



