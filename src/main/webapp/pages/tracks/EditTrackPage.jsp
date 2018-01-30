<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>
    <fmt:message key="index.title"/>
  </title>
  <c:import url="../css_import.jsp"/>
  <link href="<c:url value="/css/fileinput.css"/>" rel="stylesheet" type="text/css">

</head>

<body>
<c:import url="../NavBar.jsp"/>

<div class="container">
  <div class="py-5 text-center">
    <span class="oi oi-musical-note" style="color: #5cb3fd; font-size: x-large;"></span>
    <h2>Добавить песню</h2>
  </div>

  <div class="row">

    <%--<form class="needs-validation" novalidate="">--%>
    <div class="col-md-4 order-md-2 mb-4">

      <div class="card">

        <img class="card-img-top" src="<c:url value="/img/cd-cover.jpg"/>" height="356">
      </div>
    </div>

    <div class="col-md-8 order-md-1 ">
      <%--Track Upload Form action--%>
      <c:if test="${requestScope.track == null}">
        <c:url value="/action?name=upload_track" var="uploadUrl"/>
        <form action="${uploadUrl}" method="POST" enctype="multipart/form-data">
          <div class="mb-3">
            <label class="custom-file" id="customFile">
              <input type="file" class="custom-file-input" id="file" name="file" aria-describedby="fileHelp" required>
              <span class="custom-file-control form-control-file"></span>
            </label>
          </div>
          <hr class="mb-4"/>
          <button class="btn btn-primary btn-lg btn-block" type="submit">Загрузить</button>
        </form>
      </c:if>

      <%--Track  Edit & Save action--%>
      <c:if test="${requestScope.track != null}">
        <c:url value="/action?name=add_track" var="addUrl"/>
        <form action="${addUrl}" method="POST">

          <div class="mb-3">
            <label for="name">Track name</label>
            <div class="input-group">
              <input type="text" class="form-control" id="name" name="name" value="${requestScope.track.artist}">
                <%--<div class="invalid-feedback" style="width: 100%;">--%>
                <%--Your username is required.--%>
                <%--</div>--%>
            </div>
          </div>

          <div class=" row">
            <div class="col-md-6 mb-3">
              <label for="artist">Artist</label>
              <input type="text" class="form-control" id="artist" name="artist" value="${requestScope.track.artist}">
                <%--<div class="invalid-feedback">--%>
                <%--Valid first name is required.--%>
                <%--</div>--%>
            </div>
            <div class="col-md-6 mb-3">
              <label for="album">Album</label>
              <input type="text" class="form-control" id="album" name="album" value="${requestScope.track.album}">
                <%--<div class="invalid-feedback">--%>
                <%--Valid last name is required.--%>
                <%--</div>--%>
            </div>
          </div>

          <div class="mb-3">
            <label for="price">Price</label>
            <div class="input-group">
              <input type="number" class="form-control" id="price" name="price" required>
                <%--<div class="invalid-feedback" style="width: 100%;">--%>
                <%--Your username is required.--%>
                <%--</div>--%>
            </div>
          </div>

          <hr class="mb-4"/>
          <button class="btn btn-primary btn-lg btn-block" type="submit">Добавить</button>
        </form>
      </c:if>
    </div>
  </div>
</div>

<c:import url="../js_import.jsp"/>
</body>

</html>