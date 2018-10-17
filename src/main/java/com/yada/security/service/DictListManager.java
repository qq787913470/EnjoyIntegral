/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package com.yada.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yada.security.model.*;
import com.yada.security.dao.impl.DictListDaoImpl;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

@Service
public class DictListManager extends BaseService<DictList,java.lang.String>{
	
	@Autowired
	private DictListDaoImpl DictListDao;

	@Override
	protected BaseDao<DictList, String> getBaseDao() {
		// TODO Auto-generated method stub
		return DictListDao;
	}
	
}
