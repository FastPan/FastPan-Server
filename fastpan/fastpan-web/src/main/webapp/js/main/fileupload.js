//文件上传
function WebUploaderInit() {
	// $('#main-content-upload').css("visibility","visible");
	var $list = $('#thelist'), $btn = $('#ctlBtn'), $picker = $('#picker'), state = 'pending', uploader;
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
								file.md5 = md5;
								file.date = new Date().getTime();
								$.ajax({
									url : "../upload/checkMD5",
									method : "post",
									dataType : 'json',
									data : {
										MD5 : md5
									},
									success : function(response) {
										if (response.success === true) {
											if (response.code == 0) {// 文件不存在

											} else if (response.code == 1000) {// 秒传
												owner.skipFile(file);
												file.skip = true;
											}
											deferred.resolve();
										} else {
											alert(response.message);
											deferred.reject();
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
	alert('使用的运行时：'+uploader.predictRuntimeType());
	alert('ie:'+WebUploader.Base.browser.ie+"\n"+'webkit:'+WebUploader.Base.browser.webkit+"\n"+'chrome :'+WebUploader.Base.browser.chrome +"\n"+'firefox:'+WebUploader.Base.browser.firefox +"\n"+'safari :'+WebUploader.Base.browser.safari +"\n"+'opera :'+WebUploader.Base.browser.opera +"\n");
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
						// 0%">'+ '</div>' + '</td>
						$list
								.append('<tr id="'
										+ file.id
										+ '"><td style="vertical-align: middle;overflow-x: hidden;text-overflow: ellipsis;">'
										+ file.name+ '</td>'
										+'<td class="state" style="vertical-align: middle;">等待上传...</td><td class="speed" style="vertical-align: middle;">-</td><td></td></tr>');// <button
																																													// class="btn
																																													// btn-primary">暂停</button><button
																																													// class="btn
																																													// btn-default">取消</button>
					});

	// 文件上传过程中创建进度条实时显示。
	uploader.on('uploadProgress', function(file, percentage) {
		var nowTime=new Date().getTime();
		var $li = $('#' + file.id);
		$li.find('td.state').css("color", "#3c78d8");
		var progress=Math.round(percentage * 1000) / 1000;
		$li.find('td.state').text(Math.round(progress * 10000)/100 + '%');
		var size=(file.size*(percentage-lastProgress))/((nowTime-lastTime)/1000);
		
		if(isFinite(size)){
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
		}
		var $li = $('#' + file.id);
		$li.find('td.state').text(text);
		$li.find('td.state').css("color", "#66b82b");
		var speed=WebUploader.Base.formatSize((file.size)/(startTime==-1?1:(new Date().getTime()-startTime)/1000));
		$li.find('td.speed').text(speed+"/S");
		
	});

	uploader.on('uploadError', function(file, reason) {
		$('#' + file.id).find('td.state').text('上传出错');
		$('#' + file.id).find('td.state').css("color", "#ee0026");
	});

	uploader.on('uploadComplete', function(file) {
		$('#' + file.id).find('.progress').fadeOut();
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
		} else {
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
		data.userId = "lzp";
		data.fileSavePath = "/"
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
	// $picker.on('click',function(){
	//		
	// });

}