package com.stu.fastpan.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.fastpan.dao.pojo.user.ForgetPassMax;
import com.stu.fastpan.dao.pojo.user.Password;
import com.stu.fastpan.dao.pojo.user.UpdateEmail;
import com.stu.fastpan.dao.pojo.user.UpdateInfor;
import com.stu.fastpan.dao.pojo.user.UserCode;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.service.accountSet.AccountSetFacade;
import com.stu.fastpan.service.forgetPass.ForgetPassFacade;
import com.stu.fastpan.service.registerLogin.RegisterLoginFacade;
import com.stu.fastpan.service.sendPicCode.SendPicCodeFacade;

/**
 * 
 * Description:用户的登录和注册
 * 
 * @date 创建时间： 2016年10月31日
 * @version 1.0
 */

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private RegisterLoginFacade registerLoginFacade;

	@Autowired
	private SendPicCodeFacade sendPicCodeFacade;
	
	@Autowired
	private AccountSetFacade accountSetFacade;
	
	@Autowired
	private ForgetPassFacade forgetPassFacade;

	// login页面
	@RequestMapping("login")
	public String login() {
		return "/pages/user/login.html";
	}

	// 注册页面
	@RequestMapping("register")
	public String register() {
		return "/pages/user/register.html";
	}
	
	// 注册页面
	@RequestMapping("accountInfo")
	public String accountInfo() {
		return "/pages/user/accountInfo.html";
	}
//	// 注册页面
//	@RequestMapping(value = "doRegister", method = RequestMethod.POST)
//	public String doRegister() {
//		return "redirect:/main/index";
//	}

	/**
	 * 用户的验证账号功能
	 */

	@ResponseBody
	@RequestMapping(value = "checkAccount", method = RequestMethod.POST)
	public Object checkAccount(@RequestBody RequestMessage<String> info) {

		String user = info.getRequestContext();
		System.out.println(user);
		Object obj = registerLoginFacade.checkAccount(user);
		return obj;
	}

//	/**
//	 * 用户的注册功能
//	 */
//
//	@ResponseBody
//	@RequestMapping(value = "register", method = RequestMethod.POST)
//	public Object register(@RequestBody RequestMessage<User> info,
//			HttpSession session) {
//
//		User user = info.getRequestContext();
//		System.out.println(user);
//		Object obj = registerLoginFacade.register(user, session);
//		return obj;
//	}

//	/**
//	 * 用户的登录功能
//	 */
//
//	@ResponseBody
//	@RequestMapping(value = "loginAccount", method = RequestMethod.POST)
//	public Object login(@RequestBody RequestMessage<User> info,
//			HttpSession session) {
//
//		User user = info.getRequestContext();
//		System.out.println(user);
//		Object obj = registerLoginFacade.login(user, session);
//		return obj;
//	}

	/**
	 * 合并验证码功能加强版登录
	 */

	@RequestMapping(value = "loginCode", method = RequestMethod.POST)
	@ResponseBody
	public Object loginCode(HttpSession session,
			@RequestBody RequestMessage<UserCode> info)
			throws ServletException, IOException {
		UserCode userCode = info.getRequestContext();
		Object obj = registerLoginFacade.loginCode(userCode, session);
		
		return obj;
	}

	/**
	 * 合并验证码功能加强版注册
	 */

	@RequestMapping(value = "registerCode", method = RequestMethod.POST)
	@ResponseBody
	public Object registerCode(HttpSession session,
			@RequestBody RequestMessage<UserCode> info)
			throws ServletException, IOException {
		UserCode userCode = info.getRequestContext();
		Object obj = registerLoginFacade.registerCode(userCode, session);
		return obj;
	}
	
	/**
	 * 查询用户信息
	 */

	@RequestMapping(value = "getPersonInfor", method = RequestMethod.GET)
	@ResponseBody
	public Object getPersonInfor(HttpSession session)
			throws ServletException, IOException {
		Object obj = accountSetFacade.selectByPrimaryKey(session);
		return obj;
	}

	/**
	 * 修改头像
	 */
	
	@RequestMapping(value = "updateImage", method = RequestMethod.POST)
	@ResponseBody
	public Object updateImage(@RequestBody RequestMessage<String> info,HttpSession session)
			throws ServletException, IOException {
		String image = info.getRequestContext();
		Object obj = accountSetFacade.updateImage(image, session);
		return obj;
	}
	
	/**
	 * 头像上传
	 */
	
	@RequestMapping(value = "uploadImage", method = RequestMethod.POST)
	@ResponseBody
	public Object uploadImage(@RequestParam(value = "file") MultipartFile file,HttpSession session)
			throws ServletException, IOException {
		Object obj = accountSetFacade.uploadImg(file, session);
		return obj;
	}
	
	/**
	 * 修改昵称
	 */
	
	@RequestMapping(value = "updateNickName", method = RequestMethod.POST)
	@ResponseBody
	public Object updateNickName(@RequestBody RequestMessage<String> info,HttpSession session)
			throws ServletException, IOException {
		String nickName = info.getRequestContext();
		Object obj = accountSetFacade.updateNickName(nickName, session);
		return obj;
	}
	
	/**
	 * 修改邮箱
	 */
	
	@RequestMapping(value = "updateEmail", method = RequestMethod.POST)
	@ResponseBody
	public Object updateEmail(@RequestBody RequestMessage<UpdateEmail> info,HttpSession session)
			throws ServletException, IOException {
		UpdateEmail updateEmail = info.getRequestContext();
		Object obj = accountSetFacade.updateEmail(updateEmail, session);
		return obj;
	}

	/**
	 * 修改邮箱第二步
	 */
	
	@RequestMapping(value = "updateEmail2", method = RequestMethod.POST)
	@ResponseBody
	public Object updateEmail2(@RequestBody RequestMessage<String> info,HttpSession session)
			throws ServletException, IOException {
		String newCode = info.getRequestContext();
		Object obj = accountSetFacade.updateEmail2(newCode, session);
		return obj;
	}
	
	/**
	 * 修改密码
	 */
	
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public Object updatePassword(@RequestBody RequestMessage<Password> info,HttpSession session)
			throws ServletException, IOException {
		Password password = info.getRequestContext();
		Object obj = accountSetFacade.updatePassword(password, session);
		return obj;
	}
	
	/**
	 * 忘记密码
	 */
	
	@RequestMapping(value = "forgetPassEmail", method = RequestMethod.POST)
	@ResponseBody
	public Object forgetPassEmail(@RequestBody RequestMessage<ForgetPassMax> info,HttpSession session)
			throws ServletException, IOException {
		ForgetPassMax forgetPassMax = info.getRequestContext();
		Object obj = forgetPassFacade.forgetPassEmail(forgetPassMax, session);
		return obj;
	}
	
	/**
	 * 修改邮箱和昵称
	 */
	
	@RequestMapping(value = "updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object updateEmailName(@RequestBody RequestMessage<UpdateInfor> info,HttpSession session)
			throws ServletException, IOException {
		UpdateInfor updateInfor = info.getRequestContext();
		Object obj = accountSetFacade.updateNameEm(updateInfor.getNickName(), updateInfor.getEmail(), session);
		return obj;
	}
	
}
