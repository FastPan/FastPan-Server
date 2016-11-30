package com.stu.fastpan.dao.pojo.file;

import java.util.Date;

public class File {
    private Long fileId;

    private String fileUrl;

    private Byte fileType;

    private Byte fileState;
    
    private String fileMD5;

    private Date createTime;

    private Date updateTime;

    
    public File() {
	}

	public File(String fileUrl, Byte fileType, Byte fileState, String fileMD5) {
		super();
		this.fileUrl = fileUrl;
		this.fileType = fileType;
		this.fileState = fileState;
		this.fileMD5 = fileMD5;
		this.createTime = new Date();
		this.updateTime = createTime;
	}

	public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Byte getFileType() {
        return fileType;
    }

    public void setFileType(Byte fileType) {
        this.fileType = fileType;
    }

    public Byte getFileState() {
        return fileState;
    }

    public void setFileState(Byte fileState) {
        this.fileState = fileState;
    }
    
    public String getFileMD5() {
		return fileMD5;
	}

	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileUrl=" + fileUrl + ", fileType=" + fileType
				+ ", fileState=" + fileState + ", fileMD5=" + fileMD5 + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
    
}