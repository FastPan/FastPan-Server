package com.stu.fastpan.service.forgetPass;

import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.ForgetPassMax;
import com.stu.fastpan.dao.pojo.user.ForgetPassword;

public interface ForgetPassFacade {

	Object forgetPass(ForgetPassword forgetPassword,HttpSession session);
	
	Object forgetPassEmail(ForgetPassMax forgetPassMax,HttpSession session);
}
