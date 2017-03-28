submit = 0;
submit2 = 0;
submit3 = 0;
submit4 = 0;
submit5 = 0;
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
			imgSrc : '../plug-in/cropbox/images/avatar.png'
		};
	var cropper = $('.imageBox').cropbox(options);
	$('#fileImage').on('change', function() {
		var reader = new FileReader();
		reader.onload = function(e) {
			options.imgSrc = e.target.result;
			cropper = $('.imageBox').cropbox(options);
		}
		
		reader.readAsDataURL(this.files[0]);
		this.files = [];
	});
	$('#btnCrop').on('click',function() {
		                $(".cropped").empty();
						var img = cropper.getDataURL();
						console.log("图像路径："+img);
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
												+ '" align="absmiddle" id = "imgInfor" style="width:180px;margin-top:4px;border-radius:180px;box-shadow:0px 0px 12px #7E7E7E;"><p>180px*180px</p>');
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
										field : 'newPassword',
										message : '两次密码不一致'
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
							}
						}
					});
});

/*$(function() {
	$("#pic").click(function () {
	$("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
	$("#upload").on("change",function(){
	var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
	if (objUrl) {
		$.ajax({
			url:"../user/uploadImage",
			type:"post",
			data:objUrl,
			success:function(msg){
				console.log(msg);
			},
			error:function(msg){
				console.log(msg);
			}
		})
	$("#pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
		console.log($("#pic").attr("src"));
	}
	});
	});
	});
	 
	//建立一個可存取到該file的url
	function getObjectURL(file) {
	var url = null ;
	if (window.createObjectURL!=undefined) { // basic
	url = window.createObjectURL(file) ;
	} else if (window.URL!=undefined) { // mozilla(firefox)
	url = window.URL.createObjectURL(file) ;
	} else if (window.webkitURL!=undefined) { // webkit or chrome
	url = window.webkitURL.createObjectURL(file) ;
	}
	return url ;
	}*/

//图片上传
//function ajaxFileUpload()
//{
//    
//    $.ajaxFileUpload({
//            url:'../user/uploadImage',//用于文件上传的服务器端请求地址
//            secureuri:false ,//一般设置为false
//            fileElementId:'upload',//文件上传控件的id属性  <input type="file" id="upload" name="upload" />
//            dataType: 'text',//返回值类型 一般设置为json
//            success: function (message)  //服务器成功响应处理函数
//            {
//            	$("#pic").attr("src",message);
//                /*$("#tool_icon").val(message);
//                console.log(message);
//                $("#haha").attr("src",message.result);*/
//            },
//            error: function (data, status, e)//服务器响应失败处理函数
//            {
//                alert(e);
//                console.log("+++++++++++++++++"+message.result);
//                $("#pic").attr("src",message);
//            }
//        });
//    return false;
//}

//图片上传

/*function fileBets(){
var a=$("#file").val();
console.log(a);
//var b =a.lastIndexOf(".");
//var c = a.substr(b+1);
//if(b>-1&&b<a.length-1){
//	if(c !=='jpg' ){
//		BootstrapDialog.alert("图片格式不正确");
//		return false;
//	}
//}
BootstrapDialog.show({
	title:'提示信息',
	message:'确定要上传新的宣传图片么',
	buttons:[{
		label:'取消',
		 action: function(dialogItself){
             dialogItself.close();
         }
		
	},{
		label:'确定',
		action:function(dialogItself){
			dialogItself.close();
			fileBets1();
		}
	}]
});
}*/

function chearPhone(){
	$(".cropped").empty();
}

function uploadImages(){
	
	BootstrapDialog.show({
		title:'提示信息',
		message:'确定要上传新的头像吗？',
		buttons:[{
			 label:'取消',
			 action: function(dialogItself){
				 chearPhone();
	             dialogItself.close();
	         }
			
		},{
			label:'确定',
			action:function(dialogItself){
				imageupload2();
				chearPhone();
				dialogItself.close();
			}
		}]
	});
}

function imageupload2(){
	
	if (submit4 == 1) {
		return;
	}
	submit4++;
	
	var imgStr = $("#imgInfor")[0].src;
	console.log("图像路径："+imgStr);
	
	$.ajax({
        type: 'post',
        url: '../user/uploadImage',
        dataType: 'json',
        data:{imgStr :imgStr},
        success : function(data) {
        	submit4--;
			var message = data.success ? '上传成功' : data.message;
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
			submit4--;
			BootstrapDialog.show({
				title : "消息",
				message : "上传失败",
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

function updateImages(){
	
	BootstrapDialog.show({
		title:'提示信息',
		message:'确定要更改头像吗？',
		buttons:[{
			 label:'取消',
			 action: function(dialogItself){
	             dialogItself.close();
	         }
			
		},{
			label:'确定',
			action:function(dialogItself){
				updateImages2();
				dialogItself.close();
			}
		}]
	});
}

function updateImages2(){
	if (submit5 == 1) {
		return;
	}
	submit5++;
	
	$.ajax({
		url : '../user/updateImage',
		data : "",
		type : 'get',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit5--;
			var message = data.success ? '更新成功' : data.message;
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
			submit5--;
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

//function uploadImages() {
//
//var formData = new FormData();
//for(var i = 0; i < count; i++) {
//	formData.append("file." + i, fileArr[i]);
//
//}
//var file = $("#file").val();
//
//$.ajax({
//	url: "user/uploadImage", //Server script to process data
//	type: 'POST',
//	xhr: function() { // Custom XMLHttpRequest
//		var myXhr = $.ajaxSettings.xhr();
//		if(myXhr.upload) {
//			myXhr.upload.addEventListener('progress', progressHandlingFunction, false);
//		}
//		return myXhr;
//	},
//	//Ajax events
//	beforeSend: beforeSendHandler,
//	error: errorHandler,
//	// filesArray
//	data: file,
//	//Options to tell jQuery not to process data or worry about content-type.
//	cache: false,
//	contentType: false,
//	processData: false,
//	success: function(data) {
//		alert(data);
//		var d = JSON.parse(data);	
//
//	}
//
//});
//}

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

function testCode() {
	if (submit == 1) {
		return;
	}
	submit++;
	var emailCode = $('form input[name="verificationCode"]').val();
	$.ajax({
		url : '../verify/testEmailCode',
		data : "{\"requestContext\":\"" + emailCode + "\"}",
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit--;
			var message = data.success ? data.result : data.message;
			BootstrapDialog.show({
				title : "消息",
				message : message,
				onshown : function(dialog) {

					/*
					 * setTimeout(function() {
					 * $('button[type="submit"]').removeAttr("disabled");
					 * dialog.close(); }, 1000);
					 */

				}

			});

			if (data.success) {
				updateInfor();
			}
		},
		error : function(error) {
			submit--;
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

function updateInfor() {
	if (submit3 == 1) {
		return;
	}
	submit3++;
	var nickName = $('form input[name="nickName"]').val();
	var email = $('form input[name="email"]').val();

	$.ajax({
		url : '../user/updateInfo',
		data : "{\"requestContext\":{\"email\":\"" + email
				+ "\",\"nickName\":\"" + nickName + "\"}}",
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit3--;
			var message = data.success ? data.result : data.message;
			BootstrapDialog.show({
				title : "消息",
				message : message,
				onshown : function(dialog) {

					/*
					 * setTimeout(function() {
					 * $('button[type="submit"]').removeAttr("disabled");
					 * dialog.close(); }, 1000);
					 */

				}

			});

		},
		error : function(error) {
			submit3--;
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

function updatePass() {
	if (submit2 == 1) {
		return;
	}
	submit2++;

	var password = $('form input[name="password"]').val();
	var newPassword = $('form input[name="newPassword"]').val();
	$.ajax({
		url : '../user/updatePassword',
		data : "{\"requestContext\":{\"password\":\"" + password
				+ "\",\"newPassword\":\"" + newPassword + "\"}}",
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit2--;
			var message = data.success ? data.result : data.message;

			BootstrapDialog.show({
				title : "消息",
				message : message,
				onshown : function(dialog) {
					/*
					 * setTimeout(function() {
					 * $('button[type="submit"]').removeAttr("disabled");
					 * dialog.close(); }, 1000);
					 */
				}

			});
		},
		error : function(error) {
			submit2--;
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

$(document).ready(function() {
	$.ajax({
		url : '../user/getPersonInfor',
		data : "",
		type : 'get',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			if (data.success) {
				$('form input[name="nickName"]').val(data.result.nickName);
				$('form input[name="email"]').val(data.result.email);
			} else {
				BootstrapDialog.show({
					title : "消息",
					message : "请登录",
					onshown : function(dialog) {
						setTimeout(function() {
							$('button[type="submit"]').removeAttr("disabled");
							dialog.close();
						}, 1000);
					}
				});
			}

		},
		error : function(error) {

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
});