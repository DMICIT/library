<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/21/21
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"></fmt:setLocale>
<fmt:setBundle basename="${bundle}"></fmt:setBundle>
<c:set var="contextPath" value = "${pageContext.request.contextPath}"/>
<html>

<head>
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script></head>


<title>Books</title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

    <h2><fmt:message key="books.header"/></h2>
<form action="admin-books" method="post" id="bookForm" role ="form">
    <input type="hidden" id ="bookId" name ="bookId">
    <input type="hidden" id ="action" name ="action">

    <table class="table">
        <thead>
        <tr>
            <td><fmt:message key="books.#"/></td>
            <td><fmt:message key="books.author"/></td>
            <td><fmt:message key="books.book.name"/></td>
            <td><fmt:message key="books.edition"/></td>
            <td><fmt:message key="books.date.of.reliase"/></td>
            <td></td>
        </tr>
        </thead>

        <c:forEach items="${books}" var="book">
            <tr>
                <td><a href="admin-book?id=${book.id}">${book.id}</a></td>
                <td>${book.author}</td>
                <td>${book.bookName}</td>
                <td>${book.bookEdition}</td>
                <td>${book.reliaseDate}</td>
                <td><a href="#" id="remove"
                onclick="document.getElementById('action').value = 'delete';
                document.getElementById('bookId').value = '${book.id}';
                document.getElementById('bookForm').submit();">
                    delete
                </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
