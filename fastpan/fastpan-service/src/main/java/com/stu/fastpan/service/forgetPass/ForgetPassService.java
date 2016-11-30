package com.stu.fastpan.service.forgetPass;

import java.util.Date;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stu.fastpan.dao.mapper.user.UserMapper;
import com.stu.fastpan.dao.pojo.user.ForgetPassMax;
import com.stu.fastpan.dao.pojo.user.ForgetPassword;
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.base.ResponseMeService;
import com.stu.fastpan.service.registerLogin.RegisterLoginService;
import com.stu.fastpan.service.sendPicCode.SendPicCodeService;
import com.stu.fastpan.util.MD5;

@Service
public class ForgetPassService extends
		ResponseMeService<ForgetPassword, String> implements ForgetPassFacade {

	private static Logger log = LoggerFactory
			.getLogger(ForgetPassService.class);

	@Autowired
	private UserMapper userMapper2;

	@Autowired
	private SendPicCodeService sendPicCodeService;

	@Autowired
	private RegisterLoginService registerLoginService;

	/**
	 * 忘记密码更新密码
	 */

	@Override
	public ResponseMessage forgetPass(ForgetPassword forgetPassword,
			HttpSession session) {
		int obj;
		String userId = forgetPassword.getUserId();
		if (userId != null) {
			User user2 = userMapper2.selectByPrimaryKey(userId);
			String newPassword = MD5.getMD5(forgetPassword.getPassword()
					.getBytes());

			if (sendPicCodeService.testEmailCode(forgetPassword.getCode(),
					session).isSuccess()) {
				Date date = new Date();
				user2.setPassword(newPassword);
				user2.setUpdateTime(date);

				try {
					obj = userMapper2.updateByPrimaryKeySelective(user2);
				} catch (Exception e) {
					log.info("数据库语句问题");
					System.out.println(e.getMessage());
					return FAIL(1003, "业务参数错误");
				}
				if (obj == 1) {
					session.setAttribute("user", user2);
					log.info("更新密码成功");
					return SUCCESS("更新密码成功");
				} else {
					log.info("更新失败");
					return FAIL(1007, "更新失败");
				}

			} else {
				log.info("验证码错误");
				return FAIL(1005, "验证码错误");
			}
		} else {
			log.info("业务参数错误");
			return FAIL(1003, "业务参数错误");
		}

	}

	/**
	 * 感觉拿不到userId的更新方法
	 */

	@Override
	public ResponseMessage forgetPassEmail(ForgetPassMax forgetPassMax,
			HttpSession session) {
		User user;
		ForgetPassword forgetPassword = new ForgetPassword();
		if (registerLoginService.checkAccount(forgetPassMax.getEmail())
				.isSuccess()) {
			try {
				user = userMapper2.selectByAccount(forgetPassMax.getEmail());
				System.out.println("sd:"+userMapper2);
				String userId = user.getUserId();
				forgetPassword.setUserId(userId);
				forgetPassword.setCode(forgetPassMax.getCode());
				forgetPassword.setPassword(forgetPassMax.getPassword());
			} catch (Exception e) {
				log.info("数据库语句问题");
				System.out.println(e.getMessage());
			}

		}

		return forgetPass(forgetPassword, session);
	}

}
