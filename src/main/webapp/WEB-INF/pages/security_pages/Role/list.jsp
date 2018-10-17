<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@include file="../../base_pages/base.jsp"%>		<link rel="stylesheet" type="text/css" href="../css/dqwz.css">	
	</head>
	<body>
	<div id="dqwz">
<p>当前位置：权限管理&nbsp;〉&nbsp;角色管理</p>
<p class="line"></p>
</div>	

	<div id="content">
	<div class="biaoti"><p><b>角色列表</b></p></div>
	<div class="biao">	
	
		<form action="list.do">
			<table class="grid">
				<tr>
					<td class="lgridlist" width="150"><strong>角色名称：</strong></td>
					<td><input type="text" name="roleName" value="${role.roleName}" maxlength="32"/></td>
					<td class="lgridlist" width="150"><strong>描述：</strong></td>
					<td><input type="text" name="dsc" id="dsc" value="${role.dsc}" maxlength="32"/></td>
				</tr>
			</table>
			<table class="b">
				<tr>
				   	<td width="200px"></td>
					<td><input type="submit" value="查  询" ></td>
					<td><input type="button" name="clear" value="重  置" onclick="location.href='list.do'"></td>
					<td><input type="button" name="create" value="新 增" onclick="location.href='insert.do'" /></td>
				</tr>
			</table>
		</form>
		</div>
		
		<div class="form">
		<table id="adminRole" class="grid its">
			<thead>
				<tr>
					<th>角色名称</th>
					<th>描述</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${roleList}"  var="role"  varStatus="status">
				<c:if test="${status.index%2==0}">
				<tr class="odd">
				</c:if>
				<c:if test="${status.index%2==1}">
				<tr class="even">
				</c:if>
					<td>${role.roleName } </td>
					<td>${role.dsc}</td>
					<td>
						<a href="edit.do?roleId=${role.roleId}">编 辑&gt;&gt;|</a>
						<a href="delete.do?roleId=${role.roleId}" onclick="return delRole('${role.roleId}');">删  除&gt;&gt;</a>
						<a href="frame.do?roleId=${role.roleId}">权限分配&gt;&gt;</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</div>
		
		
		
		
		<script type="text/javascript">
			/**
				删除机构,判断是否关联用户分组或权限
			*/
			function delRole(roleId){
				
				//关联用户分组和权限数量
				var refUGnum = 0;
				dojo.xhrGet({
					url: "AJAX_beforeDelete.do",
					sync: true,
					content: {roleId: roleId, t: new Date().getTime()},
					load: function(data){
						refUGnum = data;
					},
					error: function(error){
						alert(error);
					}
				});
				
				if(refUGnum > 0){
					alert("该角色已关联数据,删除失败!");
					return false;
				}
				//删除
				return confirm("确认执行[删除]操作?");
			}
		</script>
		</div>
	</body>
</html>


