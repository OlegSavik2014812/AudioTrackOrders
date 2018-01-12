package com.audioord.web.http;

import javax.servlet.http.HttpServletResponse;

public class Response {

  private HttpServletResponse rawResponse;

  public Response(HttpServletResponse rawResponse) {
    this.rawResponse = rawResponse;
  }

  public HttpServletResponse raw() {
    return rawResponse;
  }
}
