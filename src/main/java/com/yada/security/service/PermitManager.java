package com.yada.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.PermitDaoImpl;
import com.yada.security.model.Permit;

@Service
public class PermitManager extends BaseService<Permit, Long>{

	@Autowired
	private PermitDaoImpl permitDao;

	@Override
	protected BaseDao<Permit, Long> getBaseDao() {
		return permitDao;
	}
    //根据菜单查询permit
	public List<Permit> permitsByRes(String menuResId) {
		return permitDao.permitsByRes(menuResId);
	}
	//查询最大permit排序数
	public String getMaxPermitOrder(String resId) {
		return permitDao.getMaxPermitOrder(resId);
	}
	//查询菜单级别
	public String getMenuLevel(String resId) {
		return permitDao.getMenuLevel(resId);
	}
	//更新排序
	public int updateOrder(String permitId,int orderIndex) {
		return permitDao.updateOrder(permitId,orderIndex);
	}

	//根据menuResId删除permit(lrh)
	public void deleteByResId(Long menuResId){
		this.permitDao.deleteByResId(menuResId);
	}
}
