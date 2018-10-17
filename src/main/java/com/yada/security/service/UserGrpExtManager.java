/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.UserGrpExtDaoImpl;
import com.yada.security.model.UserGrpExt;

@Service
public class UserGrpExtManager extends BaseService<UserGrpExt,java.lang.Long>{
	
	@Autowired
	private UserGrpExtDaoImpl userGrpExtDaoImpl;
	
	@Override
	protected BaseDao<UserGrpExt, Long> getBaseDao() {
		return userGrpExtDaoImpl;
	}

	
}
