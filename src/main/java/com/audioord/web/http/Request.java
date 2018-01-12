package com.audioord.web.http;

import javax.servlet.http.HttpServletRequest;

public class Request {

  private HttpServletRequest rawRequest;

  public Request(HttpServletRequest rawRequest) {
    this.rawRequest = rawRequest;
  }

  public HttpServletRequest raw() {
    return rawRequest;
  }

  public boolean hasParameter(String paramName) {
    String value = rawRequest.getParameter(paramName);
    return value != null && !value.isEmpty();
  }

  public String getParameter(String paramName) {
    return rawRequest.getParameter(paramName);
  }
}
