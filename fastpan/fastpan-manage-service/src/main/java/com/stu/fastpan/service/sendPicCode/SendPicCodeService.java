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

}
