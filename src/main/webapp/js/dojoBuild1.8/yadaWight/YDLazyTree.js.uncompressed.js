define("yadaWight/YDLazyTree", [
	"dijit/Tree",
	"require",
	"dojo/_base/declare",
	"dijit/tree/ForestStoreModel",
	"dojo/data/ItemFileWriteStore"
], function (Tree,require,declare,ForestStoreModel,ItemFileWriteStore) {
	
	var TreeNode = declare([Tree._TreeNode], {
		
	});
	
	
	
	return declare("yadaWight.YDLazyTree",[Tree], {
		
		// persist: Boolean
		//		Enables/disables use of cookies for state saving.
		persist : false,
	
		showRoot:false,
		
		dataUrl : null,
		
		//构造函数
		constructor:function(a){
            this.model=this.initData(a.dataUrl); 
		},
	
		
		//展开函数
		onOpen : function(node) {
			this.addNode(this, 'open', node);
		},
		
		//添加节点
		addNode : function(node, e, item) {
			var tree = dijit.byId(node.id);
			// 判断是否选中过
			var isOpen = false;
			if (node.selectedItems != null) {
				var tn;
				var a;
				if (e != 'open') {
					a = tree.getNodesByItem(node.selectedItems[0])[0];
					tn = node.selectedItems[0];
				} else {
					var xx = tree.getNodesByItem(item)[0];
					tn = xx.item;
					a = xx;
				}
				// 判断点击节点是否有值
				if (tn.isLeaf != undefined && tn.isLeaf != 'Y'&& tn.children == '') {
					
					isOpen = true;
					var returnData;
                    var timesTamp=new Date().getTime();
					var url = this.dataUrl+"&pTreeId="+tn.treeId+"&timesTamp="+timesTamp;
					var parent = {
						parent : tn,
						attribute : 'children'
					};
					dojo.xhrGet({
								url : url,
								// 同步加载
								sync : true,
								// 返回的send
								handle : function(data) {
									returnData = eval("("+ data + ")");
								}
							});

					for ( var i = 0; i < returnData.length; i++) {
						returnData[i].children=new Array();
						tree.model.store.newItem(returnData[i],	parent);
					}
				}

				if (!isOpen) {
					if (e != "open") {
						if (a.isExpanded) {
							a.collapse();
						} else {
							a.expand();
						}
					}
				} else {
					a.expand();
				}
			}
		},
		
		initData:function(url){
			
			var resuleValue=null;
			 dojo.xhrPost({
		                url: url,
		                sync: true,
                        handle:function(data){
	                        resuleValue=data;
                        }});   
		     
			var da ={
				"identifier":"treeId",
				"label":"treeName"
			};
			
			da.items = eval("("+ resuleValue +")");
			for(var i=0;i<da.items.length;i++){
			  if(da.items[i].isLeaf=='N'){
				  da.items[i].children=new Array();
			  }
			};
			
		   datas=new ItemFileWriteStore({data:da, 
			   							getChildren: function(object){
			   								return this.query({parent: object.treeId});
										}
			        	});
			      
	       // 实体模型
	       var model=ForestStoreModel({
		       // 确定根菜单名称，若store定义，这里定义将无效
		       rootLable:"treeName",
		       store:datas,
		       // 确定跟菜单的有何特征
		       query:{isLeaf:"N"},
		       // 确定子元素来源集合
		       childrenAttrs:["children"],
		      
		       // 判断是否为根菜单
		       mayHaveChildren: function(object){return object.isLeaf=="N";	}	  
	       });
	       return model;
		}
				
	});	
});	


