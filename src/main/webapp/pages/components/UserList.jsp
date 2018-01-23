<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="tags" uri="/WEB-INF/tags.tld" %>

<fmt:requestEncoding value="UTF-8"/>
<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="i18n.MessageBundle"/>


<div class="container">

  <div class="row mt-4">
    <div class="col-12">
      <table class="table table-striped">

      </table>

    </div>
  </div>


  <div class="row">
    <div class="col-12 ">

      <%--<c:url value="/action?name=track_list&sort=${sort}&page=##" var="searchUri"/>--%>
      <%--<tags:Paging uri="${searchUri}" currPage="${currentPage}" totalPages="${noOfPages}"/>--%>

    </div>
  </div>

</div>
