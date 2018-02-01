package com.audioord.web.filter;

import com.audioord.model.account.Role;
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
    if (commandName != null && !commandName.isEmpty()) {
      if (user != null) {

        if (user.getRole() == Role.ADMIN) {

          if (!Security.isAllowedToAdmin(commandName)) {
            request.getRequestDispatcher(Pages.FORBIDDEN_PAGE).forward(request, response);
          }

        }

        if (user.getRole() == Role.CLIENT) {

          if (!Security.isAllowedToClient(commandName)) {
            request.getRequestDispatcher(Pages.FORBIDDEN_PAGE).forward(request, response);
          }

        }

      } else if (!Security.isAllowedToGuest(commandName)) {
        request.getRequestDispatcher(Pages.FORBIDDEN_PAGE).forward(request, response);
      }
      next.doFilter(request, response);
    }
  }


  @Override
  public void destroy() {

  }
}
