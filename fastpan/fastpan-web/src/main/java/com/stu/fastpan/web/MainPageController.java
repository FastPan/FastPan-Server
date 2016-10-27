package com.stu.fastpan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/main")
public class MainPageController {
	//login页面
	@RequestMapping ("/index")
	public String login(){
		return "/pages/main/myPan.html";
	}
}
