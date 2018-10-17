<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="../js/dojo/dojo.js" data-dojo-config="isDebug:1, parseOnLoad: true"></script>
   	<link rel="stylesheet" href="../js/dijit/themes/claro/claro.css" media="screen">
   	<script type="text/javascript">
   		function all(){
   			//alert("all");
   			//alert(get_child(document.getElementsByTagName("tr")[2], 2).tagName);
   			var trs = document.getElementsByTagName("tr");
   			for(var i=2; i<trs.length; i++){
   				var radio = get_child(get_child(document.getElementsByTagName("tr")[i], 2), 2);
   				if(radio!=null && radio.checked)
   					radioAll(radio);
   			}
   		}
		//选择radio触发事件
	   	function radioAll(radio){
	   		var td = radio.parentElement;
	   		var inputs = get_nextSibling(td).getElementsByTagName("input");
	   		if(get_child(td, 2)!=null && get_child(td, 2).checked){
	   			for(var i=0; i<inputs.length; i++)
	   				inputs[i].disabled="disabled";
	   		} else {
	   			for(var i=0; i<inputs.length; i++)
	   				inputs[i].disabled="";
	   		}
	   	}
	   	/*child属性兼容firefox的处理
		  n是这个对象
		  num是第几个child*/
		function get_child(n, num){
			var y = get_firstChild(n);
			if(num != 1){
				for(var i=1; i<num; i++){
					y = get_nextSibling(y);
				}
				return y;
			}else{
				return y;
			}
		}
		//firstChild属性兼容firefox的处理
		function get_firstChild(n){
			var y = n.firstChild;
			while (y.nodeType!=1) {
				y = y.nextSibling;
		    }
			return y;
		}
		//nextSibling属性兼容firefox的处理
		function get_nextSibling(n){
			var y = n.nextSibling;
			while (y!=null && y.nodeType!=1) {
				y = y.nextSibling;
		    }
			return y;
		}
		//listChild属性兼容firefox的处理
		function get_listChild(n){
			var y = n.listChild;
			while (y.nodeType!=1) {
				y = y.previousSibling;
		    }
			return y;
		}
		window.onload = function(){
			all();
		}
   	</script>
</head>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid" >
		<legend>
			${role.roleName}:权限分配
		</legend>
		
		<mvc:form action="role/allocate.do" method="post" commandName="role">
			<mvc:hidden path="roleId"/>
			<input type="hidden" name="resTypeId" value="${resTypeId}"/>
			<input type="hidden" name="menuResId" value="${menuResId}"/>
			<input type="hidden" name="pmenuResId" value="${pmenuResId}"/>
			<table class="grid its">
			<thead>
				<tr>
					<th>资源名称</th>
					<th colspan=2>权限列表</th>
				</tr>
				<tr>
					<th></th>								
					<th>可见性</th>
					<th>可操作性</th>								
				</tr>
				</thead>
				<c:set var="i" value="1"></c:set>
				<tbody>
				<c:forEach items="${resList}" var="res" varStatus="status">
					<c:set var="i" value="${i+2}"></c:set>
					<tr>
						<td class="lgridlist"  style="text-align: left;">
							<c:if test="${ res.pmenuResId eq 0}">
								<font color="red"> ${res.menuName}</font>
							</c:if>
							<c:if test="${ res.pmenuResId ne 0}">
								&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; 
								&nbsp;&nbsp; &nbsp;${res.menuName}
							</c:if>
						</td>
						
						<td id="permit${i-1}">
							<c:forEach items="${res.permits}" var="permit" varStatus="idx">
								<c:if test="${permit.actionValue==1}">
									<input type="radio"  name="permit${i-1}" value="${permit.permitId}" onclick="radioAll(this)" 
										<c:if test="${permit.checked}">checked="checked"</c:if> style="padding-top: -23px"/>
									${permit.actionName}&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
							</c:forEach>
							
						</td>
						<td>
							<c:forEach items="${res.permits}" var="permit" varStatus="idx">
								<c:if test="${permit.actionValue==0}">
									<input type="radio"  name="permit${i-2}" value="${permit.permitId}" 
										<c:if test="${permit.checked}">checked="checked"</c:if> style="padding-top: -23px"/>
									${permit.actionName}&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<script>all();</script>
	<table height="35" class="b">
		<tr>
			<td width="30%">
				<input type="hidden" name="per" value="${i}"/>
				<input type="submit" value="保 存" <c:if test="${i==1}">disabled="disabled"</c:if> onclick="return opconfirm('save')" />
			</td>
			<td width="30%">
			  <input type="button" name="cancel" value="返  回" onclick="javascript:location.href='list.do'">
			</td>
			<td width="40%"> </td>
		</tr>
	</table>
		</mvc:form>
	</fieldset>
</body>
</html>