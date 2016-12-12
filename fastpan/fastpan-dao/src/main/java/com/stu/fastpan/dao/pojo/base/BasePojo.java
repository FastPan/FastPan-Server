package com.stu.fastpan.dao.pojo.base;

import java.io.Serializable;
import java.util.UUID;

public class BasePojo implements Serializable {

	private static final long serialVersionUID = 1L;
	private int currentPage;
	private int pageSize;

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

	// 生成UUID
	public static String createUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
