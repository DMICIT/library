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
<c:set var="contextPath" value = "${pageContext.request.contextPath}"/>
<html>

<head><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <title>Books</title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

    <h2>Books</h2>

    <table class="table">
        <thead>
        <tr>
            <td>#</td>
            <td>Author</td>
            <td>Book name</td>
            <td>Edition</td>
            <td>Date of reliase</td>
        </tr>
        </thead>

        <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.author}</td>
            <td>${book.bookName}</td>
            <td>${book.bookEdition}</td>
            <td>${book.reliaseDate}</td>
        </tr>
        </c:forEach>
    </table>
</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
