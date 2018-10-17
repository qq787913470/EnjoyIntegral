package com.yada.security.reflex;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.model.Res;
import com.yada.security.reflex.ResAnnotation.typeEnum;

@SuppressWarnings("rawtypes")
public class InitializedData {
	private static InitializedData initData = new InitializedData();
	private static final Class<? extends Annotation> MVC_ANNOTATION_CONTROLLER = Controller.class;
	private static final Class<? extends Annotation> MVC_ANNOTATION_REQUESTMAPPING = RequestMapping.class;
	private static final Class<? extends ResAnnotation> RES_ANNOTATION_REQUESTMAPPING = ResAnnotation.class;
	private static final String CONTROLLER_SUFFIX = "controller";
	private static final String URL_SUFFIX = ".do";
	private static final String URL_PERFIX = "../";
//	private static Boolean hasAll = true;

	public static InitializedData getInstance() {
		return initData;
	}

	/*
	 * 取得某一类所在包的所有类名 不含迭代
	 */
	protected String[] getPackageAllClassName(String classLocation, String packageName) {
		// 将packageName分解
		String[] packagePathSplit = packageName.split("[.]");
		String realClassLocation = classLocation;
		int packageLength = packagePathSplit.length;
		for (int i = 0; i < packageLength; i++) {
			realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
		}
		File packeageDir = new File(realClassLocation);
		if (packeageDir.isDirectory()) {
			String[] allClassName = packeageDir.list();
			return allClassName;
		}
		return null;
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param packageName
	 * @return
	 */

	public List<Class> getClasses(String packageName) {

		// 第一个class类的集合
		List<Class> classes = new ArrayList<Class>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();

				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					JarFile jar;
					try {
						// 获取jar
						jar = ((JarURLConnection) url.openConnection()).getJarFile();
						// 从此jar包 得到一个枚举类
						Enumeration<JarEntry> entries = jar.entries();
						// 同样的进行循环迭代
						while (entries.hasMoreElements()) {
							// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
							JarEntry entry = entries.nextElement();
							String name = entry.getName();
							// 如果是以/开头的
							if (name.charAt(0) == '/') {
								// 获取后面的字符串
								name = name.substring(1);
							}
							// 如果前半部分和定义的包名相同
							if (name.startsWith(packageDirName)) {
								int idx = name.lastIndexOf('/');
								// 如果以"/"结尾 是一个包
								if (idx != -1) {
									// 获取包名 把"/"替换成"."
									packageName = name.substring(0, idx).replace('/', '.');
								}
								// 如果可以迭代下去 并且是一个包
								if ((idx != -1) || recursive) {
									// 如果是一个.class文件 而且不是目录
									if (name.endsWith(".class") && !entry.isDirectory()) {
										// 去掉后面的".class" 获取真正的类名
										String className = name.substring(packageName.length() + 1, name.length() - 6);
										try {
											// 添加到classes
											classes.add(Class.forName(packageName + '.' + className));
										} catch (ClassNotFoundException e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	protected void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class> classes) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					// 添加到集合中去
					classes.add(Class.forName(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 通过类..生成相应的初始化数据实体
	public List<Res> clazz2Res(List<Class> clazzList) {
		ArrayList<Res> list = new ArrayList<Res>();
		for (Class c : clazzList) {
			List<Res> l = getAllRes(c);
			if (l == null) {
				continue;
			}
			list.addAll(l);
		}
		return list;
	}

	/**
	 * 通过类获取所有的资源对象
	 * 
	 * @param clazz
	 */
	protected List<Res> getAllRes(Class clazz) {
		List<Res> resList = new ArrayList<Res>();
		//获取Controller的Res
		Res parentRes = getControllerValue(clazz);
		if(parentRes==null){
			return null;
		}
		// 所有的URL
		getRequestMappingVlaue(parentRes,clazz);
		resList.add(parentRes);
		//把父类的URL放置在顺序的最后.
		parentRes.setOrderId(Long.parseLong(parentRes.getChildren().size()+""));
		return resList;
	}


	// 获取@Controller的值
	protected Res getControllerValue(Class clazz) {
		// 获取类上的注解@Controller
		@SuppressWarnings("unchecked")
		Annotation controllerAnn = clazz.getAnnotation(MVC_ANNOTATION_CONTROLLER);
		@SuppressWarnings("unchecked")
		ResAnnotation resAnnotation = (ResAnnotation) clazz.getAnnotation(RES_ANNOTATION_REQUESTMAPPING);
		// 没有标注@Controller的则不进行处理
		if (controllerAnn == null||resAnnotation==null) {
			return null;
		}
		// 获取@Controller的值
		Controller controller = (Controller) controllerAnn;

		String controllerValue = controller.value();
		Res parentRes = new Res();
		// 如果@Controller值为空，则按照MVC的规则进行解析。
		if (controllerValue == null || controllerValue.equals("")||controllerValue.equals("login")) {
			String simpleName = clazz.getSimpleName();
			controllerValue = simpleName.toLowerCase().substring(0, simpleName.length() - CONTROLLER_SUFFIX.length());
			parentRes.setActionName(URL_PERFIX + controllerValue + "/*");
			parentRes.setMenuName(resAnnotation.chName());
			parentRes.setTypeId(2);
			parentRes.setPmenuResId(0L);//设置父级别的URL初始值为0
			parentRes.setChildren(new ArrayList<Res>());
		}
		return parentRes;
	}

	
	// 获取@Controller的值
	protected String getControllerName(Class clazz) {
		// 获取类上的注解@Controller
		@SuppressWarnings("unchecked")
		Annotation controllerAnn = clazz.getAnnotation(MVC_ANNOTATION_CONTROLLER);
		// 没有标注@Controller的则不进行处理
		if (controllerAnn == null) {
			// System.out.println("controllerAnn is null");
			return null;
		}
		// 获取@Controller的值
		Controller controller = (Controller) controllerAnn;

		String controllerValue = controller.value();
		// 如果@Controller值为空，则按照MVC的规则进行解析。
		if (controllerValue == null || controllerValue.equals("")) {
			String simpleName = clazz.getSimpleName();
			controllerValue = simpleName.toLowerCase().substring(0, simpleName.length() - CONTROLLER_SUFFIX.length());
		}
		return controllerValue;
	}
	
	
	// 获取类里的所有RequestMappingVlaue
	protected void getRequestMappingVlaue(Res parentRes,Class clazz) {
		String controllerValue = getControllerName(clazz);
		// 获取类里的所有方法
		Method[] methods = clazz.getMethods();

		if (methods == null) {
			return;
		}
		int i = 0;
		// 循环方法获取requestMappingValue
		for (Method method : methods) {
			RequestMapping requestMapping = (RequestMapping) method.getAnnotation(MVC_ANNOTATION_REQUESTMAPPING);
			ResAnnotation resAnnotation = (ResAnnotation) method.getAnnotation(RES_ANNOTATION_REQUESTMAPPING);
			if (requestMapping == null||resAnnotation==null) {
				continue;
			}

			String[] requestMappingValue = requestMapping.value();
			// 如果requestMappingValue存在值则直接保存，否则按照MVC的解析方式解析完再保存。
			if (requestMappingValue == null || requestMappingValue.length == 0) {
				Res resModel = new Res();
				StringBuilder tempUrl = new StringBuilder();
				String methodName = method.getName();
				tempUrl.append(URL_PERFIX);
				tempUrl.append(controllerValue);
				tempUrl.append("/");
				if (!methodName.equals("")) {
					tempUrl.append(methodName);
					tempUrl.append(URL_SUFFIX);
				}
				resModel.setActionName(tempUrl.toString());
				resModel.setMenuName(resAnnotation.chName());
				resModel.setOrderId(Long.parseLong(""+i));
				if(resAnnotation.resType().equals(typeEnum.menu)){
					resModel.setTypeId(1);
				}else{
					resModel.setTypeId(2);
				}
				parentRes.getChildren().add(resModel);//向父级别URL添加子URl
				i++;
				if(resAnnotation.resType().equals(typeEnum.all)){
					Res resModel2 = new Res();
					resModel2.setActionName(resModel.getActionName());
					resModel2.setMenuName(resAnnotation.chName());
					resModel2.setOrderId(Long.parseLong(""+i));
					resModel2.setTypeId(1);
					parentRes.getChildren().add(resModel2);//向父级别URL添加子URl
					i++;
				}
			}else {
				//如果存在，直接保存
			}
		}
	}
}
