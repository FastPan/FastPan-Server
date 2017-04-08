package com.stu.fastpan.dao.pojo.file;

public class FileUpload {
	private Long chunks;
	Long chunk;
	private String fileMd5;
	private String name;
	private Long size;
	private String fileSavePath;
	private Long lastModifiedDate;
	public Long getChunks() {
		return chunks;
	}
	public void setChunks(Long chunks) {
		this.chunks = chunks;
	}
	public Long getChunk() {
		return chunk;
	}
	public void setChunk(Long chunk) {
		this.chunk = chunk;
	}
	public String getFileMd5() {
		return fileMd5;
	}
	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFileSavePath() {
		return fileSavePath;
	}
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}
	public Long getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Long lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
		
}
