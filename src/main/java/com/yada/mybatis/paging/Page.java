package com.yada.mybatis.paging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Page implements Serializable, Iterable {
	protected List result;
	protected int pageSize;
	protected int pageNumber;
	protected int totalCount;

	public Page(PageRequest p, int totalCount) {
		this(p.getPageNumber(), p.getPageSize(), totalCount);
	}

	public Page(int pageNumber, int pageSize, int totalCount) {
		this(pageNumber, pageSize, totalCount, ((List) (new ArrayList(0))));
	}

	public Page(int pageNumber, int pageSize, int totalCount, List result) {
		this.totalCount = 0;
		if (pageSize <= 0) {
			throw new IllegalArgumentException("[pageSize] must great than zero");
		} else {
			this.pageSize = pageSize;
			this.pageNumber = PageUtils.computePageNumber(pageNumber, pageSize, totalCount);
			this.totalCount = totalCount;
			setResult(result);
			return;
		}
	}

	public void setResult(List elements) {
		if (elements == null) {
			throw new IllegalArgumentException("'result' must be not null");
		} else {
			result = elements;
			return;
		}
	}

	public List getResult() {
		return result;
	}

	public boolean isFirstPage() {
		return getThisPageNumber() == 1;
	}

	public boolean isLastPage() {
		return getThisPageNumber() >= getLastPageNumber();
	}

	public boolean isHasNextPage() {
		return getLastPageNumber() > getThisPageNumber();
	}

	public boolean isHasPreviousPage() {
		return getThisPageNumber() > 1;
	}

	public int getLastPageNumber() {
		return PageUtils.computeLastPageNumber(totalCount, pageSize);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getThisPageFirstElementNumber() {
		return (getThisPageNumber() - 1) * getPageSize() + 1;
	}

	public int getThisPageLastElementNumber() {
		int fullPage = (getThisPageFirstElementNumber() + getPageSize()) - 1;
		return getTotalCount() >= fullPage ? fullPage : getTotalCount();
	}

	public int getNextPageNumber() {
		return getThisPageNumber() + 1;
	}

	public int getPreviousPageNumber() {
		return getThisPageNumber() - 1;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getThisPageNumber() {
		return pageNumber;
	}

	public List getLinkPageNumbers() {
		return PageUtils.generateLinkPageNumbers(getThisPageNumber(), getLastPageNumber(), 10);
	}

	public int getFirstResult() {
		return PageUtils.getFirstResult(pageNumber, pageSize);
	}

	public Iterator iterator() {
		return result != null ? result.iterator() : Collections.emptyList().iterator();
	}
}
