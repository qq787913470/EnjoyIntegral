define("yadaWight/YDTree", [
	"dijit/Tree",
	"require",
	"dojo/_base/declare",
	"dijit/tree/ForestStoreModel",
	"dojo/data/ItemFileWriteStore"
], function (Tree,require,declare,ForestStoreModel,ItemFileWriteStore) {
	
	var TreeNode = declare([Tree._TreeNode], {
		
	});
	
	
	
	return declare("yadaWight.YDTree",[Tree], {
		
		// persist: Boolean
		//		Enables/disables use of cookies for state saving.
		persist : false,
		
        //postCreate必走方法
		postCreate: function () {
			//调用父类的该该方法
			this.inherited(arguments);
		},
	
		showRoot:false,
		//构造函数
		constructor:function(a)
		{
            this.model=this.initData(a.el); 
		},
		
		initData:function(jsonString)
		{
			
				
				 
			     
				var da =
				{
				 "identifier":"treeId",
				 "label":"treeName"
				}
				da.items = eval("("+ jsonString +")");
				for(var i=0;i<da.items.length;i++)
				{
				  if(da.items[i].isLeaf=='N')
				  {
				  da.items[i].children=new Array();
				  }	
				}
				var myArray=new Array();
				var j=0;
				
				//将根菜单对象的下标取出
				for(var i=0;i<da.items.length;i++)
				{
				if(da.items[i].parentId=="")
				{
				      myArray[j]=i;
				      j++;
				}
				
				}
				//循环将子菜单加入到父级菜单中
				for(var i=0;i<da.items.length;i++)
				{
				//判断是否有父亲
				  if(da.items[i].parentId!="")
				  {
				  for(var k=0;k<j;k++)
				  {
				  //判断是否为一个父亲
				     if(da.items[myArray[k]].treeId==da.items[i].parentId)
				     {
				         //将找出的对象封装    此属性_reference，必须这样写，dojo模型会在加载时，自动将对应子集加入到父级中 
				         var _add={'_reference':da.items[i].treeId};
				         //加入lind 集合中
				         da.items[myArray[k]].children.push(_add);
				         break;
				     }
				  
				  }
				  
				  }
				}
				
				
				
				
				   datas=new ItemFileWriteStore({data:da,
					      getChildren: function(object){
					     
					            return this.query({parent: object.treeId});
					        }
					        
					        });
					      
					       // 实体模型
					       var model=ForestStoreModel(
					       {
					       // 确定根菜单名称，若store定义，这里定义将无效
					       rootLable:"treeName",
					       store:datas,
					       // 确定跟菜单的有何特征
					       query:{isLeaf:"N"},
					       // 确定子元素来源集合
					       childrenAttrs:["children"],
					      
					       // 判断是否为根菜单
					       	mayHaveChildren: function(object){
					       	
											return object.isLeaf=="N";
						
										}	  
					       });
					       return model;
			       
			
		}
		
				
	});	
});	


