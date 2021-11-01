<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


<title>Users</title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

    <h2><fmt:message key="users.header"/></h2>
    <form action="admin-edit-user" method="post" id="userForm" role ="form">
        <c:if test ="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id ="userId" name ="userId" value="${user.id}">
        <input type="hidden" id ="action" name ="action" value="${action}">

        <div class ="form-group col-xs-4">
            <label for="name" class="control-label col-xs-4">name</label>
            <input type = "text" name="name" id = "name" class="form-control" value ="${user.name}" required>

            <label for="email" class="control-label col-xs-4">email</label>
            <input type = "text" name="email" id = "email" class="form-control" value ="${user.email}" required>

            <label for="sex" class="control-label col-xs-4">sex</label>
            <input type = "text" name="sex" id = "sex" class="form-control" value ="${user.sex}" required>

            <label for="phone" class="control-label col-xs-4">phone</label>
            <input type = "text" name="phone" id = "phone" class="form-control" value ="${user.phone}" required>

            <label for="role" class="control-label col-xs-4">role</label>
            <input type = "text" name="role" id = "role" class="form-control" value ="${user.role}" required>

            <label for="password" class="control-label col-xs-4">password</label>
            <input type = "text" name="password" id = "password" class="form-control" value ="${user.password}" required>
            </br>
            <button type="submit" class="btn btn-primary btn-md">Accept</button>
        </div>
    </form>
</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
