<%@ page import="com.yada.security.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/base_pages/base.jsp" %>
<head>
</head>
<html>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
		<legend>用户新增</legend>
		<mvc:form action="saveUserPwd.do" theme="simple"  method="post" commandName="model" data-dojo-type="dijit.form.Form" id="saveForm">
				<table class="grid">
				<mvc:hidden path="userId"/>
		    	<tr>
			        <td class="lgridlist">用户登录名:</td>
				    <td >
			           		${model.loginName}
			        </td>
		  		</tr>
				
		    	<tr>
			        <td class="lgridlist">用户登录原密码:</td>
				    <td >
			             <input data-dojo-type="dijit.form.ValidationTextBox" type="password" required="true" data-dojo-props="trim:true,regExp:'.{6,}', invalidMessage:'密码长度至少6位!'" id="voidPwd" name="voidPwd" size="40" maxlength="32" onblur="checkVoidPwd(this.value);" />
			             <span style="color: red" id="voidPwdMsg">*</span><span style="color: red">请输入6位数以上密码</span>
			           
			        </td>
		  		</tr>
				
		    	<tr>
			        <td class="lgridlist">用户登录新密码:</td>
				    <td >
			             <input data-dojo-type="dijit.form.ValidationTextBox" type="password" required="true" data-dojo-props="trim:true,regExp:'.{6,}', invalidMessage:'密码长度至少6位!'" id="pwd" name="pwd" size="40" maxlength="32" />
			             <span id="msg" style="color: red">*</span><span style="color: red">请输入6位数以上密码</span>
			           
			        </td>
		  		</tr>
		    	<tr>
			        <td class="lgridlist">用户登录确认新密码:</td>
				    <td >
			             <input data-dojo-type="dijit.form.ValidationTextBox" type="password" required="true" data-dojo-props="trim:true,regExp:'.{6,}', invalidMessage:'密码长度至少6位!'" id="rePwd" size="40" maxlength="32" onblur="isPwdSame();" />
			             <span style="color: red" id="rePwdMsg">*</span><span style="color: red">请输入6位数以上密码</span>
			           
			        </td>
		  		</tr>
	
				</table>
				<table class="b">
					<tr  align="center">
						<td><input id="submitButton" name="submitButton" type="button" value="更新" onclick="checkOnSubmit();" /></td>
						<td><span style="color: red" >${message}</span></td>
					</tr>
				</table>
		</mvc:form>
	</fieldset>	
	<script>
		var onsbmt = true;
		var form = document.forms.saveForm;
		
		/**
			验证旧密码
		**/
		function checkVoidPwd(voidPwd){
			if(voidPwd){
				//验证是否正确
				dojo.xhrGet({
					url: "AJAX_checkVoidPwd.do",
					sync: true,
					content: {pwd: voidPwd, userId: ${model.userId}, t: new Date().getTime()},
					load: function(data){
						//分组名称不可用
						dojo.byId("voidPwdMsg").innerHTML = data;
						if("*" != data){
							onsbmt = false;
						}else{
							onsbmt = true;
						}
					},
					error: function(error){
						alert(error);
					}
				});
			}
		}
		
		/**
			两次输入密码是否一致.
		*/
		function isPwdSame(){
			if(dijit.byId("saveForm").validate()){
				
				//确认密码	
				var rePwd = dojo.byId("rePwd").value;
				//登录密码
				var pwd = dojo.byId("pwd").value;
				
				//密码不一致
				dojo.byId("rePwdMsg").innerHTML = "*";
				if(rePwd != pwd){
					dojo.byId("rePwdMsg").innerHTML = "确认密码和登录密码不一致!";
					onsbmt = false;
				}else{
					onsbmt = true;
				}
			}
		}
		
		/**
			两次输入密码是否一致.
			@param:form
		*/
		function checkOnSubmit(){
			if(form && dijit.byId("saveForm").validate()){
				
				//新密码与验证密码一致
				isPwdSame();
				
				//原密码	
				var voidPwd = dojo.byId("voidPwd").value;
				dojo.xhrGet({
					url: "AJAX_checkVoidPwd.do",
					sync: true,
					content: {pwd: voidPwd, userId: ${model.userId}, t: new Date().getTime()},
					load: function(data){
						//分组名称不可用
						dojo.byId("voidPwdMsg").innerHTML = data;
						if("*" == data && onsbmt){
							if(confirm('确定执行[保存]操作?')){
								document.forms.saveForm.submit();
							}
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