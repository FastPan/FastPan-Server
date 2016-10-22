package com.stu.fastpan.page;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 分页类
 * @param <T>
 */

public class PageObject<T> implements Serializable {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	private List<T> data; // 信息对象list

	private PageInfo pageInfo; // 分页信息

	public PageObject() {
	}

	public T getIndexData(int index) {
		return (data != null && !data.isEmpty()) ? data.get(index) : null;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public int getSize() {
		return this.data == null ? 0 : this.data.size();
	}

}
