var submit = 0;
var submit2 = 0;
var fileId;
var fileState;
var editIndex = undefined;
function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if ($('#dg').datagrid('validateRow', editIndex)) {
		var fileIdEd = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'fileId'
		});
		var fileStateEd = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'fileState'
		});
		fileId = $(fileIdEd.target).combobox('getText');
		fileState =$(fileStateEd.target).combobox('getValue');
		$('#dg').datagrid('getRows')[editIndex]['fileId'] = fileId;
		$('#dg').datagrid('getRows')[editIndex]['fileState'] = fileState;
		$('#dg').datagrid('endEdit', editIndex);
		updateFile(fileId, fileState);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function onClickRow(index) {
	if (editIndex != index) {
		if (endEditing()) {
			$('#dg').datagrid('selectRow', index).datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#dg').datagrid('selectRow', editIndex);
		}
	}
}

function removeit() {
	if (editIndex == undefined) {
		return
	}
	var fileIdEditor = $('#dg').datagrid('getEditor', {
		index : editIndex,
		field : 'fileId'
	});
	var fileId = $(fileIdEditor.target).combobox('getText');
	console.log(fileId);
	//调用删除方法
	deleteFile(fileId);
	$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
	editIndex = undefined;
}

function accept() {
	if (endEditing()) {
		$('#dg').datagrid('acceptChanges');
	}
}

function updateFile(fileId, fileState) {
	if (submit == 1) {
		return;
	}
	submit++
	var date = {
		requestContext : {
			fileId : fileId,
			fileState : fileState
		}
	}
	$.ajax({
		url : '../manageFile/updateFileInfor',
		data : JSON.stringify(date),
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit--;
			var message = data.success ? '修改成功' : data.message;
			$.messager.alert('提示', message);
		},
		error : function(error) {
			submit--;
			$.messager.alert('提示', '修改失败');
		}
	});
}

function deleteFile(fileId) {
	if (submit2 == 1) {
		return;
	}
	submit2++
	var date = {
		requestContext : {
			fileId : fileId
		}
	}
	$.ajax({
		url : '../manageFile/deleteFile',
		data : JSON.stringify(date),
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit2--;
			var message = data.success ? '删除成功' : data.message;
			$.messager.alert('提示', message);
		},
		error : function(error) {
			submit2--;
			$.messager.alert('提示', '删除失败');
		}
	});
}