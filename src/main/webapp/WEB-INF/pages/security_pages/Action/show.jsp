<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
	<fieldset>
		<legend>
			操作查看
		</legend>
		<table class="grid">
			<tr>	
			
				<td class="lgridbiaoti">
					菜单属性名称:
				</td>
				<td>${action.typeName}</td>
				
				<td class="lgridbiaoti"></td>
				<td></td>
				
			</tr>
			<tr>	
			
				<td class="lgridbiaoti">
					操作名称:
				</td>
				<td>${action.actionName}</td>
				
				<td class="lgridbiaoti">
					操作值:
				</td>
				<td>${action.actionValue}</td>
				
			</tr>
			<tr>
				<td class="lgridbiaoti">
					操作显示顺序:
				</td>
				<td>${action.actionOrder}</td>
				
				<td class="lgridbiaoti">
					操作描述:
				</td>
				<td>${action.remark}</td>
			</tr>
			<tr>
				<td width="30%" colspan="4">
				  <button  type="button" name="cancel"
				onclick="dijit.byId('conten${action.typeId}').setHref('action/list_href.do?typeId=${action.typeId}')">返  回</button>
				</td>
			</tr>
		</table>
	</fieldset>
</body>
</html>