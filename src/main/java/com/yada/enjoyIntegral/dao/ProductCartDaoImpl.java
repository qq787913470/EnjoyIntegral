package com.yada.enjoyIntegral.dao;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.enjoyIntegral.model.ProductCart;

@Component
public class ProductCartDaoImpl extends BaseDaoImpl<ProductCart, java.lang.String>{

	public List<ProductCart> getProductsByCustomerNo(String customerNoHidden) {
		return this.getSqlSessionTemplate().selectList("ProductCart_getProductsByCustomerNo", customerNoHidden);
	}
	
	public List<ProductCart> getProductsByProductCartId(String productCartId) {
		return this.getSqlSessionTemplate().selectList("ProductCart_getProductsByProductCartId", productCartId);
	}

	public Connection getConnection() {
		return this.getSqlSessionTemplate().getConnection();
	}

}
