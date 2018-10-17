<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<html>
<body>
	<h2>Hello World FROM! FileUpload</h2>

	<form method="post" action="upload"  enctype="multipart/form-data">
            文件：<input type="file" name="file1"/>
        <input type="submit" value="上传"/>
      </form>

</body>
</html>