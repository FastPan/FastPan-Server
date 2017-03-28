package com.stu.fastpan.manage.web.manageRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.dao.pojo.manageRole.ManageRole;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.service.manageRole.ManageRoleService;

@Controller
@RequestMapping("/manageRole")
public class ManageRoleController {

	@Autowired
	private ManageRoleService manageRoleService;
	
	/**
	 *  获取数据集合
	 */
	@ResponseBody
	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	public Object getRoleList() {
		
		Object obj = manageRoleService.selectRoleList();
		return obj;
	}
	
	/**
	 *  添加权限信息
	 */
	@ResponseBody
	@RequestMapping(value = "/insertRoleInfor", method = RequestMethod.POST)
	public Object updateRoleInfor(@RequestBody RequestMessage<ManageRole> info) {
		ManageRole manageRole = info.getRequestContext();
		System.out.println(manageRole);
		Object obj = manageRoleService.insertRole(manageRole);
		return obj;
	}
	
	/**
	 *  删除权限信息
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteRoleInfor", method = RequestMethod.POST)
	public Object  deleteRoleInfor(@RequestBody RequestMessage<ManageRole> info) {
		ManageRole manageRole = info.getRequestContext();
		System.out.println(manageRole);
		Object obj = manageRoleService.delectRoleInfor(manageRole.getRoleId());
		return obj;
	}
}
