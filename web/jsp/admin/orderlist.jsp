<%--
  Created by IntelliJ IDEA.
  User: Oleg Savik
  Date: 11.01.2018
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>AudioTracks</title>
</head>
<body>
<jsp:include page="../util/header.jsp"/>
<div id="container">
  <table id="entity_information">
    <thead>
    <tr>
      <th>Client login</th>
      <th>Client id</th>
      <th>Track name</th>
      <th>Track id</th>
      <th>Order price</th>
      <th>Order date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.audiotracks}" var="track">
      <tr bgcolor="#FFFFFF">
        <td><c:out value="${track}"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<jsp:include page="../util/footer.jsp"/>
</body>
</html>
