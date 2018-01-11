<%--
  Created by IntelliJ IDEA.
  User: Oleg Savik
  Date: 10.01.2018
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="../../css/for_sign_in.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<jsp:include page="../util/header.jsp"/>
<div id="header">
    <h1>Sign in</h1>
</div>
<div id="login">
    <form action="controller" method="POST">
        <input type="text" id="user_email" placeholder="Enter user email " pattern="(\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6})"
               required>
        <input type="password" id="user_password" placeholder="Enter user password"
               pattern="((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,})" required>
        <input type="button" id="sign-in-btn" value="Sign in" onclick="goToIndexPage()">
    </form>
    <input type="button" id="sign-up-btn" value="Sign up" onclick="goToSignUpFromSignIn()">
</div>
<jsp:include page="../util/footer.jsp"/>
<script src="../../js/indexredirect.js"></script>
</body>
</html>
