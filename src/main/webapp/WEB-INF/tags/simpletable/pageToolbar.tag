<%@ tag pageEncoding="UTF-8"%>
<%@ attribute name="page" required="true" type="com.yada.mybatis.paging.Page" description="Page.java" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	// set default values
%>

<table width="95%" align="center" border="0" cellspacing="0" class="gridToolbar">
  <tr>
	<td>
	
		<div class="box">
		
			<div  class="leftControls" >
				<jsp:doBody/>
			</div>
			
			<div class="paginationControls">
				<span class="buttonLabel">第${page.thisPageFirstElementNumber} - ${page.thisPageLastElementNumber}(条)记录  共${page.totalCount}(条)</span>
				
				<c:choose>
				<c:when test="${page.firstPage}"><img src="<c:url value='/js/paging/simpletable/images/firstPageDisabled.gif'/>" style="border:0" ></c:when>
				<c:otherwise><a href="javascript:window.simpleTable.togglePage(1);"><img src="<c:url value='/js/paging/simpletable/images/firstPage.gif'/>" style="border:0" ></a></c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${page.hasPreviousPage}"><a href="javascript:window.simpleTable.togglePage(${page.previousPageNumber});"><img src="<c:url value='/js/paging/simpletable/images/prevPage.gif'/>" style="border:0" ></a></c:when>
				<c:otherwise><img src="<c:url value='/js/paging/simpletable/images/prevPageDisabled.gif'/>" style="border:0" ></c:otherwise>
				</c:choose>
				
				<c:forEach var="item" items="${page.linkPageNumbers}">
				<c:choose>
				<c:when test="${item == page.thisPageNumber}"> [${item}]</c:when>
				<c:otherwise><a href="javascript:window.simpleTable.togglePage(${item});">[${item}]</a></c:otherwise>
				</c:choose>
				</c:forEach>
				
				<c:choose>
				<c:when test="${page.hasNextPage}"><a href="javascript:window.simpleTable.togglePage(${page.nextPageNumber});"><img src="<c:url value='/js/paging/simpletable/images/nextPage.gif'/>" style="border:0" ></a></c:when>
				<c:otherwise><img src="<c:url value='/js/paging/simpletable/images/nextPageDisabled.gif'/>" style="border:0" ></c:otherwise>
				</c:choose>
				
				<c:choose>
				<c:when test="${page.lastPage}"><img src="<c:url value='/js/paging/simpletable/images/lastPageDisabled.gif'/>" style="border:0"></c:when>
				<c:otherwise><a href="javascript:window.simpleTable.togglePage(${page.lastPageNumber});"><img src="<c:url value='/js/paging/simpletable/images/lastPage.gif'/>" style="border:0" ></a></c:otherwise>
				</c:choose>
				
			
				<select name="s_pageSize" onChange="window.simpleTable.togglePageSize(this.value)">
					
						<option value="20" ${page.pageSize == 20? 'selected' : '' }  >20</option>
					
						<option value="100" ${page.pageSize == 100 ? 'selected' : '' } >100</option>
					
						<option value="200" ${page.pageSize == '200' ? 'selected' : '' } >200</option>
				</select>
				<input type="hidden" name="s_show" value="true"></input>
				
			</div>
		</div>
	</td>
  </tr>
</table>