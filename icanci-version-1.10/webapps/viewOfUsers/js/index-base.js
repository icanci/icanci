$(function() {
	//表单的提交事件
	$("form").submit(function() {
		//判断结果
		var isOk = true;
		//校验每一个input标签
		$(this).find("[reg]").each(function() {
			//获取输入的值
			var val = $(this).val();
			//获取提示信息
			var tip = $(this).attr("tip");
			if ( $.trim(val) == "" || $.trim(val) == null) {
				isOk = false;
				// alert(tip);
				icanci.alert('温馨提示', tip, 'favicon');
				return false;
			}
		})
		return isOk;
	})
})
