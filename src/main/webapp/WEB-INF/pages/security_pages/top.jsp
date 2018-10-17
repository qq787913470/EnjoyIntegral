<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags"  prefix="s" %>   
<%@taglib uri="http://www.springframework.org/tags/form"  prefix="mvc"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
	<meta http-equiv='Content-Type' content='text/html; charset=gb2312'>

	
<link rel="stylesheet" type="text/css" href="../css/top.css">


	<script language="javascript">
		function mouseOn(x){
			x.className = "focus";
		}

		function mouseOff(x){
			x.className = "plane";
		}
		function home()
		{
			window.parent.frames["main"].location.href="main.do";
		}
		function logout()
		{
			document.location.href="${pageContext.request.contextPath}/login/logout.do";
		}
		function resetPassWord(userId)
		{
			window.open("${pageContext.request.contextPath}/user/updateUserPwd.do?userId="+userId,"main");
		}

		function lgoClo()
		{
			top.opener=null;
			top.close();

			window.onbeforeunload = function()   
			{   
				document.location.href="Account.do?method=close";
			};
		}
		
	</script>
	
</head>

<body>
		<div id="header">
			<div id="header_inner">
				<ul>
					<li class="logo"><img src="../images/logo_nei.jpg"/></li>
					<li class="title"><img src="../images/title_nei.png"/></li>
				</ul>
			</div>	
			<div id="pingtai">
				<p></p>
		    </div>	
			<div id="yonghu_name">	
				<table>
					<tr>
						<td><img src="../images/yonghu.jpg"/>
						<td>当前登录用户:<shiro:principal/> &nbsp;&nbsp;</td>
						<td>用户角色:${curUser.roleName}&nbsp;&nbsp;</td>
						<td><a href="#" onclick="resetPassWord(${curUser.userId})">修改密码</a></td>
						<td width="30px"></td>
						<td><img src="../images/zhuxiao.jpg"/></td>
						<td><a href="#" class="tuichu" onclick="logout()">注销</a></td>
						<td width="20px"></td>
						<td><img src="../images/tuichu.jpg"/></td>
						<td><a href="#"  class="tuichu" onclick="lgoClo();">退出</a></td>
					</tr>	
				</table>
            </div>
            </div>
</body>
</html>

