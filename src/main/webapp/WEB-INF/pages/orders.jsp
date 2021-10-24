<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/23/21
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value = "${pageContext.request.contextPath}"/>
<html>

<head><link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <title>Orders</title>
</head>
<body>
<c:import url="components/header.jsp"/>
<div class="container">

<h2>Login form</h2>
</br>
</br>
<form action="orders" method="post" >

    Book:
    <input type = "text" name="bookId" required >
    </br>


    <button type = "submit">Enter</button>
</form>
</div>
<c:import url="components/footer.jsp"/>
</body>
</html>
