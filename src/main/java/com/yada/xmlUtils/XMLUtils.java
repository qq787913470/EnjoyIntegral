package com.yada.xmlUtils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.yada.xmlUtils.model.BaseModel;


public class XMLUtils {

	/**
	 * 
	 * @param model	应该是BaseModel的子类实例对象。
	 * @param xmlFile 要输出的目标文件。
	 */
	public static void OBJ2XML(BaseModel model,File xmlFile){
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			//控制台输出
			marshaller.marshal(model, System.out);
			//文件数据
			marshaller.marshal(model, xmlFile);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param model	应该是BaseModel的子类实例对象。
	 * @param filePath 要输出的目标文件路径。如D://XX//YY.XML
	 */
	public static void OBJ2XML(BaseModel model,String filePath){
		File xmlFile = new File(filePath);
		OBJ2XML(model, xmlFile);
	}
	
	
	/**
	 * 
	 * @param clazz XML转换后的目标对象类型。
	 * @param xmlFile 数据源文件。
	 * @return  BaseModel实例。
	 */
	@SuppressWarnings("rawtypes")
	public static BaseModel XML2OBJ(Class clazz,File xmlFile){
		
		try {
			
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			return (BaseModel)unmarshaller.unmarshal(xmlFile);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param clazz XML转换后的目标对象类型。
	 * @param filePath 数据源文件路径。
	 * @return  BaseModel实例。
	 */
	@SuppressWarnings("rawtypes")
	public static BaseModel XML2OBJ(Class clazz,String filePath){
		File xmlFile = new File(filePath);
		return XML2OBJ(clazz, xmlFile);
	}
	
	
}
