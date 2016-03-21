<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edu Tube | 회원가입</title>

<!--CSS-->
<!-- <link rel="stylesheet" href="../resources/CSS/bootstrap.min.css"> -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css">
 <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
     <link rel="stylesheet" href="/edutube/resources/CSS/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/edutube/resources/CSS/blue.css">
 
<!--  파피콘 넣기 -->
<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
<link rel="icon" href="../favicon.ico" type="image/x-icon" />
<!--  검색바 -->

<!--JS-->
<script src="../resources/JS/jquery-2.1.4.min.js"></script>
<script src="../resources/JS/bootstrap.min.js"></script>

<!--스크립트-->
<!--  sha256 script  -->
<script type="text/javascript" src="../resources/JS/sha256.js">
	
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	$(document).ready(function() {
		init();
	});

	function checkuserid() {

		var id = $("#mem_id").val();
		try {
			$.ajax({
				url : "../member/ajax/sameuserid.do",
				async : false,
				type : 'post',
				dataType : 'json',
				cache : false,
				data : {
					userid : id
				},
				success : function(data) {

					if (data.result) {
						$("#checkid").html("사용할수 없는 ID 입니다. ");
						$("#mem_id").focus();
					} else {

						$("#checkid").html("사용할수 있는 ID 입니다. ");
					}
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}
			});

		} catch (ex) {
			alert("ajax=" + ex);
		}

	}

	function checknickname() {
		var nick = $("#mem_nick").val();

		try {
			$.ajax({
				url : "../member/ajax/samenickname.do",
				async : false,
				type : 'post',
				dataType : 'json',
				cache : false,
				data : {
					nickname : nick
				},

				success : function(data) {
					if (data.result) {
						$("#checknickname").html("사용할수 없는 nickname 입니다. ");
						$("#mem_nick").focus();
					} else {
						$("#checknickname").html("사용할수 있는 nickname 입니다. ");
					}
				},
				error : function(request, status, error) {
					alert("code:" + request.status + "\n" + "message:"
							+ request.responseText + "\n" + "error:" + error);
				}

			});

		} catch (ex) {
			alert("ajax=" + ex);
		}

	}

	function init() {

		try {

			$("#mem_id").blur(function() {

				checkuserid();

			});

			$("#mem_nick").blur(function() {

				checknickname();

			});

			$("#addrfind").click(function() {

				daumaddrfind();
			});

			$("#register").click(function() {
			
				try {

					var password = $("#password").val().trim();
					var repassword = $("#repassword").val().trim();

					var name = $("#mem_name").val().trim();
					var email = $("#mem_email").val().trim();

					var id = $("#mem_id").val().trim();
					var nickname = $("#mem_nick").val().trim();

					var tel = $("#mem_tel").val().trim();

					if (name == "") {
						alert("이름을 입력해 주시기 바랍니다");
						return;

					}

					if (nickname == "") {
						alert("닉네임을 입력해 주시기 바랍니다");
						return;
					}

					if (email == "") {
						alert("이 메일을 입력해 주시기 바랍니다.");
						return;
					}

					if (tel == "") {
						alert("전화 번호를 입력해 주시기 바랍니다.");
						return;
					}

					if (id == "") {
						alert("아이디를 입력해 주시기 바랍니다.");
						return;
					}

					if (password != repassword) {
						alert("password 는 일치 해야 합니다. ")
						return;
					}

					$("#mem_pass").val(Sha256.hash(password));

					$("#target").submit();
				} catch (ex) {

					alert(ex);
				}

			});

			$("#cancel").click(function() {
				self.close();
			});

		} catch (ex) {
			alert(ex);

		}
	}

	function checkpassword(input) {

		/*	
		 var decimal=  /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])(%$?!.*()\s).{6,15}$/;
		
		 if(inputmatch(decimal))   
		 {   
		
		 return true;  
		 }
		
		 else  
		 {   
		
		 return false;  
		
		 }   
		
		
		 return false;
		 */
	}

	function daumaddrfind() {

		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						try {
							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
							var fullAddr = ''; // 최종 주소 변수
							var extraAddr = ''; // 조합형 주소 변수

							// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
								if (data.userLanguageType == 'K')
									fullAddr = data.roadAddress;
								else
									fullAddr = data.roadAddressEnglish;

							} else { // 사용자가 지번 주소를 선택했을 경우(J)
								fullAddr = data.jibunAddress;
								if (data.userLanguageType == 'E')
									fullAddr = data.jibunAddressEnglish;
							}

							// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
							if (data.userSelectedType == 'R'
									&& data.userLanguageType == 'K') {
								//법정동명이 있을 경우 추가한다.
								if (data.bname !== '') {
									extraAddr += data.bname;
								}
								// 건물명이 있을 경우 추가한다.
								if (data.buildingName !== '') {
									extraAddr += (extraAddr !== '' ? ', '
											+ data.buildingName
											: data.buildingName);
								}
								// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
								fullAddr += (extraAddr !== '' ? ' ('
										+ extraAddr + ')' : '');
							}

							// 우편번호와 주소 정보를 해당 필드에 넣는다.
							document.getElementById('mem_addrCode').value = data.zonecode; //5자리 새우편번호 사용

							document.getElementById('mem_addr').value = fullAddr;

							// 커서를 상세주소 필드로 이동한다.
							document.getElementById('mem_detailaddr').focus();

						} catch (ex) {

							alert(ex);
						}
					}
				}).open();

	}
</script>

<style>
#mem_addrCode{
	width:120px;
	float:left;
}
#addrfind{
	width: 120px;
	float:right;
}
input{
	font-size: 16px;
}
</style>

</head>
  <body class="hold-transition register-page">
    <div class="register-box">
      <div class="register-logo">
        <a href="/edutube/main.do"><b>Edu</b>Tube</a>
      </div>

      <div class="register-box-body">
        <p class="login-box-msg">Register a new membership</p>
        <form id="target" method="post"
			action="../member/joinmemberprocess.do">
        <input type="hidden" id="mem_pass" name="mem_pass"/ >
        
          <div class="form-group has-feedback">
            <input type="text" id="mem_id" name="mem_id" class="form-control"
						placeholder="아이디 입력">
						<div id="checkid"></div>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          
   
	   <div class="form-group has-feedback">
	   <!--  주석처리 (시연을 위해서 간단한 비밀번호 입력) -->
	   <!-- pattern="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{7,40}$" -->
 	               <input type="password" id="password" placeholder="비밀 번호" class="form-control"
						title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"
						type="text" required	
						pattern="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{7,40}$"					
						onchange="
	   this.setCustomValidity(this.validity.patternMismatch ? this.title : '');  "> 
	   
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          
          <div class="form-group has-feedback">
            <input type="password" id="repassword" class="form-control"
						placeholder="비밀 번호 확인">
            <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
          </div>
          
            <div class="form-group has-feedback">
            <input type="text" id="mem_name" name="mem_name" class="form-control"
						placeholder="이름">
            <span class="glyphicon glyphicon-tag form-control-feedback"></span>
          </div>
          
               <div class="form-group has-feedback">
            <input type="text"  id="mem_nick" name="mem_nick" class="form-control"
						placeholder="닉네임">
            <span class="glyphicon glyphicon-paperclip form-control-feedback"></span>
            <div id="checknickname"></div>
          </div>
          
               <div class="form-group has-feedback">
            <input type="tel" id="mem_tel" name="mem_tel" class="form-control"
						placeholder="전화 번호">
            <span class="glyphicon glyphicon-phone form-control-feedback"></span>
          </div>          
              
          <div class="form-group has-feedback">
            <input type="email" id="mem_email" name="mem_email" class="form-control"
						placeholder="e-mail">
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>

          
          <!-- 우편번호 -->
          
          <div id="addrDiv1">
          <input type="text" id="mem_addrCode" name="mem_addrCode" class="form-control"
						placeholder="우편번호" readonly>
          <button  id="addrfind" class="btn bg-purple btn-flat margin">우편번호 찾기</button>
          </div>
          <div id="addrDiv2">
           	<input type="text" id="mem_addr" class="form-control"
						name="mem_addr" id="mem_addr" placeholder="주소" readonly>
			
          </div>
          <div id="addrDiv3">
          		<input
						type="text" id="mem_detailaddr" name="mem_detailaddr" class="form-control"
						placeholder="상세주소">
				<span class="glyphicon glyphicon-map-marker form-control-feedback"></span> 
          </div>
          
          <div class="row">
          
            <div class="col-xs-8">
              <div class="checkbox icheck">              
              </div>
            </div><!-- /.col -->
            
            <div class="col-xs-4">
              <button class="btn btn-primary btn-block btn-flat" id="register">Register</button>
            </div><!-- /.col -->
            
          </div>
          
        </form>

       
      </div><!-- /.form-box -->
    </div><!-- /.register-box -->

  
  </body>
</html>
