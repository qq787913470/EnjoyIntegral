package com.yada.mybatis.paging;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PageRequest implements Serializable {
	/**
	 * @deprecated Field filters is deprecated
	 */
	private Object filters;
	private int pageNumber;
	private int pageSize;
	private String sortColumns;

	public PageRequest() {
		this(0, 0);
	}

	/**
	 * @deprecated Method PageRequest is deprecated
	 */

	public PageRequest(Object filters) {
		this(0, 0, filters);
	}

	public PageRequest(int pageNumber, int pageSize) {
		this(pageNumber, pageSize, (Object) null);
	}

	/**
	 * @deprecated Method PageRequest is deprecated
	 */

	public PageRequest(int pageNumber, int pageSize, Object filters) {
		this(pageNumber, pageSize, filters, null);
	}

	public PageRequest(int pageNumber, int pageSize, String sortColumns) {
		this(pageNumber, pageSize, null, sortColumns);
	}

	/**
	 * @deprecated Method PageRequest is deprecated
	 */

	public PageRequest(int pageNumber, int pageSize, Object filters, String sortColumns) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		setFilters(filters);
		setSortColumns(sortColumns);
	}

	/**
	 * @deprecated Method getFilters is deprecated
	 */

	public Object getFilters() {
		return filters;
	}

	/**
	 * @deprecated Method setFilters is deprecated
	 */

	public void setFilters(Object filters) {
		this.filters = filters;
	}

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
		checkSortColumnsSqlInjection(sortColumns);
		if (sortColumns != null && sortColumns.length() > 50) {
			throw new IllegalArgumentException("sortColumns.length() <= 50 must be true");
		} else {
			this.sortColumns = sortColumns;
			return;
		}
	}

	public List getSortInfos() {
		return Collections.unmodifiableList(SortInfo.parseSortColumns(sortColumns));
	}

	private void checkSortColumnsSqlInjection(String sortColumns) {
		if (sortColumns == null)
			return;
		if (sortColumns.indexOf("'") >= 0 || sortColumns.indexOf("\\") >= 0)
			throw new IllegalArgumentException((new StringBuilder()).append("sortColumns:").append(sortColumns).append(" has SQL Injection risk").toString());
		else
			return;
	}
}
