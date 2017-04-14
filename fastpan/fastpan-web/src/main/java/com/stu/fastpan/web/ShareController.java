package com.stu.fastpan.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.stu.fastpan.dao.pojo.userShare.UserShare;
import com.stu.fastpan.dao.pojo.userfile.FileListModel;
import com.stu.fastpan.dao.pojo.userfile.UserFile;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.userShare.UserShareService;
import com.stu.fastpan.service.userfile.UserFileService;
import com.stu.fastpan.web.utils.SessionUtils;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Autowired
	UserFileService userFileService;

	@Autowired
	private UserShareService userShareService;

	@RequestMapping("/index")
	public ModelAndView index(String shareRootId) {
		ModelAndView mv = new ModelAndView("/pages/share/share.html");
		return mv;
	}

	@RequestMapping(value = "/shareFile", method = RequestMethod.POST)
	@ResponseBody
	public Object shareFile(HttpSession session, @RequestBody FileListModel fileListModel) {
		List<String> fileList = fileListModel.getFileList();
		String shareRootId = UUID.randomUUID().toString().replaceAll("-", "");
		for (String id : fileList) {
			UserFile selectByPrimaryKey = userFileService.selectByPrimaryKey(id);
			userShareService.insertShare(SessionUtils.getUserId(session), selectByPrimaryKey.getPath(),
					selectByPrimaryKey.getUserFileName(), selectByPrimaryKey.getFileId(), shareRootId, id);
			if (selectByPrimaryKey.getFileId() == null) {
				userShareService.insertShare2(SessionUtils.getUserId(session), selectByPrimaryKey.getPath(),
						selectByPrimaryKey.getUserFileName(), selectByPrimaryKey.getFileId(), shareRootId, id);
			}
		}
		return new ResponseMessage(1);
	}

	@RequestMapping(value = "/getShareFileList", method = RequestMethod.GET)
	@ResponseBody
	public Object getShareFileList(HttpSession session) {
		ResponseMessage selectByUserIdPathState = userShareService
				.selectAllShareRootId(SessionUtils.getUserId(session));
		return selectByUserIdPathState;
	}

	@RequestMapping(value = "/getShareFilePathList", method = RequestMethod.GET)
	@ResponseBody
	public Object getFileList(HttpSession session, String shareRootId, String path, Byte state) {
		ResponseMessage selectByUserIdPathState = userShareService.selectAllFromShareRootIdPathState(shareRootId, path,
				state);
		return selectByUserIdPathState;
	}
	
	@RequestMapping(value = "/deleteShare", method = RequestMethod.GET)
	@ResponseBody
	public Object deleteShare(String shareRootId) {
		return userShareService.deleteByShareRootId(shareRootId);
	}

}
