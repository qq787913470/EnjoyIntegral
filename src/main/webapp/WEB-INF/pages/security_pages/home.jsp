<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<body>
	<h3>这是主页。。。。</h3>
	<a href="mvchello/hello">1.这是一个匿名可以访问的连接</a>
	<br>
	<a href="login/hello">2.这是一个登录以后才能访问的地址</a>
	<br>
	<shiro:authenticated>
		<a href="login/look">3.这是一个需要登录才能看到的地址</a>
	</shiro:authenticated>
	<br>
	<a href="role/hello">4.这是一个需要admin角色权限才能访问的地址</a>
	<br>
	<shiro:hasRole name="admin">
		<a href="role/look">5.这是一个需要admin角色权限才能看到的地址</a>
	</shiro:hasRole>
	<br>
	<a href="permission/hello">6.这个连接配置是roles[roleA]</a>
	<br>
	<a href="permission/look">7.这个连接配置是roles[roleA,roleB]</a>
	<br>
	<a href="permission/ab">8.这个连接配置是perms[a:b]</a>
	<br>
	<a href="permission/abc">9.这个连接配置是perms[a:b:c]</a>
	<br>
	<a href="permission/abcd">10.这个连接配置是perms[a:b:c:d]</a>
	<br>
	<a href="permission/abce">11.这个连接配置是perms[a:b:c:e]</a>
	<br>
	<a href="permission/de">12.这个连接配置是perms[a:b:c:d,e]</a>
	<br>
	<a href="permission/ac">13.这个连接配置是perms[a:*:c]</a>
	<br>
	<a href="permission/afc">14.这个连接配置是perms[a:f:c]</a>
	<br> 以上一共12个连接。
	<br>
			<a href="urlpermission/list">显示所有URL与权限关联关系。</a>
	<br>
	<shiro:notAuthenticated>
		<a href="login">登录</a>
	</shiro:notAuthenticated>
	<br>
	<shiro:authenticated>
		<a href="logout">注销</a>
	</shiro:authenticated>
	<br> 你好
	<shiro:principal />
	<shiro:guest>客人</shiro:guest>
	<br>
	用户ab，拥有权限<strong>a:b</strong>
	<br>
	用户abc，拥有权限<strong>a:b:c</strong>
	<br>
	用户abcd，拥有权限<strong>a:b:c:d</strong>
	<br>
	用户abce，拥有权限<strong>a:b:c:e</strong>
	<br>
	用户de，拥有权限<strong>a:b:c:d,e</strong>
	<br>
	用户e，拥有2个权限<strong>a:b:c:d和e</strong>
	<br>
	用户ac，拥有权限<strong>a:*:c</strong>
</body>
</html>
