package com.yada.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.Res;
import com.yada.security.model.Role;

@Component
public  class RoleDaoImpl extends BaseDaoImpl<Role, Long> {

	public List<Role> rolesByPermit(String permitId) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("rolesByPermit", permitId);
	}

	//查找资源的所有许可,查找所有角色列表,若拥有许可,权限checked为true,mybatis放入role.permits
	public List<Role> roleAllResPermits(Res res) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("roleAllResPermits", res);
	}

}
