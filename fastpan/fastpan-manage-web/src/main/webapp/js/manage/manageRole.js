var editIndex = undefined;
var submit = 0;
var submit2 = 0;
var roleId;
var level;
var roleName;
function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if ($('#dg').datagrid('validateRow', editIndex)) {
		var roleEditor = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'roleId'
		});
		var levelEditor = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'level'
		});
		var roleNameEditor = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'roleName'
		});
		roleId = $(roleEditor.target).combobox('getText');
		level = $(levelEditor.target).combobox('getText');
		roleName = $(roleNameEditor.target).combobox('getText');
		console.log(roleId);
		$('#dg').datagrid('getRows')[editIndex]['roleId'] = roleId;
		$('#dg').datagrid('getRows')[editIndex]['level'] = level;
		$('#dg').datagrid('getRows')[editIndex]['roleName'] = roleName;
		$('#dg').datagrid('endEdit', editIndex);
		//调用保存方法
		insertRole(roleId , level, roleName);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

function onClickRow(index){
	if (editIndex != index){
		if (endEditing()){
			$('#dg').datagrid('selectRow', index)
					.datagrid('beginEdit', index);
			editIndex = index;
		} else {
			$('#dg').datagrid('selectRow', editIndex);
		}
	}
}

function append() {
	if (endEditing()) {
		$('#dg').datagrid('appendRow', {
			status : 'P'
		});
		editIndex = $('#dg').datagrid('getRows').length - 1;
		$('#dg').datagrid('selectRow', editIndex).datagrid('beginEdit',
				editIndex);
	}
}

function removeit() {
	if (editIndex == undefined) {
		return
	}
	var roleEditor = $('#dg').datagrid('getEditor', {
		index : editIndex,
		field : 'roleId'
	});
	var roleId = $(roleEditor.target).combobox('getText');
	console.log(roleId);
	//调用删除方法
	deleteRole(roleId);
	$('#dg').datagrid('cancelEdit', editIndex).datagrid('deleteRow', editIndex);
	editIndex = undefined;
}

function accept() {
	if (endEditing()) {
		$('#dg').datagrid('acceptChanges');
	}
}

function deleteRole(roleId){
	
	if (submit == 1) {
		return;
	}
	submit++;
	var date = {requestContext:{roleId : roleId}}
	
	$.ajax({
		url : '../manageRole/deleteRoleInfor',
		data : JSON.stringify(date) ,
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit--;
			var message = data.success ? '删除成功' : data.message;
			$.messager.alert('提示',message);
		},
		error : function(error) {
			submit--;
			$.messager.alert('提示','删除失败');
		}
	});
}

function insertRole(roleId , level, roleName){
	
	if (submit2 == 1) {
		return;
	}
	submit2++;
	
	var date = {
		    requestContext:{
	            roleId: roleId,
	            level: level,
	            roleName: roleName
	        }
	}
	
	$.ajax({
		url : '../manageRole/insertRoleInfor',
		data : JSON.stringify(date) ,
		type : 'post',
		dataType : 'json',
		headers : {
			"Content-Type" : "application/json"
		},
		success : function(data) {
			submit2--;
			var message = data.success ? '新增成功' : data.message;
			$.messager.alert('提示',message);
		},
		error : function(error) {
			submit2--;
			$.messager.alert('提示','新增失败');
		}
	});
}