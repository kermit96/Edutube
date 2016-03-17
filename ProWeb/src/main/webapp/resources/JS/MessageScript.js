/**
 * 	메세지가 있으면 보여주는 JS
 */
function msg(){
	if(id==null|| id==""){
			
	}
		
	else if(id != null){
		window.open("/edutube/Message/MsgList.do?id="+id,"EduTube","width=200, height=260, resizable=no, toolbar=no, location=no,scrollbars=yes");
	}
//	setTimeout(msg, 7000);
}