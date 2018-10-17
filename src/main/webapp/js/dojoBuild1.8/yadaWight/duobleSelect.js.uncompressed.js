require({cache:{
'url:yadaWight/templates/duobleSelect.html':"<div class=\"${baseClass}\" >\n\t<table>\n\t<tr>\n\t\t<td >\n\t\t<input type=\"hidden\" id=\"leftSelect\" name=\"leftSelect\"/>\n\t\t\t<select style=\"width: 300px;height: 300px\" id=\"leftContainerSelect\" class=\"${baseClass}Select\" multiple='true'  data-dojo-attach-point='leftFocusNode' data-dojo-attach-event='onchange: _onChange'></select>\n\t\t</td>\n\t\t<td id=\"center\" class=\"${baseClass}TDImage\" >\n\t\t\t<div   data-dojo-attach-point=\"centerButton_allLeftMove\" data-dojo-attach-event=\"ondijitclick:_onClick_toRightAll\"><img style=\"width: 30px;height: 30px\" class=\"${baseClass}Image\"  src=\"${spore}/dojo1.8/yadaWight/image/toRightAll.png\"  ></div>\n\t\t\t<div class=\"${baseClass}ImageDiv\"  data-dojo-attach-point=\"centerButton_leftMove\" data-dojo-attach-event=\"ondijitclick:_onClick_toRight\"><img style=\"width: 30px;height: 30px;margin-top: 30px\" class=\"${baseClass}Image\"  src=\"${spore}/dojo1.8/yadaWight/image/toRight.png\"  ></div>\n\t\t\t<div class=\"${baseClass}ImageDiv\"  data-dojo-attach-point=\"centerButton_rightMove\" data-dojo-attach-event=\"ondijitclick:_onClick_toLeft\"><img style=\"width: 30px;height: 30px;margin-top: 30px\" class=\"${baseClass}Image\" src=\"${spore}/dojo1.8/yadaWight/image/toLeft.png\"  ></div>\n\t\t\t<div class=\"${baseClass}ImageDiv\"  data-dojo-attach-point=\"centerButton_allRightMove\" data-dojo-attach-event=\"ondijitclick:_onClick_toLeftAll\"><img style=\"width: 30px;height: 30px;margin-top: 30px\" class=\"${baseClass}Image\" src=\"${spore}/dojo1.8/yadaWight/image/toLeftAll.png\"    ></div>\n\t\t</td>\n\t\t<td >\n\t\t<input type=\"hidden\" name=\"rightSelect\" id=\"rightSelect\"/>\n\t\t\t<select style=\"width: 300px;height: 300px\"  id=\"rightContainerSelect\" class=\"${baseClass}Select\"  multiple='true'  data-dojo-attach-point='rightFocusNode' data-dojo-attach-event='onchange: _onChange'></select>\n\t\t</td>\n\t\t</tr>\n\t</table>\n\t<div data-dojo-attach-point='containerNode' ></div>\n</div>\n"}});
define(
"yadaWight/duobleSelect", [ "dojo/_base/declare",
  "dijit/_WidgetBase",
  "dijit/_OnDijitClickMixin",
  "dijit/_TemplatedMixin",
  "dijit/_WidgetsInTemplateMixin",
  "dijit/form/Button", "dojo/query",
  "dojo/_base/lang",
  "dojo/text!./templates/duobleSelect.html" 
], function(declare,_WidgetBase, _OnDijitClickMixin, _TemplatedMixin,	_WidgetsInTemplateMixin, Button, query,lang, template) {

	return declare("yadaWight.duobleSelect", [ _WidgetBase, _OnDijitClickMixin,	_TemplatedMixin, _WidgetsInTemplateMixin ], {
		
		templateString : template, // 标注我们的目标模板文件
		
		baseClass : "yadaWightDuobleSelect",
		
		leftSelectData : null,

		rightSelectData : null,

		//select value的默认属性
	    val:'id',
	    
	    //select label的默认属性
        text:'name',
		
		constructor : function(args) {
			this.leftSelectData = args.leftdata;
			this.rightSelectData = args.rightdata;
		},

		postCreate : function() {

			var left = this.leftFocusNode;
			var right = this.rightFocusNode;
            var thiz=this;
			query("select[id='left']>option", this.containerNode).forEach(
					function(n) {
						var opt = thiz.getNewOption(n);
						left.options.add(opt);
					});
			dojo.destroy('left');
			query("select[id='right']>option", this.containerNode).forEach(
					function(n) {
						var opt = thiz.getNewOption(n);
						right.options.add(opt);

					});
			dojo.destroy('right');
			
			var leftListJson;
			var rightListJson;

			if (this.leftSelectData != null && this.leftSelectData != '') {
				leftListJson = eval("(" + this.leftSelectData + ")");
				this.initOption(leftListJson, left);
			};
			if (this.rightSelectData != null && this.rightSelectData != '') {
				rightListJson = eval("(" + this.rightSelectData + ")");
				this.initOption(rightListJson, right);
			};

			this.inherited(arguments);
		},
		
		//左侧字符串取值
		createStringLeftDoubleOptionHidden:function(leftName)
		{
			try {
				leftName.value;
			} catch (e) {
				throw new Error("参数不能为空");
			}
			this.getStringDoubleOption(leftName,'');
			
		},
		//右侧字符串取值
		createStringRightDoubleOptionHidden:function(rightName)
		{
			try {
				rightName.value;
			} catch (e) {
				throw new Error("参数不能为空");
			}
			this.getStringDoubleOption('',rightName);
			
		},
		//左侧数组取值
		createArrayLeftDoubleOptionHidden:function(leftName)
		{
				try {
					leftName.value;
				} catch (e) {
					throw new Error("参数不能为空");
				}
				this.getArrayDoubleOption(leftName,'');
				
		},
		//右侧数组取值
		createArrayRightDoubleOptionHidden:function(rightName)
		{
			try {
				rightName.value;
			} catch (e) {
				throw new Error("参数不能为空");
			}
			this.getArrayDoubleOption('',rightName);
				
		},
		
		
		
		// 将左右两侧option取出两个隐藏域中
		createStringDoubleOptionHidden: function(leftName, rightName) {

			try {
				rightName.value;
			} catch (e) {
				throw new Error("参数应为两个");
			}


			if (leftName != '') {
				var left = this.leftFocusNode;
				var leftArray = new Array();
				query("option", left).forEach(function(n) {
					leftArray.push(n.value);
				});
				dojo.create("input", {
					id   : leftName,
					name : leftName,
					type : "hidden"
				}, left.id);
				dojo.byId(leftName).value = leftArray;
			}

			if (rightName != '') {
				var right = this.rightFocusNode;
				var rightArray = new Array();
				query("option", right).forEach(function(n) {
					rightArray.push(n.value);
				});
				dojo.create("input", {
					name : rightName,
					id : rightName,
					type : "hidden"
				}, right.id);
				dojo.byId(rightName).value = rightArray;
			}
		},
		
		// 将左右两侧option取出多个隐藏域中
		createArrayDoubleOptionHidden: function(leftName, rightName) {
			try {
				rightName.value;
			} catch (e) {
				throw new Error("不能为一个参数");
			}


			if (leftName != "") {
				var left = this.leftFocusNode;
				query("option", left).forEach(function(n) {
					dojo.create("input", {
						name : leftName,
						value : n.value,
						type : "hidden"
					}, left.id);

				});
			}
			if (rightName != "") {
				var right = this.rightFocusNode;
				query("option", right).forEach(function(n) {
					dojo.create("input", {
						name : rightName,
						value : n.value,
						type : "hidden"
					}, right.id);
				});
			}

		},
		
		_onClick_toRightAll : function() {
			var thiz=this;
			var leftOptions = this.leftFocusNode.options;

			for ( var i = 0; i < leftOptions.length;) {
				var temp = leftOptions[0];
				var opt = thiz.getNewOption(temp);
				this.rightFocusNode.options.add(opt);
				leftOptions.remove(0);
			}
		},

		_onClick_toRight : function() {
			var thiz=this;
			var leftOptions = this.leftFocusNode.options;
			var rightOption = this.rightFocusNode;
			for ( var i = 0; i < leftOptions.length; i++) {
				var selected = leftOptions[i].selected;
				if (selected) {
					var opt = thiz.getNewOption(leftOptions[i]);
					rightOption.options.add(opt);
					leftOptions.remove(i);
					i--;
				}
			}
		},

		_onClick_toLeft : function() {
			var thiz=this;
			var rightOptions = this.rightFocusNode.options;
			var leftOptions = this.leftFocusNode;
			for ( var i = 0; i < rightOptions.length; i++) {
				var selected = rightOptions[i].selected;
				if (selected) {
					var opt = thiz.getNewOption(rightOptions[i]);
					leftOptions.options.add(opt);
					rightOptions.remove(i);
					i--;
				}
			}
		},

		_onClick_toLeftAll : function() {
			var rightOptions = this.rightFocusNode.options;
			var thiz=this;
			for ( var i = 0; i < rightOptions.length;) {
				var temp = rightOptions[0];
				var opt = thiz.getNewOption(temp);
				this.leftFocusNode.options.add(opt);
				rightOptions.remove(0);
			}
		},

		_onChange : function() {

		},

		initOption : function(items, focsNode) {
			 var thiz=this;
			query(focsNode).forEach(function(n) {
				for ( var i = 0; i < items.length; i++) {
					var opt = document.createElement("OPTION");
					n.options.add(opt);
					opt.value = items[i][thiz.val];
					opt.text = items[i][thiz.text];
				}
			});
		},
		
		getNewOption:function(node)
		{
			var opt = document.createElement("OPTION");
			opt.value = node.value;
			opt.text = node.text;
			return opt;
		}

	});
});
