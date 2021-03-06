<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
   <title><%=MerchantBase.TABLE_ALIAS%></title>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
    <div id="dqwz">
		<p>
			当前位置：业务管理&nbsp;〉&nbsp;<%=MerchantBase.TABLE_ALIAS%>管理
		</p>
		<p class="line"></p>
	</div>
 
  <mvc:form id="queryForm" name="queryForm" action="list.do"
		method="post" style="display: inline;">

	<div id="content">
		<div class="biaoti">
			<p>
				<b>普通积分商户查询</b>
			</p>
		</div>
		<div class="biao">
	     <table class="grid">
				 <tr>	
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_MER_NAME%>
					</td>		
					<td>
						<input type="text" value="${query.merName}"  name="merName"  data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
					</td>
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_MER_NO%>
					</td>		
					<td>
						<input type="text" value="${query.merNo}"  name="merNo"  data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_ACCOUNT_NO%>
					</td>		
					<td>
						<input type="text" value="${query.accountNo}"  name="accountNo"  data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
					</td>
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_ACCOUNT_NAME%>
					</td>		
					<td>
						<input type="text" value="${query.accountName}"  name="accountName"  data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_OPENING_BANK%>
					</td>		
					<td>
						<input type="text" value="${query.openingBank}"  name="openingBank" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false" />
					</td>
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_OPENING_BANK_NO%>
					</td>		
					<td>
						<input type="text" value="${query.openingBankNo}"  name="openingBankNo" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false" />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_CONTAC_PERSON%>
					</td>		
					<td>
						<input type="text" value="${query.contacPerson}"  name="contacPerson" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false" />
					</td>
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_CONTACT_ADDR%>
					</td>		
					<td>
						<input type="text" value="${query.contactAddr}"  name="contactAddr" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false" />
					</td>
				</tr>	
				 <tr>	
					<td class="lgridlist">
							<%=MerchantBase.ALIAS_SERVICE_PHONE%>
					</td>		
					<td>
						<input type="text" value="${query.servicePhone}"  name="servicePhone" data-dojo-type="dijit.form.ValidationTextBox" data-dojo-props="trim:true,required:false"/>
					</td>
					 <td class="lgridlist">
						 <%=MerchantBase.ALIAS_REVIEW_FLAG%>
					 </td>
					 <td>
						 <mytag:select name="reviewFlag"
									   value="${query.reviewFlag}"
									   dictName="REVIEW_FLAG"
									   id="reviewFlag"
									   notEmpty="false"/>
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
				        <th><%=MerchantBase.ALIAS_MER_NO%></th>
		                <th><%=MerchantBase.ALIAS_MER_NAME%></th>
		                <th><%=MerchantBase.ALIAS_ACCOUNT_NO%></th>
		                <th><%=MerchantBase.ALIAS_ACCOUNT_NAME%></th>
		                <th><%=MerchantBase.ALIAS_OPENING_BANK%></th>
		                <th><%=MerchantBase.ALIAS_OPENING_BANK_NO%></th>
		                <th><%=MerchantBase.ALIAS_CONTAC_PERSON%></th>
				        <th><%=MerchantBase.ALIAS_ORG_ID%></th>
				   		<th><%=MerchantBase.ALIAS_REVIEW_FLAG%></th>
					   <th><%=MerchantBase.SERVICE_STARE%></th>
					   <th><%=MerchantBase.END_SERVICE_DATE%></th>
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
							<td align="center">&nbsp;</td>
							<td align="center">&nbsp;</td>
						<td align="center">&nbsp;</td>
						<td align="center">&nbsp;</td>
					</tr>
				</c:if>
				<c:forEach items="${page.result}" var="item" varStatus="status">
					<tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
						<td align="center">
						    &nbsp;|&nbsp; <a href="edit.do?id=${item.merNo}">编辑>></a>
							&nbsp;|&nbsp; <a href="show.do?id=${item.merNo}">查看>></a>
							&nbsp;|&nbsp; <a href="linkProduct.do?merNo=${item.merNo}">操作商品>></a>
							<c:if test="${item.serviceStare==0}">
								<a href="changeMerchanStateReason.do?id=${item.merNo}">关闭>></a>
							</c:if>
							<c:if test="${item.serviceStare==1}">
								<a href="changeMerchanStateReason.do?id=${item.merNo}">开启></a>
							</c:if>
						</td>
						   <td><c:out value='${item.merNo}' />&nbsp;</td>
						   <td><c:out value='${item.merName}' />&nbsp;</td>
						   <td><c:out value='${item.accountNo}' />&nbsp;</td>
						   <td><c:out value='${item.accountName}' />&nbsp;</td>
						   <td><c:out value='${item.openingBank}' />&nbsp;</td>
						   <td><c:out value='${item.openingBankNo}' />&nbsp;</td>
						   <td><c:out value='${item.contacPerson}' />&nbsp;</td>
						<td><mytag:write value="${item.orgId}" dictName="Org"/>&nbsp;</td>
						<td><mytag:write value="${item.reviewFlag}" dictName="REVIEW_FLAG"/>&nbsp;</td>
						<td><mytag:write value="${item.serviceStare}" dictName="SERVICE_STARE"/>&nbsp;</td>
						<td><c:out value='${item.endServiceDate}' />&nbsp;</td>
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
