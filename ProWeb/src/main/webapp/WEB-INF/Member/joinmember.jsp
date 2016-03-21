<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edu Tube</title>

<!--CSS-->
<!-- <link rel="stylesheet" href="../resources/CSS/bootstrap.min.css"> -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600'
	rel='stylesheet' type='text/css'>
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css"
	rel="stylesheet">
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
					//	$("#checkid").html("사용할수 없는 ID 입니다. ");
						$("#checkid").html("${LANGDATA.membercheckidfail}");
						$("#mem_id").focus();
					} else {

						// $("#checkid").html("사용할수 있는 ID 입니다. ");
						$("#checkid").html("${LANGDATA.membercheckidsuccess}");
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
				//		$("#checknickname").html("사용할수 없는 nickname 입니다. ");
						$("#checknickname").html("${LANGDATA.memberchecknickfail}");
						$("#mem_nick").focus();
					} else {
						// $("#checknickname").html("사용할수 있는 nickname 입니다. ");
						$("#checknickname").html("${LANGDATA.memberchecknicksuccess}");
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
						// alert("이름을 입력해 주시기 바랍니다");
						
						alert("${LANGDATA.member_inputname}");
						return;

					}

					if (nickname == "") {
						// alert("닉네임을 입력해 주시기 바랍니다");
						alert("${LANGDATA.member_inputnickname}");
						return;
					}

					if (email == "") {
						// alert("이 메일을 입력해 주시기 바랍니다.");
						alert("${LANGDATA.member_inputemail}");
						return;
					}

					if (tel == "") {
						//alert("전화 번호를 입력해 주시기 바랍니다.");
						alert("${LANGDATA.member_inputtel}");
						return;
					}

					if (id == "") {
						alert("${LANGDATA.member_inputid}");
						return;
					}
					
					if (password =="") {
						alert("${LANGDATA.member_inputpassword}");
					}
					if (password != repassword) {
						// alert("password 는 일치 해야 합니다. ")
						alert("${LANGDATA.member_samepassword}");
						return;
					}
					
					if (!checkpassword(password))
					{						
						alert("${LANGDATA.member_badpassword}");
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
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, pre, form,
	fieldset, input, textarea, p, blockquote, th, td {
	padding: 0;
	margin: 0;
}

fieldset, img {
	border: 0
}

ol, ul, li {
	list-style: none
}

:focus {
	outline: none
}

body, input, textarea, select {
	font-family: 'Open Sans', sans-serif;
	font-size: 16px;
	color: #4c4c4c;
}

p {
	font-size: 12px;
	width: 150px;
	display: inline-block;
	margin-left: 18px;
}

h1 {
	font-size: 32px;
	font-weight: 300;
	color: #4c4c4c;
	text-align: center;
	padding-top: 10px;
	margin-bottom: 10px;
}

html {
	background-color: #ffffff;
}

.testbox {
	margin: 20px auto;
	width: 343px;
	height: 464px;
	-webkit-border-radius: 8px/7px;
	-moz-border-radius: 8px/7px;
	border-radius: 8px/7px;
	background-color: #ebebeb;
	-webkit-box-shadow: 1px 2px 5px rgba(0, 0, 0, .31);
	-moz-box-shadow: 1px 2px 5px rgba(0, 0, 0, .31);
	box-shadow: 1px 2px 5px rgba(0, 0, 0, .31);
	border: solid 1px #cbc9c9;
}

input[type=radio] {
	visibility: hidden;
}

form {
	margin: 0 30px;
}

label.radio {
	cursor: pointer;
	text-indent: 35px;
	overflow: visible;
	display: inline-block;
	position: relative;
	margin-bottom: 15px;
}

label.radio:before {
	background: #3a57af;
	content: '';
	position: absolute;
	top: 2px;
	left: 0;
	width: 20px;
	height: 20px;
	border-radius: 100%;
}

label.radio:after {
	opacity: 0;
	content: '';
	position: absolute;
	width: 0.5em;
	height: 0.25em;
	background: transparent;
	top: 7.5px;
	left: 4.5px;
	border: 3px solid #ffffff;
	border-top: none;
	border-right: none;
	-webkit-transform: rotate(-45deg);
	-moz-transform: rotate(-45deg);
	-o-transform: rotate(-45deg);
	-ms-transform: rotate(-45deg);
	transform: rotate(-45deg);
}

input[type=radio]:checked+label:after {
	opacity: 1;
}

hr {
	color: #a9a9a9;
	opacity: 0.3;
}

input[type=text], input[type=password] {
	width: 200px;
	height: 39px;
	-webkit-border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
	-moz-border-radius: 0px 4px 4px 0px/0px 0px 4px 4px;
	border-radius: 0px 4px 4px 0px/5px 5px 4px 4px;
	background-color: #fff;
	-webkit-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	-moz-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	border: solid 1px #cbc9c9;
	margin-left: -5px;
	margin-top: 13px;
	padding-left: 10px;
}

input[type=password] {
	margin-bottom: 25px;
}

#icon {
	display: inline-block;
	width: 30px;
	background-color: #3a57af;
	padding: 8px 0px 8px 15px;
	margin-left: 15px;
	-webkit-border-radius: 4px 0px 0px 4px;
	-moz-border-radius: 4px 0px 0px 4px;
	border-radius: 4px 0px 0px 4px;
	color: white;
	-webkit-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	-moz-box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	box-shadow: 1px 2px 5px rgba(0, 0, 0, .09);
	border: solid 0px #cbc9c9;
}

.gender {
	margin-left: 30px;
	margin-bottom: 30px;
}

.accounttype {
	margin-left: 8px;
	margin-top: 20px;
}

a.button {
	font-size: 14px;
	font-weight: 600;
	color: white;
	padding: 6px 25px 0px 20px;
	margin: 10px 8px 20px 0px;
	display: inline-block;
	float: right;
	text-decoration: none;
	width: 50px;
	height: 27px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	background-color: #3a57af;
	-webkit-box-shadow: 0 3px rgba(58, 87, 175, .75);
	-moz-box-shadow: 0 3px rgba(58, 87, 175, .75);
	box-shadow: 0 3px rgba(58, 87, 175, .75);
	transition: all 0.1s linear 0s;
	top: 0px;
	position: relative;
}

a.button:hover {
	top: 3px;
	background-color: #2e458b;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
}
</style>

</head>
<body>
	<div id='EduContainer' align="center">
		<h2>${LANGDATA.member_joinmember}</h2>
		<br>
		<form id="target" method="post"
			action="../member/joinmemberprocess.do">

			<input type="hidden" id="mem_pass" name="mem_pass"/ >
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" id="mem_id" name="mem_id"
						placeholder="${LANGDATA.member_idinputmsg}">
						<div id="checkid"></div></td>
				</tr>

				<tr>
					<td>${LANGDATA.member_passwordstr}</td>
					<td><input type="password" id="password" placeholder="${LANGDATA.member_passwordstr}"
						title="Password must contain at least 6 characters, including UPPER/lowercase and numbers"
						type="text" required
						pattern="^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{7,40}$"
						onchange="this.setCustomValidity(this.validity.patternMismatch ? this.title : '');  ">

					</td>
				</tr>

				<tr>
					<td>${LANGDATA.member_verypasswordstr}</td>
					<td><input type="password" id="repassword"
						placeholder="${LANGDATA.member_verypasswordstr}"></td>
				</tr>



				<tr>
					<td>${LANGDATA.member_name}</td>
					<td><input type="text" id="mem_name" name="mem_name"
						placeholder="${LANGDATA.member_name}"></td>
				</tr>

				<tr>
					<td>${LANGDATA.member_nickname}</td>
					<td><input type="text" id="mem_nick" name="mem_nick"
						placeholder="${LANGDATA.member_nickname}">
						<div id="checknickname"></div></td>
				</tr>

				<tr>
					<td>${LANGDATA.member_tel}</td>
					<td><input type="tel" id="mem_tel" name="mem_tel"
						placeholder="${LANGDATA.member_tel}"></td>
				</tr>

				<tr>
					<td>E-MAIl</td>
					<td><input type="email" id="mem_email" name="mem_email"
						placeholder="e-mail"></td>
				</tr>

				<tr>

					<td>${LANGDATA.member_addr}</td>
					<td><input type="text" id="mem_addrCode" name="mem_addrCode"
						placeholder=" ${LANGDATA.member_post}"> <input type="button" id="addrfind"
						value="${LANGDATA.member_postfind}"><br> <input type="text" id="mem_addr"
						name="mem_addr" id="mem_addr" placeholder="${LANGDATA.member_addr}"> <input
						type="text" id="mem_detailaddr" name="mem_detailaddr"
						placeholder="${LANGDATA.member_addrmore}"></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="button"
						id="register" value="${LANGDATA.member_reg}"> <input type="button" id="cancel"
						value="${LANGDATA.member_cancel}"></td>
				</tr>

			</table>
		</form>

		
			<div class="testbox">
				<h1>회원가입</h1>

				<form id="target" method="post"
					action="../member/joinmemberprocess.do">
					<hr>					
					<hr>
						<label id="icon" for="name"><i class="icon-envelope "></i></label>
						<input type="text" name="name" id="name" placeholder="Email"	required /> 
						<label id="icon" for="name"><i	class="icon-user"></i></label> 
						<input type="text" name="name" id="name" 	placeholder="Name" required /> 
						<label id="icon" for="name"><i	class="icon-shield"></i></label> 
						<input type="password" name="name"id="name" placeholder="Password" required />
					
					<button class="btn btn-primary btn-block btn-flat" id="register">가입하기</button>
				</form>
			</div>

		

	</div>

</body>
</html>