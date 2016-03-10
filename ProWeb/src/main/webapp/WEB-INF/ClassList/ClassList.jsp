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

<!--JS-->

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
/* 페이징과 테이블 CSS */
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
				<!--  게시글 테이블 (body) -->
				<!-- 아래 부터 작성 -->
				<p>&nbsp;</p>

				<h3>강의</h3>
				<br>

				<!--  선택박스 (그룹 선택) -->

				<!-- 채용공고 게시판 -->
				<table class="table table-bordered table-hover">

					<colgroup>
						<col style="width: 100px">
						<col style="width: 400px">
						<col style="width: 160px">
						<col style="width: 100px">
						<col style="width: 100px">
						<col style="width: 120px">
					</colgroup>

					<!-- 컬럼 -->
					<thead>
						<tr>
							<th>글번호</th>
							<th>글제목</th>
							<th>글쓴이</th>
							<th>추천수</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
					</thead>

					<!--  바디 -->
					<tbody>

						<!--  반복문 시작 -->
						<c:forEach var="content" items="${LIST}">
							<tr>
								<td>${content.no}</td>
								<td>
								<a href="../ClassList/ClassList.do?nowPage=${PINFO.nowPage}&oriNO=${content.no}&code=${CODE}"><span></span>[${content.code}]&nbsp;${content.title}</a>
								</td>
								<td>${content.nick}</td>
								<td>	${content.good}</td>
								<td>${content.hit}</td>
								<td>${content.realdate}</td>								
							</tr>
						</c:forEach>
						<!--  반복문 종료 -->

					</tbody>
					<!--  필요한 기능 -->
						<c:if test="${sessionScope.NAL eq 'L' }">
						<tr>
							<td colspan=6 align="center">
								<button class="btn btn-success" type="button"
									onclick="JavaScript:ReqWrite();">
									<span class="glyphicon glyphicon-pencil"></span>글쓰기
								</button>
							</td>
						</tr>
						</c:if>					

				</table>
				<!--  테이블 종료 -->
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
			</div><!--  게시글 테이블 끝 -->
		</div><!--  센터페이지 -->
	</div><!--  전체 DIV -->

</body>
</html>



