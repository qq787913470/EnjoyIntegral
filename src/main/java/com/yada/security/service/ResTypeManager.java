package com.yada.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.ResTypeDaoImpl;
import com.yada.security.model.ResType;

@Service
public class ResTypeManager extends BaseService<ResType, Long>{

	@Autowired
	private ResTypeDaoImpl resTypeDao;

	@Override
	protected BaseDao<ResType, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return resTypeDao;
	}

	
}
