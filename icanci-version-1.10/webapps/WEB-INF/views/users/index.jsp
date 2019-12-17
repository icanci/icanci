<%@page import="java.util.UUID"%>
<%@page import="cn.icanci.domain.UserSpace"%>
<%@page import="java.math.BigInteger"%>
<%@page import="cn.icanci.domain.User"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ic"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>小璨小璨,管什么行船的技巧</title>
		<link rel="shortcut icon" href="/viewOfUsers/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/index.css" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/style.css" />
		<script src="/viewOfUsers/js/jQuery-1.11.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/base-style.css"/>
		<script src="/viewOfUsers/js/ui.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<script type="text/javascript">
		$(function() {
			$("#return_top_botton").click(function() {
				$("html,body").stop().animate({
					scrollTop: 0
				}, 1000);
			});
		})
	</script>
	<body>
		<!-- 设置固定定位 -->
		<!-- 左上 -->
		<div class="pos-left">
			

			<!-- 
			<div class="pos-left-s1">
				<ul>
					<a href="index-list-friends.jsp">
						<li class="img-friend"></li>
					</a>
					<li><a href="index-list-friends.jsp">我的好友</a></li>
				</ul>
			</div>
			-->
			<div class="pos-left-s1">
				<ul>
					<a href="/user/judgeAddFriends">
						<li class="img-friend img-friend-add"></li>
					</a>
					<li><a href="/user/judgeAddFriends">添加好友</a></li>
				</ul>
			</div>
			
			<!-- 
			<div class="pos-left-s1">
				<ul>
					<a href="">
						<li class="img-friend photos"></li>
					</a>
					<li><a href="">我的相册</a></li>
				</ul>
			</div> -->
			<!-- <div class="pos-left-s1">
				<ul>
					<a href="">
						<li class="img-friend newtexts"></li>
					</a>
					<li><a href="">我的通知</a></li>
				</ul>
			</div> -->
			<!-- <div class="pos-left-s1">
				<ul>
					<a href="">
						<li class="img-friend img-set"></li>
					</a>
					<li><a href="">设置</a></li>
				</ul>
			</div> -->
			<div class="pos-left-s1">
				<ul>
					<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1845666903&site=qq&menu=yes">
						<li class="img-friend customer-service"></li>
					</a>
					<li><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1845666903&site=qq&menu=yes">联系客服</a></li>
				</ul>
			</div>
		</div>

		<!-- 右上 -->
		<div class="pos-right">
			<ul>
				<a target="_blank" href="/user/questions" title="常见问题">
					<li class="questions">常见问题</li>
				</a>
				<a target="_blank" href="https://www.open-open.com/github/view/top" title="热门GIT">
					<li class="hots">热门GIT</li>
				</a>
				<a target="_blank" href="https://github.com/" title="我的发现">
					<li class="find">我的发现</li>
				</a>
			</ul>
		</div>
		<!-- 右中 -->
		<div class="pos-right-center">
			<p></p>
			<form action="/user/userseachPaging" method="post">
				<input type="text" name="seach" id="" value="" placeholder="搜一搜"/>
				<input type="submit" value="搜索小璨空间"/>
			</form>
		</div>

		<!-- 右下 -->
		<div class="pos-button" id="return_top_botton">
			<a href="javascript:;" title="返回顶部">&#94;</a>
		</div>

		<!-- 设置顶部导航栏 -->
		<div class="top-navigation">
			<div class="top-navigation-left">
				<div class="top-navigation-left-logo">
					<img src="/viewOfUsers/images/logo1.png">
				</div>
			</div>
			<div class="top-navigation-right">
				<div class="top-navigation-right-me">
					<ul>
						<li><a href="/user/indexUpdateinformation"><img src="${sessionScope.USER_IN_SESSION.headimage}" alt="点击修改你的资料"></a></li>
						<li><a href="/user/indexUpdateinformation"  title="点击修改你的资料">${sessionScope.USER_IN_SESSION.username}</a></li>
						<li><a href="/user/logout">退出登陆</a></li>
					</ul>
				</div>
			</div>
		</div>
		<%
			//生成随机口令
			String token = UUID.randomUUID().toString();
			//保存在session
			session.setAttribute("TOKEN_IN_SESSION", token);
			User user = (User) session.getAttribute("USER_IN_SESSION");
			//获取现在时间
			Long nowTime = System.currentTimeMillis();
			//获取加入的时间
			Long q = nowTime - user.getJointime();
			//获取天
			Long t = (q / 3600000 / 24)>=1?(q / 3600000 / 24):1;
			//获取用户 userspace 表的数据
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String longToDateTime = df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(nowTime), ZoneId.of("Asia/Shanghai")));
		%>
		<!-- 设置中间主体部分 -->
		<div class="center-body">
			<div class="center-body-top">
				<ul>
					<li class="self-location"><img src="${sessionScope.USER_IN_SESSION.headimage}"></li>
					<li>${sessionScope.USER_IN_SESSION.username}</li>
					<li>个性签名: ${sessionScope.USER_IN_SESSION.personalizedSignature}</li>
					<li>已经加入小璨 <%=t%> 天</li>
				</ul>
			</div>
			<div class="center-body-center">
				<div class="sendMessage">
				<script type="text/javascript">
						function subtext(){
							var text = $("#userMessages").val();
							var file = $("#uploadImg").val();
							var textres = text.trim()
							if(textres == "" || file == ""){
								icanci.alert('温馨提示',"你要发送的小璨和图片和文字不可以同时为空或空格");
								return false;
							}else{
								return true;
							}
						}
					</script>
					<form action="/user/saveUserSpacePost" method="post"  enctype="multipart/form-data" >
						<input type="hidden" name="token" value="<%=token%>">
						<div class="box1">
							<textarea class="userMessages" name="userMessages" id="userMessages" style="resize:none" rows="3" cols="50" placeholder="说点什么吧...  可以选择图片...也可以不选择..."></textarea>
						</div>
						<div class="box1 upimg">
							<img src="/" alt="" id="selectImg">
						</div>
						<div class="box1 boxfile">
							<input type="file" id="uploadImg" onchange="xmTanUploadImg(this);" accept="image/png, image/jpeg, image/gif, image/jpg"
							 class="file" name="file" value="" />
						</div>
						<div class="box1 boxsub">
							<input type="submit" onclick="return subtext();" name="" id="" value="发表小璨" />
						</div>
					</form>
				</div>
				<div class="center-body-foot">
					<div class="user-blank"></div>
					<!-- 这里是开始循环 -->

					<ic:forEach items="${pageResult.listData}" var="user">
					<ic:set var="flag" value="true" />
					<ic:if test="${pageResult.listData == null}">
						<ic:set var="flag" value="false"/>
						<div class="user-message">
							<div class="out">
								<ul>
									<li class="l10"><a href="">你还没有发布任何小璨小璨 赶快去发布一个吧 </a></li>
									<li>你还没有发布任何小璨小璨 赶快去发布一个吧</li>
								</ul>
							</div>
						</div>
					</ic:if>
						<div class="user-message">
							<div class="out">
								<ul>
									<li class="user-img"><img src="${sessionScope.USER_IN_SESSION.headimage}"></li>
									<li class="l10">${sessionScope.USER_IN_SESSION.username}</li>
									<li class="l10">个性签名: ${sessionScope.USER_IN_SESSION.personalizedSignature} </li>
									<li class="l10">${user.outPrint}</li>
									<li class="l10"><a href="/user/deleteSelfUserSpace?deleteUserSpaceID=${user.id}">删除此条小璨</a></li>
								</ul>
							</div>
							<div class="info">
								<div class="message">
									<!-- 从用户数据读取的文字 -->
									<ic:if test="${user.userMessages!=null}">
										<p>
											${user.userMessages}
										</p>
									</ic:if>
									<p>&nbsp;</p>
								</div>
								<ic:if test="${user.userImage!=null}">
									<div class="imgs">
									<!-- 从用户信息读取的图片 -->
										<img src="${user.userImage}">
									</div>
								</ic:if>
								
							</div>
							<div class="clearfloat"></div>
						</div>
						
					</ic:forEach>
					
					<!-- 这里结束循环 -->
					
					<!-- 底部盒子导航 -->
					<div class="jump">
						<p>	
							<a href="/user/userSpaceInfomationPages?currentPage=${pageResult.beginPage}&pageSize=${PageSize}">首页</a> 
							<a href="/user/userSpaceInfomationPages?currentPage=${pageResult.prevPage}&pageSize=${PageSize}">上一页</a> 
							<a href="/user/userSpaceInfomationPages?currentPage=${pageResult.nextPage}&pageSize=${PageSize}">下一页</a> 
							<a href="/user/userSpaceInfomationPages?currentPage=${pageResult.totalPage}&pageSize=${PageSize}">尾页</a> 
							当前是 ${pageResult.currentPage}/${pageResult.totalPage} 页 一共 ${pageResult.totalCount} 条数据 
						</p> 
						<form action="/user/userSpaceInfomationPages" method="post">
							跳转到第 &nbsp; <input class="numget" type="number" value="${pageResult.currentPage}" min="1" max="${pageResult.totalPage}" name="currentPage" id="" />  &nbsp;页
							 &nbsp;<input type="submit" class="numsub" value="跳转"/>
						</form>
					</div>
					<div class="foot">
						<p>&nbsp;</p>
						<p><a href="">加入我们</a> <a href="">关于我们</a> <a href="">联系我们</a> <a href="">技术交流</a> <a href="">商务合作</a> </p>
						<p>&copy; ICANCI 版权所有 一切行为解释权归 ICANCI 所有</p>
					</div>
				</div>
			</div>
		</div>
		<!-- 底部导航 -->

		<script>
			function xmTanUploadImg(obj) {
				var file = obj.files[0];
				var reader = new FileReader();
				reader.readAsDataURL(file);
				reader.onload = function(e) { //成功读取文件
					var img = document.getElementById("selectImg");
					img.src = e.target.result; //或 img.src = this.result / e.target == this
				};
			}
		</script>
	</body>
</html>
