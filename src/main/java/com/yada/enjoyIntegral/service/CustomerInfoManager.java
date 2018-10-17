/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.mybatis.paging.Page;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.query.OperationLoginfoQuery;

import java.util.*;

import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.dao.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.enjoyIntegral.query.*;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class CustomerInfoManager extends BaseService<CustomerInfo, java.lang.String>{
	@Autowired
	private CustomerInfoDaoImpl customerInfoDao;

	@Override
	protected BaseDao<CustomerInfo, java.lang.String> getBaseDao() {
		return customerInfoDao;
	}

	public Page queryPageLinkActivity(CustomerInfoQuery query) {
		return customerInfoDao.queryPage(query, "CustomerInfo_findListByWhereLinkActivity");
	}

	public CustomerInfo getById(String certificateNo, String certificateType) {
		return customerInfoDao.getById(certificateNo,certificateType);
	}

}
