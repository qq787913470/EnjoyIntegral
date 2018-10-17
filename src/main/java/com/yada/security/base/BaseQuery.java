package com.yada.security.base;

public class BaseQuery {

	private int pageNumber = 1;
	private int pageSize = 20;
	private String sortColumns;

	// 控制页面是否加载数据
	private boolean show = false;


	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public boolean isS_show() {
		return show;
	}

	public void setS_show(boolean s_show) {
		this.show = s_show;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public int getS_pageSize() {
		return pageSize;
	}

	public void setS_pageSize(int s_pageSize) {
		this.pageSize = s_pageSize;
	}

	public int getS_pageNumber() {
		return pageNumber;
	}

	public void setS_pageNumber(int s_pageNumber) {
		this.pageNumber = s_pageNumber;
	}

}
