package com.yada.security.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.yada.mybatis.paging.Page;
import com.yada.security.dao.impl.OperationLoginfoDaoImpl;
import com.yada.security.dao.impl.OperationLoginfoExtDaoImpl;
import com.yada.security.model.OperationLoginfo;
import com.yada.security.model.OperationLoginfoExt;
import com.yada.security.model.User;

public abstract class BaseService<Entity, Id> {

	protected abstract BaseDao<Entity, Id> getBaseDao();
	
	@Autowired
	public OperationLoginfoDaoImpl operationLoginfoDaoImpl;
	
	@Autowired
	public OperationLoginfoExtDaoImpl operationLoginfoExtDaoImpl;
	
	private static final String CURUSER = "curUser";
	
	protected OperationLoginfoDaoImpl getOperationLoginfoDao(){
		return operationLoginfoDaoImpl;
	}
	
	protected OperationLoginfoExtDaoImpl getOperationLoginfoExtDao(){
		return operationLoginfoExtDaoImpl;
	}
	
	public void insert(Entity entity) {
		
		getBaseDao().insert(entity);
	}

	public void update(Entity entity) {
		
		getBaseDao().update(entity);
	}

	public void delete(Id id) {
		getBaseDao().delete(id);
	}

	public Entity getById(Id id) {
		return getBaseDao().getById(id);
	}

	public int findCountByWhere(Entity entity) {
		return getBaseDao().findCountByWhere(entity);
	}

	public List<Entity> findListByWhere(Entity entity) {
		return getBaseDao().findListByWhere(entity);
	}
	
	public Page queryPage(BaseQuery entity) {
		if(entity.isShow()){
			return getBaseDao().queryPage(entity);
		}else{
			return new Page(0, 20, 0);
		}
		
	}
	
	/**
	 * 样例方法。 业务层记日志的样例代码。
	 * @param operationLoginfo 主表的记录。 对应表名T_WEB_P_OPERATION_LOGINFO， 记录了谁在什么时间通过那个方法做了操作。
	 * @param list 扩展表的记录。对应表名T_WEB_P_OPERATION_LOGINFO_EXT ,记录具体的操作情况。
	 * 
	 * TODO 此方法应该放到每一个需要记录日志的Manager中。
	 */
	public void auditLog(OperationLoginfo operationLoginfo , List<OperationLoginfoExt> list){
		String merId = getCurUser().getUserExt().getOrgId();
		String loginName = getCurUser().getLoginName();
		String orgId = getCurUser().getUserExt().getOrgId();
		String ip = getCurUser().getUserExt().getLastLoginIpAddr();
		//获取SEQ的值
		String id = (String) getOperationLoginfoDao().getSqlSessionTemplate().selectOne("OperationLoginfo_selectKey",null);
		//ID赋值
		operationLoginfo.setId(id);
		//当前登陆用户商户号
		operationLoginfo.setMerNo(merId);
		//当前登陆用户名称
		operationLoginfo.setUserName(loginName);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
		String operDate = date.format(c.getTime());
		//当前操作时间
		operationLoginfo.setOperatorTime(operDate);
		//当前用户机构
		operationLoginfo.setOrgId(orgId);
		//行内系统
		operationLoginfo.setSysflag("0");
		//操作IP
		operationLoginfo.setIp(ip);
		//保存
		getOperationLoginfoDao().insert(operationLoginfo);
		//批量保存EXT表。
		if(list!=null){
			for(OperationLoginfoExt temp : list){
				//外键赋值
				temp.setLoginfoId(id);
				//批量保存
				//TODO OperationLoginfoExtDao应该通过Spring自动注入进去。
				getOperationLoginfoExtDao().insert(temp);
			}
		}
	}
	
	public OperationLoginfo getOperationLoginfo(String moduleName,String methodDescription,String ip){
		return new OperationLoginfo(moduleName,methodDescription,ip);
	}
	
	public OperationLoginfoExt getOperationLoginfoExt(String operation, String ovalues,
			String cvalues, String tableName,String dec){
		return new OperationLoginfoExt(operation,ovalues,cvalues,tableName,dec);
	}
	
	protected User getCurUser(){
		Subject subject = SecurityUtils.getSubject();
		User user= (User) subject.getSession().getAttribute(CURUSER);
		return user;
	}
}
