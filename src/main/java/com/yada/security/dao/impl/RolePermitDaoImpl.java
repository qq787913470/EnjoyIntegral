package com.yada.security.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.Res;
import com.yada.security.model.RolePermit;
@Component
public class RolePermitDaoImpl extends BaseDaoImpl<RolePermit, Long> {

	public void deletePermitsRes(Long roleId, String resTypeId) {
		Map map = new HashMap();
		map.put("roleId", roleId);
		map.put("resTypeId", resTypeId);
		getSqlSessionTemplate().delete("deletePermitsRes", map);
	}
	
	public void deletePermitsResTree(Long roleId, String resTypeId, String pmenuResId, String menuResId) {
		Map map = new HashMap();
		map.put("roleId", roleId);
		map.put("resTypeId", resTypeId);
		map.put("pmenuResId", pmenuResId);
		map.put("menuResId", menuResId);
		getSqlSessionTemplate().delete("deletePermitsResTree", map);
	}

	public void res2RoleInsert(Object[] permits, Object[] roles) {
		Map map = new HashMap();
		map.put("permits", permits);
		map.put("roles", roles);
		getSqlSessionTemplate().insert("res2RoleInsert", map);
	}

	public void deleteRolesByPermit(String permitId) {
		
		getSqlSessionTemplate().delete("deleteRolesByPermit", permitId);
		
	}
	
	public void permit2Role(String permitId, Object[] roles, String ordered) {
		
		Map map = new HashMap();
		map.put("permitId", permitId);
		map.put("roles", roles);
		map.put("ordered", ordered);
		getSqlSessionTemplate().insert("permit2Role", map);
		
	}

	public void resources2Role_insert(Long roleId, Object[] permits) {
		
		Map map = new HashMap();
		map.put("roleId", roleId);
		map.put("permits", permits);
		getSqlSessionTemplate().insert("resources2Role", map);
		
	}
	
	public Long findSortByRes(Res res) {
		return this.getSqlSessionTemplate().selectOne("findSortByRes",res.getMenuResId());
	}
	
	/**
	 * @Decreption 删除资源已分配权限
	 * @param menuResId,资源ID
	 */
	public void deletePermitsByResId(Long menuResId) {
		getSqlSessionTemplate().delete("deletePermitsByResId", menuResId);
	}

}
