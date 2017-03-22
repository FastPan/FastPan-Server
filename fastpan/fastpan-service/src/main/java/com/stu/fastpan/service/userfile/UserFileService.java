package com.stu.fastpan.service.userfile;

import org.apache.ibatis.annotations.Param;

import com.stu.fastpan.dao.pojo.userfile.UserFile;
import com.stu.fastpan.message.ResponseMessage;

public interface UserFileService {

	ResponseMessage  selectByUserIdPathState(@Param("userId") String userId, @Param("path") String path,
			@Param("state") Byte state);
	ResponseMessage  selectFolderByUserIdPathState(@Param("userId") String userId, @Param("path") String path,
			@Param("state") Byte state);
	ResponseMessage insert(UserFile userFile);
}
