package com.audioord.web.filter;

import com.audioord.model.account.User;
import com.audioord.web.command.Pages;
import com.audioord.web.security.CommandSecurity;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class describes the filter, which checks whether the client can execute a specific command or not
 */
public class SecurityFilter implements Filter {
  private static final String ATTRIBUTE_USER = "USER";
  private static final String PRM_COMMAND_NAME = "name";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  /**
   * There is checking command name and user status
   * if user has rights to execute command, then execute next filter, otherwise it redirect to forbidden error page
   *
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
    User user = (User) request.getSession().getAttribute(ATTRIBUTE_USER);

    String commandName = request.getParameter(PRM_COMMAND_NAME);
    if (commandName == null && commandName.isEmpty()) {
      forwardForbiddenError(request, response);
    }

    if (user == null) {
      if (!CommandSecurity.isAllowedToGuest(commandName)) {
        forwardForbiddenError(request, response);
      }
    } else {
      switch (user.getRole()) {
        case ADMIN: {
          if (!CommandSecurity.isAllowedToAdmin(commandName)) {
            forwardForbiddenError(request, response);
          }
          break;
        }

        case CLIENT: {
          if (!CommandSecurity.isAllowedToClient(commandName)) {
            forwardForbiddenError(request, response);
          }
          break;
        }
      }
    }

    next.doFilter(request, response);
  }


  @Override
  public void destroy() {

  }

  private void forwardForbiddenError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher(Pages.FORBIDDEN_PAGE).forward(request, response);
  }
}
