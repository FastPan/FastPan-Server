package com.stu.fastpan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexHomeController {
	//index页面
	@RequestMapping ("/index")
	public String index(){
		return "/pages/index.html";
	}
}
