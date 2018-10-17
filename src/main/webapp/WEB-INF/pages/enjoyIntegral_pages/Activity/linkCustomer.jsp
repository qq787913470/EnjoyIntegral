<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
   <title><%=CustomerInfo.TABLE_ALIAS%></title>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
    <div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=CustomerInfo.TABLE_ALIAS%>管理
		</p>
		<p class="line"></p>
	</div>
 
  <mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">

	<div id="content">
		<div class="biaoti">
			<p>
				<b><%=CustomerInfo.TABLE_ALIAS%>查询</b>
			</p>
		</div>
		<div class="biao">
	     <table class="grid">
				 <tr>	
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_CERTIFICATE_NO%>
					</td>		
					<td>
						<input type="text" value="${query.certificateNo}"  name="certificateNo"  />
					</td>
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%>
					</td>		
					<td>
						<mytag:select dictName="D_CERTIFICATE_TYPE" name="certificateType" value="${query.certificateType}" />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_NAME%>
					</td>		
					<td>
						<input type="text" value="${query.name}"  name="name"  />
						<input type="hidden" name="activityId" value="${query.activityId}">
					</td>
					<td class="lgridlist">
					</td>		
					<td>
					</td>
				</tr>	
	     </table>
	     <table class="b">
			<tr align="center">
				<td width="200px"></td>
				<td><input type="submit" style="width: 80px" value="查询"
					onclick="getReferenceForm(this).action='linkCustomers.do'" /></td>
				<td><input type="submit" style="width: 80px" value="上传客户资料"
					onclick="getReferenceForm(this).action='uploadCustomers.do'" /></td>
				<td><input type="submit" style="width: 80px" value="删除全部客户"
					onclick="getReferenceForm(this).action='deleteAllCustomer.do';return confirm('确认全部删除吗？')" /></td>
			</tr>
		 </table>
      </div>
      <div class="form">
		 <div id="eXtremeTable" class="eXtremeTable">
			<table class="grid its">
			 <thead>
			   <tr>
				 <th>操作</th>
		                <th><%=CustomerInfo.ALIAS_NAME%></th>
		                <th><%=CustomerInfo.ALIAS_CERTIFICATE_NO%></th>
		                <th><%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%></th>
				 </tr>
			  </thead>
			  <tbody>
			    <c:if test="${page.totalCount == 0}">
					<tr class="even">
						<td align="center">没有数据</td>
			                <td align="center">&nbsp;</td>
			                <td align="center">&nbsp;</td>
			                <td align="center">&nbsp;</td>
					</tr>
				</c:if>
				<c:forEach items="${page.result}" var="item" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						<td align="center">&nbsp;|&nbsp; <a
							href="deleteCustomer.do?certificateNo=${item.certificateNo}&certificateType=${item.certificateType}&activityId=${query.activityId}">删除>></a>
							&nbsp;|&nbsp; </a>
						</td>
						   <td><c:out value='${item.name}' />&nbsp;</td>
						   <td><c:out value='${item.certificateNo}' />&nbsp;</td>
						   <td><mytag:write dictName="D_CERTIFICATE_TYPE" value="${item.certificateType}"></mytag:write>&nbsp;</td>
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
