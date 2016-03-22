<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>EduTube | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/edutube/resources/CSS/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/edutube/resources/CSS/blue.css">
	<!--  파피콘 넣기 -->
	<link rel="shortcut icon" href="../favicon.ico" type="image/x-icon" />
	<link rel="icon" href="../favicon.ico" type="image/x-icon" />
  
    <!-- jQuery 2.1.4 -->
    <script src="/edutube/resources/JS/jquery-2.1.4.min.js"></script>
  	<script type="text/javascript" src="../resources/JS/sha256.js"></script>
  	 <script type="text/javascript" src="../resources/JS/Link.js"></script>
  	
  	<script>
	$(document).ready(function() {

		$("#login").click(login);

//		$("#makeid").click(make);

//		$("#findmember").click(findmember);

	});

	function makeid() {

		location.href = "../member/joinmember.do";
		 // window.open('../member/joinmember.do', "_blank" );
	}
 
	function findmember() {

		window.open('../member/findmember.do', "findmember","width=460, height=400,toolbar=no");
 
	}

	function login() {

		var userid = $("#userid").val().trim();
		var password = $("#password").val().trim();
		
		if (userid == "") {

           	alert("${LANGDATA.member_inputid}");
			return;
		}
		
        if (password == "") 
        {			
		   	// alert("password 를 입력해 주시기 바랍니다.");
		   	alert("${LANGDATA.member_inputpassword}");
			return;
		}
		
        try {
          password = Sha256.hash(password);
        } catch (ex) {        	
        	alert(ex);	
        }
        

        try {
        $.ajax({
            url:"../member/ajax/login.do",
            async:false,
            type:'post',
            dataType:'json',
            cache:false,
            data:{userid:userid,password:password  },
            success:function(data){
            	 if (data.isSuccess)
                 {   
            		 location.href="${returnurl}";            		 
            	 } else {            		 
            		 alert(data.failreason);
            	 }            	              
            },
            error:function(request,status,error){
                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
               }
            	                                    
        });
		
        } catch (ex) {        	
        	alert("ajax="+ex);
        }
	}
</script>
  
  </head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="../main.do"><b>Edu</b>Tube</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">${LANGDATA.member_loginmsg}</p>
       	<!--  아이디 -->
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="YourID" id="userid">
            <span class="fa  fa-user form-control-feedback"></span>
          </div>
          <!--  비밀번호 -->
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" id="password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
          
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button class="btn btn-primary btn-block btn-flat" id="login">${LANGDATA.member_login} </button>
            </div><!-- /.col -->
          </div>
             

        <a href="JavaScript:findmember()" id="findmember">${LANGDATA.member_idpasswordfind} </a><br>
        <a href="JavaScript:makeid()" class="text-center" id="makeid">${LANGDATA.member_join}</a>

      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

  
    <!-- Bootstrap 3.3.5 -->
    <script src="/edutube/resources/JS/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="/edutube/resources/JS/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>

