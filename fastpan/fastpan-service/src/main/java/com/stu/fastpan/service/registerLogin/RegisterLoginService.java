package com.stu.fastpan.service.registerLogin;


import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.fastpan.dao.mapper.user.UserMapper;
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.dao.pojo.user.UserCode;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.base.ResponseMeService;
import com.stu.fastpan.service.sendPicCode.SendPicCodeService;
import com.stu.fastpan.util.MD5Utils;

@Service
public class RegisterLoginService extends ResponseMeService<User, Long>
		implements RegisterLoginFacade {
	private static Logger log = LoggerFactory
			.getLogger(RegisterLoginService.class);

	@Autowired
	private UserMapper usermapper;

	@Autowired
	private SendPicCodeService sendPicCodeService;

	/**
	 * 注册功能 暂时不用了
	 */

	@Override
	public ResponseMessage register(User user, HttpSession session) {

		// 进行密码加密
		String password = user.getPassword();
		String mPassword = MD5Utils.getMD5(password.getBytes());
		user.setPassword(mPassword);
		user.setUserId(user.createConstraint());

		int result = 0;
		try {
			result = usermapper.insertSelective(user);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		if (result == 0) {
			return FAIL(1001, "添加失败");
		} else {
			log.info("注册成功");
			user.setPassword(password);
			session.setAttribute("user", user);
			return SUCCESS(result);
		}
	}

	/**
	 * 登录功能 暂时不用了
	 */

	@Override
	public ResponseMessage login(User user2, HttpSession session) {

		User user = null;
		try {
			user = usermapper.selectByAccount(user2.getEmail());
			System.out.println(user);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}

		if (user == null) {
			log.info("登录失败,用户不存在");
			return FAIL(1000, "用户不存在");
		} else {

			String newPassword = MD5Utils.getMD5(user2.getPassword().getBytes());

			if (user.getPassword().equals(newPassword)) {
				log.info("登录成功");
				// 将登陆返回的值放入session中
				session.setAttribute("user", user);
				
				User userTime = new User();
	
				Date date = new Date();			
				userTime.setUserId(user.getUserId());
				userTime.setLastLoginTime(date);
				usermapper.updateByPrimaryKeySelective(userTime);
				return SUCCESS("登录成功");
			} else {
				log.info("登录失败");
				return FAIL(1002, "密码错误");
			}

		}
	}

	/**
	 * 验证账号功能
	 */

	@Override
	public ResponseMessage checkAccount(String email) {

		Boolean result = false;
		User user = null;

		try {
			user = usermapper.selectByAccount(email);
			System.out.println(user);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}

		if (user == null || "".equals(user)) {
			log.info("账号错误");
			return FAIL(1000, "账号不存在");
		} else {
			log.info("账号存在");
			result = true;
			return SUCCESS(result);
		}
	}

	/**
	 * 加强版登录和验证码一起
	 * 和图片验证码一起
	 */

	@Override
	public ResponseMessage loginCode(UserCode userCode, HttpSession session) {
		User user = new User();
		sendPicCodeService.testPictureCode(userCode.getCode(), session);
		if (sendPicCodeService.testPictureCode(userCode.getCode(), session)
				.isSuccess()) {

			user.setEmail(userCode.getEmail());
			user.setPassword(userCode.getPassword());
			return login(user, session);
		} else {
			return FAIL(1005, "验证码错误");
		}

	}

	/**
	 * 加强版注册和验证码一起
	 * 和邮件验证码一起
	 */

	@Override
	public ResponseMessage registerCode(UserCode userCode, HttpSession session) {
		User user = new User();

		sendPicCodeService.testEmailCode(userCode.getCode(), session);

		if (sendPicCodeService.testEmailCode(userCode.getCode(), session)
				.isSuccess()) {

			user.setEmail(userCode.getEmail());
			user.setPassword(userCode.getPassword());
			user.setNickName(userCode.getNickName());
			return register(user, session);
		} else {
			return FAIL(1005, "验证码错误");
		}

	}

}
