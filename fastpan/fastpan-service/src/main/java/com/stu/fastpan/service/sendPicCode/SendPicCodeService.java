package com.stu.fastpan.service.sendPicCode;

import io.jstack.sendcloud4j.SendCloud;
import io.jstack.sendcloud4j.mail.Email;
import io.jstack.sendcloud4j.mail.Result;
import io.jstack.sendcloud4j.mail.Substitution;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.stu.fastpan.dao.pojo.user.PictureCode;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.base.ResponseMeService;
import com.stu.fastpan.service.registerLogin.RegisterLoginService;
import com.stu.fastpan.util.NumberUtil;
import com.stu.fastpan.util.VerificationCodeUtil;

@Service
public class SendPicCodeService extends ResponseMeService<PictureCode, Long>
		implements SendPicCodeFacade {
	private static Logger log = LoggerFactory
			.getLogger(SendPicCodeService.class);

	/**
	 * 发送邮件功能
	 */

	@Override
	public Object sendEmail(String email1, HttpSession session) {

		Properties prop = new Properties();
		try {

			InputStream in = RegisterLoginService.class.getClassLoader()
					.getResourceAsStream("email.properties");
			prop.load(in); // 加载属性列表
			// Iterator<String> it=prop.stringPropertyNames().iterator();
			// while(it.hasNext()){
			// String key=it.next();
			// System.out.println(key+":"+prop.getProperty(key));
			// }
			// in.close();
		} catch (Exception e) {
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
		Email email = Email.template(templateInvokeName).from(from)
				.fromName(fromname).substitutionVars(Substitution.sub() // 模板变量替换
						.set("email", email1).set("number", number)).to(email1);

		Result result = webapi.mail().send(email);

		// System.out.println(result.getStatusCode());

		if (result.isSuccess()) {
			session.setAttribute("EmailCode", number);
			return SUCCESS("发送邮件成功");
		} else {
			return FAIL(result.getStatusCode(), result.getMessage());
		}
	}

	/**
	 * 发送图片验证码
	 */

	@Override
	public void sendPictureCode(HttpServletRequest request,
			HttpServletResponse response, PictureCode pictureCode,
			HttpSession session) throws ServletException, IOException {
		log.info("调用成功");
		VerificationCodeUtil.outputCaptcha(request, response,
				pictureCode.getWidth(), pictureCode.getHeight());
	}

	/**
	 * 验证图片验证码
	 */

	@Override
	public ResponseMessage testPictureCode(String str, HttpSession session) {

		String code = str.toUpperCase();
		if (code.equals("")) {
			log.info("入参失败");
			return FAIL(1003, "业务参数错误");
		} else {
			String verCode = (String) session.getAttribute("pictureCode");
			if (verCode.equals("")) {
				log.info("验证码过期");
				return FAIL(1004, "验证码失效");
			} else {
				if (verCode.equals(code)) {
					log.info("调用成功");
//					session.setAttribute("pictureCode", null);
					return SUCCESS("验证码正确");
				} else {
					log.info("验证码错误");
					return FAIL(1005, "验证码错误");
				}

			}

		}
	}

	/**
	 * 验证邮件验证码
	 */

	@Override
	public ResponseMessage testEmailCode(String str, HttpSession session) {

		String code = str.toUpperCase();

		if (code.equals("")) {
			log.info("入参失败");
			return FAIL(1003, "业务参数错误");
		} else {
			String verCode = (String) session.getAttribute("EmailCode");
			if (verCode==null) {
				log.info("验证码过期");
				return FAIL(1004, "验证码失效");
			} else {
				if (verCode.equals(code)) {
					log.info("调用成功");
//					session.setAttribute("EmailCode", null);
					return SUCCESS("验证码正确");
				} else {
					log.info("验证码错误");
					return FAIL(1005, "验证码错误");
				}

			}

		}
	}

}
