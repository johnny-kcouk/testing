<%@ include file="/WEB-INF/pages/include.jsp" %>

<c:if test="${not empty message}">
	<p class="bs-callout bs-callout-success" role="alert"> 
		<i class="fa fa-check"></i> 
			<c:out value="${message}"/>
	</p>
</c:if>