package com.stu.fastpan.manage.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mainIndex")
public class MianController {

	@RequestMapping()
	public String login() {
		return "/pages/index.html";
	}

}
