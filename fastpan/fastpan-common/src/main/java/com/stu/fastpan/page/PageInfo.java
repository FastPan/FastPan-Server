package com.stu.fastpan.page;

import java.io.Serializable;

/**
 * 
 * 分页信息类
 */
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 默认每页显示多少记录 **/
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	private int maxPage = 0; // 最大页数

	private int totalCount = 0; // 总记录数

	private int currentPage = 0; // 当前页

	private int pageSize = DEFAULT_PAGE_SIZE; // 每页显示的记录数,默认为每页显示20条记录

	public int getMaxPage() {
		if (pageSize == 0)
			pageSize = DEFAULT_PAGE_SIZE;
		return totalCount / pageSize + ((totalCount % pageSize) > 0 ? 1 : 0);
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
