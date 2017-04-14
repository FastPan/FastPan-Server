package com.stu.fastpan.service.userfile;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
	
	/** 回收站的操作
	 * 查询删除文件列表
	 */
	@Override
	public ResponseMessage selectDeleteFiles(String userId) {
		List<UserFile> list;
		
		if(userId==null){
			return FAIL(1006, "session失效");
		}
		try{
			list = userFileMapper.selectDeleteFiles(userId);
		}catch(Exception e){
			e.printStackTrace();
			return FAIL(9998, "数据库执行异常");
		}
        return SUCCESS(list);		
	}

	/** 
	 *  更新单个文件
	 */
	@Override
	public ResponseMessage updateDeleteFile(UserFile userFile) {
		int result = 0;
		
		if(userFile == null){
			return FAIL(1003, "入参失败");
		}
		try{
			userFile.setDeleteTime(new Date());
			result = userFileMapper.updateByPrimaryKeySelective(userFile);
		}catch(Exception e){
			e.printStackTrace();
			return FAIL(9998, "数据库执行异常");
		}
		
		if(result == 0){
			return FAIL(9998, "更新失败");
		}
		return SUCCESS("删除成功");
	}

	/** 
	 *  删除多个文件
	 */
	@Override
	public ResponseMessage updateDeleteFiles(List<String> userIdList) {
		if(userIdList == null){
			return FAIL(1003, "入参失败");
		}
		
		if(userIdList.size() > 0){
		for(String userFileId : userIdList){
			UserFile userFile = new UserFile();
			userFile.setUserFileId(userFileId);
			userFile.setState(new Integer(1).byteValue());
			updateDeleteFile(userFile);
			if(!updateDeleteFile(userFile).isSuccess()){
				return FAIL(3000, "删除文件失败");
			}
		}
		return SUCCESS("删除成功");
		}
		return FAIL(1003, "入参失败");
	}

	/** 
	 *  还原多个文件
	 */
	@Override
	public ResponseMessage backUserFiles(List<String> userIdList) {
		
		if(userIdList == null){
			return FAIL(1003, "入参失败");
		}
		if(userIdList.size() > 0){
		for(String userFileId : userIdList){
			UserFile userFile = new UserFile();
			userFile.setUserFileId(userFileId);
			userFile.setState(new Integer(0).byteValue());
			updateDeleteFile(userFile);
			if(!updateDeleteFile(userFile).isSuccess()){
				return FAIL(3000, "还原文件失败");
			}
		}
		return SUCCESS("还原成功");
		
		}
		return FAIL(1003, "入参失败");
	}

	/** 
	 *  清空回收站
	 */
	@Override
	public ResponseMessage clearUserFiles(List<String> userIdList) {
		int result = 0;
		if(userIdList == null){
			return FAIL(1003, "入参失败");
		}
				
		if(userIdList.size() > 0){
			
		for(String userFileId : userIdList){
			System.out.println(userFileId);
			result = deleteUserFile2(userFileId);
			if(result == 0){
				break;
			}
		}
		}
		
		if(result == 0){
			return FAIL(9998, "删除失败");
		}
		return SUCCESS("删除成功");
		
	}

	/** 
	 *  删除单个文件
	 */
	@Override
	public ResponseMessage deleteUserFile(String userFileId) {
		int result = 0;
		
		if(userFileId == null){
			return FAIL(1003, "入参失败");
		}
		try{
			result = userFileMapper.deleteByPrimaryKey(userFileId);
		}catch(Exception e){
			e.printStackTrace();
			return FAIL(9998, "数据库执行异常");
		}
		
		if(result == 0){
			return FAIL(9998, "删除失败");
		}
		return SUCCESS("删除成功");
	}
	
	@Override
	public int deleteUserFile2(String userFileId) {
		int result = 0;
		
		if(userFileId == null){
			return result;
		}
		try{
			result = userFileMapper.deleteByPrimaryKey(userFileId);
		}catch(Exception e){
			e.printStackTrace();
			return result;
		}
		
		if(result == 0){
			return result;
		}
		return 1;
	}

}
