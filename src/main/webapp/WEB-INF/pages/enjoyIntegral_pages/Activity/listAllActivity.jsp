<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
   <title><%=Activity.TABLE_ALIAS%></title>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
    <div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=Activity.TABLE_ALIAS%>管理
		</p>
		<p class="line"></p>
	</div>
 
  <mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">

	<div id="content">
		<div class="biaoti">
			<p>
				<b>活动查询</b>
			</p>
		</div>
		<div class="biao">
	     <table class="grid">
				 <tr>	
					<td class="lgridlist">
							<%=Activity.ALIAS_ACTIVITY_NAME%>
					</td>		
					<td>
						<input type="text" value="${query.activityName}"  name="activityName" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
					</td>
					<td></td>
					<td></td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=Activity.ALIAS_START_DATE%>
					</td>		
					<td>
						<input id="startDate" name="startDate" value="${query.startDate}"  
								   class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss', maxDate : '#F{$dp.$D(\'endDate\')}'})" />
					</td>
					<td class="lgridlist">
							<%=Activity.ALIAS_END_DATE%>
					</td>		
					<td>
						<input id="endDate" name="endDate" value="${query.endDate}"  
								   class="Wdate" type="text" onFocus="WdatePicker({dateFmt:'yyyyMMddHHmmss', minDate :'#F{$dp.$D(\'startDate\')}'})" />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=Activity.ALIAS_GIFT_COUNT%>
					</td>		
					<td>
						<input type="text" value="${query.giftCount}"  name="giftCount" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false,regExp:'^[0-9]*$',invalidMessage:'请输入整数'"/>
					</td>
					<td class="lgridlist">
							<%=Activity.ALIAS_STATE%>
					</td>		
					<td>
						<mytag:select dictName="D_ACTIVITY_STATE" name="state" value="${query.state}" notEmpty="false"/>
					</td>
				</tr>	
	     </table>
	     <table class="b">
			<tr align="center">
				<td width="200px"></td>
				<td><input type="submit" style="width: 80px" value="查询"
					onclick="getReferenceForm(this).action='listAllActivity.do'" /></td>
			</tr>
		 </table>
      </div>
      <div class="form">
		 <div id="eXtremeTable" class="eXtremeTable">
			<table class="grid its">
			 <thead>
			   <tr>
				 <th>操作</th>
		                <th><%=Activity.ALIAS_ACTIVITY_NAME%></th>
		                <th><%=Activity.ALIAS_START_DATE%></th>
		                <th><%=Activity.ALIAS_END_DATE%></th>
				   		<td><%=Activity.ALIAS_ORG_ID%></td>
		                <th><%=Activity.ALIAS_STATE%></th>
				   		<td><%=Activity.ALIAS_REMARK%></td>
				 </tr>
			  </thead>
			  <tbody>
					<tr class="even">
						<td align="center">&nbsp;|&nbsp; <a id="nomalHref" href="${ctx}/activity/getNomalProduct.do">查看礼品</a></td>
			                <td align="center">尊享积分兑换交易（常规）</td>
			                <td align="center">00000000</td>
			                <td align="center">00000000</td>
						    <td align="center"> 中国银行苏州分行</td>
			                <td align="center">开启</td>
						    <td align="center">常规客户活动</td>
					</tr>
				
				<c:forEach items="${page.result}" var="item" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						<td align="center">
							&nbsp;|&nbsp; <a href="showAll.do?id=${item.activityId}">查看>></a>
							&nbsp;|&nbsp; <a href="${ctx}/activity/getActivityProduct.do?activityId=${item.activityId}">查看礼品</a>
						</td>
						   <td><c:out value='${item.activityName}' />&nbsp;</td>
						   <td><c:out value='${item.startDate}' />&nbsp;</td>
						   <td><c:out value='${item.endDate}' />&nbsp;</td>
						   <td><mytag:write value="${item.orgId}" dictName="Org"/>&nbsp;</td>
						   <td><mytag:write dictName="D_ACTIVITY_STATE" value="${item.state}"></mytag:write>&nbsp;</td>
						   <td><c:out value='${item.remark}' />&nbsp;</td>
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
