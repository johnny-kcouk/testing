<%@ include file="/WEB-INF/pages/include.jsp" %>

<c:if test="${not empty error}">
	<p class="bs-callout bs-callout-danger" role="alert"> 
		<i class="fa fa-close"></i> 
			<c:out value="${error}"/>
	</p>
</c:if>