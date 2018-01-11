package database.dao.factory;

import database.dao.AdminDAO;
import database.dao.ClientDAO;
import database.dao.CommonDAO;
import database.dao.impl.AdminDAOimpl;
import database.dao.impl.ClientDAOimpl;
import database.dao.impl.CommonDAOimpl;

public class DAOFactory {
    private final static DAOFactory INSTANCE = new DAOFactory();
    private final AdminDAO adminDAO = new AdminDAOimpl();
    private final ClientDAO clientDAO = new ClientDAOimpl();
    private final CommonDAO commonDAO = new CommonDAOimpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    public AdminDAO getAdminDAO() {
        return adminDAO;
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }

    public CommonDAO getCommonDAO() {
        return commonDAO;
    }
}
