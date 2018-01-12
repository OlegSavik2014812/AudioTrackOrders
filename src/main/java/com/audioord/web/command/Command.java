package com.audioord.web.command;

import com.audioord.web.http.Request;
import com.audioord.web.http.Response;

public interface Command {

  String execute(Request request, Response response);
}
