<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="ru"/>
  <fmt:setBundle basename="i18n.MessageBundle_ru"/>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="../../../../favicon.ico">

  <title><fmt:message key="signin.title"/></title>

  <!-- Bootstrap core CSS -->
  <link href="../css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
</head>
<body>

<div class="container">
  <form class="form-signin">
    <h2 class="form-signin-heading"><fmt:message key="signin.please_sign"/></h2>
    <label for="inputEmail" class="sr-only"><fmt:message key="signin.email_address_msg"/> </label>
    <input type="email" id="inputEmail" class="form-control"
           placeholder="<fmt:message key="signin.email_address_msg"/> " required="" autofocus="">
    <label for="inputPassword" class="sr-only"><fmt:message key="signin.password"/> </label>
    <input type="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="signin.password"/>"
           required="">
    <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me"> <fmt:message key="signin.remember_me"/>
      </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="button.signin"/></button>
  </form>
</div> <!-- /container -->
</body>
</html>
