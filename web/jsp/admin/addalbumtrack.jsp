<%--
  Created by IntelliJ IDEA.
  User: Oleg Savik
  Date: 10.01.2018
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" media="screen" href="../../css/for_add.css"/>
  <title>Title</title>
</head>
<body>
<jsp:include page="../util/header.jsp"/>
<div id="head">
  <input type="button" value="To main" id="to_main" onclick="goToIndexPage()">
</div>

<div id="header"><h2>Addition album track</h2></div>
<div id="add_album_track">
  <form action="post">
    <input type="text" placeholder="Enter name of artist">
    <input type="text" placeholder="Enter name of album">
    <input type="text" placeholder="Enter name of track">
    <input type="text" placeholder="Enter price of track">
    <input type="button" value="Add" id="add">
  </form>
</div>
<jsp:include page="../util/footer.jsp"/>
<script src="../../script_redirect.js"></script>
</body>
</html>
