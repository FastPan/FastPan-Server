package com.stu.fastpan.web.utils;

import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.User;

public class SessionUtils {
	public static String getUserId(HttpSession session){
		User user=(User) session.getAttribute("user");
		return user.getUserId();
	}
}
