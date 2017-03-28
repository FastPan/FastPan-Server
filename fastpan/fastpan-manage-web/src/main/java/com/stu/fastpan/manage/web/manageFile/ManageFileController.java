package com.stu.fastpan.manage.web.manageFile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.dao.pojo.manageFile.ManageFile;
import com.stu.fastpan.dao.pojo.manageUser.PageInforBefore;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.service.manageFile.ManageFileService;

@Controller
@RequestMapping("/manageFile")
public class ManageFileController {

	@Autowired
	private ManageFileService manageFileService;
		
	/**
	 *  获取数据集合
	 */
	@ResponseBody
	@RequestMapping(value = "/getFileList", method = RequestMethod.POST)
	public Object getFileList(@RequestBody RequestMessage<PageInforBefore> info) {
		
		PageInforBefore pageInforBefore = info.getRequestContext();
		Object obj = manageFileService.getFileList(pageInforBefore);
		return obj;
	}
	
	/**
	 *  获取未审核文件
	 */
	@ResponseBody
	@RequestMapping(value = "/getcheckFileList", method = RequestMethod.GET)
	public Object getcheckFileList() {
		
		List<ManageFile> list = (List<ManageFile>) manageFileService.getFileListShenHe();
		for (ManageFile manageFile : list) {
			if(manageFile.getFileState().intValue()==0){
				manageFile.setExamineName("未审核");
			}else if(manageFile.getFileState().intValue()==1){
				manageFile.setExamineName("审核通过");
			}else {
				manageFile.setExamineName("已和谐");
			}
		}
		return list;
	}
	
	/**
	 *  获取禁用文件
	 */
	@ResponseBody
	@RequestMapping(value = "/getForbiddenFileList", method = RequestMethod.GET)
	public Object getForbiddenFileList() {
		
		List<ManageFile> list = (List<ManageFile>)manageFileService.getFileListForbidden();
		for (ManageFile manageFile : list) {
			if(manageFile.getFileState().intValue()==0){
				manageFile.setExamineName("未审核");
			}else if(manageFile.getFileState().intValue()==1){
				manageFile.setExamineName("审核通过");
			}else {
				manageFile.setExamineName("已和谐");
			}
		}
		return list;
	}
	
	/**
	 *  获取审核内容
	 */
	@ResponseBody
	@RequestMapping(value = "/getExamineInfor", method = RequestMethod.GET)
	public Object getExamineInfor() {
		
		Object obj = manageFileService.getExamineInfor();
		return obj;
	}
	
	/**
	 *  修改文件信息
	 *  文件状态:未审核-0，审核通过 1，已和谐 2
	 */
	@ResponseBody
	@RequestMapping(value = "/updateFileInfor", method = RequestMethod.POST)
	public Object updateFileInfor(@RequestBody RequestMessage<ManageFile> info) {
		
		ManageFile manageFile = info.getRequestContext();
		Object obj = manageFileService.updateFileInfor(manageFile);
		return obj;
	}
	
	/**
	 *  删除文件
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public Object deleteFile(@RequestBody RequestMessage<ManageFile> info) {
		
		ManageFile manageFile = info.getRequestContext();
		Object obj = manageFileService.delectFileInfor(manageFile.getFileId());
		return obj;
	}
	
}
