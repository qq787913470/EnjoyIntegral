package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.ResType;

public interface ResTypeDao {

	// 插入
	public int insert(ResType resType);

	// 更新
	public int update(ResType resType);

	// 删除
	public int delete(Long id);

	// 根据ID查询
	public ResType getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(ResType resType);

	// 根据条件查询数据
	public List<ResType> findListByWhere(ResType resType);
}
