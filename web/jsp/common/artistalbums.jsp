<%--
  Created by IntelliJ IDEA.
  User: Oleg Savik
  Date: 11.01.2018
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="controller" method="get" target="_self">
        <input type="search" value="Enter artist name">
        <input type="submit" value="Search">
    </form>
    <table>
        <thead>
        <tr>
            <th>name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="">
            <tr>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
