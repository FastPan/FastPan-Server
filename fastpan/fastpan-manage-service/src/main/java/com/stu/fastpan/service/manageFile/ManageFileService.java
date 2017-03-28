package com.stu.fastpan.service.manageFile;

import com.stu.fastpan.dao.pojo.manageFile.ManageFile;
import com.stu.fastpan.dao.pojo.manageUser.PageInforBefore;

public interface ManageFileService {

	Object getFileList(PageInforBefore pageInforBefore);

	Object delectFileInfor(String fileId);

	Object updateFileInfor(ManageFile manageFile);
	
	Object getFileListShenHe();
	
	Object getFileListForbidden();
	
	Object getExamineInfor();
}
