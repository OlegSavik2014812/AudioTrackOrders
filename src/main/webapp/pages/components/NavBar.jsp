<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
  <button class="navbar-toggler navbar-toggler-right hidden-lg-up" type="button" data-toggle="collapse"
          data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false"
          aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand" href="<c:url value="/"/>">
    <img src="<c:url value="/img/logo.png"/>" width="30" height="30"/>
    <fmt:message key="navbar.head_name"/>
  </a>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"><fmt:message key="navbar.to_home"/> <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value="/pages/SignIn.jsp"/>"><fmt:message key="button.signin"/> </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value="/pages/SignUp.jsp"/> "><fmt:message key="button.signup"/> </a>
      </li>
    </ul>
    <form class="form-inline mt-2 mt-md-0">
      <input class="form-control mr-sm-2" type="text" placeholder="<fmt:message key="navbar.placeholder_search"/>">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="button.search"/></button>
    </form>
  </div>
</nav>
