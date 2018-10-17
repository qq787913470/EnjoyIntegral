<%@ page import="com.yada.security.model.*" %>
<%@ page import="com.yada.enjoyIntegral.model.OrgChangeMxReport" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
<%@include file="../../base_pages/base.jsp"%><link href="<c:url value='/js/paging/simpletable/simpletable.css'/>" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/js/paging/simpletable/simpletable.js'/>"></script>
<script type="text/javascript" >
			window.onload=function() {
				// 分页需要依赖的初始化动作
				window.simpleTable = new SimpleTable('queryForm',${page.thisPageNumber},${page.pageSize},'${pageRequest.sortColumns}');
			};
            /**
             机构联动下级机构
             @param 机构ID
             */
            function findlowerOrg(orgId){
                if(orgId) {

                    //清空select,添加[--请选择--]option
                    var node = dojo.byId("orgId2");
                    dojo.empty(node);

					/*---------------创建元素节点--------------*/
                    dojo.create("option", {
                        value: "",
                        innerHTML: "--请选择--"
                    }, node);

                    dojo.xhrGet({
                        url: "AJAX_findlowerOrg.do",
                        sync: true,
                        content: {orgId: orgId, t: new Date().getTime()},
                        load: function (data) {
                            //用户名可用
                            if ("" != data) {
                                //解析用户分组
                                dojo.forEach(dojo.fromJson(data), function (org) {
                                    dojo.create("option", {
                                        value: org.orgId,
                                        innerHTML: org.name
                                    }, node);
                                });
                            }
                        },
                        error: function (error) {
                            alert(error);
                        }
                    });
                }}
</script>	
<link rel="stylesheet" type="text/css" href="../css/dqwz.css">		
</head>

<body>
<div id="dqwz">
<p>当前位置：权限管理&nbsp;〉&nbsp;用户分组</p>
<p class="line"></p>
</div>
	<div id="content">
	<div class="biaoti"><p><b>用户分组查询</b></p></div>

	<mvc:form id="queryForm" name="queryForm" action="list.do" method="get" style="display: inline;" commandName="query">

	<div class="biao">
	
			<table class="grid">
			
		      	<tr>	
		      		<%--<td class="lgridlist">机构</td>
					<td>
					<td>
						<mvc:select path="orgId" id="orgId" onchange="findlowerOrg(this.value);">
							<mvc:option value="">--请选择--</mvc:option>
							<mvc:options items="${ListOrgId}" itemValue="orgId" itemLabel="name"/>
						</mvc:select>
						<mvc:select path="orgId2" id="orgId2">
							<mvc:option value="">--请选择--</mvc:option>
							<mvc:options items="${lowerOrgs}" itemValue="orgId" itemLabel="name"/>
						</mvc:select>
					</td>
					</td>--%>
					
				    <td class="lgridlist">用户分组名称</td>		
					<td>
						<input value="${userGrp.name}" name="s_name" maxlength="32"/>
					</td>
				
				</tr>	
				
			</table>
				<table class="b">
				<tr>
					<td width="200px"></td>
					<td><input type="submit"  style="width:80px" value="查询" /></td>
					<td><input type="reset" style="width:80px" value="重置" ></td>
					<td><input type="button"  style="width:80px" value="新增" onclick="window.location.reload('insert.do')"/></td>
				</tr>
			</table>
			</div>

	<div class="form">
	<div class="eXtremeTable"  id="eXtremeTable">

		<table class="grid its">
		  <thead>
			  
			  <tr>
				<th><%=UserGrpExt.ALIAS_ORG_ID %></th>
				<th><%=UserGrp.ALIAS_NAME %></th>
				<th><%=UserGrpExt.ALIAS_IHT_FLAG %></th>
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	<c:if test="${page.totalCount == 0}">
		  		 <tr class="even">
		  			 <td align="center">没有数据</td>
		  			 <td align="center">&nbsp;</td>
		  			 <td align="center">&nbsp;</td>
		  			 <td align="center">&nbsp;</td>
		  		</tr>
		  	</c:if>
		  
		  	  <c:forEach items="${page.result}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td><c:out value='${item.userGrpExt.orgName}'/></td>
				<td><c:out value='${item.name}'/></td>
				<td><mytag:write dictName="D_IHT_FLAG" value="${item.userGrpExt.ihtFlag}"></mytag:write></td>
				<td align="center">
					&nbsp;&nbsp;<a href="role.do?id=${item.userGrpId}&<mytag:params includes="s_*" type="queryStringUtf" />">分配角色</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	</div>
	</div>
	
	<simpletable:pageToolbar page="${page}">
				
		</simpletable:pageToolbar>
		
	</mvc:form>

	</div>
	
</body>