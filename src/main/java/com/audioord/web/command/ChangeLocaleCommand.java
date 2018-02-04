package com.audioord.web.command;

import com.audioord.dao.DAOException;
import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

import java.io.IOException;

/**
 * Class describes the object-command, which change locale and returns to index page
 * implementation of {@link Command}
 */
public class ChangeLocaleCommand implements Command {

  public static final String NAME = "change_local";
  private static final String PRM_LOCALE = "local";

  /**
   * If the command succeeds, the locale changes and redirection to index page
   *
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   * @throws IOException  in case, when params incorrect
   * @throws DAOException {@link DAOException}
   */
  @Override
  public String execute(Request request, Response response) throws IOException, DAOException {
    String lang = request.getParameter(PRM_LOCALE);
    if (lang == null || lang.isEmpty()) {
      // need to stay on current page in case locale value is not provided
      return Pages.INDEX_PAGE;
    }
    request.setSessionAttribute(PRM_LOCALE, lang);

    return Pages.INDEX_PAGE;
  }
}
