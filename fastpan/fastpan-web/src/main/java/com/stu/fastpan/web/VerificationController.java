package com.stu.fastpan.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.dao.pojo.user.PictureCode;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.service.sendPicCode.SendPicCodeFacade;

/**
 * 
 * Description:验证码功能
 * 
 * @date 创建时间： 2016年10月31日
 * @version 1.0
 */

@Controller
@RequestMapping("/verify")
public class VerificationController {

	@Autowired
	private SendPicCodeFacade sendPicCodeFacade;
	
	/**
	 * 用户的发送邮件功能
	 */

	@ResponseBody
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST)
	public Object sendEmail(@RequestBody RequestMessage<String> info,HttpSession session) {

		String email = info.getRequestContext();
		System.out.println(email);
		Object obj = sendPicCodeFacade.sendEmail(email,session);
		return obj;
	}

	/**
	 * 发送图片验证码
	 */

	@RequestMapping(value = "pictureCode", method = RequestMethod.GET)
	@ResponseBody
	public void pictureCode(HttpServletRequest request,
			HttpServletResponse response,
			PictureCode pictureCode, HttpSession session)
			throws ServletException, IOException {

		if (pictureCode == null) {
			pictureCode = new PictureCode();
			pictureCode.setHeight(40);
			pictureCode.setWidth(140);
		} else {
			if (pictureCode.getHeight() == 0) {
				pictureCode.setHeight(40);
			}
			if (pictureCode.getWidth() == 0) {
				pictureCode.setWidth(140);
			}
		}
		
		sendPicCodeFacade.sendPictureCode(request, response, pictureCode,
				session);
	}
	
	/**
	 * 图片验证码验证功能
	 */

	@RequestMapping(value = "testPictureCode", method = RequestMethod.POST)
	@ResponseBody
	public Object testPictureCode(HttpSession session,
			@RequestBody RequestMessage<String> info) throws ServletException,
			IOException {
		String str = info.getRequestContext();
		Object obj = sendPicCodeFacade.testPictureCode(str, session);
		return obj;
	}
	
	/**
	 * 邮件验证码验证功能
	 */

	@RequestMapping(value = "testEmailCode", method = RequestMethod.POST)
	@ResponseBody
	public Object testEmailCode(HttpSession session,
			@RequestBody RequestMessage<String> info) throws ServletException,
			IOException {
		String str = info.getRequestContext();
		Object obj = sendPicCodeFacade.testEmailCode(str, session);
		return obj;
	}
}
