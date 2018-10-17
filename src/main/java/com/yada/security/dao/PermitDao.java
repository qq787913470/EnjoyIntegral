package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.Permit;
import com.yada.security.model.Res;

public interface PermitDao {

	// 插入
	public void insert(Permit permit);

	// 更新
	public void update(Permit permit);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public Permit getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(Permit permit);

	// 根据条件查询数据
	public List<Permit> Permit_findListByWhere(Permit permit);

	public List<Res> permitsByRes(String menuResId);

	public List<Res> rolesByPermit(String string);

	public List<Permit> resPermitsAllRole(Res res);
}
