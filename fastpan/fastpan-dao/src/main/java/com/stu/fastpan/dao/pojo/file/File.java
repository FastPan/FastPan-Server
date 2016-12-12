package com.stu.fastpan.dao.pojo.file;

import java.util.Date;

import com.stu.fastpan.dao.pojo.base.BasePojo;

public class File extends BasePojo {
	private String fileId;

	private String fileUrl;
	
	private String fileName;
	
	private Byte fileType;

	private Byte fileState;

	private Long fileSize;

	private String fileMD5;

	private Date createTime;

	private Date updateTime;

	public File() {
	}

	public File(String fileUrl,Byte fileType, Byte fileState, Long fileSize, String fileMD5) {
		this.fileId = createUUID();
		this.fileUrl = fileUrl;
		this.fileType = fileType;
		this.fileState = fileState;
		this.fileMD5 = fileMD5;
		this.fileName=fileMD5;
		this.createTime = new Date();
		this.updateTime = createTime;
		this.fileSize = fileSize;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
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
		return "File [fileId=" + fileId + ", fileUrl=" + fileUrl + ", fileType=" + fileType + ", fileState=" + fileState
				+ ", fileMD5=" + fileMD5 + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}