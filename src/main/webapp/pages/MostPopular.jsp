<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="ru"/>
<fmt:setBundle basename="i18n.MessageBundle_ru"/>
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
