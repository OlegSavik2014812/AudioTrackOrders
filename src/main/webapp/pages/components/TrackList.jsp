<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="${sessionScope.local}"/>
  <fmt:setBundle basename="i18n.MessageBundle"/>


<h2><fmt:message key="index.most_popular"/></h2>
<div class="table-responsive">
  <table class="table table-striped">
    <thead>
    <tr>
      <th><fmt:message key="index.name_track"/></th>
      <th><fmt:message key="index.name_artist"/></th>
      <th><fmt:message key="index.name_album"/></th>
      <th><fmt:message key="index.popularity"/></th>
      <th><fmt:message key="index.uri"/></th>
      <th><fmt:message key="index.price"/></th>
      <th><fmt:message key="index.duration"/></th>
      <th><fmt:message key="index.add_to_order"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.TrackList}" var="track">
      <tr>
        <td><c:out value="${track.name}"/></td>
        <td><c:out value="${track.artist}"/></td>
        <td><c:out value="${track.album}"/></td>
        <td><c:out value="${track.popularity}"/></td>
        <td><c:out value="${track.uri}"/></td>
        <td><c:out value="${track.price}"/></td>
        <td><c:out value="${track.duration}"/></td>
        <td>
          <c:if test="${sessionScope.USER!=null}">
            <input type="checkbox" class="form-check-input" id="checkAdd">
            <label class="form-check-label" for="checkAdd"><fmt:message key="label.add"/> </label>
          </c:if>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
