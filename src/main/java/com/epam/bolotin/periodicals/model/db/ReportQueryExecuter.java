package com.epam.bolotin.periodicals.model.db;

import com.epam.bolotin.periodicals.model.db.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public abstract class ReportQueryExecuter<T extends Entity> {

    private static final Logger LOG = Logger.getLogger(ReportQueryExecuter.class);

    /**
     * QueryBuilder for methods {@link #executeAndReturnList(DBManager, String, Object...)} ,
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     */
    public List<T> executeAndReturnList(final DBManager instance, final String query,
                                           Object... args) {

        LOG.trace(query);
        List<T> models = null;
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(statement, args);
            try (ResultSet rs = statement.executeQuery()) {
                models = getListOfResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return models;
    }

    /**
     * @param instance the DataSource instance
     * @param query    the database query
     * @return the size of table
     */
    public long executeGetSize(final DBManager instance, final String query) {
        Connection conn = instance.getConnection();
        long size = 0;
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                size = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return size;
    }

    /**
     * Sets args of prepared statement.
     * Sets the value of the designated parameter using the given object.
     * The JDBC specification specifies a standard mapping from Java Object types to SQL types.
     * The given argument will be converted to the corresponding SQL type before being sent
     * to the database.
     *
     * @param statement the PreparedStatement
     * @param args      the args
     * @throws SQLException hte SQL exception
     */
    private void setArgsOfPreparedStatement(PreparedStatement statement, Object... args)
            throws SQLException {
        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }
    }

    /**
     * Creates objects.
     *
     * @param rs the ResultSet
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     * @throws SQLException hte SQL exception
     */
    public abstract List<T> getListOfResult(ResultSet rs) throws SQLException;

}
