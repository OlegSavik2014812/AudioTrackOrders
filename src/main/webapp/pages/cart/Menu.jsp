<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<%--MENU for the home page--%>

<nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">

  <ul class="nav nav-pills flex-column">

    <%--filter by date--%>
    <c:if test="${sessionScope.cart.hasItems}">
      <li class="nav-item">
        <a class="nav-link" href="<c:url value="/action?name=cart_order"/>">
          <fmt:message key="menu.order"/>
        </a>
      </li>

      <%--filter most selling tracks --%>
      <li class="nav-item">
        <a class="nav-link" href="<c:url value="/action?name=cart_order&clear=true"/>">
          <fmt:message key="menu.cancel"/>
        </a>
      </li>
    </c:if>
  </ul>

</nav>
