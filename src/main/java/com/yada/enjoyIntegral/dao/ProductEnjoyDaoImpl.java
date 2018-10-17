package com.yada.enjoyIntegral.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.enjoyIntegral.model.ProductEnjoy;
import com.yada.security.base.BaseDaoImpl;

@Component
public class ProductEnjoyDaoImpl extends BaseDaoImpl<ProductEnjoy, java.lang.String>{

	public List<ProductEnjoy> getProductsByMerNo(String merNo, String activityId) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("merNo", merNo);
		map.put("activityId", activityId);
		return this.getSqlSessionTemplate().selectList("ProductEnjoy_getProductsByMerNo",map);
	}

	public List<ProductEnjoy> findProductsByIds(String[] productIds) {
		return  this.getSqlSessionTemplate().selectList("ProductEnjoy_findProductsByIds", productIds);
	}

	public ProductEnjoy getProductEnjoyById(String productId) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("productId", productId);
		return this.getSqlSessionTemplate().selectOne("ProductEnjoy_getProductEnjoyById",map);
	}
	public void updateReview(Map<String,Object> map){
		this.getSqlSessionTemplate().update("ProductEnjoy_update_review",map);
	}

	public void updateIsGround(String productId ,String isGround,String startDate){
		Map<String,String> map = new HashMap<>();
		map.put("productId",productId);
		map.put("isGround",isGround);
		map.put("startDate",startDate);
		this.getSqlSessionTemplate().update("ProductEnjoy_updateIsGround",map);
	}
}
