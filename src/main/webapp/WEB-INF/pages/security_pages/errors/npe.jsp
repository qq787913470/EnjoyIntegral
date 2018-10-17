<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK" isErrorPage="true"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>系统</title>
<link href="../css/index.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.f14 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	font-style: normal;
	line-height: 30px;
	font-weight: normal;
	color: #000000;
}
-->
</style>

<link href="../css/yichang.css" rel="stylesheet" type="text/css" />
</head>


<body>
	<div id="yichang_bg">
		<div id="yichang_bg_inner">

	<div id="text_yichang">
		<div id="text-yichang_inner">
			
			
			<p><b>系统异常：</b>系统错误，请联系开发商。</p>
			
			<p><b>异常信息：</b><%=exception%></p>
			
		</div>
		<div class="line">
		</div>
	</div>

	<div class="fanhui">
		<input id="btn" name="submit" type="button" value="返回" onclick="history.back()" style="width:85px;height:30px;"/>
	</div>

	</div>
	</div>


</body>
</html>
