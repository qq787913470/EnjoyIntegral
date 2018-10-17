<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>

<%@include file="../../base_pages/base.jsp"%>
</head>
<body>
	<div id="${treeId}">
		<form id="${treeId}_order" action="res/orderRes.do" method="post">
			<select id="${treeId}_orderSelect" name="orderSelect"
				style="width: 200px; height: 300px" spore="${ctx}/js/dojoBuild1.8"
				data-dojo-props="val:'menuResId',text:'menuName'"
				data-dojo-type="yadaWight.YDMultiSelect" listdata='${resList}'>
			</select><br>
			<button data-dojo-type="dijit.form.Button" type="submit">
				保存排序
				<script type="dojo/method" data-dojo-event="onClick"
					data-dojo-args="evt">
				dijit.byId('${treeId}_orderSelect').selectedAllOption();
				return true;
			</script>
			</button>
		</form>
	</div>
</body>
</html>