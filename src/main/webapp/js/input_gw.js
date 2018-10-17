
function $( id ){return document.getElementById( id );}
function fEvent(sType,oInput){
switch (sType){
case "focus" :
oInput.isfocus = true;
case "mouseover" :
oInput.style.borderColor = '#C6E8FB';
oInput.style.backgroundColor = '#ffffff';
oInput.style.filter = 'progid:DXImageTransform.Microsoft.Glow(color=#E9F6FD,strength=3)';
break;
case "blur" :
oInput.isfocus = false;
case "mouseout" :
if(!oInput.isfocus){
oInput.style.borderColor='#BDC5CA';
oInput.style.backgroundColor = '#FFFFFF';
oInput.style.filter = 'progid:DXImageTransform.Microsoft.Glow(color=#ffffff,strength=3)';
}
break;
}
}
function MM_jumpMenu(targ,selObj,restore){ //v3.0
eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
if (restore) selObj.selectedIndex=0;
}
