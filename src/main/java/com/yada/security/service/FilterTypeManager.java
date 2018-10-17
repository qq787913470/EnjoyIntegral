package com.yada.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.FilterTypeDaoImpl;
import com.yada.security.model.FilterType;

@Service
public class FilterTypeManager extends BaseService<FilterType, Long>{

	@Autowired
	private FilterTypeDaoImpl filterTypeDao;

	@Override
	protected BaseDao<FilterType, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return filterTypeDao;
	}

}
