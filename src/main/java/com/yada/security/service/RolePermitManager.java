package com.yada.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.RolePermitDaoImpl;
import com.yada.security.model.Permit;
import com.yada.security.model.Res;
import com.yada.security.model.Role;
import com.yada.security.model.RolePermit;

@Service
public class RolePermitManager extends BaseService<RolePermit, Long>{
	@Autowired
	private RolePermitDaoImpl rolePermitDao;
	@Override
	protected BaseDao<RolePermit, Long> getBaseDao() {
		return rolePermitDao;
	}
	public void deletePermitsRes(Long roleId, String resTypeId) {
		// TODO Auto-generated method stub
		rolePermitDao.deletePermitsRes(roleId,resTypeId);
	}
	public void deletePermitsResTree(Long roleId, String resTypeId, String pmenuResId, String menuResId) {
		rolePermitDao.deletePermitsResTree(roleId, resTypeId, pmenuResId, menuResId);
	}
	public void res2RoleInsert(Object[] permits, Object[] roles) {
		// TODO Auto-generated method stub
		rolePermitDao.res2RoleInsert(permits,roles);
	}
	public void deleteRolesByPermit(String permitId){
		// TODO Auto-generated method stub
		rolePermitDao.deleteRolesByPermit(permitId);
	}
	
	/**
	 * @Description 遍历资源许可,分配某一许可给角色列表
	 * @author lw.y
	 * @param res
	 */
	public void allocate_ajax(Res res){
		List<Permit> permits = res.getPermits();
		if (permits != null) {
			for (Permit permit : permits) {
				permit2Role(permit.getPermitId().toString(), permit.getRoles().toArray(), res.getOrdered());
			}
		}
	}
	
	/**
	 * @Description 删除许可已分配情况,保存许可新分配情况
	 * @author lw.y
	 * @param permitId
	 * @param roles
	 * @param ordered
	 */
	public void permit2Role(String permitId, Object[] roles, String ordered) {
		// TODO Auto-generated method stub
		deleteRolesByPermit(permitId);
		if(roles.length > 0){
			rolePermitDao.permit2Role(permitId, roles, ordered);
		}
		
	}
	
	//分配角色批量权限
	public void resources2Role_insert(Long roleId, List<Permit> permits) {
		// TODO Auto-generated method stub
		rolePermitDao.resources2Role_insert(roleId, permits.toArray());
	}
	
	//获取正/序值
	public Long findSortByRes(Res res){
		return rolePermitDao.findSortByRes(res);
	}
	
	//角色分配资源,先删除,后增加
	public void insertPermit(HttpServletRequest request, Role role, 
			String resTypeId, String pmenuResId, String menuResId, int per) {
		
		rolePermitDao.deletePermitsResTree(role.getRoleId(), resTypeId,	pmenuResId, menuResId);
		RolePermit rolePermit = new RolePermit();
		rolePermit.setRoleId(role.getRoleId());
		if (Integer.parseInt(resTypeId) == 2) {
			if (role.getPermits().size() > 0) {
				for (Permit permit : role.getPermits()) {
					rolePermit.setPermitId(permit.getPermitId());
					rolePermitDao.insert(rolePermit);
				}
			}
		} else if (Integer.parseInt(resTypeId) == 1) {
			for (int i = 1; i <= per; i++) {
				if (null != request.getParameter("permit" + i)) {
					// System.out.println(request.getParameter("permit"+i));
					rolePermit.setPermitId(Long.parseLong(request.getParameter("permit" + i)));
					rolePermitDao.insert(rolePermit);
				}
			}
		}
		
	}

}
