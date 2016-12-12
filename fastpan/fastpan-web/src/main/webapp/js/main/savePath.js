$(function() {
	var tree = $(".tree").treemenu(getSavePathList);
});
function getSavePathList(path, id, func) {
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
				var data = result.result;
				func(id, data);
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
function clickNewSaveFolder() {
	var node;
	if($('.checked').length==0){
		$('.tree li').toggleClass('tree-opened');
		node=$('.tree li a').siblings();
	}else{
		node=$('.checked').siblings();
	}
	var path=$('.tree').getPath(node.attr('id'));
	node.append(
			'<li class="tree-empty"><input type="text"></input></li>');
	node.find('input').blur(function() {newSaveFolder(node,path,$(this).val());});
	node.find('input').bind('keypress', function(event) {
		if (event.keyCode == "13"){
			newSaveFolder(node,path,$(this).val());
		}
	});
}
function getSavePath(){
	var path=null;
	if($('.checked').length!=0){
		path=$('.tree').getPath($('.checked').siblings().attr('id'));
	}
	return path;
}
function newSaveFolder(node,path,newFolder){
	//node.find('input').parent().remove();
	$.ajax({
		url : '../userFile/newUserFile',
		data : {
			path : path,
			userFileName:newFolder,
			state : 0
		},
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(result) {
			if (result.success === true) {
				node.find('input').parent().remove();
				//node.append('<li class="tree-empty"><input type="text"></input></li>');
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
