<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>
	<!--CSS-->
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<!--  검색바 -->
	
	<!--JS-->
	 <script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
 	<script src="/edutube/resources/JS/bootstrap.min.js"></script>
 	
	<!--스크립트-->
	<script>

	</script>
	
	<!--  스타일 -->
	<style>
		#EduContainer{
				width:1200px;
				position: absolute;
				left: 50%;
				margin-left:-600px;				
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
		</div>
			
		<div id="centerPage">
			
		</div>	
	</div>
	
</div>

<!--  글쓰기 -->
<script>
 var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	    oAppRef: oEditors,
	    elPlaceHolder: "body",
	    sSkinURI: "/edutube/resources/smarteditor/SmartEditor2Skin.html",
	    fCreator: "createSEditor2"
	}); 
	
	$(document).ready(function(){
		
		$("#save").click(function(){
			
			$title =$("#title").val();
			if($title==""){
				alertify.alert("제목을 입력해 주세요");
				return;
			}
			
			$group =$("#depG").val();
			if($group==0){
				alertify.alert("그룹을 선택해 주세요");
				return;
			}
			
			$body =$("#body").val();
			if($body==""){
				alertify.alert("본문을 입력해 주세요");
				return;
			}
			
			$wdate =$("#wdate").val();
			if($wdate==""){
				alertify.alert("날짜를 선택해 주세요");
				return;
			}
			
			oEditors.getById["body"].exec("UPDATE_CONTENTS_FIELD", []);
			
			$("#frm").attr("action", "../ClassList/ClassWrite.do?nowPage=${nowPage}&code=${CODE}");
			$("#frm").submit();
		});
	});
</script>

</body>
</html>