/**
 *  네비 메뉴에서 링크 이동 시키는 스크립트
	 메뉴 누르면 이동하는 
 */
	/*Home*/
	function goHome(){
		location.href="/edutube";
	}

	/*로그인  회원 가입  마이페이지*/
	function goLogin(){
		alert('로그인');
		//location.href="/edutube/Notice/NoticeList.do";
		//location.href="/edutube/Smart/BoardList.do";
	}
	
	function memberJoin(){
		alert('회원가입');
	}
	
	function goLogout(){
		alert('로그아웃');	
	}
	
	function myPage(){
		alert('마이페이지');
/*		location.href="/edutube/member/myPage.do";*/
	}
	
	/*홈페이지 소개*/
	function goHomeIntro(){
		alert('홈페이지 소개');
	}
	
	/*공지사항*/
	function goNotice(){
		//alert('공지사항');
		//location.href="/edutube/Member/member_form.do";
		location.href="/edutube/Notice/NoticeList.do";
		//location.href="/edutube/Smart/BoardList.do";
	}
   
	/*강사소개*/
	function goLecIntro(){
		//alert('강사소개');
		//location.href="/edutube/Lecturer/BoardList.do";
		//location.href="/edutube/Notice/NoticeF.do";
		location.href="/edutube/IntroRegManager/IntroList.do";		
	}
	function goKor(){
		//location.href="";
	}
	function goKorIntro(){
		location.href="/edutube/IntroRegManager/IntroAList.do";
	}
	function goEngIntro(){
		location.href="/edutube/IntroRegManager/IntroBList.do";
	}
	function goKorIntro(){
		location.href="/edutube/IntroRegManager/IntroCList.do";
	}
	function goEngIntro(){
		location.href="/edutube/IntroRegManager/IntroDList.do";
	}
	/*강의목록*/
	function goClasses(){
		alert('강의목록');
	}
	
	/*자료실*/
	function goDownload(){
		alert('자료실');
	}
	
	/*FAQ/QA*/
	function goQuestion(){
		alert('질문게시판');
	}
		
	/*고객센터*/
	function goCS(){
		alert('고객센터');
	}

	/*검색*/
	function goSearch(){
		alert('검색!');
	}
	
	/*게시판 테스트를 위해..*/
	function goTest(){
		alert('테스트 카테고리');
	}
	
	
  

