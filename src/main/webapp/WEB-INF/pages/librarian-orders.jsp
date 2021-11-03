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
</head>

<title>Book Orders</title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

    <form action="librarian-orders" method="post" id="bookForm" role="form">
        <input type="hidden" id="action" name="action">
        <input type="hidden" id="orderId" name="orderId">

    <h2>Book Orders</h2>
    </br>
    </br>

    <table class="table">
        <thead>
        <tr>
            <td><fmt:message key="orders.#"/></td>
            <td><fmt:message key="orders.bookId"/></td>
            <td><fmt:message key="orders.book.spot"/></td>
            <td><fmt:message key="orders.status"/></td>
            <td><fmt:message key="orders.return.date"/></td>
            <td></td>

        </tr>
        </thead>
        <c:forEach items="${orders}" var="order">
        <tr>

            <td>${order.id}</td>
            <td>${order.bookId}</td>
            <td>${order.bookSpot}</td>
            <td>${order.status}</td>
            <td>${order.returnDate}</td>
            <td>
                <c:if test="${order.status eq 'expected'}">
                <a href="#" id="checked out"
                   onclick="document.getElementById('action').value = 'checked out';
                   document.getElementById('orderId').value = ${order.id};
                           document.getElementById('bookForm').submit();">
                    check out
                </a>
                </c:if>
                <c:if test="${order.status  eq 'checked out'}">
                    <a href="#" id="checked out"
                       onclick="document.getElementById('action').value = 'returned';
                               document.getElementById('orderId').value = ${order.id};
                               document.getElementById('bookForm').submit();">
                        returned
                    </a>
                </c:if>
            </td>>
        </tr>
        </c:forEach>
    </table>
    </form>

</div>
<c:import url="components/footer.jsp"/>

</body>
</html>