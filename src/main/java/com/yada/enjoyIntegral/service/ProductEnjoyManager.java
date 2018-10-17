/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.enjoyIntegral.dao.ProductEnjoyDaoImpl;
import com.yada.enjoyIntegral.model.ProductEnjoy;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class ProductEnjoyManager extends BaseService<ProductEnjoy, java.lang.String>{
	@Autowired
	private ProductEnjoyDaoImpl productEnjoyDao;

	@Override
	protected BaseDao<ProductEnjoy, java.lang.String> getBaseDao() {
		return productEnjoyDao;
	}

	public List<ProductEnjoy> getProductsByMerNo(String merNo, String activityId) {
		return productEnjoyDao.getProductsByMerNo(merNo,activityId);
	}

	public List<ProductEnjoy> findProductsByIds(String productIds) {
		productIds.subSequence(0, productIds.lastIndexOf("-"));
		String[] ids = productIds.split("-");
		//return productEnjoyDao.findProductsByIds(ids);
		List<ProductEnjoy> products = new ArrayList();
		for(String id:ids){
			ProductEnjoy product = productEnjoyDao.getById(id);
			if(product!=null)
			 products.add(product);
		}
		return products;
	}

	public ProductEnjoy getProductEnjoyById(String productId) {
		return productEnjoyDao.getProductEnjoyById(productId);
	}
	public void updateReview(Map<String,Object> map) {
		productEnjoyDao.updateReview(map);
	}
	/**
	 * @Author      ： zhangQiang
	 * @Date        : 2018/6/1-9:24
	 * @Desc        : 商品的上下架
	 * @param productId: 商品id
	 * @param isGround:  商品状态  1表示下架   0表示上架
	 */
	public void updateIsGround(String productId,String isGround){
		String startDate;
		if("1".equals(isGround)){
			startDate = "";
		}else{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String nowDate = sdf.format(date);
			startDate = nowDate;
		}
		productEnjoyDao.updateIsGround(productId,isGround,startDate);
	}
}
