package com.yada.common.dict.entity;

public class SelectOption { 
    private String value;
    private String name;
    public String desc;
    
    public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public SelectOption(){
    }
    
    public SelectOption(String value,String name){
    	this.value = value;
    	this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
        this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
