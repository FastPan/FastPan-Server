package com.stu.fastpan.manage.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainPageController {
	// index页面
	@RequestMapping("/index")
	public String index() {
		return "/pages/main/index.html";
	}
}
