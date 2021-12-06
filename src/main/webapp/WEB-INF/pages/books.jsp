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
                   value="${not empty param.search ? param.search: ''}"
                   placeholder="Type the Author or Book Name">
        </div>
        <button type="submit" class="btn btn-info"><fmt:message key="search"/></button>
        </br>
        </br>
    </form>

    <c:choose>
    <c:when test="${not empty books}">

    <form action="orders" method="post" id="bookForm" role="form">
        <input type="hidden" id="bookId" name="bookId">
        <input type="hidden" id="action" name="action">

        <table class="table">
            <thead>
            <tr>
                <td><fmt:message key="books.#"/></td>
                <td><fmt:message key="books.author"/>
                    <a style="text-decoration: none;"
                       href="books?sort=author${not empty param.search ? '&search=' += param.search  : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9650;</a>
                    <a style="text-decoration: none;"
                       href="books?sort=author&order=desc${not empty param.search ? '&search=' += param.search : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9660;</a>
                </td>
                <td><fmt:message key="books.book.name"/>
                    <a style="text-decoration: none;"
                       href="books?sort=book_Name${not empty param.search ? '&search=' += param.search : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9650;</a>
                    <a style="text-decoration: none;"
                       href="books?sort=book_Name&order=desc${not empty param.search ? '&search=' += param.search : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9660;</a>
                </td>
                </td>
                <td><fmt:message key="books.edition"/>
                    <a style="text-decoration: none;"
                       href="books?sort=book_Edition${not empty param.search ? '&search=' += param.search : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9650;</a>
                    <a style="text-decoration: none;"
                       href="books?sort=book_Edition&order=desc${not empty param.search ? '&search=' += param.search : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9660;</a>
                </td>
                <td><fmt:message key="books.date.of.reliase"/>
                    <a style="text-decoration: none;"
                       href="books?sort=reliase_Date${not empty param.search ? '&search=' += param.search : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9650;</a>
                    <a style="text-decoration: none;"
                       href="books?sort=reliase_Date&order=desc${not empty param.search ? '&search=' += param.search : ''}${not empty param.page ? '&page=' += param.page : ''}">&#9660;</a>
                </td>
                <td></td>
                <td></td>
            </tr>
            </thead>

            <c:forEach items="${books}" var="book" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${book.author}</td>
                    <td>${book.bookName}</td>
                    <td>${book.bookEdition}</td>
                    <td>${book.reliaseDate}</td>

                    <c:if test="${user.role eq 'user'}">
                        <td><a href="#" id="abonement"
                               onclick="document.getElementById('action').value = 'abonement';
                                       document.getElementById('bookId').value = '${book.id}';
                                       document.getElementById('bookForm').submit();">
                            <fmt:message key="abonement"/>
                        </a></td>
                        <td><a href="#" id="library hall"
                               onclick="document.getElementById('action').value = 'library hall';
                                       document.getElementById('bookId').value = '${book.id}';
                                       document.getElementById('bookForm').submit();">
                            <fmt:message key="library.hall"/>
                        </a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${currentPage !=1}">
        <a href="books?page=${currentPage-1}${not empty param.search ? '&search=' += param.search : ''}${not empty param.sort ? '&sort=' += param.sort : ''}${not empty param.order ? '&order=' += param.order : ''}">
            <fmt:message key="previous"/>
        </a>
        </c:if>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
        <c:when test="${currentPage eq i}">
            ${i}
        </c:when>
        <c:otherwise>
        <a href="books?page=${i}${not empty param.search ? '&search=' += param.search : ''}${not empty param.sort ? '&sort=' += param.sort : ''}${not empty param.order ? '&order=' += param.order : ''}">
                ${i}
        </a>
        </c:otherwise>
        </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt numberOfPages}">
        <a href="books?page=${currentPage+1}${not empty param.search ? '&search=' += param.search : ''}${not empty param.sort ? '&sort=' += param.sort : ''}${not empty param.order ? '&order=' += param.order : ''}">
            <fmt:message key="next"/>
        </a>

        </c:if>
        </c:when>
        <c:otherwise>
        <h2>Sorry but nothing found &#9785;</h2>
        </c:otherwise>

        </c:choose>

</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
