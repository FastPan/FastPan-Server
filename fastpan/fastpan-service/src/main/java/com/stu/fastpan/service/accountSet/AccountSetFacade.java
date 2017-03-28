package com.stu.fastpan.service.accountSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.stu.fastpan.dao.pojo.user.Password;
import com.stu.fastpan.dao.pojo.user.UpdateEmail;



public interface AccountSetFacade {

	Object selectByPrimaryKey(HttpSession session);
		
	Object updateEmail(UpdateEmail updateEmail , HttpSession session);
	
	Object updateEmail2(String newCode , HttpSession session);
	
	Object updatePassword(Password password,HttpSession session);
	
	Object updateNickName(String nickName,HttpSession session);

	Object updateNameEm(String nickName, String email, HttpSession session);
	
//	Object uploadImg(CommonsMultipartFile file,HttpSession session);
	
	Object updateImage(HttpSession session);
	
    Object uploadImg(String strImg , HttpSession session);
}
