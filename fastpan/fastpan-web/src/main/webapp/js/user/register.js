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
							nickName : {
								validators : {
									notEmpty : {
										message : '昵称不能为空'
									},
									stringLength : {
										min : 1,
										max : 10,
										message : '请输入长度为1-10的昵称'
									}
								}
							},
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
							passwordAgain : {
								validators : {
									notEmpty : {
										message : '密码不能为空'
									},
									identical : {
										field : 'password',
										message : '两次密码不一致'
									}
								}
							}
						}
					});
});

function daojishi(){
	var a=60;
	tme=setInterval(function(){
		a--;			
		var time=document.getElementById('sendCode');
		$(time).text(a)
		if(a==0){
			 clearTimeout(tme);
			 $(time).text("重新发送")
		}
	},1000)
}


/**
 * 获取验证码
 * 
 * @returns
 */
function getCode() {
	daojishi();
	var email = $('form input[name="email"]').val();
	$.ajax({
		url : '../verify/sendEmail',
		data : "{\"requestContext\":\"" + email + "\"}",
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			var message = data.success ? '验证码发送成功' : data.message;
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
		},
		error : function(error) {
			BootstrapDialog.show({
				title : "消息",
				message : "验证码发送失败,服务器出错了",
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
/**
 * 注册
 * 
 * @returns
 */
function register() {
	if (submit == 1) {
		return;
	}
	submit++;
	var nickName = $('form input[name="nickName"]').val();
	var email = $('form input[name="email"]').val();
	var password = $('form input[name="password"]').val();
	var code = $('form input[name="code"]').val();
	$.ajax({
		url : 'registerCode',
		data : "{\"requestContext\":{\"email\":\"" + email
				+ "\",\"password\":\"" + password + "\",\"nickName\":\""
				+ nickName + "\",\"code\":\"" + code + "\"}}",
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit--;
			var message = data.success ? '注册成功' : data.message;
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
			BootstrapDialog.show({
				title : "消息",
				message : "注册失败,服务器出错了",
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