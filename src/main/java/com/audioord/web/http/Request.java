package com.audioord.web.http;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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

  public boolean hasAllParameters(String... params) {
    for (String param : params) {
      if (hasParameter(param)) {
        continue;
      }
      return false;
    }
    return true;
  }

  public void addAttribute(String name, Object v) {
    Objects.requireNonNull(v, "NULL attribute not allowed");
    rawRequest.setAttribute(name, v);
  }

  public String getParameter(String paramName) {
    return rawRequest.getParameter(paramName);
  }

  public void setSessionAttribute(String name, Object v) {
    rawRequest.getSession().setAttribute(name, v);
  }

  public <T> T getSessionAttribute(String name, Class<T> type) {
    return type.cast(rawRequest.getSession().getAttribute(name));
  }

  public void removeSessionAttribute(String name) {
    rawRequest.getSession().removeAttribute(name);
  }

  public void invalidateSession() {
    rawRequest.getSession().invalidate();
  }
}
