package com.yada.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.UserGrpRoleIdxDaoImpl;
import com.yada.security.model.UserGrpRoleIdx;

@Service
public class UserGrpRoleIdxManager extends BaseService<UserGrpRoleIdx, Long> {

	@Autowired
	private UserGrpRoleIdxDaoImpl userGrpRoleIdxDaoImpl;
	
	@Override
	protected BaseDao<UserGrpRoleIdx, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return userGrpRoleIdxDaoImpl;
	}
	
	// 更新角色
	public void updateRoleId(UserGrpRoleIdx userGrpRoleIdx){
		userGrpRoleIdxDaoImpl.updateRoleId(userGrpRoleIdx);
	}

	// 更新用户分组
	public void updateUserGrpId(UserGrpRoleIdx userGrpRoleIdx){
		userGrpRoleIdxDaoImpl.updateUserGrpId(userGrpRoleIdx);
	}

	// 根据 RoleId删除
	public void deleteByRoleId(Long id){
		userGrpRoleIdxDaoImpl.deleteByRoleId(id);
	}
	
	//根据 UserGrpId删除
	public void deleteByUserGrpId(Long id){
		userGrpRoleIdxDaoImpl.deleteByUserGrpId(id);
	}

	// 根据ID查询
	public  List<UserGrpRoleIdx> selectByRoleId(Long id){
		return userGrpRoleIdxDaoImpl.selectByRoleId(id);
	}

	// 根据ID查询
	public List<UserGrpRoleIdx> selectByUserGrpId(Long id){
		return userGrpRoleIdxDaoImpl.selectByUserGrpId(id);
	}

}
