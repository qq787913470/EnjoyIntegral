package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.enjoyIntegral.model.CustomerInfo;

@Component
public class CustomerInfoDaoImpl extends BaseDaoImpl<CustomerInfo, java.lang.String>{

	public void saveOrUpdateEntity(CustomerInfo customerInfo) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("certificateNo", customerInfo.getCertificateNo());
		map.put("certificateType", customerInfo.getCertificateType());
		CustomerInfo customer = this.getSqlSessionTemplate().selectOne("CustomerInfo_getByUnionId", map);
		if(customer==null)
			this.insert(customerInfo);
		else
			this.update(customerInfo);
		
	}

	public CustomerInfo getById(String certificateNo, String certificateType) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("certificateNo", certificateNo);
		map.put("certificateType",certificateType);
		return this.getSqlSessionTemplate().selectOne("CustomerInfo_getByUnionId", map);
	}

}
