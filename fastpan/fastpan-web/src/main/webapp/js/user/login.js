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
							}
						}
					});
});
function login() {
	var email = $('form input[name="email"]').val();
	var password = $('form input[name="password"]').val();
	$.ajax({
		url : 'loginAccount',
		data : "{\"requestContext\":{\"email\":\"" + email
				+ "\",\"password\":\"" + password + "\"}}",
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			BootstrapDialog.show({
				title : "消息",
				message : data.message,
				onshown : function(dialog) {
					setTimeout(function() {
						$('button[type="submit"]').removeAttr("disabled");
						dialog.close();
					}, 1000);

				}
			});
		},
		error : function(error) {
			BootstrapDialog.show({
				title : "消息",
				message : "登录失败,服务器出错了",
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