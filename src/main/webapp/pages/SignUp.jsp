<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<html lang="en">
<head>
  <c:import url="components/Meta.jsp"/>

  <link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">
</head>

<body>
<c:import url="components/NavBar.jsp"/>

<div class="container">
  <div class="row">
    <form class="form-signin" method="POST" action="action">
      <input type="hidden" name="name" value="sign_up">


      <h2 class="form-signin-heading"><fmt:message key="signup.please_signup"/></h2>

      <div class="form-group">
        <label for="username" class="sr-only"><fmt:message key="signin.email_address_msg"/> </label>
        <input type="text" name="userName" id="username" class="form-control"
               placeholder="<fmt:message key="signin.email_address_msg"/> " required="" autofocus="">
      </div>

      <div class="form-group">
        <label for="inputPassword1" class="sr-only"><fmt:message key="signin.password"/> </label>
        <input type="password" id="inputPassword1" name="password1" class="form-control"
               placeholder="<fmt:message key="signin.password"/>"
               required="">
      </div>

      <div class="form-group">
        <label for="inputPassword2" class="sr-only"><fmt:message key="signin.password"/> </label>
        <input type="password" id="inputPassword2" name="password2" class="form-control"
               placeholder="<fmt:message key="signin.password"/>"
               required="">
      </div>

      <div class="form-group">
        <label for="inputFirstName" class="sr-only"><fmt:message key="signup.firstname"/> </label>
        <input type="text" id="inputFirstName" name="firstName" class="form-control"
               placeholder="<fmt:message key="signup.firstname"/> ">
      </div>

      <div class="form-group">
        <label for="inputLastName" class="sr-only"><fmt:message key="signup.lastname"/> </label>
        <input type="text" id="inputLastName" name="lastName" class="form-control"
               placeholder="<fmt:message key="signup.lastname"/> ">
      </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="button.signup"/></button>
    </form>
  </div>
</div>
<c:import url="components/Footer.jsp"/>
</body>
</html>
