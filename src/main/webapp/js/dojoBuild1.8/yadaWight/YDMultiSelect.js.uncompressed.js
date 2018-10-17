require({cache:{
'url:yadaWight/templates/MultiSelect.html':"<table style=\"border: 1px\">\r\n\r\n<tr>\r\n  <td>\r\n\t<select multiple='true'\r\n\t\t${!nameAttrSetting} data-dojo-attach-point='containerNode,focusNode'\r\n\t\tdata-dojo-attach-event='onchange: _onChange' style=\"${style}\">\r\n\t</select>\r\n  </td>\r\n  <td style=\"padding-left: 20px;padding-right: 20px\">\r\n\t\r\n\t<div data-dojo-attach-event=\"ondijitclick:UpTop\"><img style=\"width: 25px;height: 25px\" src=\"${spore}/yadaWight/image/upTop.png\"></div>\r\n\t<div style=\"margin-top:  20%; \" data-dojo-attach-event=\"ondijitclick:Up\"><img style=\"width: 25px;height: 25px\" src=\"${spore}/yadaWight/image/up.png\"></div>\r\n\t<div style=\"margin-top:  20%; \" data-dojo-attach-event=\"ondijitclick:Down\"><img style=\"width: 25px;height: 25px\" src=\"${spore}/yadaWight/image/down.png\"></div>\r\n\t<div style=\"margin-top:  20%; \" data-dojo-attach-event=\"ondijitclick:DownEnd\"><img style=\"width: 25px;height: 25px\" src=\"${spore}/yadaWight/image/downEnd.png\"></div>\r\n  </td>\r\n</tr>\r\n</table>"}});
define(
"yadaWight/YDMultiSelect", ["dojo/_base/declare",
 "dojo/query",
 "dijit/form/_FormValueWidget",
 "dojo/text!./templates/MultiSelect.html" 
],	function(declare, query, _FormValueWidget, template) {

			// module:
			// dijit/form/MultiSelect

			return declare("yadaWight.YDMultiSelect",[ _FormValueWidget ],{

						size : 7,

						templateString : template,
						
						multiple : true, 
						
						baseClass : "yadaWightMultiSelect",
						
						//el字符串
						elList : null,
						//定义option中value是json中什么字段,默认值是id
                        val:'id',
                        //定义option中text是json中什么字段,默认值是name
                        text:'name',
						
						//将所有option全部设置为不选中
						notSelectedAllOption: function(){
							query("option",this.containerNode).forEach(function(n){
								n.selected=false;
							});
						},
						
						//取得select中的字符串对象
						createStringOptionHidden: function(nodeName){
							var optionArray=new Array();
							var containerNode=this.containerNode;
							query("option",containerNode).forEach(function(n){
								optionArray.push(n.value)
							});
							dojo.create("input", {name:nodeName,value:optionArray,type: "hidden"}, containerNode.id);
							
							
						},
						
						//将所有option删除
						YDchearOption : function() {
							query(this.containerNode).forEach(function(n) {
								this.containerNode.remove(n);
							});
						},
						
						//根据el添加option
						YDaddOption : function(items,val,text) {
							if (items != null) {
							  if (items.length != 0) {
								  query(this.containerNode).forEach(
									  function(n) {
										for ( var i = 0; i < items.length; i++) {
											var opt = document.createElement("OPTION");
											opt.value = items[i][val];
											opt.text = items[i][text];
											n.options.add(opt);
											}
									});

								}
							}

						},

						_onChange : function(/* Event */) {
						},

						constructor : function(a) {
							if (a.listdata != null && a.listdata != "") {
								this.elList = eval("(" + a.listdata + ")");
							}else
							{
								console.error("listData为空");
							}
						},

						postCreate : function() {
							this.YDaddOption(this.elList,this.val,this.text);
							this.notSelectedAllOption();
							this.inherited(arguments);
						},

						Up : function() {
							var sel = this.containerNode;
							var nIndex = sel.selectedIndex;
							if (nIndex != 0) {
								var sValue = sel.options[nIndex].value;
								var sHTML = sel.options[nIndex].innerHTML;
								sel.options[nIndex].value = sel.options[nIndex - 1].value;
								sel.options[nIndex].innerHTML = sel.options[nIndex - 1].innerHTML;
								sel.options[nIndex - 1].value = sValue;
								sel.options[nIndex - 1].innerHTML = sHTML;
								sel.selectedIndex = nIndex - 1;
							}
						},

						Down : function() {
							var sel = this.containerNode;
							var nIndex = sel.selectedIndex;
							var nlen = sel.options.length;
							if (nIndex + 1 != nlen) {
								var sValue = sel.options[nIndex].value;
								var sHTML = sel.options[nIndex].innerHTML;
								sel.options[nIndex].value = sel.options[nIndex + 1].value;
								sel.options[nIndex].innerHTML = sel.options[nIndex + 1].innerHTML;
								sel.options[nIndex + 1].value = sValue;
								sel.options[nIndex + 1].innerHTML = sHTML;
								sel.selectedIndex = nIndex + 1;
							}
						},
						
						UpTop : function() {
							var sel = this.containerNode;
							var nIndex = sel.selectedIndex;
							var sValue = sel.options[nIndex].value;
							var sHTML = sel.options[nIndex].innerHTML;
							for ( var i = nIndex; i > 0; i--) {
								sel.options[i].value = sel.options[i - 1].value;
								sel.options[i].innerHTML = sel.options[i - 1].innerHTML;
							}
							sel.options[0].value = sValue;
							sel.options[0].innerHTML = sHTML;
							sel.selectedIndex = 0;
						},
						
						DownEnd : function() {
							var sel = this.containerNode;
							var nLen = sel.length;
							var nIndex = sel.selectedIndex;
							var sValue = sel.options[nIndex].value;
							var sHTML = sel.options[nIndex].innerHTML;
							for ( var i = nIndex; i < nLen - 1; i++) {
								sel.options[i].value = sel.options[i + 1].value;
								sel.options[i].innerHTML = sel.options[i + 1].innerHTML;
							}
							sel.options[nLen - 1].value = sValue;
							sel.options[nLen - 1].innerHTML = sHTML;
							sel.selectedIndex = nLen - 1;
						},
						
                        
                        //将所有option全部选中
                        selectedAllOption:function(){
                        	query("option",this.containerNode).forEach(function (n){
                        		    n.selected=true;
                        	});
                        }
						
					});

		});
