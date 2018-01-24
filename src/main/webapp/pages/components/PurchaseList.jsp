<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tags.tld" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>
<div class="container">
  <div class="row mt-4">
    <div class="col-12">
      <c:url value="/action?name=purchases_list&page=##" var="searchUri"/>
      <tags:Paging uri="${searchUri}" currPage="${currentPage}" totalPages="${noOfPages}"/>
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col"><fmt:message key="purchase.list.total_price"/></th>
          <th scope="col"><fmt:message key="purchase.list.status"/></th>
          <th scope="col"><fmt:message key="purchase.list.username"/></th>
          <th scope="col"><fmt:message key="purchase.list.date"/></th>
        </thead>
        <tbody>
        <c:forEach items="${PurchaseList}" var="purchase">
          <tr>
            <td><c:out value="${purchase.totalPrice}"/></td>
            <td><c:out value="${purchase.status}"/></td>
            <td><c:out value="${purchase.user.username}"/></td>
            <td><c:out value="${purchase.dateOrdered}"/></td>
            <td>
              <a href="<c:url value="/action?name=submit_order&id_order=${purchase.id} "/>">
                <span class="oi oi-circle-check"></span>
              </a>
              <a href="<c:url value="/action?name=reject_order&id_order=${purchase.id} "/>">
                <span class="oi oi-circle-x"></span>
              </a>
            </td>

          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

</div>
