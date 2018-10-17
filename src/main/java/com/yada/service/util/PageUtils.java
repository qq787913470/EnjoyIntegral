package com.yada.service.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.yada.mybatis.paging.Page;

/**
 * 一个page的工具类。主要是为了spring page和mybatis page的兼容
 * 
 * @author jiangfengming
 * 
 */
public class PageUtils {

	/**
	 * 将spring jpa的page对象转换成mybatis的page对象。
	 * 
	 * @param springPage
	 *            springJpa的page对象
	 * @param viewModelType
	 *            展示的视图对象的类型。
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T, X> Page convertSrpingPageToMybatisPage(org.springframework.data.domain.Page<T> springPage, Class<X> viewModelType)
			throws InstantiationException, IllegalAccessException {
		Page page = new Page(springPage.getNumber()+1, springPage.getSize(), (int)springPage.getTotalElements());
		Iterator<T> ts = springPage.iterator();
		List<X> list = new ArrayList<X>();

		while (ts.hasNext()) {
			X x = viewModelType.newInstance();
			BeanUtils.simpleCopy(ts.next(), x);
			list.add(x);
		}
		page.setResult(list);
		return page;
	}
}
