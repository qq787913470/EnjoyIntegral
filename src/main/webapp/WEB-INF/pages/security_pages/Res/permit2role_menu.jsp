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
			
			//改变同组中radio的checked属性,与当前radio相反
			function changeGrp(radio){
				
				//点击可见性组
				if(radio.className == "elem1"){
					//可操作性组元素
					var query = "input[type=radio][value="+ radio.value +"].elem0";
					if(radio.id.indexOf("_2") > -1){//点击不可见,ACTION_ID=2
						dojo.query(query).forEach(function(item){
							//可操作组元素disabled:true
							//dijit.getEnclosingWidget(item).set("disabled", true);
							item.disabled = true;
						});
					}else{////点击可见
						dojo.query(query).forEach(function(item){
							//可操作组元素disabled:false
							//dijit.getEnclosingWidget(item).set("disabled", false);
							item.disabled = false;
						});
					}
				}	
				
				//dojo radio模板,input放入div,input的class追加在div上.IE和firefox,获取class使用radio.get("class"),firefox可使用radio.class
				var query = "input[type=radio][value="+ radio.value +"]." + radio.className;
				//radio不是dijit widget,dojo.query返回dom nodes.getEnclosingWidget(domNode)查找domNode最近的dijit widget
				dojo.query(query).forEach(function(item){
					//可见或可操作组另一个,check属性对立
					if(item.id != radio.id){
						//dijit.getEnclosingWidget(item).set("checked", !radio.checked);
						item.checked = !radio.checked;
					}
				});
				
			}
			
			/*
			ajax提交
			必须保证permits的顺序和permits[i]顺序一致
			数据格式:
			content{
				permits:[],//该资源的所有权限主键
				permits[i].roles:[]//i的数量是该资源的所有权限数,从0开始;表示该权限分配给哪些角色
			}
			*/
			function submit(){
				var content = {};
				content.permits = dojo.query("input[name=permits]").attr("value");
				dojo.query("input[type=radio]:checked").forEach(function(item){
					var name = item.name;
					if(content[name]){
						content[name].push(item.value);
					}else{
						content[name] = new Array();
						content[name].push(item.value);
					}
				});
				dojo.xhrPost({
					url: "res/allocate_ajax.do",
					//同步加载
					sync: true,
					content: content,
					load: function(data){
						alert("保存成功");
					},
					error: function(error){
						
  						alert(error);
	 				}
				});	
				
			}
			
		</script>
	</head>
	
	
	<body class="claro">
	
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
					
						
		<div class="biaoti"><p><b>${ res.menuName } 权限分配</b></p></div>
	<div class="biao">

				<table id="adminRole" class="grid its">
					<thead>
						<tr>
							<th>角色名称</th>
							<th colspan=2>资源操作列表</th>
						</tr>
						<tr>
							<th></th>								
							<th>可见性</th>
							<th>可操作性</th>								
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
							<td>${ item.roleName }</td>
							<td>
								<form>
								<c:forEach items="${ item.permits }" var="item_" varStatus="status_">
									<c:if test="${ item_.actionValue == '1' }">
									<input type="radio" id="elem${ item.roleId }_${ item_.actionId }"  value="${ item.roleId }" name="permits[${ status_.index }].roles" 
										class="elem${ item_.actionValue }" <c:if test="${ item_.checked == 'TRUE' }">checked</c:if> onclick="changeGrp(this)"/> 
									<label for="elem${ item.roleId }_${ item_.actionId }">
										${ item_.actionName }
									</label>
									</c:if>
								</c:forEach>
								</form>
							</td>
							<td>
								<form>
								<c:forEach items="${ item.permits }" var="item_" varStatus="status_">
									<c:if test="${ item_.actionValue == '0' }">
									<input type="radio" id="elem${ item.roleId }_${ item_.actionId }"  value="${ item.roleId }" name="permits[${ status_.index }].roles" 
										class="elem${ item_.actionValue }" <c:if test="${ item_.checked == 'TRUE' }">checked</c:if> <c:if test="${ item_.disabled == 'TRUE' }">disabled</c:if> onclick="changeGrp(this)"/> 
									<label for="elem${ item.roleId }_${ item_.actionId }">
										${ item_.actionName }
									</label>
									</c:if>
								</c:forEach>
								</form>
							</td>
						</tr>
						</c:forEach>
						<tr>
							<td colspan="3">
								<button type="button" style="width:80px"  onclick="submit();">保存</button>
							</td>
						</tr>
					</tbody>
				</table>
		</div>	
		</div>	
	</body>
</html>
						
