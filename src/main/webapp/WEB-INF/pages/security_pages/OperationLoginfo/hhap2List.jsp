<%@page import="com.yada.security.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base_pages/base.jsp" %>
<html>

<head>
	<script src="<c:url value="/js/jquery-1.4.1.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/js/select_tree.js"/>" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="../css/dqwz.css">
	<script type="text/javascript">
		/**
		 *调用方法需要两个参数
		 *1、 被模拟的text文本框ID 
		 *2 、提供生成dojo树所需json数据的链接
		**/
		$(document).ready(function() {
			initSelect("orgName", "${ctx}/org/findSubOrgsByPorgId.do?");
		});
	</script>
</head>

<body  class="claro">
	<div id="dqwz">
		<p>当前位置：稽核日志查询&nbsp;〉&nbsp;营销平台稽核日志查询</p>
		<p class="line"></p>
	</div>
	<div id="content">
		<div class="biaoti"><p><b>营销平台稽核日志查询</b></p></div>
		<mvc:form id="queryForm" name="queryForm" action="hhap2List.do" method="post" commandName="queryBean">
			<table class="grid">
				
				<tr>
					
					<td class="lgridlist" width="150"><strong><%=OperationLoginfo.ALIAS_ORG_ID%></strong></td>
					<td>
						<!-- hidden的input id前缀与前一个input id一致 -->
						<input type="text" id="orgName" value="${queryBean.orgName }"/>
						<input type="hidden" id="orgName_name" name="s_orgId" value=""/>
					</td>
					
					<td class="lgridlist" width="150"><strong><%=OperationLoginfo.ALIAS_USER_NAME%></strong></td>
					<td><input value="${queryBean.userName}" id="userName" name="s_userName" maxlength="20"  class=""/></td>
					
				</tr>
				
				<tr>
					<td class="lgridlist" width="150"><strong><%=OperationLoginfo.ALIAS_OPERATOR_TIME%></strong></td>
					<td colspan="3">
						<input id="operatorTimeBegin" name="s_operatorTimeBegin"
							value="${queryBean.operatorTimeBegin}" maxlength="0"
							class="Wdate" type="text"
							onFocus="WdatePicker({dateFmt:'yyyyMMdd',maxDate:'#F{$dp.$D(\'operatorTimeEnd\')}'})" />
						&nbsp;到&nbsp;
						<input id="operatorTimeEnd" name="s_operatorTimeEnd"
							value="${queryBean.operatorTimeEnd}" maxlength="0"
							class="Wdate" type="text"
							onFocus="WdatePicker({dateFmt:'yyyyMMdd',minDate:'#F{$dp.$D(\'operatorTimeBegin\')}'})" />
					</td>
					
				</tr>
				
			</table>
			<table class="b">
				<tr  align="center">
					<td><input type="submit"  style="width:80px" value="查询" /></td>
					<td><input type="reset" style="width:80px" value="重置" /></td>
				</tr>
			</table>
	<div id="eXtremeTable" class="eXtremeTable">
	
	<simpletable:pageToolbar page="${page}"></simpletable:pageToolbar>
	<table id="adminRole" class="grid its">
		<thead>
			<tr>
				<th  ><%=OperationLoginfo.ALIAS_ORG_ID%></th>
				<th  ><%=OperationLoginfo.ALIAS_USER_NAME%></th>
				<th  ><%=OperationLoginfo.ALIAS_OPERATOR_TIME%></th>
				<th  ><%=OperationLoginfo.ALIAS_MODULE_NAME%></th>
				<th  ><%=OperationLoginfo.ALIAS_METHOD_DESCRIPTION%></th>
				<th  ><%=OperationLoginfo.ALIAS_IP%></th>
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
				</tr>
			</c:if>
			<c:forEach items="${page.result}" var="item" varStatus="status">
				<c:if test="${status.index%2==0}">
					<tr class="old">
				</c:if>
				<c:if test="${status.index%2==1}">
					<tr class="even">
				</c:if>
				<td>${item.orgName}</td>
				<td>${item.userName}</td>
				<td>${item.operatorTime}</td>
				<td>${item.moduleName}</td>
				<td>${item.methodDescription}</td>
				<td>${item.ip}</td>
			</c:forEach>
		</tbody>
	</table>
	</div>
	</mvc:form>
	</div>
</body>

</html>