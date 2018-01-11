package database.dao;

import entity.*;
import entity.Package;
import exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface CommonDAO {
    List<AudioTrack> getAllAudioTracks() throws DAOException, SQLException;

    List<AudioTrack> getAudioTrackOfArtist(Artist artist) throws DAOException, SQLException;

    AudioTrack getAudioTrackUsingName(String name) throws DAOException, SQLException;

    List<AudioTrack> getAudioTracksFromPackage(Package pack) throws DAOException, SQLException;

    List<Artist> getAllArtists() throws DAOException, SQLException;

    List<Package> getAllPackages() throws DAOException, SQLException;

    Artist getArtistUsingName(String name) throws DAOException, SQLException;

    List<Album> getAlbumOfArtist(Artist artist) throws DAOException, SQLException;

    Client signIn(String login, String password) throws DAOException, SQLException;

    void changePasswordClient(String line, Client client) throws DAOException;
}
