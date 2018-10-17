package com.yada.service.util;

import org.dozer.DozerBeanMapper;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Bean的简单工具类。使用Dozer复制bean属性。
 * 
 * @author jiangfengming
 * 
 */
public class BeanUtils {
	private static DozerBeanMapper simpleDozerBeanMapper;
	static {
		simpleDozerBeanMapper = new DozerBeanMapper();
	}

	/**
	 * 从源对象复制属性到目标对象
	 * 
	 * @param source
	 *            源Object
	 * @param destination
	 *            目标Object
	 */
	public static void simpleCopy(Object source, Object destination) {
		simpleDozerBeanMapper.map(source, destination);
	}
	/**
	 * 从源对象复制属性到Map对象
	 *
	 * @param obj 源Object
	 */
	public static Map beanToMap(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}
		return map;
	}
}
