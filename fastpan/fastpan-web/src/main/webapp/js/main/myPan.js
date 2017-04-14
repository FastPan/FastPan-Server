var arr;
var arr2;
var arr3;
submit = 0;
submit2 = 0;
submit3 = 0;
submit4 = 0;

$(window).resize(
		function() {
			var height = $(window).height() - $('nav').height()
					- $('#table-head').height() - $('#table-thead').height()
					- parseInt($('#main-content').css('padding')) * 2;
			$('#table').css('height', height + 'px');
			$('#table_upload').css('height', height + 'px');
			$('#table_upload-thead').css('width',
					$('#table_upload table').width() + 'px');
		});
$(function() {
	var height = $(window).height() - $('nav').height()
			- $('#table-head').height() - $('#table-thead').height()
			- parseInt($('#main-content').css('padding')) * 2;
	$('#table').css('height', height + 'px');
	$('#table_upload').css('height', height + 'px');
	$('#select-all').click(
			function() {
				$("#table input[type='checkbox']").prop('checked',
						$(this).prop('checked'));
			});
	$('#thead_show a').click(function() {
		if ($(this).find('font span').hasClass('glyphicon-arrow-down')) {
			$(this).find('font span').addClass("glyphicon-arrow-up");
			$(this).find('font span').removeClass("glyphicon-arrow-down");
		} else if ($(this).find('font span').hasClass('glyphicon-arrow-up')) {
			$(this).find('font span').addClass("glyphicon-arrow-down");
			$(this).find('font span').removeClass("glyphicon-arrow-up");
		} else {
			$(this).find('font span').addClass("glyphicon-arrow-down");
		}
	});
	$('#all-file').click(function() {
		getAllFileList('/');
	});
	$('#all-img').click(function() {
		getFileByType(0);
	});
	$('#all-txt').click(function() {
		getFileByType(1);
	});
	$('#all-video').click(function() {
		getFileByType(2);
	});
	$('#all-music').click(function() {
		getFileByType(3);
	});
	$('#filename').click(function() {

	});
	$('#filesize').click(function() {

	});
	$('#updatetime').click(function() {

	});
	$('#search').bind('keypress', function(event) {
		if (event.keyCode == "13") {
			searchFile($(this).val());
		}
	});
	$('#newFloderBtn').click(function() {
		BootstrapDialog.show({
			title : '新建文件夹',
			message : $('<div></div>').load('./savePath'),
			buttons : [ {
				label : '确定',
				cssClass : 'btn-primary',
				action : function(dialogItself) {
					getAllFileList('/');
					dialogItself.close();
				}
			}, {
				label : '取消',
				action : function(dialogItself) {
					dialogItself.close();
				}
			} ]
		});
	});
	$('#move').click(function() {
		var arr = new Array();
		$("table input:checkbox").each(function() {
			if ($(this).is(':checked')) {
				arr[arr.length] = $(this).attr("userFileId");
			}
		})
		moveFile(arr);
	});
	$('#table table tbody')
			.on(
					'mouseenter mouseleave',
					'tr',
					function(event) {
						if (event.type == "mouseenter") {
							if (!$('#my-share').hasClass('active')
									&& !$('#my-delete').hasClass('active')) {
								$(this)
										.find('td:nth-of-type(2)')
										.html(
												'<span class="batch blue" title="分享">&#xF159;</span><span class="glyphicon glyphicon-download-alt blue" title="下载"></span><span class="glyphicon glyphicon-trash blue" title="删除"></span>');
							}
						} else if (event.type == "mouseleave") {
							$(this).find('td:nth-of-type(2)').html('');
						}
					});
	$('#table table tbody')
			.on(
					'click',
					'.glyphicon-download-alt',
					function(event) {
						if ($(this).parent().parent().find('input').attr(
								'state') != '2') {
							window.open($(this).parent().parent().find('a')
									.attr('href2'));
						} else {
							alert('此文件已被和谐');
						}

					});
	$('#table table tbody').on('click', '.glyphicon-trash',
			function(event) {
		        var id = $(this).parent().parent().find('input').attr('userFileId');
		        checkDeleteFile(id);
			});
	$('#delete-all').click(
			function() {
				$("#table_delete input[type='checkbox']").prop('checked',
						$(this).prop('checked'));
			});
	$('.nav-sidebar li').click(function() {
		$('.nav-sidebar li').removeClass("active");
		$(this).addClass("active");
	});
	$('#sidebar li').click(function() {
		if ($(this).attr('id') != "my-upload") {
			$('#main-content-file').show();
			$('#main-content-upload').hide();
			$('#main-recycle-bin').hide();
		}
	});
	$('#my-upload').click(
			function() {
				$('#main-content-upload').show();
				$('#main-content-file').hide();
				$('#main-recycle-bin').hide();
				$('#table_upload-thead').css('width',
						$('#table_upload table').width() + 'px');
	});
	
	$('#my-delete').click(
			function() {
				$('#main-recycle-bin').show();
				$('#main-content-file').hide();
				$('#main-content-upload').hide();
				$('#table-delete-thead').css('width',
						$('#table_delete table').width() + 'px');
				recycleBin();
	});

	$('#all-file-list').on('click', 'a', function() {
		if ($(this).parent().find('span').hasClass('glyphicon-folder-open')) {
			// console.log($(this).attr('path')+$(this).html()+'/');
			getAllFileList($(this).attr('path') + $(this).html() + '/');
		}
	});
	$('#home-path').on('click', 'a', function() {
		getAllFileList($(this).attr('path'));
	});
	WebUploaderInit();

	// var data = [ {
	// fileId : null,
	// userFileName : '文件夹1'
	// }, {
	// fileId : 2,
	// userFileName : '文件1'
	// }, {
	// fileId : 2,
	// userFileName : '文件2'
	// } ];
	// $("#all-file-list").empty();
	// $("#allFile-tmpl").tmpl(data).appendTo("#all-file-list");
	getAllFileList('/');
});

function searchFile(filename) {
	$.ajax({
		url : '../userFile/search',
		data : {
			filename : filename
		},
		type : 'get',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(result) {
			if (result.success) {
				var data = result.result;
				$("#all-file-list").empty();
				$("#allFile-tmpl").tmpl(data).appendTo("#all-file-list");

				var arr = path.split('/');
				$('#home-path').empty();
				$('#home-path').append('<a path="/">全部文件</a>');
				temp = '/';
				for (var i = 0; i < arr.length; i++) {
					if (arr[i] != '') {
						temp += arr[i];
						temp += '/';
						$('#home-path').append(
								'<span	class="historylistmanager-separator">&gt;</span><a path="'
										+ temp + '">' + arr[i] + '</a>');
					}
				}
			}

		},
		error : function(error) {
			BootstrapDialog.show({
				title : "消息",
				message : " 获取文件列表失败,服务器出错了",
				onshown : function(dialog) {
					setTimeout(function() {
						dialog.close();
					}, 1000);
				}
			});
		}
	});
}

function getFileByType(type) {
	$.ajax({
		url : '../userFile/getFileByType',
		data : {
			type : type
		},
		type : 'get',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(result) {
			if (result.success) {
				var data = result.result;
				$("#all-file-list").empty();
				$("#allFile-tmpl").tmpl(data).appendTo("#all-file-list");

				var arr = path.split('/');
				$('#home-path').empty();
				$('#home-path').append('<a path="/">全部文件</a>');
				temp = '/';
				for (var i = 0; i < arr.length; i++) {
					if (arr[i] != '') {
						temp += arr[i];
						temp += '/';
						$('#home-path').append(
								'<span	class="historylistmanager-separator">&gt;</span><a path="'
										+ temp + '">' + arr[i] + '</a>');
					}
				}
			}

		},
		error : function(error) {
			BootstrapDialog.show({
				title : "消息",
				message : " 获取文件列表失败,服务器出错了",
				onshown : function(dialog) {
					setTimeout(function() {
						dialog.close();
					}, 1000);
				}
			});
		}
	});
}

function getAllFileList(path) {
	$.ajax({
		url : '../userFile/getFileList',
		data : {
			path : path,
			state : 0
		},
		type : 'get',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(result) {
			if (result.success === true) {
				// console.log(result);
				var data = result.result;
				$("#all-file-list").empty();
				$("#allFile-tmpl").tmpl(data).appendTo("#all-file-list");

				var arr = path.split('/');
				$('#home-path').empty();
				$('#home-path').append('<a path="/">全部文件</a>');
				temp = '/';
				for (var i = 0; i < arr.length; i++) {
					if (arr[i] != '') {
						temp += arr[i];
						temp += '/';
						$('#home-path').append(
								'<span	class="historylistmanager-separator">&gt;</span><a path="'
										+ temp + '">' + arr[i] + '</a>');
					}
				}
			}
		},
		error : function(error) {
			BootstrapDialog.show({
				title : "消息",
				message : " 获取文件列表失败,服务器出错了",
				onshown : function(dialog) {
					setTimeout(function() {
						dialog.close();
					}, 1000);
				}
			});
		}
	});
}

function removeLogin(){
	BootstrapDialog.show({
		title:'提示信息',
		message:'确定要注销用户吗?',
		buttons:[{
			 label:'取消',
			 action: function(dialogItself){
	             dialogItself.close();
	         }
		},{
			label:'确定',
			action:function(dialogItself){
				removeLogin2();
				dialogItself.close();
			}
		}]
	});
}

function removeLogin2(){
	
	$.ajax({
		url : '../user/removeUser',
		type : 'get',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(result) {
			
				BootstrapDialog.show({
					title : "消息",
					message : "已退出",
					onshown : function(dialog) {
						setTimeout(function() {
//							$('button[type="submit"]').removeAttr("disabled");
							dialog.close();
							if (result == 1) {
								window.location.href = "../index";
							}
						}, 1000);

					}
				});
		},
		error : function(error) {
			BootstrapDialog.show({
				title : "消息",
				message : "网络问题或接口问题",
				onshown : function(dialog) {
					setTimeout(function() {
						dialog.close();
					}, 1000);
				}
			});
		}
	});
}

//获取回收站列表
function recycleBin(){
	$.ajax({
		url : '../userFile/getRecycleBinList',
		type : 'get',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(result) {
			if (result.success === true) {
				// console.log(result);
				var data = result.result;
				
				$("#delete-file-list").empty();
				$("#deleteFile-tmpl").tmpl(data).appendTo("#delete-file-list");
				
			}
		},
		error : function(error) {
			BootstrapDialog.show({
				title : "消息",
				message : " 获取文件列表失败,服务器出错了",
				onshown : function(dialog) {
					setTimeout(function() {
						dialog.close();
					}, 1000);
				}
			});
		}
	});
}

function getcheckboxList(){
	arr = new Array();
	$("table input:checkbox").each(function() {
		if ($(this).is(':checked')) {
			if($(this).attr("userFileId") != undefined){
				arr[arr.length] = $(this).attr("userFileId");
			}
		}
	})
}

function getcheckboxList2(){
	arr2 = new Array();
	$("table input:checkbox").each(function() {
		if ($(this).is(':checked')) {
			if($(this).attr("userFileId") != undefined){
			    arr2[arr2.length] = $(this).attr("userFileId");
			}
		}
	})
}

function deleteFiles(){
	submit++;
	getcheckboxList();
	console.log(arr);
	
	$.ajax({
		url : '../userFile/deleteRecycleBinFiles',
		data : JSON.stringify(arr),
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
					setTimeout(function() {
					$('button[type="submit"]').removeAttr("disabled");
					dialog.close(); }, 1000);
				}

			});

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

function backDeleteFiles(){
	BootstrapDialog.show({
		title:'提示信息',
		message:'确定要还原文件吗?',
		buttons:[{
			 label:'取消',
			 action: function(dialogItself){
	             dialogItself.close();
	         }
		},{
			label:'确定',
			action:function(dialogItself){
				backDeleteFiles2();
				dialogItself.close();
			}
		}]
	});
}

function backDeleteFiles2(){
	
	submit2++;
	getcheckboxList2();
	console.log(arr2);
	
	$.ajax({
		url : '../userFile/backRecycleBinFiles',
		data : JSON.stringify(arr2),
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
					setTimeout(function() {
					$('button[type="submit"]').removeAttr("disabled");
					dialog.close(); }, 1000);
					recycleBin();
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

function clearDeleteFiles(){
	BootstrapDialog.show({
		title:'提示信息',
		message:'确定要清空文件吗?',
		buttons:[{
			 label:'取消',
			 action: function(dialogItself){
	             dialogItself.close();
	         }
		},{
			label:'确定',
			action:function(dialogItself){
				clearDeleteFiles2();
				dialogItself.close();
			}
		}]
	});
}

function clearDeleteFiles2(){
	
	submit3++;
	getcheckboxList2();
	console.log(arr2);
	
	$.ajax({
		url : '../userFile/clearRecycleBinFiles',
		data : JSON.stringify(arr2),
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
					setTimeout(function() {
					$('button[type="submit"]').removeAttr("disabled");
					dialog.close(); }, 1000);
					recycleBin();
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

function checkDeleteFile(userFileId){
	submit4++;
	console.log(userFileId);
	var date = {requestContext:{userFileId : userFileId}}
	$.ajax({
		url : '../userFile/deleteRecycleBinFile',
		data : JSON.stringify(date),
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit4--;
			var message = data.success ? data.result : data.message;
			BootstrapDialog.show({
				title : "消息",
				message : message,
				onshown : function(dialog) {
					setTimeout(function() {
					$('button[type="submit"]').removeAttr("disabled");
					dialog.close(); }, 1000);
					recycleBin();
				}

			});

		},
		error : function(error) {
			submit4--;
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
				// console.log(data.result.nickName);
				// $('form input[name="nickName"]').val(data.result.nickName);
				// $('form input[name="email"]').val(data.result.email);
				$("#nickName").html(data.result.nickName);
				$("#imageId").attr("src", data.result.image);

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

function moveFile(arr) {
	BootstrapDialog.show({
		title : '请选择存储位置',
		message : $('<div></div>').load('./savePath'),
		buttons : [ {
			label : '确定',
			cssClass : 'btn-primary',
			action : function(dialogItself) {
				// 取路径
				var path = getSavePath();
				var fileListModel=new Object();
				fileListModel.fileList=arr;
				fileListModel.path=path;
				$.ajax({
					url : '../userFile/moveFile',
					data : JSON.stringify(fileListModel),
					type : 'post',
					dataType : 'json',
					headers : {
						"Content-Type" : "application/json"
					},
					success : function(result) {
						getAllFileList('/')
						dialogItself.close();
					},
					error : function(error) {
						BootstrapDialog.show({
							title : "消息",
							message : " 获取文件列表失败,服务器出错了",
							onshown : function(dialog) {
								setTimeout(function() {
									dialog.close();
								}, 1000);
							}
						});
					}
				});
			}
		}, {
			label : '取消',
			action : function(dialogItself) {
				dialogItself.close();
			}
		} ]
	});
}