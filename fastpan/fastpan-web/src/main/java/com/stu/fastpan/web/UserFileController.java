package com.stu.fastpan.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*import com.stu.fastpan.dao.pojo.userfile.FileListModel;*/
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.dao.pojo.userfile.UserFile;
import com.stu.fastpan.message.RequestMessage;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.userfile.UserFileService;
import com.stu.fastpan.web.utils.SessionUtils;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/userFile")
public class UserFileController {

	@Autowired
	UserFileService userFileService;

	@RequestMapping(value = "/getFileList", method = RequestMethod.GET)
	@ResponseBody
	public Object getFileList(HttpSession session, String path, Byte state) {
		ResponseMessage selectByUserIdPathState = userFileService
				.selectByUserIdPathState(SessionUtils.getUserId(session), path, state);
		System.out.println(selectByUserIdPathState);
		return selectByUserIdPathState;
	}

	@RequestMapping(value = "/getSavePathList", method = RequestMethod.GET)
	@ResponseBody
	public Object getSavePathList(HttpSession session, String path, Byte state) {
		ResponseMessage selectByUserIdPathState = userFileService
				.selectFolderByUserIdPathState(SessionUtils.getUserId(session), path, state);
		System.out.println(selectByUserIdPathState);
		return selectByUserIdPathState;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public Object search(HttpSession session, String filename) {
		ResponseMessage selectByUserIdPathState = userFileService.selectLikeFileName(SessionUtils.getUserId(session),
				filename);
		return selectByUserIdPathState;
	}

	@RequestMapping(value = "/newFloder", method = RequestMethod.POST)
	@ResponseBody
	public Object newFloder(UserFile userFile, HttpSession session) {
		userFile.setUserId(SessionUtils.getUserId(session));
		userFile.setUserFileId(UserFile.createUUID());
		System.out.println(userFile);
		ResponseMessage rm = userFileService.insert(userFile);
		System.out.println(rm);
		return rm;
	}

	@RequestMapping(value = "/getFileByType", method = RequestMethod.GET)
	@ResponseBody
	public Object getFileByType(HttpSession session, Byte type) {
		ResponseMessage selectByUserIdPathState = userFileService.selectByFileType(SessionUtils.getUserId(session),
				type);
		return selectByUserIdPathState;
	}

//	@RequestMapping(value = "/moveFile", method = RequestMethod.POST)
//	@ResponseBody
//	public Object moveFile(HttpSession session, @RequestBody FileListModel fileListModel) {
//		boolean flag = true;
//		// List<String> fileList = fileListModel.getFileList();
//		System.out.println();
//		List<String> fileList = fileListModel.getFileList();
//		// fileList=jsonArray.toList();
//		String path = fileListModel.getPath();
//		for (String id : fileList) {
//			UserFile selectByPrimaryKey = userFileService.selectByPrimaryKey(id);
//			System.out.println(selectByPrimaryKey);
//			if (userFileService.moveUserFile(selectByPrimaryKey.getUserFileId(), path).isSuccess() == false) {
//				flag = false;
//			}
//			if (selectByPrimaryKey.getFileId() == null) {
//				if (userFileService.moveUserFile2(selectByPrimaryKey.getUserFileName(),selectByPrimaryKey.getPath(), path).isSuccess() == false) {
//					flag = false;
//				}
//			}
//		}
//		ResponseMessage rm;
//		if (flag == false) {
//			rm = new ResponseMessage(0, "移动失败", null, false);
//		} else {
//			rm = new ResponseMessage(1);
//		}
//		return rm;
//	}
	
	/**
	 * 获取回收站列表
	 */
	@RequestMapping(value = "/getRecycleBinList", method = RequestMethod.GET)
	@ResponseBody
	public Object getRecycleBinList(HttpSession session) {
		ResponseMessage response;
		User user = (User) session.getAttribute("user");
		response = userFileService.selectDeleteFiles(user.getUserId());
		return response;
	}
	
	/**
	 * 删除单个文件
	 */
	@RequestMapping(value = "/deleteRecycleBinFile", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteRecycleBinFile(HttpSession session,@RequestBody RequestMessage<UserFile> infor) {
		UserFile userFile = infor.getRequestContext();
		ResponseMessage response;
//		User user = (User) session.getAttribute("user");
		userFile.setState(new Integer(1).byteValue());
		response = userFileService.updateDeleteFile(userFile);
		return response;
	}
	
	/**
	 * 删除多个文件
	 */
	@RequestMapping(value = "/deleteRecycleBinFiles", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteRecycleBinFiles(HttpSession session,@RequestBody List<String> userFileId) {
		ResponseMessage response;
//		User user = (User) session.getAttribute("user");
		response = userFileService.updateDeleteFiles(userFileId);
		return response;
	}
	
	/**
	 * 还原单个文件
	 */
	@RequestMapping(value = "/backRecycleBinFile", method = RequestMethod.POST)
	@ResponseBody
	public Object backRecycleBinFile(HttpSession session,@RequestBody RequestMessage<UserFile> infor) {
		UserFile userFile = infor.getRequestContext();
		ResponseMessage response;
//		User user = (User) session.getAttribute("user");
		userFile.setState(new Integer(0).byteValue());
		response = userFileService.updateDeleteFile(userFile);
		return response;
	}
	
	/**
	 * 还原多个文件
	 */
	@RequestMapping(value = "/backRecycleBinFiles", method = RequestMethod.POST)
	@ResponseBody
	public Object backRecycleBinFiles(HttpSession session,@RequestBody List<String> userFileId) {
		ResponseMessage response;
//		User user = (User) session.getAttribute("user");
		response = userFileService.backUserFiles(userFileId);
		return response;
	}
	
	/**
	 * 清除单个文件
	 */
	@RequestMapping(value = "/removeRecycleBinFile", method = RequestMethod.POST)
	@ResponseBody
	public Object removeRecycleBinFile(HttpSession session,@RequestBody RequestMessage<UserFile> infor) {
		UserFile userFile = infor.getRequestContext();
		ResponseMessage response;

		response = userFileService.deleteUserFile(userFile.getUserFileId());
		return response;
	}
	
	/**
	 * 清除多个文件
	 */
	@RequestMapping(value = "/clearRecycleBinFiles", method = RequestMethod.POST)
	@ResponseBody
	public Object clearRecycleBinFiles(HttpSession session,@RequestBody List<String> userFileId) {
		ResponseMessage response;
//		User user = (User) session.getAttribute("user");
		response = userFileService.clearUserFiles(userFileId);
		return response;
	}
	
}
