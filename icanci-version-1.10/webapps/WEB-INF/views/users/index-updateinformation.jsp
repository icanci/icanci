<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<!-- 登陆之后先跳转到 servlet 判断 是否由session 有就直接跳转
			有就直接登陆
	 -->
	<head>
		<meta charset="utf-8">
		<title>小璨小璨,修改资料</title>
		<link rel="shortcut icon" href="/viewOfUsers/favicon.ico" type="image/x-icon" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/viewOfUsers/css/index-updateinformation.css" />
		<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
		<link rel="stylesheet" href="https://www.jq22.com/jquery/bootstrap-3.3.4.css">
		<script src="https://www.jq22.com/jquery/bootstrap-3.3.4.js"></script>
		<script src="/viewOfUsers/js/index-updateinfo.js" type="text/javascript" charset="utf-8"></script>
	</head>
	<body>
		<div class="top-navigationme">
			<div class="top-navigationme-left">
				<div class="top-navigationme-left-logo">
					<img src="/viewOfUsers/images/logo1.png">
				</div>
			</div>
			<div class="top-navigationme-right">
				<div class="top-navigationme-right-me">
					<ul>
						<li><a href=""><img src="${sessionScope.USER_IN_SESSION.headimage}"></a></li>
						<li><a>${sessionScope.USER_IN_SESSION.username}</a></li>
						<li><a href="/user/logout">退出登陆</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="center">
			<form action="/user/updateInformation" method="post" enctype="multipart/form-data">
				<h3>在这里可以查看/修改你的个人资料</h3>
				<hr>
				<div class="inputs c1">
					 <input type="text" name="username" id="" value="${sessionScope.USER_IN_SESSION.username}" placeholder="请输入你的用户名 4-6位" />
				</div>
				<div class="inputs c2">
					 <input class="personalizedSignature" type="text" name="personalizedSignature" id="" value="${sessionScope.USER_IN_SESSION.personalizedSignature}" placeholder="请输入你的个性签名 1 - 20个字以内" />
				</div>
				<div class="inputs c3">
					<input class="personalizedSignature" type="number" name="age" id="" value="${sessionScope.USER_IN_SESSION.age}" placeholder="请输入你的年龄" />
				</div>
				<div class="box">
					<div class="inputs images">
						请选择你的头像
						<div class="im">
							<input type="file" id="uploadImg" onchange="xmTanUploadImg(this);" accept="image/png, image/jpeg, image/gif, image/jpg"
							 class="file" name="file" value="" />
						</div>
						<img src="" id="selectImg">
					</div>
				</div>

				<div class="inputs">
					<input type="submit" class="sub" value="提交" />
				</div>
			</form>
		</div>


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
