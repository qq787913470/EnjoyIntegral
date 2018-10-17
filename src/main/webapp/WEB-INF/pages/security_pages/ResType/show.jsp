<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@include file="../../base_pages/base.jsp"%></head>
</head>
<body>
	<fieldset>
		<legend>
			控制类型查看
		</legend>
		<mvc:form action="update.do" method="post"  modelAttribute="resType">
			<table class="grid">
				<tr>	
					
					<td class="lgridbiaoti">
						<strong>名称：</strong>
					</td>
					<td>
						<mvc:input path="typeName" readonly="true"/>
						<strong style="color: red">*</strong>
					</td>
					
					<td class="lgridbiaoti">
						<strong>描述：</strong>
					</td>
					<td>
						<mvc:input path="remark" readonly="true"/>
					</td>
				</tr>
				
				<tr>
				    <td class="lgridbiaoti"><strong>动作列表：</strong></td>
				    <td colspan="3">
					    <table>
	                      <c:forEach items="${actions}" var="action" varStatus="status">
	                        <tr>
	                          	<td>${action.actionName}</td>
	                        </tr>
	                      </c:forEach>
	                    </table>
                    </td>
				</tr>
					
				<tr>
					<td width="30%" colspan="4">
					   <input type="button" name="cancel" value="返  回" onclick="javascript:location.href='list.do'">
					</td>
				</tr>
			</table>
		</mvc:form>
	</fieldset>
</body>
</html>