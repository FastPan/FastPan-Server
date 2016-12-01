package com.stu.fastpan.dao.pojo.user;

/**
 * 
 * Description:用于忘记密码
 * 
 * @date 创建时间： 2016年11月25日
 * @version 1.0
 */

public class ForgetPassword extends TestCode {
	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
