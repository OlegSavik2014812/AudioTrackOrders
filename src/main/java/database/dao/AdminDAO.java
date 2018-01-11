package database.dao;

import entity.*;
import entity.Package;
import exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface AdminDAO {
    void addAlbum(Album album, String nameArtist) throws DAOException, SQLException;

    void addArtist(Artist artist) throws DAOException, SQLException;

    void addAlbumAudioTrack(AudioTrack audioTrack, String nameArtist, String nameAlbum) throws DAOException, SQLException;

    void addSingleAudioTrack(AudioTrack audioTrack, String nameArtist) throws DAOException, SQLException;

    void addPackage(Package pack) throws DAOException, SQLException;

    void addPackageTrack(AudioTrack audioTrack, String namePackage) throws DAOException, SQLException;

    void updateAudioTrackPrice(AudioTrack audioTrack, double newPrice) throws DAOException, SQLException;

    void updatePackageName(Package pack, String line) throws DAOException, SQLException;

    void changeClientBonus(Client client, double newBonus) throws DAOException, SQLException;

    void deleteTrackFromPackage(PackageTrack packageTrack) throws DAOException, SQLException;

    List<Purchase> getAllPurchases() throws DAOException, SQLException;

    void setMoneyToClient(Client client, double money) throws DAOException, SQLException;

    List<Client> getAllClients() throws DAOException, SQLException;
}
