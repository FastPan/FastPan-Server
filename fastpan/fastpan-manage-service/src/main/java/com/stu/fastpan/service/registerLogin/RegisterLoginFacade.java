package com.stu.fastpan.service.registerLogin;

import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.dao.pojo.user.UserCode;

public interface RegisterLoginFacade {

	Object login(User user, HttpSession session);

	Object checkAccount(String email);

	Object loginCode(UserCode userCode,HttpSession session);
	
}
