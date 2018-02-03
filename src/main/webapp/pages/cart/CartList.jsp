<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tags.tld" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<div class="container">
  <div class="row">
    <div class="col-12">
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col"><fmt:message key="cart.name"/> </th>
          <th scope="col"><fmt:message key="cart.price"/> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.cart.list}" var="item">
          <tr>
            <td><c:out value="${item.displayName}"/></td>
            <td><fmt:formatNumber value="${item.cost}" type="currency"/></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
