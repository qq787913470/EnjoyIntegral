//>>built
define("yadaWight/YDLazyTree",["dijit/Tree","require","dojo/_base/declare","dijit/tree/ForestStoreModel","dojo/data/ItemFileWriteStore"],function(_1,_2,_3,_4,_5){var _6=_3([_1._TreeNode],{});return _3("yadaWight.YDLazyTree",[_1],{persist:false,showRoot:false,dataUrl:null,constructor:function(a){this.model=this.initData(a.dataUrl);},onOpen:function(_7){this.addNode(this,"open",_7);},addNode:function(_8,e,_9){var _a=dijit.byId(_8.id);var _b=false;if(_8.selectedItems!=null){var tn;var a;if(e!="open"){a=_a.getNodesByItem(_8.selectedItems[0])[0];tn=_8.selectedItems[0];}else{var xx=_a.getNodesByItem(_9)[0];tn=xx.item;a=xx;}if(tn.isLeaf!=undefined&&tn.isLeaf!="Y"&&tn.children==""){_b=true;var _c;var _d=new Date().getTime();var _e=this.dataUrl+"&pTreeId="+tn.treeId+"&timesTamp="+_d;var _f={parent:tn,attribute:"children"};dojo.xhrGet({url:_e,sync:true,handle:function(_10){_c=eval("("+_10+")");}});for(var i=0;i<_c.length;i++){_c[i].children=new Array();_a.model.store.newItem(_c[i],_f);}}if(!_b){if(e!="open"){if(a.isExpanded){a.collapse();}else{a.expand();}}}else{a.expand();}}},initData:function(url){var _11=null;dojo.xhrPost({url:url,sync:true,handle:function(_12){_11=_12;}});var da={"identifier":"treeId","label":"treeName"};da.items=eval("("+_11+")");for(var i=0;i<da.items.length;i++){if(da.items[i].isLeaf=="N"){da.items[i].children=new Array();}}datas=new _5({data:da,getChildren:function(_13){return this.query({parent:_13.treeId});}});var _14=_4({rootLable:"treeName",store:datas,query:{isLeaf:"N"},childrenAttrs:["children"],mayHaveChildren:function(_15){return _15.isLeaf=="N";}});return _14;}});});