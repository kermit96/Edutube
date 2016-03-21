<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#rBtn").click(function(){
			
			
			if($("#code").val()==0){
				alert("코드값 입력해줘")
				return;
			}
			
			if($("#bcode").val()==0){
				alert("코드값 입력해줘")
				return;
			}
			$("#rfrm").attr("action","../myPage/REPWrite.do");
			$("#rfrm").submit();
		});
	});
	
	
</script>
<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
		}
		#main{
				width:1200px;
		}	
		#centerPage{
			float:right;
			position: relative;					
			width:980px;
			height:100%;
			padding:10px;
			font-size:16px;					
		}
		#sideBarDiv{
			float:left;
			border-top:solid 1px white;
			clear:both;
			width:150px;			
		}
		#realContent{
			width:700px;
			padding-top:50px;
			padding-bottom:100px;
			padding-right:100px;
			padding-left:50px;			
			margin: 0 auto;			
		}	
</style>
</head>
	<body>
	
	<div id='EduContainer'>
	
	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>		
		
	<div id="main">
				
		<div id="centerPage">
			<div id="realContent">
			
				<form method="POST" action="" id="rfrm" enctype="multipart/form-data">
				<table border="1" align="center">
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
			
			
			</div>
		</div>
		
	</div>
	
	</div>
	
	</body>
</html>