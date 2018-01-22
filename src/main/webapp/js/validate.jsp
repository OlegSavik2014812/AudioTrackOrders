<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<fmt:message key="pattern.username" var="username"/>
<fmt:message key="pattern.firstname" var="firstname"/>


<script>
  var username = document.getElementById('username');
  var firstName = document.getElementById('inputFirstName');
  var lastName = document.getElementById('inputLastName');
  username.oninvalid = function (event) {
    event.target.setCustomValidity("${username}");
  }
  firstName.oninvalid = function (event) {
    event.target.setCustomValidity("${firstname}");
  }
  lastName.oninvalid = function (event) {
    event.target.setCustomValidity("${firstname}");
  }
</script>
