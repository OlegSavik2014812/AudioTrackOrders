<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="css\for_index.css" rel="stylesheet">
  <title></title>
</head>
<body>
<div id="head">
  <ul id="list_button">
    <li><input type="button" id="sign-in-btn" value="Sign in" onclick="goToSignIn()"></li>
    <li><input type="button" id="sign-up-btn" value="Sign up" onclick="goToSignUpFromIndex()"></li>
  </ul>
</div>

<div id="hello" style="text-align:center;">
  <h1>AUDIOTRACK ORDERS</h1>
</div>
<div id="menu">
  <div id="menuitem" onclick="goToAudioTracksList()">Tracks</div>
  <div id="menuitem">Artists</div>
  <div id="menuitem">Albums</div>
  <div id="menuitem">Packages</div>
</div>
<div id="main">


  <ul>
    <li id="mainitem">
      <div><input type="text" id="audiotrack"></div>
      <div><input type="button" id="dwnld" value="download" onclick="goToOrder()"></div>
    </li>
    <li id="mainitem">
      <div>
        <div><input type="text" id="audiotrack"></div>
        <div><input type="button" id="dwnld" value="download" onclick="goToOrder()"></div>
      </div>
    </li>
    <li id="mainitem">
      <div>
        <div><input type="text" id="audiotrack"></div>
        <div><input type="button" id="dwnld" value="download" onclick="goToOrder()"></div>
      </div>
    </li>
    <li id="mainitem">
      <div>
        <div><input type="text" id="audiotrack"></div>
        <div><input type="button" id="dwnld" value="download" onclick="goToOrder()"></div>
      </div>
    </li>
  </ul>
</div>

<div id="right">
  <h2>About</h2>
  <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.</p>
</div>

<jsp:include page="jsp/util/footer.jsp"/>

<script src="js\indexredirect.js"></script>

</body>
</html>
