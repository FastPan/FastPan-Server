package com.stu.fastpan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/share")
public class ShareController {
	// login页面
	@RequestMapping("/index")
	public String login() {
		return "/pages/share/share.html";
	}
}
