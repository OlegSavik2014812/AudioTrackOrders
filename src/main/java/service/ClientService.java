package service;

import entity.Client;
import entity.Purchase;
import entity.Review;
import exception.ServiceException;

import java.util.List;

public interface ClientService {
    void signUpClient(Client client) throws ServiceException;

    void makePurchase(Purchase purchase) throws ServiceException;

    List<Purchase> getClientPurchases(Client client) throws ServiceException;

    double checkClientBalance(Client client) throws ServiceException;

    void addReview(Review review) throws ServiceException;
}
