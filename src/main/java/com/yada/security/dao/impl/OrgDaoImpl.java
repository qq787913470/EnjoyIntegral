package com.yada.security.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.Org;
import com.yada.security.model.TreeObject;

@Component
public class OrgDaoImpl extends BaseDaoImpl<Org, String> {
	
	/**
	 * 获取当前登录用户机构及以下机构(省级)
	 * @param String orgId
	 * @return List<Org>
	 * @author longwu.yan
	 */
	public List<Org> findLowerPrvcOrg(String orgId){
		return getSqlSessionTemplate().selectList("Org_findLowerPrvcOrg", orgId);
	}



	public List<Org> findLowerPrvcOrgIDandName(String orgId){
		return getSqlSessionTemplate().selectList("Org_IDANDNAME_findLowerPrvcOrg", orgId);
	}
	public List<String> findLowerPrvcOrgID(String orgId){
		return getSqlSessionTemplate().selectList("Org_ID_findLowerPrvcOrg", orgId);
	}

	public List<Org> findLowerPrvcOrg_tow(String orgId){
		return getSqlSessionTemplate().selectList("Org_tow_findLowerPrvcOrg",orgId);
	}

	/**
	 * 获取商户可分配的网点机构号
	 * @param String orgId
	 * @return List<Org>
	 * @author QQ
	 */
	public List<Org> findMerBranchOrg(String merId){
		return getSqlSessionTemplate().selectList("Org_findMerBranchOrg", merId);
	}
	
	public List<Org> getNextLevOrgListByOrgId(Map<String, String> map){
		return getSqlSessionTemplate().selectList("Org_getNextLevOrgListByOrgId", map);
	}
	
	/**
	 * 根据上级机构查找直属下级所有机构,查询结果是当前登录用户机构及以下,并直接封装到TreeObject.机构树使用
	 * @author longwu.yan
	 * @param 当前登录用户机构-cOrgId,上级机构-parentId
	 * @return List<TreeObject>
	 */
	public List<TreeObject> findSubOrgsByPorgId(String pTreeId){
		
		return getSqlSessionTemplate().selectList("Org_findSubOrgsByPorgId", pTreeId);
	}
	
	/**
	 * 直属下级最大机构号 + 1
	 * @author longwu.yan
	 * @param 上级机构号
	 */
	public String maxSubOrgIdByPid(String orgId){
		return getSqlSessionTemplate().selectOne("Org_maxSubOrgIdByPid", orgId);
	}
	
	/**
	 * 根据机构号查询EACQ机构号
	 * TX
	 * return EacqOrgId
	 */
	public String findEacqOrgByOrgId(String orgId){
		return getSqlSessionTemplate().selectOne("Org_findEacqOrgByOrgId", orgId);
	}

	public List<Org> findZbankList(String orgId, String orgLev) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("orgId", orgId);
		map.put("orgLev", orgLev);
		return getSqlSessionTemplate().selectList("Org_findZbankList", map);
	}
}
