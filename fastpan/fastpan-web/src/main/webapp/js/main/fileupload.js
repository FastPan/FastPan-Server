//stopId=[];
// 文件上传
function WebUploaderInit() {
	// $('#main-content-upload').css("visibility","visible");
	var $list = $('#thelist'), $btn = $('#ctlBtn'), $picker = $('#picker'), state = 'pending';
	var lastTime,lastProgress,startTime=-1;
	WebUploader.Uploader
			.register(
					{
						'before-send-file' : 'md5'
					},
					{
						md5 : function(file) {
							var me = this, owner = this.owner, server = me.options.server, deferred = WebUploader
									.Deferred();
							$('#' + file.id).find('td.state').text('正在计算秒传');
							owner.md5File(file.source)

							// 如果读取出错了，则通过reject告诉webuploader文件上传出错。
							.fail(function() {
								deferred.reject();
							})

							// md5值计算完成
							.then(function(md5) {
								// console.log('md5 over');
								file.md5 = md5;
								file.date = new Date().getTime();
								$.ajax({
									url : "../upload/checkMD5",
									method : "post",
									dataType : 'json',
									data : {
										MD5 : md5,
										savePath: file.fileSavePath,
										fileName:file.name
									},
									success : function(response) {
										if (response.success === true) {
											if(file.getStatus()!='cancelled'&&file.getStatus()!='interrupt'){
												if (response.code == 0) {// 文件不存在

												} else if (response.code == 1000) {// 秒传
													owner.skipFile(file);
													file.skip = true;
												}
											}else{
												file.cancelled=true;
											}
											deferred.resolve();
										} else {
											if(file.getStatus()!='cancelled'){
												alert(response.message);
												deferred.reject();
											}
										}

									}

								});
							});

							return deferred.promise();
						}
					});
	uploader = WebUploader.create({

		// swf文件路径
		swf : '../plug-in/webuploader/Uploader.swf',
		// 文件接收服务端。
		server : '../upload/uploadFile',
		// 不压缩image
		resize : false,

		// 内部根据当前运行是创建，可能是input元素，也可能是flash. pick : '#picker',
		threads : 3,
		chunked : true,
		fileNumLimit : 10,
		fileSingleSizeLimit : 5120 * 1024 * 1024,
		prepareNextFile :true,


	});
	// alert('使用的运行时：'+uploader.predictRuntimeType());
	// alert('ie:'+WebUploader.Base.browser.ie+"\n"+'webkit:'+WebUploader.Base.browser.webkit+"\n"+'chrome
	// :'+WebUploader.Base.browser.chrome
	// +"\n"+'firefox:'+WebUploader.Base.browser.firefox +"\n"+'safari
	// :'+WebUploader.Base.browser.safari +"\n"+'opera
	// :'+WebUploader.Base.browser.opera +"\n");
	// 当有文件添加进来的时候
	uploader
			.on(
					'fileQueued',
					function(file) {
						// $list.append('<div id="' + file.id + '"
						// class="item">'
						// + '<h4 class="info">' + file.name + '</h4>'
						// + '<p class="state">等待上传...</p>' + '</div>');
						// <td class="progress progress-striped">' + '<div
						// class="progress-bar" role="progressbar" style="width:
						// 0%">'+ '</div>' + '</td><button class="btn
						// btn-primary stop">暂停</button>
						$list
								.append('<tr id="'
										+ file.id
										+ '"><td style="vertical-align: middle;overflow-x: hidden;text-overflow: ellipsis;">'
										+ file.name+ '</td>'
										+'<td class="state" style="vertical-align: middle;">等待上传...</td><td class="speed" style="vertical-align: middle;">-</td><td><button class="btn btn-default remove-this">取消</button></td></tr>');
					});
	uploader
	.on(
			'filesQueued',
			function(files) {
				BootstrapDialog.show({
					title: '请选择存储位置',
					message: $('<div></div>').load('./savePath'),
				    buttons: [{
				        label: '确定',
				        cssClass: 'btn-primary',
				        action: function(dialogItself){
				        	// 取路径
				        	var path=getSavePath();
				        	console.log(path);
				        	if(path!=null){
				        		files.forEach(function(e){
				        			e.fileSavePath=path;
				        			console.log(e);
				        		});
				        		dialogItself.close();
				        	}
				        }
				    },{
				        label: '取消',
				        action: function(dialogItself){
				        	// 删除这些文件
				        	files.forEach(function(e){
				        		uploader.removeFile(e,true);
				        	})
				        	dialogItself.close();
				        }
				    }]});
			});

	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percentage) {		
		var nowTime=new Date().getTime();
		var $li = $('#' + file.id);
		$li.find('td.state').css("color", "#3c78d8");
		var progress=Math.round(percentage * 1000) / 1000;
		$li.find('td.state').text(Math.round(progress * 10000)/100 + '%');
		var size=(file.size*(percentage-lastProgress))/((nowTime-lastTime)/1000);
		
		if(isFinite(size)&&size>0){
			var speed=WebUploader.Base.formatSize(size);
			$li.find('td.speed').text(speed+"/S");
		}
		
		lastProgress=percentage;
		lastTime=nowTime;
	});

	uploader.on('uploadSuccess', function(file, response) {
		var text = '已上传';
		if (file.skip) {
			text = "已秒传";
		}else if(file.cancelled){
			text = "已取消";
		}
		
		var $li = $('#' + file.id);
		$li.find('td.state').text(text);
		if(file.cancelled){
			$li.find('td.speed').text('-');
		}else{
			$li.find('td.state').css("color", "#66b82b");
			var speed=WebUploader.Base.formatSize((file.size)/(startTime==-1?1:(new Date().getTime()-startTime)/1000));
			$li.find('td.speed').text(speed+"/S");
		}
		
	});

	uploader.on('uploadError', function(file, reason) {
		$('#' + file.id).find('td.state').text('上传出错');
		$('#' + file.id).find('td.state').css("color", "#ee0026");
	});

	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
		$('#' + file.id).find('.remove-this').siblings().hide();
		$('#' + file.id).find('.remove-this').hide();
		
	});

	uploader.on('fileDequeued', function(file) {
		$('#' + file.id).find('td.state').text('已取消');
	});
	uploader.on('all', function(type) {
		if (type === 'startUpload') {
			state = 'uploading';
		} else if (type === 'stopUpload') {
			state = 'paused';
		} else if (type === 'uploadFinished') {
			state = 'done';
		}
		
		if (state === 'uploading') {
			$btn.text('全部暂停');
		} else{
			$btn.text('开始上传');
		}
	
	});
	uploader.on('uploadBeforeSend', function(block, data) {
		// block为分块数据。

		// file为分块对应的file对象。
		var file = block.file;
		// 将存在file对象中的md5数据携带发送过去。
		data.fileMd5 = file.md5;
		data.lastModifiedDate = file.date;
		data.fileSavePath =file.fileSavePath;
		// 删除其他数据
		// delete data.key;
		lastTime=new Date().getTime();
		startTime=lastTime;
		lastProgress=0;
	});
	$btn.on('click', function() {
		if (state === 'uploading') {
			uploader.stop(true);
		} else {
			uploader.upload();
		}
	});
	$('#my-upload').one('click', function() {
		uploader.addButton({
			id : '#picker',
			innerHTML : '选择文件'
		});
	});
	uploader.addButton({
		id : '#picker1',
		innerHTML : '文件上传'
	});
	$list.on('click', '.remove-this', function() {
		uploader.cancelFile($(this).parent().parent().attr('id'));
		$(this).hide();
		$(this).siblings().hide();
	})
// $list.on('click', '.stop', function() {
// console.log($(this).html());
// if($(this).html()=='暂停'){
// uploader.stop($(this).parent().parent().attr('id'));
// //stopId[stopId.length]=$(this).parent().parent().attr('id');
// $(this).text('开始');
// $(this).siblings().hide();
// }else{
// uploader.upload($(this).parent().parent().attr('id'));
// $(this).text('暂停');
// $(this).siblings().show();
// }
//		
// })
}