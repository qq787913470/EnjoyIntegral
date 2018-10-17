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
							<%=CustomerInfo.ALIAS_NAME%>
					</td>		
					<td>
						<input type="text" value="${query.name}"  name="name"  data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"  />
					</td>
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_CUSTOMER_NO%>
					</td>		
					<td>
						<input type="text" value="${query.customerNo}"  name="customerNo"  data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"  />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_CERTIFICATE_NO%>
					</td>		
					<td>
						<input type="text" value="${query.certificateNo}"  name="certificateNo"  data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"  />
					</td>
					<td class="lgridlist">
							<%=CustomerInfo.ALIAS_CERTIFICATE_TYPE%>
					</td>		
					<td>
						<mytag:select dictName="D_CERTIFICATE_TYPE" name="certificateType" value="${query.certificateType}"/>
					</td>
				</tr>	
	     </table>
	     <table class="b">
			<tr align="center">
				<td width="200px"></td>
				<td><input type="submit" style="width: 80px" value="查询"
					onclick="getReferenceForm(this).action='list.do'" /></td>
			</tr>
		 </table>
      </div>
      <div class="form">
		 <div id="eXtremeTable" class="eXtremeTable">
			<table class="grid its">
			 <thead>
			   <tr>
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
					</tr>
				</c:if>
				<c:forEach items="${page.result}" var="item" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
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
