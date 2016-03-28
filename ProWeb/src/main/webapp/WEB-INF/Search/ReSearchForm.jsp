<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EduTube</title>

<!--  Favicon (Main Icon) -->
<link rel="shortcut icon" href="/edutube/favicon.ico"
	type="image/x-icon" />
<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon" />

<!--CSS-->
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<!--JS-->
<script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
<script src="/edutube/resources/JS/bootstrap.min.js"></script>

<!--CustomScript-->
<script>
	
</script>

<!--Never Delete "EduContainer" style tag-->
<style>
#EduContainer {
	width: 1200px;
	position: absolute;
	left: 50%;
	margin-left: -600px;
}

#main {
	width: 1200px;
	height: 100%;
}

#searchMain {
	width: 1200px;
	height: 100%;
}

#searchTop {
	width: 800px;
	height: 100px;
	line-height: 100px;
	margin: 0 auto;
}

#searchTop p {
	vertical-align: middle;
	font-size: 18px;
}

#searchUtil {
	clear: both;
	width: 800px;
	padding: 20px;
	border: 1px solid black;
	margin: 0 auto;
}

#searchList {
	width: 800px;
	height: 100%;
	border: 1px solid black;
	margin: 0 auto;
	margin-bottom: 100px; padding : 20px;
	overflow: auto;
	padding: 20px;
}

#researchBox {
	border: 3px solid #ff8000;
	outline-style: none;
	width: 350px;
}

#paging{		
	text-align:center;
	margin: 0 auto;
}
tbody tr td a:link{
	text-decoration: none;
}	
tbody tr td a:hover{
	text-decoration: none;
}	
tbody tr td a:visited{
	text-decoration: none;
}
</style>

<script>
function reSearchOnKey()
{
     if(event.keyCode == 13)
     {	          
    	 detailSearch(); 
     }	     
}
function detailSearch(){
	var kind = $("input[type=radio][name=a]:checked").val()
	if(kind == "a"){
		if($("#code").val()==0){
			alert("코드값 입력해줘")
			return;
		}
	}
	else {
		
	}
	$searchWord=$("#researchBox").val();
	$searchWord=$searchWord.replace(/^\s+|\s+$/g,"");
	if($searchWord==""){
		alert('검색어를 입력해주세욜');
		return;
	}	

	$("#detailSearch").attr("action", "/edutube/Search/ReSearchForm.do?kind="+kind);
	$("#detailSearch").submit();
	
}

function viewListBtn(ck) {

	var obj = document.getElementById("listBtn");

	if( ck == "a" ) {
	obj.style.display = "block"; 

	} else if( ck == "b" ) {
	obj.style.display = "none";
	} 

}
</script>

</head>
<body>

	<div id='EduContainer'>

		<div id='top'>
			<jsp:include page="/MenuBar/Top.jsp" flush="false" />
		</div>

		<div id="Main">
			<!-- This area is Body Part -->
			<div id="searchMain">
				<div id="searchTop">
					<p>"${searchBox}" 에 대한 검색 결과</p>
				</div>

				<div id="searchUtil">
					<h3>상세 검색</h3>
					<form method="POST" name="detailSearch" id="detailSearch" onKeyDown="JavaScript:reSearchOnKey();">
					<table id="utilTable">
						<tr>
							<td>
								<input type="radio" name="a"  value="a" onclick="viewListBtn('a');" checked> 과목검색&nbsp;
					  &nbsp;	<input type="radio" name="a"  value="b" onclick="viewListBtn('b');"> 강사검색
							</td>
						</tr>
						&nbsp;
						&nbsp;
						<tr>
							<td>
								<div id="listBtn" style="display:block;">
								<select id="code" name="code" class="input-large">
									<option value="0" >과목선택</option>
										<c:forEach var="sublist" items="${SUBLIST}">
									<option value="${sublist.subcode}">${sublist.subname}</option>
										</c:forEach>
								</select>
								</div>
							</td>							
						</tr>
						<tr>
							<td colspan="3">
								&nbsp;
							</td>							
						</tr>
						
						<tr>
							<td  colspan="3">
							<input type="text" id="researchBox" name="researchBox">
								<a href="JavaScript:detailSearch()"><img
									src="/edutube/resources/img/searchBtn.png"></a>
							</td>
						</tr>

					</table>
					</form>
				</div>
				<!-- 검색 결과  -->
				<div id="searchList">검색결과
		<table border="1" align="center" width="100%">
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>글쓴이</th>
					<th>추천수</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			<c:forEach var="content" items="${LIST}">
				<tr>
					<td>${content.no}</td>
					<td>
						<a href="../ClassList/ClassView.do?nowPage=${PINFO.nowPage}&oriNO=${content.no}&code=${CODE}"><span></span>[${content.code}]&nbsp;${content.title}</a>
					</td>
					<td>${content.nick}</td>
					<td>${content.good}</td>
					<td>${content.hit}</td>
					<td>${content.realdate}</td>
				</tr>
			</c:forEach>
		</table>
 		<!--  페이징 처리 -->
				<div id="paging">
					<ul class="pagination">
					

						<c:if test="${PINFO.startPage ne 1}">
							<li><a
								href="../ClassList/ClassList.do?nowPage=${PINFO.startPage-1}&code=${CODE}">이전</a></li>
						</c:if>

						<c:forEach var="imsi" begin="${PINFO.startPage}"
							end="${PINFO.endPage}">

							<c:if test="${imsi ne PINFO.nowPage}">
								<li><a href="../ClassList/ClassList.do?nowPage=${imsi}&code=${CODE}">${imsi}</a></li>
							</c:if>
							<c:if test="${imsi eq PINFO.nowPage}">
								<li class="active"><a href="#">${imsi}</a></li>
							</c:if>

						</c:forEach>

						<c:if test="${PINFO.endPage ne PINFO.pageNum}">
							<li><a
								href="../ClassList/ClassList.do?nowPage=${PINFO.endPage + 1}&code=${CODE}">다음</a></li>
						</c:if>

						<c:if test="${PINFO.endPage eq PINFO.pageNum}">
						</c:if>

					</ul>
				</div><!--  페이징 처리 끝 -->
				</div>

			</div>
			<!-- 작은 메인 -->

		</div>
		<!-- big Main -->
	</div>
</body>
</html>



