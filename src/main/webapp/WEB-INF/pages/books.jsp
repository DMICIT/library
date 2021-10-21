<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/21/21
  Time: 10:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
    <h2>Books</h2>

    <table>
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

</body>
</html>
