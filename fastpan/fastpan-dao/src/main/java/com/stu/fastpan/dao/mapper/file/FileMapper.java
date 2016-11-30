package com.stu.fastpan.dao.mapper.file;

import com.stu.fastpan.dao.pojo.file.File;

public interface FileMapper {
    int deleteByPrimaryKey(Long fileId);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(Long fileId);
    
    File selectByMD5(String MD5);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}