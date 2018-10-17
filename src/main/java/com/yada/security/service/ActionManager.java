package com.yada.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.ActionDaoImpl;
import com.yada.security.model.Action;

@Service
public class ActionManager extends BaseService<Action, Long>{

	@Autowired
	private ActionDaoImpl actionDao;

	@Override
	protected BaseDao<Action, Long> getBaseDao() {
		// TODO Auto-generated method stub
		return this.actionDao;
	}
	//新增类型
	public void insertType(Action action){
		actionDao.insertType(action);
	}
	//查询最大值
	public java.lang.Long nextVal(){
		return actionDao.nextVal();
	}
//根据菜单类型查询
	public List<Action> findListByRTpId(String resTypeId) {
		// TODO Auto-generated method stub
		return actionDao.findListByRTpId(resTypeId);
	}
	//查询所有菜单类型
	public List<Action> findListResType() {
		// TODO Auto-generated method stub
		return actionDao.findListResType();
	}
	//查询是否已经新增
	public List<Action> isInsert(String typeId) {
		// TODO Auto-generated method stub
		return actionDao.isInsert(typeId);
	}
	//查询最大排序数
	public String MaxOrderNumber(String typeId) {
		// TODO Auto-generated method stub
		return actionDao.MaxOrderNumber(typeId);
	}
	//更新排序结果
	public int updateOrder(String actionId,int index) {
		// TODO Auto-generated method stub
		return actionDao.updateOrder(actionId,index);
	}
}
