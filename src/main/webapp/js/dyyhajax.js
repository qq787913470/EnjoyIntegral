/**
* @author sujt 2009-06
* function ajax process frame	
**/
var aHttp_request=false;
function send_request(url,processFuncName)
{
   //\u521d\u59cb\u5316,\u6307\u5b9a\u5904\u7406\u51fd\u6570,\u53d1\u9001\u8bf7\u6c42\u7684\u51fd\u6570
  aHttp_request=false;
  //\u5f00\u59cb\u521d\u59cb\u5316XMLHttpRequest\u5bf9\u8c61
  if(window.XMLHttpRequest)
  {
	aHttp_request=new XMLHttpRequest();
	if(aHttp_request.overrideMimeType)
        {
     	//\u8bbe\u7f6eMiME\u7c7b\u522b
	    aHttp_request.overrideMimeType("text/xml");
		}
   }else if(window.ActiveXObject)
   {
        //IE\u6d4f\u89c8\u5668
	try{
		aHttp_request=new ActiveXObject("Msxml2.XMLHTTP");
	   }catch(e)
	   {
		try{
			aHttp_request=new ActiveXObject("Microsoft.XMLHTTP");
		   }catch(e){}
	   }
   }
   if(!aHttp_request)
   {
      //\u5f02\u5e38,\u521b\u5efa\u5bf9\u8c61\u5b9e\u4f8b\u5931\u8d25
      window.alert("\u4e0d\u80fd\u521b\u5efaXMLHttpRequest\u5bf9\u8c61\u5b9e\u4f8b.");
      return false;
   }
   aHttp_request.onreadystatechange=eval(processFuncName);
   //\u786e\u5b9a\u53d1\u9001\u8bf7\u6c42\u7684\u65b9\u5f0f\u548cURL\u4ee5\u53ca\u662f\u5426\u540c\u6b65\u6267\u884c\u4e0b\u6bb5\u4ee3\u7801
   aHttp_request.open("GET",url,true);
   aHttp_request.send(null);
}
//ajax\u6839\u636e\u624b\u7eed\u8d39\u6536\u53d6\u65b9\u5f0f\u83b7\u53d6\u7701\u884c\u624b\u7eed\u8d39\u53ca\u5176\u5355\u7b14\u9650\u989d\u8bbe\u7f6e
function queryFeeStyleInfo()
{
	 if(aHttp_request.readyState==4)
    {
		if(aHttp_request.status==200)
		{
		  //success return ,process start
		  var xml = aHttp_request.responseXML;
		  var feeStyleInfo=xml.getElementsByTagName("feeStyleInfo").item(0);
		  var consume_fee_up = feeStyleInfo.getAttribute("consume_fee_up");
		  var consume_fee_down = feeStyleInfo.getAttribute("consume_fee_down");
		  var consume_fee_percent = feeStyleInfo.getAttribute("consume_fee_percent");
		  var consume_a_trade = feeStyleInfo.getAttribute("consume_a_trade");
		  var union_fee_pt = feeStyleInfo.getAttribute("union_fee_pt");
		  var union_fee_max = feeStyleInfo.getAttribute("union_fee_max");
		  var transfer_self_pt = feeStyleInfo.getAttribute("transfer_self_pt");
		  var transfer_self_max = feeStyleInfo.getAttribute("transfer_self_max");
		  var transfer_self_min = feeStyleInfo.getAttribute("transfer_self_min");
		  var transfer_other_pt = feeStyleInfo.getAttribute("transfer_other_pt");
		  var transfer_fee_max = feeStyleInfo.getAttribute("transfer_fee_max");
		  var transfer_fee_min = feeStyleInfo.getAttribute("transfer_fee_min");
		  var month_fee = feeStyleInfo.getAttribute("month_fee");
		  
		  document.getElementById("consume_fee_up").value=consume_fee_up;
		  document.getElementById("consume_fee_down").value=consume_fee_down;
		  document.getElementById("consume_fee_percent").value=consume_fee_percent;
		  document.getElementById("consume_a_trade").value=consume_a_trade;
		  document.getElementById("union_fee_pt").value=union_fee_pt;
		  document.getElementById("union_fee_max").value=union_fee_max;
		  document.getElementById("transfer_self_pt").value=transfer_self_pt;
		  document.getElementById("transfer_self_max").value=transfer_self_max;
		  document.getElementById("transfer_self_min").value=transfer_self_min;
		  document.getElementById("transfer_other_pt").value=transfer_other_pt;
		  document.getElementById("transfer_fee_max").value=transfer_fee_max;
		  document.getElementById("transfer_fee_min").value=transfer_fee_min;
		  document.getElementById("month_fee").value=month_fee;
		}else
		{
		  //error interner server
		  alert("\u83b7\u53d6\u7701\u884c\u624b\u7eed\u8d39\u8bbe\u7f6e\u5931\u8d25,\u8bf7\u91cd\u8bd5!");
		}
    }
}
//\u624b\u7eed\u8d39\u91c7\u7528\u7701\u884c\u8bbe\u7f6e\u8fd8\u662f\u5355\u72ec\u8bbe\u7f6e\u6240\u9700\u8981\u9690\u85cf\u7684\u8f93\u5165\u6846
function feeSetFlagChg()
{
	var fee_sty_id=document.getElementById("fee_sty_id").value;
	var feeSetFlags=document.getElementsByName("fee_set_flag");
	var arr,feeSetFlag;
	for(i=0;i<feeSetFlags.length;i++)
	{
		if(feeSetFlags[i].checked)
			feeSetFlag=feeSetFlags[i].value;
	}
	if(feeSetFlag=="0")
	{
		getFeeStyleInfo();
		document.getElementById("consume_fee_up").readOnly=true;
		document.getElementById("consume_fee_percent").readOnly=true;
		document.getElementById("consume_fee_down").readOnly=true;
		document.getElementById("consume_a_trade").readOnly=true;
		document.getElementById("union_fee_pt").readOnly=true;
		document.getElementById("union_fee_max").readOnly=true;
		document.getElementById("transfer_self_pt").readOnly=true;
		document.getElementById("transfer_self_max").readOnly=true;
		document.getElementById("transfer_self_min").readOnly=true;
		document.getElementById("transfer_other_pt").readOnly=true;
		document.getElementById("transfer_fee_max").readOnly=true;
		document.getElementById("transfer_fee_min").readOnly=true;
		document.getElementById("month_fee").readOnly=true;
		document.getElementById("consume_fee_up").style.backgroundColor="#ececec";
		document.getElementById("consume_fee_percent").style.backgroundColor="#ececec";
		document.getElementById("consume_fee_down").style.backgroundColor="#ececec";
		document.getElementById("consume_a_trade").style.backgroundColor="#ececec";
		document.getElementById("union_fee_pt").style.backgroundColor="#ececec";
		document.getElementById("union_fee_max").style.backgroundColor="#ececec";
		document.getElementById("transfer_self_pt").style.backgroundColor="#ececec";
		document.getElementById("transfer_self_max").style.backgroundColor="#ececec";
		document.getElementById("transfer_self_min").style.backgroundColor="#ececec";
		document.getElementById("transfer_other_pt").style.backgroundColor="#ececec";
		document.getElementById("transfer_fee_max").style.backgroundColor="#ececec";
		document.getElementById("transfer_fee_min").style.backgroundColor="#ececec";
		document.getElementById("month_fee").style.backgroundColor="#ececec";
	}else
	{
		if(fee_sty_id=="2")
		{
			document.getElementById("consume_fee_up").readOnly=false;
			document.getElementById("consume_fee_percent").readOnly=false;
			document.getElementById("consume_fee_down").readOnly=false;
			document.getElementById("consume_a_trade").readOnly=false;
			document.getElementById("union_fee_pt").readOnly=false;
			document.getElementById("union_fee_max").readOnly=false;
			document.getElementById("transfer_self_pt").readOnly=false;
			document.getElementById("transfer_self_max").readOnly=false;
			document.getElementById("transfer_self_min").readOnly=false;
			document.getElementById("transfer_other_pt").readOnly=false;
			document.getElementById("transfer_fee_max").readOnly=false;
			document.getElementById("transfer_fee_min").readOnly=false;
			document.getElementById("month_fee").readOnly=false;
			document.getElementById("consume_fee_up").style.backgroundColor="";
			document.getElementById("consume_fee_percent").style.backgroundColor="";
			document.getElementById("consume_fee_down").style.backgroundColor="";
			document.getElementById("consume_a_trade").style.backgroundColor="";
			document.getElementById("union_fee_pt").style.backgroundColor="";
			document.getElementById("union_fee_max").style.backgroundColor="";
			document.getElementById("transfer_self_pt").style.backgroundColor="";
			document.getElementById("transfer_self_max").style.backgroundColor="";
			document.getElementById("transfer_self_min").style.backgroundColor="";
			document.getElementById("transfer_other_pt").style.backgroundColor="";
			document.getElementById("transfer_fee_max").style.backgroundColor="";
			document.getElementById("transfer_fee_min").style.backgroundColor="";
			document.getElementById("month_fee").style.backgroundColor="";
		}else
		{
			//document.getElementById("fix_fee_amount").readOnly=false;
			//document.getElementById("fix_fee_amount").style.backgroundColor="";
			document.getElementById("consume_a_trade").readOnly=false;
			document.getElementById("consume_a_trade").style.backgroundColor="";
			document.getElementById("consume_fee_up").readOnly=true;
			document.getElementById("consume_fee_percent").readOnly=true;
			document.getElementById("consume_fee_down").readOnly=true;
			document.getElementById("consume_fee_up").style.backgroundColor="#ececec";
			document.getElementById("consume_fee_percent").style.backgroundColor="#ececec";
			document.getElementById("consume_fee_down").style.backgroundColor="#ececec";
		}
	}
}

function getFeeStyleInfo()
{
     var feeStyleId=document.getElementById("fee_sty_id").value;
     var feeSetFlags=document.getElementsByName("fee_set_flag");
     var mcc=document.getElementById("mcc").value;
	 var arr,feeSetFlag;
	 for(i=0;i<feeSetFlags.length;i++)
	 {
		if(feeSetFlags[i].checked)
			feeSetFlag=feeSetFlags[i].value;
	 }
     if(feeStyleId.trim()==""||feeStyleId.trim()==null)
     {
       alert("\u624b\u7eed\u8d39\u8ba1\u7b97\u65b9\u5f0f\u662f\u5fc5\u987b\u7684\uff01");
     }
     else
     {
       if(feeSetFlag=="0")
       {
       	var url='app.action.Terminal.do?method=queryFeeStyleInfo&feeStyleId='+feeStyleId+"&mcc="+mcc;
       	send_request(url,'queryFeeStyleInfo');
       }
     }
}
//\u5224\u65ad\u4e0b\u9650\u662f\u5426\u5c0f\u4e8e\u7b49\u4e8e\u4e0a\u9650
function checkDownUpFee()
{
	var consume_fee_up=document.getElementById("consume_fee_up");
	var consume_fee_down=document.getElementById("consume_fee_down");
	var transfer_self_max=document.getElementById("transfer_self_max");
	var transfer_self_min=document.getElementById("transfer_self_min");
	var transfer_fee_max=document.getElementById("transfer_fee_max");
	var transfer_fee_min=document.getElementById("transfer_fee_min");
	var patrn=/^[0-9]+$/;
	if(consume_fee_up.value.trim()=="")
		consume_fee_up.value=0;
	if(consume_fee_down.value.trim()=="")
		consume_fee_down.value=0;
	if(transfer_self_max.value.trim()=="")
		transfer_self_max.value=0;
	if(transfer_self_min.value.trim()=="")
		transfer_self_min.value=0;
	if(transfer_fee_max.value.trim()=="")
		transfer_fee_max.value=0;
	if(transfer_fee_min.value.trim()=="")
		transfer_fee_min.value=0;
	if (!patrn.exec(consume_fee_up.value))
	{   
		window.alert("\u683c\u5f0f\u9519\u8bef!");      
		consume_fee_up.select();   
	    return false;                        
    }
	if (!patrn.exec(consume_fee_down.value))
	{   
		window.alert("\u683c\u5f0f\u9519\u8bef!");      
		consume_fee_down.select();   
	    return false;                        
    }
	if (!patrn.exec(transfer_self_max.value))
	{   
		window.alert("\u683c\u5f0f\u9519\u8bef!");      
		transfer_self_max.select();   
	    return false;                        
    }
	if (!patrn.exec(transfer_self_min.value))
	{   
		window.alert("\u683c\u5f0f\u9519\u8bef!");      
		transfer_self_min.select();   
	    return false;                        
    }
	if (!patrn.exec(transfer_fee_max.value))
	{   
		window.alert("\u683c\u5f0f\u9519\u8bef!");      
		transfer_fee_max.select();   
	    return false;                        
    }
	if (!patrn.exec(transfer_fee_min.value))
	{   
		window.alert("\u683c\u5f0f\u9519\u8bef!");      
		transfer_fee_min.select();   
	    return false;                        
    }
	if((parseInt(consume_fee_up.value)-parseInt(consume_fee_down.value))<0)
	{
		window.alert("\u4e2d\u884c\u5361\u6536\u6b3e\u624b\u7eed\u8d39\u4e0a\u9650\u4e0d\u80fd\u5c0f\u4e8e\u4e0b\u9650!");      
		consume_fee_up.select();
		return false;   
	}
	if((parseInt(transfer_self_max.value)-parseInt(transfer_self_min.value))<0)
	{
		window.alert("\u4e2d\u884c\u5361\u540c\u57ce\u4ed8\u6b3e\u624b\u7eed\u8d39\u4e0a\u9650\u4e0d\u80fd\u5c0f\u4e8e\u4e0b\u9650!");      
		transfer_self_max.select();
		return false;   
	}
	if((parseInt(transfer_fee_max.value)-parseInt(transfer_fee_min.value))<0)
	{
		window.alert("\u4e2d\u884c\u5361\u5f02\u5730\u4ed8\u6b3e\u624b\u7eed\u8d39\u4e0a\u9650\u4e0d\u80fd\u5c0f\u4e8e\u4e0b\u9650!");      
		transfer_fee_max.select();
		return false;   
	}
	return true;
}