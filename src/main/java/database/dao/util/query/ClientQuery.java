package database.dao.util.query;

public final class ClientQuery {
    private ClientQuery() {
    }

    public final static String SIGN_UP_INSERT_NEW_CLIENT = "insert into Client (first_nameClient, last_nameClient, loginClient, passwordClient, bonus, role,balance) values(?,?,?,?,?,?,?)";
    public final static String UPDATE_MINUS_BALANCE = "UPDATE client SET balance = balance - ? WHERE idClient = ?";
    public final static String SELECT_CLIENT_PURCHASES = "select purchase.* ,audiotrack.nameTrack,client.loginClient from purchase,audiotrack,client where purchase.AudioTrack_idAudioTrack=audiotrack.idAudioTrack and purchase.Client_idClient=client.idClient and Client_idClient =?";
    public final static String SELECT_CLIENT_BALANCE = "select balance from client where loginClient = ?";


    public final static String INSERT_NEW_PURCHASE = "insert into Purchase(pricePurchase,Client_idClient,AudioTrack_idAudioTrack)values(?,?,?)";
    public final static String SELECT_LAST_INSERT = "SELECT * FROM purchase ORDER BY purchase.datePurchase DESC LIMIT 1";


    public final static String INSERT_NEW_REVIEW = "insert into Review(text, Client_idClient, AudioTrack_idAudioTrack)values(?,?,?)";
}
