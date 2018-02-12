<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tags.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<div class="container">
  <div class="row">
    <div class="col-12">

      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col"><fmt:message key="orderlist.user"/></th>
          <th scope="col"><fmt:message key="orderlist.date"/></th>
          <th scope="col"><fmt:message key="orderlist.price"/></th>
          <th scope="col"><fmt:message key="orderlist.tracks"/></th>
          <th scope="col"><fmt:message key="orderlist.status"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.orders}" var="item">
          <tr>
            <td><c:out value="${item.user.username}"/></td>
            <td>
              <fmt:formatDate value="${item.dateOrdered}" var="date" type="date" pattern="yyyy-MM-dd"/>
              <c:out value="${date}"/>
            </td>
            <td><fmt:formatNumber value="${item.totalPrice}" type="currency"/></td>
            <td>
              (${fn:length(item.tracks)})
              <a href="#" data-toggle="modal" data-target="#modal${item.id}"><span
                class="oi oi-musical-note"></span></a>

              <div id="modal${item.id}" class="modal fade" tabindex="-1" role="dialog"
                   aria-labelledby="myLargeModalLabel" aria-hidden="true">

                <div class="modal-dialog modal-lg">
                  <div class="modal-content">
                    <ul class="list-group">
                      <c:forEach items="${item.tracks}" var="track">
                        <li class="list-group-item justify-content-between">
                          <c:out value="${track.name}"/> <c:out value="${track.artist}"/> <c:out
                          value="${track.album}"/>
                          <span class="badge badge-default badge-pill"><fmt:formatNumber value="${track.price}"
                                                                                         type="currency"/></span>
                        </li>
                      </c:forEach>
                    </ul>
                  </div>
                </div>
              </div>
            </td>
            <td>

              <c:choose>

                <c:when test="${item.status == 'SUBMITTED'}">
                  <div class="btn-group">
                    <button class="btn btn-secondary btn-sm dropdown-toggle" type="button" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                      <span class="oi oi-box"></span> <fmt:message key="orderlist.submitted"/>
                    </button>
                    <div class="dropdown-menu">
                      <c:url value="/action?name=order_edit&order_id=${item.id}&status=REJECTED" var="urlOrderReject"/>
                      <c:url
                        value="/action?name=order_edit&order_id=${item.id}&status=COMPLETED&username=${item.user.username}"
                        var="urlOrderComplete"/>
                      <a class="dropdown-item" href="${urlOrderReject}"><fmt:message key="orderlist.reject"/> </a>
                      <a class="dropdown-item" href="${urlOrderComplete}"><fmt:message
                        key="orderlist.complete"/> </a>
                    </div>
                  </div>
                </c:when>

                <c:when test="${item.status == 'REJECTED'}">
                  <span class="oi oi-x"></span> <fmt:message key="orderlist.rejected"/>
                </c:when>

                <c:when test="${item.status == 'COMPLETED'}">
                  <span class="oi oi-check"></span> <fmt:message key="orderlist.completed"/>
                </c:when>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>
