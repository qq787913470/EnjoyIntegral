package com.yada.security.model;

public class TreeObject {
	
	private String treeId;
	
	private String treeName;
	
	private String isLeaf;
	
	private String treeParentId;
	// 预留url为点击跳转用
	private String url;
	// 为checkBox回选使用
	private String checked;

	public TreeObject() {
		super();
	}

	public TreeObject(String menuId, String menuName, String isLeaf,
			String parentId) {
		super();
		this.treeId = menuId;
		this.treeName = menuName;
		this.isLeaf = isLeaf;
		this.treeParentId = parentId;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getTreeParentId() {
		return treeParentId;
	}

	public void setTreeParentId(String treeParentId) {
		this.treeParentId = treeParentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
