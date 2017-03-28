package com.stu.fastpan.dao.mapper.manageFile;

import java.util.List;

import com.stu.fastpan.dao.pojo.manageFile.ManageFile;

public interface ManageFileMapper {
	
	int updateByPrimaryKeySelective(ManageFile record);
	 
    int deleteByPrimaryKey(String fileId);

    List<ManageFile> selectFileList();
    
    List<ManageFile> selectcheckFile();
    
    List<ManageFile> selectForbiddenFile();
    
    int insert(ManageFile record);

    int insertSelective(ManageFile record);

    ManageFile selectByPrimaryKey(String fileId);

    int updateByPrimaryKey(ManageFile record);
}