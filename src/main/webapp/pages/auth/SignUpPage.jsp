<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="signup.title"/></title>
  <c:import url="../css_import.jsp"/>
  <link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">

  <style>
    .form-control.disabled {
      border-color: red;
    }

    .validation-message {
      color: red;
      font-size: 12px;
      word-wrap: break-word;
      display: none;
    }

    .validation-message.visible {
      display: block;
    }
  </style>
</head>

<body>
<c:import url="../NavBar.jsp"/>

<div class="container">
  <div class="row">
    <form class="form-signin" method="POST" action="action" name="sign_up_form">
      <input type="hidden" name="name" value="sign_up">

      <h2 class="form-signin-heading"><fmt:message key="signup.please_signup"/></h2>

      <c:out value="${sessionScope.error}"/>
      <div class="form-group">

        <label for="username" class="sr-only">
          <fmt:message key="signup.enter_username"/>
        </label>
        <span class="validation-message"><fmt:message key="signup.msg.username"/> </span>
        <input type="text" name="userName" id="username" class="form-control"
               placeholder="<fmt:message key="signup.enter_username"/> " autofocus="" onchange="isFormValid()">
      </div>

      <div class="form-group">
        <span class="validation-message"><fmt:message key="signup.msg.password"/> </span>

        <label for="inputPassword1" class="sr-only"><fmt:message key="signup.enter_password"/> </label>

        <input type="password" id="inputPassword1" name="password1" class="form-control"
               placeholder="<fmt:message key="signin.enter_password"/>"
               required="" onchange="isFormValid()">
      </div>

      <div class="form-group">
        <label for="inputPassword2" class="sr-only"><fmt:message key="signup.confirm_password"/> </label>
        <input type="password" id="inputPassword2" name="password2" class="form-control"
               placeholder="<fmt:message key="signup.confirm_password"/>"
               required="" onchange="isFormValid()">
      </div>

      <div class="form-group">
        <span class="validation-message"><fmt:message key="signup.msg.firstname"/> </span>
        <label for="inputFirstName" class="sr-only"><fmt:message key="signup.firstname"/> </label>
        <input type="text" id="inputFirstName" name="firstName" class="form-control"
               placeholder="<fmt:message key="signup.firstname"/> " onchange="isFormValid()">
      </div>

      <div class="form-group">
        <span class="validation-message"><fmt:message key="signup.msg.lastname"/> </span>
        <label for="inputLastName" class="sr-only"><fmt:message key="signup.lastname"/> </label>
        <input type="text" id="inputLastName" name="lastName" class="form-control"
               placeholder="<fmt:message key="signup.lastname"/>" onchange=isFormValid()>
      </div>

      <button class="btn btn-lg btn-primary btn-block" id="submitButton" type="submit" disabled>
        <fmt:message key="button.signup"/>
      </button>

    </form>
  </div>
</div>

<c:import url="../js_import.jsp"/>
</body>
</html>
