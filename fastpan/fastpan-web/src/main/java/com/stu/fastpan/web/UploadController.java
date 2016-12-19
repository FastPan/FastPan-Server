package com.stu.fastpan.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.stu.fastpan.dao.pojo.file.FileUpload;
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.file.FileService;
import com.stu.fastpan.web.utils.SessionUtils;

/**
 * 文件上传
 * 
 * 
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseMessage upload(FileUpload fileUpload,@RequestParam(value = "file") MultipartFile[] files, HttpSession session) {
		return fileService.fileUpload(SessionUtils.getUserId(session),fileUpload,files);
	}

	@RequestMapping(value = "/checkMD5", method = { RequestMethod.POST })
	@ResponseBody
	public ResponseMessage checkMD5(String MD5, String savePath, String fileName, HttpSession session) {
		User user = (User) session.getAttribute("user");
		return fileService.checkMD5(SessionUtils.getUserId(session), MD5, savePath, fileName);
	}

	
}
