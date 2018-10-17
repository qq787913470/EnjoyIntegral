package com.yada.xmlUtils;

import com.yada.security.reflex.MyResUtil;
import com.yada.xmlUtils.model.Res;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Res root = MyResUtil.clazz2Res("com.yada.security.controller");
		XMLUtils.OBJ2XML(root, "D://Root.xml");
		
	}

}
