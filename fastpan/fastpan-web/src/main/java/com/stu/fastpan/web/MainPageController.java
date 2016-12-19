package com.stu.fastpan.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainPageController {
	// 首页
	@RequestMapping("/index")
	public String index() {
		return "/pages/main/myPan.html";
	}

	// 保存路径
	@RequestMapping("/savePath")
	public String savePath() {
		return "/pages/main/savePath.html";
	}
}
