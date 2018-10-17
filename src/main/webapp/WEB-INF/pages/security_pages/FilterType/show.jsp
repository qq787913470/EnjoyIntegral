<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link rel="stylesheet" type="text/css" href="../css/site.css">
<link rel="stylesheet" type="text/css" href="../css/screen.css">
<link rel="stylesheet" type="text/css" href="../css/table.css">
</head>
<body>
	<fieldset>
		<legend>
			控制类型查看
		</legend>
		<table class="grid">
			<tr>	
				<td class="lgridbiaoti">
					名称：
				</td>
				<td>
					${filterType.typeName}
				</td>
			</tr>
		</table>
		<table height="35" class="b">
		<tr>
			<td width="30%">
			   <input type="button" name="cancel" value="返  回" onclick="javascript:location.href='list.do'">
			</td>
		</tr>
	</table>
	</fieldset>
</body>
</html>