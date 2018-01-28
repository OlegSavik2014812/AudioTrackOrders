<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>

<%--WELCOME PAGE ROUTE TO TRACK LIST or ORDER LIST PAGE--%>
<c:choose>
  <c:when test="${sessionScope.USER == null }">
    <jsp:forward page="/action?name=track_list"/>
  </c:when>
  <c:when test="${sessionScope.USER.role == 'ADMIN' }">
    <jsp:forward page="/action?name=order_list"/>
  </c:when>
  <c:otherwise>
    <jsp:forward page="/action?name=track_list"/>
  </c:otherwise>
</c:choose>
