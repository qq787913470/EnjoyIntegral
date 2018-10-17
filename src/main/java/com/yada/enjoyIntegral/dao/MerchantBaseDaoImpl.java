package com.yada.enjoyIntegral.dao;

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

}
