<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ic"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>小璨用户</title>
		<link rel="shortcut icon" href="/viewOfAdmins/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="/viewOfAdmins/css/userinfo.css"/>
	</head>
	<body>
		<div class="mainpage">
			<div class="top">
				<span class="cn same">ICANCI.CN</span>
				<span class="user same">用户信息</span>
				<a href="/admin/list" style="color: red;">点此进入意见反馈管理界面</a>
			</div>
			<div class="content">
				<div class="content-top">
					<a href="##">全部用户</a>
					<div class="info">
						<div class="info-top">
							<span >头像</span>
							<span class="">用户名</span>
							<span class="">年龄</span>
							<span class="">个性签名</span>
							<span class="">E-mail</span>
							<span class="">发送邮件</span>
							<span class="">发出警告</span>
						</div>
						<div class="info-content">
						<ic:forEach items="${USRE_LIST_IN_SESSION}" var="user">
							<div class="userinfo">
								<ul>
									<ic:if test="${user.headimage!=null}">
										<li><img src="${user.headimage}" style="width: 60px; height: 60px; bottom: 20px;"></li>
									</ic:if>
									<ic:if test="${user.headimage == null}">
										<li><img src="/viewOfAdmins/images/favicon.jpg" style="width: 60px; height: 60px; bottom: 20px;"></li>
									</ic:if>
									<li class="s1">${user.username}</li>
									<li class="s2">${user.age}</li>
									<ic:if test="${user.personalizedSignature!=null}">
										<li class="s4">${user.personalizedSignature}</li>
									</ic:if>
									<ic:if test="${user.personalizedSignature == null}">
										<li class="s4" style="color:blue">该用户没有设置头像和签名</li>
									</ic:if>
									<li class="s5">${user.email}</li>
									<li class="s6"><a href="">发送邮件</a></li>
									<li class="s7"><a href="">示警</a></li>
								</ul>
							</div>
							</ic:forEach>
						</div>
					</div>
				</div>
			</div>
			<div class="footer">
				
			</div>
		</div>
	</body>
</html>
