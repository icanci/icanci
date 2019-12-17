<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>小璨小璨,跟我走不迷路~~</title>
<link rel="stylesheet" type="text/css" href="/css/index-email.css" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script src="/js/jquery-1.8.2.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/ui.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/index-register.js" type="text/javascript"
	charset="utf-8"></script>
<script src="/js/index.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/getXMLHttpRequest.js" type="text/javascript"></script>
<script src="/js/sendEmailAndUserCode.js" type="text/javascript"></script>
</head>
<body>

<%
	//销毁session
	// 如果没有session 强制销毁session 就会报异常  IllegalStateException
	//session.invalidate();   
	//生成随机口令
	String token = UUID.randomUUID().toString();
	//保存在session
	session.setAttribute("TOKEN_IN_SESSION", token);
%>

	<form class="box" action="/user/loginByEmail" method="post">
		<h2>登陆 小璨小璨</h2>
		<div class="getVerificationCode username">
			<div class="getCode">
				<p>
					<a href="/index.jsp">账号登陆</a>
				</p>
			</div>
			<div class="reset email">
				<p>
					<a href="">邮箱登陆</a>
				</p>
			</div>
		</div>
		<input type="hidden" name="token" value="<%=token%>">
		<input type="email" id="email"
			reg="[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?"
			tip="请输入正确的邮箱 如 icanci@163.com" name="email" placeholder="邮箱"
			onblur="checkEmail();" /> <a href="javaScript:checkUserEmailCode();"><input
			type="button" value="发送验证码" /></a> <input type="text" name="usercode"
			id="usercode" reg="^[0-9]{6}$" tip="请查看你的邮箱信件 输入正确的验证码" value=""
			onblur="checkUserEmailCodes();" placeholder="请输入6位数验证码" /> <input
			type="text" name="userkey" id="userkey" reg="^[a-zA-Z0-9]{5}$"
			tip="请输入正确的图片验证码" value="" onblur="checkUserkey();"
			placeholder="请输入图片验证码" />
		<div class="getVerificationCode">
			<div class="getCode">
				<img id="codeImg" alt="看不清?换一张" src="/user/outputimagecode"
					onclick="toChange();">
			</div>
			<div class="reset">
				<p>
					<a href="javaScript:toChange();">看不清?</a>
				</p>
			</div>
		</div>
		<input type="submit" value="登陆小璨" />
		<p>
			<a href="index-not-get-code.html">收不到验证码?</a> <span>|</span> <a
				href="index-reset-password.jsp">忘记密码?</a> <span>|</span> <a
				href="index-register.jsp">免费注册账号</a>
		</p>
		<p>
			<a href="index-user-manual.html">用户手册 </a> &nbsp; <a
				href="index-feedback.jsp">界面意见反馈</a>
		</p>
	</form>
</body>
</html>
