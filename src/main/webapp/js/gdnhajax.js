var aHttp_request=false;
function send_request(url)
{
   //\u521D\u59CB\u5316,\u6307\u5B9A\u5904\u7406\u51FD\u6570,\u53D1\u9001\u8BF7\u6C42\u7684\u51FD\u6570
  aHttp_request=false;
  //\u5F00\u59CB\u521D\u59CB\u5316XMLHttpRequest\u5BF9\u8C61
  if(window.XMLHttpRequest)
  {
	aHttp_request=new XMLHttpRequest();
	if(aHttp_request.overrideMimeType)
        {
     	//\u8BBE\u7F6EMiME\u7C7B\u522B
	    aHttp_request.overrideMimeType("text/xml");
		}
   }else if(window.ActiveXObject)
   {
        //IE\u6D4F\u89C8\u5668
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
      //\u5F02\u5E38,\u521B\u5EFA\u5BF9\u8C61\u5B9E\u4F8B\u5931\u8D25
      window.alert("\u4E0D\u80FD\u521B\u5EFAXMLHttpRequest\u5BF9\u8C61\u5B9E\u4F8B.");
      return false;
   }
   aHttp_request.onreadystatechange=processRequest;
   //\u786E\u5B9A\u53D1\u9001\u8BF7\u6C42\u7684\u65B9\u5F0F\u548CURL\u4EE5\u53CA\u662F\u5426\u540C\u6B65\u6267\u884C\u4E0B\u6BB5\u4EE3\u7801
   aHttp_request.open("GET",url,true);
   aHttp_request.send(null);
}