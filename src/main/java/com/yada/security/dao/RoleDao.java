package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.Role;

public interface RoleDao {

	// 插入
	public void insert(Role role);

	// 更新
	public void update(Role role);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public Role getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(Role role);

	// 根据条件查询数据
	public List<Role> findListByWhere(Role role);

	public List<Role> rolesByPermit(String permitId);
}
