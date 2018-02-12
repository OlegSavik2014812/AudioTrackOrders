<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<%--MENU for the home page--%>

<nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">

  <ul class="nav nav-pills flex-column">
    <%--filter by popularity--%>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=track_list&sort=most_popular"/>">
        <fmt:message key="menu.most_popular"/>
      </a>
    </li>

    <%--filter by date--%>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=track_list&sort=brand_new"/>">
        <fmt:message key="menu.brand_new"/>
      </a>
    </li>

    <%--filter most selling tracks --%>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=track_list&sort=best_selling"/>">
        <fmt:message key="menu.best_selling"/>
      </a>
    </li>
  </ul>
  <ul class="nav nav-pills flex-column">
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=show_packs"/>">
        <fmt:message key="menu.packs"/>
      </a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=show_albums"/>">
        <fmt:message key="menu.albums"/>
      </a>
    </li>
  </ul>

</nav>
