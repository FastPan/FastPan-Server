submit = 0;
$(function() {
	window.addEventListener("keydown",function(e){
		if(e.keyCode==13){
			findPassword();//处理事件
		}
	});
	$('.nav-sidebar li').click(function() {
		$('.nav-sidebar li').removeClass("active");
		$(this).addClass("active");
	});
	$('#left-sidebar a').click(function() {
		$('#left-sidebar a').removeClass("active");
		$(this).addClass('active');
		$('#tab-myinfo').hide();
		$('#tab-head-img').hide();
		$('#tab-pass').hide();
		$('#tab-' + $(this).attr('id')).show();
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
							verificationCode : {
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
							newPassword : {
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
										field : 'newPassword',
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

function getCode() {
	daojishi();
	if (submit == 1) {
		return;
	}
	submit++;
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
			submit--;
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
			submit--;
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

function findPassword() {
	if (submit == 1) {
		return;
	}
	submit++;
	var password = $('form input[name="newPassword"]').val();
	var email = $('form input[name="email"]').val();
	var code = $('form input[name="verificationCode"]').val();
	$.ajax({
		url : '../user/forgetPassEmail',
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
			console.log(data);
			var message = data.success ? data.result : data.message;
			BootstrapDialog.show({
				title : "消息",
				message : message,
				onshown : function(dialog) {

					setTimeout(function() {
						$('button[type="submit"]').removeAttr("disabled");
						dialog.close();
						if (data.success) {
							window.location.href = "../index";
						}
					}, 1000);

				}

			});

		},
		error : function(error) {
			submit--;
			console.log("========"+data);
			BootstrapDialog.show({
				title : "消息",
				message : "服务器出错了",
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