<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tags.tld" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<div class="container">
  <div class="row">
    <div class="col-4">
      <form action="action" method="post">
        <input type="hidden" name="name" value="make_order">
        <nav>
          <ul class="pagination pagination-sm">
            <c:url value="/action?name=track_list&sort=${sort}&page=##" var="searchUri"/>
            <tags:Paging uri="${searchUri}" currPage="${currentPage}" totalPages="${noOfPages}"/>
          </ul>
        </nav>
      </form>
    </div>
  </div>
  <div class="row">
    <div class="col-12">
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col"><fmt:message key="index.name_track"/></th>
          <th scope="col"><fmt:message key="index.name_artist"/></th>
          <th scope="col"><fmt:message key="index.name_album"/></th>
          <th scope="col"><fmt:message key="index.popularity"/></th>
          <th scope="col"><fmt:message key="index.uri"/></th>
          <th scope="col"><fmt:message key="index.price"/></th>
          <th scope="col"><fmt:message key="index.duration"/></th>

          <c:if test="${sessionScope.USER!=null&&sessionScope.USER.role=='CLIENT'}">
            <th scope="col"><input type="submit" value="<fmt:message key="index.add_to_order"/>"></th>
          </c:if>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${TrackList}" var="track">
          <tr>
            <td><c:out value="${track.name}"/></td>
            <td><c:out value="${track.artist}"/></td>
            <td><c:out value="${track.album}"/></td>
            <td><c:out value="${track.popularity}"/></td>
            <td><c:out value="${track.uri}"/></td>
            <td><c:out value="${track.price}"/></td>
            <td><c:out value="${track.duration}"/></td>

            <c:if test="${sessionScope.USER!=null&&sessionScope.USER.role=='CLIENT'}">
              <td>
                <input type="checkbox" class="form-check-input" id="checkAdd" name="check" value="${track.name}">
                <label class="form-check-label" for="checkAdd"><fmt:message key="label.add"/> </label>
              </td>
            </c:if>

          </tr>
        </c:forEach>
        </tbody>
      </table>
      </form>
    </div>
  </div>
</div>
