package com.stu.fastpan.dao.pojo.manageFile;

public class ExamineInformation {

	private int fileState;
	
	private String examineName;

	public ExamineInformation() {

	}
	
	public ExamineInformation(int fileState, String examineName) {
		this.fileState = fileState;
		this.examineName = examineName;
	}

	public int getFileState() {
		return fileState;
	}

	public void setFileState(int fileState) {
		this.fileState = fileState;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}
	
}
