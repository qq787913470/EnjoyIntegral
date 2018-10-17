<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
   <title><%=ProductBase.TABLE_ALIAS%></title>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
    <div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=ProductBase.TABLE_ALIAS%>管理
		</p>
		<p class="line"></p>
	</div>
 
  <mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">

	<div id="content">
		<div class="biaoti">
			<p>
				<b>普通积分商品查询</b>
			</p>
		</div>
		<div class="biao">
	     <table class="grid">
				 <tr>	
					<td class="lgridlist">
							<%=ProductBase.ALIAS_PRODUCT_NAME%>
					</td>		
					<td>
						<input type="text" value="${query.productName}"  name="productName"  />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=ProductBase.ALIAS_PRICE%>
					</td>		
					<td>
						<input type="text" value="${query.price}"  name="price"  />
					</td>
					<td class="lgridlist">
							<%=ProductBase.ALIAS_REMARK%>
					</td>		
					<td>
						<input type="text" value="${query.remark}"  name="remark"  />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=ProductBase.ALIAS_MER_NO%>
					</td>		
					<td>
						<input type="text" value="${query.merNo}"  name="merNo"  />
					</td>
					<td class="lgridlist">
							<%=ProductBase.ALIAS_INTEGRAL_VALUE%>
					</td>		
					<td>
						<input type="text" value="${query.integralValue}"  name="integralValue"  />
					</td>
				</tr>	
	     </table>
	     <table class="b">
			<tr align="center">
				<td width="200px"></td>
				<td><input type="submit" style="width: 80px" value="查询"
					onclick="getReferenceForm(this).action='list.do'" /></td>
				<td><input type="button" style="width: 80px" value="新增"
					onclick="window.location.href='insert.do'" /></td>
			</tr>
		 </table>
      </div>
      <div class="form">
		 <div id="eXtremeTable" class="eXtremeTable">
			<table class="grid its">
			 <thead>
			   <tr>
				 <th>操作</th>
		                <th><%=ProductBase.ALIAS_PRODUCT_NAME%></th>
		                <th><%=ProductBase.ALIAS_PRICE%></th>
		                <th><%=ProductBase.ALIAS_REMARK%></th>
		                <th><%=ProductBase.ALIAS_MER_NO%></th>
		                <th><%=ProductBase.ALIAS_INTEGRAL_VALUE%></th>
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
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						<td align="center">&nbsp;|&nbsp; <a
							href="edit.do?id=${item.productId}">编辑>></a>
							&nbsp;|&nbsp; <a href="show.do?id=${item.productId}">查看>></a>
						</td>
						   <td><c:out value='${item.productName}' />&nbsp;</td>
						   <td><c:out value='${item.price}' />&nbsp;</td>
						   <td><c:out value='${item.remark}' />&nbsp;</td>
						   <td><c:out value='${item.merNo}' />&nbsp;</td>
						   <td><c:out value='${item.integralValue}' />&nbsp;</td>
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
