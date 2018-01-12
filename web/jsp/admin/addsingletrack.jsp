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
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Page Title</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" media="screen" href="../../css/for_add.css"/>

</head>
<body>
<jsp:include page="../util/header.jsp"/>
<div id="head">
  <input type="button" value="To main" id="to_main" onclick="goToIndexPage()">
</div>
<div id="header"><h2>Addition single track</h2></div>
<div id="add_single_track">
  <form action="post">
    <input type="text" placeholder="Enter name of artist">
    <input type="text" placeholder="Enter name of track">
    <input type="text" placeholder="Enter price of track">
    <input type="button" value="Add" id="add">
  </form>
</div>
<jsp:include page="../util/footer.jsp"/>
</body>
</html>
