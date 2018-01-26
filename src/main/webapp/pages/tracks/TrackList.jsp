<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tags.tld" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<div class="container">

  <div class="row">
    <div class="col-4 ml-auto">
      <form method="GET" action="action">
        <input type="hidden" name="name" value="search_track"/>
        <div class="input-group">
          <input type="text" name="track_name" class="form-control form-control-sm" aria-describedby="sizing-addon1"
                 required
                 placeholder="<fmt:message key="search.enter_track_name"/>"/>
          <span class="input-group-btn" id="sizing-addon1">
            <button class="btn btn-sm btn-secondary" type="submit"><fmt:message key="button.search"/></button>
          </span>
        </div>
      </form>
    </div>
  </div>

  <div class="row mt-4">
    <div class="col-12">
      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col"><fmt:message key="index.name_track"/></th>
          <th scope="col"><fmt:message key="index.name_artist"/></th>
          <th scope="col"><fmt:message key="index.name_album"/></th>
          <th scope="col"><fmt:message key="index.popularity"/></th>
          <th scope="col"><fmt:message key="index.duration"/></th>
          <th scope="col"><fmt:message key="index.price"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${TrackList}" var="track">
          <tr>
            <td><c:out value="${track.name}"/></td>
            <td><c:out value="${track.artist}"/></td>
            <td><c:out value="${track.album}"/></td>
            <td><c:out value="${track.popularity}"/></td>
            <td>
                ${track.duration}
            </td>
            <td>
              <fmt:formatNumber value="${track.price}" type="currency"/>
              <c:if test="${sessionScope.USER!=null&&sessionScope.USER.role=='CLIENT'}">
                <c:url value="/action?name=add_cart&track_id=${track.id}" var="addUri"/>
                <a href="#" onclick="executeTrackAdd('${addUri}');">
                  <span class="oi oi-cart"></span>
                </a>
              </c:if>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <div class="row">
    <div class="col-12 ">
      <c:url value="/action?name=track_list&sort=${sort}&page=##" var="searchUri"/>
      <tags:Paging uri="${searchUri}" currPage="${currentPage}" totalPages="${noOfPages}"/>
    </div>
  </div>


  <div id="addModal" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog"
       aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-body">
          <fmt:message key="addtrack.cart"/>
        </div>
      </div>
    </div>
  </div>

</div>
