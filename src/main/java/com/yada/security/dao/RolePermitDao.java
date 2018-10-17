package com.yada.security.dao;

import java.util.List;
import java.util.Map;

import com.yada.security.model.RolePermit;

public interface RolePermitDao {

	// 插入
	public void insert(RolePermit rolePermit);

	// 更新
	public void update(RolePermit rolePermit);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public RolePermit getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(RolePermit rolePermit);

	// 根据条件查询数据
	public List<RolePermit> findListByWhere(RolePermit rolePermit);

	public List<RolePermit> getMyDataOnDates(Map map);

	public List<RolePermit> RolePermit_findListByWhere(RolePermit rolePermit);

	public List<RolePermit> select_test(List list);

	public List<RolePermit> select_test(Map map);
}
