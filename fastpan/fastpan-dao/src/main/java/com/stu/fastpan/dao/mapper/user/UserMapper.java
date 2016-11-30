package com.stu.fastpan.dao.mapper.user;

import com.stu.fastpan.dao.pojo.user.User;

/**
 * 
 * Description:用户表 
 *             实现用户的注册
 *                    登录功能
 * @date 创建时间： 2016年10月31日
 * @version 1.0
 */

public interface UserMapper {
	
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByAccount(String email);
}