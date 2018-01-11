package service;

import entity.*;
import entity.Package;
import exception.ServiceException;

import java.util.List;

public interface CommonService {
    List<AudioTrack> getAllAudioTracks() throws ServiceException;

    List<AudioTrack> getAudioTrackOfArtist(Artist artist) throws ServiceException;

    AudioTrack getAudioTrackUsingName(String name) throws ServiceException;

    List<AudioTrack> getAudioTracksFromPackage(Package pack) throws ServiceException;

    List<Artist> getAllArtists() throws ServiceException;

    List<Package> getAllPackages() throws ServiceException;

    Artist getArtistUsingName(String name) throws ServiceException;

    List<Album> getAlbumOfArtist(Artist artist) throws ServiceException;
    Client signIn(String login, String password) throws ServiceException;

    void changePasswordClient(String line, Client client) throws ServiceException;
}
