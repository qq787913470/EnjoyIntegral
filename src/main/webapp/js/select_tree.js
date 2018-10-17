//用DIV模拟出树形select
function Offset(e) 
{ 
//取标签的绝对位置 
	var t = e.offsetTop; 
	var l = e.offsetLeft; 
	var w = e.offsetWidth-2; 
	var h = e.offsetHeight-2; 
	while(e=e.offsetParent) 
	{ 
		t+=e.offsetTop; 
		l+=e.offsetLeft; 
	} 
	return { 
		top : t, 
		left : l, 
		width : w, 
		height : h 
	};
} 
function initSelect(obj,url){
//取得obj标签的位置。obj:页面上被替代的标签 id；url：jsTree 获得json后台
	var $Obj = $("#"+obj);
	var $Obj_name = $("#"+obj+"_name");
	var offset_text=Offset($Obj.get(0)); //取得text所在的位置 
	$Obj.css("display","none");//隐藏原来的标签
	var $iDiv = $("<div id='selectof"+obj+"'></div>");//模拟一个div替代test
	$iDiv.css("width",offset_text.width+"px");
	$iDiv.css("height",offset_text.height+"px");
	$iDiv.css("z-index","2");
	$iDiv.css("background","no-repeat right 4px");
	$iDiv.css("border","1px solid #7C9BCF");
	$iDiv.css("fontSize","12px");
	$iDiv.css("lineHeight",offset_text.height+"px");
	$iDiv.css("textIndent","4px");
	if($Obj_name.val()==""||$Obj_name.val()==null){
		$iDiv.text("请选择");
	}else{
		$iDiv.text($("#"+obj+"_name").val());
	}
	$Obj.parent().append($iDiv);//将DIV添加到text所在的父元素内
	//鼠标事件
//	$iDiv.mouseover(function(){//鼠标移到 
//		$iDiv.css("background","url(images/icon_select_focus.gif) no-repeat right 4px");
//		});
//	$iDiv.mouseout(function(){//鼠标移走 
//		$iDiv.css("background","url(images/icon_select.gif) no-repeat right 4px");
//		});
	$iDiv.click(function(){
		if($("#selectchild"+obj).length==1){
			//判断是否创建过弹出层div
			if(($("#selectchild"+obj+":hidden").length==1)){
				//定位
				var offset_div = Offset($iDiv.get(0));//取得text元素的新位置
				$("#selectchild"+obj).css("top",offset_div.top+offset_div.height+2+"px");
				$("#selectchild"+obj).css("left",offset_div.left+"px");
				//打开
				$("#selectchild"+obj).css("display","block");
			}else{
				//隐藏
				$("#selectchild"+obj).css("display","none");
			}
		}else{

			//初始一个div放在上一个div下边，当options的替身。
			var $cDiv = $("<div id='selectchild"+obj+"'></div>");
			$cDiv.css("position","absolute");
			$cDiv.css("top",offset_text.top+offset_text.height+2+"px");
			$cDiv.css("left",offset_text.left+"px");
			$cDiv.css("width",offset_text.width+"px");
			$cDiv.css("z-index","3");
			$cDiv.css("background","#f7f7f7");
			$cDiv.css("border","1px solid silver");
			$deptDiv = $("<div id='selectTree'></div>");
			
			$cDiv.append($deptDiv);
			$("body").append($cDiv);
			
			tree = new yadaWight.selectTree({
				dataUrl:url,
				textId:obj
			},'selectTree');
		}
	});
//	$("body").mouseleaver(function(){
//		alert("111");
//		if(($("#selectchild"+obj+":hidden").length!=1)){
//			$("#selectchild"+obj).css("display","none");
//		}
//	});
}