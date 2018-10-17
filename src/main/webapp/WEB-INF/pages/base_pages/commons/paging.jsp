<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>

<link href="<c:url value="/js/paging/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">

<script type="text/javascript"	src="<c:url value="/js/paging/simpletable/simpletable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/application.js"/>"></script>
<script type="text/javascript">
	window.onload=function() {
		window.simpleTable = new SimpleTable('queryForm','${page.thisPageNumber}','${page.pageSize}','${pageRequest.sortColumns}');
	};
</script>
