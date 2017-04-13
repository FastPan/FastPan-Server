package com.stu.fastpan.dao.mapper.userfile;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.stu.fastpan.dao.pojo.userfile.UserFile;

public interface UserFileMapper {
    int deleteByPrimaryKey(String userFileId);

    int insert(UserFile record);

    int insertSelective(UserFile record);

    UserFile selectByPrimaryKey(String userFileId);
    
	List<UserFile> selectLikeFileName(@Param("userId") String userId, @Param("fileName") String fileName);

	List<UserFile> selectByFileType(@Param("userId") String userId, @Param("type") Byte type);
	
    List<UserFile>  selectByUserId(@Param("userId")String userId);
    				
    List<UserFile>  selectByUserIdAndPath(@Param("userId")String userId,@Param("path")String path);
    
    List<UserFile>  selectByUserIdPathState(@Param("userId")String userId,@Param("path")String path,@Param("state")Byte state);
    
    List<UserFile>  selectFolderByUserIdPathState(@Param("userId")String userId,@Param("path")String path,@Param("state")Byte state);
    
    int updateByPrimaryKeySelective(UserFile record);

    int updateByPrimaryKey(UserFile record);
    
    int updateFilesByManage(UserFile record);
    
    List<UserFile> selectDeleteFiles(String userId);
}