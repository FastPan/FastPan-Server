package com.stu.fastpan.service.accountSet;

import javax.servlet.http.HttpSession;

import com.stu.fastpan.dao.pojo.user.Password;
import com.stu.fastpan.dao.pojo.user.UpdateEmail;



public interface AccountSetFacade {

	Object selectByPrimaryKey(HttpSession session);
	
	Object updateImage(String image ,HttpSession session);
	
	Object updateEmail(UpdateEmail updateEmail , HttpSession session);
	
	Object updateEmail2(String newCode , HttpSession session);
	
	Object updatePassword(Password password,HttpSession session);
	
	Object updateNickName(String nickName,HttpSession session);

	Object updateNameEm(String nickName, String email, HttpSession session);
}
