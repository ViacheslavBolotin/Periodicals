package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.DBManager;
import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.db.dto.AccountDto;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 03.01.2023
 */
public class AccountQueryBuilder extends QueryExecuter {
    private static final Logger LOG = Logger.getLogger(AccountQueryBuilder.class);
    @Override
    public List<Account> getListOfResult(ResultSet rs) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        while (rs.next()) {
            Account account = new Account();
            AccountBuilder.fillAccount(rs, account);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account getResult(ResultSet rs) throws SQLException {
        Account account = new Account();
        while (rs.next()) {
            AccountBuilder.fillAccount(rs, account);
        }
        return account;
    }

    public List<AccountDto> executeAndReturnValuesDto(final DBManager instance, final String query, Object... args) {

        LOG.trace(query);

        List<AccountDto> models = null;
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            try (ResultSet rs = statement.executeQuery()) {
                models = getListOfResultDto(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return models;
    }

    public List<AccountDto> getListOfResultDto(ResultSet rs) throws SQLException {
        List<AccountDto> accounts = new ArrayList<>();
        while (rs.next()) {
            AccountDto account = new AccountDto();
            AccountBuilder.fillAccountDto(rs, account);
            accounts.add(account);
        }
        return accounts;
    }

}
