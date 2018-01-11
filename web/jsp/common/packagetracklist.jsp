<%--
  Created by IntelliJ IDEA.
  User: Oleg Savik
  Date: 11.01.2018
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="../../css/for_list.css" rel="stylesheet">
    <title>AudioTracks</title>

<body>
<jsp:include page="../util/header.jsp"/>

<div id="container">
    <div id="search">
        <form action="controller" method="get">
            <select id="search_select" onchange="window.location.href=this.value">
                <option value="packagetracklist.jsp">Package name</option>
                <option value="artistaudio.jsp">Artist name</option>
                <option value="audiotracklist.jsp">AudioTrack name</option>
            </select>
            <input type="search" id="search_input">
            <input type="submit" value="Search" id="search-btn">
        </form>
    </div>
    <table id="entity_information">
        <thead>
        <tr>
            <th>Name</th>
            <th>Artist</th>
            <th>Price</th>
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
