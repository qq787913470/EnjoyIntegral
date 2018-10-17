/*
author:sujintian
*/
/****************ShuiDrinking界面提示控件***********************************/
var Dialog={};
Dialog.initFlag=false;
Dialog.InfoDIV=document.createElement("div");
Dialog.MaskDIV=document.createElement("div");
/**
        * 提示框类型：
        * 0-为普通提示信息
        * 1-为普通等待处理
        * 2-为异常信息
**/
Dialog.InfoStrHtml="";
Dialog.Width=400;
Dialog.Height=150;
Dialog.Top="30%";
Dialog.Left="30%";

Dialog.Title="提示信息:";
Dialog.Message="正在处理，请您稍候";

//该方法只是确保当页面完成后加载，否则document.body不存在
Dialog.init=function(){
	document.body.appendChild(Dialog.InfoDIV);
	document.body.appendChild(Dialog.MaskDIV);

	//设置两个div的显示属性
	Dialog.InfoDIV.style.position = "absolute";
	Dialog.InfoDIV.style.width = Dialog.Width;
	Dialog.InfoDIV.style.height = Dialog.Height;
	Dialog.InfoDIV.style.top = Dialog.Top;
	Dialog.InfoDIV.style.left = Dialog.Left;
	
	Dialog.InfoDIV.style.zIndex =10001;

	Dialog.MaskDIV.style.position = "absolute";
	Dialog.MaskDIV.style.top=0;
	Dialog.MaskDIV.style.left=0;
	Dialog.MaskDIV.style.width =document.body.scrollWidth;
	Dialog.MaskDIV.style.height =document.body.scrollHeight;
	Dialog.MaskDIV.style.zIndex =10000;
	Dialog.MaskDIV.style.background="#ffffff";
	Dialog.MaskDIV.style.filter ="Alpha(opacity=60)";
	Dialog.MaskDIV.innerHTML = "<iframe src='javascript:false' style=\"position:absolute; top:0px; left:0px; width:100%; height:"+document.body.scrollWidth+"; z-index:-1; filter='Alpha(opacity=0);'\"></iframe>";
	Dialog.initFlag=true;//初始化完
}
Dialog.show=function(){
        	if(!Dialog.initFlag){
        		Dialog.init();//全局只初始化一次
        	}
                if(Dialog.InfoType < 0 || Dialog.InfoType > 2){
                        alert("指定的提示信息类别错误");
                        return;
                }
                Dialog.MaskDIV.style.width =document.body.scrollWidth;
		Dialog.MaskDIV.style.height =document.body.scrollHeight;
		Dialog.InfoStrHtml = "<table margin=0px border=0px cellspacing=0 cellpadding=0 width=400px style='word-break:break-all'>";

                if(Dialog.InfoType==0){
			Dialog.InfoStrHtml += " <tr><td style=\"background:#5AB3E4;text-align:left;padding-left:20px;font-size:14px;font-weight:bold;height:25px;border:1px solid #5AB3E4;\">[系统提示]</td></tr>";
         		Dialog.InfoStrHtml += " <tr><td style=\"background:#fff;text-align:center;valign:middle;font-size:12px;height:120px;border-left:1px solid #5AB3E4;border-right:1px solid #5AB3E4;\">"+Dialog.Message+"</td></tr>";
         		Dialog.InfoStrHtml += " <tr><td style=\"background:#5AB3E4;text-align:center;font-weight:bold;height:25px;line-height:25px; border:1px solid #5AB3E4;\"><input type=\"button\" class=action value=\"确 定\" onclick=\"Dialog.close()\" /></td></tr>";
        		/*"<image src="+Configer.FrameSourcePath+"/images/comment.gif>&nbsp;&nbsp提示信息:<hr size='1' color='#f3f3f3'>";*/
               	}
        	if(Dialog.InfoType==1){
        		Dialog.InfoStrHtml += " <tr><td style=\"background:#5AB3E4;text-align:left;padding-left:20px;font-size:14px;font-weight:bold;height:25px;border:1px solid #5AB3E4;\">[系统提示]</td></tr>";
         		Dialog.InfoStrHtml += " <tr><td style=\"background:#fff;text-align:center;vertical-align:middle;font-size:12px;height:120px;border-left:1px solid #5AB3E4;border-right:1px solid #5AB3E4;\">"+Dialog.Message+"</td></tr>";
         		Dialog.InfoStrHtml += " <tr><td style=\"background:#5AB3E4;text-align:center;font-weight:bold;height:25px;border:1px solid #5AB3E4;\">&nbsp;</td></tr>";
        		/*"<image src="+Configer.FrameSourcePath+"/images/loading.gif>&nbsp;&nbsp;";*/
               	}
        	if(Dialog.InfoType==2){
        		Dialog.InfoStrHtml += " <tr><td style=\"background:#5AB3E4;text-align:left;padding-left:20px;font-color:#ff0000;font-size:14px;font-weight:bold;height:25px;border:1px solid #5AB3E4;\">[系统错误]</td></tr>";
         		Dialog.InfoStrHtml += " <tr><td style=\"background:#fff;text-align:center;valign:middle;font-size:12px;height:120px;border-left:1px solid #5AB3E4;border-right:1px solid #5AB3E4;\">"+Dialog.Message+"</td></tr>";
         		Dialog.InfoStrHtml += " <tr><td style=\"background:#5AB3E4;text-align:center;font-weight:bold;height:25px; border:1px solid #5AB3E4;\"><input type=\"button\" class=action value=\"确 定\" onclick=\"Dialog.close()\" /></td></tr>";
        		/*"<image src="+Configer.FrameSourcePath+"/images/error.jpg width=20 height=20>&nbsp;&nbsp错误信息:<hr size='1' color='#f3f3f3'>";*/
               	}
		Dialog.InfoStrHtml += "</table>";
		Dialog.InfoDIV.innerHTML = Dialog.InfoStrHtml;
        	Dialog.InfoDIV.style.display="";
        	Dialog.MaskDIV.style.display="";
}
Dialog.close=function(){
		Dialog.InfoDIV.style.display="none";
        	Dialog.MaskDIV.style.display="none";
}

var Infomation={};
Infomation.tip=function(txt){
	Dialog.InfoType=0;
    	Dialog.Message=txt;
    	Dialog.show();
} 
Infomation.wait=function(msg){
	Dialog.InfoType=1;
    	Dialog.Message=msg;
    	Dialog.show();
}

Infomation.error=function(msg){
	Dialog.InfoType=2;
	Dialog.Message=msg;
	Dialog.show();
}
Infomation.close=function(){
	Dialog.close();
}