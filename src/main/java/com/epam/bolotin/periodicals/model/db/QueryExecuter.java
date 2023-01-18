package com.epam.bolotin.periodicals.model.db;

import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.entity.Entity;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

public abstract class QueryExecuter<T extends Entity> {

    private static final Logger LOG = Logger.getLogger(QueryExecuter.class);

    /**
     * Lets you know the next automatic id postgres.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @return next automatic id postgres
     */
    public final int getNextAutoIncrement(final DBManager instance, final String query) {
        int nextid = -1;
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    nextid = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return nextid;
    }

    /**
     * Execute query (CREATE, UPDATE, DELETE) the database.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     */
    public final void execute(final DBManager instance, final String query) throws DBException {
        LOG.trace(query);
        executeQuery(instance, query, new Object[0]);
    }

    /**
     * Execute query (CREATE, UPDATE, DELETE) the database.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     */
    public final void execute(final DBManager instance, final String query, Object... args) throws DBException {
        LOG.trace(query);
        executeQuery(instance, query, args);
    }

    public final Connection executeTransactionStart(final DBManager instance, final String query, Object... args) {
        LOG.trace(query);
        return executeQueryTransactionStart(instance, query, args);
    }

    public final void executeTransactionEnd(final DBManager instance, Connection conn, final String query, Object... args) throws DBException {
        LOG.trace(query);
        executeQueryTransactionSEnd(instance, conn, query, args);
    }

    /**
     * Reading from database.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     */
    public final List<T> executeAndReturnList(final DBManager instance, final String query,
                                              Object... args) {
        LOG.trace(query);
        return executeAndReturnValues(instance, query, args);
    }

    /**
     * Reading from database by parameter.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     */
    public final T executeAndReturn(final DBManager instance, final String query, Object... args) {
        LOG.trace(query);
        return executeAndReturnValue(instance, query, args);
    }

    /**
     * QueryBuilder for methods {@link #execute(DBManager, String),
     * {@link #execute(DBManager, String, Object...)}}.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     */
    private void executeQuery(final DBManager instance, String query, Object... args) throws DBException {
        Connection conn = instance.getConnection();
        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(st, args);
            st.executeUpdate();
            LOG.trace(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException(e.getMessage());
        }
        instance.closeConnection(conn);
    }

    private Connection executeQueryTransactionStart(final DBManager instance, String query, Object... args) {
        Connection conn = instance.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(st, args);
            st.executeUpdate();
            LOG.trace(query);
        } catch (SQLException e) {
            e.printStackTrace();
            instance.closeConnection(conn);
            return null;
        }
        return conn;
    }

    private void executeQueryTransactionSEnd(final DBManager instance, Connection conn, String query, Object... args) throws DBException {

        try (PreparedStatement st = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(st, args);
            st.executeUpdate();
            conn.commit();
            LOG.trace(query);
        } catch (SQLException e) {

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new DBException(ex.getMessage());
            }

            throw new DBException(e.getMessage());

        } finally {
            instance.closeConnection(conn);
        }
    }

    /**
     * QueryBuilder for methods {@link #executeAndReturnList(DBManager, String, Object...)} ,
     * {@link #executeAndReturn(DBManager, String, Object...)}}.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     */
    private List<T> executeAndReturnValues(final DBManager instance, final String query,
                                           Object... args) {
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
     * @param instance the DataSource instance
     * @param query    the database query
     * @return the size of table
     */
    public long executeGetSizeById(final DBManager instance, final String query, Object... args) {
        Connection conn = instance.getConnection();
        long size = 0;
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(statement, args);
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
     * QueryBuilder for methods {@link #executeAndReturn(DBManager, String, Object...)} ,
     * {@link #executeAndReturn(DBManager, String, Object...)}}.
     *
     * @param instance the DataSource instance
     * @param query    the database query
     * @param args     the args
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     */
    private T executeAndReturnValue(final DBManager instance, final String query, Object... args) {
        T model = null;
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            setArgsOfPreparedStatement(statement, args);
            try (ResultSet rs = statement.executeQuery()) {
                model = getResult(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return model;
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
        LOG.trace(statement.toString());
    }

    /**
     * Creates objects.
     *
     * @param rs the ResultSet
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     * @throws SQLException hte SQL exception
     */
    public abstract List<T> getListOfResult(ResultSet rs) throws SQLException;


    /**
     * Creates object.
     *
     * @param rs the ResultSet
     * @return the list of {@link com.epam.bolotin.periodicals.model.db.entity.Entity}
     * @throws SQLException hte SQL exception
     */
    public abstract T getResult(ResultSet rs) throws SQLException;

}
