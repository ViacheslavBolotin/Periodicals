package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.dto.AccountDto;
import com.epam.bolotin.periodicals.model.db.entity.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AccountService {

    void save(Account account) throws AppException;
    void update(Account account) throws AppException;
    void delete(long id) throws AppException;

    List<Account> findAll();
    List<AccountDto> findAllFullInfo();
    Account findByID(long id);
    Account findByUserId(long id);

    List<AccountDto> findAllLimitSort(long from, long size, String sortType);
    long findSize();

    boolean validateAndFillAccount(Account account, HttpServletRequest request);
}
