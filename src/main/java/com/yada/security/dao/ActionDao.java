package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.Action;

public interface ActionDao {

	// 插入
	public void insert(Action action);

	// 更新
	public void update(Action action);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public Action getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(Action action);

	// 根据条件查询数据
	public List<Action> findListByWhere(Action action);

	public List<Action> findListByRTpId(String resTypeId);
}
