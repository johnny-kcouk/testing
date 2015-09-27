<%@ include file="/WEB-INF/pages/include.jsp" %>

<c:if test="${not empty info}">
	<p class="bs-callout bs-callout-info" role="alert"> 
		<i class="fa fa-exclamation"></i> 
			<c:out value="${info}"/>
	</p>
</c:if>