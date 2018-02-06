<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="tracklist.title"/></title>
  <c:import url="../css_import.jsp"/>
</head>
<body>

<c:import url="../NavBar.jsp"/>

<div class="container-fluid ">

  <div class="row">
    <div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner" role="listbox">
        <div class="carousel-item active">
          <img class="d-block img-fluid" src="<c:url value="/img/slide1.jpg"/>">
        </div>
        <div class="carousel-item">
          <img class="d-block img-fluid" src="<c:url value="/img/slide2.jpg"/>">
        </div>
        <div class="carousel-item">
          <img class="d-block img-fluid" src="<c:url value="/img/slide3.jpg"/>">
        </div>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="container">
      <div class="col-12">
        <table class="table table-hover">
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col"><fmt:message key="tracklist.track_name"/></th>
            <th scope="col"><fmt:message key="tracklist.artist_name"/></th>
            <th scope="col"></th>
            <th scope="col"></th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${tracks}" var="track" varStatus="loop">
            <tr>
              <td>${loop.index + 1}</td>
              <td><c:out value="${track.name}"/></td>
              <td><c:out value="${track.artist}"/></td>
              <td>
                <jsp:useBean id="dateValue" class="java.util.Date"/>
                <jsp:setProperty name="dateValue" property="time" value="${track.duration}"/>
                <fmt:formatDate value="${dateValue}" pattern="mm:ss"/>
              </td>
              <td>
                <a href="<c:url value="/file/${track.uri}"/>">
                  <span class="oi oi-data-transfer-download"></span>
                </a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<c:import url="../js_import.jsp"/>
</body>
</html>
