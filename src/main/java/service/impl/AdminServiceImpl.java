package service.impl;

import database.dao.AdminDAO;
import database.dao.factory.DAOFactory;
import entity.*;
import entity.Package;
import exception.DAOException;
import exception.ServiceException;
import service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public void addAlbum(Album album, String nameArtist) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.addAlbum(album, nameArtist);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addArtist(Artist artist) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.addArtist(artist);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addAlbumAudioTrack(AudioTrack audioTrack, String nameArtist, String nameAlbum) throws DAOException, SQLException, ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.addAlbumAudioTrack(audioTrack, nameArtist, nameAlbum);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addSingleAudioTrack(AudioTrack audioTrack, String nameArtist) throws DAOException, SQLException, ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.addSingleAudioTrack(audioTrack, nameArtist);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addPackage(Package pack) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.addPackage(pack);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void addPackageTrack(AudioTrack audioTrack, String namePackage) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.addPackageTrack(audioTrack, namePackage);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void changeClientBonus(Client client, double newBonus) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.changeClientBonus(client, newBonus);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteTrackFromPackage(PackageTrack packageTrack) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.deleteTrackFromPackage(packageTrack);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Client> getAllClients() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            return adminDAO.getAllClients();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Purchase> getAllPurchases() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            return adminDAO.getAllPurchases();
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setMoneyToClient(Client client, double newBalance) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.setMoneyToClient(client, newBalance);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateAudioTrackPrice(AudioTrack audioTrack, double newPrice) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.updateAudioTrackPrice(audioTrack, newPrice);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updatePackageName(Package pack, String newName) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        AdminDAO adminDAO = daoFactory.getAdminDAO();
        try {
            adminDAO.updatePackageName(pack, newName);
        } catch (DAOException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
