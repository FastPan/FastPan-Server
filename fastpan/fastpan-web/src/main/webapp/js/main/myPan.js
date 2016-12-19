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
										'<span class="batch blue" title="分享">&#xF159;</span><span class="glyphicon glyphicon-download-alt blue" title="下载"></span><span class="glyphicon glyphicon-trash blue" title="删除"></span>');
					}
				} else if(event.type == "mouseleave"){
					$(this).find('td:nth-of-type(2)').html('');
				}
			});
	$('.nav-sidebar li').click(function() {
		$('.nav-sidebar li').removeClass("active");
		$(this).addClass("active");
	});
	$('#sidebar li').click(function() {
		if ($(this).attr('id') != "my-upload") {
			$('#main-content-file').show();
			$('#main-content-upload').hide();
		}
	});
	$('#my-upload').click(
			function() {
				$('#main-content-upload').show();
				$('#main-content-file').hide();
				$('#table_upload-thead').css('width',
						$('#table_upload table').width() + 'px');
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
				var data=result.result;
				$("#all-file-list").empty();
				$("#allFile-tmpl").tmpl(data).appendTo("#all-file-list");
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