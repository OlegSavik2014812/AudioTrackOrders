<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="${sessionScope.local}"/>
  <fmt:setBundle basename="i18n.MessageBundle"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="index.title"/></title>
  <c:import url="components/css_import.jsp"/>
  <link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<c:import url="components/NavBar.jsp"/>

<div class="container">
  <div class="row">

    <form class="form-signin" method="POST" action="action">
      <input type="hidden" name="name" value="sign_in"/>


      <h2 class="form-signin-heading"><fmt:message key="signin.please_sign"/></h2>

      <div class="form-group">
        <label for="username" class="sr-only">
          <fmt:message key="signin.email_address_msg"/>
        </label>
        <input type="text" id="username" class="form-control" name="userName"
               placeholder="<fmt:message key="signin.email_address_msg"/> " required="" autofocus="">
      </div>

      <div class="form-group">
        <label for="inputPassword" class="sr-only">
          <fmt:message key="signin.password"/>
        </label>
        <input type="password" name="password" id="inputPassword" class="form-control"
               placeholder="<fmt:message key="signin.password"/>"
               required="">
      </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit">
        <fmt:message key="button.signin"/>
      </button>
    </form>

  </div>
</div>
<c:import url="components/js_import.jsp"/>
</body>
</html>
