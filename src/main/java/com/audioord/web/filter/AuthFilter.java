package com.audioord.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 */
public class AuthFilter implements Filter {
  private static final String ATTRIBUTE_USER = "USER";
  private String signInPath = "";
  private String signUpPath = "";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  /**
   * @param req  {@link ServletRequest}
   * @param res  {@link ServletResponse}
   * @param next {@link FilterChain}
   * @throws IOException      in case, when params incorrect
   * @throws ServletException in case, servlet encounters difficulty.
   */
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain next) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse response = (HttpServletResponse) res;
    HttpSession session = request.getSession(false);
    // user already logged in
    boolean loggedIn = session != null && session.getAttribute(ATTRIBUTE_USER) != null;

    next.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}
