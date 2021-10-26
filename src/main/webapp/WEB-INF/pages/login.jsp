<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/17/21
  Time: 12:14 PM
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

<title><fmt:message key="login.login"/></title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

<h2><fmt:message key="login.login.form"/></h2>
</br>
</br>
<form action="login" method="post" >

  <div class="mb-3">
    <label for="email" class="form-label"><fmt:message key="login.email"/></label>
    <input type="text" class="form-control" id="email" name="email">
  </div>

  <div class="mb-3">
    <label for="password" class="form-label"><fmt:message key="login.password"/></label>
    <input type="password" class="form-control" id="password" name="password">
  </div>

  <button class= "btn btn-primary" type = "submit"><fmt:message key="login.login"/></button></form>

  <fmt:message key="login.message"/>: ${errorMessage}
</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
