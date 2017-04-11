submit = 0;
$(function() {
	$('form').submit(function() {
		return false;
	});
	$('form')
			.bootstrapValidator(
					{
						message : '输入非法',
						feedbackIcons : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						fields : {
							email : {
								validators : {
									notEmpty : {
										message : '邮箱不能为空'
									},
									emailAddress : {
										message : '邮箱地址格式有误'
									},
									stringLength : {
										min : 6,
										max : 40,
										message : '请输入长度为6-40的邮箱'
									}
								}
							},
							password : {
								validators : {
									notEmpty : {
										message : '密码不能为空'
									},
									regexp : {
										regexp : /(?=.*[a-z])(?=.*\d)(?=.*[#@!~%^&*])[a-z\d#@!~%^&*]{8,16}/i,
										message : '请输入8-16位,包含数字,字母及以下符号其中之一的密码：~!@#$%^&*'
									}
								}
							},
							code : {
								validators : {
									notEmpty : {
										message : '验证码不能为空'
									},
									regexp : {
										regexp : /^[a-zA-Z0-9]{6}$/,
										message : '请输入6位由数字和字母组成的验证码'
									}
								}
							}
						}
					});
	$('#pictureCode').click(function() {
		$('#pictureCode').attr('src', '../verify/pictureCode?' + new Date().getTime());
	});
});
function login() {
	if (submit == 1) {
		return;
	}
	submit++;
	var email = $('form input[name="email"]').val();
	var password = $('form input[name="password"]').val();
	var code = $('form input[name="code"]').val();
	$.ajax({
		url : 'loginCode',
		data : "{\"requestContext\":{\"email\":\"" + email
				+ "\",\"password\":\"" + password + "\",\"code\":\"" + code
				+ "\"}}",
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit--;
			//alert(JSON.stringify(data));
			var message = data.success ? '登录成功' : data.message;
			BootstrapDialog.show({
				title : "消息",
				message : message,
				onshown : function(dialog) {
					setTimeout(function() {
						$('button[type="submit"]').removeAttr("disabled");
						dialog.close();
						if (data.success) {
							
							window.location.href = "../main/index";
						}
					}, 1000);

				}
			});
		},
		error : function(error) {
			submit--;
			var message = data.success ? '登录成功' : data.message;
			BootstrapDialog.show({
				title : "消息",
				message : message,
				onshown : function(dialog) {
					setTimeout(function() {
						$('button[type="submit"]').removeAttr("disabled");
						dialog.close();
					}, 1000);
				}
			});
		}
	});
}