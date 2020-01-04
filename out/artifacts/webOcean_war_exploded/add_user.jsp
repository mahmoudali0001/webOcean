<%--
  Created by IntelliJ IDEA.
  User: Romani Ezzat
  Date: 12/28/2019
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/LoginStyle.css">
    <title>Login</title>
</head>
<body>
<div>
    <h1>Sign Up</h1>

    <form action="AddNewUser" method="POST" oninput='confirm_password.setCustomValidity(confirm_password.value != password.value ? "Passwords do not match." : "")'>
        <input type="text" class="SignUp-box" placeholder="first_name" name="first_name" autofocus="on">
        <input type="text" class="SignUp-box" placeholder="last_name" name="last_name">
        <input type="email" class="SignUp-box" placeholder="email" name="email">
        <input type="text" class="SignUp-box" placeholder="birthday" name="birthday">
        <input type="text" class="SignUp-box" placeholder="phone_number" name="phone_number">
        <input type="text" class="SignUp-box" placeholder="user_name" name="user_name" required>
        <input type="password" class="SignUp-box" placeholder="password" name="password" required>
        <input type="password" class="SignUp-box" placeholder="Confrim Password" name="confirm_password" required>

        <input type="submit" value="Sign Up" class="SignUp-Btn" name="Sign Up">

    </form>


</div>
</body>
</html>
