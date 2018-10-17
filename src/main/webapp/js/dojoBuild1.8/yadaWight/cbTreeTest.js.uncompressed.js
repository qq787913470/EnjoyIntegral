require({cache:{
'url:yadaWight/templates/cbtreeNode.html':"<div class=\"dijitTreeNode\" role=\"presentation\">\n\t<div data-dojo-attach-point=\"rowNode\" class=\"dijitTreeRow dijitInline\" role=\"presentation\">\n\t\t<div data-dojo-attach-point=\"indentNode\" class=\"dijitInline\"></div>\n\t\t<img src=\"${_blankGif}\" alt=\"\" data-dojo-attach-point=\"expandoNode\"class=\"dijitTreeExpando\" role=\"presentation\" />\n\t\t<span data-dojo-attach-point=\"expandoNodeText\" class=\"dijitExpandoText\" role=\"presentation\"></span>\n\t\t<span data-dojo-attach-point=\"checkBoxNode\" class=\"cbtreeCheckBox\" role=\"presentation\"></span>\n\t\t<span data-dojo-attach-point=\"contentNode\" class=\"dijitTreeContent\" role=\"presentation\">\n\t\t\t<img src=\"${_blankGif}\" alt=\"\" data-dojo-attach-point=\"iconNode\" class=\"dijitIcon dijitTreeIcon\" role=\"presentation\"/>\n\t\t\t<span data-dojo-attach-point=\"labelNode\" class=\"dijitTreeLabel\" role=\"treeitem\" tabindex=\"-1\" aria-selected=\"false\"></span>\n\t\t</span>\n\t</div>\n\t<div data-dojo-attach-point=\"containerNode\" class=\"dijitTreeContainer\" role=\"presentation\" style=\"display: none;\"></div>\n</div>\n"}});
//
// Copyright (c) 2010-2012, Peter Jekel
// All rights reserved.
//
//	The Checkbox Tree (cbtree), also known as the 'Dijit Tree with Multi State Checkboxes'
//	is released under to following three licenses:
//
//	1 - BSD 2-Clause							 (http://thejekels.com/cbtree/LICENSE)
//	2 - The "New" BSD License			 (http://trac.dojotoolkit.org/browser/dojo/trunk/LICENSE#L13)
//	3 - The Academic Free License	 (http://trac.dojotoolkit.org/browser/dojo/trunk/LICENSE#L43)
//
//	In case of doubt, the BSD 2-Clause license takes precedence.
//
define("yadaWight/cbTreeTest", [
	"dojo/_base/array",
	"dojo/_base/declare",
	"dojo/_base/event",
	"dojo/_base/lang", 
	"dojo/DeferredList",
	"dojo/dom-construct",
	"dojo/text!./templates/cbtreeNode.html",
	"dijit/_Container",
	"dijit/registry",
	"dijit/Tree",
	"dijit/form/CheckBox",
	"cbtree/models/_dndSelector",  // 固定的dijit树问题...
	"require",
	"cbtree/models/ForestStoreModel",
	"dojo/data/ItemFileWriteStore",
	"cbtree/Tree"
], function (array, declare, event, lang, DeferredList, domConstruct, NodeTemplate,_Container, registry, Tree, CheckBox,
		_dndSelector, require,ForestStoreModel,ItemFileWriteStore , cbTree) {

	// module:
	//		cbtree/Tree
	// note:
	//		这个实现是兼容dojo1.8的

	var TreeNode = declare([Tree._TreeNode], {
		// templateString: String
		//		指定要使用的HTML模板.
		templateString: NodeTemplate,

		moduleName: "cbTree/_TreeNode",
		
		//		复选框或custome部件实例.
		_checkBox: null,

		//       表示如果选中widget支持切换功能。
		_toggle: true,
		
        //      指定的窗口小部件被实例化的树节点。默认的是cbtree CheckBox窗口的部件
		_widget: null,
		
		constructor: function (args){
			
			//如果指定了一个自定义的窗口小部件，它是用来代替的默认cbtree“复选框。可选参数被追加到默认的部件参数列表

			var checkBoxWidget = { type: CheckBox, target: 'INPUT', mixin: null, postCreate: null };
			var widgetArgs		 = { multiState: null, checked: undefined, value: 'on' };
			var customWidget	 = args.widget;

			if (customWidget) {
				lang.mixin( widgetArgs, customWidget.args );
				lang.mixin(checkBoxWidget, customWidget);
			}
			checkBoxWidget.args = widgetArgs;
			
			//测试如果widget支持toggle（）方法
			this._toggle = lang.isFunction (checkBoxWidget.type.prototype.toggle);
			this._widget = checkBoxWidget;
		},

		_createCheckBox: function (/*Boolean*/ multiState) {
			// summary:
			//		Create a checkbox on the TreeNode if a checkbox style is specified.
			//      创建一个复选框，如果CheckBox样式指定的TreeNode用指定的样式创建复选框
			// description:
			//		Create a checkbox on the tree node. A checkbox is only created if
			//		the data item has a valid 'checked' attribute OR the model has the
			//		'checkboxAll' attribute enabled.
			//      树节点上创建一个复选框。只创建一个复选框，如果该数据项有一个有效的'checked'属性或该模型具有“checkboxAll属性启用。
			//      
			// multiState:
			//			Indicate of multi state checkboxes are to be used (true/false).
			//          指示要使用多状态复选框(true/false)。
			// tags:
			//		private

			var itemState = this.tree.model.getItemState(this.item);
			var checked   = itemState.checked;
			var enabled   = itemState.enabled;
			var widget	  = this._widget;
			var args	  = widget.args;
			
			if (checked !== undefined) {
				// Initialize the default checkbox/widget attributes.
				// 初始化默认的 checkbox或widget 属性
				args.multiState = multiState;
				args.checked	= checked;
				args.value		= this.label;

				if (lang.isFunction(widget.mixin)) {
					lang.hitch(this, widget.mixin)(args);
				}

				this._checkBox = new widget.type( args );
				if (this._checkBox) {
					if (lang.isFunction(this._widget.postCreate)) {
						lang.hitch(this._checkBox, this._widget.postCreate)(this);
					}
					domConstruct.place(this._checkBox.domNode, this.checkBoxNode, 'replace');
				}
			}
			if (this._checkBox) {
				if (this.isExpandable) {
          if (this.tree.branchReadOnly || !enabled) {
            this._checkBox.set("readOnly", true);
          }
				} else {
          if (this.tree.leafReadOnly || !enabled) {
            this._checkBox.set("readOnly", true);
          }
				}
			}
		},

		_getCheckedAttr: function () {
			// summary:
			//		Get the current checkbox state. This method provides the hook for
			//		get("checked").
			//      获取当前复选框的状态。此方法提供了钩 选（"checked"）
			if (this._checkBox) {
				return this.tree.model.getChecked(this.item);
			}
		},

		_set_checked_Attr: function (newState) {
			// summary:
			//		Set a new state for the tree node checkbox. This method handles the
			//		internal '_checked_' events generated by the model in which case we
			//		only need to update the checkbox.
			//      树节点的复选框，设置一个新的状态。此方法处理由模型生成的事件内部的_checked_在这种情况下，我们只需要更新的复选框。
			//	newState:
			//		The checked state: 'mixed', true or false.
			//      选中的状态：'mixed'，true or false
			// tags:
			//		private
			if (this._checkBox) {
				this._checkBox.set("checked", newState);
			}
		},
		
		_setCheckedAttr: function (/*String|Boolean*/ newState) {
			// summary:
			//		Set a new state for the tree node checkbox. This method implements
			//		the set("checked", newState). These requests are recieved from the
			//		API and therefore we need to inform the model.
			//      树节点的复选框，设置一个新的状态。此方法实现set（'checked'，newState）。按接收这些请求的API,因此我们需要告知的模型。
			//	newState:
			//		The checked state: 'mixed', true or false.
            //	      选中的状态：'mixed'，true or false
			// tags:
			//		private

			if (this._checkBox) {
				return this.tree.model.setChecked(this.item, newState);
			}
		},

		_getEnabledAttr: function () {
			// summary:
			//		Get the current 'enabled' state of the item associated with this
			//		tree node. This method provides the hook for get("enabled").
			//      目前的“启用”状态的树节点与此相关的项目。该方法提供了钩（“启用”）。
			// tag:
			//		Private
			return this.tree.model.getEnabled(this.item);
		},
		
		_set_enabled_Attr: function (enabled) {
			// summary:
			//		Set the 'Read Only' property of the checkbox. This method handles
			//		the internal '_enabled_' event generated by the model after the
			//		store update.
			//      将“只读”属性的复选框。此方法处理 的内部的“_enabled_”事件后的模型 存储更新
			//	enabled:
			//		新启用的状态。
			// tags:
			//		private
			this._checkBox.set("readOnly", !enabled);
		},

		_setEnabledAttr: function (/*Boolean*/ newState) {
			// summary:
			//		Set the new 'enabled' state of the item associated with this tree
			//		node. This method provides the hook for set("enabled", newState).
			//      设置新的“启用”状态树与此相关的项目 节点。这种方法提供了钩set（“已启用”，newState）
			// newState:
			//		Boolean, true or false.
			// tag:
			//		Private.
			return this.tree.model.setEnabled(this.item, newState);
		},
		
		_toggleCheckBox: function (){
			// summary:
			//		Toggle the current checkbox checked attribute and update the model
			//		accordingly. Typically called when the spacebar is pressed. 
			//		If a custom widget does not support toggle() we will just mimic it.
			//      切换当前选中复选框属性，并更新模型相应地。通常的空格键被按下时调用的。如果一个自定义的控件不支持切换（），我们只是模仿它。
			// tags:
			//		private

			var newState, oldState;
			if (this._checkBox) {
				if (this._toggle) {
					newState = this._checkBox.toggle();
				} else {
					oldState = this._checkBox.get("checked");
					newState = (oldState == "mixed" ? true : !oldState);
				}
				this.tree.model.setChecked(this.item, newState);
			}
			return newState;
		},
		
		destroy: function () {
			// summary:
			//		Destroy the checkbox of the tree node widget.
			//
			if (this._checkbox) {
				this._checkbox.destroy();
				delete this._checkbox;
			}
			this.inherited(arguments);
		},

		postCreate: function () {
			// summary:
			//		Handle the creation of the checkbox and node specific icons after
			//		the tree node has been instanciated.
			//      处理复选框和节点特定的图标后创造的树节点已经实例化。
			// description:
			//		Handle the creation of the checkbox after the tree node has been
			//		instanciated. If the item has a custom icon specified, overwrite
			//		the current icon.
			//      处理后的复选框树节点已经创造实例化。如果该项目指定一个自定义的图标，覆盖当前图标。
			//
			var tree	= this.tree,
					itemIcon = null,
					nodeIcon;

			if (tree.checkBoxes === true) {
				this._createCheckBox(tree._multiState);
			}
			// If Tree styling is loaded and the model has its iconAttr set go see if
			// there is a custom icon amongst the item attributes.
			// 如果树的造型被加载的模式有其iconAttr的set去看看 有一个自定义图标之间的项目属性
			if (tree._hasStyling && tree._iconAttr) {
				var itemIcon = tree.get("icon", this.item);
				if (itemIcon) {
					this.set("_icon_",itemIcon);
				}
			}
			// Just in case one is available, set the tooltip.
			this.set("tooltip", this.title);
			this.inherited(arguments);
		},

		setChildItems: function(/* Object[] */ items){
			// summary:
			//		Sets the child items of this node, removing/adding nodes
			//		from current children to match specified items[] array.
			//		Also, if this.persist == true, expands any children that were previously
			//		opened.
			// returns:
			//		Deferred object that fires after all previously opened children
			//		have been expanded again (or fires instantly if there are no such children).

			var tree = this.tree,
				model = tree.model,
				defs = [];	// list of deferreds that need to fire before I am complete

			// Orphan all my existing children.
			// If items contains some of the same items as before then we will reattach them.
			// Don't call this.removeChild() because that will collapse the tree etc.
			var oldChildren = this.getChildren();
			array.forEach(oldChildren, function(child){
				_Container.prototype.removeChild.call(this, child);
			}, this);

			// All the old children of this TreeNode are subject for destruction if
			//		1) they aren't listed in the new children array (items)
			//		2) they aren't immediately adopted by another node (DnD)
			this.defer(function(){
				array.forEach(oldChildren, function(node){
					if(!node._destroyed && !node.getParent()){
						// If node is in selection then remove it.
						tree.dndController.removeTreeNode(node);

						// Deregister mapping from item id --> this node
						var id = model.getIdentity(node.item),
							ary = tree._itemNodesMap[id];
						if(ary.length == 1){
							delete tree._itemNodesMap[id];
						}else{
							var index = array.indexOf(ary, node);
							if(index != -1){
								ary.splice(index, 1);
							}
						}
						// And finally we can destroy the node
						node.destroyRecursive();
					}
				});
			});

			this.state = "LOADED";

			if(items && items.length > 0){
				this.isExpandable = true;
				// Create _TreeNode widget for each specified tree node, unless one already
				// exists and isn't being used (presumably it's from a DnD move and was recently
				// released
				array.forEach(items, function(item){	// MARKER: REUSE NODE
					var id = model.getIdentity(item),
						existingNodes = tree._itemNodesMap[id],
						node;
					if(existingNodes){
						for(var i=0;i<existingNodes.length;i++){
							// FIX 1 - Don't re-used destroyed nodes, instead clean them up.
							if (!existingNodes[i] || existingNodes[i]._beingDestroyed) {
								existingNodes.splice(i,1);
								if (existingNodes.length == 0) {
									delete tree._itemNodesMap[id];
								}
							} else {
								if(!existingNodes[i].getParent()) {
									node = existingNodes[i];
									node.set('indent', this.indent+1);
									break;
								}
							}
						}
					}
					if(!node){
						node = this.tree._createTreeNode({
							item: item,
							tree: tree,
							isExpandable: model.mayHaveChildren(item),
							label: tree.getLabel(item),
							tooltip: tree.getTooltip(item),
							ownerDocument: tree.ownerDocument,
							dir: tree.dir,
							lang: tree.lang,
							textDir: tree.textDir,
							indent: this.indent + 1
						});
						if(existingNodes){
							existingNodes.push(node);
						}else{
							tree._itemNodesMap[id] = [node];
						}
					}
					this.addChild(node);

					// If node was previously opened then open it again now (this may trigger
					// more data store accesses, recursively)
					if(this.tree.autoExpand || this.tree._state(node)){
						defs.push(tree._expandNode(node));
					}
				}, this);

				// note that updateLayout() needs to be called on each child after
				// _all_ the children exist
				array.forEach(this.getChildren(), function(child){
					child._updateLayout();
				});
			}else{
				// FIX 2 - If no children, delete _expandNodeDeferred if any...
				tree._collapseNode(this);
				this.isExpandable=false;
			}

			if(this._setExpando){
				// change expando to/from dot or + icon, as appropriate
				this._setExpando(false);
			}

			// Set leaf icon or folder icon, as appropriate
			this._updateItemClasses(this.item);

			// On initial tree show, make the selected TreeNode as either the root node of the tree,
			// or the first child, if the root node is hidden
			if(this == tree.rootNode){
				var fc = this.tree.showRoot ? this : this.getChildren()[0];
				if(fc){
					fc.setFocusable(true);
					tree.lastFocused = fc;
				}else{
					// fallback: no nodes in tree so focus on Tree <div> itself
					tree.domNode.setAttribute("tabIndex", "0");
				}
			}

			var def =  new DeferredList(defs);
			this.tree._startPaint(def);		// to reset TreeNode widths after an item is added/removed from the Tree
			return def;		// dojo/_base/Deferred
		}

	});	/* end declare() _TreeNode*/

	return declare('dojoJS.cbTreeTest',[Tree], {
		
		//是否用cookies保存节点状态
		persist : false,
		
		//private
		checkBoxes: true,
		_eventAttrMap: null,
		_checkedAttr: "",
		_customWidget: null,
		
		constructor:function(a){
			this.model=this.initData(a.url)
		},
		
		//private
		initData:function(url){
			
			var resuleValue=null;
			 dojo.xhrPost({
		                url: url,
		                sync: true,
                        handle:function(data){
		                        resuleValue=data;
		                        }
			 });   
		     
			var da ={
			 "identifier":"treeId",
			 "label":"treeName"
			}
			
			da.items = eval("("+ resuleValue +")");
			for(var i=0;i<da.items.length;i++){
			  if(da.items[i].isLeaf=='N'){
				  da.items[i].children=new Array();
			  }
			}
			
			
		   datas = new ItemFileWriteStore({data:da,
			      		getChildren: function(object){
			      			return this.query({parent: object.treeId});
			      		}
			        });
			      
			       //实体模型
			       var model=ForestStoreModel(
			       {
			       //确定根菜单名称，若store定义，这里定义将无效
			       rootLable:"treeName",
			       store:datas,
			       //确定跟菜单的有何特征
			       query:{isLeaf:"N"},
			       //确定子元素来源集合
			       childrenAttrs:["children"],
			      
			       //判断是否为根菜单
			       	mayHaveChildren: function(object){
									return object.isLeaf=="N";
								}	  
			       });
			       return model;
		},
		
		onOpen : function(node) {
			this.addNode(this, 'open', node);
		},
		
		//private
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
				if (tn.isLeaf != undefined && tn.isLeaf != 'Y'	&& tn.children == '') {
					isOpen = true;
					var returnData;

					var url = tn.url;
					var parent = {
						parent : tn,
						attribute : 'children'
					}
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
						// alert(returnData[i].treeName)
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
		
		//获取所有的被选中的节点值
		getSelectedCheckBox:function(){
			var menuTree = new Array();
		
		    var menuTreeStore = this.model.store;
            for (var i = 0; i < menuTreeStore._arrayOfAllItems.length; i++) {
                var a = menuTreeStore._arrayOfAllItems[i].checked;
                if (a == "true" || a == 'mixed') {
                    menuTree.push(menuTreeStore._arrayOfAllItems[i].treeId);
                }
            }
			return menuTree;
		},
		
		createOneHiddenInputForSubmit:function(nodeName){
			var menuTree = new Array();
			
			var menuTreeStore = this.model.store;
			for (var i = 0; i < menuTreeStore._arrayOfAllItems.length; i++) {
				var a = menuTreeStore._arrayOfAllItems[i].checked;
				if (a == "true" || a == 'mixed') {
					menuTree.push(menuTreeStore._arrayOfAllItems[i].treeId);
				}
			}
			dojo.create("input", {id:nodeName,name:nodeName,type: "hidden"}, this.id);
			dojo.byId(nodeName).value = menuTree;
			return menuTree;
		},
		
		createAnyHiddenInputForSubmit:function(nodeName){
			var menuTree = new Array();
			
			var menuTreeStore = this.model.store;
			for (var i = 0; i < menuTreeStore._arrayOfAllItems.length; i++) {
				var a = menuTreeStore._arrayOfAllItems[i].checked;
				if (a == "true" || a == 'mixed') {
					var temp = dojo.create("input", {id:nodeName,name:nodeName,type: "hidden"}, this.id);
					temp.value = menuTreeStore._arrayOfAllItems[i].treeId;
				}
			}
			return menuTree;
		},
		//===================事件开始==================
		_onCheckBoxClick: function (/*TreeNode*/ nodeWidget, /*Boolean|String*/ newState, /*Event*/ evt) {
			// summary:
			//		Translates checkbox click events into commands for the controller
			//		to process.
			// description:
			//		the _onCheckBoxClick function is called whenever a mouse 'click'
			//		on a checkbox is detected. Because the click was on the checkbox
			//		we are not dealing with any node expansion or collapsing here.
			// tags:
			//		private

			var item = nodeWidget.item;
				
			this._publish("checkbox", { item: item, node: nodeWidget, state: newState, evt: evt});
			// Generate events incase any listeners are tuned in...
			this.onCheckBoxClick(item, nodeWidget, evt);
			this.onClick(nodeWidget.item, nodeWidget, evt);
			this.focusNode(nodeWidget);
			event.stop(evt);
		},

		_onClick: function(/*TreeNode*/ nodeWidget, /*Event*/ evt){
			// summary:
			//		Handler for onclick event on a tree node
			// description:
			//		If the click event occured on a checkbox, get the new checkbox checked
			//		state, update the model and generate the checkbox click related events
			//		otherwise pass the event on to the tree as a regular click event.
			// evt:
			//		Event object.
			// tags:
			//		private extension
			var checkedWidget = nodeWidget._widget;

			if (evt.target.nodeName == checkedWidget.target) {
				var newState = nodeWidget._checkBox.get("checked");				
				this.model.setChecked(nodeWidget.item, newState);
				this._onCheckBoxClick(nodeWidget, newState, evt);
			} else {
				this.inherited(arguments);
			}
		},
		
		_onLabelChange: function (/*String*/ oldValue, /*String*/ newValue) {
			// summary:
			//		Handler called when the model changed its label attribute property.
			//		Map the new label attribute to "label"
			// tags:
			//		private

			this.mapEventToAttr(oldValue, newValue, "label");
		},
		
		//===================事件结束==================
		postMixInProperties: function(){
			this._eventAttrMap = {};		/* Create event mapping object */

			this.inherited(arguments);
		},
		
		postCreate: function () {
			// summary:
			//		Handle any specifics related to the tree and model after the
			//		instanciation of the Tree. 
			// description:
			//		Whenever checkboxes are requested Validate if we have a model
			//		capable of updating item attributes.
			var model = this.model;

			if (this.model) {
				if (this.checkBoxes === true) {
					if (!this._modelOk()) {
						throw new Error(this.moduleName+"::postCreate(): model does not support getChecked() and/or setChecked().");
					}
					this._multiState 	= model.multiState;
					this._checkedAttr = model.checkedAttr;

					// Add item attributes and other attributes of interest to the mapping
					// table. Checkbox checked events from the model are mapped to the 
					// internal '_checked_' event so a Tree node is able to distinguesh
					// between events coming from the model and those coming from the API
					// like set("checked",true)
					
					this.mapEventToAttr(null,(this._checkedAttr || "checked"), "_checked_");
					model.validateData();
				}
				// Monitor any changes to the models label attribute and add the current
				// 'label' and 'enabled' attribute to the mapping table.
				this.connect(model, "onLabelChange", "_onLabelChange");

				this.mapEventToAttr(null, model.get("enabledAttr"), "_enabled_");
				this.mapEventToAttr(null, model.get("labelAttr"), "label");

				this.inherited(arguments);
			} 
			else // The CheckBox Tree requires a model.
			{
				throw new Error(this.moduleName+"::postCreate(): no model was specified.");
			}
		},
		mapEventToAttr: function (/*String*/ oldAttr, /*String*/ attr, /*String*/ nodeAttr, /*anything?*/ value) {
			// summary:
			//		Add an event mapping to the mapping table.
			//description:
			//		Any event, triggered by the model or some other extension, can be
			//		mapped to a _TreeNode attribute resulting a 'set' request for the
			//		associated _TreeNode attribute.
			// oldAttr:
			//		Original attribute name. If present in the mapping table it is deleted
			//		and replace with 'attr'.
			// attr:
			//		Attribute/event name that needs mapping.
			// nodeAttr:
			//		Name of a _TreeNode attribute to which 'attr' is mapped.
			// value:
			//		If specified the value to be assigned to the _TreeNode attribute. If
			//		value is a function the function is called as: 
			//
			//			function(item, nodeAttr, newValue)
			//
			//		and the result returned is assigned to the _TreeNode attribute.
			
			if (lang.isString(attr) && lang.isString(nodeAttr)) {
				if (attr.length && nodeAttr.length) {
					if (oldAttr) {
						delete this._eventAttrMap[oldAttr];
					}
					this._eventAttrMap[attr] = {attribute: nodeAttr, value: value};
				}
			}
		},
		_modelOk: function () {
			// summary:
			//		Test if the model has the minimum required feature set, that is,
			//		model.getChecked() and model.setChecked().
			// tags:
			//		private

			if ((this.model.getChecked && lang.isFunction( this.model.getChecked )) &&
					(this.model.setChecked && lang.isFunction( this.model.setChecked ))) {
				return true;
			}
			return false;
		},
		_createTreeNode: function (args) {
			// summary:
			//		Create a new cbtreeTreeNode instance.
			// description:
			//		Create a new cbtreeTreeNode instance.
			// tags:
			//		private

			args["widget"] = this._customWidget;		/* Mixin the custom widget */
			if (this._hasStyling && this._icon) {
				args["icon"] = this._icon;
			}
			return new TreeNode(args);
		}
				
	});	/* end declare() Tree */
	Tree._TreeNode = TreeNode;
});	/* end define() */
