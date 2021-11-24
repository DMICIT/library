<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"></fmt:setLocale>
<fmt:setBundle basename="${bundle}"></fmt:setBundle>
<html>
<head>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <title>User abonement</title>
</head>
<body>

<c:import url="components/header.jsp"/>
<div class="container">

<h2>${userData.userName}</h2>


<table class="table">
<thead>
<tr>

    <td><fmt:message key="orders.#"/></td>
    <td><fmt:message key="books.book.name"/></td>
    <td><fmt:message key="books.author"/></td>
    <td><fmt:message key="orders.book.spot"/></td>
    <td><fmt:message key="orders.return.date"/></td>
    <td><fmt:message key="orders.status"/></td>


</tr>
</thead>

<c:forEach items="${allOrders}" var="order">
    <tr>

        <td>${order.id}</td>
        <td>${order.bookData.bookName}</td>
        <td>${order.bookData.author}</td>
        <td> <c:if test="${order.bookSpot eq 'abonement'}">
            <fmt:message key="abonement"/>
        </c:if>
            <c:if test="${order.bookSpot eq 'library hall'}">
                <fmt:message key="library.hall"/>
            </c:if></td>
        <td>${order.returnDate}</td>
        <td>
            <c:if test="${order.status eq 'expected'}">
                <fmt:message key="expected"/>
            </c:if>
            <c:if test="${order.status eq 'checked out'}">
                <fmt:message key="checked.out"/>
            </c:if>
            <c:if test="${order.status eq 'returned'}">
                <fmt:message key="returned"/>
            </c:if>
        </td>
    </tr>

    </c:forEach>
    </table>
    </div>
    <c:import url="components/footer.jsp"/>
    </body>
    </html>
