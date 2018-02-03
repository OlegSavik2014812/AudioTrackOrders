<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="feedback.title"/></title>
  <c:import url="../css_import.jsp"/>
</head>
<body>
<c:import url="../NavBar.jsp"/>
<div class="blog-header">
  <div class="container">
    <h1 class="blog-title text-center">${requestScope.track.artist}- ${requestScope.track.name}</h1>
    <p class="lead blog-description text-center">${requestScope.track.album}</p>
  </div>
</div>
<div class="container">
  <div class="row justify-content-center">
    <c:if test="${sessionScope.USER!=null}">
      <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="button_user"
              data-target="#editFeedBackModal">
        <fmt:message key="editfeedback.send_feedback"/>
      </button>
    </c:if>
  </div>
  <hr>
</div>
<div class="container">
  <c:forEach items="${requestScope.Feedbacks}" var="feedback">
    <div class="row justify-content-center">

      <div class="blog-main col-lg-7">

        <div class="blog-post">
          <h2 class="blog-post-title text-center"><c:out value="${feedback.user.username}"/></h2>
          <p class="blog-post-meta text-center"><c:out value="${feedback.createdAt}"/></p>
          <p class="text-center"><c:out value="${feedback.comments}"/></p>
          <c:if test="${sessionScope.USER.role=='ADMIN'}">
            <a href="<c:url value="/action?name=delete_feedback&feedback_id=${feedback.id}"/>">
              <span class="oi oi-action-undo"></span>
            </a>
          </c:if>
        </div>
        <hr/>
      </div>

    </div>
  </c:forEach>

  <div class="modal fade" id="editFeedBackModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
       aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="editfeedback.modal_title"/></h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form method="post" action="action" id="edit_feedback">
            <input type="hidden" name="name" value="edit_feedback">

            <div class="form-group">
              <label for="comment" class="sr-only">

              </label>
              <input type="text" id="comment" class="form-control" name="comment"
                     placeholder="<fmt:message key="editfeedback.write_comment"/> " required
                     pattern=".{0,254}"
                     autofocus="">
            </div>
            <button onclick="form_submit_feedback()" type="submit" class="btn btn-secondary" data-dismiss="modal">
              <fmt:message key="editfeedback.add"/>
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  function form_submit_feedback() {
    document.getElementById("edit_feedback").submit();
  }
</script>
<c:import url="../js_import.jsp"/>
</body>
</html>
