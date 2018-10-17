package com.yada.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.RestypeActionDaoImpl;
import com.yada.security.model.RestypeAction;

@Service
public class RestypeActionManager extends  BaseService<RestypeAction, Integer> {

	@Autowired
	private RestypeActionDaoImpl restypeActionDao;
	@Override
	protected BaseDao<RestypeAction, Integer> getBaseDao() {
		return restypeActionDao;
	}

	public String findRestypeActionListByTypeId(int typeId){
		List<RestypeAction> ras = restypeActionDao.findRestypeActionListByTypeId(typeId);
		String str = "";
		for(RestypeAction ra:ras){
			str = str+ra.getActionId()+",";
		}
		return str;
	}
	
	public void deleteByActionId(Long actionId){
		restypeActionDao.deleteByActionId(actionId);
	}
}
