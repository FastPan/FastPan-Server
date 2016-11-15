package com.stu.fastpan.dao.pojo.user;

/**
 * Description:登录时入参实体类加验证码
 * @author   
 * @date 创建时间： 2016年11月14日
 * @version 1.0
 */

public class UserCode extends User {

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
