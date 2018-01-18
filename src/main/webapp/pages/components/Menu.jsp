<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
  <ul class="nav nav-pills flex-column">
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=track_list&filter=most_popular"/>"><fmt:message
        key="index.most_popular"/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=track_list&filter=brand_new"/>"><fmt:message
        key="index.brand_new"/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=track_list&filter=best_selling"/>"><fmt:message
        key="index.best_selling"/> </a>
    </li>
  </ul>
  <ul class="nav nav-pills flex-column">
    <c:if test="${sessionScope.USER.role == 'ADMIN'}">
      <li class="nav-item">
        <a class="nav-link" href="pages/AddTrack.jsp"><fmt:message key="menu.add_tarck"/></a>
        <a class="nav-link" href="pages/AddDiscount.jsp"><fmt:message key="menu.add_discount"/></a>
      </li>
    </c:if>
  </ul>
</nav>
