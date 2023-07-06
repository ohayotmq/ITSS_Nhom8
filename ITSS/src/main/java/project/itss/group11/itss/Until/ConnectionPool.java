package project.itss.group11.itss.Until;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static int INITIAL_POOL_SIZE;

    private static String HOST;

    private static String PORT;

    private static String DB_NAME;
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASS;

    private static ConnectionPool instance;
    private List<Connection> connections;

    private ConnectionPool(String confDir) {
        connections = new ArrayList<>();
        loadConfig(confDir);
        initializePool();
    }

    public static synchronized ConnectionPool getInstance(String confDir) {
        if (instance == null) {
            logger.info("Create pool");
            instance = new ConnectionPool(confDir);
        }
        return instance;
    }

    private void loadConfig(String confDir ){
        Properties properties  =new Properties();
        try(FileInputStream fileInputStream = new FileInputStream(confDir)){
            properties.load(fileInputStream);
        }catch (Exception e){
            logger.error("Error when load DB config File: ", e);
        }
        INITIAL_POOL_SIZE = Integer.parseInt(properties.getProperty("maxPoolSize"));
        HOST = properties.getProperty("database.host");
        PORT = properties.getProperty("database.port");
        DB_NAME = properties.getProperty("database.dbname");
        DB_URL = "jdbc:postgresql://"+HOST+":"+PORT+"/"+DB_NAME+"?loggerLevel=OFF";
        DB_USER = properties.getProperty("database.username");
        DB_PASS = properties.getProperty("database.password");
        logger.info("Load conf database OK");
    }

    private void initializePool() {
        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                connections.add(connection);
            }
        } catch (SQLException e) {
            logger.error("Error when create Connection: ", e);
        }
    }

    public synchronized Connection getConnection() {
        while (connections.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                logger.error("Error when getConnection: " , e);
            }
        }
        return connections.remove(connections.size() - 1);
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
        notifyAll();
    }
}

