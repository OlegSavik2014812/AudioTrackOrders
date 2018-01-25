package com.audioord.web.command.user;

import com.audioord.dao.DAOException;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.ROLE;
import com.audioord.model.account.User;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.List;

public class UserListCommand implements Command {
  public static final String NAME = "user_list";
  private static final String PRM_PAGE = "page";
  private UserDAO userDAO = new UserDAO();

  @Override
  public String execute(Request request, Response response) throws IOException, DAOException, ClassNotFoundException {
    boolean check = request.getSessionAttribute("USER", User.class).getRole().equals(ROLE.ADMIN);
    if (!check) {
      return Pages.INDEX_PAGE;
    }
    int page = 1;
    int recordsPerPage = 8;
    if (request.hasParameter(PRM_PAGE)) {
      page = Integer.parseInt(request.getParameter(PRM_PAGE));
    }
    List<User> userList;
    int noOfRecords = userDAO.countAllUsers();
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    userList = userDAO.getAllUsers((page - 1) * recordsPerPage,
    recordsPerPage);

    request.addAttribute("UserList", userList);
    request.addAttribute("noOfPages", noOfPages);
    request.addAttribute("currentPage", page);
    return Pages.USER_LIST;
  }
}
