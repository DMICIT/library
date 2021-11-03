<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/24/21
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"></fmt:setLocale>
<fmt:setBundle basename="${bundle}"></fmt:setBundle>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a href="${contextPath}" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
            Library </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${contextPath}/" class="nav-link px-2 link-secondary"><fmt:message key="header.home"/></a></li>
            <li><a href="${contextPath}/books" class="nav-link px-2 link-dark"><fmt:message key="header.books"/></a>
            </li>
            <c:if test="${not empty user}">
                <li><a href="${contextPath}/orders" class="nav-link px-2 link-dark"><fmt:message
                        key="header.orders"/></a></li>
                <li><a href="${contextPath}/personal-account" class="nav-link px-2 link-dark"><fmt:message
                        key="header.account"/></a></li>
            </c:if>
            <c:if test="${user eq 'admin@gmail.com'}">
                <li><a href="${contextPath}/admin-users?type=users" class="nav-link px-2 link-dark"><fmt:message
                        key="header.users"/></a></li>
                <li><a href="${contextPath}/admin-users?type=librarians" class="nav-link px-2 link-dark"><fmt:message
                        key="header.librarians"/></a></li>
            </c:if>
        </ul>

        <div class="col-md-3 text-end">
            <c:choose>
                <c:when test="${empty user}">
                    <a href="login" class="btn btn-outline-primary me-2"><fmt:message key="header.login"/></a>
                    <a href="registration" class="btn btn-primary"><fmt:message key="header.registration"/></a>
                </c:when>
                <c:otherwise>
                    <a href="logout" class="btn btn-primary"><fmt:message key="header.logout"/></a>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="dropdown">
            <a class="btn btn-outline-primary dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
               data-bs-toggle="dropdown" aria-expanded="false">
                ${locale}
            </a>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                <li><a class="dropdown-item" href="language?locale=en">EN</a></li>
                <li><a class="dropdown-item" href="language?locale=ru">RU</a></li>
            </ul>
        </div>
    </header>
</div>

</body>
</html>
