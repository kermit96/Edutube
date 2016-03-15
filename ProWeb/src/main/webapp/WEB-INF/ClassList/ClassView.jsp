<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
<link rel="stylesheet" href="/edutube/resources/CSS/AdminLTE.min.css">

<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>

<!--스크립트-->
<script>
	function moContent(){
		location.href="../ClassList/ClassModifyForm.do?code=${CODE}&nowPage=${nowPage}&oriNo=${DATA.no}";
	}
	function delContent(){
		var check = confirm("정말 글을 삭제하시겠습니까?");
		if(check){
			location.href="../ClassList/ClassDelete.do?nowPage=${nowPage}&code=${CODE}&oriNo=${DATA.no}";
		}
		else{
			return;
		}
	}
	function goClassList(){
		location.href="../ClassList/ClassList.do?nowPage=${nowPage}&code=${CODE}";
	}
	
	$(document).ready(	function() {
		$("#rewBtn").click(function() {
			
				/* 유효성검사 */
					$relplybody=$("#relplybody").val();					
					$relplybody=$relplybody.replace(/^\s+|\s+$/g,"");
										
					$relplybody = $("#relplybody").val();
						if ($relplybody == "") {
							alert("본문을 입력해 주세요");
							return;
					}
			
				$("#reFrm").attr("action",
					"../ClassList/ClassReplyWrite.do?code=${CODE}&nowPage=${nowPage}&oriNo=${DATA.no}");
				$("#reFrm").submit();
		});
	});
		
	/* 좋아요 처리 */
	$(document).ready(function(){
	$("#goodB").click(function(){
		$.ajax({
			url : "../ClassList/ClassGood.do",
			data : "oriNo=${DATA.no}&temp=" + new Date(),
			dataType : "xml",
			type : "GET",
			success : function(data) {
				//	응답 결과를 좋아요 단추에 기록해준다.
				$good = $(data).find("good").text();
				$("#goodVal").html($good);				
			},
			error : function() {
				alert("에러다");
			}
		});
	});
	}); /*좋아요 처리 끝*/
	
	/*댓글보기*/
	$(document).ready(function(){
		getReListD(1);		
	});
	
	function getReList(num) {				
		var rePage=num;				
		$.ajax({
			url:"../ClassList/ReplyList.do",
			data:"oriNo=${DATA.no}&rePage="+rePage+"&temp="+new Date(),
			type:"GET",
			dataType:"html",
			success: function(data){
				$("#replyListDiv").html(data);
				document.body.scrollTop = document.body.scrollHeight;
			},
			error: function(){
				alert("이거나오면 안되는데....");
			}
		});		
	}
	
	function getReListD(num) {				
		var rePage=num;				
		$.ajax({
			url:"../ClassList/ReplyList.do",
			data:"oriNo=${DATA.no}&rePage="+rePage+"&temp=" + new Date(),
			type:"GET",
			dataType:"html",
			success: function(data){
				$("#replyListDiv").html(data);				
			},
			error: function(){
				alert("이거나오면 안되는데....");
			}
		});		
	}
	
	function writeReply(num) {				
		var no=num;
		$content=$("#relplybody").val();
		$.ajax({
			url:"../ClassList/ClassReplyWrite.do",
			data:"relplybody="+$content+"&oriNo="+no+"&temp="+new Date(),
			type:"POST",			
			success: function(data){				
				getReList("last");
			},
			error: function(){
				alert("이거나오면 안되는데....");
			}
		});		
	}
	
	/*댓글 리스트 끝*/
	
	/*댓글 삭제&수정*/
		function deleteReply(num) {	
		
		var check = confirm("정말 댓글을 삭제하시겠습니까?");
		
		 if(check){		
			var no=num;
			
			$.ajax({
				url:"../ClassList/ReplyDelete.do",
				data:"&reno="+no+"&temp="+new Date(),
				type:"POST",			
				success: function(data){				
					getReList("last");
				},
				error: function(){
					alert("이거나오면 안되는데....");
				}
			});
		 }
		 else{
			 return;
		 }
	}
	
</script>

<!--  스타일 -->
<style>
@font-face {
	font-family: 'NanumGothic';
	src: url('/edutube/resources/fonts/NanumGothic.eot');
	src: url('/edutube/resources/fonts/NanumGothic.eot?#iefix')
		format('embedded-opentype'),
		url('/edutube/resources/fonts/NanumGothic.woff') format('woff');
	font-weight: normal;
	font-style: normal;
}

#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

#main {
	width: 1200px;
}

#centerPage {
	float: right;
	position: relative;
	width: 980px;
	height: 100%;
	padding: 10px;
	font-size: 16px;
}

#sideBarDiv {
	float: left;
	border-top: solid 1px white;
	clear: both;
	width: 150px;
}
#viewMain{
	width:800px;
	margin:0 auto;	
	margin-bottom:100px;
}

#videoM{
	width:800px;
	height:100%;
	margin:0 auto;	
}

#ContentTop{
	width:800px;
	height:100%;
	margin:0 auto;
	padding:20px;
	border-bottom:2px solid orange;
}
#bodycontent{
	width:800px;
	min-height:300px;
	margin:0 auto;
}
#goodUtil{
	width:800px;
	height:100%;
	margin:0 auto;
	margin-bottom:20px;
	border-top:2px solid orange;
}
#utilBar00{
	float:right;
	width:800px;
	height:100%;
	margin:0 auto;
	border-top:2px solid orange;
	border-bottom:2px solid orange;
	padding:20px;
}
#titleC{
	border-top:2px solid purple;
	border-bottom:2px solid orange;
	width:800px;
	height:100%;
	margin:0 auto;
	padding:10px;
}
p#title{
	font-family: 나눔고딕, 'NanumGothic';
	font-size: 24px;
	font color: black;
	font-weight: plane;
}
p#cheer{
	font-family: 나눔고딕, 'NanumGothic';
	font-size: 18px;
	font color: black;
	font-weight: plane;
}
#replyWF{
	width:800px;
	height:100%;
	margin:0 auto;
	padding:20px;
	border-bottom:2px solid orange;
}
/*댓글 배경*/
#replyList{
	width:800px;
	height:100%;
	margin:0 auto;
	background-color:#ecf0f5;
}
textarea#relplybody{
	width:600px;
	resize:none;
}
#replyPagingDiv{
	width:800px;
	margin:0 auto;
	text-align:center;
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
			<div id="sideBarDiv">
				<jsp:include page="/MenuBar/ClassSide.jsp" flush="false" />
			</div> <!--  사이드바 -->

			<div id="centerPage">
				<div id="viewMain">
					<div class="videoM">
					      <c:forEach var="mList" items="${mList}">					      	
					      	<iframe width="600" height="300" src="https://www.youtube.com/embed/${mList.realURL}?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>  					
     					 </c:forEach>	
     					 <p>&nbsp;</p>
					</div>					

					<div id="titleC">
								<p id="title">[${DATA.code}]${DATA.title}</p>							
					</div>		
					<div id="ContentTop">
						<i class="fa fa-user"></i>강사 : <a href="#">${DATA.nick}</a>								
						&nbsp;&nbsp;&nbsp;	
						작성일 : ${DATA.realdate}				
						&nbsp;&nbsp;&nbsp;		
						조회수 : ${DATA.hit}
					</div><!--  topcontent 끝 -->
					<div id="bodycontent">
						${DATA.body}
					</div>					
					<div id="goodUtil">
						<p id="cheer">마음에 드는 강의면 추천을 눌러주세요 :)&nbsp;&nbsp;(중복추천 불가)</p>
							<button class="btn btn-primary" type="button"  id="goodB" >
							<i class="fa fa-thumbs-o-up"></i>좋아요!<span class="badge" id="goodVal">${DATA.good}</span></button>						
					</div>
					<div id="utilBar00">
						<c:if test="${DATA.id eq sessionScope.ID}">
						<a class="button button-yellow" onClick="JavaScript:moContent();"><i class="fa fa-clock-o"></i>수정하기</a>
						<a class="button button-red" onClick="JavaScript:delContent();"><i class="fa fa-times"></i>삭제하기</a>
						</c:if>						
						<a class="button button-orange" onClick="JavaScript:goClassList();" id="ListBtn" >목록으로</a>	
					</div>
					
					<div id="replyWF">
					<h2>댓글 남기기</h2>
						<div id="replybox">
							<form id="reFrm" name="reFrm" method="POST">							
							<textarea class="form-group" rows="4" id="relplybody"  name="relplybody" required></textarea>
							</form>
							<a class="button button-green" onClick="JavaScript:writeReply(${DATA.no});"><i class="fa fa-check"></i>
							댓글 쓰기</a>
						</div>
					</div>
					
					<div id="replyList">
						<div id="replyListDiv">							
						</div>								
					</div>				

				</div><!--  viewmain 끝 -->
			</div><!--  실제로 들어갈 내용 -->
			
		</div><!-- 메뉴바 밑 감싸기 -->

	</div><!-- 전체 감싸기 -->
					 


</body>
</html>