/*
 * Powered By []
 * Web Site: 
 * 
 */

package com.yada.enjoyIntegral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.mybatis.paging.Page;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;

import java.util.*;

import com.yada.enjoyIntegral.model.*;
import com.yada.enjoyIntegral.dao.*;
import com.yada.enjoyIntegral.service.*;
import com.yada.enjoyIntegral.query.*;

/**
 * @author  sg
 * @version 1.0
 * @since 1.0
 */


@Service
public class ActivityProductEnjoyManager extends BaseService<ActivityProductEnjoy, java.lang.String>{
	@Autowired
	private ActivityProductEnjoyDaoImpl activityProductEnjoyDao;
	@Autowired
	private ProductEnjoyDaoImpl productEnjoyDao;
	@Autowired
	private ProductBaseDaoImpl productBaseDao;

	@Override
	protected BaseDao<ActivityProductEnjoy, java.lang.String> getBaseDao() {
		return activityProductEnjoyDao;
	}

	public void getPoolProduct(List<ProductCart> productCarts) {
		for(int i=0;i<productCarts.size();i++){
			ProductCart product = productCarts.get(i);
			//如果为尊享商品，就进行以下操作
			if(product.getProductType().equals("1")){
				ProductEnjoy productEnjoy = productEnjoyDao.getById(product.getProductId());
				if(productEnjoy!=null){
					if(product.getCount()>productEnjoy.getGiftCount()){
						throw new RuntimeException("商品"+product.getProductName()+"库存已经不足 ''");
					}else{
						productEnjoy.setGiftCount(productEnjoy.getGiftCount()-product.getCount());
						productEnjoyDao.update(productEnjoy);
					}

				}
			}

			//如果为普通商品，就进行以下操作
			if(product.getProductType().equals("0")){
				ProductBase productBase = productBaseDao.getById(product.getProductId());
				if(productBase!=null){
					Integer restCount = productBase.getGiftCount();
					if(product.getCount()>restCount){
						throw new RuntimeException("商品"+product.getProductName()+"库存已经不足 ''");
					}else{
						productBase.setGiftCount(productBase.getGiftCount()-product.getCount());
						productBaseDao.update(productBase);
					}
				}
			}
		}
	}

	public void retrunGiftCount(List<ProductCart> productCarts) {
		for(int i=0;i<productCarts.size();i++){
			ProductCart product = productCarts.get(i);
			//0为普通商户
			if("0".equals(product.getProductType())){
				ProductBase productBase = productBaseDao.getById(product.getProductId());
				if(productBase!=null){
					productBase.setGiftCount(productBase.getGiftCount()+product.getCount());
					productBaseDao.update(productBase);
				}
			}
			//1为尊享商品
			if("1".equals(product.getProductType())){
				ProductEnjoy activityProduct = productEnjoyDao.getById(product.getProductId());
				if(activityProduct!=null){
					activityProduct.setGiftCount(activityProduct.getGiftCount()+product.getCount());
					productEnjoyDao.update(activityProduct);
				}
			}
		}
		
	}

}
