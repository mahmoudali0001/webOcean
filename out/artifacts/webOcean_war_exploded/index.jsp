<%@ page import="com.app.ocean.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Romani Ezzat
  Date: 12/28/2019
  Time: 7:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OceanApp.com</title>
    <link rel="stylesheet" href="css/CssStyle.css">
</head>
<body>

<h1 class="The-Logo">Ocean</h1>
<div class="login-box">

    <form action="Login" method="post">

        <h1>Login</h1>

        <div class="text-box">

            <input type="text"  placeholder="Username" name="user_name" value="${username}" autofocus="on">
        </div>

        <div class="text-box">

            <input type="password" placeholder="Password" value="${password}" name="password">
        </div>

        <input class="btn" type="submit" name="btn" value="Sign in">

    </form>
    <p STYLE="color: red; font-size: 25px; margin: 25px;">${error}</p>

    <a class="forget" href="forget_password.jsp">Forget Password!</a>
    <a class="newAcc" href="add_user.jsp">Sign Up</a>
</div>

</body>
</html>

