$(function() {
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