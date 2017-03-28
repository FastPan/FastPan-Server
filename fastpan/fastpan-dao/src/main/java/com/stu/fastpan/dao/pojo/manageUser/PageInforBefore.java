package com.stu.fastpan.dao.pojo.manageUser;

public class PageInforBefore {

	private int pageNum;
	private int pageSize;
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "PageInforBefore [pageNum=" + pageNum + ", pageSize=" + pageSize
				+ "]";
	}

}
