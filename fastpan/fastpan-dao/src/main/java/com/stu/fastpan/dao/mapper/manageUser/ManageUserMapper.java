package com.stu.fastpan.dao.mapper.manageUser;

import java.util.List;

import com.stu.fastpan.dao.pojo.manageUser.ManageUser;
import com.stu.fastpan.dao.pojo.manageUser.PageInforBefore;

public interface ManageUserMapper {

    ManageUser selectByPrimaryKey(String userId);

    List<ManageUser> selectUserList();
    
    int updateByPrimaryKeySelective(ManageUser record);
	
	int deleteByPrimaryKey(String userId);

    int insert(ManageUser record);

    int insertSelective(ManageUser record);

    int updateByPrimaryKey(ManageUser record);
}