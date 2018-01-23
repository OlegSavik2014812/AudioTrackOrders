<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tags.tld" %>

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
    <div class="col-4">
      <c:url value="/action?name=user_list&page=##" var="searchUri"/>
      <tags:Paging uri="${searchUri}" currPage="${currentPage}" totalPages="${noOfPages}"/>
    </div>
  </div>
  <div class="row">
    <div class="col-12">
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col"><fmt:message key="userlist.username"/></th>
          <th scope="col"><fmt:message key="userlist.firstname"/></th>
          <th scope="col"><fmt:message key="userlist.lastname"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${UserList}" var="user">
          <tr>

            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <c:if test="${sessionScope.USER.role == 'ADMIN'}">
              <td><a class="nav-link" href="action?name=order_list&user=${user.username}">
                <fmt:message key="userlist.show_orders"/>
              </a></td>
            </c:if>
          </tr>


        </c:forEach>

        </tbody>
      </table>
    </div>
  </div>
</div>
<c:import url="components/js_import.jsp"/>
</body>
</html>
