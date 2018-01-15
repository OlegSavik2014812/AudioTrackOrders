<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>

<link rel="shortcut icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon">
<link rel="icon" href="<c:url value="/favicon.ico"/>" type="image/x-icon">

<title><fmt:message key="index.title"/></title>

<!-- Bootstrap core CSS -->
<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
<!-- Custom styles for this page -->
<link href="<c:url value="/css/audioord.css"/>" rel="stylesheet" type="text/css">
