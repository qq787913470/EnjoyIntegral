package com.yada.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.UserGrp;

@Component
public class UserGrpDaoImpl extends BaseDaoImpl<UserGrp, String> {
	
	/**
	 * 获取主键
	 * @author longwu.yan
	 * @return T_SEQ_P_SHIRO_USER_GRP.nextval
	 */
	public String selectKey(){
		return getSqlSessionTemplate().selectOne("UserGrp_selectKey");
	}
	
	/**
	 * 根据机构查找上级机构可继承(IHT_FLAG = 0)的用户分组及本机构下所有用户分组
	 * @param String orgId
	 * @return List<UserGrp>
	 */
	public List<UserGrp> findGrpByOrg(String orgId){
		return getSqlSessionTemplate().selectList("UserGrp_findGrpByOrg", orgId);
	}
	public List<UserGrp> findAll(){
		return getSqlSessionTemplate().selectList("UserGrp_findGrpByAll");
	}
	
}
