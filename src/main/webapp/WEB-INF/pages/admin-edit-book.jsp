<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
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

    <form action="admin-edit-book${not empty book.id ? '?id=' += book.id : ''}" method="post" id="bookForm" role ="form">
        <c:if test ="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id ="bookId" name ="bookId" value="${book.id}">
        <input type="hidden" id ="action" name ="action" value="${action}">

        <div class ="form-group col-xs-4">
            <label for="author" class="control-label col-xs-4"><fmt:message key="books.author"/></label>
            <input type = "text" name="author" id = "author" class="form-control" value ="${book.author}" required>

            <label for="bookName" class="control-label col-xs-4"><fmt:message key="books.book.name"/></label>
            <input type = "text" name="bookName" id = "bookName" class="form-control" value ="${book.bookName}" required>

            <label for="bookEdition" class="control-label col-xs-4"><fmt:message key="books.edition"/></label>
            <input type = "text" name="bookEdition" id = "bookEdition" class="form-control" value ="${book.bookEdition}" required>

            <label for="reliaseDate" class="control-label col-xs-4"><fmt:message key="books.date.of.reliase"/></label>
            <input type = "text" name="reliaseDate" id = "reliaseDate" class="form-control" value ="${book.reliaseDate}" required>

            <label for="count" class="control-label col-xs-4"><fmt:message key="count"/></label>
            <input type = "text" name="count" id = "count" class="form-control" value ="${book.catalogData.totalQuantity}" required>
            </br>
            <button type="submit" class="btn btn-primary btn-md">Accept</button>
        </div>
    </form>
    <custom:tags errorMessages="${errorMessages}" bundle="${bundle}" locale="${locale}"></custom:tags>

</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
