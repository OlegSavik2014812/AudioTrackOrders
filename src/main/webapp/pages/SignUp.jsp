<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<!DOCTYPE html>
<html lang="en">
<head>
  <title><fmt:message key="index.title"/></title>
  <c:import url="components/css_import.jsp"/>
  <link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">
</head>

<body>
<c:import url="components/NavBar.jsp"/>

<div class="container">
  <div class="row">
    <form class="form-signin" method="POST" action="action" name="sign_up_form">
      <input type="hidden" name="name" value="sign_up">


      <h2 class="form-signin-heading"><fmt:message key="signup.please_signup"/></h2>

      <div class="form-group">
        <label for="username" onblur="checkExist()" class="sr-only"><fmt:message
          key="signin.email_address_msg"/> </label><span id="isE">Checl</span>
        <input type="text" name="userName" id="username" class="form-control"
               placeholder="<fmt:message key="signin.email_address_msg"/> " required pattern="" autofocus="">
      </div>

      <div class="form-group">
        <label for="inputPassword1" class="sr-only"><fmt:message key="signin.password"/> </label>
        <input type="password" id="inputPassword1" name="password1" class="form-control"
               placeholder="<fmt:message key="signin.password"/>"
               required="">
      </div>

      <div class="form-group">
        <label for="inputPassword2" class="sr-only"><fmt:message key="signin.password"/> </label>
        <input type="password" id="inputPassword2" name="password2" class="form-control"
               placeholder="<fmt:message key="signin.password"/>"
               required="">
      </div>

      <div class="form-group">
        <label for="inputFirstName" class="sr-only"><fmt:message key="signup.firstname"/> </label>
        <input type="text" id="inputFirstName" name="firstName" class="form-control"
               placeholder="<fmt:message key="signup.firstname"/> ">
      </div>

      <div class="form-group">
        <label for="inputLastName" class="sr-only"><fmt:message key="signup.lastname"/> </label>
        <input type="text" id="inputLastName" name="lastName" class="form-control"
               placeholder="<fmt:message key="signup.lastname"/> ">
      </div>

      <button class="btn btn-lg btn-primary btn-block" id="submitButton" type="submit"><fmt:message
        key="button.signup"/></button>
    </form>
  </div>
</div>
<script>
  function checkExist() {
    var xmlhttp = new XMLHttpRequest();
    var username = document.forms["sign_up_form"]["userName"].value;
    console.log(username);
    var url = "exist.jsp?userName" + username;
    console.log(url);
    xmlhttp.onreadystatechange = function () {
      if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
        if (xmlhttp.responseText === "Exists")
          document.getElementById("isE").style.color = "red";
        else
          document.getElementById("isE").style.color = "green";
        document.getElementById("isE").innerHTML = xmlhttp.responseText;
      }

    };
    try {
      xmlhttp.open("GET", url, true);
      xmlhttp.send();
    } catch (e) {
      alert("unable to connect to server");
    }
  }
</script>
<c:import url="components/js_import.jsp"/>
</body>
</html>
