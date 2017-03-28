package com.stu.fastpan.service.manageUser;

import com.stu.fastpan.dao.pojo.manageUser.ManageUser;
import com.stu.fastpan.dao.pojo.manageUser.PageInforBefore;

public interface ManageUserService {

	Object updateUser(ManageUser manageUser);
	
	Object selectUserList(PageInforBefore pageInfor);
	
	Object selectUserList();
	
	Object selectUserOne(String userId);
}
