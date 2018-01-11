package listener;

import database.connectionpool.ConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ConnectionListener implements ServletContextListener {
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            connectionPool.init();
        } catch (SQLException e) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            connectionPool.closeConnections();
        } catch (SQLException e) {

        }
    }
}
