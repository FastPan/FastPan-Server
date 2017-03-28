package com.stu.fastpan.manage.web.manageUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.dao.pojo.manageUser.ManageUser;
import com.stu.fastpan.dao.pojo.manageUser.PageInforBefore;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.service.manageUser.ManageUserService;

@Controller
@RequestMapping("/manageUser")
public class ManageUserController {

    @Autowired
	ManageUserService manageUserService;
	
	/**
	 *  获取数据集合
	 */

	@ResponseBody
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public Object getUserList(@RequestBody RequestMessage<PageInforBefore> info) {

		PageInforBefore pageInfor = info.getRequestContext();
		System.out.println(pageInfor);
		Object obj = manageUserService.selectUserList(pageInfor);
		return obj;
	}
	
	/**
	 *  获取数据集合
	 */

	@ResponseBody
	@RequestMapping(value = "/getUserListno", method = RequestMethod.GET)
	public Object getUserListno() {
		
		Object obj = manageUserService.selectUserList();
		return obj;
	}
	
	/**
	 *  更新角色信息
	 */
	@ResponseBody
	@RequestMapping(value = "/updateUserInfor", method = RequestMethod.POST)
	public Object updateUserInfor(@RequestBody RequestMessage<ManageUser> info) {
		ManageUser manageUser = info.getRequestContext();
		System.out.println(manageUser);
		Object obj = manageUserService.updateUser(manageUser);
		return obj;
	}
	
	/**
	 *  获取单条数据
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserInfor", method = RequestMethod.POST)
	public Object getUserInfor(@RequestBody RequestMessage<ManageUser> info) {
		ManageUser manageUser = info.getRequestContext();
		System.out.println(manageUser);
		Object obj = manageUserService.selectUserOne(manageUser.getUserId());
		return obj;
	}
	
}
