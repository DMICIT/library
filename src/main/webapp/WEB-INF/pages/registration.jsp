<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/12/21
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<a href >Home</a>
<h2>Registration form</h2>
</br>
</br>
<form action="registration" method="post" >
    Name:
    <input type = "text" name="name" required >
    </br>
    Email:
    <input type = "text" name="email" required >
    </br>
    Password:
    <input type = "text" name="password" required >
    </br>
    </br>
    Confirm Password:
    <input type = "text" name="confirm_password" required >
    </br>
    Phone:
    <input type = "text" name="phone" required >
    </br>
    Sex:
    <input type = "text" name="sex" required >
    </br>

    <button type = "submit">Register</button>
</form>
</body>
</html>
