package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.UserGrp;

public interface UserGrpDao {

	// 插入
	public void insert(UserGrp userGrp);

	// 更新
	public void update(UserGrp userGrp);

	// 删除
	public void delete(Long id);

	// 根据ID查询
	public UserGrp getById(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(UserGrp userGrp);

	// 根据条件查询数据
	public List<UserGrp> findListByWhere(UserGrp userGrp);
}
