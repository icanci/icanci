<%@page import="java.time.ZoneId"%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.UUID"%>
<%@page import="cn.icanci.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ic" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>小璨小璨,查找好友~~</title>
		<link rel="shortcut icon" href="/viewOfUsers/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/index-add-friends.css" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/base-style.css"/>
		<script src="/viewOfUsers/js/jquery-1.8.2.js" type="text/javascript" charset="utf-8"></script>
		<script src="/viewOfUsers/js/ui.js" type="text/javascript" charset="utf-8"></script>
		<script src="/viewOfUsers/js/index-base.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<script type="text/javascript">
		function checkSeach(){
			var seach= document.getElementById('seach').value;
			if (seach == "") {
				document.getElementById('seach').value = "";
				icanci.alert('温馨提示', '你的输入不能为空或者为空格', 'favicon');
				return false;
			}
		}
	</script>
	
	<body>
		<!-- 昵称搜索 -->
		<!-- 顶部导航 -->
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
		<!-- 中间部分 -->
		<div class="div1">
			<div class="inner1">
				<form action="/user/addFriends" method="post">
				<%
					//销毁session
					// 如果没有session 强制销毁session 就会报异常  IllegalStateException
					//session.invalidate();   
					//生成随机口令
					String token = UUID.randomUUID().toString();
					//保存在session
					session.setAttribute("TOKEN_IN_SESSION", token);
					
					String longToDateTime = "";
					//获取用户 userspace 表的数据
					
					User user = (User)session.getAttribute("USER_BY_SEACH_SESSION");
					if(user !=null && user.getId()!=null){
						Long joinTime = user.getJointime();
						DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						longToDateTime = df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(joinTime), ZoneId.of("Asia/Shanghai")));
					}
					
					
				%>
					<input type="hidden" name="token" value="<%=token%>">
					<input type="text" name="seach" tip="你的输入不能为空或者为空格" onblur="checkSeach();" id="seach" value="${sessionScope.USER_INPUT_VALUE_IN_SESSION}" placeholder="请输入你要搜索的好友的昵称或QQ邮箱" />
					<br>
					<input type="submit" value="搜一搜" />
				</form>
			</div>
			<h3>搜索结果</h3>
			<div class="inner2">
				<!-- 如果没有 就显示没有搜到 如果有就显示 搜到了 -->
				<!-- 现在已经 -->
				
				<!-- 获取加入的时间 -->
				
				<ic:if test="${USER_BY_SEACH_SESSION == null || USER_BY_SEACH_SESSION.id == null}">
				<ul>
					<li><a>你要查找的好友暂时还没有加入小璨小璨 请换个邮箱或者名字搜索吧</a></li>
					<li><a href="/user/userspace">返回首页</a></li>
				</ul>
				</ic:if>
				<ic:if test="${sessionScope.USER_BY_SEACH_SESSION != null && USER_BY_SEACH_SESSION.id != null}">
				<ul>
					<li><img src="${sessionScope.USER_BY_SEACH_SESSION.headimage}"></li>
					<li>昵称 : ${sessionScope.USER_BY_SEACH_SESSION.username}</li>
					<li>个性签名 : ${sessionScope.USER_BY_SEACH_SESSION.personalizedSignature}</li>
					<li>在 <%=longToDateTime%> 加入小璨</li>
					<li><a href="">添加好友</a></li>
					<li><a href="/user/userspace">返回首页</a></li>
				</ul>
				</ic:if>
			</div>
		</div>
	</body>
</html>
