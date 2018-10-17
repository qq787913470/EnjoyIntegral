/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.enjoyIntegral.dao.MerchantEnjoyDaoImpl;
import com.yada.enjoyIntegral.model.MerchantEnjoy;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;

import java.util.Map;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class MerchantEnjoyManager extends BaseService<MerchantEnjoy, java.lang.String>{
	@Autowired
	private MerchantEnjoyDaoImpl merchantEnjoyDao;

	@Override
	protected BaseDao<MerchantEnjoy, java.lang.String> getBaseDao() {
		return merchantEnjoyDao;
	}
	public void updateReview(Map<String,Object> map){
		merchantEnjoyDao.updateReview(map);
	}
}
