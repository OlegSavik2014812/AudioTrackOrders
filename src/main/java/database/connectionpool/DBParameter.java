package database.connectionpool;

public final class DBParameter {
    private DBParameter() {
    }

    public final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/audiotrackorders_db?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public final static String DB_USER = "root";
    public final static String DB_PASSWORD = "ANDROhexer1996()";
    public final static int DB_POOLSIZE = 5;
}
