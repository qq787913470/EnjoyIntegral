package com.yada.security.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.UserDaoImpl;
import com.yada.security.dao.impl.UserExtDaoImpl;
import com.yada.security.model.OperationLoginfoExt;
import com.yada.security.model.User;
import com.yada.security.model.UserExt;
import com.yada.security.shiro.util.md5keyBean;

@Service
public class UserManager extends BaseService<User, String> {
	
	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private UserExtDaoImpl userExtDaoImpl;

	@Override
	protected BaseDao<User, String> getBaseDao() {
		return userDaoImpl;
	}
	
	//根据分组ID查询
	public int getByUserGrpId(Long userGrpId){
		return userDaoImpl.getByUserGrpId(userGrpId);
	}
	
	// 根据唯一键查询
	public User getByLoginName(java.lang.String loginName) {
		return userDaoImpl.getByLoginName(loginName);
	}

	// 查询全部数据
	public List<User> selectAll() {
		return userDaoImpl.selectAll();
	}

	@Override
	public void insert(User entity) {
		userDaoImpl.insert(entity);
	}
	
	public User getById1(){
		return userDaoImpl.getById1(1);
	}
	
	/**
	 * 保存用户及用户额外信息
	 * @param user
	 * @author longwu.yan
	 */
	public void saveUserAndExt(User user){
		
		//主键
		String userId = userDaoImpl.selectKey();
		
		//无设置密码,默认密码:111111,加密
		if("".equals(user.getPwd())){
			user.setPwd("111111");
		}
		md5keyBean md5 = new md5keyBean();
		user.setPwd(md5.getkeyBeanofStr(user.getPwd()));
		
		//保存用户信息
		user.setUserId(userId);
		super.insert(user);
		
		//保存用户额外信息
		UserExt userExt = addUserExtInfo(user);
		//保存
		userExtDaoImpl.insert(userExt);
		
		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		extList.add(getOperationLoginfoExt("insert", "", user.toAuditLogString(), "T_P_SHIRO_USER,T_P_SHIRO_USER_EXT", "用户信息管理-新增用户信息及额外信息"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户信息管理-新增用户信息及额外信息", ""), extList);
	}
	
	/**
	 * 添加用户额外信息.包括用户ID,创建时间,创建用户
	 * @param user
	 * @return UserExt
	 */
	private UserExt addUserExtInfo(User user){
		UserExt userExt = user.getUserExt();
		
		//用户主键
		userExt.setUserId(user.getUserId());
		
		//创建时间
		String createDateTime = getCurrTime();
		userExt.setCreateDateTime(createDateTime);
		
		//创建用户
		String createUserId = getCurUser().getUserId();
		userExt.setCreateUserId(createUserId);
		
		return userExt;
	}
	
	/**
	 * 根据登录名获取用户信息,包括用户分组,用户额外信息
	 * @param loginName
	 * @return User
	 * @author longwu.yan
	 */
	public User findUserAllInfoByLoginName(String loginName){
		
		//查找
		return userDaoImpl.findUserAllInfoByLoginName(loginName);
	}
	
	/**
	 * 验证用户名是否可用
	 * @param loginName
	 * @return true:可用,false:其他
	 * @author longwu.yan
	 */
	public boolean isLoginNameExist(String loginName){
		
		//null:不存在;其他:存在
		return userDaoImpl.getByLoginName(loginName) == null;
	}
	
	/**
	 * 重置密码.默认密码:111111,加密.记录更新时间
	 * @param user
	 * @author longwu.yan
	 */
	public void resetUserPwd(User user){
		
		//加密默认密码:111111
		md5keyBean md5 = new md5keyBean();
		user.setPwd(md5.getkeyBeanofStr("111111"));
		
		//重置
		userDaoImpl.updateUserPwd(user);
		
		//记录更新时间
		String lastChgPwdDateTime = getCurrTime();
		UserExt userExt = new UserExt(user.getUserId());
		userExt.setLastChgPwdDateTime(lastChgPwdDateTime);
		
		//更新
		userExtDaoImpl.updateUserExtOnPwdChg(userExt);
		
		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		User oUser = getById(user.getUserId());
		String ovalues = "LOGIN_NAME=" + oUser.getLoginName() + ",PWD=" + oUser.getPwd();
		String cvalues = "LOGIN_NAME=" + oUser.getLoginName() + ",PWD=" + user.getPwd();
		extList.add(getOperationLoginfoExt("update", ovalues, cvalues, "T_P_SHIRO_USER", "用户信息管理-用户信息重置密码,默认密码:111111"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户信息管理-用户信息重置密码", ""), extList);
	}
	
	/**
	 * 更新密码.加密.记录更新时间
	 * @param user
	 * @author longwu.yan
	 */
	public void updateUserPwd(User user){
		
		//加密
		md5keyBean md5 = new md5keyBean();
		user.setPwd(md5.getkeyBeanofStr(user.getPwd()));
		
		//更新
		userDaoImpl.updateUserPwd(user);
		
		//记录更新时间
		String lastChgPwdDateTime = getCurrTime();
		UserExt userExt = new UserExt(user.getUserId());
		userExt.setLastChgPwdDateTime(lastChgPwdDateTime);
		
		//更新
		userExtDaoImpl.updateUserExtOnPwdChg(userExt);

		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		User oUser = getById(user.getUserId());
		String ovalues = "LOGIN_NAME=" + oUser.getLoginName() + ",PWD=" + oUser.getPwd();
		String cvalues = "LOGIN_NAME=" + oUser.getLoginName() + ",PWD=" + user.getPwd();
		extList.add(getOperationLoginfoExt("update", ovalues, cvalues, "T_P_SHIRO_USER", "用户信息管理-用户信息修改密码"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户信息管理-用户信息修改密码", ""), extList);
	}
	
	/**
	 * 分配用户分组
	 * @param user
	 * @author longwu.yan
	 */
	public void assignUserGrp(User user){
		
		//分配
		userDaoImpl.assignUserGrp(user);
		
		//稽核日志明细(操作,旧值,新值,表名,描述)
		List<OperationLoginfoExt> extList = new ArrayList<OperationLoginfoExt>();
		User oUser = getById(user.getUserId());
		String ovalues = "LOGIN_NAME=" + oUser.getLoginName() + ",USER_GRP_ID=" + oUser.getUserGrpId();
		String cvalues = "LOGIN_NAME=" + oUser.getLoginName() + ",USER_GRP_ID=" + user.getUserGrpId();
		extList.add(getOperationLoginfoExt("update", ovalues, cvalues, "T_P_SHIRO_USER", "用户信息管理-用户信息分配用户分组"));
		//保存稽核日志(模块名称,方法描述,用户IP)
		auditLog(getOperationLoginfo("UserManager", "用户信息管理-用户信息分配用户分组", ""), extList);
	}
	
	/**
	 * 当前系统时间.
	 * @return 8位日期+6位时间
	 */
	private String getCurrTime(){
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}

	public void assignUserGrpAndOrgId(User user) {
		userDaoImpl.assignUserGrp(user);
		UserExt ue = new UserExt();
		ue.setUserId(user.getUserId());
		ue.setOrgId(user.getUserExt().getOrgId());
		userExtDaoImpl.updateUserOrg(ue);
		
	}
}
