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
	$('#table table tbody tr')
			.hover(
					function() {
						
						$(this)
								.find('td:nth-of-type(2)')
								.html(
										'<span class="glyphicon glyphicon-download-alt blue" title="下载"></span>');
					}, function() {
						$(this).find('td:nth-of-type(2)').html('');
					});
	$('.nav-sidebar li').click(function() {
		$('.nav-sidebar li').removeClass("active");
		$(this).addClass("active");
	});
});