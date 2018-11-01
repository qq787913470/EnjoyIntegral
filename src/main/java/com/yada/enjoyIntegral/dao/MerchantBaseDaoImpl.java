package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.enjoyIntegral.model.MerchantBase;

@Component
public class MerchantBaseDaoImpl extends BaseDaoImpl<MerchantBase, java.lang.String>{

	public List<String> getAllMerchantNoBaseAndEnjoy() {
		
		return this.getSqlSessionTemplate().selectList("MerchantBase_getAllMerchantNoBaseAndEnjoy");
	}

	public void updateReview(Map<String,Object> map){
		this.getSqlSessionTemplate().update("MerchantBase_update_review",map);
	}
	public void updateReason(String merNo,String serviceStare,String closeReason){
		Map<String,String> map = new HashMap();
		map.put("merNo",merNo);
		map.put("serviceStare",serviceStare);
		map.put("closeReason",closeReason);
		this.getSqlSessionTemplate().update("MerchantBase_updateReason",map);
	}

}
