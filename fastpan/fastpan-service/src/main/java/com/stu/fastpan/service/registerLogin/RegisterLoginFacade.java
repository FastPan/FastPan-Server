package com.stu.fastpan.service.registerLogin;

import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.User;


public interface RegisterLoginFacade {

	Object register(User user);
	
	Object login(User user , HttpSession session);
	
	Object checkAccount(String email);
	
	Object sendEmail(String email);
	
	Object sendPictureCode(String code);
}
