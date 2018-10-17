<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<html>
<head>
</head>
<body>
	<fieldset>
		<legend>
			权限查看
		</legend>
		<table class="grid">
			<tr>	
				<td class="lgridbiaoti">
					资源名称:
				</td>
				<td>${permit.menuName}</td>
				<td class="lgridbiaoti">
					操作名称:
				</td>
				<td>${permit.actionName}</td>
			</tr>
			<tr>
				<td class="lgridbiaoti">
					资源类型:
				</td>
				<td>${permit.typeName}</td>
				
				<td class="lgridbiaoti">
					加载顺序:
				</td>
				<td>${permit.permitOrder}</td>
			</tr>
		</table>
	</fieldset>
</body>
</html>