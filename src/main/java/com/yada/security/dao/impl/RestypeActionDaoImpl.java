package com.yada.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.RestypeAction;

@Component
public class RestypeActionDaoImpl  extends BaseDaoImpl<RestypeAction, Integer>{

	public List<RestypeAction> findRestypeActionListByTypeId(int typeId){
		return this.getSqlSessionTemplate().selectList("findRestypeActionListByTypeId", typeId);
	}
	
	public void deleteByActionId(Long actionId){
		this.getSqlSessionTemplate().delete("deleteByActionId", actionId);
	}
}
