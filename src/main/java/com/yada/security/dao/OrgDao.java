package com.yada.security.dao;

import java.util.List;

import com.yada.security.model.Org;
import com.yada.security.model.User;

public interface OrgDao {
	// 插入
		public int insert(Org org);

		// 更新
		public int update(Org org);

		// 删除
		public int delete(String id);

		// 根据ID查询
		public Org getById(String id);

		// 根据条件查询数据数据COUNT
		public int findCountByWhere(Org org);

		// 根据条件查询数据
		public List<Org> findListByWhere(Org org);

		//
		public List<Org> selectAll();
}
