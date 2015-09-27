<%@tag description="Extended input tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="id" required="false" type="java.lang.String"%>
<%@attribute name="cssClass" required="false" type="java.lang.String"%>
<%@attribute name="label" required="false" type="java.lang.String"%>
<%@attribute name="maxlength" required="false" type="java.lang.String"%>
<%@attribute name="required" required="false" type="java.lang.Boolean"%>
<%@attribute name="tabindex" required="false" type="java.lang.String"%>
<%@attribute name="readonly" required="false" type="java.lang.Boolean"%>
<%@attribute name="date" required="false" type="java.lang.Boolean"%>
<%@attribute name="gridClass" required="false" type="java.lang.String"%>
<%@attribute name="type" required="false" type="java.lang.String"%>

<c:if test="${empty label}">
    <c:set var="label" value="${fn:toUpperCase(fn:substring(path, 0, 1))}${fn:toLowerCase(fn:substring(path, 1,fn:length(path)))}" />
</c:if>
<spring:bind path="${path}">
	<c:if test="${not empty gridClass}">
	    <div class="${gridClass}">
	</c:if>
    <div class="form-group ${status.error ? 'has-error' : '' }">
        <label class="control-label" for="${path}">${label}<c:if test="${required}">&nbsp;<span class="required">*</span></c:if></label>
        <div class="controls">
        <c:if test="${not empty date and date==true }">
        <div class="input-group date" id="${empty id?path:id }" data-date="${path}" data-date-format="dd-mm-yyyy">
        </c:if>
            <form:input path="${path}" cssClass="form-control ${empty cssClass ? 'input-xlarge' : cssClass}" id="${empty id?path:id }" maxlength="${maxlength!=null?maxlength:'' }" tabindex="${empty tabindex?'':tabindex }" readonly="${empty readonly?false:readonly } " type="${empty type?'':type }"/>
            <c:if test="${not empty date and date==true }">
            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span></div>
            </c:if>
            <c:if test="${status.error}">
                <span class="control-label">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>
     <c:if test="${not empty date and date==true }">
		<script>
		$('#<c:out value="${empty id?path:id }"/>').datepicker({
			autoclose: true,
			language: 'es',
			format: 'dd/mm/yyyy'
		});
		</script>
	</c:if>
	<c:if test="${not empty gridClass}">
	    </div>
	</c:if>
	
</spring:bind>
