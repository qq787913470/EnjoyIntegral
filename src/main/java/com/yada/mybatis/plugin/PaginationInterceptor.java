package com.yada.mybatis.plugin;


import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;

import com.yada.mybatis.dialect.Dialect;



@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor implements Interceptor{

	private final static String DIALECTCLASS = "dialectClass";
	private Dialect dialect;
	
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);
		RowBounds rowBounds = (RowBounds)metaStatementHandler.getValue("delegate.rowBounds");
		if(rowBounds == null || rowBounds == RowBounds.DEFAULT){
			return invocation.proceed();
		}
		
		
		String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit()) );
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET );
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT );
//		if(log.isDebugEnabled()){
//			log.debug("生成分页SQL : " + boundSql.getSql());
//		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	

	public void setProperties(Properties properties) {
		
		String dialectClass = properties.getProperty(DIALECTCLASS);
		try {
			dialect = (Dialect)Class.forName(dialectClass).newInstance();
		} catch (Exception e) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:"+dialectClass,e);
		} 
	}

}
