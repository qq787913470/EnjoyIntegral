package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.RoleRes;

public interface RoleResDao {

	// 插入
	public void insert(RoleRes roleRes);

	// 更新
	public void update(RoleRes roleRes);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public RoleRes getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(RoleRes roleRes);

	// 根据条件查询数据
	public List<RoleRes> findListByWhere(RoleRes roleRes);
}
