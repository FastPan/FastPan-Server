package com.stu.fastpan.manage.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
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
}
