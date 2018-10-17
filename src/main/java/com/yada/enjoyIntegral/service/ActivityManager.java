/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.enjoyIntegral.dao.ActivityDaoImpl;
import com.yada.enjoyIntegral.dao.CustomerActivityDaoImpl;
import com.yada.enjoyIntegral.dao.CustomerInfoDaoImpl;
import com.yada.enjoyIntegral.model.Activity;
import com.yada.enjoyIntegral.model.CustomerActivity;
import com.yada.enjoyIntegral.model.CustomerInfo;
import com.yada.enjoyIntegral.model.MerchantEnjoy;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class ActivityManager extends BaseService<Activity, java.lang.String>{
	@Autowired
	private ActivityDaoImpl activityDao;
	
	@Autowired
	private CustomerInfoDaoImpl customerInfoDao;
	
	@Autowired
	private CustomerActivityDaoImpl customerActivityDao;
	
	@Autowired
	private CustomerActivityDaoImpl customerActivityDaoImpl;

	@Override
	protected BaseDao<Activity, java.lang.String> getBaseDao() {
		return activityDao;
	}

	public List<Map<String,String>> getMersByMerName(String merName) {
		return activityDao.getMersByMerName(merName);
	}

	public void saveUploadCustomers(Set<CustomerInfo> customerInfoSets,
			String activityId) {
		Iterator<CustomerInfo> iterator = customerInfoSets.iterator();
		while (iterator.hasNext()) {
			CustomerInfo customerInfo = iterator.next();
			customerInfoDao.saveOrUpdateEntity(customerInfo);
			CustomerActivity  customerActivity = new CustomerActivity();
			customerActivity.setActivityId(activityId); 
			customerActivity.setCertificateNo(customerInfo.getCertificateNo());
			customerActivity.setCertificateType(customerInfo.getCertificateType());
			customerActivity.setIsJoin("1");
			customerActivityDaoImpl.saveOrUpdateEntity(customerActivity);
		}
		
	}

	public List<Activity> getActivityByCustomerNo(String certificateTypeId, String certificateNo) {
		String dateStr = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return activityDao.getActivityByCustomerNo(certificateTypeId,certificateNo,dateStr);
	}

	public void deleteCustomer(String activityId, String certificateNo,String certificateType) {
		customerActivityDao.deleteCustomer(activityId,certificateNo,certificateType);
		
	}

	public void deleteAllCustomer(String activityId) {
		customerActivityDao.deleteAllCustomer(activityId);
		
	}

}
