package com.stu.fastpan.service.userfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.fastpan.dao.mapper.userfile.UserFileMapper;
import com.stu.fastpan.dao.pojo.userfile.UserFile;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.base.BaseService;

@Service
public class UserFileServiceImpl extends BaseService implements UserFileService {
	@Autowired
	UserFileMapper userFileMapper;

	@Override
	public ResponseMessage selectByUserIdPathState(String userId, String path, Byte state) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userFileMapper.selectByUserIdPathState(userId, path, state));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public ResponseMessage insert(UserFile userFile) {
		ResponseMessage rm = null;
		try {
			if (1 == userFileMapper.insert(userFile)) {
				rm = SUCCESS();
			} else {
				rm = FAIL(9998, "数据库执行异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public ResponseMessage selectFolderByUserIdPathState(String userId, String path, Byte state) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userFileMapper.selectFolderByUserIdPathState(userId, path, state));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public ResponseMessage selectLikeFileName(String userId, String fileName) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userFileMapper.selectLikeFileName(userId, "%" + fileName + "%"));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public ResponseMessage selectByFileType(String userId, Byte type) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userFileMapper.selectByFileType(userId, type));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public ResponseMessage moveUserFile(String userFileId,  String path){
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userFileMapper.moveUserFile(userFileId, path));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public UserFile selectByPrimaryKey(String userFileId) {
		return userFileMapper.selectByPrimaryKey(userFileId);
	}

	@Override
	public ResponseMessage moveUserFile2(String userFileName, String path, String moveTo,String userId) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userFileMapper.moveUserFile2(userFileName, path, moveTo,userId));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

}
