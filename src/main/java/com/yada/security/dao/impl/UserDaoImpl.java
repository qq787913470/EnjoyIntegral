package com.yada.security.dao.impl;

import com.yada.security.base.BaseDaoImpl;
import com.yada.security.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl extends BaseDaoImpl<User, String> {

	public int getByUserGrpId(Long userGrpId){
		return this.getSqlSessionTemplate().selectOne("User_getByUserGrpId", userGrpId);
	}

	public User getByLoginName(String loginName){
		return this.getSqlSessionTemplate().selectOne("User_getByLoginName", loginName);
	}
	
	public List<User> selectAll(){
		return this.getSqlSessionTemplate().selectList("User_selectAll");
	}
	
	public User getById1(int i) {
		return getSqlSessionTemplate().selectOne("User_getById1", 1);
	}
	
	/**
	 * 获取主键
	 * @return T_SEQ_P_SHIRO_USER.NEXTVAL
	 * @author longwu.yan
	 */
	public String selectKey(){
		return getSqlSessionTemplate().selectOne("User_selectKey");
	}

	/**
	 * 根据登录名获取用户信息,包括用户分组,用户额外信息
	 * @param loginName
	 * @return User
	 * @author longwu.yan
	 */
	public User findUserAllInfoByLoginName(String loginName){
		return getSqlSessionTemplate().selectOne("User_findUserAllInfoByLoginName", loginName);
	}
	
	/**
	 * 更新密码.
	 * @param user
	 * @author longwu.yan
	 */
	public void updateUserPwd(User user){
		getSqlSessionTemplate().update("User_resetUserPwd", user);
	}
	
	/**
	 * 分配用户分组
	 * @param user
	 * @author longwu.yan
	 */
	public void assignUserGrp(User user){
		getSqlSessionTemplate().update("User_assignUserGrp", user);
	}
	
}
