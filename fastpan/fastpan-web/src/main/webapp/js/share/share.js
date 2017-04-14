$(function() {
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
	$('#filename').click(function() {

	});
	$('#filesize').click(function() {

	});
	$('#updatetime').click(function() {

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
												'</span><span class="glyphicon glyphicon-download-alt blue" title="下载"></span>');
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
							if ($(this).parent().parent().find('a').attr(
									'href2') != '') {
								window.open($(this).parent().parent().find('a')
										.attr('href2'));
							} else {
								alert('暂时不支持文件夹下载');
							}
						} else {
							alert('此文件已被和谐');
						}

					});
	$('.nav-sidebar li').click(function() {
		$('.nav-sidebar li').removeClass("active");
		$(this).addClass("active");
	});
	$('#all-file-list').on('click', 'a', function() {
		if ($(this).parent().find('span').hasClass('glyphicon-folder-open')) {
			getAllShareFileList($(this).attr('path') + $(this).html() + '/');
		}
	});
	$('#home-path').on('click', 'a', function() {
		getAllShareFileList($(this).attr('path'));
	});
	getAllShareFileList('/');
});
function getAllShareFileList(path) {
	$.ajax({
		url : '../share/getShareFilePathList',
		type : 'get',
		data : {
			path : path,
			state : 0,
			shareRootId : GetQueryString('shareRootId')
		},
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(result) {
			if (result.success === true) {
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
								'<span class="historylistmanager-separator">&gt;</span><a path="'
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
function pathToUrl(path) {
	return path.substring(15, path.length);
}
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
