<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ic"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>小璨小璨,常见问题~</title>
		<link rel="shortcut icon" href="/viewOfUsers/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/index-questions.css"/>
	</head>
	<body>
		<div class="top-navigation">
			<div class="top-navigation-left">
				<div class="top-navigation-left-logo">
					<img src="/viewOfUsers/images/logo1.png">
				</div>
			</div>
			<div class="top-navigation-right">
				<div class="top-navigation-right-me">
					<ul>
						<li><a href="/user/judgeImproveInformation"><img src="${sessionScope.USER_IN_SESSION.headimage}" alt="点击修改你的资料"></a></li>
						<li><a >${sessionScope.USER_IN_SESSION.username}</a></li>
						<li><a href="/user/logout">退出登陆</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="easy-questions">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<h3>1 如何更换头像以及资料?</h3>
			<p>&nbsp;</p>
			<img src="/viewOfUsers/images/q1.png" >
			<p>&nbsp;</p>
			<p>下面所有的数据都要填写清楚哦</p>
			<p>&nbsp;</p>
			<img src="/viewOfUsers/images/q4.png" >
			<p>&nbsp;</p>
			<h3>2 如何发送小璨?</h3>
			<p>&nbsp;</p>
			<p>1.图中的 1 可以发送文字 </p>
			<p>2.图中的 2 用来显示 3 选择的图片</p>
			<p>3.图中的 3 用来选择图片</p>
			<p>4.图中的 4 用来发表图片</p>
			<p>&nbsp;</p>
			<img src="/viewOfUsers/images/q2.png" >
			<p>&nbsp;</p>
			<h3>3 如何安全退出小璨?</h3>
			<p>&nbsp;</p>
			<p>点击图中 退出登陆 即可安全退出</p>
			<p>&nbsp;</p>
			<img src="/viewOfUsers/images/q3.png" >
		</div>
	</body>
</html>
