<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#rBtn").click(function(){
			
			alert($("#code").val());
			if($("#code").val()==0){
				alert("코드값 입력해줘")
				return;
			}
			alert($("#bcode").val());
			if($("#bcode").val()==0){
				alert("코드값 입력해줘")
				return;
			}
			$("#rfrm").attr("action","../myPage/REPWrite.do");
			$("#rfrm").submit();
		});
	});
	
	
</script>
</head>
	<body>
		<form method="POST" action="" id="rfrm" enctype="multipart/form-data">
				<table width="70%" border="1" align="center">
					<tr>
						<td align="center">신고 항목
							<select id="code" name="code">
								<option value="1" selected>폭언/욕설</option>
								<option value="2">저작권침해</option>
								<option value="3">음란물등록</option>
								<option value="4">계정해킹</option>
								<option value="5">기타</option>
							</select>
						</td>
						
						<td align="center">게시판 분류
							<select id="bcode" name="bcode">
								<option value="6" selected>자료실</option>
								<option value="7">질문게시판</option>
								<option value="8">댓글</option>
								<option value="9">기타</option>
							</select>
						</td>
					</tr>
						<!-- <input type="hidden" id="no" name="no"> -->
					<tr>
						<td align="center" >글쓴이</td>
						<td><input type="text" id="id" name="id"
							value="${sessionScope.ID}" readonly="readonly"></td>
					</tr>
					<tr>
						<td align="center">신고 게시판 번호</td>
						<td><input type="text" id="bno" name="bno"></td>
					</tr>
					<tr>
						<td align="center">신고할 아이디</td>
						<td><input type="text" id="repid" name="repid"></td>
					</tr>
					<tr align="center">
						<td colspan="2"><input type="button" value="신고" id="rBtn" name="rBtn"></td>
					</tr>
				</table>
			</form>
	</body>
</html>