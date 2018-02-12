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
        <thead>
        <tr>
          <th scope="col"><fmt:message key="userlist.username"/></th>
          <th scope="col"><fmt:message key="userlist.first_name"/></th>
          <th scope="col"><fmt:message key="userlist.last_name"/></th>
          <th scope="col"><fmt:message key="userlist.cash"/></th>
          <th scope="col"><fmt:message key="userlist.discount"/></th>
          <th scope="col"><fmt:message key="userlist.edit_cash"/></th>
          <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${UserMap}" var="userMap">

          <tr>
            <td><c:out value="${userMap.key.username}"/></td>
            <td><c:out value="${userMap.key.firstName}"/></td>
            <td><c:out value="${userMap.key.lastName}"/></td>
            <td><c:out value="${userMap.key.cash}"/></td>

            <td>
              <c:if test="${sessionScope.USER!=null&&sessionScope.USER.role=='ADMIN'}">
                <c:if test="${userMap.value==false}">
                  <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="button_user"
                          data-target="#editDiscountModal">
                    <span class="oi oi-check"></span>
                  </button>
                </c:if>
                <c:if test="${userMap.value==true}">
                  <a href="<c:url value="/action?name=delete_discount&id=${userMap.key.id}" />">
                    <span class="oi oi-x"></span>
                  </a>
                </c:if>
              </c:if>
            </td>
            <td>
              <c:if test="${sessionScope.USER!=null&&sessionScope.USER.role=='ADMIN'}">
                <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="button_user"
                        data-target="#editUserCash">
                  <span class="oi oi-dollar"></span>
                </button>
              </c:if>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
  </div>

  <div class="row">
    <div class="col-12 ">
      <c:url value="/action?name=user_list&page=##" var="searchUri"/>
      <tags:Paging uri="${searchUri}" currPage="${currentPage}" totalPages="${noOfPages}"/>
    </div>
  </div>

  <div class="modal fade" id="editDiscountModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
       aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="editdiscount.modal_title"/></h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form method="POST" action="action" id="add_discount">
            <input type="hidden" name="name" value="add_discount">

            <div class="form-group">
              <label for="username" class="sr-only">
                <fmt:message key="editdiscount.enter_username"/>
              </label>
              <input type="text" id="username" class="form-control" name="username"
                     placeholder="<fmt:message key="editdiscount.enter_username"/> " required="" autofocus="">
            </div>

            <div class="form-group">
              <label for="percent" class="sr-only">
                <fmt:message key="editdiscount.enter_percent"/>
              </label>
              <input type="text" id="percent" class="form-control" name="percent"
                     placeholder="<fmt:message key="editdiscount.enter_percent"/> " required="" autofocus="">
            </div>

            <div class="form-group">
              <label for="date_from" class="sr-only">
                <fmt:message key="editdiscount.date_begin"/>
              </label>
              <input type="date" id="date_from" class="form-control" name="date_from"
                     placeholder="<fmt:message key="editdiscount.date_begin"/> " required="" autofocus="">
            </div>

            <div class="form-group">
              <label for="date_to" class="sr-only">
                <fmt:message key="editdiscount.date_end"/>
              </label>
              <input type="date" id="date_to" class="form-control" name="date_to"
                     placeholder="<fmt:message key="editdiscount.date_end"/> " required="" autofocus="">
            </div>
            <button onclick="form_submit_discount()" type="submit" class="btn btn-secondary" data-dismiss="modal">
              <fmt:message
                key="editdiscount.add"/></button>
          </form>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="editUserCash" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
       aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabelCash"><fmt:message key="user.add_cash"/></h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form method="POST" action="action" id="add_cash">
            <input type="hidden" name="name" value="add_money">

            <div class="form-group">
              <label for="usernamecash" class="sr-only">
                <fmt:message key="editdiscount.enter_username"/>
              </label>
              <input type="text" id="usernamecash" class="form-control" name="username"
                     placeholder="<fmt:message key="editdiscount.enter_username"/> " required="" autofocus="">
            </div>

            <div class="form-group">
              <label for="cash" class="sr-only">
                <fmt:message key="editdiscount.enter_percent"/>
              </label>
              <input type="text" id="cash" class="form-control" name="cash"
                     placeholder="<fmt:message key="user.add_cash"/> " required="" autofocus="">
            </div>
            <button onclick="form_submit_cash()" type="submit" class="btn btn-secondary" data-dismiss="modal">
              <fmt:message
                key="editdiscount.add"/></button>
          </form>
        </div>
      </div>
    </div>
  </div>


</div>
<script type="text/javascript">
  function form_submit_discount() {
    document.getElementById("add_discount").submit();
  }

  function form_submit_cash() {
    document.getElementById("add_cash").submit();
  }
</script>

