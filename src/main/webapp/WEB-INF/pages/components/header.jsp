<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/24/21
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value = "${pageContext.request.contextPath}"/><html>
<head>
    <title>Title</title>
</head>
<body>

<div class="container">
    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
        <a href="${contextPath}" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none"> Library </a>

        <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
            <li><a href="${contextPath}/" class="nav-link px-2 link-secondary">Home</a></li>
            <li><a href="${contextPath}/books" class="nav-link px-2 link-dark">Books</a></li>
            <li><a href="${contextPath}/orders" class="nav-link px-2 link-dark">Orders</a></li>
        </ul>

        <div class="col-md-3 text-end">
            <c:choose>
                <c:when test = "${empty user}">
                    <a href="login" class="btn btn-outline-primary me-2">login</a>
                    <a href="registration" class="btn btn-primary">registration</a>
                </c:when>
                <c:otherwise>
                    <a href="logout" class="btn btn-primary">logout</a>
                </c:otherwise>
            </c:choose>
        </div>
    </header>
</div>

</body>
</html>
