$(function() {
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
	var options = {
		thumbBox : '.thumbBox',
		spinner : '.spinner',
		imgSrc : '../images/avatar.png'
	};
	var cropper = $('.imageBox').cropbox(options);
	$('#upload-file').on('change', function() {
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
		}
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	})
	$('#btnCrop')
			.on(
					'click',
					function() {
						var img = cropper.getDataURL();
						$('.cropped').html('');
						$('.cropped')
								.append(
										'<img src="'
												+ img
												+ '" align="absmiddle" style="width:64px;margin-top:4px;border-radius:64px;box-shadow:0px 0px 12px #7E7E7E;" ><p>64px*64px</p>');
						$('.cropped')
								.append(
										'<img src="'
												+ img
												+ '" align="absmiddle" style="width:128px;margin-top:4px;border-radius:128px;box-shadow:0px 0px 12px #7E7E7E;"><p>128px*128px</p>');
						$('.cropped')
								.append(
										'<img src="'
												+ img
												+ '" align="absmiddle" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
					});
	$('#btnZoomIn').on('click', function() {
		cropper.zoomIn();
	});
	$('#btnZoomOut').on('click', function() {
		cropper.zoomOut();
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
							},
							oldPassword : {
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