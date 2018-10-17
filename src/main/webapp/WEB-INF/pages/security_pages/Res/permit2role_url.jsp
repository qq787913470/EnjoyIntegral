<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- 
		<script type="text/javascript" src="../js/dojo1.8/dojo/dojo.js" 
			data-dojo-config="parseOnLoad: true"></script>
		 -->
		<script type="text/javascript">
			function checkAll(checkbox){
				//checkbox不是dijit widget,dojo.query返回dom nodes.getEnclosingWidget(domNode)查找domNode最近的dijit widget
				dojo.query("input[type=checkbox][value="+ checkbox.value +"]").forEach(function(item){
					//dijit.getEnclosingWidget(item).set("checked",checkbox.checked);
					item.checked = checkbox.checked;
				});
			}
		</script>
	</head>
	<body class="claro">
		<form  method="post" id="assigned" >
		<div id="content">
		<div class="biaoti"><p><b>${ res.menuName } 详细信息</b></p></div>
		<div class="biao">
				
				<table class="grid">
					<tr>	
						<td class="lgridbiaoti">
							资源名称:
						</td>
						<td>${ res.menuName }</td>
						<td class="lgridbiaoti">
							资源类型:
						</td>
						<td>${ res.typeName }</td>
					</tr>
					<tr>	
						<td class="lgridbiaoti">
							URL:
						</td>
						<td colspan="3">${ res.actionName }</td>
					</tr>
					<tr>
						<td class="lgridbiaoti">
							操作列表:
						</td>
						<td colspan="3">
							<c:forEach items="${ res.permits }" var="permit">
								<input type="hidden" name="permits" value="${ permit.permitId }" />
								${ permit.actionName },
							</c:forEach>
						</td>
					</tr>
				</table>
			</div>		
			
			<c:if test="${res.ordered == 1}">
		
		<div class="biaoti"><p><b>正序/逆序</b></p></div>
		<div class="biao">
			

				<input name="ordered" value="1" type="radio" id="ordered1" <c:if test="${sort == 1}"> checked </c:if> /><label for="ordered1">正序	</label>
				<input name="ordered" value="0" type="radio" id="ordered0" <c:if test="${sort == 0}"> checked </c:if> /><label for="ordered0">逆序	</label>
			</div>	
			</c:if>	
				
				<div class="biaoti"><p><b>${ res.menuName } 权限分配</b></p></div>
		<div class="biao">
				

					<table id="adminRole" class="grid its">
						<thead>
							<tr>
								<th>全选</th>
								<th>角色名称</th>
								<th>资源操作列表</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${roleList}" var="item"
								varStatus="status">
								<tr>
								<c:if test="${status.index%2==0}">
									<tr class="old">
								</c:if>
								<c:if test="${status.index%2==1}">
									<tr class="even">
								</c:if>
								<td>
									<input type="checkbox"  value="${ item.roleId }" onClick="checkAll(this)"/>
								</td>
								<td>${ item.roleName }</td>
								<td>
									<c:forEach items="${ item.permits }" var="item_" varStatus="status_">
										<input type="checkbox" id="elem${ item.roleId }${ item_.permitId }" value="${ item.roleId }" name="permits[${ status_.index }].roles" <c:if test="${ item_.checked == 'TRUE' }">checked</c:if> /> 
										<label for="elem${ item.roleId }${ item_.permitId }">
											${ item_.actionName }
										</label>
									</c:forEach>
								</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="3"> 
									<button data-dojo-type="dijit.form.Button" type="button" >保存
									<script type="dojo/on" data-dojo-event="click">
											dojo.xhrPost({
			                					url: "res/allocate_ajax.do",
			                					//同步加载
			                					sync: true,
			                					form: "assigned",
			                					load: function(data){
			                						alert("保存成功");
			                					},
			                					error: function(error){
			                  						alert(error);
			               	 					}
			            					});			
   										</script>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
					</div>	
			</div>	
		</form>					
			
	</body>
</html>
						
