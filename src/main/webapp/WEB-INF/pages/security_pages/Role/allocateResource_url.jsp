<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		function all(){
			var inputs = document.getElementsByTagName("input");
			for(var i=5; i<inputs.length; i++){
				if(inputs[i].type=="checkbox"){
					if(inputs[i].onclick!=null){
						checkAll(inputs[i]);
					}
				}
			}
		}
		//选择checkbox触发事件
		function checkAll(checkbox){
			var hidden = get_firstChild(checkbox.parentElement);
			var tds = document.getElementsByName(hidden.value);
			var num = 2;
			for(var k=2; k<7; k++){
				var val = get_child(tds[0].parentElement, k).value;
				if(checkbox.value == val)
					num = k;
			}
			//alert(num+":"+tds.length);
			for(var i=1; i<tds.length; i++){
				if(checkbox!=null && checkbox.checked == true)
					get_child(tds[i].parentElement, num).disabled = "true";
				else 
					get_child(tds[i].parentElement, num).disabled = "";
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
	</script>
</head>
<body class="claro">
	<fieldset style="margin: 10px 0;padding: 10px;border: #DDD 1px solid">
		<legend>
			${role.roleName}:权限分配
		</legend>
		
		<mvc:form action="role/allocate.do" method="post" commandName="role">
			<mvc:hidden path="roleId"/>
			<input type="hidden" name="resTypeId" value="${resTypeId}"/>
			<input type="hidden" name="menuResId" value="${menuResId}"/>
			<input type="hidden" name="pmenuResId" value="${pmenuResId}"/>
			<table class="grid its">
				<tr>
					<td class="lgridlist">
						资源名称
					</td>
					<td class="lgridlist">
						权限列表
					</td>
				</tr>
				<c:set var="i" value="0"></c:set>
				<c:forEach items="${resList}" var="res" varStatus="status">
					<tr>
						<td class="lgridlist"  style="text-align: left;">
							<c:if test="${ res.pmenuResId eq 0}">
								<font color="red"> ${res.menuName}</font>
								<c:set var="i" value="${i+1}"></c:set>
							</c:if>
							<c:if test="${ res.pmenuResId ne 0}">
								&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
								&nbsp;&nbsp; &nbsp;${res.menuName}
							</c:if>
						</td>
						
					
						<td>
							<input type="hidden" name="${i}" value="${i}">
							<c:forEach items="${res.permits}" var="permit" varStatus="idx">
								 <c:if test="${ permit.typeId != 1}">
									 <input type="checkbox" name="permits" value="${permit.permitId}" 
									 	<c:if test="${permit.checked}">checked="checked"</c:if> 
									 	<c:if test="${ res.pmenuResId eq 0}">onclick="checkAll(this)"</c:if> />
									${permit.actionName}&nbsp;&nbsp;&nbsp;&nbsp;
								</c:if>
							</c:forEach>
							
						</td>
					</tr>
				</c:forEach>
			</table>
	<table height="35" class="b">
		<tr>
			<td width="30%">
				<input type="hidden" name="per" value="2"/>
				<input type="submit" value="保 存" onclick="return opconfirm('save')" />
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