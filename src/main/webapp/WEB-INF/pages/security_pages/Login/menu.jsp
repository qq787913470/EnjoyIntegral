<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags"  prefix="s" %>   
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="mvc"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=gb2312'>
	<link rel="stylesheet" type="text/css" href="css/site.css">
<SCRIPT language="javascript1.2">
<!--
function showsubmenu(sid){
    whichEl = eval('submenu' + sid);
    if (whichEl.style.display == 'none'){
	hideAll(sid);
        eval("submenu" + sid + ".style.display='';");
    }
    else{  
        eval("submenu" + sid + ".style.display='none';");
    }
}
function hideAll(sid){
  for(i=1;i<document.all.FrameTr.length+1;i++){   
  if(i!=sid){
  eval("submenu" + i + ".style.display='none';");
     }
  }
}

function test(c){
	var span = document.getElementsByTagName("span");
	for(var i = 0;i < span.length;i++){
		span[i].style.color = '#2952A1';
	}
	c.style.color = 'red';
}
//-->
</SCRIPT>
<SCRIPT LANGUAGE="javascript">
function wh_showStatus(){
if (wh_StatusType == "left") {
wh_StatusText = " " + wh_StatusText;
}
else
if (wh_StatusType == "right") {
wh_StatusText = wh_StatusText.substring(1,wh_StatusText.length);
}
if (wh_StatusText == "" || wh_StatusText.length > 80) {
wh_StatusText = "权限平台";
if (wh_StatusType == "right") {
while (wh_StatusText.length < 80) wh_StatusText = " " + wh_StatusText; 
}
}
status = wh_StatusText;
setTimeout("wh_showStatus()",wh_StatusSpeed);
}
wh_StatusText = "欢迎使用Shiro权限";
wh_StatusSpeed = 100;
wh_StatusType = "left";
if (wh_StatusType == "right") {
while (wh_StatusText.length < 80) wh_StatusText = " " + wh_StatusText; 
}
</SCRIPT>
	<style type="text/css">
<!--
.style1 {
	color: #2952A1;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.f12 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 25px;
	color: #2952A1;
	text-decoration: none;
}
-->
</style>
</head>
<body style="background-color: 92CEE6;">
<table height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="223" valign="top" bgcolor="D9E6F5">
    
<table height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="223" valign="top" bgcolor="D9E6F5"><table width="209" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:8px;">
      <tr>
        <td height="62" valign="top" background="../images/mianban_c.gif" style="font-size:18px;font-weight:bold;padding-left:90px;padding-top:20px;">权限平台</td>
      </tr>
    </table>
	<table width="210" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;">
        <tr>
          <td width="210"><img src="../images/matebox_topimg.gif"  /></td>
        </tr>
        <tr>
          <td background="../images/matebox_bg.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="4">
	   <c:forEach items="${menus}" var="menu" varStatus="status">
		<table cellpadding="0" cellspacing="0" width="156" align="center">
			<tr id="FrameTr">
				<td width="156" height="30" align="left" valign="middle"
					id="menuTitle1"
					style="cursor: hand; background-image: url(../images/menu_bg.gif); background-repeat: repeat;background-position: center;"
					onclick="showsubmenu(<c:out value="${status.index + 1}" />)">
					<span class="style1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${menu.menuName}" />
					</span>
				</td>
			</tr>
			<tr>
				<td style='display: none' id='submenu<c:out value="${status.index + 1}" />'>
					<div style='width: 158'>
						<table cellpadding="0" cellspacing="0" align="center" width="132">
							<c:forEach items="${menu.children}" var="submenu" varStatus="s">
								<tr>
									<td height="24">
									  <c:if test="${submenu.isExecution>=2 }">
										<img src="../images/noadd2_1.gif" >
										<a  target="main" title="不可操作"><span class="f12"><c:out value="${submenu.menuName}" /></span></a>
										</c:if>
										<c:if test="${submenu.isExecution==1 }">
										<img src="../images/noadd2.gif" >
										<a href="${submenu.actionName}"  target="main"><span class="f12" onclick="test(this);"><c:out value="${submenu.menuName}" /></span></a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</table>
					</div></br>
				</td>
			</tr>
		</table>
	</c:forEach></td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td><img src="../images/matebox_botimg.gif" width="210" height="9" /></td>
        </tr>
      </table>
      
      <table width="208" height="60" border="0" align="center" cellpadding="0" cellspacing="0" background="../images/mategs_bg.gif" style="margin-top:15px;">
        <tr>
          <td align="center" style="font-size:12px;color:#ffffff;">版权所有：中国银行<br>
</td>
        </tr>
      </table></td>
    
  </tr>
</table>

  </td>    
  </tr>
</table>
</body>
<html>

