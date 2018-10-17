package com.yada.security.reflex;

import java.util.List;

import com.yada.xmlUtils.model.Res;

public class MyResUtil {

	private static final String URLTYPE = "2";
	private static final String URL_SUFFIX = ".do";
	private static final String URL_PERFIX = "../";
	private static final String antAll = "*";
//	private static Boolean hasAll = true;
	
	/**
	 * 扫描包生成XML中的实体Res
	 * @param packageName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Res clazz2Res(String packageName){
		
		List<Class>  classes = ClassUtils.getClasses(packageName);
		
		//XML中的根节点
		Res root = new Res();
		
		//XML中的URL类型的资源的跟节点
		Res urlRoot = new Res();
		urlRoot.setModel(antAll);
		urlRoot.setMethod(antAll);
		urlRoot.setType(URLTYPE);
		urlRoot.setOrder("9999");
		
		for(Class clazz : classes){
			//获取class的名称。生成该class对应的顶级资源。
			String model = ClassUtils.getControllerValue(clazz);
			Res pRes = new Res();
			pRes.setModel(model);
			pRes.setMethod(antAll);
			pRes.setType(URLTYPE);
//			pRes.setName(clazz.getSimpleName());
			
			//获取class的所有方法。循环生成对应的Res
			List<String> methods = ClassUtils.getRequestMappingVlaue(clazz);
			int order = 0;
			for(String method : methods){
				Res res = new Res();
				res.setModel(model);
				res.setMethod(method);
				res.setType(URLTYPE);
				res.setOrder(order+"");
//				res.setName(method + ".do");
				
				//设置子父关系。
				pRes.getLists().add(res);
				//顺序递增。
				order ++ ;
			}
			pRes.setOrder(order+"");
			//加到XML的跟节点下
			urlRoot.getLists().add(pRes);
		}
		root.getLists().add(urlRoot);
		return root;
	}
	
	/**
	 * 作废！
	 * 
	 * XML中定义的Res对象 转换成该项目中的Res对象。以便使用现有的处理方式。
	 * @param root
	 * @return
	*/	
//	public static List<com.yada.security.model.Res> myRes2Ress(Res root){
//		
//		List<com.yada.security.model.Res> ress = new ArrayList<com.yada.security.model.Res>();
//		
//		//跟资源 通常是 ../*/*.do
//		com.yada.security.model.Res rootRes = myRes2Res(root);
//		
//		//跟资源的子资源集合
//		List<com.yada.security.model.Res> rootResChildren = new ArrayList<com.yada.security.model.Res>();
//		
//		//遍历跟资源的子资源
//		for(Res pRes : root.getLists()){
//
//			//pRes的子资源集合
//			List<com.yada.security.model.Res> pResChildren = new ArrayList<com.yada.security.model.Res>();
//			
//			//循环pRes的子资源
//			for(Res res : pRes.getLists()){
//				//如果有 ../XX/*.do这种资源(pRes默认都是../XX/*.do这种形式的资源)，则需要将pRes的子资源保存到pResChildren中。
//				if(hasAll){
//					pResChildren.add(myRes2Res(res));
//				}else{//不然则存放到跟资源下
//					rootResChildren.add(myRes2Res(res));
//				}
//			}
//			
//			
//			//如果有 ../XX/*.do这种资源(pRes默认都是../XX/*.do这种形式的资源)
//			if(hasAll){
//				//转换成系统识别的对象。
//				com.yada.security.model.Res syspRes =  myRes2Res(pRes);
//				//设置该对象的子资源集合
//				syspRes.setChildren(pResChildren);
//				//把pRes转换后的、系统识别的对象保存到rootResChildren（跟资源的子资源集合）
//				rootResChildren.add(syspRes);
//			}
//		}
//		//设置跟资源的子资源集合
//		rootRes.setChildren(rootResChildren);
//		//资源集合加值
//		ress.add(rootRes);
//		return ress;
//	}

	//属性转换。
	public static com.yada.security.model.Res myRes2Res(Res res){
		
		com.yada.security.model.Res temp = new com.yada.security.model.Res();
		if(!("".equals(res.getModel()) && "".equals(res.getMethod()))){
			temp.setActionName(URL_PERFIX + res.getModel()+"/"+res.getMethod()+URL_SUFFIX);
		}
		temp.setDsc(res.getDec());
		temp.setMenuName(res.getName());
		temp.setOrderId(Long.parseLong(res.getOrder()));
		temp.setTypeId(Integer.parseInt(res.getType()));
		return temp;
	}
}
