<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>Insert title here</title>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
</head>

<body>


<table  border="0" cellspacing="0" cellpadding="0" style="background:url(../images/mid_tiao.jpg) repeat-y top; width:34px;" height="600" >

  <tr style="CURSOR: pointer；">
     <TD id=menuSwitch style="CURSOR: pointer；" vAlign=center >
	     <div class="mid_btn" id="Layer1" onClick="changeFrame();" style="CURSOR: pointer; position:absolute; height:10%; z-index:1; left: 0px; top: 45%;">
	     	<img id="buttonImg" src="../images/mid_btn.jpg"  />
	     </div>
     </TD>
  </tr>
</table>

</body>

<script language="JavaScript" type="text/JavaScript">
var flag=1;

function changeFrame()
{
	if(flag==1)
	{
		 top.document.getElementById("bottomSet").cols="0,34,*";
		 document.getElementById("buttonImg").src="../images/mid_btn.jpg";
		flag=2;
	}else{
		top.document.getElementById("bottomSet").cols="188,34,*";
		document.getElementById("buttonImg").src="../images/mid_btn.jpg";
		flag=1;
	}
}
</script>
</html>
