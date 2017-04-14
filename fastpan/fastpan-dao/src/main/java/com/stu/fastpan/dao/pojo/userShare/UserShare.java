package com.stu.fastpan.dao.pojo.userShare;

import java.util.Date;

import com.stu.fastpan.dao.pojo.file.File;

public class UserShare {
    private Long shareId;

    private String shareRootId;

    private String fileName;

    private String path;

    private String shareUserId;

    private Date createTime;

    private String fileId;

    private Byte state;
    
    private File file;

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public String getShareRootId() {
        return shareRootId;
    }

    public void setShareRootId(String shareRootId) {
        this.shareRootId = shareRootId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(String shareUserId) {
        this.shareUserId = shareUserId;
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
   
}