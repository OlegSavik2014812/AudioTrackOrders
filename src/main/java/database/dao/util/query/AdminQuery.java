package database.dao.util.query;

public final class AdminQuery {
    private AdminQuery() {
    }

    public final static String INSERT_NEW_ALBUM = "INSERT INTO Album(nameAlbum,Arist_idArtist)values(?,?)";


    public final static String INSERT_NEW_ARTIST = "insert into Artist (pseudonymArtist) values(?)";


    public final static String INSERT_AUDIOTRACK_ALBUM = "insert into AudioTrack(idArtist, nameTrack, trackPrice,Album_idAlbum)values(?,?,?,?)";
    public final static String INSERT_AUDIOTRACK_NO_ALBUM = "insert into AudioTrack(idArtist, nameTrack, trackPrice)values(?,?,?)";
    public final static String UPDATE_TRACK_PRICE = "update audiotrack set trackPrice = ? where nameTrack = ?";


    public final static String INSERT_NEW_PACKAGE = "insert into Package(namePackage)values(?)";
    public final static String UPDATE_PACKAGE_NAME = "update package set namePackage =? where namePackage =?";


    public final static String INSERT_PACKAGETRACK = "insert into PackageTrack(Package_idPackage,AudioTrack_idAudioTrack,AudioTrack_idArtist)values(?,?,?)";
    public final static String DELETE_PACKAGE_TRACK = "delete from packagetrack where AudioTrack_idAudioTrack = ? and Package_idPackage = ?";


    public final static String SELECT_ALL_PURCHASES = "select purchase.* ,audiotrack.nameTrack,client.loginClient from purchase,audiotrack,client where purchase.AudioTrack_idAudioTrack=audiotrack.idAudioTrack and purchase.Client_idClient=client.idClient";


    public final static String SELECT_ALL_CLIENTS = "select * from client";
    public final static String SET_MONEY = "update client set balance = ? where loginClient = ?";
    public final static String SET_BONUS = "update client set bonus = ? where loginClient = ?";
}
