package com.yada.security.dao.impl;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.OperationLoginfo;

@Component
public class OperationLoginfoDaoImpl extends BaseDaoImpl<OperationLoginfo, String> {

	//获取当前主键的值
	public String getPKValue() {
		return getSqlSessionTemplate().selectOne("selectKey");
	}
	
}
