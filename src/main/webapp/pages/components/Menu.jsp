<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="i18n.MessageBundle_ru"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
  <ul class="nav nav-pills flex-column">

    <li class="nav-item">
      <a class="nav-link" href="#"><fmt:message key="index.brand_new"/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"><fmt:message key="index.most_popular"/></a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#"> <fmt:message key="index.best_selling"/> </a>
    </li>

    <c:if test="%{User.Role == 'ROLE.ADMIN'}">
      <li class="nav-item">
        <a class="nav-link" href="#"><fmt:message key="menu.add_tarck"/></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#"><fmt:message key="menu.add_discount"/></a>
      </li>
    </c:if>
  </ul>
</nav>
