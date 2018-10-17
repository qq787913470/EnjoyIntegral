package com.yada.mybatis.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SortInfo implements Serializable {

	private static final long serialVersionUID = 0x6096cb81bbcd57d2L;
	private String columnName;
	private String sortOrder;

	public SortInfo() {
	}

	public SortInfo(String columnName, String sortOrder) {
		this.columnName = columnName;
		this.sortOrder = sortOrder;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public static List parseSortColumns(String sortColumns) {
		if (sortColumns == null)
			return new ArrayList(0);
		List results = new ArrayList();
		String sortSegments[] = sortColumns.trim().split(",");
		for (int i = 0; i < sortSegments.length; i++) {
			String sortSegment = sortSegments[i];
			String array[] = sortSegment.split("\\s+");
			SortInfo sortInfo = new SortInfo();
			sortInfo.setColumnName(array[0]);
			sortInfo.setSortOrder(array.length != 2 ? null : array[1]);
			results.add(sortInfo);
		}

		return results;
	}

	public String toString() {
		return (new StringBuilder()).append(columnName).append(sortOrder != null ? (new StringBuilder()).append(" ").append(sortOrder).toString() : "")
				.toString();
	}
}