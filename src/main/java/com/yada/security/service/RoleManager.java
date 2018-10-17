package com.yada.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.RoleDaoImpl;
import com.yada.security.model.Res;
import com.yada.security.model.Role;

@Service
public class RoleManager  extends BaseService<Role, Long> {

	@Autowired
	private RoleDaoImpl  RoleDao;
	@Override
	protected BaseDao<Role, Long> getBaseDao() {
		return RoleDao;
	}
	public List<Role> rolesByPermit(String permitId) {
		// TODO Auto-generated method stub
		return RoleDao.rolesByPermit(permitId);
	}
	public List<Role> roleAllResPermits(Res res) {
		// TODO Auto-generated method stub
		return RoleDao.roleAllResPermits(res);
	}
	
}
