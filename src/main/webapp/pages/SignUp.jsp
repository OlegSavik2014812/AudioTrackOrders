<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="ru"/>
  <fmt:setBundle basename="i18n.MessageBundle_ru"/>
  <link rel="icon" href="../../../../favicon.ico">

  <title><fmt:message key="button.signup"/></title>

  <!-- Bootstrap core CSS -->
  <link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet">
  <link href="<c:url value="css/audioord.css"/> " rel="stylesheet">
  <!-- Custom styles for this template -->
</head>
<body>
<c:import url="components/NavBar.jsp"/>
<div class="container">
  <form class="form-signin" method="post" action="/audioord/action?name=sign_up">
    <h2 class="form-signin-heading"><fmt:message key="signup.please_signup"/></h2>
    <label for="inputEmail" class="sr-only"><fmt:message key="signin.email_address_msg"/> </label>
    <input type="email" name="userName" id="inputEmail" class="form-control"
           placeholder="<fmt:message key="signin.email_address_msg"/> " required="" autofocus="">
    <label for="inputPassword" class="sr-only"><fmt:message key="signin.password"/> </label>
    <input type="password" id="inputPassword" name="password" class="form-control"
           placeholder="<fmt:message key="signin.password"/>"
           required="">
    <input type="text" id="inputFirstName" name="firstName" class="form-control"
           placeholder="<fmt:message key="signup.firstname"/> ">
    <input type="text" id="inputLastName" name="lastName" class="form-control"
           placeholder="<fmt:message key="signup.lastname"/> ">
    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="button.signup"/></button>
  </form>
</div> <!-- /container -->
<c:import url="components/Footer.jsp"/>
</body>
</html>
