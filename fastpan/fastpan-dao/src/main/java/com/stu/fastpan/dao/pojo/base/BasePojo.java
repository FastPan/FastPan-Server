package com.stu.fastpan.dao.pojo.base;

import java.io.Serializable;

public class BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	public int currentPage;
	public int pageSize;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
