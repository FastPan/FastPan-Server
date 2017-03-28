package com.stu.fastpan.dao.mapper.manageRole;

import java.util.List;

import com.stu.fastpan.dao.pojo.manageRole.ManageRole;

public interface ManageRoleMapper {
    
	int insertSelective(ManageRole record);
    
	List<ManageRole> selectRoleList();

    ManageRole selectByPrimaryKey(Byte roleId);
	
	int deleteByPrimaryKey(Byte roleId);

    int insert(ManageRole record);

    int updateByPrimaryKeySelective(ManageRole record);

    int updateByPrimaryKey(ManageRole record);
}