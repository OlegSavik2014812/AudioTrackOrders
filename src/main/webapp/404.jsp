<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="error.title"/></title>
  <c:import url="pages/components/css_import.jsp"/>
</head>

<body>
<%--simple style for error page--%>
<style>
  body {
    background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAAaCAYAAACpSkzOAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAxMC8yOS8xMiKqq3kAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAABHklEQVRIib2Vyw6EIAxFW5idr///Qx9sfG3pLEyJ3tAwi5EmBqRo7vHawiEEERHS6x7MTMxMVv6+z3tPMUYSkfTM/R0fEaG2bbMv+Gc4nZzn+dN4HAcREa3r+hi3bcuu68jLskhVIlW073tWaYlQ9+F9IpqmSfq+fwskhdO/AwmUTJXrOuaRQNeRkOd5lq7rXmS5InmERKoER/QMvUAPlZDHcZRhGN4CSeGY+aHMqgcks5RrHv/eeh455x5KrMq2yHQdibDO6ncG/KZWL7M8xDyS1/MIO0NJqdULLS81X6/X6aR0nqBSJcPeZnlZrzN477NKURn2Nus8sjzmEII0TfMiyxUuxphVWjpJkbx0btUnshRihVv70Bv8ItXq6Asoi/ZiCbU6YgAAAABJRU5ErkJggg==);
  }

  .error-template {
    padding: 40px 15px;
    text-align: center;
  }

  .error-actions {
    margin-top: 15px;
    margin-bottom: 15px;
  }
</style>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="error-template">
        <h1><fmt:message key="error.oops"/></h1>
        <h2><fmt:message key="error.404notfound"/></h2>
        <div class="error-details">
          <fmt:message key="error.sorry"/>
        </div>
        <div class="error-actions">

          <a class="nav-link" href="<c:url value="/"/>"><fmt:message
            key="navbar.to_home"/></a>

        </div>
      </div>
    </div>
  </div>
</div>
<c:import url="pages/components/js_import.jsp"/>
</body>
</html>
