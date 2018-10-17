///////////////////////////////////////////////////////////////////////////////////

function compareOptionValues(a, b) 
{ 
  // Radix 10: for numeric values
  // Radix 36: for alphanumeric values
  var sA = parseInt( a.value, 36 );  
  var sB = parseInt( b.value, 36 );  
  return sA - sB;
}

// Compare two options within a list by TEXT

function compareOptionText(a, b) 
{ 
  // Radix 10: for numeric values
  // Radix 36: for alphanumeric values
  var sA = parseInt( a.text, 36 );  
  var sB = parseInt( b.text, 36 );  
  return sA - sB;
}

// Dual list move function

function moveliftList1( repertoireList,operatorInfoList,entrype_type_List,entrype_method_list,checkout_type_List,checkout_method_List, destList, moveAll ) 
{
  // Do nothing if nothing is selected
  
  if (  ( repertoireList.selectedIndex == 0 ) && ( moveAll == false )   )
  {
  	alert("\u8bf7\u9009\u62e9\u6307\u4ee4!");
    return;
  }
    
	if(entrype_type_List.selectedIndex==2 && entrype_method_list.selectedIndex<=0){
	alert("\u8bf7\u9009\u62e9\u52a0\u5bc6\u65b9\u6cd5\uff01");
	return;
	}

	if(entrype_type_List.selectedIndex==1 && checkout_type_List.selectedIndex<=0){
	alert("\u8bf7\u6b63\u786e\u9009\u62e9\u6821\u9a8c\u65b9\u5f0f\uff01");
	return;
	}
	if(entrype_type_List.selectedIndex==2 && checkout_type_List.selectedIndex == 0){
	alert("\u8bf7\u6b63\u786e\u9009\u62e9\u6821\u9a8c\u65b9\u5f0f\uff01");
	return;
	}
	if(entrype_type_List.selectedIndex<=0 && checkout_type_List.selectedIndex==1){
	alert("\u8bf7\u6b63\u786e\u9009\u62e9\u52a0\u5bc6\u65b9\u5f0f\uff01");
	return;
	}
	if(checkout_type_List.selectedIndex<=0 && checkout_method_List.selectedIndex>0){
	alert("\u8bf7\u9009\u62e9\u6821\u9a8c\u65b9\u5f0f\uff01");
	return;
	}
	if(checkout_type_List.selectedIndex==2 && checkout_method_List.selectedIndex<=0){
	alert("\u8bf7\u9009\u62e9\u6821\u9a8c\u65b9\u6cd5\uff01");
	return;
	}
 	if(checkout_type_List.selectedIndex>0 && entrype_type_List.selectedIndex<=0){
	alert("\u8bf7\u9009\u62e9\u52a0\u5bc6\u65b9\u5f0f\uff01");
	return;
	}
	if(checkout_type_List.selectedIndex>0 && entrype_type_List.selectedIndex>0&&operatorInfoList.selectedIndex<=0){
	alert("\u8bf7\u9009\u62e9\u63d0\u793a\u4fe1\u606f\uff01");
	return;
	}
 	
 	var checkout_type="",entrype_type="",checkout_method="",entrype_method="";
 	
  		if(checkout_type_List.options[ checkout_type_List.selectedIndex ].value=='0'){
  		checkout_type=checkout_type_List.options[ checkout_type_List.selectedIndex ].text;
  		checkout_method="/0";
  		entrype_type="/"+entrype_type_List.options[ entrype_type_List.selectedIndex ].text+"+";
  		if(entrype_type_List.options[ entrype_type_List.selectedIndex ].value=='0'){
  			entrype_method="/0";
  		}
  		if(entrype_type_List.options[ entrype_type_List.selectedIndex ].value=='1'){
  			entrype_method="/1"+entrype_method_list.options[ entrype_method_list.selectedIndex ].value;
  		}
  		}
  		if(checkout_type_List.options[ checkout_type_List.selectedIndex ].value=='1'){
  		checkout_type=checkout_method_List.options[ checkout_method_List.selectedIndex ].text;
  		checkout_method="/1"+checkout_method_List.options[ checkout_method_List.selectedIndex ].value;
  		entrype_type="/"+entrype_method_list.options[ entrype_method_list.selectedIndex ].text+"+";
  		if(entrype_type_List.options[ entrype_type_List.selectedIndex ].value=='0'){
  			entrype_method="/0";
  			entrype_type="/"+entrype_type_List.options[1].text+"+";
  		}
  		if(entrype_type_List.options[ entrype_type_List.selectedIndex ].value=='1'){
  			entrype_method="/1"+entrype_method_list.options[ entrype_method_list.selectedIndex ].value;
  		}
  		}
	newDestList = new Array( destList.options.length );
	var len = 0;
  for( len = 0; len < destList.options.length; len++ ) 
  {
    if ( destList.options[ len ] != null )
    {
      newDestList[ len ] = new Option( destList.options[ len ].text, destList.options[ len ].value, destList.options[ len ].defaultSelected, destList.options[ len ].selected );
    }
  }

  for( var i = 0; i < repertoireList.options.length; i++ ) 
  { 
    if ( repertoireList.options[i] != null && ( repertoireList.options[i].selected == true || moveAll ) )
    {
       // Statements to perform if option is selected
       // Incorporate into new list
       newDestList[ len ] = new Option("\u7b2c"+(len+1)+"\u6b65:"+repertoireList.options[i].text, repertoireList.options[i].value, repertoireList.options[i].defaultSelected, repertoireList.options[i].selected );
       len++;
    }
  }



 for ( var j = 0; j < newDestList.length; j++ ) 
  {
    if ( newDestList[ j ] != null )
    {
      destList.options[ j ] = newDestList[ j ];
    }
  } 
  if( operatorInfoList.selectedIndex >0 ){
   for( var i = 0; i < operatorInfoList.options.length; i++ ) 
  { 
    if ( operatorInfoList.options[i] != null && ( operatorInfoList.options[i].selected == true || moveAll ) )
    {
    	
      destList.options[ destList.options.length-1 ] = new Option(destList.options[ destList.options.length-1 ].text+":"+operatorInfoList.options[i].text+entrype_type+checkout_type, destList.options[ destList.options.length-1 ].value+"/"+operatorInfoList.options[i].value+entrype_method+checkout_method, operatorInfoList.options[i].defaultSelected, operatorInfoList.options[i].selected );
    }
  }
  }

 repertoireList.selectedIndex = -1;
 entrype_type_List.selectedIndex=0;
 entrype_method_list.selectedIndex=0;
 checkout_type_List.selectedIndex=0;
 checkout_method_List.selectedIndex=0;
} // End of moveDualList()
function moveliftList2( srcList, destList, moveAll ) 
{
  // Do nothing if nothing is selected
  if (  ( srcList.selectedIndex == -1 ) && ( moveAll == false )   )
  {
    return;
  }
  var len = 0;
   for( var i = 0; i < srcList.options.length; i++ ) 
  { 
    if ( srcList.options[i] != null && ( srcList.options[i].selected == true || moveAll ) )
    {
      destList.options[ destList.options.length-1 ] = new Option(destList.options[ destList.options.length-1 ].text+":"+srcList.options[i].text, destList.options[ destList.options.length-1 ].value+"/"+srcList.options[i].value, srcList.options[i].defaultSelected, srcList.options[i].selected );
    }
  }
  

} // End of moveDualList()
function moverightList( srcList, destList, moveAll ) 
{
  // Do nothing if nothing is selected
  if (  ( srcList.selectedIndex == -1 ) && ( moveAll == false )   )
  {
    return;
  }
  newDestList = new Array( destList.options.length );
  var len = 0;
  for( len = 0; len < destList.options.length; len++ ) 
  {
    if ( destList.options[ len ] != null )
    {
      newDestList[ len ] = new Option( destList.options[ len ].text, destList.options[ len ].value, destList.options[ len ].defaultSelected, destList.options[ len ].selected );
    }
  }

  for( var i = 0; i < srcList.options.length; i++ ) 
  { 
   if ( srcList.options[i] != null && ( srcList.options[i].selected == true || moveAll ) )
    {
       newDestList[ len ] = new Option( srcList.options[i].text, srcList.options[i].value, srcList.options[i].defaultSelected, srcList.options[i].selected );
       len++;
    }
  }


  for( var i = srcList.options.length - 1; i >= 0; i-- ) 
  { 
    if ( srcList.options[i] != null && ( srcList.options[i].selected == true || moveAll ) )
    {
       srcList.options[i]       = null;
    }
  }
} // End of moveDualList()
	function selectAll(selectObj)
	{
      for(i=0,j=selectObj.length;i<j;i++){
      selectObj.options[i].selected=true;  
    }
    }
    function clearList(repertoireList,operatorInfoList,entrype_type_List,entrype_method_list,checkout_type_List,checkout_method_List, destList, moveAll)
	{
	
	
	repertoireList.selectedIndex = -1;
 	entrype_type_List.selectedIndex =0;
 	entrype_method_list.selectedIndex =0;
 	checkout_type_List.selectedIndex =0;
 	checkout_method_List.selectedIndex =0;
    }
    
    function onclick_none_block(index,id){
    if(index==1){
  		document.getElementById(id).style.display="none";
  		}else{
  		document.getElementById(id).style.display="block";
  		}
    }
