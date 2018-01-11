package service.factory;

import service.AdminService;
import service.ClientService;
import service.CommonService;
import service.impl.AdminServiceImpl;
import service.impl.ClientServiceImpl;
import service.impl.CommonServiceImpl;

public class ServiceFactory {
    private final static ServiceFactory INSTANCE = new ServiceFactory();
    private final AdminService adminService = new AdminServiceImpl();
    private final CommonService commonService = new CommonServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getINSTANCE() {
        return INSTANCE;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public CommonService getCommonService() {
        return commonService;
    }

    public ClientService getClientService() {
        return clientService;
    }
}
