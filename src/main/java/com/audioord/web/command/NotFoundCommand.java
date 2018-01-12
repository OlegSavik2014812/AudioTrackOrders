package com.audioord.web.command;

import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

public final class NotFoundCommand implements Command {

  public static final String NAME = "not_found_command";

  @Override
  public String execute(Request request, Response response) {
    return "/404.jsp";
  }
}
