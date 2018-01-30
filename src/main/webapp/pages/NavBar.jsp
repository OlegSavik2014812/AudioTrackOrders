<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
  <div class="container">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
            aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <a class="navbar-brand" href="<c:url value="/action?name=track_list&sort=MOST_POPULAR&page=1"/>">
      <img src="<c:url value="/img/logo.png"/>" width="30" height="30">
      <fmt:message key="navbar.head_name"/>
    </a>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">

      <ul class="navbar-nav mr-auto">
        <c:if test="${sessionScope.USER != null && sessionScope.USER.role=='ADMIN'}">
          <a class="nav-item nav-link" href="<c:url value="/action?name=upload_track"/>">Добавить музыку</a>
        </c:if>

        <c:if test="${sessionScope.USER != null && sessionScope.USER.role=='CLIENT'}">
          <a class="nav-item nav-link" href="<c:url value="/action?name=my_tracks"/>">Моя музыка</a>
        </c:if>
      </ul>

      <%--LOGIN LOGOUT--%>
      <ul class="navbar-nav">
        <c:if test="${sessionScope.USER == null }">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/action?name=sign_in"/>"><fmt:message key="button.signin"/></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/action?name=sign_up"/> "><fmt:message key="button.signup"/></a>
          </li>
        </c:if>
      </ul>

      <%--USER ITEMS--%>
      <ul class="navbar-nav">
        <c:if test="${sessionScope.USER != null }">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink2"
               data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
              <i class=""></i>
                ${sessionScope.USER.username}
            </a>

            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink2">
                <%--USER CART--%>
              <c:if test="${sessionScope.USER != null && sessionScope.USER.role=='CLIENT'}">
                <a class="dropdown-item" href="<c:url value="/action?name=view_cart"/>">Корзина</a>
              </c:if>

                <%--ADMIN USER LIST--%>
              <c:if test="${sessionScope.USER != null && sessionScope.USER.role=='ADMIN'}">
                <a class="dropdown-item" href="<c:url value="/action?name=user_list&page=1"/> "><fmt:message
                  key="navbar.userlist"/></a>

                <%--ADMIN ORDERS--%>
                <a class="dropdown-item" href="<c:url value="/action?name=order_list&page=1"/> "><fmt:message
                  key="navbar.purchases"/></a>
              </c:if>

                <%--SING OUT--%>
              <a class="dropdown-item" href="<c:url value="/action?name=sign_out"/> "><fmt:message
                key="button.signout"/></a>
            </div>
          </li>
        </c:if>

        <%--LOCALE--%>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink1"
             data-toggle="dropdown"
             aria-haspopup="true" aria-expanded="false">
            <fmt:message key="localization.language"/>
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink1">
            <a href="<c:url value="/action?name=change_local&local=en_US"/>"
               class="dropdown-item"><fmt:message
              key="localization.english"/> </a>
            <a href="<c:url value="/action?name=change_local&local=ru_RU"/>"
               class="dropdown-item"><fmt:message
              key="localization.russian"/> </a>
          </div>
        </li>
      </ul>
    </div>
  </div>
</nav>








