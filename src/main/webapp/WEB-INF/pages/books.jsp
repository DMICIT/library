<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"></fmt:setLocale>
<fmt:setBundle basename="${bundle}"></fmt:setBundle>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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


<title>Books</title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

    <h2><fmt:message key="books.header"/></h2>
    <form action="books" method="get">
        <div class="form-group col-xs-5">
            <input type="text" name="search" id="search" class="form-control" required
                   placeholder="Type the Author or Book Name">
        </div>
        <button type="submit" class="btn btn-info">Search</button>
        </br>
        </br>
    </form>

    <c:choose>
        <c:when test="${not empty books}">
            <table class="table">
                <thead>
                <tr>
                    <td><fmt:message key="books.#"/></td>
                    <td><fmt:message key="books.author"/></td>
                    <td><fmt:message key="books.book.name"/></td>
                    <td><fmt:message key="books.edition"/></td>
                    <td><fmt:message key="books.date.of.reliase"/></td>
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

        </c:when>
        <c:otherwise>
            <h2>Sorry but nothing found =_( </h2>
        </c:otherwise>


    </c:choose>

</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
