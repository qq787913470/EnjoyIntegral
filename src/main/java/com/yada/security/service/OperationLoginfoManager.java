/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.mybatis.paging.Page;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.OperationLoginfoDaoImpl;
import com.yada.security.model.OperationLoginfo;
import com.yada.security.query.OperationLoginfoQuery;

/**
 * @author LRH
 */

@Service
public class OperationLoginfoManager extends BaseService<OperationLoginfo,java.lang.String>{

	@Autowired
	private OperationLoginfoDaoImpl operationLoginfoDao;

	@Override
	protected BaseDao<OperationLoginfo,java.lang.String> getBaseDao() {
		// TODO Auto-generated method stub
		return operationLoginfoDao;
	}
	
	/**
	 *	总对总平台稽核日志 
	 *	@author longwu.yan
	 */
	public Page findHhap2List(OperationLoginfoQuery query){
		return  operationLoginfoDao.queryPage(query, "OperationLoginfo_findHhap2ListByWhere");
	}
	
	/**
	 *  商户服务平台稽核日志
	 *  @author longwu.yan
	 */
	public Page findMerList(OperationLoginfoQuery query){
		return operationLoginfoDao.queryPage(query, "OperationLoginfo_findMerListByWhere");
	}
}
