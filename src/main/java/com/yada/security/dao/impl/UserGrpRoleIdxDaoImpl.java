package com.yada.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.UserGrpRoleIdx;

@Component
public class UserGrpRoleIdxDaoImpl extends BaseDaoImpl<UserGrpRoleIdx, Long> {
	// 更新角色
	public void updateRoleId(UserGrpRoleIdx userGrpRoleIdx){
		this.getSqlSessionTemplate().update("updateRoleId", userGrpRoleIdx);
	}

	// 更新用户分组
	public void updateUserGrpId(UserGrpRoleIdx userGrpRoleIdx){
		this.getSqlSessionTemplate().update("updateUserGrpId", userGrpRoleIdx);
	}

	//根据 RoleId删除
	public void deleteByRoleId(Long id){
		this.getSqlSessionTemplate().delete("deleteByRoleId", id);
	}
	
	//根据 UserGrpId删除
	public void deleteByUserGrpId(Long id){
		this.getSqlSessionTemplate().delete("deleteByUserGrpId", id);
	}

	// 根据RoleId查询
	public  List<UserGrpRoleIdx> selectByRoleId(Long id){
		return this.getSqlSessionTemplate().selectList("selectByRoleId", id);
	}

	// 根据serGrpIdD查询
	public List<UserGrpRoleIdx> selectByUserGrpId(Long id){
		return this.getSqlSessionTemplate().selectList("selectByUserGrpId", id);
	}

}
