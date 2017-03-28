package com.stu.fastpan.service.manageUser;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.stu.fastpan.dao.mapper.manageUser.ManageUserMapper;
import com.stu.fastpan.dao.pojo.manageUser.ManageUser;
import com.stu.fastpan.dao.pojo.manageUser.PageBean;
import com.stu.fastpan.dao.pojo.manageUser.PageInforBefore;
import com.stu.fastpan.service.base.BaseService;

@Service
public class ManageUserServiceImpl extends BaseService implements ManageUserService{

	private static Logger log = LoggerFactory
			.getLogger(ManageUserServiceImpl.class);

	@Autowired
	private ManageUserMapper manageUserMapper;
	
	@Override
	public Object updateUser(ManageUser manageUser) {
		ManageUser manageUser2 = new ManageUser();
		int result = 0;
		
		try {
			manageUser2.setUserId(manageUser.getUserId());
			manageUser2.setRoleId(manageUser.getRoleId());
			manageUser2.setUpdateTime(new Date());
			result = manageUserMapper.updateByPrimaryKeySelective(manageUser2);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		if(result == 0){
			log.info("调用失败");
			return FAIL(1007, "更新失败");
		}
		log.info("调用成功");
		return SUCCESS("更新成功");
	}

	@Override
	public Object selectUserList(PageInforBefore pageInfor) {
		List<ManageUser> list;
		PageBean<ManageUser> pageInfo;
		
		PageHelper.startPage(pageInfor.getPageNum(), pageInfor.getPageSize());  
	    PageHelper.orderBy("last_login_time desc");
	    
		try {
			list = manageUserMapper.selectUserList();
			pageInfo = new PageBean<ManageUser>(list); 
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		log.info("调用成功");
		return SUCCESS(pageInfo);
	}

	@Override
	public Object selectUserList() {
		List<ManageUser> list;
  
		try {
			list = manageUserMapper.selectUserList();
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		log.info("调用成功");
//		return SUCCESS(pageInfo);
		return list;
	}
	
	@Override
	public Object selectUserOne(String userId) {
		ManageUser manageUser;
		
		if(userId == null){
			log.info("调用失败");
			return FAIL(1003, "入参失误");
		}
		
		try {
			manageUser = manageUserMapper.selectByPrimaryKey(userId);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		log.info("调用成功");
		return SUCCESS(manageUser);
	}

}
