package com.stu.fastpan.service.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.fastpan.dao.mapper.file.FileMapper;
import com.stu.fastpan.dao.pojo.file.File;
import com.stu.fastpan.service.base.BaseService;

@Service
public class FileServiceImpl extends BaseService implements FileService {

	@Autowired
	private FileMapper fileMapper;

	public File checkMD5FileExist(String MD5) {
		return fileMapper.selectByMD5(MD5);
	}

	@Override
	public boolean insert(File file) {
		if (fileMapper.insert(file) == 1) {
			return true;
		} else {
			return false;
		}
	}
}
