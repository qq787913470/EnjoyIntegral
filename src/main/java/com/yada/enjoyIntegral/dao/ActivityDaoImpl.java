package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.enjoyIntegral.model.Activity;
import com.yada.enjoyIntegral.model.MerchantEnjoy;
import com.yada.security.base.BaseDaoImpl;

@Component
public class ActivityDaoImpl extends BaseDaoImpl<Activity, java.lang.String>{

	public List<Map<String,String>> getMersByMerName(String merName) {
		return this.getSqlSessionTemplate().selectList("Activity_getMersByMerName",merName);
	}

	public List<Activity> getActivityByCustomerNo(String certificateTypeId, String certificateNo,String nowDate) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("certificateTypeId", certificateTypeId);
		map.put("certificateNo", certificateNo);
		map.put("nowDate", nowDate);
		return this.getSqlSessionTemplate().selectList("Activity_getActivityByCustomerNo",map);
	}

	public Activity getActivityByCustomerNoAndProductId(
			String certificateTypeId,String certificateNo, String productId) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("certificateTypeId", certificateTypeId);
		map.put("certificateNo", certificateNo);
		map.put("productId", productId);
		
		return this.getSqlSessionTemplate().selectOne("Activity_getActivityByCustomerNoAndProductId",map);
	}

	public void updateIsGroup(String activityId,String state,String closeReason){
		Map<String,String> map = new HashMap();
		map.put("activityId",activityId);
		map.put("state",state);
		map.put("closeReason",closeReason);
		this.getSqlSessionTemplate().update("Activity_updateIsGroup",map);
	}

}
