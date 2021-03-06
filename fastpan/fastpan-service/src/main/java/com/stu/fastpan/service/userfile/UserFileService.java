package com.stu.fastpan.service.userfile;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stu.fastpan.dao.pojo.userfile.UserFile;
import com.stu.fastpan.message.ResponseMessage;

public interface UserFileService {

	ResponseMessage selectByUserIdPathState(@Param("userId") String userId, @Param("path") String path,
			@Param("state") Byte state);

	ResponseMessage selectFolderByUserIdPathState(@Param("userId") String userId, @Param("path") String path,
			@Param("state") Byte state);

	ResponseMessage selectByFileType(@Param("userId") String userId, @Param("type") Byte type);

	ResponseMessage insert(UserFile userFile);

	ResponseMessage selectLikeFileName(@Param("userId") String userId, @Param("fileName") String fileName);
	
	ResponseMessage moveUserFile(@Param("userFileId")String userFileId, @Param("moveTo") String path);
	
	ResponseMessage moveUserFile2(@Param("userFileName") String userFileName,@Param("path") String path, @Param("moveTo") String moveTo, @Param("userId") String userId);
	
	UserFile selectByPrimaryKey(String userFileId);
	
	ResponseMessage selectDeleteFiles(String userId);
	
	ResponseMessage updateDeleteFile(UserFile userFile);
	
	ResponseMessage updateDeleteFiles(List<String> userFileId);
	
	ResponseMessage backUserFiles(List<String> userFileId);
	
	ResponseMessage clearUserFiles(List<String> userFileId);
	
	ResponseMessage deleteUserFile(String userFileId);
	
	int deleteUserFile2(String userFileId);
	
}
