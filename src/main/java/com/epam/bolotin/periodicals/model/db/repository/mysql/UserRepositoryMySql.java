package com.epam.bolotin.periodicals.model.db.repository.mysql;

import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.builder.UserQueryBuilder;
import com.epam.bolotin.periodicals.model.db.DBManager;
import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.db.repository.UserRepository;

import java.sql.Connection;
import java.util.List;

public class UserRepositoryMySql implements UserRepository {

    private DBManager instance = DBManager.getInstance();
    private QueryExecuter queryExecuter = new UserQueryBuilder();

    private static final String GET_ALL = "SELECT * FROM user ORDER BY "+ Fields.USER_NAME;
    private static final String GET_BY_ID = "SELECT * FROM user WHERE "+ Fields.USER_ID +" = ?";
    private static final String GET_BY_NAME = "SELECT * FROM user WHERE "+ Fields.USER_NAME +" = ?";
    private static final String CREATE =
            "INSERT INTO user ("+Fields.USER_ID+", "+Fields.USER_FIRST_NAME+", "+Fields.USER_LAST_NAME+", "
                    +Fields.USER_NAME+", "+Fields.USER_PASSWORD+", "+Fields.USER_EMAIL+", "
                    +Fields.USER_ROLE_ID+", "+Fields.USER_CREATE_TIME+", "+Fields.USER_BLOCKED+") "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, 0)";
    private static final String UPDATE =
            "UPDATE user SET "+Fields.USER_FIRST_NAME+" = ?, "+Fields.USER_LAST_NAME+" = ?, "
                    +Fields.USER_BLOCKED+" = ?, "+Fields.USER_NAME+" = ?, "+Fields.USER_PASSWORD+" = ?, "
                    +Fields.USER_EMAIL+" = ?, "+Fields.USER_ROLE_ID+" = ? WHERE "+Fields.USER_ID+" = ?";
    private static final String DELETE = "DELETE FROM user WHERE "+ Fields.USER_ID +" = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX("+ Fields.USER_ID +")+1 from user";

    private static final String CREATE_ACCOUNT =
            "INSERT INTO account (" + Fields.ACCOUNT_ID+", " + Fields.ACCOUNT_USER_ID+", " +
                    Fields.ACCOUNT_AMOUNT + ") VALUES (?, ?, 0)";
    private static final String GET_NEXT_AUTO_INCREMENT_ACCOUNT = "SELECT MAX(" + Fields.ACCOUNT_ID + ")+1 from account";

    @Override
    public User getById(long id) {
        return (User) queryExecuter.executeAndReturn(instance, GET_BY_ID, id);
    }
    @Override
    public User getByName(String userName) {
        return (User) queryExecuter.executeAndReturn(instance, GET_BY_NAME, userName);
    }
    @Override
    public List<User> getAll() {
        return queryExecuter.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public void create(User user) throws DBException {

        long userId = queryExecuter.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        Connection connection = queryExecuter.executeTransactionStart(
                instance, CREATE, userId, user.getFirstName(), user.getLastName(),
                user.getUserName(), user.getPassword(), user.getEmail(), user.getUserRoleId());
        user.setId(userId);

        long accountId = queryExecuter.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT_ACCOUNT);
        queryExecuter.executeTransactionEnd(instance, connection, CREATE_ACCOUNT, accountId, userId);

    }

    @Override
    public void update(User user) throws DBException {
        queryExecuter.execute(instance, UPDATE, user.getFirstName(), user.getLastName(), user.isBlocked(),
                user.getUserName(), user.getPassword(), user.getEmail(), user.getUserRoleId(), user.getId());
    }

    @Override
    public void delete(long id) throws DBException {
        queryExecuter.execute(instance, DELETE, id);
    }
}
