package com.stu.fastpan.service.sendPicCode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.PictureCode;

public interface SendPicCodeFacade {

	void sendPictureCode(HttpServletRequest request,HttpServletResponse 
	response, PictureCode pictureCode,HttpSession session) throws ServletException, IOException;

	Object testPictureCode(String code,HttpSession session);
	
	Object testEmailCode(String code,HttpSession session);
}
