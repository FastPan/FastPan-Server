package com.stu.fastpan.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.dao.pojo.user.PictureCode;
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.dao.pojo.user.UserCode;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.service.registerLogin.RegisterLoginFacade;

/**
 * 
 * Description:MVO:用户的登录和注册
 * 
 * @date 创建时间： 2016年10月31日
 * @version 1.0
 */

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private RegisterLoginFacade registerLoginFacade;

	// login页面
	@RequestMapping("login")
	public String login() {
		return "/pages/user/login.html";
	}

	// 注册页面
	@RequestMapping("register")
	public String register() {
		return "/pages/user/register.html";
	}

	// 注册页面
	@RequestMapping(value = "doRegister", method = RequestMethod.POST)
	public String doRegister() {
		return "redirect:/main/index";
	}

	/**
	 * 用户的验证账号功能
	 */

	@ResponseBody
	@RequestMapping(value = "checkAccount", method = RequestMethod.POST)
	public Object checkAccount(@RequestBody RequestMessage<String> info) {

		String user = info.getRequestContext();
		System.out.println(user);
		Object obj = registerLoginFacade.checkAccount(user);
		return obj;
	}

	/**
	 * 用户的注册功能
	 */

	@ResponseBody
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public Object register(@RequestBody RequestMessage<User> info) {

		User user = info.getRequestContext();
		System.out.println(user);
		Object obj = registerLoginFacade.register(user);
		return obj;
	}

	/**
	 * 用户的登录功能
	 */

	@ResponseBody
	@RequestMapping(value = "loginAccount", method = RequestMethod.POST)
	public Object login(@RequestBody RequestMessage<User> info, HttpSession session) {

		User user = info.getRequestContext();
		System.out.println(user);
		Object obj = registerLoginFacade.login(user, session);
		return obj;
	}

	/**
	 * 用户的发送邮件功能
	 */

	@ResponseBody
	@RequestMapping(value = "sendEmail", method = RequestMethod.POST)
	public Object sendEmail(@RequestBody RequestMessage<String> info) {

		String email = info.getRequestContext();
		System.out.println(email);
		Object obj = registerLoginFacade.sendEmail(email);
		return obj;
	}

	/**
	 * 发送图片验证码
	 */
	@RequestMapping(value = "pictureCode", method = RequestMethod.GET)
	@ResponseBody
	public void pictureCode(HttpServletRequest request, HttpServletResponse response, PictureCode pictureCode,
			HttpSession session) throws ServletException, IOException {
		if (pictureCode == null) {
			pictureCode = new PictureCode();
			pictureCode.setHeight(30);
			pictureCode.setWidth(120);
		} else {
			if (pictureCode.getHeight() == 0) {
				pictureCode.setHeight(30);
			}
			if (pictureCode.getWidth() == 0) {
				pictureCode.setWidth(120);
			}
		}
		registerLoginFacade.sendPictureCode(request, response, pictureCode, session);
	}

	/**
	 * 验证码验证功能
	 */

	@RequestMapping(value = "testPictureCode", method = RequestMethod.POST)
	@ResponseBody
	public Object testPictureCode(HttpSession session, @RequestBody RequestMessage<String> info)
			throws ServletException, IOException {
		String str = info.getRequestContext();
		Object obj = registerLoginFacade.testPictureCode(str, session);
		return obj;
	}

	/**
	 * 合并验证码功能加强版登录
	 */

	@RequestMapping(value = "loginCode", method = RequestMethod.POST)
	@ResponseBody
	public Object loginCode(HttpSession session, @RequestBody RequestMessage<UserCode> info)
			throws ServletException, IOException {
		UserCode userCode = info.getRequestContext();
		Object obj = registerLoginFacade.loginCode(userCode, session);
		return obj;
	}

}
