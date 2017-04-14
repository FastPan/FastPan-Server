package com.stu.fastpan.service.userShare;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.fastpan.dao.mapper.userShare.UserShareMapper;
import com.stu.fastpan.dao.pojo.userShare.UserShare;
import com.stu.fastpan.message.ResponseMessage;
import com.stu.fastpan.service.base.BaseService;

@Service
public class UserShareService extends BaseService implements UserShareFacade {
	private static Logger log = LoggerFactory.getLogger(UserShareService.class);

	@Autowired
	private UserShareMapper userShareMapper;

	@Override
	public ResponseMessage selectAllShareRootId(String shareUserId) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userShareMapper.selectAllShareRootId(shareUserId));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public int insertShare(@Param("userId") String userId, @Param("path") String path,
			@Param("userFileName") String userFileName, @Param("fileId") String fileId, String shareRootId,
			String userFileId) {
		return userShareMapper.insertShare(userId, path, userFileName, fileId, shareRootId, userFileId);
	}

	@Override
	public ResponseMessage selectAllFromShareRootIdPathState(String shareRootId, String path, Byte state) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userShareMapper.selectAllFromShareRootIdPathState(shareRootId, path, state));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

	@Override
	public int insertShare2(String userId, String path, String userFileName, String fileId, String shareRootId,
			String userFileId) {
		return userShareMapper.insertShare2(userId, path, userFileName, fileId, shareRootId, userFileId);
	}

	@Override
	public ResponseMessage deleteByShareRootId(String shareRootId) {
		ResponseMessage rm = null;
		try {
			rm = SUCCESS(userShareMapper.deleteByShareRootId(shareRootId));
		} catch (Exception e) {
			e.printStackTrace();
			rm = FAIL(9998, "数据库执行异常");
		}
		return rm;
	}

}
