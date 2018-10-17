package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.RestypeAction;

public interface RestypeActionDao {

	// 插入
	public void insert(RestypeAction restypeAction);

	// 更新
	public void update(RestypeAction restypeAction);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public RestypeAction getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(RestypeAction restypeAction);

	// 根据条件查询数据
	public List<RestypeAction> findListByWhere(RestypeAction restypeAction);
}
