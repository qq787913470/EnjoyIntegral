///////////////////////////////////////////////////////////////////////////////////
// \u6587\u4ef6\u540d \uff1acommon.js
// \u529f  \u80fd \uff1a\u5b9a\u4e49\u4e86\u4e00\u4e9b\u516c\u7528\u51fd\u6570\uff0c
//          \u6bd4\u5982\uff1a\u8bbe\u7f6e\u63d0\u793a\u4fe1\u606f\uff0c\u8bbe\u7f6e\u63d0\u4ea4 XML \u6570\u636e\u7b49\u529f\u80fd\u3002
//
// \u7f16\u5199\u8005 \uff1a    zxh
//
// \u65e5  \u671f \uff1a    2004/4/21
//
///////////////////////////////////////////////////////////////////////////////////

//+----------------------------------------------
// \u9519\u8bef\u5e38\u91cf
//-----------------------------------------------
var DATE_FORMAT_ERROR       = "\u65e5\u671f\u683c\u5f0f\u9519\u8bef";
var TIME_FORMAT_ERROR       = "\u65f6\u95f4\u683c\u5f0f\u9519\u8bef";
var DATETIME_FORMAT_ERROR   = "\u65e5\u671f\uff0b\u65f6\u95f4\u683c\u5f0f\u9519\u8bef";
var IDCARD_FORMAT_ERROR     = "\u516c\u6c11\u8eab\u4efd\u8bc1\u53f7\u7801\u683c\u5f0f\u9519\u8bef";
var NUMBER_FORMAT_ERROR     = "\u6570\u5b57\u683c\u5f0f\u9519\u8bef";
var OVER_RANGE_ERROR        = "\u8f93\u5165\u7684\u503c\u8d85\u51fa\u4e86\u8303\u56f4,\u6709\u6548\u8303\u56f4\u5728:";
var MASK_FORMAT_ERROR       = "\u540c\u6240\u9700\u7684\u683c\u5f0f\u4e0d\u7b26\u5408";
var MASK_SEX_ERROR          = "\u8f93\u5165\u7684\u6027\u522b\u4e0e\u516c\u6c11\u8eab\u4efd\u8bc1\u53f7\u7801\u4e0d\u76f8\u7b26\u5408";
var MASK_BIRTHDAY_ERROR     = "\u8f93\u5165\u7684\u51fa\u751f\u65e5\u671f\u4e0e\u516c\u6c11\u8eab\u4efd\u8bc1\u53f7\u7801\u4e0d\u76f8\u7b26\u5408";
var LESS_NAME_LENGTH        = "\u59d3\u540d\u5c11\u4e8e2\u4e2a\u6c49\u5b57";
//+------------------------------------
// \u53d8\u91cf
//-------------------------------------
var m_bUpIDCard     = true;         // \u5f53\u8eab\u4efd\u8bc1\u4e0d\u8db318\u4f4d\u65f6\uff0c\u662f\u5426\u5347\u7ea7\u4e3a18\u4f4d
var m_bFormatError  = false;        // \u662f\u5426\u683c\u5f0f\u9519\u8bef
var m_bHoldFocus    = false;        // \u662f\u5426\u83b7\u5f97\u7126\u70b9
var m_bReadOnly     = false;        // \u53ea\u8bfb
var m_sErrorInfo    = "";           // \u9519\u8bef\u4fe1\u606f
var m_sRealValue;                   // \u7528\u6237\u5b9e\u9645\u8f93\u5165\u7684\u503c
var m_kind;                         // \u79cd\u7c7b

//check your machine of msxml
function checkXmlVer()
{
	var strVer;
	var xml = "<?xml version=\"1.0\" encoding=\"UTF-16\"?><cjb></cjb>";
	var xsl = "<?xml version=\"1.0\" encoding=\"UTF-16\"?><x:stylesheet version=\"1.0\" xmlns:x=\"http://www.w3.org/1999/XSL/Transform\" xmlns:m=\"urn:schemas-microsoft-com:xslt\"><x:template match=\"/\"><x:value-of select=\"system-property('m:version')\" /></x:template></x:stylesheet>";
	//var xsl = "<?xml version=\"1.0\" encoding=\"UTF-16\"?><x:stylesheet version=\"1.0\" xmlns:x=\"http://www.w3.org/TR/WD-xsl\"></x:stylesheet>";

	var x = null;

	try{
	    x = new ActiveXObject("Msxml2.DOMDocument");
	    x = null;
	}catch(e){
        strVer = "0.0";
        return strVer;
	}

	try{
	    x = new ActiveXObject("Msxml2.DOMDocument.2.6");
        x = null;
	}catch(e){
	    strVer = "1.0";
        return strVer;
	}

	try{
	    x = new ActiveXObject("Msxml2.DOMDocument.3.0");
        x = null;
	}catch(e){
	    strVer = "2.6";
        return strVer;
	}

	try{
	    x = new ActiveXObject("Msxml2.DOMDocument.4.0");
        x = null;
	}catch(e){
	    strVer = "3.0";
        return strVer;
	}

    strVer = "4.0";
    return strVer;

}

//check your machine's Type of IE
function checkIETypeVer()
{

  return navigator.appName;

}

//check your machine's width and height of screen
function checkScreen()
{

  return window.screen.width+"x"+window.screen.height;

}

//\u5224\u65ad\u5206\u8fa8\u7387\u662f\u5426\u5927\u4e8e800x600
//\u5982\u679c\u5c0f\u4e8e800x600 \u5219 \u8fd4\u56de 0
//\u5982\u679c\u7b49\u4e8e800x600 \u5219 \u8fd4\u56de 1
//\u5982\u679c\u5927\u4e8e800x600 \u5219 \u8fd4\u56de 2
function Is800x600()
{

  if(window.screen.width==800&&window.screen.height==600)
  {
     return 1;
  }
  else{
      if(window.screen.width<800&&window.screen.height<600){
         return 0;
      }
      else{
         return 2;
      }
  }

}

//\u5224\u65ad\u989c\u8272\u4f4d\u6570
function checkColorDepth()
{

return window.screen.colorDepth;

}


//check your machine's Version of IE
function checkIEVer()
{
    var ua = window.navigator.userAgent;
    var msiePos = ua.indexOf("MSIE");
    var msieVer = 0;
    var msieVer;
    if (msiePos >= 0) {
        msieVer = ua.substr(msiePos+5,3);
        return msieVer;
    }
    return "";

}




//+------------------------------------
// \u8bbe\u7f6e\u63d0\u793a\u4fe1\u606f
// sMsg : String    \u63d0\u793a\u4fe1\u606f
// iIndex : Integer \u4e3a\u8981\u663e\u793a\u63d0\u793a\u7684\u9875\u9762\u7d22\u5f15
//-------------------------------------
function setHint(sMsg, iIndex)
{
    try {
        var index = iIndex ? iIndex : g_xTabPane.selectedIndex
        g_xTabPane.pages[index].setHint(sMsg);
    }
    catch (e) {}
}

//+------------------------------------
// \u8bbe\u7f6e\u8ddf\u968f\u63d0\u793a
// obj : Object     \u76f8\u5173\u5bf9\u8c61
// sMsg : String    \u63d0\u793a\u4fe1\u606f
//-------------------------------------
function setFollowHint(obj, sMsg)
{
    try {
        if (obj)
            g_xHint.setHint(obj, sMsg);
        else
            g_xHint.hidden();
    }
    catch (e) {}
}

//+------------------------------------
// \u4e0a\u4e00\u7126\u70b9
//-------------------------------------
function PrevFocus()
{
    g_xTabPane.movePrev();
}

//+------------------------------------
// \u4e0b\u4e00\u7126\u70b9
//-------------------------------------
function NextFocus()
{
    g_xTabPane.moveNext();
}

//+------------------------------------
// \u5904\u7406\u5b57\u5178\u63a7\u4ef6\u5355\u51fb\u9009\u62e9\u4e8b\u4ef6
//-------------------------------------
function ctlDic_Item_Click()
{
    if (g_xDic.currentCode != "") {
        SetContentBase(g_xDic.currentCode, g_xDic.currentText);
    }
    if (g_xTabPane.pages[g_xTabPane.selectedIndex].edits[g_xTabPane.indexFocus].jump!="true")
    {
    g_xTabPane.pages[g_xTabPane.selectedIndex].edits[g_xTabPane.indexFocus].focus();
    NextFocus();
    }
    else
    {
      document.frmInput.cmdunit.focus()
    }
}

//+------------------------------------
// \u8bbe\u7f6e\u5f53\u524d\u5f55\u5165\u6846\u7684\u503c
//-------------------------------------
function SetContent()
{
    SetContentBase(g_xDic.currentCode, g_xDic.currentText);
}

//+------------------------------------
// \u8bbe\u7f6e\u5f53\u524d\u5f55\u5165\u6846\u7684\u57fa\u503c
//-------------------------------------
function SetContentBase(sCode, sText)
{
    var edits = g_xTabPane.pages[g_xTabPane.selectedIndex].edits;
    var iIndex = g_xTabPane.indexFocus;
    if (iIndex == -1) return;
    if (edits[iIndex].kind.toLowerCase() == "dic" && !edits[iIndex].readOnly) {
        g_bCheckChanged = false;
        edits[iIndex].code = sCode;
        edits[iIndex].value = sText;
        g_bCheckChanged = false;
    }
}


///////////////////////////////////////////////////////////////////////////////////
// \u63d0\u4ea4\u5904\u7406
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// \u8bbe\u7f6e DataInfo XML \u5b57\u7b26\u4e32
//-------------------------------------
function setDataInfoXML(edits)
{
    var len;
    var arr_fieldName = [];
    var arr_nodeValue = [];
    var arr_showValue = [];     // \u663e\u793a\u503c
    var arr_valueType = [];     // code \u4e2d\u662f\u5426\u5305\u542b\u7684\u662f XML \u5b57\u7b26\u4e32
    var kind;
    for (var i = 0; i < edits.length; i++) {
        if (edits[i].ignore) {
            continue;
        }
        if (edits[i].fieldname) {
            if (edits[i].fieldname.length < 1) {
                continue;
            }
            len = arr_fieldName.length;
            kind = edits[i].kind.toLowerCase();
            arr_fieldName[len] = edits[i].fieldname.toUpperCase();
            if (kind == "dic" || kind == "query" || kind == "more") {
                if (typeof(edits[i].code) == "string") {
                    arr_nodeValue[len] = edits[i].code.length > 0 ? edits[i].code : "";
                    arr_showValue[len] = (kind == "more") ? "" : (edits[i].value == ">" ? "" : edits[i].value);
                }
                else {
                    arr_nodeValue[len] = "";
                    arr_showValue[len] = "";
                }
            }
            else {
                if (typeof(edits[i].code) == "string") {
                    arr_nodeValue[len] = edits[i].code.length > 0 ? edits[i].code : edits[i].value;
                }
                else {
                    arr_nodeValue[len] = edits[i].value;
                }
                arr_showValue[len] = "";
            }
            arr_valueType[len] = (kind == "more") ? true : false;
        }
    }

    return buildDataInfo(arr_fieldName, arr_nodeValue, arr_showValue, arr_valueType);
}

//+------------------------------------
// \u83b7\u5f97\u63d0\u4ea4\u6570\u636e(\u7528\u4e8e\u4e3b\u5f55\u5165\u9879\u76ee)
//-------------------------------------
function getValue()
{
    try {
        var pages, edits, labels;
        var oBtn = g_frmPost.all("cmdSubmit");
        var oHidden = g_frmPost.all("txtXML");
        var arr_Edit = [];

        oHidden.value = "";
        if (oBtn) {
            oBtn.disabled = true;
        }

        pages = g_xTabPane.pages;
        for (var i = 0; i < pages.length; i++) {
            edits = pages[i].edits;
            labels = pages[i].labels;
            for (var j = 0; j < edits.length; j++) {
                if (edits[j].must == "true") {
                    var kind = edits[j].kind.toLowerCase();
                    if (kind == "dic" || kind == "query" || kind == "more") {
                        if (edits[j].code.length < 1) {
                            err("[" + labels[j].innerText + "]\u662f\u5fc5\u5f55\u9879\uff01");
                        }
                    }
                    else if (edits[j].value.length < 1) {
                        err("[" + labels[j].innerText + "]\u662f\u5fc5\u5f55\u9879\uff01");
                    }
                }
                if (edits[j].error == true) {
                    err("[" + labels[j].innerText + "]\u7684\u503c\u65e0\u6548\uff01");
                }
                arr_Edit[arr_Edit.length] = edits[j];
            }
        }
        i = j = 0;
        oHidden.value = setDataInfoXML(arr_Edit).xml;
        return true;
    }
    catch (e) {
        printf(e.description);
        if (oBtn) {
            oBtn.disabled = false;
        }
        g_xTabPane.setSelectedIndex(i);
        edits[j].focus();
        return false;
    }
}

//+------------------------------------
// \u83b7\u5f97\u63d0\u4ea4\u6570\u636e(\u7528\u4e8e\u8f85\u52a9\u5f55\u5165\u9879\u76ee)
//-------------------------------------
function getMoreValue()
{
    // \u68c0\u67e5\u5f55\u5165\u6846\u662f\u5426\u5168\u4e3a\u7a7a
    function checkAllIsBlank(edits) {
        for (var i = 0; i < edits.length; i++) {
            if (edits[i].type != "hidden") {
                if (edits[i].value.length > 0) {
                    return false;
                }
            }
        }
        return true;
    };

    try {
        var pages, edits, labels;
        var oBtn = g_frmPost.all("cmdSubmit");
        var oHidden = g_frmPost.all("txtXML");
        var arr_Edit;
        var sXML = "";
        var sNodePath = "DATAINFO/";

        oHidden.value = "";
        if (oBtn) {
            oBtn.disabled = true;
        }

        pages = g_xTabPane.pages;
        for (var i = 0; i < pages.length; i++) {
            edits = pages[i].edits;
            labels = pages[i].labels;

            if (checkAllIsBlank(edits)) {
                continue;
            }

            arr_Edit = [];
            for (var j = 0; j < edits.length; j++) {
                if (edits[j].must == "true") {
                    var kind = edits[j].kind.toLowerCase();
                    if (kind == "dic" || kind == "query" || kind == "more") {
                        if (edits[j].code.length < 1) {
                            err("[" + labels[j].innerText + "]\u662f\u5fc5\u5f55\u9879\uff01");
                        }
                    }
                    else if (edits[j].value.length < 1) {
                        err("[" + labels[j].innerText + "]\u662f\u5fc5\u5f55\u9879\uff01");
                    }
                }
                if (edits[j].error == true) {
                    err("[" + labels[j].innerText + "]\u7684\u503c\u65e0\u6548\uff01");
                }
                arr_Edit[arr_Edit.length] = edits[j];
            }
            j = 0;
            var oNode = getNodeDOM(setDataInfoXML(arr_Edit), sNodePath);
            sXML += oNode.firstChild.xml;
        }
        oHidden.value = sXML;
        return true;
    }
    catch (e) {
        printf(e.description);
        if (oBtn) {
            oBtn.disabled = false;
        }
        g_xTabPane.setSelectedIndex(i);
        edits[j].focus();
        return false;
    }
}


///////////////////////////////////////////////////////////////////////////////////
// \u8bbe\u7f6e\u521d\u59cb\u503c
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// \u901a\u8fc7 XML \u5b57\u7b26\u4e32\u83b7\u5f97\u5f55\u5165\u6846\u7684\u7f16\u7801\u503c\u548c\u6587\u672c\u503c
// sRoot \uff1a String      \u6839\u8282\u70b9\u540d
// sXML \uff1a String       XML \u5b57\u7b26\u4e32
// bByPage \uff1a Boolean   \u53bb\u591a\u503c\u6570\u636e\u662f\u5426\u6309\u9875\u9762\u5e8f\u53f7
//  (\u5982\u679c\u4e3atrue,\u8868\u793a\u6309\u9875\u9762\u5e8f\u53f7,\u4f8b\u5982\u643a\u5e26\u4eba\u7684\u521d\u59cb\u8d4b\u503c)
//-------------------------------------
function setValue(sRoot, sXML, bByPage)
{
    try {
        if (sXML.length < 1) {
            return false;
        }

        var pages, edits, labels;
        var kind, nodePath, attValue, nodeValue;
        var objXML = createDOMDocument();
        var index;
        sXML = sXML.replace(/&quot;/g, "'");
        objXML.loadXML(sXML);
        if (objXML.parseError.errorCode != 0) {
            throw 0;
        }

        pages = g_xTabPane.pages;
        for (var i = 0; i < pages.length; i++) {
            edits = pages[i].edits;
            index = bByPage ? i : 0;
            for (var j = 0; j < edits.length; j++) {
                kind = edits[j].kind.toLowerCase();
                if (!edits[j].fieldname)
                    continue;
                nodePath = sRoot + "/" + edits[j].fieldname.toUpperCase();
                nodeValue = getNodeValue(objXML, nodePath, index);
                nodeValue = nodeValue ? nodeValue : "";
                attValue = getAttribValue(objXML, nodePath, "sv", index);
                attValue = attValue ? attValue : "";
                if (nodeValue == "" && attValue == "")
                  {
                    edits[j].value=""
                    edits[j].code=""
                  }
                if (kind == "dic" || kind == "query") {
                    g_bCheckChanged = false;
                    edits[j].value = attValue;
                    g_bCheckChanged = false;
                    edits[j].code = nodeValue;
                }
                else if (kind != "more"){
                   if(kind=="datetime"){
                       edits[j].value = datetimeToStr(nodeValue);
                   }
                   else{
                       edits[j].value = nodeValue;
                   }
                }
                else {
                  /*
                   * \u83b7\u5f97\u591a\u503c\u8282\u70b9\u7684\u8282\u70b9\u540d
                   * \u5bf9\u4e8e kind="more" \u7c7b\u578b\u7684\u8282\u70b9,\u6709\u4e00\u4e2a fieldname1 \u7684\u5c5e\u6027\u6807\u8bc6\u5b50\u8282\u70b9\u7684\u8def\u5f84
                   */
                  var sFieldName = edits[j].fieldname.toUpperCase();
                  var sFieldName1 = edits[j].fieldname1.toUpperCase();
                  var nlMore = objXML.selectNodes(sRoot + "/" + sFieldName + "/" + sFieldName1);
                  var sMore = "";   // \u4fdd\u5b58\u591a\u503c\u8282\u70b9\u7684 XML \u5b57\u7b26\u4e32
                  if (nlMore.length > 0){
                    edits[j].value = "[\u5df2\u5f55\u5165]";
                    // \u5faa\u73af\u83b7\u5f97\u6240\u6709\u5b50\u8282\u70b9\u7684 XML \u5b57\u7b26\u4e32
                    for (var k=0; k<nlMore.length; k++){
                      sMore += nlMore.item(k).xml;
                    }
                    sMore = "<DATA>" + sMore + "</DATA>";
                  }
                  edits[j].code = sMore;
                }
            }
        }
        return true;
    }
    catch (e) {
        return false;
    }
}

///////////////////////////////////////////////////////////////////////////////////
// \u67e5\u8be2\u5904\u7406
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// \u83b7\u5f97\u7b80\u5355\u67e5\u8be2\u7684 XML \u5b57\u7b26\u4e32
//-------------------------------------
function simpleQuery(edits, iPageSize, iCurrentPage)
{
    var arr_Condition = [];
    var sFieldName, sValue, sShowValue, sOperation, sKind, len;
    var sXML = "";
    for (var i = 0; i < edits.length; i++) {
        if (edits[i].fieldname && edits[i].value.length > 0) {
            len = arr_Condition.length;
            sFieldName = edits[i].fieldname.toUpperCase();
            sKind = edits[i].kind.toLowerCase();
            if (sKind == "dic" || sKind == "query" || sKind == "more") {
                if (typeof(edits[i].code) == "string") {
                    sValue = edits[i].code.length > 0 ? edits[i].code : "";
                }
                else {
                    sValue = "";
                }
                sShowValue = edits[i].value;
            }
            else {
                if (typeof(edits[i].code) == "string") {
                    sValue = edits[i].code.length > 0 ? edits[i].code : edits[i].value;
                }
                else {
                    sValue = edits[i].value;
                }
                sShowValue = ""
            }
            sOperation = edits[i].operation ? edits[i].operation : "=";
            if (sOperation.toLowerCase() == "like") {
                sValue = "%" + sValue + "%";
            }
            arr_Condition[len] = makeCondition(sFieldName, sOperation, sValue, sShowValue);
        }
    }
    if (arr_Condition.length > 0) {
        var xmlConditions= makeConditions("and", arr_Condition);
        sXML = makeQueryCondition("", xmlConditions).xml;
    }
    return sXML;
}

//+------------------------------------
// \u83b7\u5f97\u590d\u6742\u67e5\u8be2\u7684 XML \u5b57\u7b26\u4e32
//-------------------------------------
function complexQuery(edits, iPageSize, iCurrentPage)
{
    return "";
}

//+------------------------------------
// \u8fdb\u884c\u67e5\u8be2,\u53d6\u5f97\u67e5\u8be2\u6761\u4ef6
// iIndex \uff1a Integer        \u9875\u9762\u5e8f\u53f7
// bComplex \uff1a Boolean      \u662f\u5426\u590d\u6742\u67e5\u8be2
//-------------------------------------
function doQuery(iIndex, bComplex)
{
    try {
        var sXML;
        var n = iIndex ? iIndex : 0;
        var pages = g_xTabPane.pages[n];
        var edits = pages.edits;
        var labels = pages.labels;
        var arr_Edit = [];
        var oBtn = g_frmPost.all("cmdSubmit");
        var oHidden = g_frmPost.all("txtXML");

        var i;
        var bIsAllBlank = true;

        oHidden.value = "";
        if (oBtn) {
            oBtn.disabled = true;
        }

        for (i = 0; i < edits.length; i++) {
            if (edits[i].value.length > 0 && edits[i].type != "hidden") {
                bIsAllBlank = false;
            }
        }

        if (bIsAllBlank) {
            i = 0;
            err("\u67e5\u8be2\u6761\u4ef6\u4e0d\u80fd\u4e3a\u7a7a");
        }


        for (i = 0; i < edits.length; i++) {
            if (edits[i].must == "true" && edits[i].value.length < 1) {
                err("[" + labels[i].innerText + "]\u662f\u5fc5\u5f55\u9879\uff01");
            }
            if (edits[i].error == true) {
                err("[" + labels[i].innerText + "]\u7684\u503c\u65e0\u6548\uff01");
            }
            if (edits[i].value.length > 0) {
                arr_Edit[arr_Edit.length] = edits[i];
            }
        }

        i = 0;

        if (bComplex) {
            sXML = complexQuery(arr_Edit);
        }
        else {
            sXML = simpleQuery(arr_Edit);
        }
        oHidden.value = sXML;
        return true;
    }
    catch (e) {
        printf(e.description);
        if (oBtn) {
            oBtn.disabled = false;
        }
        g_xTabPane.setSelectedIndex(n);
        edits[i].focus();
        return false;
    }
}


//+------------------------------------
// \u5224\u65ad\u662f\u5426\u4e3a\u7a7a
// strInput\uff1a\u5b57\u7b26\u4e32
// \u8fd4\u56de\uff1a
// true:\u4e3a\u7a7a
// false:\u4e0d\u4e3a\u7a7a
//-------------------------------------

function isEmpty(strInput){

    if (isBlank(strInput)){
        return true;
    }
    else{
        if(!strInput){
            return true;
        }
        else{
            if(strInput=="null"){
                return true;
            }
        }
    }
    return false;
}

//+------------------------------------
// \u8bbe\u7f6e\u63a7\u4ef6\u98ce\u683c
//-------------------------------------
function setStyle(sType)
{
    try {
	var obj=event.srcElement;
	m_bReadOnly=false;
    if(obj.readonly){m_bReadOnly=true}
        if (m_bReadOnly) return;
        with (obj.runtimeStyle) {
            color = eval("EDIT_COLOR_" + sType);
            //border = eval("EDIT_BORDER_" + sType);
            background = eval("EDIT_BACKGROUND_" + sType);
        }
        obj.tabPage.labels[obj.index].runtimeStyle.color = eval("LABEL_COLOR_" + sType);
    }
    catch (e) {}
}


//+------------------------------------
// \u83b7\u5f97\u5f53\u524d\u63d0\u793a
//-------------------------------------
function getCurrentHint()
{
    var sHint;
   var obj=event.srcElement;

   m_kind=(obj.kind)?obj.kind.toLowerCase():"";
    switch (m_kind) {
        case "dic":
            sHint = obj.hint ? obj.hint : STR_HINT_DIC;
            break;

        case "query":
            sHint = obj.hint ? obj.hint : STR_HINT_QUERY;
            break;

        case "more":
            sHint = obj.hint ? obj.hint : STR_HINT_MORE;
            break;

        case "date":
            sHint = obj.hint ? obj.hint : STR_HINT_DATA;
            break;

        case "time":
            sHint = obj.hint ? obj.hint : STR_HINT_TIME;
            break;

        case "datetime":
            sHint = obj.hint ? obj.hint : STR_HINT_DATATIME;
            break;

        case "idcard":
            sHint = obj.hint ? obj.hint : STR_HINT_IDCARD;
            break;

        case "int":
            sHint = obj.hint ? obj.hint : STR_HINT_INT;
            break;

        case "float":
            sHint = obj.hint ? obj.hint : STR_HINT_FLOAT;
            break;

        case "mask":
            sHint = obj.hint ? obj.hint : (obj.mask ? STR_HINT_MASK : "");
            break;

        default:
            sHint = obj.hint ? obj.hint : "";
    }
    return sHint;
}


//+------------------------------------
// \u5bf9\u8eab\u4efd\u8bc1\u7684\u5173\u8054\u5904\u7406
//-------------------------------------
function relatHandle(obj)
{
    // \u4ece\u8eab\u4efd\u8bc1\u53f7\u7801\u4e2d\u63d0\u53d6\u6027\u522b\u548c\u51fa\u751f\u65e5\u671f
    if(!obj){
		var obj=event.srcElement;
	}

    m_kind=(obj.kind)?obj.kind.toLowerCase():"";

m_sErrorInfo=obj.errorText;
    if (m_kind == "idcard") {
        var oSex, oBirthDate;
        try {
            var oSex = obj.sex.substr(0,1) == '#' ?
                obj.tabPage.edits[obj.sex.substr(1)] : window.document.getElementById(obj.sex);
            var oBirthDate = obj.birthdate.substr(0,1) == '#' ?
                oBirthDate = obj.tabPage.edits[obj.birthdate.substr(1)] : window.document.getElementById(obj.birthdate);
            var oOld = obj.birthdate.substr(0,1) == '#' ?
                oOld = obj.tabPage.edits[obj.old.substr(1)] : window.document.getElementById(obj.old);
        }
        catch (e) {}

        if (obj.value != "" && !m_bFormatError) {
            if (oSex) {
                var s = getSex(obj.value);
                g_bCheckChanged = false;
                oSex.value = s == "1" ? "\u7537" : (s == "2" ? "\u5973" : "\u672a\u77e5\u7684\u6027\u522b");
                oSex.code = s;
                g_bCheckChanged = false;
            }

            if (oBirthDate) {
                var s = getBirthday(obj.value);
                if (s) {
                    oBirthDate.value = s.substr(0,4) + "\u5e74" + s.substr(4,2) + "\u6708" + s.substr(6,2) + "\u65e5";
                    oBirthDate.code = s;
                }
            }
            if (oOld) {
               var s = getOld(obj.value);
               if (s) {
                    oOld.value=s
               }
            }
        }
    }


    if (obj.idcard) {
        var oIDCard;
        try {
            var oIDCard = obj.idcard.substr(0,1) == '#' ?
                obj.tabPage.edits[obj.idcard.substr(1)] : window.document.getElementById(obj.idcard);
        }
        catch (e) {}

        if (oIDCard) {
            // \u8eab\u4efd\u8bc1\u5408\u6cd5?
            if (isIDCard(oIDCard.value)) {

                if (m_kind == "dic") {
                    // \u5224\u65ad\u6027\u522b\u662f\u5426\u4e0e\u8eab\u4efd\u8bc1\u76f8\u7b26\u5408
                    var s = getSex(oIDCard.value);
                    if (parseInt(s) != parseInt(obj.code)) {
                         doError(MASK_SEX_ERROR);
                    }
                    else {
                        obj.errorText = "";
                    }
                }

                if (m_kind == "date") {
                    // \u5224\u65ad\u51fa\u8eab\u65e5\u671f\u662f\u5426\u4e0e\u8eab\u4efd\u8bc1\u76f8\u7b26\u5408
                    var s = getBirthday(oIDCard.value);
                    if (parseInt(s) != parseInt(obj.code)) {
                         doError(MASK_BIRTHDAY_ERROR);
                    }
                    else {
                        obj.errorText = "";
                    }
                }
            }
        }
    }
}

//+------------------------------------
// \u683c\u5f0f\u5316\u5f55\u5165\u6846\u7684\u503c
//-------------------------------------
function formatValue(obj)
{

    if(!obj){
		var obj=event.srcElement;
	}

    m_kind=(obj.kind)?obj.kind.toLowerCase():"";

    m_sErrorInfo=obj.errorText;
    var sTemp = obj.value;

    m_bFormatError = false;


    if (sTemp.length < 1) {
        return;
    }

    switch (m_kind) {
        case "date":
            sTemp = checkDate(sTemp,obj);
            break;

        case "time":
            sTemp = checkTime(sTemp,obj);
            break;

        case "datetime":
            sTemp = checkDateTime(sTemp,obj);
            break;

        case "idcard":
            sTemp = checkIDCard(sTemp,obj);
            break;

        case "xm":
            sTemp = checkName(sTemp,obj);
            break;

        case "int":
            sTemp = checkInt(sTemp,obj);
            break;

        case "float":
            sTemp = checkFloat(sTemp,obj);
            break;

        case "mask":
            sTemp = checkMask(sTemp,obj);
            break;

        default:
            break;
    }

    if (m_bFormatError) {
    	obj.error=true;
        doError(obj.errorText);
    }
    else {
        g_bCheckChanged = false;
        obj.value = sTemp;
        g_bCheckChanged = false;
        obj.errorText=""
    }
}

function doError(sMsg)
{
    var obj=event.srcElement;
    alert(sMsg);
    obj.select();
}

//+------------------------------------
// \u683c\u5f0f\u5316\u6587\u672c\u503c
//-------------------------------------
function formatText(obj)
{
	if(!obj){
		var obj=event.srcElement;
	}
	m_kind=(obj.kind)?obj.kind.toLowerCase():"";
	m_sErrorInfo=obj.errorText;
    var sTemp = obj.innerText;

    m_bFormatError = false;


    if (sTemp.length < 1) {
        return;
    }

    m_bFormatError = false;

    switch (m_kind) {
        case "date":
            sTemp = checkDate(sTemp,obj);
            break;

        case "time":
            sTemp = checkTime(sTemp,obj);
            break;

        case "datetime":
            sTemp = checkDateTime(sTemp,obj);
            break;

        default:
            break;
    }

    if (m_bFormatError) {
        doError(obj.errorText);
    }
    else {
        obj.innerText = sTemp;
    }
}


//+------------------------------------
// \u68c0\u67e5\u65e5\u671f
//-------------------------------------
function checkDate(sValue)
{
    try {
        var sTemp;
        var bFlag = false;

        var sTemp;
        var bFlag = false;
        if (/^\d{4}[-|\/.]\d{1,2}([-|\/.]\d{1,2}){0,1}$/.test(sValue)) {  // yyyy-mm-dd \u6216 yyyy/mm/dd \u6216 yyyy.mm.dd
            var ss = sValue.replace(/[\/.]/g, "-");
            ss = ss.split("-");
            ss[2] = (ss.length == 2) ? "01" : ss[2];
            sTemp = ss[0] + patchLen(ss[1]) + patchLen(ss[2]);
            bFlag = true;
        }
        else if (/^\d{4}\u5e74\d{2}\u6708\d{2}\u65e5$/.test(sValue)) {  // yyyy\u5e74mm\u6708dd\u65e5
            bFlag = true;
            sTemp = sValue.substr(0,4) + sValue.substr(5,2) + sValue.substr(8,2);
        }
        else if (/^\d{4}$/.test(sValue)) {  // yyyy added by zxh on 2004.05.19 16:06
            sTemp = sValue + "0101";
            bFlag = true;
        }
        else if (/^\d{6}$/.test(sValue)) {  // yyyymm
            sTemp = sValue + "01";
            bFlag = true;
        }
        else if (/^\d{8}$/.test(sValue)) {  // yyyymmdd
            sTemp = sValue;
            bFlag = true;
        }

        if (!bFlag) {
            throw 0;
        }

        if (isDate(sTemp)) {
            var year = sTemp.substr(0, 4);
            var month = sTemp.substr(4, 2);
            var day = sTemp.substr(6, 2);
            var c = "/";
            return year + "-" + month + "-" + day;
        }
        else {
            throw 0;
        }
    }
    catch (e) {
        m_bFormatError = true;
        return sValue;
    }
}

function checkDate(sValue,obj)
{
    try {
        var sTemp;
        var bFlag = false;

        var sTemp;
        var bFlag = false;
        if(!obj){
			var obj=event.srcElement;
		}
        if (/^\d{4}[-|\/.]\d{1,2}([-|\/.]\d{1,2}){0,1}$/.test(sValue)) {  // yyyy-mm-dd \u6216 yyyy/mm/dd
            var ss = sValue.replace(/[\/.]/g, "-");
            ss = ss.split("-");
            ss[2] = (ss.length == 2) ? "01" : ss[2];
            sTemp = ss[0] + patchLen(ss[1]) + patchLen(ss[2]);
            bFlag = true;
        }
        else if (/^\d{4}\u5e74\d{2}\u6708\d{2}\u65e5$/.test(sValue)) {  // yyyy\u5e74mm\u6708dd\u65e5
            bFlag = true;
            sTemp = sValue.substr(0,4) + sValue.substr(5,2) + sValue.substr(8,2);
        }
        else if (/^\d{4}$/.test(sValue)) {  // yyyy added by zxh on 2004.05.19 16:06
            sTemp = sValue + "0101";
            bFlag = true;
        }
        else if (/^\d{6}$/.test(sValue)) {  // yyyymm
            sTemp = sValue + "01";
            bFlag = true;
        }
        else if (/^\d{8}$/.test(sValue)) {  // yyyymmdd
            sTemp = sValue;
            bFlag = true;
        }

        if (!bFlag) {
            throw 0;
        }

        if (isDate(sTemp)) {
            var year = sTemp.substr(0, 4);
            var month = sTemp.substr(4, 2);
            var day = sTemp.substr(6, 2);
            var c = "/";
            obj.code = year + month + day;
            return year + "-" + month + "-" + day;
        }
        else {
            throw 0;
        }
    }
    catch (e) {
        m_bFormatError = true;
        if(!obj){
	    var obj=event.srcElement;
	}
        obj.errorText = DATE_FORMAT_ERROR;
        return sValue;
    }
}

function patchLen(str){return str.length!=2?"0"+str:str}

//+------------------------------------
// \u68c0\u67e5\u65f6\u95f4
//-------------------------------------
function checkTime(sValue,obj)
{
    try {
        var sTemp = sValue;
        var bFlag = false;

        if(!obj){
			var obj=event.srcElement;
		}

        if (/^\d{1,2}:\d{1,2}(:\d{1,2}){0,1}$/.test(sValue)) {  // hh:mm:ss \u6216 hh:mm
            var ss = sValue.split(":");
            ss[2] = (ss.length == 2) ? "00" : ss[2];
            sTemp = patchLen(ss[0]) + patchLen(ss[1]) + patchLen(ss[2]);
            bFlag = true;
        }
        else if (/^\d{4}$/.test(sValue)) {  // hhmm
            sTemp = sValue.substr(0,2) + sValue.substr(2,2) + "00";
            bFlag = true;
        }
        else if (/^\d{6}$/.test(sValue)) {  // hhmmss
            sTemp = sValue.substr(0,2) + sValue.substr(2,2) + sValue.substr(4,2);
            bFlag = true;
        }
        else if (/^\d{2}\u65f6\d{2}\u5206\d{2}\u79d2$/.test(sValue)) {  // hh\u65f6mm\u5206ss\u79d2
            sTemp = sValue.substr(0,2) + sValue.substr(3,2) + sValue.substr(6,2);
            bFlag = true;
        }

        if (!bFlag) {
            throw 0;
        }

        if (isTime(sTemp)) {
            var h = sTemp.substr(0,2);
            var m = sTemp.substr(2,2);
            var s = sTemp.substr(4,2);
            var c = ":";
            obj.code = h + c + m + c + s;
            return h + ":" + m + ":" + s;
        }
        else {
            throw 0;
        }
    }
    catch (e) {
        m_bFormatError = true;
        if(!obj){
			var obj=event.srcElement;
		}
        obj.errorText  = TIME_FORMAT_ERROR;
        return sValue;
    }
}


//+------------------------------------
// \u68c0\u67e5\u65e5\u671f\u65f6\u95f4
//-------------------------------------
function checkDateTime(sValue,obj)
{
    try {
        var sTemp = sValue.replace(new RegExp("[ :/-]/g"), "");
        var bFlag = false;
	if(!obj){
	    var obj=event.srcElement;
	}
        if (/^\d{12}$/.test(sTemp))  // YYYYMMDDhhmm
            bFlag = true;
        else if (/^\d{14}$/.test(sTemp))  // YYYYMMDDhhmmss
            bFlag = true;
        else if(/^\d{8}$/.test(sTemp))  // YYYYMMDD
			bFlag = true;
		else if (/^\d{8}\s\d{2}$/.test(sTemp))  // YYYYMMDD hh
            bFlag = true;
        else if (/^\d{8}\s\d{4}$/.test(sTemp))  // YYYYMMDD hhmm
            bFlag = true;
        else if (/^\d{8}\s\d{6}$/.test(sTemp))  // YYYYMMDD hhmmss
            bFlag = true;
        else if (new RegExp("^(\d{4}[/|-]\d{2}[/|-]\d{2})\s(\d{2}:\d{2}(:\d{2}){0,1})$").test(sTemp))  // YYYY-MM-DD hh:mm:ss \u6216 YYYY-MM-DD hh:mm
            bFlag = true;
        else if (new RegExp("^(\d{4}[/|-]\d{2}[/|-]\d{2})\s\d{4}$").test(sTemp))  // YYYY-MM-DD hhmm
            bFlag = true;
        else if (new RegExp("^(\d{4}[/|-]\d{2}[/|-]\d{2})\s\d{6}$").test(sTemp))  // YYYY-MM-DD hhmmss
            bFlag = true;
        else if (new RegExp("^\d{8}\s(\d{2}:\d{2}(:\d{2}){0,1})$").test(sTemp))  // YYYYMMDD hh:mm:ss \u6216 YYYYMMDD hh:mm
            bFlag = true;
        else if (new RegExp("^\d{8}\s(\d{2}\u65f6\d{2}\u5206)$").test(sTemp))  // YYYYMMDD hh\u65f6mm\u5206
            bFlag = true;
        else if (/^\d{8}\s(\d{2}\u65f6\d{2}\u5206\d{2}\u79d2)$/.test(sTemp))  // YYYYMMDD hh\u65f6mm\u5206ss\u79d2
            bFlag = true;
        else if (/^(\d{4}\u5e74\d{2}\u6708\d{2}\u65e5)\s\d{4}$/.test(sTemp))  // YYYY\u5e74MM\u6708DD\u65e5 hhmm
            bFlag = true;
        else if (/^(\d{4}\u5e74\d{2}\u6708\d{2}\u65e5)\s\d{6}$/.test(sTemp))  // YYYY\u5e74MM\u6708DD\u65e5 hhmmss
            bFlag = true;
        else if (/^(\d{4}\u5e74\d{2}\u6708\d{2}\u65e5)\s(\d{2}:\d{2}(:\d{2}){0,1})$/.test(sTemp))  // YYYY\u5e74MM\u6708DD\u65e5 hh:mm:ss \u6216 YYYY\u5e74MM\u6708DD\u65e5 hh:mm
            bFlag = true;
        else if (/^(\d{4}\u5e74\d{2}\u6708\d{2}\u65e5)\s(\d{2}\u65f6\d{2}\u5206)$/.test(sTemp))  // YYYY\u5e74MM\u6708DD\u65e5 hh\u65f6mm\u5206
            bFlag = true;
        else if (/^(\d{4}\u5e74\d{2}\u6708\d{2}\u65e5)\s(\d{2}\u65f6\d{2}\u5206\d{2}\u79d2)$/.test(sTemp))  // YYYY\u5e74MM\u6708DD\u65e5 hh\u65f6mm\u5206ss\u79d2
            bFlag = true;
        else if (/^(\d{4}-\d{2}-\d{2})\s(\d{2}\u65f6\d{2}\u5206)$/.test(sTemp))     // YYYY-MM-DD hh\u65f6mm\u5206
            bFlag = true;
        else if (/^(\d{4}-\d{2}-\d{2})\s(\d{2}\u65f6\d{2}\u5206\d{2}\u79d2)$/.test(sTemp))     // YYYY-MM-DD hh\u65f6mm\u5206ss\u79d2
            bFlag = true;

        if(!bFlag){throw 0;}

        sTemp = sTemp.replace(/[\u5e74\u6708\u65e5\u65f6\u5206\u79d2]/g, "");
        sTemp = sTemp.replace(new RegExp("[ :/-]/g"), "");
        if(isDateTime1(sTemp)){
            var year = sTemp.substr(0,4);
            var month = sTemp.substr(4,2);
            var day = sTemp.substr(6,2);
            var h = (sTemp.length >= 10) ?sTemp.substr(8,2) : "00";
            var m = (sTemp.length >= 12) ?sTemp.substr(10,2) : "00";
            var s = (sTemp.length == 14) ? sTemp.substr(12,2) : "00";
            var c1 = "-";
            var c2 = ":";

	    var obj=event.srcElement;
            obj.code = year + month + day + " " + h + c2 + m + c2 + s;
            return year + c1 + month + c1 + day + " " + h + c2 + m + c2 + s;
        }
        else{
            throw 0;
        }
    }
    catch (e) {
        m_bFormatError = true;
        if(!obj){
			var obj=event.srcElement;
		}
        obj.errorText = DATETIME_FORMAT_ERROR;
        return sValue;
    }
}

//+------------------------------------
// \u6821\u9a8c\u65e5\u671f\uff0b\u65f6\u95f4\u683c\u5f0f\u662f\u5426\u4e3aYYYYmmDDhhMMss
//-------------------------------------
function isDateTime1(sValue)
{
    var s = sValue.trim();
    if (s.length > 14)
        return false;
    else if (isNaN(s))
        return false;
    else
        return verifyDateTime(s.substr(0,4),s.substr(4,2),s.substr(6,2),
                s.substr(8,2),s.substr(10,2),s.substr(12,2));
}

//+------------------------------------
// \u68c0\u67e5\u8eab\u4efd\u8bc1
//-------------------------------------
function checkIDCard(sValue,obj)
{
    try {
        if (isIDCard(sValue)) {
            return m_bUpIDCard ? UpID(sValue) : sValue;
        }
        else {
            throw 0;
        }
    }
    catch (e) {
        m_bFormatError = true;
        if(!obj){
	   var obj=event.srcElement;
	}
        obj.errorText  = IDCARD_FORMAT_ERROR;
        return sValue;
    }
}

//+------------------------------------
// \u68c0\u67e5\u59d3\u540d
//-------------------------------------
function checkName(sValue,obj)
{
    try {
	if(!obj){
		var obj=event.srcElement;
	}
	if (sValue.length<2) {
		obj.erroeText = LESS_NAME_LENGTH;
		throw 0;
	}
	return sValue;
    }
    catch (e) {
        m_bFormatError = true;
        return sValue;
    }
}

//+------------------------------------
// \u68c0\u67e5\u6574\u6570\u503c
//-------------------------------------
function checkInt(sValue,obj)
{
    try {
		if(!obj){
			var obj=event.srcElement;
		}
        if (isInt(sValue)) {
            if (!checkRange(sValue)) {

        obj.errorText  = OVER_RANGE_ERROR + obj.range;
                throw 0;
            }
            return parseInt(sValue, 10);
        }
        else {

        obj.errorText  = NUMBER_FORMAT_ERROR;
            throw 0;
        }
    }
    catch (e) {
        m_bFormatError = true;
        return sValue;
    }
}


//+------------------------------------
// \u68c0\u67e5\u6d6e\u70b9\u503c
//-------------------------------------
function checkFloat(sValue,obj)
{
    try {
		if(!obj){
			var obj=event.srcElement;
		}
        if (isFloat(sValue)) {
            if (!checkRange(sValue)) {

        obj.errorText  = OVER_RANGE_ERROR + obj.range;
                throw 0;
            }
            return parseFloat(sValue);
        }
        else {

        obj.errorText  = NUMBER_FORMAT_ERROR;
            throw 0;
        }
    }
    catch (e) {
        m_bFormatError = true;
        return sValue;
    }
}


//+------------------------------------
// \u68c0\u67e5\u63a9\u7801\u503c(# -- \u6570\u5b57; @ -- \u5b57\u6bcd)
//-------------------------------------
function checkMask(sValue,obj)
{
    try {
		if(!obj){
			var obj=event.srcElement;
		}
        if (obj.mask) {
            if (sValue.length != obj.mask.length)
                throw 0;

            for (var i=0; i<obj.mask.length; i++) {
                switch (obj.mask.charAt(i)) {
                    case "#":
                        if (!isInt(sValue.charAt(i)))
                            throw 0;
                        break;
                    case "@":
                        if (!isLetter(sValue.charAt(i)))
                            throw 0;
                        break;
                    default:
                        if (obj.mask.charAt(i) != sValue.charAt(i))
                            throw 0;
                }
            }
        }
        return sValue;
    }
    catch (e) {
        m_bFormatError = true;

        obj.errorText  = MASK_FORMAT_ERROR + "<br>\u6b63\u786e\u7684\u683c\u5f0f\uff1a<br><span style='font:8pt'>" +
                obj.mask + "</span>";
        return sValue;
    }
}


//+------------------------------------
// \u68c0\u67e5\u8303\u56f4
//-------------------------------------
function checkRange(sValue,obj)
{
    try {
		if(!obj){
			var obj=event.srcElement;
		}
        var range = [];
        if (obj.range) {
            eval("range = " + obj.range);
            if (sValue < range[0] || sValue > range[1]) {
                throw 0;
            }
        }
        return true;
    }
    catch (e) {
        return false;
    }
}

//+------------------------------------
// \u751f\u6210\u5f02\u5e38
//-------------------------------------
function err(sDesc, iNumber)
{
    var number = iNumber ? iNumber : 0;
    e = new Error(number, sDesc);
    throw e;
}

//+------------------------------------
// \u5728\u65b0\u7a97\u53e3\u67e5\u770b xml \u5b57\u7b26\u4e32
//-------------------------------------
function seeXML(sXML)
{
    var w = window.open(escapeUrl(""));
    w.document.write("<xmp>" + sXML + "</xmp>");
}

//+------------------------------------
// \u8f93\u51fa\u63d0\u793a
//-------------------------------------
function printf(sMsg)
{
    alert(sMsg);
}

//-----------------------------------------------
// \u53d6\u968f\u673a\u6570
//-----------------------------------------------
function getRandom () {
    if((navigator.appName == "Netscape") &&
      ((navigator.appVersion.indexOf("2.0") >= 0) ||
      (navigator.appVersion.indexOf("Win3") >= 0))) {
        now = new Date();
        // pseudo random
        return (now.getTime() % 1000000) / 1000000;
    } else {
        return Math.random();
    }
}

//-----------------------------------------------
// \u66ff\u6362 > < \u4e3a &gt; &lt;
//-----------------------------------------------
function re(s)
{
    return s.replace(/>/g, "&gt;").replace(/</g, "&lt;");
}

//-----------------------------------------------
// \u66ff\u6362 &gt; &lt; \u4e3a > <
//-----------------------------------------------
function unRe(s)
{
    return s.replace(/&gt;/g, ">").replace(/&lt;/g, "<");
}


///////////////////////////////////////////////////////////////////////////////////
// \u6d4f\u89c8\u5668\u68c0\u67e5
///////////////////////////////////////////////////////////////////////////////////

//if (!hasSupportBehaviors()) {
//    alert("\u5f88\u62b1\u6b49\u4f60\u7684\u6d4f\u89c8\u5668\u4e0d\u652f\u6301 Behaviors \u7279\u6027\n\u53ea\u6709 IE5.5 \u4ee5\u4e0a\u7684\u6d4f\u89c8\u5668\u624d\u652f\u6301\uff01");
//}

//+------------------------------------
// \u68c0\u67e5\u6d4f\u89c8\u5668\u662f\u5426\u652f\u6301 Behaviors \uff08IE5.5 \u4ee5\u4e0a\u624d\u652f\u6301\uff09
//-------------------------------------
function hasSupportBehaviors()
{
    if (typeof(hasSupportBehaviors.support) != "undefined")
       //return hasSupportBehaviors.support;
       return true

    var ua = window.navigator.userAgent;
    var msiePos = ua.indexOf("MSIE");
    var msieVer = 0;
    var behaviorsAvailable = false;
    var iHandle = 0;
    if (msiePos >= 0) {
        msieMajorVer = parseInt(ua.charAt(msiePos + 5));
        msieMinorVer = parseInt(ua.charAt(msiePos + 7));
        if (msieMajorVer >= 5) {
            if (((msieMajorVer == 5) && (msieMinorVer >= 5)) ||
                (msieMajorVer > 5))
            {
                hasSupportBehaviors.support = true;
            }
        }
    }
    //return hasSupportBehaviors.support;
    return true;
}

//+------------------------------------
// \u68c0\u67e5\u6d4f\u89c8\u5668\u662f\u5426\u652f\u6301
//-------------------------------------
function hasSupport()
{
    if (typeof(hasSupport.support) != "undefined")
       // return hasSupport.support;
        return true;

    var ie55 = /msie 5\.[56789]/i.test(navigator.userAgent);

    hasSupport.support = (typeof(document.implementation) != "undefined" &&
            document.implementation.hasFeature("html", "1.0") || ie55)

    //return hasSupport.support;
    return true
}




///////////////////////////////////////////////////////////////////////////////////
// \u5b57\u7b26\u4e32\u5904\u7406
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// trim     \u53bb\u9664\u5b57\u7b26\u4e32\u4e24\u7aef\u7684\u7a7a\u683c
// trimL    \u9664\u53bb\u5b57\u7b26\u4e32\u5de6\u8fb9\u7684\u7a7a\u683c
// trimR    \u9664\u53bb\u5b57\u7b26\u4e32\u53f3\u8fb9\u7684\u7a7a\u683c
// stripBlanks  \u5265\u53bb\u5b57\u7b26\u4e32\u4e2d\u7684\u7a7a\u683c
// lenB     \u8fd4\u56de\u6307\u5b9a\u5b57\u7b26\u4e32\u4e2d\u7684\u5b57\u8282\u4e2a\u6570(\u5907\u6ce8:\u4e2d\u6587\u5b57\u7b26\u53602\u4e2a\u5b57\u8282)
// contain  \u662f\u5426\u5305\u542b\u6307\u5b9a\u5b57\u7b26\u4e32
// left     \u4ece\u5de6\u8fb9\u5f00\u59cb\u622a\u53d6\u6307\u5b9a\u957f\u5ea6\u7684\u5b57\u7b26\u4e32
// right    \u4ece\u53f3\u8fb9\u5f00\u59cb\u622a\u53d6\u6307\u5b9a\u957f\u5ea6\u7684\u5b57\u7b26\u4e32
// urlEncode \u8f6c\u6362\u5b57\u7b26\u4e32\u4e2d\u7684#\u4e0e&\u7b26\u53f7,\u7528\u4e8eget\u65b9\u6cd5\u4f20\u9012\u53c2\u6570
//-------------------------------------
String.prototype.trim=function(){return this.replace(/(^\s*)|(\s*$)/g,"")}
String.prototype.trimL=function(){return this.replace(/(^\s*)/g,"")}
String.prototype.trimR=function(){return this.replace(/(\s*$)/g,"")}
String.prototype.stripBlanks=function(){return this.replace(/\s/g, "")}
String.prototype.lenB=function(){try{return this.match(/[^\x00-\xff]/g).length+this.length}catch(e){return this.length}}
String.prototype.contain=function(str){return (this.indexOf(str)!=-1)?true:false}
String.prototype.left=function(n){var len=this.length;if(len<n) return false; return this.substring(0,n);}
String.prototype.right=function(n){var len=this.length;if(len<n) return false; return this.substring(len-n,len);}
String.prototype.urlEncode=function(){return this.replace(/(#)/g,"%23").replace(/(&)/g,"%26");}


///////////////////////////////////////////////////////////////////////////////////
// \u65e5\u671f\u68c0\u67e5\u51fd\u6570
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// \u5224\u65ad\u662f\u5426\u662f\u95f0\u5e74
//-------------------------------------
function isLeapYear(iYear){return ((!(iYear%4)&&(iYear%100))||!(iYear%400))?true:false}

//+------------------------------------
// \u5904\u7406\u5c0f\u4e8e1000\u7684\u5e74\u4efd\uff0c\u5c06\u5176\u52a0\u4e0a1900
//-------------------------------------
function y2k(iYear){return (iYear<1000)?iYear+1900:iYear}

//+------------------------------------
// \u83b7\u5f97\u7cfb\u7edf\u5f53\u524d\u65e5\u671f
//-------------------------------------
function getNowDate()
{
    var today = new Date();
    var y = today.getFullYear() + "";
    var m = today.getMonth() + 1 + "";
    m = m.length == 1 ? "0" + m : m;
    var d = today.getDate()+"";
    d = d.length == 1 ? "0" + d : d;
    return y + m + d;
}

function getNowDatetoStr()
{
    var today = new Date();
    var y = today.getFullYear() + "";
    var m = today.getMonth() + 1 + "";
    m = m.length == 1 ? "0" + m : m;
    var d = today.getDate()+"";
    d = d.length == 1 ? "0" + d : d;
    return y +"-"+ m +"-"+ d;
}

//+------------------------------------
// \u83b7\u5f97\u7cfb\u7edf\u5f53\u524d\u65f6\u95f4
//-------------------------------------
function getNowTime()
{
    var today = new Date();
    var h = today.getHours() + "";
    h = h.length == 1 ? "0" + h : h;
    var m = today.getMinutes() + "";
    m = m.length == 1 ? "0" + m : m;
    var s = today.getSeconds() + "";
    s = s.length == 1 ? "0" + s : s;
    return h + m + s;
}

//+------------------------------------
// \u5224\u65ad\u6307\u5b9a\u65e5\u671f\u662f\u5426\u6b63\u786e
// \u5f53\u8f93\u5165\u53c2\u6570\u4e3averifyDate(dd,mm,ccyy)\u65f6\uff0c\u8868\u793a\u8981\u68c0\u67e5\u5e74\uff0c\u6708\uff0c\u65e5
// \u5f53\u8f93\u5165\u53c2\u6570\u4e3averifyDate(dd,mm) \u8868\u793a\u9ed8\u8ba4\u5e74\u4e3a\u5f53\u524d\u5e74
// \u5f53\u8f93\u5165\u53c2\u6570\u4e3averifyDate(dd)    \u8868\u793a\u9ed8\u8ba4\u5e74\uff0c\u6708\u4e3a\u5f53\u524d\u5e74\u6708
// \u6ce8\u610f\uff1a\u8f93\u5165\u6708\u4efd\u4fdd\u8bc1\u57281-12\u4ee5\u5185\u3002
//-------------------------------------
function verifyDate(day,month,year)
{
    if (!day) return false;
    var iToday = new Date();
    month = month ? month-1 : iToday.getMonth();
    year = year ? y2k(parseInt(year)) : iToday.getFullYear();
    var iDate = new Date(year,month,day);
    if ((iDate.getFullYear() == year) && (iDate.getMonth() == month) && (iDate.getDate() == day))
        return true;
    else
        return false;
}

//+------------------------------------
// \u5224\u65ad\u6307\u5b9a\u65f6\u95f4\u683c\u5f0f\u662f\u5426\u6b63\u786e
// \u6ce8\u610f\uff1a\u8f93\u5165\u5c0f\u65f6\u4fdd\u8bc1\u57280(\u5348\u591c)-23(\u665a\u4e0a11\u70b9)
//      \u8f93\u5165\u5206\u949f\u4fdd\u8bc1\u57280-59
//      \u8f93\u5165\u79d2\u6570\u4fdd\u8bc1\u57280-59
//-------------------------------------
function verifyTime(hh,mm,ss)
{
    var iDate = new Date(1977,10,27,hh,mm,ss);
    if ((iDate.getHours() == hh) && (iDate.getMinutes() == mm) && (iDate.getSeconds() == ss))
        return true;
    else
        return false;
}

//+------------------------------------
// \u5224\u65ad\u6307\u5b9a\u65e5\u671f\u65f6\u95f4\u683c\u5f0f\u662f\u5426\u6b63\u786e
// \u6ce8\u610f\uff1a\u8f93\u5165\u5c0f\u65f6\u4fdd\u8bc1\u57280(\u5348\u591c)-23(\u665a\u4e0a11\u70b9)
//      \u8f93\u5165\u5206\u949f\u4fdd\u8bc1\u57280-59
//      \u8f93\u5165\u79d2\u6570\u4fdd\u8bc1\u57280-59
//-------------------------------------
function verifyDateTime(year,month,day,hh,mm,ss)
{
    if (!verifyDate(day,month,year)) return false;
    var iToday = new Date();
    return verifyTime(hh,mm,ss);
}




///////////////////////////////////////////////////////////////////////////////////
// \u8eab\u4efd\u8bc1\u5904\u7406\u51fd\u6570
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// \u6839\u636e17\u4f4d\u8eab\u4efd\u8bc1\u7b97\u51fa18\u4f4d\u8eab\u4efd\u8bc1
//-------------------------------------
function CalID_17to18(sId)
{
    var aW = new Array(1,2,4,8,5,10,9,7,3,6,1,2,4,8,5,10,9,7);
    var aA = new Array("1","0","X","9","8","7","6","5","4","3","2");
    var aP = new Array(17);
    var aB = new Array(17);
    var i,iSum = 0;

    for (i=1;i<18;i++)
        aP[i] = sId.substr(17-i, 1);
    for (i=1;i<18;i++) {
        aB[i] = parseInt(aP[i]) * parseInt(aW[i]);
        iSum += aB[i];
    }
    return sId + aA[iSum%11];
}

//+------------------------------------
// \u6839\u636e15\u4f4d\u8eab\u4efd\u8bc1\u7b97\u51fa18\u4f4d\u8eab\u4efd\u8bc1
//-------------------------------------
function CalID_15to18(sId)
{
    return CalID_17to18(sId.substr(0,6) + "19" + sId.substr(6));
}

//+------------------------------------
// \u628a15\u621617\u4f4d\u8eab\u4efd\u8bc1\u5347\u7ea7\u4f4d18\u4f4d\uff0c\u5e76\u8fdb\u884c\u5224\u65ad
//-------------------------------------
function UpID(sId)
{
    var s = sId.trim();
    if (s.length == 15) {
        if (isNaN(sId)) return false;
        if (verifyDate(s.substr(10,2),s.substr(8,2),s.substr(6,2)))
            return CalID_15to18(s);
        else
            return false;
    }
    else if (s.length == 17) {
        if (isNaN(s)) return false;
        if (verifyDate(s.substr(12,2),s.substr(10,2),s.substr(6,4)))
            return CalID_17to18(s);
        else
            return false;
    }
    else if (s.length == 18) {
        if (isNaN(s.substr(0,17))) return false;
        if (verifyDate(s.substr(12,2),s.substr(10,2),s.substr(6,4))) {
            if (CalID_17to18(s.substr(0,17)) == s)
                return s;
            else
                return false;
        }
        else
            return false;
    }
    return false;
}

//+------------------------------------
// \u4ece\u8eab\u4efd\u8bc1\u53f7\u7801\u4e2d\u63d0\u53d6\u51fa\u751f\u65e5\u671f
//-------------------------------------
function getBirthday(sId)
{
    var s = UpID(sId);
    return s ? checkDate(s.substr(6,8)) : "";
}

//+------------------------------------
// \u4ece\u8eab\u4efd\u8bc1\u53f7\u7801\u4e2d\u63d0\u53d6\u6027\u522b\uff0c\u5e74\u9f84
// (0--\u672a\u77e5\u7684\u6027\u522b; 1--\u7537; 2--\u5973)
//-------------------------------------
function getSex(sId)
{
    var s = UpID(sId);
    return s ? (parseInt(s.substr(16,1))%2 ? "\u7537" : "\u5973") : "";
}

function getOld(sId)
{
    var s=UpID(sId);
    var dNow=new Date()
    var iOld=parseInt(dNow.getFullYear())-parseInt(s.substr(6,4))
    return iOld
}


///////////////////////////////////////////////////////////////////////////////////
// \u6570\u503c\u6821\u9a8c\u51fd\u6570
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// \u6821\u9a8c\u8eab\u4efd\u8bc1
//-------------------------------------
function isIDCard(sValue)
{
    return UpID(sValue) ? true : false;
}

//+------------------------------------
// \u6821\u9a8c\u65e5\u671f\u683c\u5f0f\u662f\u5426\u4e3ayyyyMMdd
//-------------------------------------
function isDate(sValue)
{
    var s = sValue.trim();
    if (s.length != 8)
        return false;
    else if (isNaN(s))
        return false;
    else
        return verifyDate(s.substr(6),s.substr(4,2),s.substr(0,4));
}


//+------------------------------------
// \u6821\u9a8c\u65f6\u95f4\u683c\u5f0f\u662f\u5426\u4e3ahhMMss(24\u5c0f\u65f6\u5236)
//-------------------------------------
function isTime(sValue)
{
    var s = sValue.trim();
    if (s.length != 6)
        return false;
    else if (isNaN(s))
        return false;
    else
        return verifyTime(s.substr(0,2),s.substr(2,2),s.substr(4,2));
}


//+------------------------------------
// \u6821\u9a8c\u65e5\u671f\uff0b\u65f6\u95f4\u683c\u5f0f\u662f\u5426\u4e3aYYYYmmDDhhMMss
//-------------------------------------
function isDateTime(sValue)
{
    var s = sValue.trim();
    if (s.length != 14)
        return false;
    else if (isNaN(s))
        return false;
    else
        return verifyDateTime(s.substr(0,4),s.substr(4,2),s.substr(6,2),
                s.substr(8,2),s.substr(10,2),s.substr(12,2));
}

//+------------------------------------
//\u4e24\u4e2a\u65f6\u95f4(YYYYMMDD)\u7684\u6bd4\u8f83
//-------------------------------------
function compareDate(sValue1,sValue2)
{
    var s1 = sValue1.trim();
    var s2 = sValue2.trim();
    s1 = s1.replace(/[-\/: ]/g, "");
    s2 = s2.replace(/[-\/: ]/g, "");

    if (s1>=8) s1=s1.substring(0,8);
    if (s2>=8) s2=s2.substring(0,8);

    if (isDate(s1)&&isDate(s2)) {
      if (s1==s2)
        return true;
      else if(s1>s2) return "morethan";
      else return "lessthan";
    } else
      return false;
}
//+------------------------------------
//\u4e24\u4e2a\u65f6\u95f4(YYYYmmddhhmiss)\u7684\u6bd4\u8f83
//-------------------------------------
function compareDateTime(sValue1,sValue2)
{
    var s1 = sValue1.trim();
    var s2 = sValue2.trim();
    s1 = s1.replace(/[-\/: ]/g, "");
    s2 = s2.replace(/[-\/: ]/g, "");

    if (isDateTime(s1)&&isdatetime(s2)) {
      if (s1==s2)
        return true;
      else if(s1>s2) return "morethan";
      else return "lessthan";
    } else
      return false;
}

//+------------------------------------
// \u662f\u5426\u662f\u6574\u6570
//-------------------------------------
function isInt(sValue)
{
    return /^\d*$/.test(sValue);
}

//+------------------------------------
// \u662f\u5426\u662f\u6d6e\u70b9\u6570
//-------------------------------------
function isFloat(sValue)
{
    return (/^\d*\.\d+$/.test(sValue) || /^\d*$/.test(sValue));
}

//+------------------------------------
// \u662f\u5426\u662f\u6570\u5b57
//-------------------------------------
function isNumber(sValue)
{
    return isInt(sValue) || isFloat(sValue);
}

//+------------------------------------
// \u662f\u5426\u662f\u5b57\u6bcd
//-------------------------------------
function isLetter(sValue)
{
    return /^[A-Za-z]*$/.test(sValue);
}

//+------------------------------------
// \u662f\u5426\u662f\u5b57\u6bcd\u6216\u6570\u5b57
//-------------------------------------
function isLetterOrNumber(sValue)
{
    return /^\w*$/.test(sValue);
}

//+------------------------------------
// \u662f\u5426\u662f\u7a7a
//-------------------------------------
function isBlank(sValue)
{
    return /^\s*$/.test(sValue);
}




///////////////////////////////////////////////////////////////////////////////////
// DOM&XML \u51fd\u6570
///////////////////////////////////////////////////////////////////////////////////

// IE55 has a serious DOM1 bug... Patch it!
if (/msie 5\.[56789]/i.test(navigator.userAgent)) {
    document._getElementsByTagName = document.getElementsByTagName;
    document.getElementsByTagName = function (sTagName) {
        if (sTagName == "*")
            return document.all;
        else
            return document._getElementsByTagName(sTagName);
    };
}

//+------------------------------------
// \u521b\u5efa XMLDocument \u5bf9\u8c61
//-------------------------------------
function createDOMDocument()
{
    try {
        var objXML = new ActiveXObject("Msxml2.DOMDocument");
        objXML.async = false;
        return objXML
    }
    catch (e) {
        return null;
    }
}

//+------------------------------------
// \u8bbe\u7f6e\u8282\u70b9\u7684\u5c5e\u6027
// objXML \uff1a        DOM         DOM \u5bf9\u8c61
// nodePath \uff1a      String      \u8282\u70b9 xPath \u8def\u5f84(\u7531\u6839\u8282\u70b9\u8d77\u81f3\u8be5\u8282\u70b9)
// attribName \uff1a    String      \u5c5e\u6027\u540d
// attribValue \uff1a   String      \u5c5e\u6027\u503c
//-------------------------------------
function setAttribValue(objXML, nodePath, attribName, attribValue)
{
    var node = objXML.selectSingleNode("//" + nodePath);
    if (node) {
        node.setAttribute(attribName, attribValue);
        return true;
    }
    return false;
}

//+------------------------------------
// \u83b7\u5f97\u8282\u70b9\u7684\u5c5e\u6027
// objXML \uff1a        DOM         DOM \u5bf9\u8c61
// nodePath \uff1a      String      \u8282\u70b9 xPath \u8def\u5f84(\u7531\u6839\u8282\u70b9\u8d77\u81f3\u8be5\u8282\u70b9)
// attribName \uff1a    String      \u5c5e\u6027\u540d
// index \uff1a         Integer     \u5f53\u6709\u591a\u4e2a\u8282\u70b9\u8def\u5f84\u76f8\u540c\u65f6\uff0c\u7528\u4e8e\u7d22\u5f15
//-------------------------------------
function getAttribValue(objXML, nodePath, attribName, index)
{
    try {
        var objNodeList = objXML.selectNodes("//" + nodePath);
        if (objNodeList.length > 0) {
            if (index) {
                return objNodeList.item(index).getAttribute(attribName);
            }
            else {
                return objNodeList.item(0).getAttribute(attribName);
            }
        }
    }
    catch (e) {}
    return null;
}

//+------------------------------------
// \u521b\u5efa\u6839\u8282\u70b9
// nodeName \uff1a String       \u6839\u8282\u70b9\u540d
//-------------------------------------
function makeRootNode(nodeName)
{
    try {
        var objXML = createDOMDocument();
        objXML.loadXML("<" + nodeName + "/>");
        if (objXML.parseError.errorCode != 0) {
            throw 0;
        }
        return objXML;
    }
    catch (e) {
        return null;
    }
}

//+------------------------------------
// \u8bbe\u7f6e\u8282\u70b9\u503c
// objXML \uff1a    DOM         DOM \u5bf9\u8c61
// nodePath \uff1a  String      \u8282\u70b9 xPath \u8def\u5f84(\u7531\u6839\u8282\u70b9\u8d77\u81f3\u8be5\u8282\u70b9)
// nodeValue \uff1a String      \u8282\u70b9\u503c
//-------------------------------------
function setNodeValue(objXML, nodePath, nodeValue)
{
    try {
        var node, path, value;
        var nodes = [];
        var nodeNameList = nodePath.split("/");
        var len = nodeNameList.length - 1;

        if (!objXML.documentElement) {
            throw 0;
        }

        for (var i = 0; i < len; i++) {
            path = nodeNameList[0];
            for (var j=1; j<=i+1; j++)
                path += "/" + nodeNameList[j];
            value = (i != len-1) ? "" : nodeValue;
            node = objXML.selectSingleNode("//" + path);
            if (!node) {
                var pos = path.lastIndexOf("/");
                node = objXML.createElement(path.substr(pos+1));
            }
            if (value != "") node.text = value;
            nodes[i] = node;
        }

        node = nodes[0];
        objXML.documentElement.appendChild(node);
        for (i=1; i<nodes.length; i++) {
            node = node.appendChild(nodes[i]);
        }
        return true;
    }
    catch (e) {
        return false;
    }
}

//+------------------------------------
// \u83b7\u5f97\u8282\u70b9\u503c
// objXML \uff1a    DOM         DOM \u5bf9\u8c61
// nodePath \uff1a  String      \u8282\u70b9 xPath \u8def\u5f84(\u7531\u6839\u8282\u70b9\u8d77\u81f3\u8be5\u8282\u70b9)
// index \uff1a     Integer     \u5f53\u6709\u591a\u4e2a\u8282\u70b9\u8def\u5f84\u76f8\u540c\u65f6\uff0c\u7528\u4e8e\u7d22\u5f15
//-------------------------------------
function getNodeValue(objXML, nodePath, index)
{
    try {
        var objNodeList = objXML.selectNodes("//" + nodePath);
        if (objNodeList.length > 0) {
            if (index) {
                return objNodeList.item(index).text;
            }
            else {
                return objNodeList.item(0).text;
            }
        }
    }
    catch (e) {}
    return "";
}

//+------------------------------------
// \u8bbe\u7f6e\u8282\u70b9\u5bf9\u8c61
// objXML \uff1a    DOM          DOM \u5bf9\u8c61
// nodePath \uff1a  String       \u8282\u70b9 xPath \u8def\u5f84(\u7531\u6839\u8282\u70b9\u8d77\u81f3\u8be5\u8282\u70b9)
// sXML \uff1a      String       XML \u5b57\u7b26\u4e32
//-------------------------------------
function setNodeDOM(objXML, nodePath, sXML)
{
    try {
        if (!objXML.documentElement) {
            throw 0;
        }

        var node = objXML.selectSingleNode("//" + nodePath);
        if (!node) {
            setNodeValue(objXML, nodePath, "");
            node = objXML.selectSingleNode("//" + nodePath);
        }

        var objTXML = createDOMDocument();
        objTXML.loadXML(sXML);
        if (objTXML.parseError.errorCode != 0) {
            throw 0;
        }

        while (objTXML.documentElement.hasChildNodes()) {
            node.appendChild(objTXML.documentElement.firstChild);
        }

        return true;
    }
    catch (e) {
        return false;
    }
}

//+------------------------------------
// \u83b7\u5f97\u8282\u70b9\u5bf9\u8c61
// objXML \uff1a    DOM         DOM \u5bf9\u8c61
// nodePath \uff1a  String      \u8282\u70b9 xPath \u8def\u5f84(\u7531\u6839\u8282\u70b9\u8d77\u81f3\u8be5\u8282\u70b9)
// index \uff1a     Integer     \u5f53\u6709\u591a\u4e2a\u8282\u70b9\u8def\u5f84\u76f8\u540c\u65f6\uff0c\u7528\u4e8e\u7d22\u5f15
//-------------------------------------
function getNodeDOM(objXML, nodePath, index)
{
    try {
        var objNodeList = objXML.selectNodes("//" + nodePath);
        if (objNodeList.length > 0) {
            if (index) {
                return objNodeList.item(index);
            }
            else {
                return objNodeList.item(0);
            }
        }
    }
    catch (e) {}
    return null;
}

//+------------------------------------
// \u751f\u6210 DataInfo XML \u6570\u636e
// arr_fieldName \uff1a Array       \u8282\u70b9\u540d\u6570\u7ec4
// arr_nodeValue \uff1a Array       \u8282\u70b9\u503c\u6570\u7ec4
// arr_showValue \uff1a Array       \u8282\u70b9sv\u5c5e\u6027\u503c\u6570\u7ec4
// arr_type \uff1a      Array       \u8282\u70b9\u503c\u7684\u7c7b\u578b
//  (true: XML\u5b57\u7b26\u4e32; false: \u6587\u672c\u503c)
//-------------------------------------
function buildDataInfo(arr_fieldName, arr_nodeValue, arr_showValue, arr_valueType)
{
    var nodeRoot = "DATAINFO";
    var objXML = makeRootNode(nodeRoot);
    if (!objXML) return;
    var len = arr_fieldName.length;
    for (var i = 0; i < len; i++) {
        var path = nodeRoot + "/" + arr_fieldName[i];
        if (arr_valueType[i]) {
            setNodeDOM(objXML, path, arr_nodeValue[i]);
        }
        else {
            setNodeValue(objXML, path, arr_nodeValue[i]);
            if (arr_showValue[i] != "") {
                setAttribValue(objXML, path, "sv", arr_showValue[i]);
            }
        }
    }
    return objXML;
}

//+------------------------------------
// \u751f\u6210\u4e00\u4e2a\u67e5\u8be2\u5b50\u6761\u4ef6
// sFiledName : String      \u5b57\u6bb5\u540d
// sOperation : String      \u64cd\u4f5c\u7c7b\u578b
// sValue :     String      \u67e5\u8be2\u503c
// sSV :        String      \u5f53\u67e5\u8be2\u503c\u4e3a\u7f16\u7801\u65f6\u7684\u6587\u672c\u503c
//-------------------------------------
function makeCondition(sFiledName, sOperation, sValue, sSV)
{
    try {
        var sXML = "<CONDITION alias='' datatype=''>" +
            "<FIELDNAME sv='" + sSV + "'>" + sFiledName + "</FIELDNAME>" +
            "<OPERATION>" + sOperation + "</OPERATION>" +
            "<VALUE>" + re(sValue) + "</VALUE>" +
            "</CONDITION>";
        var xmlCondition = createDOMDocument();
        xmlCondition.loadXML(sXML);
        if (xmlCondition.parseError.errorCode != 0) {
            throw 0;
        }
        return xmlCondition;
    }
    catch (e) {
        return null;
    }
}

//+------------------------------------
// \u589e\u52a0\u4e00\u4e2a\u67e5\u8be2\u6761\u4ef6\u7ec4
// sType :          String      \u903b\u8f91\u5173\u7cfb\u7c7b\u578b
// xmlConditionA :  XML       \u67e5\u8be2\u6761\u4ef6A
// xmlConditionB :  XML       \u67e5\u8be2\u6761\u4ef6B
//-------------------------------------
function addConditionItem(sType, xmlConditionA, xmlConditionB)
{
    try  {
        var sXML = "<CONDITIONS>" +
            "<TYPE>" + sType + "</TYPE>" +
            "</CONDITIONS>";
        var xmlConditions = createDOMDocument();
        xmlConditions.loadXML(sXML);
        if (xmlConditions.parseError.errorCode != 0) {
            throw 0;
        }
        if (xmlConditionA) {
             xmlConditions.documentElement.appendChild(xmlConditionA.documentElement.cloneNode(true));
        }
        if (xmlConditionB) {
             xmlConditions.documentElement.appendChild(xmlConditionB.documentElement.cloneNode(true));
        }
        return xmlConditions;
    }
    catch (e) {
        return null;
    }
}

//+------------------------------------
// \u7531\u4e00\u7ec4\u67e5\u8be2\u5b50\u6761\u4ef6,\u6784\u9020\u6761\u4ef6\u7ec4
// sType :          String    \u903b\u8f91\u5173\u7cfb\u7c7b\u578b
// arr_xmlCondition : XML     \u67e5\u8be2\u5b50\u6761\u4ef6\u6570\u7ec4(\u5b83\u4eec\u4e4b\u95f4\u7684\u903b\u8f91\u5173\u7cfb\u662f\u4e0e)
//-------------------------------------
function makeConditions(sType, arr_xmlCondition)
{
    try {
        var xmlConditions = null;
        if (arr_xmlCondition.length >= 2) {
            xmlConditions = addConditionItem(sType, arr_xmlCondition[0], arr_xmlCondition[1]);
            for (var i=2; i<arr_xmlCondition.length; i++) {
                xmlConditions = addConditionItem(sType, xmlConditions, arr_xmlCondition[i]);
            }
        }
        else {
            xmlConditions = addConditionItem(sType, arr_xmlCondition[0]);
        }
        return xmlConditions;
    }
    catch (e) {
        return null;
    }
}

//+------------------------------------
// \u521b\u5efa\u67e5\u8be2\u6761\u4ef6
// sPredicate :     String      \u8c13\u8bcd
// xmlConditions :  XML         \u67e5\u8be2\u6761\u4ef6
//-------------------------------------
function makeQueryCondition(sPredicate, xmlConditions)
{
    try {
        var sXML = "<?xml version='1.0'?>" +
            "<ZAGLDOC tchsoft=\"urn=schemas-tchsoft-com:zagl\" version=\"2.0\">" +
            "<QUERYCONDITION>" +
            "<PREDICATE>" + sPredicate + "</PREDICATE>" +
            "</QUERYCONDITION>" +
            "</ZAGLDOC>";
        var xmlDoc = createDOMDocument();
        xmlDoc.loadXML(sXML);
        if (xmlDoc.parseError.errorCode != 0) {
            throw 0;
        }
        xmlDoc.documentElement.firstChild.appendChild(xmlConditions.documentElement);
        return xmlDoc;
    }
    catch (e) {
        return null;
    }
}

//+------------------------------------
// \u751f\u6210\u67e5\u8be2\u6761\u4ef6 XML
// arr_FieldName \uff1a Array       \u5b57\u6bb5\u540d\u6570\u7ec4
// arr_Logic \uff1a     Array       \u903b\u8f91\u5173\u7cfb\u6570\u7ec4
// arr_Operation \uff1a Array       \u64cd\u4f5c\u5173\u7cfb\u6570\u7ec4
// arr_Value \uff1a     Array       \u6761\u4ef6\u503c\u6570\u7ec4
// arr_SV \uff1a        Array       \u6761\u4ef6\u503c\u7684\u6587\u672c\u6570\u7ec4
//-------------------------------------
function buildConditions(arr_FieldName, arr_Logic, arr_Operation, arr_Value, arr_SV)
{
    var arr_Condition = [];
    for (var i = 0; i < arr_Value.length; i++) {
        var n = arr_Condition.length;
        arr_Condition[n] = makeCondition(arr_FieldName[i], arr_Operation[i], arr_Value[i], arr_SV[i]);
    }

    var xmlConditions = null;
    if (arr_Condition.length >= 2) {
        xmlConditions = addConditionItem(arr_Logic[0], arr_Condition[0], arr_Condition[1]);

        for (var i = 2; i < arr_Condition.length; i++) {
            xmlConditions = addConditionItem(arr_Logic[i-1],
                xmlConditions, arr_Condition[i]);
        }
    }
    else {
        xmlConditions = addConditionItem(arr_Logic[0], arr_Condition[0]);
    }
    var xmlQuery = makeQueryCondition("", xmlConditions);
    return xmlQuery.xml;
}

///////////////////////////////////////////////////////////////////////////////////
// WEB\u5e94\u7528\u6269\u5c55\u51fd\u6570
///////////////////////////////////////////////////////////////////////////////////

//+------------------------------------
// \u6839\u636e\u6307\u5b9a\u7684\u64cd\u4f5c\u65b9\u6cd5\u5f39\u51fa\u76f8\u5e94\u786e\u8ba4\u63d0\u793a\u6846
// method \uff1a	String	\u64cd\u4f5c\u65b9\u6cd5\u540d\u79f0
//-------------------------------------

var backhref=true;
function opconfirm(method) {
	backhref=true;
	if(method == 'save') {
		if(confirm('\u662f\u5426\u4fdd\u5b58?')) {
			return true;
		}
	} else if(method == 'delete') {
		if(confirm('\u662f\u5426\u5220\u9664?')) {
			return true;
		}
	} else if(method == 'reuse') {
		if(confirm('\u662f\u5426\u6062\u590d?')) {
			return true;
		}
	} else if(method == 'open') {
		if(confirm('\u662f\u5426\u542f\u7528?')) {
			return true;
		}
	} else if(method == 'close') {
		if(confirm('\u662f\u5426\u7981\u7528?')) {
			return true;
		}
	} else if(method == 'deleteWordbook') {
		if(confirm('\u662f\u5426\u5220\u9664\u8be5\u5b57\u5178\u53ca\u5176\u6240\u6709\u4e0b\u7ea7\u5b57\u5178?')) {
			return true;
		}
	} else if(method == 'deleteWordbookCategory') {
		if(confirm('\u8be5\u64cd\u4f5c\u5c06\u5220\u9664\u5b57\u5178\u7c7b\u578b\u3001\u6240\u5c5e\u5b57\u5178\u548c\u5176\u4e0b\u7ea7\u5b57\u5178\n\u662f\u5426\u786e\u8ba4\uff1f')) {
			return true;
		}
	} else if(method == 'deletePos') {
		if(confirm('\u662f\u5426\u8981\u5c06\u8be5\u7ec8\u7aef\u7f6e\u4e3a\u4e0d\u53ef\u7528\uff1f')) {
			return true;
		}
	}else if(method=='logout'){
		if(confirm('\u786e\u8ba4\u6ce8\u9500\u8be5\u5ba2\u6237')){
			return true;
		}
	}else if(method=='surelogout'){
		if(confirm('\u786e\u8ba4\u6ce8\u9500\u8be5\u5ba2\u6237\u5417\uff1f')){
			return true;
		}
	}
	backhref=false;
	return false;
}

//+------------------------------------
// \u8df3\u51fa\u6846\u67b6\u9875\u9762
//-------------------------------------
function jumpframe() {
	if (top.location !== self.location) {
		top.location=self.location;
	}
}

//+------------------------------------
// \u8868\u683c\u9009\u4e2d\u884c\u9ad8\u4eae\uff0c\u53ca\u5355\u51fb\u4e8b\u4ef6\u5904\u7406
// tableId \uff1aString	\u8868\u683cID
//-------------------------------------


///////////////////////////////////////////////////////////////////////////////////
// \u7ea7\u8054\u4e0b\u62c9\u5217\u8868\u51fd\u6570
///////////////////////////////////////////////////////////////////////////////////

var request;

function createRequest() {
  try {
    request = new XMLHttpRequest();
  } catch (trymicrosoft) {
    try {
      request = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (othermicrosoft) {
      try {
        request = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (failed) {
        request = false;
      }
    }
  }

  if (!request)
    alert("Error initializing XMLHttpRequest!");
}

var buf = [];

var subvalue = "";
function clear_select(so) {
    for (var i = so.options.length - 1; i > -1; i--) {
		if (so.options.remove) {
		    so.options.remove(i);
		} else {
		    so.removeChild(so.options[i]);
		}
    }
}

//+------------------------------------
// \u6839\u636e\u7236\u7ea7\u4e0b\u62c9\u5217\u8868\uff0c\u521d\u59cb\u5316\u5b50\u7ea7\u4e0b\u62c9\u5217\u8868\u9879
// oid \uff1aString	\u7236\u7ea7\u4e0b\u62c9\u5217\u8868ID
// soid \uff1aString \u5b50\u7ea7\u4e0b\u62c9\u5217\u8868ID
// url : String \u5b50\u7ea7\u5217\u8868\u8d44\u6e90\u94fe\u63a5\u5730\u5740
//-------------------------------------
function change_select(oid, soid, url) {
	var o = document.getElementById(oid);
	var so = document.getElementById(soid);
	var pid = o.value;
	clear_select(so);
	if(isEmpty(pid)) {
		set_select(so, [], 0, 1);
		return;
	}

	if(isNaN(pid = parseInt(pid))) pid = 0;
	if (buf[pid]) {
    	set_select(so, buf[pid], 0, 1);
    } else {
    	if(url) {
	   		createRequest();
    		call_server(so, pid, url);
    	} else {
    		set_select(so, [], 0, 1);
    	}
    }
}

//+------------------------------------
// \u88c5\u8f7d\u4e0b\u62c9\u5217\u8868\u9879
// so \uff1aObject \u4e0b\u62c9\u5217\u8868\u5bf9\u8c61
// d \uff1aArray \u4e0b\u62c9\u5217\u8868\u9879\u6570\u7ec4
// vf : Integer value\u503c\u7d22\u5f15
// tf : Integer label\u503c\u7d22\u5f15
//-------------------------------------
function set_select(so, d, vf, tf) {
	var opt0 = document.createElement('option');
	var substr;
    opt0.value = "";
    opt0.text = "\u8bf7\u9009\u62e9";
    if (so.options.add) {
        so.options.add(opt0);
    } else {
        so.appendChild(opt0);
    }

    for (var i = 0, n = d.length; i < n; i++) {
        var opt = document.createElement('option');
        opt.value = d[i][vf];
        opt.text = d[i][tf];
        subvalue = subvalue.substr(3);
        if(opt.value == subvalue) {
        	opt.selected = true;
        	subvalue = "";
        }
        if (so.options.add) {
            so.options.add(opt);
        } else {
            so.appendChild(opt);
        }
    }
}

function call_server(so, pid, url) {
	alert(1);
	url += "id=" + pid;
  	request.open("GET", url, true);
  	request.onreadystatechange = function () {
 		if (request.readyState == 4) {
	   		if (request.status == 200) {
	   			var xmlDoc = new ActiveXObject("MSXML2.DOMDocument");
	   			xmlDoc.async = false;
	     		xmlDoc.loadXML(request.responseText);
	     		var xml = xmlDoc.documentElement;
	     		var options = [];
	     		for(var i = 0; i < xml.childNodes.length; i++){
	     			var option = [];
					option[0] = xml.childNodes(i).attributes(1).text;
					option[1] = xml.childNodes(i).attributes(0).text;
					options[i] = option;
				}
				buf[pid] = options;
		 		set_select(so, buf[pid], 0, 1);
	   		} else
	     		alert("status is " + request.status);
	     }
  	};
  	request.send(null);
}


//-------------------
//author:tanph
//-------------------
	var customersOption = new Array();
	var merchantsOption = new Array();
	function initCustomersOption()
	{
		for(i=0;i<=2;i++){
	 	 customersOption[i] = new Array();
		}
	}

	//---------------------
	//so:\u9700\u8981\u88ab\u8bbe\u7f6e\u7684select
	//index:\u7236\u7ea7select\u88ab\u9009\u4e2d\u7684index,\u5373\u5f71\u54cd\u5230so\u7684select.
	//---------------------
    function setCustomerSelect(so,index){
    	var len = document.getElementById(so).options.length;
    	var tarSel= document.getElementById(so);
    	for(m=len-1;m>0;m--){
    		tarSel.options[m]=null;
    	}
    	if(index != 0){
    	  for (i=1;i<=customersOption[index].length;i++){
			  tarSel.options[i]= customersOption[index][i-1];
		  }
		}
		document.getElementById(so).options.selectedIndex=0;
    }

//------------------
//pid:\u5c06\u8981\u4f20\u9012\u7ed9\u670d\u52a1\u5668\u7684id\u53c2\u6570.
//url:\u8bf7\u6c42\u670d\u52a1\u5668\u7684url.
//sof:\u7236\u7ea7select\u5bf9\u8c61.
//sos:\u5b50\u7ea7select\u5bf9\u8c61.
//------------------
function callServer(url, pname, so) {
	createRequest();
	document.getElementById('tipLayer').style.visibility = "visible";
	//initCustomersOption();
	merchantsOption = new Array();
	url += "&name=" + pname;

  	request.open("GET", url, true);
  	request.onreadystatechange = function () {
 		if (request.readyState == 4) {
	   		if (request.status == 200 && request.responseXML) {
	     		var xml = request.responseXML;
	     		var merchants = xml.getElementsByTagName("merchant");
	     		for(var i=0;i<merchants.length;i++)
	     		{
	     			var merchant_in = merchants[i];
	     			var merchant_id = merchant_in.getAttribute("id");
	     			var name_short = merchant_in.getAttribute("name");
	     			//alert(merchant_id+'=='+name_short);
	     			merchantsOption[i]=new Option(name_short,merchant_id);
	     		}
	     		setSigleSelect(so,merchantsOption);
	   		} else{
	     		alert(request.statusText);
	     	}
	     	document.getElementById('tipLayer').style.visibility = "hidden";
	     }
  	};
  	request.send(null);
}

function setSigleSelect(so,options)
{
	var selObj = document.getElementById(so);
	for(m=selObj.options.length-1;m>0;m--){
    	selObj.options[m]=null;
    }
  	//alert('======');
	for (i=1;i<=options.length;i++){
		 selObj.options[i]= options[i-1];
	}
}
//------------------
//pid:\u5c06\u8981\u4f20\u9012\u7ed9\u670d\u52a1\u5668\u7684id\u53c2\u6570.
//url:\u8bf7\u6c42\u670d\u52a1\u5668\u7684url.
//sof:\u7236\u7ea7select\u5bf9\u8c61.
//sos:\u5b50\u7ea7select\u5bf9\u8c61.
//------------------
function callServer(url, pid, pname, sof, sos) {
	createRequest();
	document.getElementById('tipLayer').style.visibility = "visible";
	initCustomersOption();
	url += "&id=" + pid + "&name=" + pname;
  	request.open("GET", url, true);
  	request.onreadystatechange = function () {
 		if (request.readyState == 4) {
	   		if (request.status == 200 && request.responseXML) {
	     		var xml = request.responseXML;
	     		var customer_type = xml.getElementsByTagName("type")[0].getAttribute("id");
	     		var customers = xml.getElementsByTagName("customer");
	     		for(var i=0;i<customers.length;i++)
	     		{
	     			var customer_in = customers[i];
	     			var customer_id = customer_in.getAttribute("id");
	     			var customer_name = customer_in.getAttribute("name");
	     			customersOption[customer_type][i]=new Option(customer_name,customer_id);
	     		}
	     		setCustomerSelect(sos,document.getElementById(sof).options.selectedIndex);
	   		} else{
	     		alert(request.statusText);
	     	}
	     	document.getElementById('tipLayer').style.visibility = "hidden";
	     }
  	};
  	request.send(null);
}

//------------author sujt----------------------
function callServerforCustomer(url,pname,sos) {
	createRequest();
	document.getElementById('tipLayer').style.visibility = "visible";
	initCustomersOption();
	url += "&name=" + pname;
  	request.open("GET",url, true);
  	request.onreadystatechange = function () {
 		if (request.readyState == 4) {
	   		if (request.status == 200 && request.responseXML) {
	     		var xml = request.responseXML;
	     		var customers = xml.getElementsByTagName("customer");
	     		for(var i=0;i<customers.length;i++)
	     		{
	     			var customer_in = customers[i];
	     			var customer_id = customer_in.getAttribute("id");
	     			var customer_name = customer_in.getAttribute("name");
	     			customersOption[1][i]=new Option(customer_name,customer_id);
	     		}
	     		setCustomerSelect(sos,1);
	   		} else{
	     		alert(request.statusText);
	     	}
	     	document.getElementById('tipLayer').style.visibility = "hidden";
	     }
  	};
  	request.send(null);
}
//----------------add  @2009-8-13-----------------------
//pname is search condition ,and sos is the select list name
function callServerforMcc(url,pname,sos) {
	createRequest();
	document.getElementById('tipLayer').style.visibility = "visible";
	initCustomersOption();
	url += "&name=" + pname;
  	request.open("GET",url, true);
  	request.onreadystatechange = function () {
 		if (request.readyState == 4) {
	   		if (request.status == 200 && request.responseXML) {
	     		var xml = request.responseXML;
	     		var mccs = xml.getElementsByTagName("mcc");
	     		for(var i=0;i<mccs.length;i++)
	     		{
	     			var mcc_in = mccs[i];
	     			var mcc = mcc_in.getAttribute("id");
	     			var mcc_name = mcc_in.getAttribute("name");
	     			customersOption[1][i]=new Option(mcc_name,mcc);
	     		}
	     		setCustomerSelect(sos,1);
	   		} else{
	     		alert(request.statusText);
	     	}
	     	document.getElementById('tipLayer').style.visibility = "hidden";
	     }
  	};
  	request.send(null);
}

//---------------------

//-----------------
function callServerforOrg(url,pname,sos) {
	createRequest();
	document.getElementById('tipLayer').style.visibility = "visible";
	initCustomersOption();
	//url += "&id=" + pid + "&name=" + pname;
	url += "&name=" + pname;
  	request.open("GET",url, true);
  	request.onreadystatechange = function () {
 		if (request.readyState == 4) {
	   		if (request.status == 200 && request.responseXML) {
	     		var xml = request.responseXML;
	     		var orgs = xml.getElementsByTagName("organization");
	     		for(var i=0;i<orgs.length;i++)
	     		{
	     			var org_in = orgs[i];
	     			var old_org_id = org_in.getAttribute("id");
	     			var old_org_name = org_in.getAttribute("name");
	     			customersOption[1][i]=new Option(old_org_name,old_org_id);
	     		}
	     		setCustomerSelect(sos,1);
	   		} else{
	     		alert(request.statusText);
	     	}
	     	document.getElementById('tipLayer').style.visibility = "hidden";
	     }
  	};
  	request.send(null);
}

//-------------

function callServerforTerminal(url,pname,sos) {
	createRequest();
	document.getElementById('tipLayer').style.visibility = "visible";
	initCustomersOption();
	url += "&name=" + pname;
  	request.open("GET", url, true);
  	request.onreadystatechange = function () {
 		if (request.readyState == 4) {
	   		if (request.status == 200 && request.responseXML) {
	     		var xml = request.responseXML;
	     		var terminals = xml.getElementsByTagName("terminal");
	     		for(var i=0;i<terminals.length;i++)
	     		{
	     			var terminal_in = terminals[i];
	     			var terminal_id = terminal_in.getAttribute("id");
	     			var terminal_name = terminal_in.getAttribute("name");
	     			customersOption[1][i]=new Option(terminal_name,terminal_id);
	     		}
	     		setCustomerSelect(sos,1);
	   		} else{
	     		alert(request.statusText);
	     	}
	     	document.getElementById('tipLayer').style.visibility = "hidden";
	     }
  	};
  	request.send(null);
}
//terminalopen,change USED author:sujt

function compare2Date(date1,date2)	
{	
	var d = parseFloat(date2-date1);
	if(d<=0)
	{
		return true;
	}else
	{
		return false;
	}
}
function compare2Date2(date1,date2)	
{	
	var d = parseFloat(date2-date1);
	if(d>=0)
	{
		return false;
	}else
	{
		return true;
	}
}

Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
function highlightTableRows(tableId) {
	var table1="";
	if(tableId.substr(tableId.length-1)=="#"){
		tableId=tableId.substr(0,tableId.length-1);
		table1="1";
	}
    var previousClass = null;
    var table = document.getElementById(tableId);
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows;
    if (tbody == null) {
        rows = table.getElementsByTagName("tr");
    } else {
        rows = tbody.getElementsByTagName("tr");
    }
    // add event handlers so rows light up and are clickable

    for (i=0; i < rows.length; i++) {
        rows[i].onmouseover = function() { previousClass=this.className;this.style.backgroundColor='#BFDCFA';this.style.color='#ffffff';this.className='over' };
        rows[i].onmouseout = function() { this.className=previousClass;this.style.backgroundColor='';this.style.color=''};
    }
}
