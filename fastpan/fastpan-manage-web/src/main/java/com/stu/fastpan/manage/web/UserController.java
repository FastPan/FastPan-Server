package com.stu.fastpan.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	// login页面
	@RequestMapping("/login")
	public String login() {
		return "/pages/user/login.html";
	}
}
