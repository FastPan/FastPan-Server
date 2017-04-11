package com.stu.fastpan.service.manageFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.stu.fastpan.dao.mapper.manageFile.ManageFileMapper;
import com.stu.fastpan.dao.mapper.userfile.UserFileMapper;
import com.stu.fastpan.dao.pojo.manageFile.ExamineInformation;
import com.stu.fastpan.dao.pojo.manageFile.ManageFile;
import com.stu.fastpan.dao.pojo.manageUser.PageBean;
import com.stu.fastpan.dao.pojo.manageUser.PageInforBefore;
import com.stu.fastpan.dao.pojo.userfile.UserFile;
import com.stu.fastpan.service.base.BaseService;

@Service
public class ManageFileServiceImpl extends BaseService implements
		ManageFileService {

	private static Logger log = LoggerFactory
			.getLogger(ManageFileServiceImpl.class);

	@Autowired
	private ManageFileMapper manageFileMapper;
	
	@Autowired
    private UserFileMapper userFileMapper;

	@Override
	public Object getFileList(PageInforBefore pageInfor) {
		List<ManageFile> list;
		PageBean<ManageFile> pageInfo;

		PageHelper.startPage(pageInfor.getPageNum(), pageInfor.getPageSize());
		PageHelper.orderBy("update_time desc");

		try {
			list = manageFileMapper.selectFileList();
			pageInfo = new PageBean<ManageFile>(list);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		log.info("调用成功");
		return SUCCESS(pageInfo);

	}

	/*
	 * 删除文件
	 */
	@Override
	public Object delectFileInfor(String fileId) {
		int result = 0;
		if (fileId == null) {
			log.info("调用失败");
			return FAIL(1003, "入参失误");
		}
		
		ManageFile manageFile = manageFileMapper.selectByPrimaryKey(fileId);
		
		UserFile userFile= new UserFile();
		userFile.setFileId(fileId);
		userFile.setDeleteTime(new Date());
		userFile.setState(new Integer(2).byteValue());
		
		try {
			FileUtils.deleteQuietly(new File(manageFile.getFileUrl()));
			userFileMapper.updateFilesByManage(userFile);
			result = manageFileMapper.deleteByPrimaryKey(fileId);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		if (result == 0) {
			log.info("调用失败");
			return FAIL(1007, "删除失败");
		} else {
			log.info("调用成功");
			return SUCCESS("删除成功");
		}
	}

	/*
	 * 更新文件信息
	 */
	@Override
	public Object updateFileInfor(ManageFile manageFile) {
		int result = 0;
		if (manageFile == null) {
			log.info("调用失败");
			return FAIL(1003, "入参失误");
		}

		ManageFile manageFile2 = new ManageFile();
		manageFile2.setFileId(manageFile.getFileId());
		manageFile2.setFileState(manageFile.getFileState());
		manageFile2.setUpdateTime(new Date());

		try {
			result = manageFileMapper.updateByPrimaryKeySelective(manageFile2);
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		if (result == 0) {
			log.info("调用失败");
			return FAIL(1007, "更新失败");
		} else {
			log.info("调用成功");
			return SUCCESS("更新成功");
		}
	}

	/*
	 * 未审核文件管理
	 * 不加分页的
	 */
	@Override
	public Object getFileListShenHe() {
		List<ManageFile> list;
		PageHelper.orderBy("update_time desc");

		try {
			list = manageFileMapper.selectcheckFile();
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		log.info("调用成功");
//		return SUCCESS(pageInfo);
        return list;
	}
	
	/*
	 * 禁用文件列表
	 */
	@Override
	public Object getFileListForbidden() {
		List<ManageFile> list;
		PageHelper.orderBy("update_time desc");

		try {
			list = manageFileMapper.selectForbiddenFile();
		} catch (Exception e) {
			log.info("数据库语句问题");
			System.out.println(e.getMessage());
			return FAIL(1003, "业务参数错误");
		}
		log.info("调用成功");
//		return SUCCESS(pageInfo);
        return list;
	}

	/*
	 * 审核信息
	 */
	@Override
	public Object getExamineInfor() {
        List<ExamineInformation> list = new ArrayList<ExamineInformation>();
        ExamineInformation exa1 = new ExamineInformation(0,"未审核");
        ExamineInformation exa2 = new ExamineInformation(1,"审核通过");
        ExamineInformation exa3 = new ExamineInformation(2,"已和谐");
        list.add(exa1);
        list.add(exa2);
        list.add(exa3);
        return list;
	}
}
