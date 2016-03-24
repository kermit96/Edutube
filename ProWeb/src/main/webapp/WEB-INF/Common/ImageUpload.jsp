<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>이미지 업로드</title>
		<script src="/edutube/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
		<script src="/edutube/resources/smarteditor/sample/photo_uploader/attach_photo.js" charset="utf-8"></script>
		<script>
			//	할일
			//		넘어온 파라메터를 이용해서 이미지 경로를 지정하고
			var res =[ {sFileName :"${name}",
				sFileURL :"http://192.168.56.103:8080/edutube/${path}/${name}",
		        bNewLine : true }];
			//		setPhotoToEditor()를 이용해서 이미지를 표시한다.
		    setPhotoToEditor(res);			
			//		자신을 닫아준다.
			self.close();
		</script>
	</head>
</html>