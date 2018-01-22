<%@page import="com.audioord.dao.DAOException" %>
<%@page import="com.audioord.dao.UserDAO" %>
<%@ page import="com.audioord.model.account.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%
  UserDAO userDAO = new UserDAO();
  try {
    User user = userDAO.getByUsername(request.getParameter("userName"));
    if (!user.getUsername().isEmpty()) {
      out.print("Exist");
    } else {
      out.print("non");
    }
  } catch (DAOException e) {
    e.printStackTrace();
  }
%>
