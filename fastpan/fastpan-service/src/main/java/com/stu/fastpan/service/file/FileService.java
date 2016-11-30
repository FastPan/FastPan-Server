package com.stu.fastpan.service.file;

import com.stu.fastpan.dao.pojo.file.File;
import com.stu.fastpan.message.ResponseMessage;

public interface FileService {
	public File checkMD5FileExist(String MD5);
	public boolean insert(File file);
}
