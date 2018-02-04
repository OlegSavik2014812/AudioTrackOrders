package com.audioord.web.http;

import javax.servlet.http.HttpServletResponse;

/**
 * Class describes object, which wrap {@link HttpServletResponse}
 */
public class Response {

  private HttpServletResponse rawResponse;

  /**
   * constructor
   *
   * @param rawResponse {@link HttpServletResponse}
   */
  public Response(HttpServletResponse rawResponse) {
    this.rawResponse = rawResponse;
  }

  /**
   * @return {@link HttpServletResponse}
   */
  public HttpServletResponse raw() {
    return rawResponse;
  }
}
