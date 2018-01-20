<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="${sessionScope.local}"/>
  <fmt:setBundle basename="i18n.MessageBundle"/>


<div>
  <c:if test="${requestScope.Name=='most_popular'}">
    <h2><fmt:message key="index.most_popular"/></h2>
  </c:if>
  <c:if test="${requestScope.Name=='brand_new'}">
    <h2><fmt:message key="index.brand_new"/></h2>
  </c:if>
  <c:if test="${requestScope.Name=='best_selling'}">
    <h2><fmt:message key="index.best_selling"/></h2>
  </c:if>

</div>
<div class="table-responsive">
  <div class="table table-striped">
    <form method="post" action="action">
      <input type="hidden" name="name" value="make_order">
      <table>
        <thead>
        <tr>
          <th><fmt:message key="index.name_track"/></th>
          <th><fmt:message key="index.name_artist"/></th>
          <th><fmt:message key="index.name_album"/></th>
          <th><fmt:message key="index.popularity"/></th>
          <th><fmt:message key="index.uri"/></th>
          <th><fmt:message key="index.price"/></th>
          <th><fmt:message key="index.duration"/></th>
          <c:if test="${sessionScope.USER!=null&&sessionScope.USER.role=='CLIENT'}">
            <th><input type="submit" value="<fmt:message key="index.add_to_order"/>"></th>
          </c:if>
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
              <c:if test="${sessionScope.USER!=null&&sessionScope.USER.role=='CLIENT'}">
                <input type="checkbox" class="form-check-input" id="checkAdd" name="check" value="${track.name}">
                <label class="form-check-label" for="checkAdd"><fmt:message key="label.add"/> </label>
              </c:if>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </form>
  </div>
  <%--For displaying Previous link except for the 1st page --%>
  <%--For displaying Previous link except for the 1st page --%>

  <c:if test="${currentPage != 1}">
    <a href="/action?name=track_list&filter=most_popular?page=${currentPage - 1}">Previous</a>
  </c:if>
  <%--/action?name=track_list&filter=brand_new--%>
  <%--For displaying Page numbers.
  The when condition does not display a link for the current page--%>
  <table border="1" cellpadding="5" cellspacing="5">
    <tr>
      <c:forEach begin="1" end="${noOfPages}" var="i">
        <c:choose>
          <c:when test="${currentPage eq i}">
            <td>${i}</td>
          </c:when>
          <c:otherwise>
            <td><a href="/action?name=track_list&filter=most_popular?page=${i}">${i}</a></td>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </tr>
  </table>

  <ul class="pagination">
    <c:if test="${currentPage != 1}">
      <li class="page-item">
        <a href="/action?name=track_list&filter=most_popular?page=${currentPage - 1}">Previous</a>
      </li>
    </c:if>
    <c:forEach begin="1" end="${noOfPages}" var="i">
      <c:choose>
        <c:when test="${currentPage eq i}">
          <li class="page-item">
              ${i}
          </li>
        </c:when>
        <c:otherwise>
          <li class="page-item"><a href="/action?name=track_list&filter=most_popular?page=${i}">${i}</a></li>
        </c:otherwise>
      </c:choose>
    </c:forEach>
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
      <li class="page-item"><a href="employee.do?page=${currentPage + 1}">Next</a></li>
    </c:if>
  </ul>
</div>
