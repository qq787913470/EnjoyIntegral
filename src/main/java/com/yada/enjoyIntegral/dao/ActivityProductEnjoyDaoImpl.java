package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.enjoyIntegral.model.ActivityProductEnjoy;
import com.yada.enjoyIntegral.model.CustomerActivity;

@Component
public class ActivityProductEnjoyDaoImpl extends BaseDaoImpl<ActivityProductEnjoy, java.lang.String>{

	public ActivityProductEnjoy getByActivityIdAndProductId(String activityId,
			String productId) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("activityId", activityId);
		map.put("productId", productId);
		return this.getSqlSessionTemplate().selectOne("ActivityProductEnjoy_getByActivityIdAndProductId", map);
	}

}
