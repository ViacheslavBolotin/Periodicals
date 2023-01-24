package com.epam.bolotin.periodicals.model.service.implementation;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.Validator;
import com.epam.bolotin.periodicals.model.db.dto.AccountDto;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.db.repository.AccountRepository;
import com.epam.bolotin.periodicals.model.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 03.01.2023
 */
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void save(Account account) throws AppException {
        this.accountRepository.create(account);
    }

    @Override
    public void update(Account account) throws AppException {
        this.accountRepository.update(account);
    }

    @Override
    public void delete(long id) throws AppException {
        this.accountRepository.delete(id);
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.getAll();
    }

    @Override
    public Account findByID(long id) {
        return this.accountRepository.getById(id);
    }

    @Override
    public Account findByUserId(long id) {
        return this.accountRepository.getByUserId(id);
    }

    @Override
    public boolean validateAndFillAccount(Account account, HttpServletRequest request) {
        String errorMessage;
        String tempString = request.getParameter("amount").trim();

        errorMessage = Validator.validateAmount(tempString);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;        }
        account.setAmount(new BigDecimal(tempString));

        return true;
    }

    @Override
    public List<AccountDto> findAllLimitSort(long from, long size, String sortType){
        return this.accountRepository.getAllLimitSort(from, size, sortType);
    }

    @Override
    public long findSize() {
        return this.accountRepository.getSize();
    }

}
