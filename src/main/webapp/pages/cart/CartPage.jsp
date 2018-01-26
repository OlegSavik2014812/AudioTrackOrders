<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<!DOCTYPE html>
<html lang="en">
<head>
  <title>Корзина</title>
  <c:import url="../css_import.jsp"/>
</head>
<body>


<c:import url="../NavBar.jsp"/>

<div class="container-fluid">
  <div class="row">
    <c:import url="Menu.jsp"/>

    <main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">
      <c:import url="CartList.jsp"/>
    </main>

  </div>
</div>
<c:import url="../js_import.jsp"/>
</body>

</html>
