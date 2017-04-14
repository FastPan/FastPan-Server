package com.stu.fastpan.service.userShare;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stu.fastpan.dao.pojo.userShare.UserShare;
import com.stu.fastpan.message.ResponseMessage;

public interface UserShareFacade {
	ResponseMessage selectAllShareRootId(String shareUserId);

	int insertShare(@Param("userId") String userId,@Param("path") String path,@Param("userFileName") String userFileName,@Param("fileId") String fileId,@Param("shareRootId") String shareRootId, @Param("userFileId") String userFileId);
	int insertShare2(@Param("userId") String userId,@Param("path") String path,@Param("userFileName") String userFileName,@Param("fileId") String fileId,@Param("shareRootId") String shareRootId, @Param("userFileId") String userFileId);

	ResponseMessage selectAllFromShareRootIdPathState(@Param("shareRootId") String shareRootId,
			@Param("path") String path, @Param("state") Byte state);
	
	ResponseMessage deleteByShareRootId(String shareRootId);

}
