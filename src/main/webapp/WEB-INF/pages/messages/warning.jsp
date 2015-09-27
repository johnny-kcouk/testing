<%@ include file="/WEB-INF/pages/include.jsp" %>

<c:if test="${not empty warning}">
	<p class="bs-callout bs-callout-warning" role="alert"> 
		<i class="fa fa-warning"></i> 
			<c:out value="${warning}"/>
	</p>
</c:if>