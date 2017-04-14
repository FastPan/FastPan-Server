package com.stu.fastpan.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stu.fastpan.dao.pojo.userfile.FileListModel;
import com.stu.fastpan.dao.pojo.userfile.UserFile;
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

	@RequestMapping(value = "/moveFile", method = RequestMethod.POST)
	@ResponseBody
	public Object moveFile(HttpSession session, @RequestBody FileListModel fileListModel) {
		boolean flag = true;
		System.out.println();
		List<String> fileList = fileListModel.getFileList();
		String path = fileListModel.getPath();
		for (String id : fileList) {
			UserFile selectByPrimaryKey = userFileService.selectByPrimaryKey(id);
			System.out.println(selectByPrimaryKey);
			if (userFileService.moveUserFile(id, path).isSuccess() == false) {
				flag = false;
			}
			if (selectByPrimaryKey.getFileId() == null) {
				if (userFileService.moveUserFile2(selectByPrimaryKey.getUserFileName(),selectByPrimaryKey.getPath(), path,SessionUtils.getUserId(session)).isSuccess() == false) {
					flag = false;
				}
			}
		}
		ResponseMessage rm;
		if (flag == false) {
			rm = new ResponseMessage(0, "移动失败", null, false);
		} else {
			rm = new ResponseMessage(1);
		}
		return rm;
	}
}
