<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<body>
<div style="height: 95%;width: 95%" align="center">
<mvc:form action="permit/orderPermit.do" method="post"
	modelAttribute="permit" data-dojo-type="dijit.form.Form">
	<input type="hidden" name="menuResId" value="${permit.menuResId }"/>
	<legend style="width:100%" align="center">
			${permit.menuName }权限排序
		</legend>
	
	 <select id="orderSelect" name="orderSelect"
           style="width: 200px;height: 300px" 
           spore="${ctx }/js/dojo1.8" data-dojo-props="val:'permitId',text:'actionName'" 
           data-dojo-type="yadaWight.YDMultiSelect" listdata='${resPermit}'>
           </select>  
           <button data-dojo-type="dijit.form.Button" type="submit">保存排序
           <script type="dojo/method" data-dojo-event="onClick" data-dojo-args="evt">
	                      dijit.byId('orderSelect').selectedAllOption();
	                         return true;
</script>
           </button>
           <button   type="button" name="cancel"
				 onclick="dijit.byId('rightContentPane').setHref('permit/showResPermit.do?resId=${permit.menuResId }')">返回</button>
           </mvc:form>
           </div>
</body>
</html>