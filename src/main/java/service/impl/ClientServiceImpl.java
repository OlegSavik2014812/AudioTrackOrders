package service.impl;

import database.dao.ClientDAO;
import database.dao.factory.DAOFactory;
import entity.Client;
import entity.Purchase;
import entity.Review;
import exception.DAOException;
import exception.ServiceException;
import service.ClientService;

import java.sql.SQLException;
import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public void signUpClient(Client client) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        try {
            clientDAO.signUpClient(client);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void makePurchase(Purchase purchase) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        try {
            clientDAO.makePurchase(purchase);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Purchase> getClientPurchases(Client client) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        try {
            return clientDAO.getClientPurchases(client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public double checkClientBalance(Client client) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        try {
            return clientDAO.checkClientBalance(client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addReview(Review review) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ClientDAO clientDAO = daoFactory.getClientDAO();
        try {
            clientDAO.addReview(review);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
