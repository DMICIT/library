<%--
  Created by IntelliJ IDEA.
  User: dmytro
  Date: 10/17/21
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<a href = "/">Home</a>

<h2>Login form</h2>
</br>
</br>
<form action="login" method="post" >

  Email:
  <input type = "text" name="email" required >
  </br>
  Password:
  <input type = "text" name="password" required >
  </br>

  <button type = "submit">Enter</button>
</form>

Message: ${errorMessage}

</body>
</html>
