package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.enjoyIntegral.model.ProductBase;

@Component
public class ProductBaseDaoImpl extends BaseDaoImpl<ProductBase, java.lang.String>{

	public List<ProductBase> findProductsByIds(String[] productIds) {
		
		return  this.getSqlSessionTemplate().selectList("ProductBase_findProductsByIds", productIds);
	}
	public void updateReview(Map<String,Object> map){
		this.getSqlSessionTemplate().update("ProductBase_update_review",map);
	}
	public void updateIsGroup(String productId,String isGround,String startDate){
		Map<String,String> map = new HashMap();
		map.put("productId",productId);
		map.put("isGround",isGround);
		map.put("startDate",startDate);
		this.getSqlSessionTemplate().update("ProductBase_updateIsGroup",map);
	}
}
