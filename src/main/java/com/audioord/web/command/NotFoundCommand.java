package com.audioord.web.command;

import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

/**
 * Class describes the object-command, which used as 404 error page
 * implementation of {@link Command}
 */
public final class NotFoundCommand implements Command {

  public static final String NAME = "not_found_command";

  /**
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   */
  @Override
  public String execute(Request request, Response response) {
    return Pages.NOT_FOUND_PAGE;
  }
}
