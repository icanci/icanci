<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>小璨小璨,跟我走不迷路~~</title>
<link rel="stylesheet" type="text/css" href="/css/index-feedback.css" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script src="/js/jquery-1.8.2.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/ui.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/index-register.js" type="text/javascript"
	charset="utf-8"></script>
<script src="/js/index.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function IsNull() {
		var str = document.getElementById('str').value.trim();
		if (str.length == 0) {
			icanci.alert('温馨提示', '对不起,你输入的反馈主题不能为空或者为空格!', 'favicon');
		}
	}

	function IsNull2() {
		var str = document.getElementById('str2').value.trim();
		if (str.length == 0) {
			icanci.alert('温馨提示', '对不起,你输入吐槽不能为空或者为空格!', 'favicon');
		}
	}

	function doSubmitFrom() {
		var str = document.getElementById('str').value.trim();
		var str2 = document.getElementById('str2').value.trim();
		if (str.length != 0 && str2.length != 0) {
			document.getElementById("form").submit();
			icanci.alert('温馨提示', '谢谢您的反馈意见,小璨会认真查看并且尽量满足您的要求~~', 'favicon');
		} else {
			icanci.alert('温馨提示', '对不起,你输入反馈主题或者吐槽不能为空或者为空格!', 'favicon');
			return false;
		}
	}
</script>
</head>
<body>

	<%
		//生成随机口令
		String token = UUID.randomUUID().toString();
		//保存在session
		//为了防止表单的重复提交
		session.setAttribute("TOKEN_IN_SESSION", token);
	%>
	<form class="box" action="/user/feedback" method="post" id="form"
		onsubmit="return doSubmitFrom();">
		<h2>界面意见反馈</h2>
		<input type="hidden" name="token" value="<%=token%>">
		<input type="hidden" name="feedbackTime" id="" value="" /> <input
			type="text" id="str" name="feedbackTheme" placeholder="反馈主题"
			onblur="IsNull();" /> <input type="text" id="str2"
			name="feedbackContent" placeholder="你要吐槽些什么?请告诉小璨,小璨会看的哦?争取尽快解决~~~"
			onblur="IsNull2();" /> <input type="submit"
			onclick="doSubmitFrom();" value="意见反馈" />
		<p>
			<a href="index-register.jsp">免费注册账号</a> <span>|</span> <a
				href="index-user-manual.html">用户手册</a>
		</p>
		<p>
			<a href="index.jsp">已有账户? 去登陆</a>
		</p>
	</form>
</body>
</html>