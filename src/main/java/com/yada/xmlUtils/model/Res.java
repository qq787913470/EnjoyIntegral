package com.yada.xmlUtils.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Res" )
public class Res extends BaseModel {

	private List<Res> lists = new ArrayList<Res>();

	private String name = "";
	private String model = "";
	private String method = "";
	private String type = "";
	private String order = "";
	private String dec ="";

	public List<Res> getLists() {
		return lists;
	}

	@XmlElement(name="Res")
	public void setLists(List<Res> lists) {
		this.lists = lists;
	}

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	@XmlAttribute
	public void setModel(String model) {
		this.model = model;
	}

	public String getMethod() {
		return method;
	}

	@XmlAttribute
	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	@XmlAttribute
	public void setType(String type) {
		this.type = type;
	}

	public String getOrder() {
		return order;
	}

	@XmlAttribute
	public void setOrder(String order) {
		this.order = order;
	}

	public String getDec() {
		return dec;
	}

	@XmlAttribute
	public void setDec(String dec) {
		this.dec = dec;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getClazz() {
		return Res.class;
	}

}
