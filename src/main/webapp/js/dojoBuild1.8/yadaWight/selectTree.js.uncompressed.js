define("yadaWight/selectTree", [
	"./YDLazyTree",
	"dojo/_base/declare",
	"dijit/Tree"
], function (YDLazyTree,declare,Tree) {
	var TreeNode = declare([Tree._TreeNode], {
		
	});
	
	return declare("yadaWight.selectTree" ,[YDLazyTree], {

		textId:null,
		
		onClick:function(){
			var treeNode=this.selectedItems[0];
			
			dojo.byId("selectof"+this.textId).innerText = treeNode["treeName"];
			dojo.byId(this.textId+"_name").value = treeNode["treeId"];
			dojo.style("selectchild"+this.textId,{
				display:"none"
			});
		}
	});	
});