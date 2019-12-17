<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ic"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>温馨提示</title>
	<link rel="stylesheet" type="text/css" href="/css/resultInformation.css" />
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
	</head>
	<script type="text/javascript">
		window.onload = function() {
			icanci.alert('温馨提醒', "UPDATE_SUSSESS_IN_SESSION", 'favicon');
		}
	</script>
	<body>
	
		<form class="box" action="#" method="post">
			<h2 class="h2">温馨提醒</h2>
			<p><img src="/favicon.ico" alt=""></p>
			<p class="p1">${sessionScope.UPDATE_INFO_SESSION}</p>
			<p> <a href="/index-user-manual.html">用户手册 </a> <span>|</span> <a href="/index-register.jsp">免费注册账号</a> <span>|</span> <a href="/index-feedback.jsp">界面意见反馈</a> </p>
			<p><a href="/index.jsp">已有账户? 去登陆</a></p>
		</form>
	</body>
</html>