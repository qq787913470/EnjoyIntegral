package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.UserGrpRoleIdx;

public interface UserGrpRoleIdxDao {

	// 插入
	public void insert(UserGrpRoleIdx userGrpRoleIdx);

	// 更新角色
	public void updateRoleId(UserGrpRoleIdx userGrpRoleIdx);

	// 更新用户分组
	public void updateUserGrpId(UserGrpRoleIdx userGrpRoleIdx);

	//根据 RoleId删除
	public void deleteByRoleId(Long id);
	
	//根据 UserGrpId删除
	public void deleteByUserGrpId(Long id);

	// 根据ID查询
	public  List<UserGrpRoleIdx> getByRoleId(Long id);

	// 根据ID查询
	public List<UserGrpRoleIdx> getByUserGrpId(Long id);

	// 根据条件查询数据数据COUNT
	public int findCountByWhere(UserGrpRoleIdx userGrpRoleIdx);

	// 根据条件查询数据
	public List<UserGrpRoleIdx> findListByWhere(UserGrpRoleIdx userGrpRoleIdx);
}
