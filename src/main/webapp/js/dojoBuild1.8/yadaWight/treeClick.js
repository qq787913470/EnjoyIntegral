//>>built
define("yadaWight/treeClick",["./YDLazyTree","dojo/_base/declare","dijit/Tree","dijit/registry"],function(_1,_2,_3,_4){var _5=_2([_3._TreeNode],{});return _2("yadaWight.treeClick",[_1],{tourl:null,reftourl:null,ids:null,treeId:null,frame:null,treeName:null,treeParent:null,constructor:function(a){this.reftourl=a.tourl;},onClick:function(){this.tourl=this.reftourl;var _6=this.selectedItems[0];var _7=this.ids;for(var i=0;i<_7.length;i++){var _8=dojo.byId(_7[i]).value;var _9=dojo.byId(_7[i]).name;this.tourl=this.tourl+_9+"="+_8+"&";}if(this.treeId!=null&&this.treeId!=""){this.tourl=this.tourl+this.treeId+"="+_6["treeId"]+"&";}if(this.treeName!=null&&this.treeName!=""){this.tourl=this.tourl+this.treeName+"="+_6["treeName"]+"&";}if(this.treeParent!=null&&this.treeParent!=""){this.tourl=this.tourl+this.treeParent+"="+_6["treeParentId"];}_4.byId(this.frame).set("href",this.tourl);}});});