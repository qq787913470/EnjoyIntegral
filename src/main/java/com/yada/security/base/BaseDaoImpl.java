package com.yada.security.base;

import java.util.*;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yada.common.dict.util.DictUtil;
import com.yada.mybatis.paging.Page;
import com.yada.mybatis.paging.PageUtils;

@Repository
public class BaseDaoImpl<Entity, Id> implements BaseDao<Entity, Id> {

	
	private SqlSessionFactory sessionFactory;
	private SqlSessionTemplate sqlSessionTemplate;
	//DAO后缀，为了获取Mybatis MAPPER nameSapce
	private static final String DAOSUFFIX = "DaoImpl";
	
	
	public void insert(Entity entity) {
		sqlSessionTemplate.insert(getInsertMapperID(),entity);
	}

	public void update(Entity entity) {
		sqlSessionTemplate.update(getUpdateMapperID(), entity);
	}

	public void delete(Id id) {
		sqlSessionTemplate.delete(getDeleteMapperID(), id);
	}
	//只做dblink插入使用
	public void insert_DBLink(Entity entity) {
		sqlSessionTemplate.insert(getInsertToDBLinkMapperID(),entity);
	}
	//只做dblink更新使用
	public void update_DBLink(Entity entity) {
		sqlSessionTemplate.update(getUpdateToDBLinkMapperID(), entity);
	}
	//只做dblink删除使用
	public void delete_DBLink(Id id) {
		sqlSessionTemplate.delete(getDeleteToDBLinkMapperID(), id);
	}

	public Entity getById(Id id) {
		return  sqlSessionTemplate.selectOne(getSelectOneMapperId(), id);
	}

	public int findCountByWhere(Entity entity) {
		return sqlSessionTemplate.selectOne(getCountMapperID(), entity);
	}

	public List<Entity> findListByWhere(Entity entity) {
		return sqlSessionTemplate.selectList(getListMapperID(), entity);
	}
	
	/**
	 * 默认使用"_findListByWhere"进行分页查询。
	 */
	public Page queryPage(BaseQuery query){

		/*int totalCount = getSqlSessionTemplate().selectOne(getCountMapperID(), query);
		
		Page page = new Page(query.getPageNumber(),query.getPageSize(), totalCount);
		
		int offset = PageUtils.getFirstResult(query.getPageNumber(), query.getPageSize());
		
		RowBounds rowBounds = new RowBounds(offset, query.getPageSize());
		
		List result = getSqlSessionTemplate().selectList(getListMapperID(), query,rowBounds);
		
		page.setResult(result);
		
		return page;*/
		
		return queryPage(query, null);
	}
	
	/**
	 * 提供分页查询的基础方法，mapperId对应的查询count方法为 mapperId+"_count".
	 * 
	 * @param query	查询条件
	 * @param mapperId XML文件中的mapperId
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	public Page queryPage(BaseQuery query,String mapperId){
		
		int totalCount = getSqlSessionTemplate().selectOne(mapperId == null ? getCountMapperID():getCountMapperID(mapperId), query);
		
		Page page = new Page(query.getPageNumber(),query.getPageSize(), totalCount);
		
		int offset = PageUtils.getFirstResult(query.getPageNumber(), query.getPageSize());
		
		RowBounds rowBounds = new RowBounds(offset, query.getPageSize());
		
        List result = new ArrayList();
		
		if(totalCount != 0){
			result = getSqlSessionTemplate().selectList(mapperId == null ? getListMapperID() : mapperId, query,rowBounds);
		}
		
		page.setResult(result);
		
		return page;
	}

	//sqlSessionTemplate无init方法spring无法通过自动注入进行赋值，在这里进行初始化
	@Autowired
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.sqlSessionTemplate =new SqlSessionTemplate(sessionFactory);
	}
	
	public SqlSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	//获取每个不同的DAO访问的NameSapce
	public String getIbatisMapperNamesapce(){
		String clazzName = getClass().getSimpleName() ;
		if(!clazzName.contains(DAOSUFFIX)){
			throw new RuntimeException("DAO命名不规范");
		}
		return clazzName.substring(0, clazzName.length()-DAOSUFFIX.length());
	}
	
	//myBatis MAPPER标识
	private String getInsertMapperID() {
		//System.out.println(getIbatisMapperNamesapce()+"_insert");
		praceDict(getIbatisMapperNamesapce());
		return getIbatisMapperNamesapce()+"_insert";
		
	}
	private String getUpdateMapperID() {
		praceDict(getIbatisMapperNamesapce());
		return getIbatisMapperNamesapce()+"_update";
	}
	private String getDeleteMapperID() {
		praceDict(getIbatisMapperNamesapce());
		//System.out.println(getIbatisMapperNamesapce()+"_delete");
		return getIbatisMapperNamesapce()+"_delete";
	}
	//myBatis MAPPER标识
	private String getInsertToDBLinkMapperID() {
		//System.out.println(getIbatisMapperNamesapce()+"_insert");
		praceDict(getIbatisMapperNamesapce());
		return getIbatisMapperNamesapce()+"_insert_toDBLink";
		
	}
	private String getUpdateToDBLinkMapperID() {
		praceDict(getIbatisMapperNamesapce());
		return getIbatisMapperNamesapce()+"_update_toDBLink";
	}
	private String getDeleteToDBLinkMapperID() {
		praceDict(getIbatisMapperNamesapce());
		//System.out.println(getIbatisMapperNamesapce()+"_delete");
		return getIbatisMapperNamesapce()+"_delete_toDBLink";
	}
	
	private String getCountMapperID() {
		//System.out.println(getIbatisMapperNamesapce()+"_findCountByWhere");
		return getIbatisMapperNamesapce()+"_findCountByWhere";
	}
	
	private String getCountMapperID(String mapperId) {
		if(mapperId != null){
			return mapperId+"_count";
		}
		return getCountMapperID();
	}
	
	private String getListMapperID() {
		//System.out.println(getIbatisMapperNamesapce()+"_findListByWhere");
		return getIbatisMapperNamesapce()+"_findListByWhere";
	}
	private String getSelectOneMapperId(){
		//System.out.println(getIbatisMapperNamesapce() + "_getById");
		return getIbatisMapperNamesapce() + "_getById";
	}
	
	//动态字典的统一处理
	public void praceDict(String rs){
		for(String str : DictUtil.getDynamicDict()){
			if(rs.equals(str)){
				DictUtil.remove(str);
			}
		}
	}

	
}
