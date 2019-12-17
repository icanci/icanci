// 定义全局变量
var code = "";

window.onload = function() {
	// 1.获取XMLHttpRequest对象
	var req = getXMLHttpRequest();
	// 4.相应结果 回调函数 是否连接成功
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				// 服务器反应正常
				// 获取服务器返回的值
				code = req.responseText;
				// icanci.alert('温馨提示', code, 'favicon');
			}
		}
	}
	// 2.建立连接
	req.open("get", "/user/outputcode?" + new Date().getTime()); // 添加随机数
	// 获取不同的session
	// 避免重复
	// 3.发送请求
	req.send(null);
}

function sleep(numberMillis) {
	var now = new Date();
	var exitTime = now.getTime() + numberMillis;
	while (true) {
		now = new Date();
		if (now.getTime() > exitTime) {
			return false;
		}
	}
}

function toChange() {
	// 首先加载图片 然后再获取值
	document.getElementById("codeImg").src = "/user/outputimagecode?"
			+ new Date().getTime();
	// 因为jsp加载速度比session更新的速度快 所以jsp获取的是之前session中的数据 所以让 jsp等待5毫秒再获取
	// 可能方式的问题 多线程访问 导致验证码混乱
	// 睡5毫秒
	sleep(5);

	// 1.获取XMLHttpRequest对象
	var req = getXMLHttpRequest();
	// 4.相应结果 回调函数 是否连接成功
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				// 服务器反应正常
				// 获取服务器返回的值
				code = req.responseText;
				// icanci.alert('温馨提示', code, 'favicon');
			}
		}
	}
	// 2.建立连接
	req.open("get", "/user/outputcode?" + new Date().getTime()); // 建立连接
	// 3.发送请求
	req.send(null);
}

function checkUserkey() {
	var userCode = document.getElementById('userkey').value;
	if (code.toUpperCase() != userCode.toUpperCase()) {
		icanci.alert('温馨提示', '你输入的验证码不正确,请重新输入,不区分大小写', 'favicon');
		// 修改 重新加载图片 和 验证码
		// toChange();
	}
}

// 检验邮箱的验证码
function checkUserEmailCode() {
	// js生成一个表单
	// 把邮箱的数据传送到servlet中
	// servlet接收数据 然后 发送邮箱

	//可以通过get方法传输邮箱号码
	
	var userEmail = document.getElementById('email').value;
	
	if(userEmail == ""){
		icanci.alert('温馨提示', '请输入正确的邮箱 如: icanci@163.com', 'favicon');
		return ;
	}
	// 建议睡5毫秒
	sleep(5);
	// 1.获取XMLHttpRequest对象
	var req = getXMLHttpRequest();
	// 4.相应结果 回调函数 是否连接成功
	req.onreadystatechange = function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				// 服务器反应正常
				// 获取服务器返回的值
				icanci.alert('温馨提示', '验证码已经发送,请查看邮箱,验证码十分钟之内有效', 'favicon');
				emailCode = req.responseText;
			}
		}
	}
	// 2.建立连接
	req.open("get", "/user/checkemail?email="+userEmail); // 添加随机数    + new Date().getTime()    get 传值
	
	// 获取不同的session
	// 避免重复
	// 3.发送请求
	req.send(null);

}
function checkUserEmailCodes() {
	// 获取用户传入的值
	var userEmailCode = document.getElementById('usercode').value;
	if (emailCode != userEmailCode) {
		icanci.alert('温馨提示', '你输入的验证码不正确,请重新输入,验证码十分钟之内有效', 'favicon');
	}
}