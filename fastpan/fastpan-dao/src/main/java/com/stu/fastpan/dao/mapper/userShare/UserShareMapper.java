package com.stu.fastpan.dao.mapper.userShare;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.stu.fastpan.dao.pojo.userShare.UserShare;

public interface UserShareMapper {
	int deleteByPrimaryKey(Long shareId);

	int deleteByShareRootId(String shareRootId);

	int insert(UserShare record);

	int insertShare(@Param("userId") String userId, @Param("path") String path,
			@Param("userFileName") String userFileName, @Param("fileId") String fileId,
			@Param("shareRootId") String shareRootId, @Param("userFileId") String userFileId);

	int insertShare2(@Param("userId") String userId, @Param("path") String path,
			@Param("userFileName") String userFileName, @Param("fileId") String fileId,
			@Param("shareRootId") String shareRootId, @Param("userFileId") String userFileId);

	int insertSelective(UserShare record);

	UserShare selectByPrimaryKey(Long shareId);

	List<Long> selectAllShareRootId(String shareUserId);

	List<UserShare> selectAllFromShareRootIdPathState(@Param("shareRootId") String shareRootId,
			@Param("path") String path, @Param("state") Byte state);

	int updateByPrimaryKeySelective(UserShare record);

	int updateByPrimaryKey(UserShare record);
}