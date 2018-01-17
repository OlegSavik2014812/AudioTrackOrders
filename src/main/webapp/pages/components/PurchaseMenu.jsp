<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
  <ul class="nav nav-pills flex-column">
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=order_list&status=COMPLETED"/>"><fmt:message
        key="purchasemenu.completed"/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=order_list&filter=SUBMITTED"/>">
        <fmt:message key="purchasemenu.submitted"/></a>
      <a class="nav-link" href="<c:url value="/action?name=order_list&status=REJECTED"/>"><fmt:message
        key="purchasemenu.rejected"/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="<c:url value="/action?name=order_list&status=SUBMITTED"/>"><fmt:message
        key="purchasemenu.submitted"/> </a>
    </li>

    <c:if test="${sessionScope.USER.role == 'ADMIN'}">
      <li class="nav-item">
        <a class="nav-link" href="#"><fmt:message key="menu.add_tarck"/></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><fmt:message key="menu.add_discount"/></a>
      </li>
    </c:if>
  </ul>
</nav>
