package com.stu.fastpan.dao.pojo.userfile;

import java.util.List;

public class FileListModel {
	List<String> fileList;
	String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public FileListModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileListModel(List<String> fileList) {
		super();
		this.fileList = fileList;
	}

}
