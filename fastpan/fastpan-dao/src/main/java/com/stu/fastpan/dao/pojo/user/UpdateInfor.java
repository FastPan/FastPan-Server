package com.stu.fastpan.dao.pojo.user;

/**
 * 
 * Description:用于更新用户邮箱和昵称的实体类
 * 
 * @date 创建时间： 2016年11月25日
 * @version 1.0
 */
public class UpdateInfor {

	private String nickName;
	private String email;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UpdateInfor [nickName=" + nickName + ", email=" + email + "]";
	}
	
}
