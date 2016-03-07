<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="../se2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<script src="../se2/sample/photo_uploader/attach_photo.js" charset="utf-8"></script>
	<script>
			//	할일
			//		넘어온 파라메터를 이용해서 이미지 경로를 지정하고
			var res =[ {sFileName :"${name}",
				sFileURL :"http://localhost:8080/mainApp/${path}/${name}",
		        bNewLine : true }];
    
			//		setPhotoToEditor()를 이용해서 이미지를 표시한다.
		    setPhotoToEditor(res);			
			//		자신을 닫아준다.
			self.close();
	</script>
	</head>
</html>