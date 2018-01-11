package database.dao.impl;

import database.connectionpool.ConnectionPool;
import database.dao.AdminDAO;
import database.dao.util.query.AdminQuery;
import database.dao.util.query.CommonQuery;
import database.dao.util.tablecolumns.*;
import entity.*;
import entity.Package;
import exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOimpl implements AdminDAO{
    private final static Logger LOGGER = LogManager.getLogger();

    public void addAlbum(Album album, String artistName) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_ARTIST_USING_NAME);
            preparedStatement.setString(1, artistName);
            ResultSet resultSet = preparedStatement.executeQuery();


            preparedStatement = connection.prepareStatement(AdminQuery.INSERT_NEW_ALBUM);
            preparedStatement.setString(2, album.getNameAlbum());
            preparedStatement.setInt(3, resultSet.getInt(ArtistTableColumn.ID));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (InterruptedException | SQLException exc) {
            connection.rollback();
            throw new DAOException(exc);
        } finally {
            connection.setAutoCommit(true);
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                throw new DAOException(e);
            }
        }
    }

    public void addArtist(Artist artist) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AdminQuery.INSERT_NEW_ARTIST);
            preparedStatement.setString(1, artist.getNameArtist());
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

    public void addAlbumAudioTrack(AudioTrack audioTrack, String nameArtist, String nameAlbum) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_ALBUM_USING_NAME);
            preparedStatement.setString(1, nameAlbum);
            ResultSet albumResult = preparedStatement.executeQuery();


            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_ARTIST_USING_NAME);
            preparedStatement.setString(1, nameArtist);
            ResultSet artistResult = preparedStatement.executeQuery();


            preparedStatement = connection.prepareStatement(AdminQuery.INSERT_AUDIOTRACK_ALBUM);
            preparedStatement.setInt(1, artistResult.getInt(ArtistTableColumn.ID));
            preparedStatement.setString(2, audioTrack.getNameAudioTrack());
            preparedStatement.setDouble(3, audioTrack.getPriceAudioTrack());
            preparedStatement.setInt(4, albumResult.getInt(AlbumTableColumn.ID));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (InterruptedException | SQLException exc) {
            connection.rollback();
            throw new DAOException(exc);
        } finally {
            if (preparedStatement != null) {
                connection.setAutoCommit(true);
                preparedStatement.close();
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSingleAudioTrack(AudioTrack audioTrack, String nameArtist) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_ARTIST_USING_NAME);
            preparedStatement.setString(1, nameArtist);
            ResultSet resultSet = preparedStatement.executeQuery();


            preparedStatement = connection.prepareStatement(AdminQuery.INSERT_AUDIOTRACK_NO_ALBUM);
            preparedStatement.setInt(1, resultSet.getInt(ArtistTableColumn.ID));
            preparedStatement.setString(2, audioTrack.getNameAudioTrack());
            preparedStatement.setDouble(3, audioTrack.getPriceAudioTrack());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (InterruptedException | SQLException exc) {
            connection.rollback();
            throw new DAOException(exc);
        } finally {
            connection.setAutoCommit(true);
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

    public void addPackage(Package pack) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AdminQuery.INSERT_NEW_PACKAGE);
            preparedStatement.setInt(1, pack.getIdPackage());
            preparedStatement.setString(2, pack.getNamePackage());
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

    public void addPackageTrack(AudioTrack audioTrack, String namePackage) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_PACKAGE_USIN_NAME);
            preparedStatement.setString(1, namePackage);
            ResultSet resultSet = preparedStatement.executeQuery();


            preparedStatement = connection.prepareStatement(AdminQuery.INSERT_PACKAGETRACK);
            preparedStatement.setInt(1, resultSet.getInt(PackageTableColumn.ID));
            preparedStatement.setInt(2, audioTrack.getIdAudioTrack());
            preparedStatement.setInt(3, audioTrack.getIdArtist());
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

    public void updateAudioTrackPrice(AudioTrack audioTrack, double newPrice) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AdminQuery.UPDATE_TRACK_PRICE);
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setString(2, audioTrack.getNameAudioTrack());
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

    public void updatePackageName(Package pack, String line) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AdminQuery.UPDATE_PACKAGE_NAME);
            preparedStatement.setString(1, line);
            preparedStatement.setString(2, pack.getNamePackage());
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

    public void changeClientBonus(Client client, double newBonus) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AdminQuery.SET_BONUS);
            preparedStatement.setDouble(1, newBonus);
            preparedStatement.setString(2, client.getLoginClient());
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

    public void deleteTrackFromPackage(PackageTrack packageTrack) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AdminQuery.DELETE_PACKAGE_TRACK);
            preparedStatement.setInt(1, packageTrack.getIdAudioTrack());
            preparedStatement.setInt(2, packageTrack.getIdPackage());
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

    public List<Purchase> getAllPurchases() throws DAOException, SQLException {
        List<Purchase> purchaseList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(AdminQuery.SELECT_ALL_PURCHASES);
            while (resultSet.next()) {
                Purchase purchase = new Purchase();
                purchase.setIdClient(resultSet.getInt(PurchaseTableColumn.ID_CLIENT));
                purchase.setDatePurchase(resultSet.getString(PurchaseTableColumn.PURCHASE_DATE));
                purchase.setIdAudioTrack(resultSet.getInt(PurchaseTableColumn.ID_AUDIO_TRACK));
                purchase.setPricePurchase(resultSet.getDouble(PurchaseTableColumn.PRICE));
                purchase.setNameClient(resultSet.getString(PurchaseTableColumn.LOGIN_CLIENT));
                purchase.setNameAudioTrack(resultSet.getString(PurchaseTableColumn.NAME_TRACK));
                purchaseList.add(purchase);
            }
            return purchaseList;
        } catch (InterruptedException | SQLException exc) {
            throw new DAOException(exc);
        } finally {
            if (statement != null) {
                statement.close();
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMoneyToClient(Client client, double money) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(AdminQuery.SET_MONEY);
            preparedStatement.setDouble(1, money);
            preparedStatement.setString(2, client.getLoginClient());
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

    public List<Client> getAllClients() throws DAOException, SQLException {
        Connection connection = null;
        Statement statement = null;
        List<Client> clientList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(AdminQuery.SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt(ClientTableColumn.ID));
                client.setFirstNameClient(resultSet.getString(ClientTableColumn.FIRST_NAME));
                client.setLastNameClient(resultSet.getString(ClientTableColumn.LAST_NAME));
                client.setLoginClient(resultSet.getString(ClientTableColumn.LOGIN));
                client.setPasswordClient(resultSet.getString(ClientTableColumn.PASSWORD));
                client.setRoleClient(resultSet.getInt(ClientTableColumn.ROLE));
                client.setBonusClient(resultSet.getDouble(ClientTableColumn.BONUS));
                client.setBalanceClient(resultSet.getDouble(ClientTableColumn.BALANCE));
                clientList.add(client);
            }
            return clientList;
        } catch (InterruptedException | SQLException exc) {
            throw new DAOException(exc);
        } finally {
            if (statement != null) {
                statement.close();
            }
            try {
                ConnectionPool.getInstance().putBackConnection(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}