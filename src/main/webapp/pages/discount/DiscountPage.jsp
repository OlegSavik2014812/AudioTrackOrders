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

  <c:import url="../components/css_import.jsp"/>
  <link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">

</head>

<body><c:import url="../components/NavBar.jsp"/>

<div class="container">

  <div class="row">

    <form class="" method="POST" action="../action?name=add_discount">
      <input type="hidden" name="name" value="add_track">

      <h2 class="form-signin-heading"><fmt:message key="add.discount.addition_discount"/></h2>

      <div class="form-group">
        <label for="percent" class="sr-only">
          <fmt:message key="add.discount.percent"/>
        </label>
        <input type="text" id="percent" name="percent" class="form-control"
               placeholder="<fmt:message key="add.discount.enter_percent"/> " required="" autofocus="">
      </div>

      <div class="form-group">
        <label for="effectiveFrom" class="sr-only">
          <fmt:message key="add.discount.date_begin"/>
        </label>
        <input type="date"  id="effectiveFrom" name="effectiveFrom" class="form-control"
               placeholder="<fmt:message key="add.discount.enter_date"/>" required="">
      </div>

      <div class="form-group">
        <label for="effectiveTo" class="sr-only">
          <fmt:message key="add.discount.date_end"/>
        </label>
        <input type="date" id="effectiveTo" name="effectiveTo" class="form-control"
               placeholder="<fmt:message key="add.discount.enter_date"/>">
      </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit" data-toggle="modal"
              data-target=".bd-example-modal-sm">
        <fmt:message key="add.discount.add"/>
      </button>

    </form>

  </div>

</div>

<div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <fmt:message key="info.add_track_success"/>
    </div>
  </div>
</div>
<c:import url="../components/js_import.jsp"/>

</body>

</html>
