package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.FilterType;

public interface FilterTypeDao {

	// 插入
	public void insert(FilterType filterType);

	// 更新
	public void update(FilterType filterType);

	// 删除
	public void delete(int id);

	// 根据ID查询
	public FilterType getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(FilterType filterType);

	// 根据条件查询数据
	public List<FilterType> findListByWhere(FilterType filterType);
}
