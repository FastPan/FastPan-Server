package com.stu.fastpan.service.registerLogin;

import java.io.InputStream;
import java.util.Properties;
import io.jstack.sendcloud4j.SendCloud;
import io.jstack.sendcloud4j.mail.Email;
import io.jstack.sendcloud4j.mail.Result;
import io.jstack.sendcloud4j.mail.Substitution;
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
import com.stu.fastpan.util.MD5;
import com.stu.fastpan.util.NumberUtil;

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
	 * 注册功能
	 */

	@Override
	public ResponseMessage register(User user) {

		// 进行密码加密
		String password = user.getPassword();
		String mPassword = MD5.getMD5(password.getBytes());
		user.setPassword(mPassword);

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
			return SUCCESS(result);
		}
	}

	/**
	 * 登录功能
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

			String newPassword = MD5.getMD5(user2.getPassword().getBytes());

			if (user.getPassword().equals(newPassword)) {
				log.info("登录成功");
				// 将登陆返回的值放入session中
				session.setAttribute("user", user);
				return SUCCESS(user);
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
	 * 发送邮件功能
	 */

	@Override
	public Object sendEmail(String email1,HttpSession session) {

		Properties prop = new Properties();
		try{

		InputStream in = RegisterLoginService.class.getClassLoader().getResourceAsStream("email.properties"); 
		prop.load(in);     //加载属性列表
//		Iterator<String> it=prop.stringPropertyNames().iterator();
//		while(it.hasNext()){
//		        String key=it.next();
//		        System.out.println(key+":"+prop.getProperty(key));
//		       }
//		in.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
		String number = NumberUtil.getRandomCharAndNumr(6);
		String apiUser = prop.getProperty("apiUser");
		String apiKey = prop.getProperty("apiKey");
		String templateInvokeName = prop.getProperty("templateInvokeName");
		String from = prop.getProperty("from");
		String fromname = prop.getProperty("fromname");
		SendCloud webapi = SendCloud.createWebApi(apiUser, apiKey);

		@SuppressWarnings("rawtypes")
		Email email = Email.template(templateInvokeName)
				.from(from).fromName(fromname)
				.substitutionVars(Substitution.sub() // 模板变量替换
						.set("email", email1).set("number", number)).to(email1);

		Result result = webapi.mail().send(email);

		// System.out.println(result.getStatusCode());

		if (result.isSuccess()) {
			session.setAttribute("EmailCode",number);
			return SUCCESS();
		} else {
			return FAIL(result.getStatusCode(), result.getMessage());
		}
	}

	/**
	 * 加强版登录和验证码一起
	 */

	@Override
	public Object loginCode(UserCode userCode, HttpSession session) {
		User user = new User();
		sendPicCodeService.testPictureCode(userCode.getCode(), session);
		if (sendPicCodeService.testPictureCode(userCode.getCode(), session).isSuccess()) {

			user.setEmail(userCode.getEmail());
			user.setPassword(userCode.getPassword());
			return login(user, session);
		}else{
			return FAIL(1005, "验证码错误");
		}
		
	}
	
	/**
	 * 加强版注册和验证码一起
	 */
	
	@Override
	public Object registerCode(UserCode userCode, HttpSession session){
		User user = new User();
		sendPicCodeService.testPictureCode(userCode.getCode(), session);
		if (sendPicCodeService.testPictureCode(userCode.getCode(), session).isSuccess()) {

			user.setEmail(userCode.getEmail());
			user.setPassword(userCode.getPassword());
			user.setNickName(userCode.getNickName());
			return register(user);
		}else{
			return FAIL(1005, "验证码错误");
		}
		
	}

}
