package com.stu.fastpan.service.registerLogin;

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
import com.stu.fastpan.service.base.ResponseMeService;
import com.stu.fastpan.util.MD5;
import com.stu.fastpan.util.NumberUtil;


@Service
public class RegisterLoginService extends ResponseMeService<User,Long> implements RegisterLoginFacade{
	private static Logger log = LoggerFactory.getLogger(RegisterLoginService.class);
	
	
	@Autowired
	private UserMapper usermapper;

	/**
	 *  注册功能
	 */
	
	@Override
	public Object register(User user) {
	    
		//进行密码加密
		String password = user.getPassword();
		String mPassword = MD5.getMD5(password.getBytes());
		user.setPassword(mPassword);
		
	    int result = 0; 
	    try{
	    	result = usermapper.insertSelective(user);
	    }catch(Exception e){
	    	log.info("数据库语句问题");
	    	System.out.println(e.getMessage());
	    }
	    if(result == 0){
	        return FAIL(1001, "添加失败");	
	    }else{
	    	log.info("注册成功");
	        return SUCCESS(result);
	    }
	}

	/**
	 * 登录功能
	 */
	
	@Override
	public Object login(User user2,HttpSession session) { 
	    User user = null; 
	    try{
	    	user = usermapper.selectByAccount(user2.getEmail());
	    	System.out.println(user);
	    }catch(Exception e){
	    	log.info("数据库语句问题");
	    	System.out.println(e.getMessage());
	    }
	    
	    String newPassword = MD5.getMD5(user2.getPassword().getBytes());
	    
		if(user.getPassword().equals(newPassword)){
			log.info("登录成功");
			//将登陆返回的值放入session中
			session.setAttribute("user", user);
			return SUCCESS(user);
		}else{
			log.info("登录失败");
			return FAIL(1002, "密码错误");
		}
	}

	/**
	 * 验证账号功能
	 */
	
	@Override
	public Object checkAccount(String email) {
		
	    Boolean result = false; 
	    User user = null; 
	    
	    try{
	    	user = usermapper.selectByAccount(email);
	    	System.out.println(user);
	    }catch(Exception e){
	    	log.info("数据库语句问题");
	    	System.out.println(e.getMessage());
	    }
	    
	    if(user ==null || "".equals(user)){
	    	log.info("账号错误");
	    	return FAIL(1000, "账号不存在");
	    }else{
	    	log.info("账号存在"); 
	    	result = true;
	        return SUCCESS(result);	
	    }
	}

	/**
	 * 发送邮件功能
	 */
	
	@Override
	public Object sendEmail(String email1) {
		
		String number = NumberUtil.getRandomCharAndNumr(6);
		String apiUser = "EcoKz_test_slQj9I";
		String apiKey = "1TeCeyHK8YtKdkfe";
		
		SendCloud webapi = SendCloud.createWebApi(apiUser, apiKey);
		
		
		Email email = Email.template("fastpan_sendEmail")
			    .from("1043244426@qq.com")
			    .fromName("客服支持<1043244426@qq.com>")
			    .substitutionVars(Substitution.sub()  // 模板变量替换
			            .set("email", email1)
			            .set("number", number))
			    .to(email1);
		
		Result result = webapi.mail().send(email);
		
//		System.out.println(result.getStatusCode());
		
	    if(result.isSuccess()){
	    	return SUCCESS(number);
	    }else{
	    	return FAIL(result.getStatusCode(), result.getMessage());
	    }
	}

	/**
	 * 发送图片验证码
	 */
	
	@Override
	public Object sendPictureCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
