define("yadaWight/mtree", [
	"dojo/_base/declare", "dojo/data/ItemFileWriteStore", "dojo/ready", 
    "dojo/dom", "dijit/Tree", "dijit/tree/ForestStoreModel", "dijit/registry"], 
    function(declare, ItemFileWriteStore, ready, dom, Tree, ForestStoreModel, registry){
		return declare(null, {
			url: "",
			nid: "",
			showId: "",
			showUrl: "",
			parentId: "0",
			self: this,
			/*
			 * 构造函数.
			 * 实例化对象参数,生成树,添加树节点菜单
			 */
			constructor: function(args){
				declare.safeMixin(this,args);
				this.getTree().startup();
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
			        	return item.pmenuResId == "0" || item.actionName == "" || item.root == true;
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
		    	var showId = this.showId;
		    	var showUrl = this.showUrl;
		    	return Tree({
		            model: this.getModel(),
		            //openOnClick: true,
		            onClick: function(item){
		            	if(item.root == true){
		            		alert("主菜单不允许操作.");
		            		return false;
		            	}
		            	registry.byId(showId).set("href", showUrl+"?menuResId=" + item.menuResId + '&time=' + new Date().getTime());
					},
		            domNode: dom.byId(this.nid)
		        });
		    }
		    
		});
		
});