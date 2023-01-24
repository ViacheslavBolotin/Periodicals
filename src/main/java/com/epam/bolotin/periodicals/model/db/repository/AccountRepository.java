package com.epam.bolotin.periodicals.model.db.repository;

import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.dto.AccountDto;
import com.epam.bolotin.periodicals.model.db.entity.Account;

/**
 * @author: Viacheslav Bolotin
 * @date: 06.01.2023
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public interface AccountRepository {

    void create(Account account) throws DBException;

    void update(Account account) throws DBException;

    void delete(long id) throws DBException;

    Account getById(long id);

    Account getByUserId(long id);

    BigDecimal getAmountByUserId(long id);

    List<Account> getAll();

    long  getSize();
    List<AccountDto> getAllLimitSort(long from, long size, String sortType);

    Connection debitedFromAccount(long userId, BigDecimal sum) throws DBException;
}
