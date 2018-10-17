package com.yada.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yada.security.base.BaseDao;
import com.yada.security.base.BaseService;
import com.yada.security.dao.impl.PermitDaoImpl;
import com.yada.security.dao.impl.ResDaoImpl;
import com.yada.security.dao.impl.ResTypeDaoImpl;
import com.yada.security.dao.impl.RestypeActionDaoImpl;
import com.yada.security.dao.impl.RolePermitDaoImpl;
import com.yada.security.model.Permit;
import com.yada.security.model.Res;
import com.yada.security.model.ResType;
import com.yada.security.model.RestypeAction;
import com.yada.security.reflex.ClassUtils;
import com.yada.security.reflex.InitializedData;

@Service
public class ResManager extends BaseService<Res, Long> {

	@Autowired
	private ResDaoImpl resDao;

	@Autowired
	private ResTypeDaoImpl resTypeDao;
	
	@Autowired
	private RestypeActionDaoImpl restypeActionDaoImpl;
	
	@Autowired
	private PermitDaoImpl permitDaoImpl;
	
	@Autowired
	private RolePermitDaoImpl rolePermitDaoImpl;

	protected BaseDao<Res, Long> getBaseDao() {
		return resDao;
	}

	//删除资源
	public void deleteRes(Long menuResId){
		//删除资源已分配权限
		rolePermitDaoImpl.deletePermitsByResId(menuResId);
		permitDaoImpl.deleteByResId(menuResId);
		resDao.delete(menuResId);
	}
	
	// 根据用户Id查询菜单
	public List<Res> findMenusByUserId(Long userId) {
		List<Res> newRes = new ArrayList<Res>();
		List<Res> oldRes = resDao.findMenusByUserId(userId);
		List<Res> tempRes = null;
		int i = 0;
		for (Res r : oldRes) {
			if (r.getActionName() == null
					|| "".equals(r.getActionName().trim())) {
				newRes.add(r);
				tempRes = new ArrayList<Res>();
				i++;
			} else {
				tempRes.add(r);
			}
			if (i - 1 >= 0) {
				newRes.get(i - 1).setChildren(tempRes);
			}
		}
		return newRes;
	}

	// 查询菜单类型
	public List<ResType> findMenuType() {
		return resTypeDao.findListByWhere(new ResType());
	}

	// 查询菜单类型
	public List<Res> getPMenuRes(Long menuId) {
		return resDao.getPMenuRes(menuId);
	}

	// public List<Res> findPermitsResList(Long roleId) {
	// return resDao.findPermitsResList(roleId);
	// }
	// 根据用户名查询菜单
	public List<Res> findMenusByLoginName(String loginName) {

		List<Res> newRes = new ArrayList<Res>();
		List<Res> oldRes = resDao.findMenusByLoginName(loginName);
		List<Res> tempRes = null;
		boolean isShow=true;//标记是否可见
		int i = 0;
		for(Res r : oldRes){
			if(r.getMenuResId()==508L)
				System.out.println("---------------"+r.getMenuName());
			if(r.getPmenuResId() ==0){//判断是否是一级菜单
				List<Permit> permits = r.getPermits();
				for (Permit permit : permits) {
					 if(permit.getActionId()==2L)
						 isShow=false;
					    continue;//不可见
				}
				if(isShow){
					newRes.add(r);
					i++;
				}
				isShow=true;
				tempRes = new ArrayList<Res>();
				
			}
			else{//二级菜单
				List<Permit> permits = r.getPermits();
				if(permits.size()<=1){//不可见
					continue;
				}
				else{
					for (Permit permit : permits) {
						
						 if(permit.getActionId()==2L){
							 r.setIsExecution("3");//初始化为3-->不可见
							 isShow=false;
							 continue;//不可见
						 }
						 else  if(permit.getActionId()==4L)
					    	 r.setIsExecution("2");//2-->不可执行
						 else if(r.getIsExecution()==null)
							 r.setIsExecution("1");//初始化为1-->可执行
					}
				}
				if(isShow)
				tempRes.add(r);
				isShow=true;
			}
			if(i-1>=0) {
				if(newRes.get(i-1)!=null){
					if(tempRes.size()>0){
						Collections.sort(tempRes, new Res());
						newRes.get(i-1).setChildren(tempRes);
					}
				}
			}
		}
		Collections.sort(newRes, new Res());
		return newRes;
	}

	public List<Res> findPermitsResList(Long roleId, String resTypeId,
			String pmenuResId, String menuResId) {
		// TODO Auto-generated method stub
		return resDao.findPermitsResList(roleId, resTypeId, pmenuResId,menuResId);
	}

	public void initRes() {
		// 原始数据
		RestypeAction restypeAction = new RestypeAction();
		restypeAction.setTypeId(2);

		Long pMenuResId = 0l;

		List<Class> clazzList = ClassUtils.getClasses("com.yada.security.conteoller");

		int index = 0;

		// clazzList永远不为null
		for (Class clazz : clazzList) {
			List<String> urlList = ClassUtils.clazz2URL(clazz);
			// 没有标识@Controller的类的urlList==null
			if (urlList == null) {
				continue;
			}

			// 主键值
			Long pk = null;
			Long id = null;
			// 循环生成RES，记库
			for (int i = 0; i < urlList.size(); i++) {
				Res res = new Res();
				res.setMenuName(clazz.getSimpleName());
				res.setActionName(urlList.get(i));
				res.setTypeId(restypeAction.getTypeId());
				res.setOrderId(Long.parseLong(i + ""));
				res.setDsc("勿动-程序inser");
				// 处理RES的子父关系
				if (i == 0) {
					pk = getPKValue();
					res.setMenuResId(pk);
					res.setPmenuResId(pMenuResId);
				} else {
					id = getPKValue();
					res.setMenuResId(id);
					res.setPmenuResId(pk);
				}
				insertWithPK(res);
				// 生成许可
				List<RestypeAction> RestypeActions = restypeActionDaoImpl
						.findListByWhere(restypeAction);

				if (RestypeActions != null) {
					for (int y = 0; y < RestypeActions.size(); y++) {
						Permit permit = new Permit();
						permit.setActionId(RestypeActions.get(y).getActionId());
						permit.setMenuResId(id == null ? pk : id);
						permit.setTypeId(restypeAction.getTypeId());
						permit.setPermitOrder(index++);
						permitDaoImpl.insert(permit);
					}
				}
			}
		}
	}
	
	public void initRes2(){
		String packageName = "com.yada.hhap2.controller";
		InitializedData initData = InitializedData.getInstance();
		List<Class> clazzList = initData.getClasses(packageName);
		List<Res> urlList = initData.clazz2Res(clazzList);
		
		for (int i = 0; i < urlList.size(); i++) {
			Long menuResId = getPKValue();
			Res res = urlList.get(i);
			res.setMenuResId(menuResId);
			res.setDsc("勿动-程序insert");
//			System.out.println(res.toConsoleString());
			resDao.insertWithPK(res);
			initShiroPermit(res);
			List<Res> childrenList =  res.getChildren();
			for (int j = 0; j < childrenList.size(); j++) {
				Res childrenRes = childrenList.get(j);
				childrenRes.setMenuResId(getPKValue());
				childrenRes.setPmenuResId(menuResId);
				childrenRes.setDsc("勿动-程序insert");
				resDao.insertWithPK(childrenRes);
				initShiroPermit(childrenRes);
			}
		}
	} 
	
	public void initShiroPermit(Res res){
		RestypeAction restypeAction = new RestypeAction();
		restypeAction.setTypeId(res.getTypeId());
		// 生成许可
		List<RestypeAction> restypeActions = restypeActionDaoImpl.findListByWhere(restypeAction);
		for (int i = 0; i < restypeActions.size(); i++) {
			Permit permit = new Permit();
			permit.setActionId(restypeActions.get(i).getActionId());
			permit.setMenuResId(res.getMenuResId());
			permit.setTypeId(1);
			permit.setPermitOrder(i);
			permitDaoImpl.insert(permit);
		}
	}

	public List<Res> getTreeData(String pTreeId,String typeId) {
		List<Res> list = new ArrayList<Res>();
		if(pTreeId==null||pTreeId.equals(""))
		{
			pTreeId="0";
		}
		list=resDao.findPMenuIdbyRes(pTreeId,typeId);
		
		
		
		return list;
	}

	// 获取当前主键的值（SEQ）
	public Long getPKValue() {
		return resDao.getPKValue();
	}

	// 带主键的insert
	public void insertWithPK(Res res) {
		resDao.insertWithPK(res);
	}

	public List<Res> findMenuResList() {
		// TODO Auto-generated method stub
		return resDao.findMenuResList();
	}

	public List<Res> findUrlResList() {
		// TODO Auto-generated method stub
		return resDao.findUrlResList();
	}

	public List<Res> findResPermits(Res res) {
		// TODO Auto-generated method stub
		return resDao.findResPermits(res);
	}
	
	//判断是否是叶子节点
	public Long findResIsLeaf(Long menuResId) {
		return resDao.findResIsLeaf(menuResId);
	}
	
	public List<Res> findListByPmenu(Long pmenuResId, Long typeId) {
		return resDao.findListByPmenu(pmenuResId, typeId);
	}
	
	//程序插入-ZQD
	public void insertWithPermit(Res res){
		resDao.insertWithPK(res);
		initShiroPermit(res);
	}
	
	// 带主键的insert
	public void insertAll(Res res) {
		if (res.getMenuResId() == null) {
			Long menuResId = getPKValue();
			res.setMenuResId(menuResId);
			res.setOrderId(findOrderMax(res.getPmenuResId()) + 1);
			resDao.insertWithPK(res);
			// permit新增
			RestypeAction ra = new RestypeAction(res.getTypeId(), null);
			List<RestypeAction> restypeActions = restypeActionDaoImpl.findListByWhere(ra);
			for (RestypeAction ras : restypeActions) {
				Permit permit = new Permit();
				permit.setMenuResId(menuResId);
				permit.setTypeId(res.getTypeId());
				permit.setActionId(ras.getActionId());
				permitDaoImpl.insert(permit);
			}
		} else {
			if (res.getPmenuResId() == null) {
				res.setPmenuResId(0L);
			}
			resDao.update(res);
		}
	}
	
	//查找order_id最大值
	public Long findOrderMax(Long menuResId) {
		return resDao.findOrderMax(menuResId);
	}
	
	//修改order_id值
	public Long updateOrderId(Long menuResId, Long orderId) {
		return resDao.updateOrderId(menuResId, orderId);
	}
	
	//获得排序数据
	public List<Res> findByOrder(Long pmenuResId, Long typeId) {
		return resDao.findByOrder(pmenuResId, typeId);
	}
	
	/**
	 * 权限初始化角色权限分配
	 * @author lw.y
	 */
	public void res_init_allotPmt(){
		
		resDao.Res_init_updLikeFlagAct();
		resDao.Res_init_allotAdminPmt();
		resDao.Res_init_allotCXPmt();
		resDao.Res_init_allotIcBBPmt();
		resDao.Res_init_allotIcCSPmt();
		resDao.Res_init_allotIcCXPmt();
		resDao.Res_init_allotIcSHPmt();
		resDao.Res_init_allotMerGGPmt();
		resDao.Res_init_allotMerGLPmt();
		resDao.Res_init_allotMerTHPmt();
		resDao.Res_init_allotMerUDPmt();
		resDao.Res_init_allotYWPmt();
		resDao.Res_init_allotZHPmt();
		
	}
}
