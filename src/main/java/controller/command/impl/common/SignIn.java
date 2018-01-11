package controller.command.impl.common;

import controller.command.Command;
import entity.Client;
import exception.ServiceException;
import service.CommonService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String page = null;
        try {
            ServiceFactory serviceFactory = ServiceFactory.getINSTANCE();
            CommonService commonService = serviceFactory.getCommonService();
            String loginClient = request.getParameter("login");
            String passwordClient = request.getParameter("password");
            Client client = commonService.signIn(loginClient, passwordClient);



        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
