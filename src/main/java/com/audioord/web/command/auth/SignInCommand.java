package com.audioord.web.command.auth;

import com.audioord.dao.AuthInfoDao;
import com.audioord.dao.DAOException;
import com.audioord.dao.OrderDiscountDAO;
import com.audioord.dao.UserDAO;
import com.audioord.model.account.AuthInfo;
import com.audioord.model.account.User;
import com.audioord.model.order.OrderDiscount;
import com.audioord.web.command.Command;
import com.audioord.web.command.Pages;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Class describes the object-command, the execution of which sign in user in system,
 * implementation of {@link Command}
 */
public class SignInCommand implements Command {

  public static final String NAME = "sign_in";
  private static final String PRM_USERNAME = "userName";
  private static final String PRM_PASSWORD = "password";
  private static final String ATTRIBUTE_USER = "USER";
  private static final String ATTRIBUTE_BONUS = "BONUS";

  private final AuthInfoDao authInfoDao = new AuthInfoDao();
  private final UserDAO userDAO = new UserDAO();
  private final OrderDiscountDAO orderDiscountDAO = new OrderDiscountDAO();

  /**
   * if the command succeeds, the page goes to the page
   * the input parameters are checked - the username and password received from the request
   * <p>
   * if the parameters are checked, then the variables are initialized
   * the parameters are passed to the method {@link AuthInfoDao#getById(Serializable)} and
   * the {@link AuthInfo} object is initialized
   * the {@link User} object is initialized, using {@link UserDAO#getByUsername(String)} with input param
   * {@link AuthInfo#getUserName()}
   * after that params of {@link User} object passed to session
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    if (!request.hasAllParameters(PRM_USERNAME, PRM_PASSWORD)) {
      return Pages.SIGN_IN_PAGE;
    }

    String userName = request.getParameter(PRM_USERNAME);
    String password = request.getParameter(PRM_PASSWORD);
    String encryptedPassword = DigestUtils.md5Hex(password);

    if (userName.isEmpty() || password.isEmpty()) { // required fields
      return Pages.SIGN_IN_PAGE;
    }

    AuthInfo authInfo = authInfoDao.getById(userName);
    if (authInfo == null) { // unknown user
      return Pages.SIGN_UP_PAGE;
    }

    if (!authInfo.getPassword().equals(encryptedPassword)) { // wrong password
      return Pages.SIGN_IN_PAGE;
    }

    User user = userDAO.getByUsername(authInfo.getUserName());
    if (user == null) {
      return Pages.SIGN_UP_PAGE;
    }

    OrderDiscount orderDiscount = orderDiscountDAO.getByUserId(user.getId());
    request.setSessionAttribute(ATTRIBUTE_USER, user);
    request.setSessionAttribute(ATTRIBUTE_BONUS, orderDiscount);
    return Pages.INDEX_PAGE;
  }
}
