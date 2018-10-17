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
import com.yada.security.dao.impl.OperationLoginfoExtDaoImpl;
import com.yada.security.model.OperationLoginfoExt;

/**
 * @author LRH
 */

@Service
public class OperationLoginfoExtManager extends BaseService<OperationLoginfoExt,java.lang.String>{

	@Autowired
	private OperationLoginfoExtDaoImpl operationLoginfoExtDao;

	@Override
	protected BaseDao<OperationLoginfoExt,java.lang.String> getBaseDao() {
		// TODO Auto-generated method stub
		return operationLoginfoExtDao;
	}
}
