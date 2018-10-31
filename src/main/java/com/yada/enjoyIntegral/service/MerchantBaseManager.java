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
public class MerchantBaseManager extends BaseService<MerchantBase, java.lang.String>{
	@Autowired
	private MerchantBaseDaoImpl merchantBaseDao;

	@Override
	protected BaseDao<MerchantBase, java.lang.String> getBaseDao() {
		return merchantBaseDao;
	}

	public List<String> getAllMerchantNoBaseAndEnjoy() {
		return merchantBaseDao.getAllMerchantNoBaseAndEnjoy();
	}
	public void updateReview(Map<String,Object> map){
		merchantBaseDao.updateReview(map);
	}

	public void updateReason(String merNo,String serviceStare,String closeReason){


		merchantBaseDao.updateReason(merNo,serviceStare,closeReason);
	}

}
