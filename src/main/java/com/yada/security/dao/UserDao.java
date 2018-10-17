package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.User;

public interface UserDao {

	// 插入
	public int insert(User user);

	// 更新
	public int update(User user);

	// 删除
	public int delete(Long id);

	// 根据ID查询
	public User getById(Long id);

	// 根据唯一键查询
	public User getByLoginName(java.lang.String loginName);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(User user);

	// 根据条件查询数据
	public List<User> findListByWhere(User user);

	//
	public List<User> selectAll();
}
