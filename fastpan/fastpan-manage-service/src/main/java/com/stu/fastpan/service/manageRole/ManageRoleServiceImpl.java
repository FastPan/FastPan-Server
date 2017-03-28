package com.stu.fastpan.service.manageRole;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.fastpan.dao.mapper.manageRole.ManageRoleMapper;
import com.stu.fastpan.dao.pojo.manageRole.ManageRole;
import com.stu.fastpan.service.base.BaseService;

@Service
public class ManageRoleServiceImpl extends BaseService implements ManageRoleService {

	private static Logger log = LoggerFactory
			.getLogger(ManageRoleServiceImpl.class);
	
	@Autowired
	private ManageRoleMapper manageRoleMapper;
	
	@Override
	public Object selectRoleList() {
		
		List<ManageRole> list;
		try {
			list = manageRoleMapper.selectRoleList();
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		if(list.size() <= 0){
			log.info("调用失败");
			return FAIL(1007, "查询失败");
		}
		log.info("调用成功");
//		return SUCCESS(list);
		return list;
	}

	@Override
	public Object insertRole(ManageRole manageRole) {
		int result = 0;
		
		if(manageRole == null){
			log.info("调用失败");
			return FAIL(1003, "入参失误");
		}
		try {
			result = manageRoleMapper.insertSelective(manageRole);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		
		if(result == 0){
			log.info("添加失败");
			return FAIL(1007, "添加失败");
		}else{
			log.info("调用成功");
			return SUCCESS("添加成功");
		}
		
	}
	
	@Override
	public Object delectRoleInfor(Byte roleId) {
		int result = 0;
		
		if(roleId == null){
			log.info("调用失败");
			return FAIL(1003, "入参失误");
		}
		try {
			result = manageRoleMapper.deleteByPrimaryKey(roleId);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		
		if(result == 0){
			log.info("删除失败");
			return FAIL(1007, "删除失败");
		}else{
			log.info("调用成功");
			return SUCCESS("删除成功");
		}
	}

}
