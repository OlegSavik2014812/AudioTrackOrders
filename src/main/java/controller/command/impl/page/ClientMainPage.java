package controller.command.impl.page;

import controller.command.Command;
import exception.ServiceException;
import resource.JspPageName;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientMainPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        String page = JspPageName.CLIENT_MAIN_PAGE;
        request.getRequestDispatcher(page).forward(request, response);
    }
}
