<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.yada.enjoyIntegral.model.*"%>
<html>
<head>
	<%@include file="../../base_pages/base.jsp"%>
	<script src="<c:url value="/js/teletext.js"/>" type="text/javascript"></script>
</head>
<body class="claro" >
<fieldset style="margin: 10px 0; padding: 10px; border: #DDD 1px solid">
		<legend>查看</legend>

		<table class="grid">
		<c:forEach items="${list}"  var="model" varStatus="status">
			<tr>
				<td class="lgridlist">商户名称</td>
				<td class="lgridlist">商品名称</td>
				<td class="lgridlist">活动名称</td>
				<td class="lgridlist">交易数量</td>
				<td class="lgridlist">积分消费数</td>
				<td class="lgridlist">交易日期</td>
				<td class="lgridlist">交易时间</td>
			</tr>
			<tr>
				<td><span>${model.merName}</span></td>
				<td><span>${model.productName}</span></td>
				<td><span>${model.activityName}</span></td>
				<td><span>${model.tranCount}</span></td>
				<td><span>${model.integralValue}</span></td>
				<td><span>${model.tranDate}</span></td>
				<td><span>${model.tranTime}</span></td>
			</tr>
		</c:forEach>
		</table>
			<table class="b">
				<tr align="center">
					<td><input type="button" value="返回"
						onclick="window.location='list.do'"/></td>
				</tr>
			</table>
	</fieldset>
</body>
</html>

