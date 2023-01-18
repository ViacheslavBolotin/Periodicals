package com.epam.bolotin.periodicals.model.db;

import com.epam.bolotin.periodicals.Constants;
import com.epam.bolotin.periodicals.controller.FrontController;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

    private static DBManager instance;
    private static final Logger LOG = Logger.getLogger(DBManager.class);
    private MysqlDataSource ds;

    /**
     * Singleton.
     */
    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }
    private DBManager() {
        try {
            createPool();
        } catch (Exception e) {
            LOG.error("Failed to create DataSource ("+e.getMessage()+")");
        }
    }

    /**
     * Establishes a connection to the database.
     *
     * @return the connection to the database
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = this.ds.getConnection();
        } catch (SQLException e) {
            LOG.error("Failed to connect to DB ("+e.getMessage()+")");
        }
        return conn;
    }

    /**
     * Closes the connection.
     *
     * @param conn closes the database connection
     */
    public void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets utils properties.
     *
     * @return the databases connection properties
     * @throws IOException by failed or interrupted I/O operations.
     */
    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        props.load(DBManager.class.getResourceAsStream(Constants.SETTINGS_DB_FILE));
        return props;
    }

    /**
     * Create a pool.
     *
     * @throws Exception the exception
     */
    private void createPool() throws Exception {
        Properties props = getProperties();
        ds = new MysqlConnectionPoolDataSource();
        ds.setURL((String) props.get("connection.url"));
    }

}
