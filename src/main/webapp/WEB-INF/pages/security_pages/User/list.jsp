<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<!DOCTYPE head PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户 维护</title>
		<base target="_self"></base>
		<script type="text/javascript" src="<c:url value='/js/jquery-1.4.1.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/application.js'/>"></script>
		<script type="text/javascript" >
			
			/**
				批量关闭用户
			*/
			  function closeUsers() {
				    var form = document.forms.queryForm;
					if(!form) return;
					if (!hasOneChecked('userIds')){
		               alert('请选择要操作的对象!');
		               return;
		             }
			        if (confirm('确定执行[关闭]操作?')){
				        form.action = 'batchCloseUser.do';
			            form.submit();
			        }
			  }
			  
			/**
				批量开启用户
			*/
			  function openUsers() {
				    var form = document.forms.queryForm;
					if(!form) return;
					if (!hasOneChecked('userIds')){
		               alert('请选择要操作的对象!');
		               return;
		             }
			        if (confirm('确定执行[开启]操作?')){
				        form.action = 'batchOpenUser.do';
			            form.submit();
			        }
			  }
			
			/**
				机构联动下级机构
				@param 机构ID
			*/
			function findlowerOrg(orgId){
				if(orgId){
					//alert(orgId);
					//清空select,添加[--请选择--]option
					var node = dojo.byId("orgId2");
					dojo.empty(node);
				
					/*---------------创建元素节点--------------*/
					dojo.create("option", {
						value : "",
						innerHTML : "--请选择--"
					}, node);
					
					dojo.xhrGet({
						url: "AJAX_findlowerOrg.do",
						sync: true,
						content: {orgId: orgId, t: new Date().getTime()},
						load: function(data){
							//用户名可用
							if("" != data){
								//解析用户分组
								dojo.forEach(dojo.fromJson(data), function(org){
									dojo.create("option", {
										value : org.orgId,
										innerHTML : org.name
									}, node);
								});
							}
						},
						error: function(error){
							alert(error);
						}
					});
					
					//findGrpByOrg(orgId);
				}
			}
			/**
				机构联动用户分组
				@param 机构ID
			*/
			function findGrpByOrg(orgId){
				if(orgId){
					
					//清空select,添加[--请选择--]option
					var node = dojo.byId("userGrpId");
					dojo.empty(node);
				
					/*---------------创建元素节点--------------*/
					dojo.create("option", {
						value : "",
						innerHTML : "--请选择--"
					}, node);
					
					dojo.xhrGet({
						url: "AJAX_findGrpByOrg.do",
						sync: true,
						content: {orgId: orgId, t: new Date().getTime()},
						load: function(data){
							//用户名可用
							if("" != data){
								//解析用户分组
								dojo.forEach(dojo.fromJson(data), function(userGrp){
									dojo.create("option", {
										value : userGrp.userGrpId,
										innerHTML : userGrp.name
									}, node);
								});
							}
						},
						error: function(error){
							alert(error);
						}
					});
				}
			}
			
			  /**
			  	确认重置密码
			  	@param:nameId,登录用户名显示列Id,loginName_userId
			  **/
			  function confirmReset(nameId){
				  
				  var form = document.forms.queryForm;
				  
				  //用户名
				  var name = dojo.byId(nameId).innerHTML;
				  var msg = "确定重置用户:" + name + "的密码(重置后密码:111111)?";
				 
				 //重置
		         if(confirm(msg)){
		        	 //添加userId
		        	 dojo.create("input", {
		        		type : "hidden",
						value : nameId.split("_")[1],
						name : "userId"
					 }, form);
		        	 form.action = "resetUserPwd.do";
		        	 form.submit();
		         }
			  }
			
		</script>
		<link rel="stylesheet" type="text/css" href="../css/dqwz.css">
	</head>
	
	<body>

<div id="dqwz">
<p>当前位置：权限管理&nbsp;〉&nbsp;用户管理</p>
<p class="line"></p>
</div>	

			
			<mvc:form id="queryForm" name="queryForm" action="list.do" method="get" commandName="query">
		
<div id="content">
<div class="biaoti"><p><b>用户查询</b></p></div>
<div class="biao">	
		
					<input type="hidden" name="queryString" value="<mytag:params includes="s_*" type="queryStringUtf" />" />


					<table class="grid">
					
				      	<tr>	
				      	
							<td class="lgridlist">机构</td>		
							<td>
								<mvc:select path="orgId" id="orgId" onchange="findlowerOrg(this.value);">
						    		<mvc:option value="">--请选择--</mvc:option>
						    		<mvc:options items="${orgs}" itemValue="orgId" itemLabel="name"/>
						    	</mvc:select>
								<mvc:select path="orgId2" id="orgId2">
						    		<mvc:option value="">--请选择--</mvc:option>
						    		<mvc:options items="${lowerOrgs}" itemValue="orgId" itemLabel="name"/>
						    	</mvc:select>
							</td>
		
							
						   <%-- <td class="lgridlist">用户分组</td>
							<td>
								<mvc:select path="s_userGrpId" id="userGrpId">
						    		<mvc:option value="">--请选择--</mvc:option>
						    		<mvc:options items="${userGrps}" itemValue="userGrpId" itemLabel="name"/>
						    	</mvc:select>
							</td>--%>
						</tr>	
				      	<tr>	
				      		
						    <td class="lgridlist">用户登录名</td>		
							<td>
								<input value="${query.loginName}" name="s_loginName" maxlength="16"  />
							</td>
							
							<%--<td class="lgridlist">状态</td>
							<td>
								<mytag:select dictName="D_USER_STATUS" name="s_status" value="${query.status}" notEmpty="false"></mytag:select>
							</td>--%>
		
						</tr>	
						
					</table>
					
					<table class="b">
						<tr >
						<td width="150px"></td>
							<td><input type="submit"  style="width:80px" value="查询" onclick="getReferenceForm(this).action='list.do'"/></td>
							<td><input type="reset" style="width:80px" value="重置" /></td>
							<td><input type="button"  style="width:80px" value="新增" onclick="window.location.reload('insert.do')"/></td>
							<td><input type="button"  style="width:80px" value="启用" onclick="openUsers();"/></td>
							<td><input type="button"  style="width:80px" value="停用" onclick="closeUsers();"/></td>
						</tr>
					</table>
			</div>
			<div class="form">	
			
			<div id="eXtremeTable" class="eXtremeTable">
			
				
				<table class="grid its">
				  <thead>
					  
					  <tr>
					  	<th><input type="checkbox" onclick="setAllCheckboxState('userIds',this.checked)" /></th>
						<th>机构</th>
						<th>用户分组</th>
						<th>用户登录名</th>
						<th>用户姓名</th>
						<th>最后登录IP</th>
						<th>状态</th>
						<th>操作</th>
					  </tr>
					  
				  </thead>
				  <tbody>
				  	<c:if test="${page.totalCount == 0}">
				  		 <tr class="even">
				  			 <td align="center">没有数据</td>
				  			 <td align="center">&nbsp;</td>
				  			 <td align="center">&nbsp;</td>
				  			 <td align="center">&nbsp;</td>
				  			 <td align="center">&nbsp;</td>
				  			 <td align="center">&nbsp;</td>
				  			 <td align="center">&nbsp;</td>
				  			 <td align="center">&nbsp;</td>
				  		</tr>
				  	</c:if>
				  
				  	  <c:forEach items="${page.result}" var="item" varStatus="status">
				  	  
					  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						
						<td><input type="checkbox" name="userIds" value="${item.userId}" />&nbsp;</td>
						<td><c:out value='${item.userExt.orgName}'/></td>
						<td><c:out value='${item.userGrp.name}'/></td>
						<td id="loginName_${item.userId }"><c:out value='${item.loginName}'/></td>
						<td><c:out value='${item.userName}'/></td>
						<td><c:out value='${item.userExt.lastLoginIpAddr}'/></td>
						<td><mytag:write dictName="D_USER_STATUS" value="${item.userExt.status}"></mytag:write>&nbsp;</td>
						<td align="center">
							&nbsp;&nbsp;<a href="show.do?userId=${item.userId}&<mytag:params includes="s_*" type="queryStringUtf" />">查看</a>
							<!-- 上级机构用户管理下级用户(canMnged == '1'),本身不可管理本身及同行用户,超级管理员(userGrpId == '1')可管理所有用户 -->
							<c:if test="${item.userExt.canMnged == '1' || userGrpId == '1' }">
							&nbsp;&nbsp;<a href="javascript:void(0);" onclick="confirmReset('loginName_${item.userId }');">重置密码</a>
							&nbsp;&nbsp;<a href="editUserForOrg.do?userId=${item.userId}&<mytag:params includes="s_*" type="queryStringUtf" />" >重新分配机构</a>
							&nbsp;&nbsp;<a href="updateUserPwd.do?userId=${item.userId}&<mytag:params includes="s_*" type="queryStringUtf" />" >修改密码</a>
							&nbsp;&nbsp;<a href="assignUserGrp.do?userId=${item.userId}&<mytag:params includes="s_*" type="queryStringUtf" />">分配用户分组</a>
							&nbsp;&nbsp;<a href="edit.do?userId=${item.userId}&<mytag:params includes="s_*" type="queryStringUtf" />">修改</a>
								&nbsp;&nbsp;<a href="delete.do?userId=${item.userId}&<mytag:params includes="s_*" type="queryStringUtf" />">删除</a>
							</c:if>
						</td>
								
					  </tr>
					  
				  	  </c:forEach>
				  </tbody>
				</table>
			</div>
			</div>
				<simpletable:pageToolbar page="${page}">
				
				</simpletable:pageToolbar>
				</div>
			</mvc:form>

	</body>
</html>	