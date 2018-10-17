<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
   <title><%=ActivityProductEnjoy.TABLE_ALIAS%></title>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
    <div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=ActivityProductEnjoy.TABLE_ALIAS%>管理
		</p>
		<p class="line"></p>
	</div>
 
  <mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">

	<div id="content">
		<div class="biaoti">
			<p>
				<b>活动产品表查询</b>
			</p>
		</div>
		<div class="biao">
	     <table class="grid">
				 <tr>	
					<td class="lgridlist">
							<%=ActivityProductEnjoy.ALIAS_ACTIVITY_ID%>
					</td>		
					<td>
						<input type="text" value="${query.activityId}"  name="activityId"  />
					</td>
					<td class="lgridlist">
							<%=ActivityProductEnjoy.ALIAS_PRODUCT_ID%>
					</td>		
					<td>
						<input type="text" value="${query.productId}"  name="productId"  />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=ActivityProductEnjoy.ALIAS_LIMIT_COUNT%>
					</td>		
					<td>
						<input type="text" value="${query.limitCount}"  name="limitCount"  />
					</td>
					<td class="lgridlist">
							<%=ActivityProductEnjoy.ALIAS_GIFT_PROMPT%>
					</td>		
					<td>
						<input type="text" value="${query.giftPrompt}"  name="giftPrompt"  />
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
		                <th><%=ActivityProductEnjoy.ALIAS_ACTIVITY_ID%></th>
		                <th><%=ActivityProductEnjoy.ALIAS_PRODUCT_ID%></th>
		                <th><%=ActivityProductEnjoy.ALIAS_LIMIT_COUNT%></th>
		                <th><%=ActivityProductEnjoy.ALIAS_GIFT_PROMPT%></th>
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
					</tr>
				</c:if>
				<c:forEach items="${page.result}" var="item" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						<td align="center">&nbsp;|&nbsp; <a
							href="edit.do?id=${item.activityId}">编辑>></a>
							&nbsp;|&nbsp; <a href="show.do?id=${item.activityId}">查看>></a>
						</td>
						   <td><c:out value='${item.activityId}' />&nbsp;</td>
						   <td><c:out value='${item.productId}' />&nbsp;</td>
						   <td><c:out value='${item.limitCount}' />&nbsp;</td>
						   <td><c:out value='${item.giftPrompt}' />&nbsp;</td>
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
