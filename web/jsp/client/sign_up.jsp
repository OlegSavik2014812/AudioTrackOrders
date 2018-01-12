<%--
  Created by IntelliJ IDEA.
  User: Oleg Savik
  Date: 10.01.2018
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" href="../../css/for_sign_up.css">
  <title>Title</title>
</head>
<body>
<jsp:include page="../util/header.jsp"/>
<div id="header">
  <h1>Registration form</h1>
</div>
<div id="registration">
  <form action="controller" method="POST">
    <input type="text" id="user_email" placeholder="Enter your email(example@email.com)"
           pattern="(\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})" required>
    <input type="text" id="user_first_name" placeholder="Enter your first name" pattern="^(([A-Z]{1}[a-z]{1,}))$"
           required>
    <input type="text" id="user_last_name" placeholder="Enter your last name" pattern="^(([A-Z]{1}[a-z]{1,}))$"
           required>
    <input type="text" id="user_login" placeholder="Enter your login" pattern="^([A-Za-z0-9_]{5,})$" required>
    <input type="password" id="user_password" placeholder="Enter your password"
           pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})" required>
    <input type="password" id="confirm_password" placeholder="Confirm your password"
           pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})" required>
    <input type="button" id="sign-up-btn" value="Sign up" onclick="goToIndexPage()">
  </form>
</div>
<jsp:include page="../util/footer.jsp"/>
<script src="../../js/indexredirect.js"></script>
</body>
</html>
