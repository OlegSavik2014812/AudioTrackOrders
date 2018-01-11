package database.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ConnectionPool {

    private final static ConnectionPool INSTANCE = new ConnectionPool();

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolsize;

    private BlockingQueue<Connection> availableConnections;
    private BlockingQueue<Connection> occupiedConnections;

    private ConnectionPool() {
        this.driverName = DBParameter.DB_DRIVER;
        this.url = DBParameter.DB_URL;
        this.user = DBParameter.DB_USER;
        this.password = DBParameter.DB_PASSWORD;
        this.poolsize = DBParameter.DB_POOLSIZE;
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public void init() throws SQLException {
        availableConnections = new ArrayBlockingQueue<Connection>(poolsize);
        occupiedConnections = new ArrayBlockingQueue<Connection>(poolsize);
        for (int i = 0; i < poolsize; i++) {
            availableConnections.add(DriverManager.getConnection(url, user, password));
        }
    }

    public Connection takeConnection() throws InterruptedException {
        Connection connection = availableConnections.take();
        occupiedConnections.put(connection);
        return connection;
    }

    public void putBackConnection(Connection connection) throws InterruptedException {
        if (connection != null) {
            availableConnections.put(connection);
            occupiedConnections.remove(connection);
        }
    }


    public void closeConnections() throws SQLException {
        for (Connection availableConnection : availableConnections) {
            availableConnection.close();
        }
        for (Connection occupiedConnection : occupiedConnections) {
            occupiedConnection.close();
        }
        availableConnections.clear();
        occupiedConnections.clear();
    }
}

