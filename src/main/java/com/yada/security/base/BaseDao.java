package com.yada.security.base;

import java.util.List;

import com.yada.mybatis.paging.Page;

public interface BaseDao<Entity,Id> {

	// 插入
	abstract void insert(Entity entity);

	// 更新
	abstract void update(Entity entity);

	// 删除
	abstract void delete(Id id);
	// dblink插入
	abstract void insert_DBLink(Entity entity);
	
	// dblink更新
	abstract void update_DBLink(Entity entity);
	
	// dblink删除
	abstract void delete_DBLink(Id id);

	// 根据ID查询
	abstract Entity getById(Id id);

	// 根据条件查询数据数据COUNT
	abstract int findCountByWhere(Entity entity);

	// 根据条件查询数据
	abstract List<Entity> findListByWhere(Entity entity);
	
	abstract Page queryPage(BaseQuery query);
}
