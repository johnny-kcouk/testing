<%@tag description="Extended textarea tag to allow for sophisticated errors" pageEncoding="UTF-8"%>
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
<%@attribute name="cols" required="false" type="java.lang.String"%>
<%@attribute name="rows" required="false" type="java.lang.String"%>
<%@attribute name="gridClass" required="false" type="java.lang.String"%>

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
            <form:textarea path="${path}" cssClass="form-control ${empty cssClass ? 'input-xlarge' : cssClass}" id="${empty id?path:id }" maxlength="${maxlength!=null?maxlength:'' }" tabindex="${empty tabindex?'':tabindex }" readonly="${empty readonly?false:readonly }" cols="${empty cols?'':cols }" rows="${empty rows?'':rows }"/>
            <c:if test="${status.error}">
                <span class="control-label">${status.errorMessage}</span>
            </c:if>
        </div>
    </div>     
	<c:if test="${not empty gridClass}">
	    </div>
	</c:if>
</spring:bind>
