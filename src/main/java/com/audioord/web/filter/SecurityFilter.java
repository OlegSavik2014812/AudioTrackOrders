package com.audioord.web.filter;

import com.audioord.model.account.User;
import com.audioord.web.command.Pages;
import com.audioord.web.security.Security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain next) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    User user = (User) request.getSession().getAttribute("USER");

    String commandName = request.getParameter("name");
    if (commandName == null && commandName.isEmpty()) {
      forwardForbiddenError(request, response);
    }

    if (user == null) {
      if (!Security.isAllowedToGuest(commandName)) {
        forwardForbiddenError(request, response);
      }
    } else {
      switch (user.getRole()) {
        case ADMIN: {
          if (!Security.isAllowedToAdmin(commandName)) {
            forwardForbiddenError(request, response);
          }
          break;
        }

        case CLIENT: {
          if (!Security.isAllowedToClient(commandName)) {
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
