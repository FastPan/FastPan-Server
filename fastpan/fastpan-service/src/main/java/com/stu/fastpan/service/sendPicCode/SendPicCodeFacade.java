package com.stu.fastpan.service.sendPicCode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.PictureCode;
import com.stu.fastpan.message.ResponseMessage;

public interface SendPicCodeFacade {

	Object sendEmail(String email,HttpSession session);
	
	void sendPictureCode(HttpServletRequest request,HttpServletResponse 
	response, PictureCode pictureCode,HttpSession session) throws ServletException, IOException;

	ResponseMessage testPictureCode(String code,HttpSession session);
	
	ResponseMessage testEmailCode(String code,HttpSession session);
}
