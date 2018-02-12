package com.audioord.web.command.user;

import com.audioord.dao.DAOException;
import com.audioord.dao.OrderDiscountDAO;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.User;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class describes the object-command, which shows all registered users and returns to user list page
 * implementation of {@link Command}
 */
public class UserListCommand implements Command {

  public static final String NAME = "user_list";
  private static final String PRM_PAGE = "page";
  private static final String ATTRIBUTE_USER_MAP = "UserMap";
  private static final String ATTRIBUTE_NUMBER_OF_PAGES = "noOfPages";
  private static final String ATTRIBUTE_CURRENT_PAGE = "currentPage";

  private final UserDAO userDAO = new UserDAO();
  private final OrderDiscountDAO orderDiscountDAO = new OrderDiscountDAO();

  /**
   * The input parameters for paging are checked if they are not valid. then the default value is initialized
   * then the object list is created
   * * if command executed successfully, then creation of list of {@link User} objects
   * and redirection to user list page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {
    int page = 1;
    int recordsPerPage = 8;

    if (request.hasParameter(PRM_PAGE)) {
      if (validateRequestPage(request.getParameter(PRM_PAGE))) {
        page = Integer.parseInt(request.getParameter(PRM_PAGE));
      } else {
        page = 1;
      }
    }
    List<User> userList;
    int noOfRecords = userDAO.countAllUsers();
    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
    page = validatePageValue(page, noOfPages);
    userList = userDAO.getAllUsers((page - 1) * recordsPerPage, recordsPerPage);
    if (userList == null || userList.isEmpty()) {
      return Pages.INDEX_PAGE;
    }
    Map<User, Boolean> userMap = new HashMap<>();
    for (User user : userList) {
      if (orderDiscountDAO.getByUserId(user.getId()) != null) {
        userMap.put(user, true);
      } else {
        userMap.put(user, false);
      }


    }
    request.addAttribute(ATTRIBUTE_USER_MAP, userMap);
    request.addAttribute(ATTRIBUTE_NUMBER_OF_PAGES, noOfPages);
    request.addAttribute(ATTRIBUTE_CURRENT_PAGE, page);
    return Pages.USER_LIST_PAGE;
  }

  private int validatePageValue(int page, int noOfPages) {
    if (page > noOfPages) {
      return noOfPages;
    }
    if (page < 0) {
      return 1;
    } else {
      return page;
    }
  }

  private boolean validateRequestPage(String page) {
    Pattern pattern = Pattern.compile("^\\d+$");
    Matcher matcher = pattern.matcher(page);
    return matcher.find();
  }
}
