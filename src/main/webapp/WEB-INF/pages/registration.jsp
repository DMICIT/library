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
<html>

<head><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

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
