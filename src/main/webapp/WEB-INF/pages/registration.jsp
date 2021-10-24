<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/12/21
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value = "${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"></fmt:setLocale>
<fmt:setBundle basename="${bundle}"></fmt:setBundle>
<html>

<head>

    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script></head>

<title>Registration</title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

<h2>Registration form</h2>
</br>
</br>
<form action="registration" method="post" >
    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" class="form-control" id="name" name="name" >
    </div>
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="text" class="form-control" id="email" name="email">
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <div class="mb-3">
        <label for="confirm_password" class="form-label">Confirm password</label>
        <input type="password" class="form-control" id="confirm_password" name="confirm_password">
    </div>
    <div class="mb-3">
        <label for="phone" class="form-label">Phone</label>
        <input type="text" class="form-control" id ="phone" name ="phone">
    </div>
  <div class="mb-3">
      <label for="sex" class="form-label">Sex</label>
      <input type="text" class="form-control" id ="sex" name ="sex">
  </div>

    <button class= "btn btn-primary" type="submit">Register</button>
</form>
    Message: ${errorMessage}
</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
