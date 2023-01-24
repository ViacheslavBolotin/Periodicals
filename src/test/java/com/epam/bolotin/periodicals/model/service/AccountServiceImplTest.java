package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.db.repository.AccountRepository;
import com.epam.bolotin.periodicals.model.service.implementation.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Viacheslav Bolotin
 * @date: 23.01.2023
 */
@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    HttpServletRequest req;
    @Mock
    AccountRepository accountRepository;

    Account account = new Account();

    @Test
    void TestValidateAndFillAccountSuccess() {

        AccountService accountService = new AccountServiceImpl(accountRepository);
        Mockito.when(req.getParameter("amount")).thenReturn("10.56");
        boolean result = accountService.validateAndFillAccount(account, req);
        assertEquals(BigDecimal.valueOf(10.56), account.getAmount());
        assertEquals(true, result);
    }

    @Test
    void TestValidateAndFillAccountError() {

        AccountService accountService = new AccountServiceImpl(accountRepository);
        Mockito.when(req.getParameter("amount")).thenReturn("**111");
        boolean result = accountService.validateAndFillAccount(account, req);
        assertEquals(null, account.getAmount());
        assertEquals(false, result);
    }
}