<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">

<head>
  <fmt:requestEncoding value="UTF-8"/>
  <fmt:setLocale value="ru"/>
  <fmt:setBundle basename="i18n.MessageBundle_ru"/>


  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="shortcut icon" href="<c:url value="favicon.ico"/>" type="image/x-icon">
  <link rel="icon" href="<c:url value="favicon.ico"/>" type="image/x-icon">

  <title><fmt:message key="index.title"/></title>

  <!-- Bootstrap core CSS -->
  <link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
  <!-- Custom styles for this page -->
  <link href="<c:url value="css/audioord.css"/>" rel="stylesheet" type="text/css">

</head>
<body>
<c:import url="pages/NavBar.jsp"/>
<div class="container-fluid">
  <div class="row">
    <nav class="col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar">
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link active" href="#">Overview <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">x
          <a class="nav-link" href="#"></a>
        </li>
      </ul>

      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link" href="#"><fmt:message key="index.brand_new"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"><fmt:message key="index.most_popular"/></a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#"> <fmt:message key="index.best_selling"/> </a>
        </li>
      </ul>
    </nav>

    <main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">
      <h1>Dashboard</h1>
      <h2><fmt:message key="index.most_popular"/></h2>
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
          <tr>
            <th><fmt:message key="index.name_track"/></th>
            <th><fmt:message key="index.name_artist"/></th>
            <th><fmt:message key="index.name_album"/></th>
            <th><fmt:message key="index.popularity"/></th>
            <th><fmt:message key="index.price"/></th>
            <th><fmt:message key="index.add_to_order"/></th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td>1,001</td>
            <td>Lorem</td>
            <td>ipsum</td>
            <td>dolor</td>
            <td>sit</td>
            <td>
              <input type="checkbox" class="form-check-input" id="checkAdd">
              <label class="form-check-label" for="checkAdd"><fmt:message key="label.add"/> </label>
            </td>
          </tr>
          </tbody>
        </table>
      </div>
    </main>
  </div>
</div>

<%--Bootstrap--%>
<script src="<c:url value="js/jquery-3.1.1.slim.min.js"/>"></script>
<script src="<c:url value="js/tether.min.js"/>"></script>
<script src="<c:url value="js/bootstrap.min.js"/>"></script>
<script src="<c:url value="js/ie10-viewport-bug-workaround.js"/>"></script>
</body>

</html>
