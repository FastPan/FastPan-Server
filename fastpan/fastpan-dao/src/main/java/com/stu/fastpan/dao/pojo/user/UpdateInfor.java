package com.stu.fastpan.dao.pojo.user;

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
