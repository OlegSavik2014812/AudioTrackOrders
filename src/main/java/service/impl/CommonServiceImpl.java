package service.impl;

import database.dao.CommonDAO;
import database.dao.factory.DAOFactory;
import entity.*;
import entity.Package;
import exception.DAOException;
import exception.ServiceException;
import service.CommonService;

import java.sql.SQLException;
import java.util.List;

public class CommonServiceImpl implements CommonService {
    @Override
    public List<AudioTrack> getAllAudioTracks() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getAllAudioTracks();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<AudioTrack> getAudioTrackOfArtist(Artist artist) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getAudioTrackOfArtist(artist);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public AudioTrack getAudioTrackUsingName(String nameTrack) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getAudioTrackUsingName(nameTrack);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<AudioTrack> getAudioTracksFromPackage(Package pack) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getAudioTracksFromPackage(pack);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Artist> getAllArtists() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getAllArtists();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Package> getAllPackages() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getAllPackages();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Artist getArtistUsingName(String nameArtist) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getArtistUsingName(nameArtist);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Album> getAlbumOfArtist(Artist artist) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.getAlbumOfArtist(artist);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Client signIn(String login, String password) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            return commonDAO.signIn(login, password);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changePasswordClient(String password, Client client) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        CommonDAO commonDAO = daoFactory.getCommonDAO();
        try {
            commonDAO.changePasswordClient(password, client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
