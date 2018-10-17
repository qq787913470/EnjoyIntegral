package com.yada.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.Res;

@Component
public class ResDaoImpl extends BaseDaoImpl<Res, Long> {

	// 根据用户Id查询菜单
	public List<Res> findMenusByUserId(Long userId) {
		return this.getSqlSessionTemplate().selectList("findMenusByUserId",userId);
	}

	// 根据用户登录名称查询菜单
	public List<Res> findMenusByLoginName(String loginName) {
		return this.getSqlSessionTemplate().selectList("findMenusByLoginName",loginName);
	}
	public List<Res> findPMenuIdbyRes(String pMenuId,String typeId) {
		Map map=new HashMap();
		map.put("pMenuId", pMenuId);
		map.put("typeId", typeId);
		return this.getSqlSessionTemplate().selectList("findPMenuIdbyRes",map);
	}

	// 根据条件查询数据
	public List<Res> getPMenuRes(Long menuId) {
		return this.getSqlSessionTemplate().selectList("getPMenuRes", menuId);
	}

	// 获取所有资源权限列表
	public List<Res> findPermitsResList(Long roleId, String resTypeId, String pmenuResId, String menuResId) {
		Map map = new HashMap();
		map.put("roleId", roleId);
		map.put("resTypeId", resTypeId);
		map.put("pmenuResId", pmenuResId);
		map.put("menuResId", menuResId);
		return this.getSqlSessionTemplate().selectList("findPermitsResList",map);
	}

	//获取当前主键的值
	public Long getPKValue() {
		return getSqlSessionTemplate().selectOne("RES_SELECTKEY");
	}

	//带主键的insert
	public void insertWithPK(Res res) {
		getSqlSessionTemplate().insert("Res_insertWithPK", res);
	}
	
	/*
	 * 获取菜单类型资源
	 */
	public List<Res> findMenuResList() {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("findMenuResList");
	}
	
	/*
	 * 获取url类型资源
	 */
	public List<Res> findUrlResList() {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("findUrlResList");
	}

	public List<Res> findResPermits(Res res) {
		// TODO Auto-generated method stub
		return getSqlSessionTemplate().selectList("resPermits", res);
	}
	
	//判断是否是叶子节点
	public Long findResIsLeaf(Long pmenuResId) {
		return this.getSqlSessionTemplate().selectOne("findResIsLeaf",pmenuResId);
	}
	
	public List<Res> findListByPmenu(Long pmenuResId, Long typeId) {
		Map map = new HashMap();
		map.put("typeId", typeId);
		map.put("pmenuResId", pmenuResId);
		return this.getSqlSessionTemplate().selectList("findListByPmenu",map);
	}
	
	//查找order_id最大值
	public Long findOrderMax(Long pmenuResId) {
		return this.getSqlSessionTemplate().selectOne("findOrderMax",pmenuResId);
	}
	
	//修改order_id值
	public Long updateOrderId(Long menuResId, Long orderId) {
		Map map = new HashMap();
		map.put("menuResId", menuResId);
		map.put("orderId", orderId);
		return this.getSqlSessionTemplate().selectOne("updateOrderId",map);
	}
	
	//获得排序数据
	public List<Res> findByOrder(Long pmenuResId, Long typeId) {
		Map map = new HashMap();
		map.put("pmenuResId", pmenuResId);
		map.put("typeId", typeId);
		return this.getSqlSessionTemplate().selectList("findByOrder",map);
	}
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_updLikeFlagAct(){
		getSqlSessionTemplate().update("Res_init_updLikeFlagAct");
	}
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotAdminPmt(){
		getSqlSessionTemplate().update("Res_init_allotAdminPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotIcCSPmt(){
		getSqlSessionTemplate().update("Res_init_allotIcCSPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotIcSHPmt(){
		getSqlSessionTemplate().update("Res_init_allotIcSHPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotIcCXPmt(){
		getSqlSessionTemplate().update("Res_init_allotIcCXPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotIcBBPmt(){
		getSqlSessionTemplate().update("Res_init_allotIcBBPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotMerGLPmt(){
		getSqlSessionTemplate().update("Res_init_allotMerGLPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotMerTHPmt(){
		getSqlSessionTemplate().update("Res_init_allotMerTHPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotMerUDPmt(){
		getSqlSessionTemplate().update("Res_init_allotMerUDPmt");
	}
	
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotMerGGPmt(){
		getSqlSessionTemplate().update("Res_init_allotMerGGPmt");
	}
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotCXPmt(){
		getSqlSessionTemplate().update("Res_init_allotCXPmt");
	}
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotYWPmt(){
		getSqlSessionTemplate().update("Res_init_allotYWPmt");
	}
	
	/**
	 * 权限初始化 @author:lw.y
	 */
	public void Res_init_allotZHPmt(){
		getSqlSessionTemplate().update("Res_init_allotZHPmt");
	}

}
