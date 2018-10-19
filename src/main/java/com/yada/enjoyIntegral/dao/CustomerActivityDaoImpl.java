package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.enjoyIntegral.model.CustomerActivity;
import com.yada.enjoyIntegral.model.CustomerInfo;

@Component
public class CustomerActivityDaoImpl extends BaseDaoImpl<CustomerActivity, java.lang.String>{

	public void saveOrUpdateEntity(CustomerActivity customerActivity) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("certificateNo", customerActivity.getCertificateNo());
		map.put("certificateType", customerActivity.getCertificateType());
		map.put("activityId", customerActivity.getActivityId());
		CustomerActivity customerAct = this.getSqlSessionTemplate().selectOne("CustomerActivity_getById_link", map);
		if(customerAct==null)
			this.insert(customerActivity);
		else
			this.update(customerActivity);
		
	}

	public void deleteCustomer(String activityId, String certificateNo, String certificateType) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("certificateNo", certificateNo);
		map.put("certificateType", certificateType);
		map.put("activityId", activityId);
		this.getSqlSessionTemplate().delete("CustomerActivity_deleteCustomer",map);
	}

	public void deleteAllCustomer(String activityId) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("activityId", activityId);
		this.getSqlSessionTemplate().delete("CustomerActivity_deleteAllCustomer",map);
		
	}

}
