package com.yada.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.Action;

@Component
public class ActionDaoImpl extends BaseDaoImpl<Action, Long>{

	//插入动作
	public void insertType(Action action){
		this.getSqlSessionTemplate().insert("insertType", action);
	}
	//获取主键
	public java.lang.Long nextVal(){
		return this.getSqlSessionTemplate().selectOne("nextVal");
	}
    //根据id查询
	public List<Action> findListByRTpId(String resTypeId) {
		return getSqlSessionTemplate().selectList("findListByRTpId", resTypeId);
	}
	//查询所有菜单类型
	public List<Action> findListResType() {
		return getSqlSessionTemplate().selectList("findListResType");
	}
	//判断是否已经插入
	public List<Action> isInsert(String typeId) {
		return getSqlSessionTemplate().selectList("isInsert",typeId);
	}
	//查询最大排序号
	public String MaxOrderNumber(String typeId) {
		return getSqlSessionTemplate().selectOne("MaxOrderNumber",typeId);
	}
	//更新排序后的结果
	public int updateOrder(String actionId,int index) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("actionId", actionId);
		map.put("index", index);
		return getSqlSessionTemplate().update("updateActionOrder",map);
	}
}