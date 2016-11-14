package com.stu.fastpan.service.registerLogin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.PictureCode;
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.dao.pojo.user.UserCode;

public interface RegisterLoginFacade {

	Object register(User user);

	Object login(User user, HttpSession session);

	Object checkAccount(String email);

	Object sendEmail(String email);

	void sendPictureCode(HttpServletRequest request,HttpServletResponse 
	response, PictureCode pictureCode,HttpSession session) throws ServletException, IOException;

	Object testPictureCode(String code,HttpSession session);
	
	Object loginCode(UserCode userCode,HttpSession session);
}
