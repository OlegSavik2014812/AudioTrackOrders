package com.audioord.web.command;

import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

public final class NoCommand implements Command {

  @Override
  public String execute(Request request, Response response) {
    // use home page as default
    return Pages.INDEX_PAGE ;
  }
}
