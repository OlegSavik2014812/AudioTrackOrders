package com.audioord.web.command.auth;

import com.audioord.dao.DAOException;
import com.audioord.web.command.Command;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

/**
 * Class describes object-command, execution of which leads to the user's exit from the system
 * and forwarding to the Index page.
 * implementation of {@link Command}
 */
public class SignOutCommand implements Command {

  public static final String NAME = "sign_out";
  private static final String ATTRIBUTE_USER = "USER";
  private static final String REDIRECT_INDEX = "redirect:/audioord";

  /**
   * The user is deleted from the session, and the user forwards to the start page.
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws DAOException, IOException {

    request.invalidateSession();
    request.removeSessionAttribute(ATTRIBUTE_USER);

    return REDIRECT_INDEX;
  }
}
