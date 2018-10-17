<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>

</head>
<body>
<div align="center" style="width: 300px;padding: 0px">
<mvc:form id="${action.typeId }_Action" action="action/orderSort.do" method="post"	modelAttribute="action" data-dojo-type="dijit.form.Form">
		<legend>${action.typeName}动作排序</legend>
	<input type="hidden" name="typeId" value="${action.typeId }"/>
	 <select id="${action.typeId }_orderSelect" name="orderSelect"
           style="width: 200px;height: 300px" 
           spore="${ctx }/js/dojo1.8" data-dojo-props="val:'actionId',text:'actionName'" 
           data-dojo-type="yadaWight.YDMultiSelect" listdata='${actionList}'>
      </select>  
           <button data-dojo-type="dijit.form.Button" type="submit">保存排序
           <script type="dojo/method" data-dojo-event="onClick" data-dojo-args="evt">
	                      dijit.byId('${action.typeId }_orderSelect').selectedAllOption();
	                         return true;
</script>
           </button>
           <button   type="button" name="cancel"
				 onclick="dijit.byId('conten${action.typeId}').setHref('action/list_href.do?typeId=${action.typeId}')">返回</button>
           </mvc:form>
           </div>
</body>
</html>