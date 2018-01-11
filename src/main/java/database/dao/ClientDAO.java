package database.dao;

import entity.Client;
import entity.Purchase;
import entity.Review;
import exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    void signUpClient(Client client) throws DAOException, SQLException;

    void makePurchase(Purchase purchase) throws DAOException;

    List<Purchase> getClientPurchases(Client client) throws DAOException;

    double checkClientBalance(Client client) throws DAOException;

    void addReview(Review review) throws DAOException, SQLException;
}
