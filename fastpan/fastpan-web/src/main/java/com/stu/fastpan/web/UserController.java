package com.stu.fastpan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	// login页面
	@RequestMapping("/login")
	public String login() {
		return "/pages/user/login.html";
	}

	// 注册页面
	@RequestMapping("/register")
	public String register() {
		return "/pages/user/register.html";
	}

	// 注册页面
	@RequestMapping(value = "/doRegister", method = RequestMethod.POST)
	public String doRegister() {
		return "redirect:/main/index";
	}

	// 账户设置页面
	@RequestMapping(value = "/accountInfo")
	public String accountInfo() {
		return "/pages/user/accountInfo.html";
	}
}
