package service;

import entity.*;
import entity.Package;
import exception.DAOException;
import exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    void addAlbum(Album album, String nameArtist) throws DAOException, SQLException, ServiceException;

    void addArtist(Artist artist) throws DAOException, SQLException, ServiceException;

    void addAlbumAudioTrack(AudioTrack audioTrack, String nameArtist, String nameAlbum) throws DAOException, SQLException, ServiceException;

    void addSingleAudioTrack(AudioTrack audioTrack, String nameArtist) throws DAOException, SQLException, ServiceException;

    void addPackage(Package pack) throws DAOException, SQLException, ServiceException;

    void addPackageTrack(AudioTrack audioTrack, String namePackage) throws DAOException, SQLException, ServiceException;

    void updateAudioTrackPrice(AudioTrack audioTrack, double newPrice) throws DAOException, SQLException, ServiceException;

    void updatePackageName(Package pack, String line) throws DAOException, SQLException, ServiceException;

    void changeClientBonus(Client client, double newBonus) throws DAOException, SQLException, ServiceException;

    void deleteTrackFromPackage(PackageTrack packageTrack) throws DAOException, SQLException, ServiceException;

    List<Purchase> getAllPurchases() throws DAOException, SQLException, ServiceException;

    void setMoneyToClient(Client client, double money) throws DAOException, SQLException, ServiceException;

    List<Client> getAllClients() throws DAOException, SQLException, ServiceException;
}
