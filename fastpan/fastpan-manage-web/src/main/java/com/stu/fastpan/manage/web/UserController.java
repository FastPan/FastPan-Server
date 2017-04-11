package com.stu.fastpan.manage.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.dao.pojo.user.UserCode;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.service.registerLogin.RegisterLoginFacade;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private RegisterLoginFacade registerLoginFacade;

	// login页面
	@RequestMapping("/login")
	public String login() {
		return "/pages/user/login.html";
	}

	// 登录
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public String doLogin(HttpSession session) { 
		session.setAttribute("user", "test");
		return "redirect:/main/index";
	}
	
	/**
	 * 合并验证码功能加强版登录
	 */

	@RequestMapping(value = "loginCode", method = RequestMethod.POST)
	@ResponseBody
	public Object loginCode(HttpSession session,
			@RequestBody RequestMessage<UserCode> info)
			throws ServletException, IOException {
		UserCode userCode = info.getRequestContext();
		Object obj = registerLoginFacade.loginCode(userCode, session);
		
		return obj;
	}
}
