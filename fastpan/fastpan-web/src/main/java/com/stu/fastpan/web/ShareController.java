package com.stu.fastpan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/share")
public class ShareController {
	// login页面
	@RequestMapping("/index")
	public String login() {
		return "/pages/share/share.html";
	}
}
