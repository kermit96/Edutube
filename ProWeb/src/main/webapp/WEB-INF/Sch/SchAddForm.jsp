<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>EduTube</title>
	
	<!--  Favicon (Main Icon) -->
	<link rel="shortcut icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	<link rel="icon" href="/edutube/favicon.ico" type="image/x-icon"/> 
	
	<!--CSS-->
 	<link rel="stylesheet" href="/edutube/resources/CSS/customB.css">
	<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>
	<link rel="stylesheet" href="/edutube/resources/CSS/bootstrap.min.css">
	 <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
	<link rel="stylesheet" href="../resources/Picker/bootstrap-material-datetimepicker.css">
	
	<!--JS-->
	<script src="../resources/JS/jquery-2.1.4.min.js"></script>
	<script src="../resources/JS/bootstrap.min.js"></script>
	
	
	
		
	<!--CustomScript-->
	<script>
			
		$(document).ready(	function() {

			$("#saveE").click(function() {
				
				 var startDate = $("#sdate").val();
				 var endDate = $("#edate").val();
				 var arr1 = startDate.split('-');
				 var arr2 = endDate.split('-');
				 var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
				 var dat2 = new Date(arr2[0], arr2[1], arr2[2]);
				 				 
				 var diff = dat2 - dat1;
				 var currDay = 24 * 60 * 60 * 1000;
				 
				 var difday = parseInt(diff/currDay);
													
					$eventname = $("#eventname").val();
						if ($eventname == "") {
							alert("일정을 입력해 주세요");
							return;
					}
						
					if(difday<0){
						alert("종료 날짜를 확인해 주세요 :)");
						return;
					}	

					$("#eventForm").attr("action",
									"../Sch/SchAdd.do?nowPage=${nowPage}&oriNo=${oriNo}");
					$("#eventForm").submit();
				});
	});
	</script>
	
	<!--Never Delete "EduContainer" style tag-->
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
		#clock{			
			height:200px;
			margin: 0 auto;
		}
		.eventD{
			width:600px;			
		}
		#eventTop{
			padding:10px;
		}
		input#eventname{
			width:350px;			
		}
		#eventFormD{
			padding:20px;
			border-bottom:2px solid #91B1E8;
		}
		#eventAddD{
			margin:0 auto;
			text-align:right;
		}
		
	</style>
	
</head>
<body>

<div id='EduContainer'>

	<div id='top'>	
		<jsp:include page="/MenuBar/Top.jsp" flush="false" />
	</div>		
		
	<div id="main">
		<div id="sideBarDiv">
			<jsp:include page="/MenuBar/IntroSide.jsp" flush="false" />
		</div>
	
		<!-- This area is Body Part -->
		<div id="centerPage">
			<div id="realContent">
				
				<div id="eventFormD" class="eventD">
					<form class="form-horizontal" id="eventForm" name="eventForm" method="POST">
						<fieldset>
						<!-- Form Name -->
							<legend>일정 추가</legend>
							<input id="mid" name="mid" value="${sessionScope.ID}" type="hidden" />
							
							<!-- Text input-->
							<div class="control-group">
							  <label class="control-label" for="eventname">일정</label>
							  <div class="controls">
							    <input id="eventname" name="eventname" type="text" placeholder="" class="input-xXlarge" required>							    
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="control-group">
							  <label class="control-label" for="sdate">시작 날짜</label>
							  <div class="controls">
							    <input id="sdate" name="sdate" type="text" placeholder="" class="input-xlarge" readonly required>							    
							  </div>
							</div>
							
							<!-- Text input-->
							<div class="control-group">
							  <label class="control-label" for="edate">종료 날짜</label>
							  <div class="controls">
							    <input id="edate" name="edate" type="text" placeholder="" class="input-xlarge"  readonly required>							    
							  </div>
							</div>
							
						</fieldset>
					</form>
				</div>				
									
				<div id="eventAddD" class="eventD">
					<a class="button button-purple"  id="saveE" ><i class="fa fa-rocket"></i>
					일정추가</a>								
				</div>
			</div>
		</div>		
	</div>
	 
</div>

<!--  피커 -->
<script src="../resources/JS/moment-with-locales.min.js"></script>
<script src="../resources/JS/bootstrap-material-datetimepicker.js"></script>
	<script type="text/javascript">
	$(function()
			{				
				$('#sdate').bootstrapMaterialDatePicker				
				({						
					weakStart:0,
					time: false
				});
				$('#edate').bootstrapMaterialDatePicker				
				({						
					weakStart:0,
					time: false
				});
			});
	</script>

</body>
</html>





