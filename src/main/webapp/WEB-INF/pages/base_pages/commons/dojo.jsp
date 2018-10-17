<%-- <script type="text/javascript" src="<c:url value="js/common.js"/>"></script> --%>
<%-- <script type="text/javascript" src="<c:url value="js/SDDialog.js"/>"></script> --%>
<script type="text/javascript" src="<c:url value='/js/json2.js'/>"></script>
<script src="<c:url value='/js/My97DatePicker/WdatePicker.js'/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value='/js/dojoBuild1.8/dojo/dojo.js'/>" data-dojo-config="parseOnLoad: true"></script>
<%-- <script type="text/javascript" src="<c:url value='/js/dojoBuild1.8/dojo/preloadJS.js'/>" ></script> --%>
<script type="text/javascript">

	dojo.require("dojo.ready");  
	dojo.require("dijit.dijit");  
	dojo.require("dojo.on");
	dojo.require("dojo.io.iframe");
	dojo.require("dojo.string");
	
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.ValidationTextBox"); 
    dojo.require("dijit.form.Button"); 
    dojo.require("dijit.form.Textarea");
    dojo.declare("net.yutuo.dojo.Textarea",
            [ dijit.form.ValidationTextBox, dijit.form.Textarea ],
    {
        regExp : "(.|\s)*"
    });
    
    dojo.require("yadaWight.duobleSelect"); 
    dojo.require("yadaWight.YDLazyTree"); 
    dojo.require("yadaWight.YDLazyTreeMenu");
    dojo.require("yadaWight.YDMultiSelect");
    dojo.require("yadaWight.selectTree");
	
	
	
	dojo.require("dijit.layout.ContentPane");
	dojo.require("dijit.layout.BorderContainer");
	dojo.require("dijit.layout.TabContainer");
	dojo.require("dojox.layout.ContentPane");
	dojo.require("dijit.Editor");
    dojo.require("dijit._editor.plugins.FontChoice");
</script>

 <style type="text/css"> 
/*  	@import "<c:url value='/js/dojoBuild1.8/dojo/resources/dojo.css'/>";  */
/*  	@import "<c:url value='/js/dojoBuild1.8/dijit/themes/claro/claro.css'/>";  */
/*  	@import "<c:url value='/js/ddojoBuild1.8/dijit/themes/tundra/tundra.css'/>";  */
 </style> 

<link rel="stylesheet" type="text/css" href="<c:url value='/js/dojoBuild1.8/dijit/themes/claro/claro.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/css/site.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/screen.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/css/table.css"/>">
