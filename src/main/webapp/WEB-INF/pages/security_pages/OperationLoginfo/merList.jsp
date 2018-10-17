<%@page import="com.yada.security.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base_pages/base.jsp" %>
<html>

<head>
	<link rel="stylesheet" type="text/css" href="../css/dqwz.css">
</head>

<body  class="claro">
	<div id="dqwz">
		<p>当前位置：稽核日志查询&nbsp;〉&nbsp;商户服务平台稽核日志查询</p>
		<p class="line"></p>
	</div>
	<div id="content">
		<div class="biaoti"><p><b><%=OperationLoginfo.TABLE_MER_ALIAS%> 查询</b></p></div>
		<mvc:form id="queryForm" name="queryForm" action="merList.do" method="post" commandName="queryBean">
			<table class="grid">
				
				<tr>
					
					<td class="lgridlist" width="150"><strong><%=OperationLoginfo.ALIAS_MER_NO%></strong></td>
					<td><input value="${queryBean.merNo}" id="merNo" name="s_merNo" maxlength="15"  class=""/></td>
					
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
				<th  ><%=OperationLoginfo.ALIAS_MER_NO%></th>
				<th  ><%=OperationLoginfo.ALIAS_MER_NAME%></th>
				<th  ><%=OperationLoginfo.ALIAS_USER_NAME%></th>
				<th  ><%=OperationLoginfo.ALIAS_OPERATOR_TIME%></th>
				<th  ><%=OperationLoginfo.ALIAS_MODULE_NAME%></th>
				<th  ><%=OperationLoginfo.ALIAS_METHOD_DESCRIPTION%></th>
				<th  ><%=OperationLoginfo.ALIAS_IP%></th>
				<th  >操作</th>
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
				<c:if test="${status.index%2==0}">
					<tr class="old">
				</c:if>
				<c:if test="${status.index%2==1}">
					<tr class="even">
				</c:if>
				<td>${item.merNo}</td>
				<td>${item.merName}</td>
				<td>${item.userName}</td>
				<td>${item.operatorTime}</td>
				<td>${item.moduleName}</td>
				<td>${item.methodDescription}</td>
				<td>${item.ip}</td>
				<td>
					<a href='merShow.do?id=${item.id}&<mytag:params includes="s_*" type="queryStringUtf" />'>查看&gt;&gt;|</a> 
				</td>
			</c:forEach>
		</tbody>
	</table>
	</div>
	</mvc:form>
	</div>
</body>

</html>