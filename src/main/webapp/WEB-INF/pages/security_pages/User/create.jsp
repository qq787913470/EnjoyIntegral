<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<head>
</head>
<html>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
		<legend>用户新增</legend>
		<mvc:form action="save.do" theme="simple"  method="post" commandName="model" id="saveForm"  data-dojo-type="dijit.form.Form">
				<table class="grid">
				
			    	<tr>
				        <td class="lgridlist">机构:</td>
					    <td >
					    	<mvc:select path="userExt.orgId" onchange="findlowerOrg(this.value);" id="orgId">
					    		<mvc:option value="">--请选择--</mvc:option>
					    		<mvc:options items="${lowerOrgs}" itemValue="orgId" itemLabel="name"/>
					    	</mvc:select>
					    	<mvc:select path="userExt.orgId" id="lowerOrgId">
					    		<mvc:option value="">--请选择--</mvc:option>
					    		<mvc:options items="${orgs}" itemValue="orgId" itemLabel="name"/>
					    	</mvc:select>
				            <span style="color: red" id="orgMsg">*</span>
				        </td>
				   </tr>
				   
				   <tr>
				        <td class="lgridlist">用户分组:</td>
					    <td >
							<select id="userGrpId" name="userGrpId">
								<option value="">--请选择--</option>
								<c:forEach items="${userGrps}" var="item">
									<option value="${item.userGrpId}">${item.name}</option>
								</c:forEach>
							</select>
				            <span style="color: red" id="userGrpMsg">*</span>
				           
				        </td>
			  		</tr>
				
			    	<tr>
				        <td class="lgridlist">用户登录名:</td>
					    <td >
				             <input name="loginName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:true,regExp:'^([a-zA-Z0-9]+)$'" id="loginName" maxlength="16" size="40" onblur="isLoginNameExist(this.value);" />
				             <span id="checkMsg" style="color: red">*使用员工7位员工EHR号</span>
				        </td>
				   </tr>
			    	<tr>
				        <td class="lgridlist">用户姓名:</td>
					    <td >
				             <input name="userName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:true" id="userName" maxlength="16" size="40" />
				             <span id="checkMsg" style="color: red">*</span>
				        </td>
				   </tr>
				   
				   <tr>
				        <td class="lgridlist">用户密码:</td>
					    <td >
				             <input type="password" id="pwd" name="pwd" size="40" maxlength="16" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,regExp:'^\.{6,}$', invalidMessage:'请输入6位数以上密码.'" />
				             <span style="color: red">请输入6位数以上密码.不输入默认为:111111</span>
				        </td>
			  		</tr>
				
			    	<tr>
				        <td class="lgridlist">用户状态:</td>
					    <td >
					    	<mytag:select dictName="D_USER_STATUS" name="userExt.status" id="status"></mytag:select>
				            <span style="color: red" id="statusMsg">*</span>
				        </td>
				   </tr>
	
				</table>
				<table class="b">
					<tr  align="center">
						<td><input id="submitButton" name="submitButton" type="button" value="保存" onclick="checkOnSubmit();" /></td>
						<td><input type="button" value="返回" onclick="window.location='list.do'"/></td>
					</tr>
				</table>
		</mvc:form>
	</fieldset>	
	<script>
		var form = document.forms.saveForm;
		var onsbmt = true;
		/**
			用户名是否可用
			@param 用户名
		*/
		function isLoginNameExist(loginName){
			
			//清空提示信息
			var checkMsg = dojo.byId("checkMsg");
			checkMsg.innerHTML = "*";
			
			//验证是否可用
			dojo.xhrGet({
				url: "AJAX_isLoginNameExist.do",
				sync: true,
				content: {loginName: loginName, t: new Date().getTime()},
				load: function(data){
					//提示信息
					checkMsg.innerHTML = data;
					if("*" != data){
						onsbmt = false;
					}
				},
				error: function(error){
					alert(error);
				}
			});
			
		}
		
		/**
		机构联动下级机构
		@param 机构ID
	*/
	function findlowerOrg(orgId){
		if(orgId){
			
			//清空select,添加[--请选择--]option
			var node = dojo.byId("lowerOrgId");
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
			提交,验证信息
		*/
		function checkOnSubmit(){
			
			onsbmt = true;
			
			//选择机构
			dojo.byId("orgMsg").innerHTML = "*";
			if(!dojo.byId("orgId").value){
				dojo.byId("orgId").focus();
				dojo.byId("orgMsg").innerHTML = "请选择机构!";
				onsbmt = false;
			}
			
			//选择用户分组
			dojo.byId("userGrpMsg").innerHTML = "*";
			if(!dojo.byId("userGrpId").value){
				dojo.byId("userGrpId").focus();
				dojo.byId("userGrpMsg").innerHTML = "请选择用户分组!";
				onsbmt = false;
			}
			
			//输入状态
			dojo.byId("statusMsg").innerHTML = "*";
			if(!dojo.byId("status").value){
				dojo.byId("status").focus();
				dojo.byId("statusMsg").innerHTML = "请选择用户状态!";
				onsbmt = false;
			}
			
			if(!form || !dijit.byId("saveForm").validate()){
				return false;
			}
			
			//验证登录用户名
			var loginName = dojo.byId("loginName").value;
			dojo.xhrGet({
				url: "AJAX_isLoginNameExist.do",
				sync: true,
				content: {loginName: loginName, t: new Date().getTime()},
				load: function(data){
					//提示信息
					checkMsg.innerHTML = data;
					//用户名可用
					if("*" == data && onsbmt){
						if(confirm("是否确定执行[提交]操作?")){
							document.forms.saveForm.submit();
						}
					}
				},
				error: function(error){
					alert(error);
				}
			});
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
						dojo.byId("userGrpId").disabled = false;
						if("" != data){
							//解析用户分组
							dojo.forEach(dojo.fromJson(data), function(userGrp){
								dojo.create("option", {
									value : userGrp.userGrpId,
									innerHTML : userGrp.name
								}, node);
							});
						}else{
							dojo.byId("userGrpId").value = "该机构下无可用用户分组";
							dojo.byId("userGrpId").disabled = true;
						}
					},
					error: function(error){
						alert(error);
					}
				});
			}
		}
		
	</script>
</body>
</html>