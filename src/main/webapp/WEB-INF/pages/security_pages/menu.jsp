<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags"  prefix="s" %>   
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="mvc"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
  <meta http-equiv='Content-Type' content='text/html; charset=gb2312'>
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
		if(span[i].className == 'f12'){
			span[i].style.color = '#7b792a';
		}
		
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
	color: #7b792a;
	font-size: 12px;
	font-family: Arial, Helvetica, sans-serif;
	font-weight: bold;
}
.f12 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	line-height: 25px;
	color: #7d7c46;
	text-decoration: none;
}
-->
</style>
</head>


<body>
<table height="100%"  width="188" border="0" cellpadding="0" cellspacing="0">
 
  <tr>
    <td width="188" valign="top" bgcolor="#F4F3CD" >

     <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
              <tr>
                <td height="4">
	  
	  
	   <c:forEach items="${menus}" var="menu" varStatus="status">
	   
		<table cellpadding="0" cellspacing="0" width="177" align="center">
			<tr id="FrameTr">
				<td width="177" height="30" align="left" valign="middle"
					id="menuTitle1"
					style="cursor: hand; background-image: url(../images/nav_btn_bg.jpg); background-repeat: repeat;background-position: center;"
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
								
								<c:if test="${submenu.isExecution==1 }">
								<tr>
									<td height="24">
									<img src="../images/point.jpg" >
											<a href="${submenu.actionName}"  target="main"><span class="f12" onclick="test(this);"><c:out value="${submenu.menuName}" /></span></a>
									</td>
								</tr>
								</c:if>
								
								<c:if test="${submenu.isExecution==2 }">
								<tr>
									<td height="24">
									<img src="../images/noadd2_1.gif" >
									<a target="main" title="不可操作"><span class="f12"><c:out value="${submenu.menuName}" /></span></a>
									</td>
								</tr>
								</c:if>
								
							</c:forEach>
							
						</table>
					</div></br>
				</td>
			</tr>
		</table>
		
	</c:forEach></td>
              </tr>
            </table>

      </table>
      
</td>
</tr>
</table>

  </td>    
  </tr>
</table>
</body>
<html>

