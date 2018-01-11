package database.dao.impl;

import database.connectionpool.ConnectionPool;
import database.dao.ClientDAO;
import database.dao.util.query.ClientQuery;
import database.dao.util.tablecolumns.ClientTableColumn;
import database.dao.util.tablecolumns.PurchaseTableColumn;
import entity.Client;
import entity.Purchase;
import entity.Review;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOimpl implements ClientDAO {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public void signUpClient(Client client) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ClientQuery.SIGN_UP_INSERT_NEW_CLIENT);
            preparedStatement.setString(1, client.getFirstNameClient());
            preparedStatement.setString(2, client.getLastNameClient());
            preparedStatement.setString(3, client.getLoginClient());
            preparedStatement.setString(4, client.getPasswordClient());
            preparedStatement.setDouble(5, client.getBonusClient());
            preparedStatement.setInt(6, client.getRoleClient());
            preparedStatement.setDouble(7, client.getBalance());
            preparedStatement.executeUpdate();
        } catch (InterruptedException | SQLException exc) {
            throw new DAOException(exc);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void makePurchase(Purchase purchase) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(ClientQuery.INSERT_NEW_PURCHASE);
            preparedStatement.setDouble(1, purchase.getPricePurchase());
            preparedStatement.setInt(2, purchase.getIdClient());
            preparedStatement.setInt(3, purchase.getIdAudioTrack());
            preparedStatement.executeUpdate();


            statement = connection.createStatement();
            resultSet = statement.executeQuery(ClientQuery.SELECT_LAST_INSERT);
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(2);
            }

            preparedStatement = connection.prepareStatement(ClientQuery.UPDATE_MINUS_BALANCE);
            preparedStatement.setDouble(1, purchase.getPricePurchase());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (InterruptedException | SQLException exc) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                throw new DAOException(e);
            }
        }
    }

    @Override
    public List<Purchase> getClientPurchases(Client client) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Purchase> purchaseList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ClientQuery.SELECT_CLIENT_PURCHASES);
            preparedStatement.setInt(1, client.getIdClient());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Purchase purchase = new Purchase();
                purchase.setIdClient(resultSet.getInt(PurchaseTableColumn.ID_CLIENT));
                purchase.setIdAudioTrack(resultSet.getInt(PurchaseTableColumn.ID_AUDIO_TRACK));
                purchase.setPricePurchase(resultSet.getDouble(PurchaseTableColumn.PRICE));
                purchase.setDatePurchase(resultSet.getString(PurchaseTableColumn.PURCHASE_DATE));
                purchaseList.add(purchase);
            }
            return purchaseList;
        } catch (InterruptedException | SQLException exc) {
            throw new DAOException(exc);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public double checkClientBalance(Client client) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ClientQuery.SELECT_CLIENT_BALANCE);
            preparedStatement.setString(1, client.getLoginClient());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(ClientTableColumn.BALANCE);
            } else {
                //error
                return Double.parseDouble(null);
            }

        } catch (InterruptedException | SQLException exc) {
            throw new DAOException(exc);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addReview(Review review) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(ClientQuery.INSERT_NEW_REVIEW);
            preparedStatement.setString(1, review.getTextReview());
            preparedStatement.setInt(2, review.getIdClient());
            preparedStatement.setInt(3, review.getIdAudioTrack());
            preparedStatement.executeUpdate();
        } catch (InterruptedException | SQLException exc) {
            throw new DAOException(exc);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

