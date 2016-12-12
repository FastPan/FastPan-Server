package com.stu.fastpan.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.userfile.UserFileService;
import com.stu.fastpan.web.utils.SessionUtils;

@Controller
@RequestMapping("/userFile")
public class UserFileController {

	@Autowired
	UserFileService userFileService;
	
	@RequestMapping(value="/getFileList",method=RequestMethod.GET)
	@ResponseBody
	public Object getFileList(HttpSession session,String path,Byte state){
		ResponseMessage selectByUserIdPathState = userFileService.selectByUserIdPathState(SessionUtils.getUserId(session), path, state);
		System.out.println(selectByUserIdPathState);
		return selectByUserIdPathState;
	}
}
