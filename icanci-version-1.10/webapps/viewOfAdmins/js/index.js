function checkUsername() {
	var username = document.getElementById('username').value;
	if (!(username.length >= 4 && username.length <= 12)) {
		icanci.alert('温馨提示', '用户名格式不正确,请输入有效的用户名', 'favicon');
		document.getElementById('username').value = "";
	}
}
function returnNotEmpty(str) {
	return str.replace(/\s*/g, "");
}

function checkPassword1() {
	var password1 = document.getElementById('password1').value;

	if (password1 == "") {
		icanci.alert('温馨提示', '密码不能为空', 'favicon');
		return false;
	}
	if (!(returnNotEmpty(password1).length >= 6 && returnNotEmpty(password1).length <= 16)) {
		icanci.alert('温馨提示', '密码格式不正确,请输入有效的密码', 'favicon');
		document.getElementById('password1').value = "";
		return false;
	}
}

function checkPassword2() {
	var password1 = document.getElementById('password1').value;
	var password2 = document.getElementById('password2').value;

	if (password1 == "") {
		icanci.alert('温馨提示', '密码不能为空', 'favicon');
		return false;
	}

	if (password2 == "") {
		icanci.alert('温馨提示', '密码不能为空', 'favicon');
		return false;
	}

	if (!(returnNotEmpty(password1).length >= 6 && returnNotEmpty(password1).length <= 16)) {
		icanci.alert('温馨提示', '密码格式不正确,请输入有效的密码', 'favicon');
		document.getElementById('password1').value = "";
		return false;
	}
	if (!(returnNotEmpty(password2).length >= 6 && returnNotEmpty(password2).length <= 16)) {
		icanci.alert('温馨提示', '密码格式不正确,请输入有效的密码', 'favicon');
		document.getElementById('password2').value = "";
		return false;
	}
	if (password1 != password2) {
		icanci.alert('温馨提示', '两次输入的密码不一致', 'favicon');
		document.getElementById('password2').value = "";
		return false;
	}
}

function checkEmail() {
	//获取email的值
	var email = document.getElementById("email").value;
	// var regObj = new RegExp("^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$");
	var reg = new RegExp("^[A-Za-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
	//正则表达式
	if (!reg.test(email)) {
		icanci.alert('温馨提示', '邮箱格式不正确', 'favicon');
	}
}
