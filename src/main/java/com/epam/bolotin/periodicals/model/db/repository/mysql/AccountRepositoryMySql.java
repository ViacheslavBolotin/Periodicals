package com.epam.bolotin.periodicals.model.db.repository.mysql;

import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.builder.AccountQueryBuilder;
import com.epam.bolotin.periodicals.model.db.DBManager;
import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.service.dto.AccountDto;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.db.repository.AccountRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class AccountRepositoryMySql implements AccountRepository {

    private DBManager instance = DBManager.getInstance();
    private QueryExecuter queryExecuter = new AccountQueryBuilder();

    private static final String GET_SIZE = "SELECT count(*) FROM account";
    private static final String GET_ALL = "SELECT * FROM account";
    private static final String GET_ALL_LIMIT =
            "SELECT a.*, u.username, u.first_name, u.last_name FROM account as a INNER JOIN user as u ON (a.user_id = u.id) LIMIT ? , ?";
    private static final String GET_ALL_LIMIT_SORT_NAME_ASC =
            "SELECT a.*, u.username, u.first_name, u.last_name FROM account as a INNER JOIN user as u ON (a.user_id = u.id) ORDER BY username ASC LIMIT ? , ?";
    private static final String GET_ALL_LIMIT_SORT_NAME_DESC =
            "SELECT a.*, u.username, u.first_name, u.last_name FROM account as a INNER JOIN user as u ON (a.user_id = u.id) ORDER BY username DESC LIMIT ? , ?";
    private static final String GET_ALL_LIMIT_SORT_AMOUNT_ASC =
            "SELECT a.*, u.username, u.first_name, u.last_name FROM account as a INNER JOIN user as u ON (a.user_id = u.id) ORDER BY amount ASC LIMIT ? , ?";
    private static final String GET_ALL_LIMIT_SORT_AMOUNT_DESC =
            "SELECT a.*, u.username, u.first_name, u.last_name FROM account as a INNER JOIN user as u ON (a.user_id = u.id) ORDER BY amount DESC LIMIT ? , ?";

    private static final String GET_BY_ID = "SELECT * FROM account WHERE " + Fields.ACCOUNT_ID +" = ?";
    private static final String GET_BY_USER_ID = "SELECT * FROM account WHERE " + Fields.ACCOUNT_USER_ID +" = ?";
    private static final String CREATE =
            "INSERT INTO account (" + Fields.ACCOUNT_ID+", " + Fields.ACCOUNT_USER_ID+", " +
                    Fields.ACCOUNT_AMOUNT + ") VALUES (?, ?, 0)";
    private static final String UPDATE =
            "UPDATE account SET " + Fields.ACCOUNT_AMOUNT + " = ? WHERE " + Fields.ACCOUNT_ID + " = ?";
    private static final String DEBITED_FROM_ACCOUNT =
            "UPDATE account SET " + Fields.ACCOUNT_AMOUNT + " = " + Fields.ACCOUNT_AMOUNT
                    + " - ? WHERE " + Fields.ACCOUNT_USER_ID + " = ?";
    private static final String DELETE = "DELETE FROM account WHERE " + Fields.ACCOUNT_ID + " = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX(" + Fields.ACCOUNT_ID + ")+1 from account";

    @Override
    public void create(Account account) throws DBException {
        long id = queryExecuter.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryExecuter.execute(instance, CREATE, id, account.getUserId());
        account.setId(id);
    }

    @Override
    public void update(Account account) throws DBException {
        queryExecuter.execute(instance, UPDATE, account.getAmount(), account.getId());
    }

    @Override
    public void delete(long id) throws DBException {
        queryExecuter.execute(instance, DELETE, id);
    }

    @Override
    public Account getById(long id) {
        return (Account) queryExecuter.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public Account getByUserId(long id) {
        return (Account) queryExecuter.executeAndReturn(instance, GET_BY_USER_ID, id);
    }

    @Override
    public List<Account> getAll() {
        return queryExecuter.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public long getSize() {
        return queryExecuter.executeGetSize(instance, GET_SIZE);
    }

    @Override
    public List<AccountDto> getAllLimitSort(long from, long size, String sortType) {

        switch(sortType) {
            case PaginationSort.NAME_STRAIGHT:
                return ((AccountQueryBuilder)queryExecuter).executeAndReturnValuesDto(instance, GET_ALL_LIMIT_SORT_NAME_ASC, from, size);
            case PaginationSort.NAME_FORWARD:
                return ((AccountQueryBuilder)queryExecuter).executeAndReturnValuesDto(instance, GET_ALL_LIMIT_SORT_NAME_DESC, from, size);
            case PaginationSort.BALANCE_STRAIGHT:
                return ((AccountQueryBuilder)queryExecuter).executeAndReturnValuesDto(instance, GET_ALL_LIMIT_SORT_AMOUNT_ASC, from, size);
            case PaginationSort.BALANCE_FORWARD:
                return ((AccountQueryBuilder)queryExecuter).executeAndReturnValuesDto(instance, GET_ALL_LIMIT_SORT_AMOUNT_DESC, from, size);
            case PaginationSort.WITHOUT_SORT:
            default:
                return ((AccountQueryBuilder)queryExecuter).executeAndReturnValuesDto(instance, GET_ALL_LIMIT, from, size);
        }
    }

    @Override
    public BigDecimal getAmountByUserId(long id) {

        Account account = getByUserId(id);
        if (account == null) {return new BigDecimal(0);
        } else {return account.getAmount();}
    }

    @Override
    public Connection debitedFromAccount(long userId, BigDecimal sum) throws DBException {
        return queryExecuter.executeTransactionStart(instance, DEBITED_FROM_ACCOUNT, sum, userId);
    }
}
