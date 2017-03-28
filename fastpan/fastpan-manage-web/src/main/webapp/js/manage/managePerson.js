var userId ;
var roleId ;
var submit = 0;

var editIndex = undefined;
function endEditing() {
	if (editIndex == undefined) {
		return true
	}
	if ($('#dg').datagrid('validateRow', editIndex)) {
		var userIdEditor = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'userId'
		});
		var roleIdEditor = $('#dg').datagrid('getEditor', {
			index : editIndex,
			field : 'roleId'
		});
		userId = $(userIdEditor.target).combobox('getText');
		roleId = $(roleIdEditor.target).combobox('getValue');
		console.log(roleId);
		$('#dg').datagrid('getRows')[editIndex]['userId'] = userId;
		$('#dg').datagrid('getRows')[editIndex]['roleId'] = roleId;
		$('#dg').datagrid('endEdit', editIndex);
		savePerson(userId, roleId);
		editIndex = undefined;
		return true;
	} else {
		return false;
	}
}

//$.extend($.fn.datagrid.methods, {
//	editCell : function(jq, param) {
//		return jq.each(function() {
//			var opts = $(this).datagrid('options');
//			var fields = $(this).datagrid('getColumnFields', true).concat(
//					$(this).datagrid('getColumnFields'));
//
//			for (var i = 0; i < fields.length; i++) {
//				var col = $(this).datagrid('getColumnOption', fields[i]);
//				col.editor1 = col.editor;
//				if (fields[i] != param.field) {
//					col.editor = null;
//				}
//			}
//
//			$(this).datagrid('beginEdit', param.index);
//			for (var i = 0; i < fields.length; i++) {
//				var col = $(this).datagrid('getColumnOption', fields[i]);
//				col.editor = col.editor1;
//			}
//		});
//	}
//});

// function onClickCell(index,userId){
// if (endEditing()) {
// $('#dg').datagrid('selectRow', index)
// .datagrid('editCell', {index:index,field:userId});
// editIndex = index;
// }
// }

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

function accept() {
	if (endEditing()) {
		$('#dg').datagrid('acceptChanges');
	}
}

function reject() {
	$('#dg').datagrid('rejectChanges');
	editIndex = undefined;
}

function getChanges() {
	var rows = $('#dg').datagrid('getChanges');
	alert(rows.length + ' rows are changed!');
}

function savePerson(userId, roleId) {
	if (submit == 1) {
		return;
	}
	submit++
	var date = {
		requestContext : {
			userId : userId,
			roleId : roleId
		}
	}
	$.ajax({
		url : '../manageUser/updateUserInfor',
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