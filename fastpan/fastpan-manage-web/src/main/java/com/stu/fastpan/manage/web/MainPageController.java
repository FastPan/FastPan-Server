package com.stu.fastpan.manage.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainPageController {
	
	// index页面
	@RequestMapping("/index")
	public String index() {
		return "/pages/main/index.html";
	}
	
	// 角色管理页面
	@RequestMapping("/manageRole")
	public String manageRole() {
		return "/pages/manage/manageRole.html";
	}
	
	// 人员管理页面
	@RequestMapping("/managePerson")
	public String managePerson() {
		return "/pages/manage/managePerson.html";
	}
	
	// 未审核文件管理页面
	@RequestMapping("/manageFile")
	public String manageFile() {
		return "/pages/manage/manageFile.html";
	}
	
	// 禁用文件管理页面
	@RequestMapping("/manageFile2")
	public String manageFile2() {
		return "/pages/manage/manageFile2.html";
	}
}
