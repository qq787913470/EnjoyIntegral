package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.Res;

public interface ResDao {

	// 插入
	public void insert(Res res);

	// 更新
	public void update(Res res);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public Res getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(Res res);

	// 根据条件查询数据
	public List<Res> Res_findListByWhere(Res res);
	
	// 根据条件查询数据
	public List<Res> getPMenuRes(Long menuId);
	
	
	//根据用户Id查询菜单
	public List<Res> findMenusByUserId(Long userId);

	public List<Res> findMenuResList();

	public List<Res> findUrlResList();
	
}
