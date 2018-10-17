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

import com.yada.enjoyIntegral.dao.ProductBaseDaoImpl;
import com.yada.enjoyIntegral.model.ProductBase;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class ProductBaseManager extends BaseService<ProductBase, java.lang.String>{
	@Autowired
	private ProductBaseDaoImpl productBaseDao;

	@Override
	protected BaseDao<ProductBase, java.lang.String> getBaseDao() {
		return productBaseDao;
	}

	public List<ProductBase> findProductsByIds(String productIds) {
		productIds.subSequence(0, productIds.lastIndexOf("-"));
		String[] ids = productIds.split("-");
		//return productBaseDao.findProductsByIds(ids);
		List<ProductBase> products = new ArrayList();
		for(String id:ids){
			ProductBase product = productBaseDao.getById(id);
			if(product!=null)
			 products.add(product);
		}
		return products;
	}
	public void updateReview(Map<String,Object> map){
		productBaseDao.updateReview(map);
	}

	/**
	 * @Author      ： zhangQiang
	 * @Date        : 2018/5/31-17:17
	 * @Desc        : 更新商品上下架状态
	 * @param productId: 商品id
	 * @param isGround:	   上下架状态
	 * @param startDate:   开始时间，如果下架，则开始时间为"",如果上架，则取当前时间
	 */
	public void updateIsGroup(String productId,String isGround){
		String startDate = "";
		if("1".equals(isGround)){
			startDate = "";
		}else{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String nowDate = sdf.format(date);
			startDate =nowDate;
		}
		productBaseDao.updateIsGroup(productId,isGround,startDate);
	}
}
