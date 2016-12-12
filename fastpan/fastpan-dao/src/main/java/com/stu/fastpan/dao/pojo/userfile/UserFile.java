package com.stu.fastpan.dao.pojo.userfile;

import java.util.Date;

import com.stu.fastpan.dao.pojo.base.BasePojo;
import com.stu.fastpan.dao.pojo.file.File;

public class UserFile extends BasePojo{
    private String userFileId;

    private String userFileName;

    private String path;

    private String userId;

    private Date deleteTime;

    private Date createTime;

    private String fileId;

    private Byte state;
    
    private File file;

    
    public UserFile() {
	}

    
	public UserFile(String userFileId,String userFileName, String path, String userId, String fileId, Byte state) {
		super();
		this.userFileId=userFileId;
		this.userFileName = userFileName;
		this.path = path;
		this.userId = userId;
		this.fileId = fileId;
		this.state = state;
	}


	public String getUserFileId() {
        return userFileId;
    }

    public void setUserFileId(String userFileId) {
        this.userFileId = userFileId;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "UserFile [userFileId=" + userFileId + ", userFileName=" + userFileName + ", path=" + path + ", userId="
				+ userId + ", deleteTime=" + deleteTime + ", createTime=" + createTime + ", fileId=" + fileId
				+ ", state=" + state + "]";
	}
    
}