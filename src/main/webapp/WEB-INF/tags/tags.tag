<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ attribute name="errorMessages" required="true" %>--%>
<%@ attribute name="errorMessages" required="true" type="java.util.List" %>
<%@ attribute name="locale" required="true" %>
<%@ attribute name="bundle" required="true" %>
<fmt:setLocale value="${locale}"></fmt:setLocale>
<fmt:setBundle basename="${bundle}"></fmt:setBundle>

<c:if test="${not empty errorMessages}">
    <c:forEach items="${errorMessages}" var="message">
        <fmt:message key="${message}"/>
        </br>

    </c:forEach>
</c:if>