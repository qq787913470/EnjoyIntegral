define(
"yadaWight/YDLazyTreeMenu", [ "dijit/Tree",
  "require",
  "dojo/_base/declare",
  "dijit/tree/ForestStoreModel",
  "dojo/data/ItemFileWriteStore",
  "dijit/Menu",
  "dijit/MenuItem",
  "dijit/Dialog",
  "dijit/registry",
  "dojo/on",
  "dojo/_base/event",
  "./YDLazyTree"
  ],function(Tree, require, declare, ForestStoreModel, ItemFileWriteStore,
				Menu, MenuItem, Dialog,registry,on,event,YDLazyTree) {

	var TreeNode = declare([ Tree._TreeNode ], {

	});

	return declare("yadaWight.YDLazyTreeMenu",	[ YDLazyTree ],{
				
				persist : false,
		
				//是否显示右键菜单（最大）
                isRightMenu:false,
				
                //具体的一个菜单的控制。（待定）
				showDelete:true,
				//是否显示查看页面
				showShow:false,
				
				//跟节点，右键菜单是否完全显示
				rootShowMenu : true,
				
				//点击后跳转的url
				clicktoUrl : null,
				
				//操作的URL
				editJspUrl : null,
				addJspUrl : null,
				deleteUrl : null,
				//查看页面
				showUrl : null,
				
				//右键菜单名称.longwu.yan
				editLabel : null,
				addLabel : null,
				deleteLabel : null,
				//查看页面
				showLabel : null,
                
                //是否有左键单击事件
	            click:false,
				
	            //点击后触发的id
	            clicktoId:null,
				
	            //全局当前操作的node
				createNode:null,
				
				//弹出页面的Dialog
	            treeDialog:null,
				
	            // 构造函数
				constructor : function(a) {
					this.isExpandable = false;
				},
	            
				// postCreate必走方法
				postCreate : function() {
					var self = this;
					this.own(
						on(this.domNode, on.selector(".dijitTreeNode", 'contextmenu'), function(evt){
							self._onContextMenu(registry.byNode(this), evt);
						}));
					// 调用父类的该该方法
					this.treeDialog=this.newDialog();
					if(this.isRightMenu){
						this.newMenu();
					}
					
					this.inherited(arguments);
				},

				
				_onContextMenu: function(/*TreeNode*/ nodeWidget, /*Event*/ e){
					this.focusNode(nodeWidget);
				    event.stop(e);
				},
				
				onClick:function(node){
		           //只适用于DOJO布局
		           if(this.click&&this.clicktoId!=null&&this.clicktoId!=""){
		        	   if (this.clicktoUrl != null) {
		        		   dijit.byId(this.clicktoId).setHref(this.clicktoUrl+node.treeId);
						} else {
							throw new Error('请添加点击的url地址');
						};
		           };
		           this.inherited(arguments);
		          },
				
				//创建菜单
				newMenu : function() {

					var node = null;
					var thiz = this;
					var menu1 = null;
					var menu2 = null;
					var menu3 = null;
					var showMenu = null;
					var dialog = this.treeDialog;
					dialog.setAttribute('style', 'width: 800px; height: 350px;');
					var pMenu = Menu({
						
						targetNodeIds : [ thiz.id ],
						style:{width:"100px"},
						startup : function() {
							dojo.connect(this,"_openMyself",thiz,function(e) {
								node = dijit.getEnclosingWidget(e.target);
								pMenu.removeChild(menu2);
								pMenu.removeChild(menu3);
								pMenu.removeChild(showMenu);
								if(thiz.showDelete){
									pMenu.addChild(menu3);
								}
								if(thiz.showShow){
									pMenu.addChild(showMenu);
								}
								if (node.lastFocused.item.isLeaf == 'Y') {
									pMenu.addChild(menu2);
								}
								if (thiz.rootShowMenu) {
									pMenu.addChild(menu2);
								}                 

							});
						}
					});
					menu1 = MenuItem({
						label : thiz.addLabel || "增加菜单",
						onClick : function() {
							thiz.createNode=node;
							dialog.setAttribute('title', thiz.addLabel || '增加菜单');
							if (thiz.addJspUrl != null) {
								dialog.setAttribute('href',thiz.addJspUrl+"&treeId="+node.lastFocused.item.treeId);
							} else {
								throw new Error('请添加引入的url地址');
							}
							dialog.show();
						}
					});
					
					menu2 = MenuItem({
						label : thiz.editLabel || "修改菜单",
						onClick : function() {
							thiz.createNode=node;
							dialog.setAttribute('title', thiz.editLabel || '修改菜单');
							if (thiz.editJspUrl != null) {
								dialog.setAttribute('href',thiz.editJspUrl+ "&treeId="+ node.lastFocused.item.treeId);
							} else {
								throw new Error('请添加引入的url地址');
							}
							dialog.show();
						}
					});

					showMenu = MenuItem({
						label : thiz.showLabel || "查看菜单",
						onClick : function() {
							thiz.createNode=node;
							dialog.setAttribute('title', thiz.showLabel || "查看菜单");
							if (thiz.showUrl != null) {
								dialog.setAttribute('href',thiz.showUrl+ "&treeId="+ node.lastFocused.item.treeId);
							} else {
								throw new Error('请添加引入的url地址');
							}
							dialog.show();
						}
					
					});

					menu3 = MenuItem({
						label : thiz.deleteLabel || "删除菜单",
						onClick : function() {
							thiz.deleteNode(node);
						}
					});
					pMenu.addChild(menu1);
					
					pMenu.startup();
				},
				
				// 返回成功或者失败
				getIsTrueSubmit : function(url, treeId) {
					var returnBoolean = false;
					if (url != null) {
						dojo.xhrPost({
							url : url+"&treeId="+treeId,
							sync : true,
							handle : function(data) {
								returnBoolean = data;
							}
						});
					} else {
						throw new Error('请添加删除的url地址');
					}
					return returnBoolean;
				},
			
				newDialog : function() {
					var treeDialog = Dialog({
								preventCache:false,
								refreshOnShow:true
							});
					return treeDialog;
				},
				
				//增加节点
				addTreeNode:function(isTrue,refMenuId,refMenuName,refIsLeaf){
				 if(isTrue!='false'){
				     var menu={
					    treeId:refMenuId,
					    treeName:refMenuName,
					    isLeaf:refIsLeaf
					  };
				     var parent = {parent : this.createNode.lastFocused.item,attribute : 'children'};
				     this.model.store.newItem(menu, parent);
				     this.model.store.setValue(this.createNode.lastFocused.item,'isLeaf', 'N');
				     this.treeDialog.hide();
		           }else{
		        	   alert('保存失败');
		        	 }
				},
				
				//修改节点
			    editTreeNode:function(isTrue,refMenuName,refIsLeaf){
				   if(isTrue!='false'){
					  var menu={
						    treeName:refMenuName,
						    isLeaf:refIsLeaf
						    };
	                   for(var eachAttr in menu){
	                	   this.model.store.setValue(this.createNode.lastFocused.item,eachAttr,menu[eachAttr]);
                       };
	                  this.treeDialog.hide();
				   }else{
					   alert('修改失败');
				   }
	            },
	            
	            //删除节点
				deleteNode : function(node) {
					if (confirm('是否删除此菜单?')) {
						var isTrue=this.getIsTrueSubmit(this.deleteUrl,node.lastFocused.item.treeId);
						if (isTrue!='false') {
							this.model.store.deleteItem(node.lastFocused.item);
						} else {
							alert('删除失败!');
						};
					};
				}
	          
			});
	});
