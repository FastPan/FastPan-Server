package com.stu.fastpan.service.manageRole;

import com.stu.fastpan.dao.pojo.manageRole.ManageRole;

public interface ManageRoleService {

	Object insertRole(ManageRole manageRole);
	
	Object selectRoleList();
	
	Object delectRoleInfor(Byte roleId);
	
}
