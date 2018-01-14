<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
<head>
  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="ru"/>
  <fmt:setBundle basename="i18n.MessageBundle_ru"/>

  <c:import url="components/Meta.jsp"/>
</head>
<body>
<c:import url="components/NavBar.jsp"/>

<%--<c:if test="${err.message}" var="">--%>
<%----%>
<%--</c:if>--%>

<div class="container">
  <div class="row">

    <form class="form-signin" method="POST" action="action">
      <input type="hidden" name="name" value="sign_in"/>

      <h2 class="form-signin-heading"><fmt:message key="signin.please_sign"/></h2>

      <label for="inputEmail" class="sr-only">
        <fmt:message key="signin.email_address_msg"/>
      </label>
      <input type="text" id="inputEmail" class="form-control" name="userName"
             placeholder="<fmt:message key="signin.email_address_msg"/> " required="" autofocus="">

      <label for="inputPassword" class="sr-only">
        <fmt:message key="signin.password"/>
      </label>
      <input type="password" name="password" id="inputPassword" class="form-control" placeholder="<fmt:message key="signin.password"/>"
             required="">

      <button class="btn btn-lg btn-primary btn-block" type="submit">
        <fmt:message key="button.signin"/>
      </button>
    </form>

  </div>
</div>
<c:import url="components/Footer.jsp"/>
</body>
</html>
