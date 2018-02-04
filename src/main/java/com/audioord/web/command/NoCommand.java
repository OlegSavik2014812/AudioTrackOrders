package com.audioord.web.command;

import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

/**
 * Class describes the object-command, which used as default page
 * implementation of {@link Command}
 */
public final class NoCommand implements Command {
  /**
   * @param request  {@link Request}
   * @param response {@link Response}
   * @return string name of page
   */
  @Override
  public String execute(Request request, Response response) {
    return Pages.INDEX_PAGE;
  }
}
