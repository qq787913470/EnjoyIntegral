define("yadaWight/resmenu", [
	"dojo/_base/declare", "dojo/data/ItemFileWriteStore", "dojo/ready", 
    "dojo/dom", "dijit/Tree", "dijit/tree/ForestStoreModel", "dijit/Menu", 
    "dijit/MenuItem", "dojo/_base/array", "dojo/aspect", "dijit/Dialog", "dijit/registry"], 
    function(declare, ItemFileWriteStore, ready, dom, Tree, 
    		ForestStoreModel, Menu, MenuItem, array,  aspect, Dialog, registry){
		return declare(null, {
			url: "",
			nid: "",
			typeId: "",
			typeName: "",
			parentId: "0",
			self: this,
			/*
			 * 构造函数.
			 * 实例化对象参数,生成树,添加树节点菜单
			 */
			constructor: function(args){
				declare.safeMixin(this,args);
				this.getTree().startup();
				this.addTreeMenu();
			},
			
			/*
			 * 存取对象属性值
			 */
			set: function(attr,value){
		    	this[attr] = value;
		    },
		    get: function(attr){
		    	return this[attr];
		    },
		    
		    /*
		     * 得到后台JSON数据,作为树数据仓库store
		     */
		    getStore: function(){
		    	return new ItemFileWriteStore({
		    		url: this.url
		    	});
		    },
		    
		    /*
		     * 处理数据仓库store,构造树节点显示的数据结构.
		     * ForestStoreModel: 可以有多个根节点(若不指定根节点)
		     * mayHaveChildren: 指定树节点是否显示文件夹样式,即其下有子节点
		     * getChildren: 获取树节点的子节点
		     */
		    getModel: function(){
		    	return new ForestStoreModel({
		    		store: this.getStore(),
		    		query: {pmenuResId: this.parentId},
			        rootId: "0",
         			rootLabel: "主菜单",
			        mayHaveChildren: function(item){
			        	var resuleValue = '';
			        	dojo.xhrGet({
			        		url: "res/resIsLeaf_ajax.do?t="+new Date().getTime(),
			        		sync: true,
			        		content: {
			        			menuResId: item.menuResId
			        		},
			        		load: function(data){
			        			resuleValue = data;
			        		},
			        		error: function(error){
			        			alert(error);
			        		}
			        	});
			        	if(resuleValue == '0') {
							return false;
						}
			        	return true;
			        },
			        getChildren: function(parentItem, callback, onError) {
			              if( parentItem.root == true ){
			                 console.debug("Root onLoad here!");
			                 if(this.root.children){
				                    console.debug("--already loaded, just return!");
				                    callback(this.root.children);
				               }else{
				                   this.store.fetch({
				                        query: this.query, // Call all parents
				                        queryOptions: {cache:false},
				                        onComplete: dojo.hitch(this, function(items){
				                            this.root.children = items;
				                            callback(items);
				                        }),
				                        onError: onError
				                    });
			                 }
			              } else {
			                 console.debug("Tree child onLoad here: "+parentItem.menuResId);
		                     var sx = parentItem.menuResId;
			                   this.store.fetch({
			                        query: { pmenuResId: sx.toString() }, //Find all children based on parentItem.id
			                        queryOptions: {cache:false},
			                        onComplete: dojo.hitch(this, function(items){
			                            parentItem.children = items;
			                            callback(items);
			                        }),
			                        onError: onError
			                    });
			              }  // if( parentItem.root == true )
			        }  // getChildren 
		    	});
		    },
		    
		    /*
		     * 树的显示层
		     * openOnClick: 左键点击树节点,是否展开子节点
		     * domNode: 树在页面的显示位置
		     * onClick: 树节点点击事件
		     */
		    getTree: function(){
		    	return Tree({
		            model: this.getModel(),
		            openOnClick: true,
		            domNode: dom.byId(this.nid)
		        });
		    },
		    
		    addTreeMenu: function(){
		    	var typeId = this.typeId;
		    	var typeName = this.typeName;
		    	var menu = new Menu({
		    		targetNodeIds: [this.nid],
		    		style: "width: 40px;"
		    	});
		    	menu.addChild(new MenuItem({
		    		label: "Simple menu item"
		    	}));
		    	aspect.before(menu, "_openMyself", function(args){
		    		var rn = window.document.elementFromPoint(args.coords.x, args.coords.y);
		    		var tn = dijit.getEnclosingWidget(rn);
		    		dojo.forEach(menu.getChildren(), function(child){
		    			menu.removeChild(child);
		    		}, this);
		    		var dialog = new Dialog();
		    		menu1 = dijit.MenuItem({
						label : "增加" + tn.item.typeName,
						onClick : function() {
							dialog.setAttribute("title", "增加" + tn.item.typeName);
							dialog.setAttribute("style", "width: 500px;");
							dialog.setAttribute("href", "res/insert.do?typeId="+typeId+"&treeId="+tn.item.menuResId);
							dialog.show();
						}
					});

					menu2 = dijit.MenuItem({
						label : "修改" + tn.item.typeName,
						onClick : function() {
							dialog.setAttribute("title", "修改" + tn.item.typeName);
							dialog.setAttribute("style", "width: 500px;");
							dialog.setAttribute("href", "res/edit.do?typeId="+typeId+"&treeId="+tn.item.menuResId);
							dialog.show();
						}
					});
					
					menu3 = dijit.MenuItem({
						label : "删除" + tn.item.typeName,
						onClick : function() {
							if (confirm('是否删除'+ tn.item.menuName + '?')) {
								window.location.href="delete.do?typeId="+typeId+"&treeId="+tn.item.menuResId;
							}else {
								return false;
							}
						}
					});
					
					menu4 = dijit.MenuItem({
						label : "增加" + typeName,
						onClick : function() {
							dialog.setAttribute("title", "增加" + typeName);
							dialog.setAttribute("style", "width: 500px;");
							dialog.setAttribute("href", "res/insert.do?typeId="+typeId+"&treeId=0");
							dialog.show();
						}
					});
					
					menu5 = dijit.MenuItem({
						label : "子" + tn.item.typeName + "排序",
						onClick : function() {
							var dialog2 = new Dialog({
								title: tn.item.menuName + "子" + tn.item.typeName + "排序",
								style: "width: 500px;",
								href: "res/showOrderRes.do?typeId="+typeId+"&treeId="+tn.item.menuResId
							});
							//dialog.setAttribute("title", tn.item.menuName + "子" + tn.item.typeName + "排序");
							//dialog.setAttribute("style", "width: 500px;");
							//dialog.setAttribute("href", "res/showOrderRes.do?typeId="+typeId+"&treeId="+tn.item.menuResId);
							dialog2.show();
						}
					});
					
					menu6 = dijit.MenuItem({
						label : "子" + typeName + "排序",
						onClick : function() {
							var dialog3 = new Dialog({
								title: "主菜单" + "子" + typeName + "排序",
								style: "width: 500px;",
								href: "res/showOrderRes.do?typeId="+typeId+"&treeId=0"
							});
							//dialog.setAttribute("title", "主菜单" + "子" + typeName + "排序");
							//dialog.setAttribute("style", "width: 500px;");
							//dialog.setAttribute("href", "res/showOrderRes.do?typeId="+typeId+"&treeId=0");
							dialog3.show();
						}
					});
					var resuleValue = '';
		        	dojo.xhrGet({
		        		url: "res/resIsLeaf_ajax.do?t="+new Date().getTime(),
		        		sync: true,
		        		content: {
		        			menuResId: tn.item.menuResId
		        		},
		        		load: function(data){
		        			resuleValue = data;
		        		},
		        		error: function(error){
		        			alert(error);
		        		}
		        	});
		        	
					if(tn.label != '主菜单'){
						menu.addChild(menu1);
						menu.addChild(menu2);
					}else{
						menu.addChild(menu4);
					}
		        	if(resuleValue == '0') {
		        		menu.addChild(menu3);
					}else if(tn.label != '主菜单'){
						menu.addChild(menu5);
					}else{
						menu.addChild(menu6);
					}
					
		    	});
		    	menu.startup();
		    }
		});
		
});