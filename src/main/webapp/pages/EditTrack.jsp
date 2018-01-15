<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="${sessionScope.local}"/>
  <fmt:setBundle basename="i18n.MessageBundle"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="index.title"/></title>
  <c:import url="components/css_import.jsp"/>
</head>
<body>
<c:import url="components/NavBar.jsp"/>
<div class="container">
  <form class="form-signin">
    <h2 class="form-signin-heading"><fmt:message key="addtrack.title"/></h2>
    <input type="text" id="trackName" class="form-control" placeholder="<fmt:message key="addtrack.name_track"/>"
           required="" autofocus="">
    <input type="text" id="artistName" class="form-control" placeholder="<fmt:message key="addtrack.name_artist"/>">
    <input type="text" id="albumName" class="form-control" placeholder="<fmt:message key="addtrack.name_album"/>">
    <input type="text" id="URI" class="form-control" placeholder="URI" required="<fmt:message key="addtrack.uri"/>">
    <input type="text" id="trackPrice" class="form-control" placeholder="<fmt:message key="addtrack.price"/>">
    <button class="add_track_btn" type="submit"><fmt:message key="button.add_track"/></button>
  </form>
</div>
<c:import url="components/js_import.jsp"/>
</body>
</html>

