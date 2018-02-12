<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="signin.title"/></title>
  <c:import url="../css_import.jsp"/>
  <link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<c:import url="../NavBar.jsp"/>

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

<div class="container">
  <div class="row">

    <form class="form-signin" method="POST" action="action">
      <input type="hidden" name="name" value="sign_in"/>

      <h2 class="form-signin-heading"><fmt:message key="signin.head_msg"/></h2>

      <div class="form-group">
        <label for="username" class="sr-only">
          <fmt:message key="signin.enter_username"/>
        </label>
        <span class="validation-message"><fmt:message key="signup.msg.username"/> </span>
        <input type="text" id="username" class="form-control" name="userName"
               placeholder="<fmt:message key="signin.enter_username"/> " required="" autofocus="">
      </div>

      <div class="form-group">
        <label for="inputPassword" class="sr-only">
          <fmt:message key="signin.enter_password"/>
        </label>
        <span class="validation-message"><fmt:message key="signup.msg.username"/> </span>
        <input type="password" name="password" id="inputPassword" class="form-control"
               placeholder="<fmt:message key="signin.enter_password"/>"
               required="">
      </div>

      <button class="btn btn-lg btn-primary btn-block" id="submitButton" type="submit">
        <fmt:message key="button.signin"/>
      </button>

    </form>
  </div>
</div>
<c:import url="../js_import.jsp"/>
</body>
</html>
