package com.stu.fastpan.dao.pojo.user;

import java.util.Date;
import java.util.UUID;

/**
 * 
 * Description:用户表 用于用户的返回值的实体类
 * 
 * @date 创建时间： 2016年10月31日
 * @version 1.0
 */

public class User {

	private String userId;

	private String email;

	private String password;

	private String nickName;

	private Byte roleId;

	private Date createTime;

	private Date lastLoginTime;

	private Date updateTime;

	private String image;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Byte getRoleId() {
		return roleId;
	}

	public void setRoleId(Byte roleId) {
		this.roleId = roleId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password="
				+ password + ", nickName=" + nickName + ", roleId=" + roleId
				+ ", createTime=" + createTime + ", lastLoginTime="
				+ lastLoginTime + ", updateTime=" + updateTime + ", image="
				+ image + "]";
	}

	// 生成UUID
	public String createConstraint() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}