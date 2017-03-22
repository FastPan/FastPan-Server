$(function() {
	$('#savePath').on('click', 'tr', function() {
		showPath($(this).find('.userFileName').html(), true);
	});
	$('#path').on('click', 'a', function() {
		// getSavePathList($(this).attr('path'));
		showPath($(this).attr('path'), false);
	});

	getSavePathList('/');
});
function showPath(path, isAppend) {
	//var node = $('#path :not(.historylistmanager-separator)');
	if (isAppend === true) {
		path = getSavePath() + path + '/';
	}
	getSavePathList(path, function() {
		var arr = path.split('/');
		$('#path').empty();
		$('#path').append('<a path="/">全部文件</a>');
		temp='/';
		for (var i = 0; i < arr.length; i++) {
			if (arr[i] != '') {
				temp+=arr[i];
				temp+='/';
				$('#path').append(
						'<span	class="historylistmanager-separator">&gt;</span><a path="'
								+ temp + '">' + arr[i] + '</a>');
			}
		}

	});
}

function getSavePathList(path, func) {
	var argumentsLength = arguments.length;
	$.ajax({
		url : '../userFile/getSavePathList',
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
				var data = result.result;
				// console.log(data);
				switch (argumentsLength) {
				case 2:
					func();
				case 1:
					$("#savePath").empty();
					$("#savePath-tmpl").tmpl(data).appendTo("#savePath");
					break;
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
		},
		fail : function() {
			alert('fail');
		}
	});
}
function clickNewSaveFolder() {
	BootstrapDialog
			.show({
				title : '新建文件夹',
				message : '<div><label>请输入文件夹名：</label><input id="newFloder"style="width:100%"/></div>',
				onshown:function(){
					$('#newFloder').focus();
				},
				buttons : [ {
					label : '确定',
					cssClass : 'btn-primary',
					action : function(dialogItself) {
						var newFloder = $('#newFloder').val();
						if (newFloder != '' && newFloder !== undefined) {
							newSaveFolder(getSavePath(), newFloder);
						}
						dialogItself.close();
					}
				}, {
					label : '取消',
					action : function(dialogItself) {
						dialogItself.close();
					}
				} ]
			});
}
function getSavePath() {
	return $('#path').children("a:last-child").attr('path');
}
function newSaveFolder(path, newFolder) {
	$.ajax({
		url : '../userFile/newFloder',
		data : {
			path : path,
			userFileName : newFolder,
			state : 0
		},
		type : 'post',
		dataType : 'json',
		success : function(result) {
			if (result.success === true) {
				getSavePathList(path);
			} else {
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
function back(){
	getSavePath()
}
