package database.dao.impl;

import database.connectionpool.ConnectionPool;
import database.dao.CommonDAO;
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

public class CommonDAOimpl implements CommonDAO {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public List<AudioTrack> getAllAudioTracks() throws DAOException, SQLException {
        List<AudioTrack> tracks = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CommonQuery.SELECT_ALL_AUDIO);
            while (resultSet.next()) {
                tracks.add(createTrack(resultSet));
            }
            return tracks;
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

    @Override
    public List<AudioTrack> getAudioTrackOfArtist(Artist artist) throws DAOException, SQLException {
        List<AudioTrack> tracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_AUDIOTRACK_OF_ARTIST);
            preparedStatement.setString(1, artist.getNameArtist());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tracks.add(createTrack(resultSet));
            }
            return tracks;
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
    public AudioTrack getAudioTrackUsingName(String name) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_AURIOTRACK_WITH_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createTrack(resultSet);
            } else {
                //написать ошибку
                return null;
            }
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
    public List<AudioTrack> getAudioTracksFromPackage(Package pack) throws DAOException, SQLException {
        List<AudioTrack> tracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_TRACKS_FROM_PACKAGE);
            preparedStatement.setString(1, pack.getNamePackage());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AudioTrack audioTrack = createTrack(resultSet);
                tracks.add(audioTrack);
            }
            return tracks;
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
    public List<Artist> getAllArtists() throws DAOException, SQLException {
        List<Artist> artistList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CommonQuery.SELECT_MOST_ARTIST);
            while (resultSet.next()) {
                artistList.add(createArtist(resultSet));
            }
            return artistList;
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

    @Override
    public List<Package> getAllPackages() throws DAOException, SQLException {
        List<Package> packageList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(CommonQuery.SELECT_ALL_PACKAGES);
            while (resultSet.next()) {
                packageList.add(createPackage(resultSet));
            }
            return packageList;
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

    @Override
    public Artist getArtistUsingName(String name) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_ARTIST_USING_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createArtist(resultSet);
            } else {
                //написать ошибку
                return null;
            }
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
    public List<Album> getAlbumOfArtist(Artist artist) throws DAOException, SQLException {
        List<Album> albumList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CommonQuery.SELECT_ALBUM_BY_ARTIST);
            preparedStatement.setString(1, artist.getNameArtist());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                albumList.add(createAlbum(resultSet));
            }
            return albumList;
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
    public Client signIn(String login, String password) throws DAOException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CommonQuery.SIGN_IN_CLIENT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt(ClientTableColumn.ID));
                client.setFirstNameClient(resultSet.getString(ClientTableColumn.FIRST_NAME));
                client.setLastNameClient(resultSet.getString(ClientTableColumn.LAST_NAME));
                client.setLoginClient(resultSet.getString(ClientTableColumn.LOGIN));
                client.setPasswordClient(resultSet.getString(ClientTableColumn.PASSWORD));
                client.setBonusClient(resultSet.getDouble(ClientTableColumn.BONUS));
                client.setRoleClient(resultSet.getInt(ClientTableColumn.ROLE));
                client.setBalanceClient(resultSet.getDouble(ClientTableColumn.BALANCE));
                return client;
            } else {
                //error
                return null;
            }

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
    public void changePasswordClient(String line, Client client) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            preparedStatement = connection.prepareStatement(CommonQuery.CHANGE_PASSWORD);
            preparedStatement.setString(1, line);
            preparedStatement.setString(2, client.getLoginClient());
            preparedStatement.setString(3, client.getPasswordClient());
            preparedStatement.executeUpdate();

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

    private AudioTrack createTrack(ResultSet resultSet) throws SQLException {
        AudioTrack audioTrack = new AudioTrack();
        audioTrack.setIdAudioTrack(resultSet.getInt(AudioTrackTableColumn.ID));
        audioTrack.setIdArtist(resultSet.getInt(AudioTrackTableColumn.ID_ARTIST));
        audioTrack.setNameAudioTrack(resultSet.getString(AudioTrackTableColumn.NAME));
        audioTrack.setPriceAudioTrack(resultSet.getDouble(AudioTrackTableColumn.PRICE));
        audioTrack.setIdAlbum(resultSet.getInt(AudioTrackTableColumn.idAlbum));
        audioTrack.setNameArtist(resultSet.getString(AudioTrackTableColumn.nameArtist));
        return audioTrack;
    }

    private Album createAlbum(ResultSet resultSet) throws SQLException {
        Album album = new Album();
        album.setIdArtist(resultSet.getInt(AlbumTableColumn.ID_ARTIST));
        album.setNameAlbum(resultSet.getString(AlbumTableColumn.NAME));
        album.setIdAlbum(resultSet.getInt(AlbumTableColumn.ID));
        return album;
    }

    private Artist createArtist(ResultSet resultSet) throws SQLException {
        Artist artist = new Artist();
        artist.setIdArtist(resultSet.getInt(ArtistTableColumn.ID));
        artist.setNameArtist(resultSet.getString(ArtistTableColumn.PSEUDONYM));
        return artist;
    }

    private Package createPackage(ResultSet resultSet) throws SQLException {
        Package pack = new Package();
        pack.setNamePackage(resultSet.getString(PackageTableColumn.NAME));
        pack.setIdPackage(resultSet.getInt(PackageTableColumn.ID));
        return pack;
    }
}
