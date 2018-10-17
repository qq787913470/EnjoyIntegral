package com.yada.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.model.OperationLoginfoExt;
import com.yada.security.model.Org;
import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.OrgDaoImpl;
import com.yada.security.model.TreeObject;

@Service
public class OrgManager extends BaseService<Org, String>{

	@Autowired
	private OrgDaoImpl orgDao;

	@Override
	protected BaseDao<Org, String> getBaseDao() {
		// TODO Auto-generated method stub
		return orgDao;
	}
	
	/**
	 * 机构新增
	 * 补充机构号和机构级别
	 */
	public void insert(Org org){
		//上级机构
		Org Porg = getById(org.getPorgId());
		//机构号
		//String orgId = maxSubOrgIdByPid(org.getPorgId());
		//org.setOrgId(orgId);
		//机构级别
		org.setOrgLev(Porg.getOrgLev() + 1);
		org.setStatus("1");
		org.setOnlineFlag("1");
		//保存机构
		super.insert(org);
		
		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		extList.add(getOperationLoginfoExt("insert", "", org.toAuditLogString(), "T_B_ORG", "机构信息管理-机构信息新增"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("OrgManager", "机构信息管理-机构信息新增", ""), extList);
	}
	
	/**
	 *	机构修改
	 *	@author longwu.yan
	 *	@param  
	 */
	public void update(Org org){
		
		//更新前机构信息
		Org oOrg = getById(org.getOrgId());
		String oldStr = oOrg.toAuditLogString();
		oOrg.setStatus(org.getStatus());
		
		super.update(oOrg);
		
		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		extList.add(getOperationLoginfoExt("update", oldStr, org.toAuditLogString(), "T_B_ORG", "机构信息管理-机构信息修改"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("OrgManager", "机构信息管理-机构信息修改", ""), extList);
		
	}
	
	/**
	 *	机构修改
	 *	@author longwu.yan
	 *	@param  
	 */
	public void delete(String orgId){
		
		super.delete(orgId);
		
		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		extList.add(getOperationLoginfoExt("delete", "ORG_ID=" + orgId, "", "T_B_ORG", "机构信息管理-机构信息删除"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("OrgManager", "机构信息管理-机构信息删除", ""), extList);
	}
	
	
	/**
	 * 获取当前登录用户机构及以下机构(省级)
	 * @param String orgId
	 * @return List<Org>
	 * @author longwu.yan
	 */
	public List<Org> findLowerPrvcOrg(){
		//当前登录用户机构
		String orgId = getCurUser().getUserExt().getOrgId();
		
		return orgDao.findLowerPrvcOrg(orgId);
	}
	
	public List<Org> getNextLevOrgListByOrgId(Map<String, String> map){
		return orgDao.getNextLevOrgListByOrgId(map);
	}
	
	/**
	 * 根据上级机构查找直属下级所有机构,查询结果是当前登录用户机构及以下,并直接封装到TreeObject.机构树使用
	 * @author longwu.yan
	 * @param parentId
	 * @return List<TreeObject>
	 */
	public List<TreeObject> findSubOrgsByPorgId(String pTreeId){
		
		return orgDao.findSubOrgsByPorgId(pTreeId);
	}
	
	/**
	 * 获取当前新增机构号.
	 * 数据库返回空或直属最大机构号+1,并省略前面的0.
	 * @author longwu.yan
	 * @param 上级机构号
	 */
	public String maxSubOrgIdByPid(String porgId){
		//直属下级最大机构号 + 1
		String maxSubOrgId = orgDao.maxSubOrgIdByPid(porgId);
		//机构号
		String orgId = "";
		if(maxSubOrgId == null){//无下级机构
			orgId = porgId + "001";
		}else{
			//机构前缀,最多五个0
			String prefix_zore = "000000";
			//补充0.例:总行下新增机构,下级机构最大为002,则补充5个0.
			orgId = prefix_zore.substring(0, porgId.length() + 3 - maxSubOrgId.toString().length()) + maxSubOrgId;
		}
		return orgId;
	}
	

	/**
	 * 根据机构号查询EACQ机构号
	 * TX
	 * return EacqOrgId
	 */
	public String findEacqOrgByOrgId(String orgId){
		
		return orgDao.findEacqOrgByOrgId(orgId);
	}
	public List<Org> findLowerPrvcOrgIDandName(String orgId){
		return orgDao.findLowerPrvcOrgIDandName(orgId);
	}
	public List<String> findLowerPrvcOrgID(String orgId){
		return orgDao.findLowerPrvcOrgID(orgId);
	}

	public List<Org> findLowerPrvcOrg_tow(String orgId){
		return orgDao.findLowerPrvcOrg_tow(orgId);
	}

	public List<Org> findZbankList(String orgId) {
		//当前登录用户机构
				Org org = orgDao.getById(orgId);
				int orgLev = org.getOrgLev();
				
				return orgDao.findZbankList(orgId,(orgLev+1)+"");
	}
}
