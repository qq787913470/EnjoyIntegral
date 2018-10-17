package com.yada.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.Permit;
@Component
public class PermitDaoImpl extends BaseDaoImpl<Permit, Long>{

    //根据菜单ID查询permit列表
	public List<Permit> permitsByRes(String menuResId) {
		return getSqlSessionTemplate().selectList("permitsByRes", menuResId);
	}
	//查询最大permit索引
	public String getMaxPermitOrder(String resId) {
		return getSqlSessionTemplate().selectOne("getMaxPermitOrder", resId);
	}
	//查询菜单级别
	public String getMenuLevel(String resId) {
		return getSqlSessionTemplate().selectOne("getMenuLevel", resId);
	}
	//更新排序结果
	public int updateOrder(String permitId,int orderIndex) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("permitId", permitId);
		map.put("orderIndex", orderIndex);
		return getSqlSessionTemplate().update("updateOrder", map);
	}
	
	//根据menuResId删除permit(lrh)
	public void deleteByResId(Long menuResId){
		this.getSqlSessionTemplate().delete("deleteByResId", menuResId);
	}
}
