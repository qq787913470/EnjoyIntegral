define("yadaWight/treeClick", [
	"./YDLazyTree",
	"dojo/_base/declare",
	"dijit/Tree",
	"dijit/registry"
], function (YDLazyTree,declare,Tree,registry) {
	var TreeNode = declare([Tree._TreeNode], {
		
	});
	
	return declare("yadaWight.treeClick" ,[YDLazyTree], {
		//去往的url
		tourl:null,
		
		reftourl:null,
		//id集合
		ids:null,
		//树形节点id
		treeId:null,
		//右侧定义的框架id
		frame:null,
		
		treeName:null,
		
		treeParent:null,
		
		constructor:function(a){
			this.reftourl=a.tourl;
		},
		
		onClick:function()
		{
			this.tourl=this.reftourl;
			var treeNode=this.selectedItems[0];
            var idItem=this.ids; 
            for(var i=0;i<idItem.length;i++)
            {
            	var hiddenVal=dojo.byId(idItem[i]).value;
            	var hiddenName=dojo.byId(idItem[i]).name;
            	this.tourl=this.tourl+hiddenName+"="+hiddenVal+"&";
            }
            if(this.treeId!=null&&this.treeId!=""){
            	this.tourl=this.tourl+this.treeId+"="+treeNode["treeId"]+"&";
            }
            if(this.treeName!=null&&this.treeName!=""){
            	this.tourl=this.tourl+this.treeName+"="+treeNode["treeName"]+"&";
            }
            if(this.treeParent!=null&&this.treeParent!=""){
            	this.tourl=this.tourl+this.treeParent+"="+treeNode["treeParentId"];
            }
            
            registry.byId(this.frame).set("href",this.tourl);
            
		}
	});	
});	


