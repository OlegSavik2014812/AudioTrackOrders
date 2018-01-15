<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <c:import url="pages/components/Meta.jsp"/>
</head>
<body>
<c:import url="pages/components/NavBar.jsp"/>
<div class="container-fluid">
  <div class="row">
    <c:import url="pages/components/Menu.jsp"/>
    <main role="main" class="col-sm-9 ml-sm-auto col-md-10 pt-3">
      <c:import url="pages/TrackList.jsp"/>
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
