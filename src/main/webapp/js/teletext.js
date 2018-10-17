
var editorFocus = "";
function setCurFocusObject(id) {
	editorFocus = id;
}
//根据选中的textarea插入文字
function insertTextarea(text)
{
	var obj = dojo.byId(editorFocus);
	if(obj){
	if(document.selection) { 
	obj.focus(); 
	var sel=document.selection.createRange(); 
	document.selection.empty(); 
	sel.text = text; 
	} else { 
	var prefix, main, suffix; 
	prefix = obj.value.substring(0, obj.selectionStart); 
	main = obj.value.substring(obj.selectionStart, obj.selectionEnd); 
	suffix = obj.value.substring(obj.selectionEnd); 
	obj.value = prefix + text + suffix; 
	} 
	obj.focus(); 
	}
}
//添加占位符
function addPlaceholder(){
	if (editorFocus=='message') {
		var urlText = "$";
		insertTextarea(urlText);
	} 
}