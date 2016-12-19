package com.stu.fastpan.service.file;

import org.springframework.web.multipart.MultipartFile;

import com.stu.fastpan.dao.pojo.file.File;
import com.stu.fastpan.dao.pojo.file.FileUpload;
import com.stu.fastpan.dao.pojo.user.User;
import com.stu.fastpan.message.ResponseMessage;

public interface FileService{
	public File checkMD5FileExist(String MD5);
	public boolean insert(File file);
	public ResponseMessage fileUpload(String userId, FileUpload fileUpload, MultipartFile[] files);
	public ResponseMessage checkMD5(String userId,String MD5, String savePath, String fileName);
}
