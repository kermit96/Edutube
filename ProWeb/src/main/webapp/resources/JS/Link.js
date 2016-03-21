/**
 *  네비 메뉴에서 링크 이동 시키는 스크립트
	 메뉴 누르면 이동하는 
 */
	/*Home*/


	function goHome(){
		location.href="/edutube/main.do";
	}

	/*로그인  회원 가입  마이페이지*/
	function goLogin(){
		 var loc = window.location;
			
		 var html = loc.pathname+loc.search+loc.hash;
		
		 location.href="/edutube/member/login.do"+"?returnurl=" +encodeURIComponent(html);
		 

	}
	
	function memberJoin(){

		 var loc = window.location;
			
		 var html = loc.pathname+loc.search+loc.hash;
		
		 location.href="/edutube/member/memberjoin.do"+"?returnurl=" +encodeURIComponent(html);
		
	}
	
	function goLogout(){
		 var loc = window.location;
			
		 var html = loc.pathname+loc.search+loc.hash;
		
		 location.href="/edutube/member/logout.do";	
		
	}
	
	/*홈페이지 소개*/
	function goHomeIntro(){
//		alert('홈페이지 소개');
		location.href="/edutube/Info/InfoMain.do";
	}
	
	/*공지사항*/
	function goNotice(){
		//alert('공지사항');
		location.href="/edutube/Notice/NoticeList.do";
	}

	/*강사소개*/
	function goLecIntro(){
		//alert('강사소개');
		location.href="/edutube/IntroRegManager/IntroList.do";
	}
	
	/*강의목록*/
	function goClasses(){
//		alert('강의목록');
		location.href="/edutube/ClassList/ClassList.do"
	}
	
	/*자료실*/
	function goDownload(){		
		location.href="/edutube/DownLoad/DownMain.do";
	}
	
	/*FAQ*/
	function goFAQ(){
		location.href="/edutube/CustomerBoard/FAQBoardList.do";
		/*location.href="/edutube/CustomerBoard/FAQBoardWriteForm.do";*/	
	}
	
		
	/*QA*/
	function goQA(){
		location.href="/edutube/CustomerBoard/QABoardList.do";
	}
	
	/*게시판 테스트를 위해..*/
	function goTest(){
		location.href="/edutube/Sch/debugList.do";
	}
	
	
	/*로그인  회원 가입  마이페이지*/
	function goLogin(){
	
		 var loc = window.location;
			
		 var html = loc.pathname+loc.search+loc.hash;
		
		 try {
		 location.href="/edutube/member/login.do"+"?returnurl=" +encodeURIComponent(html);
		 
		 } catch (ex) {
			 
			 alert(ex);
		 }		 
	}
	
		
	function goLogout()
	{		
		 location.href="/edutube/member/logout.do";
	}
	
	function memberJoin(){

		 var loc = window.location;
			
		 var html = loc.pathname+loc.search+loc.hash;
		
		 location.href="/edutube/member/joinmember.do"+"?returnurl=" +encodeURIComponent(html);		
	}	


	

	//과목별 강사 소개 링크
	function goJavaLang()

	{
		location.href="/edutube//IntroRegManager/IntroJavaLang.do";
	}
    function goCLang(){
    	location.href="/edutube/IntroRegManager/IntroCLang.do";
    }
    function goPhpLang(){
    	location.href="/edutube/IntroRegManager/IntroPhpLang.do";
    }
 
    function goKorLang(){
    	//alert('KorLang');
    	location.href="/edutube/IntroRegManager/IntroKorLang.do";    
    }
    function goJapLang(){
    	location.href="/edutube/IntroRegManager/IntroJapLang.do";
    }
    function goChnLang(){
    	location.href="/edutube/IntroRegManager/IntroChnLang.do";
    }
    function goEngLang(){
    	location.href="/edutube/IntroRegManager/IntroEngLang.do";
    }
    function goKorCook(){
    	location.href="/edutube/IntroRegManager/IntroKorCook.do";
    }
    function goChnCook(){
    	location.href="/edutube/IntroRegManager/IntroChnCook.do";
    }
    function goJapCook(){
    	location.href="/edutube/IntroRegManager/IntroJapCook.do";
    }
    function goWestCook(){
    	location.href="/edutube/IntroRegManager/IntroWestCook.do";
    }
	function changeLanguage()
	{	
		
	}
	
	function goInfo(){
		location.href="/edutube/myPage/myPageMain.do";
	}
  
