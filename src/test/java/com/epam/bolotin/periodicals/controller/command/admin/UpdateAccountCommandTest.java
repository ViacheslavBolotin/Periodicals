package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.client.UpdateAccountCommand;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author: Viacheslav Bolotin
 * @date: 20.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class UpdateAccountCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    AccountService accountService;
    @InjectMocks
    UpdateAccountCommand command;

    @Test
    public void testUpdateAccount() throws AppException {

        Account account = new Account();
        account.setId(1);
        account.setAmount(BigDecimal.valueOf(100));

        Mockito.when(req.getParameter("account_id")).thenReturn("1");
        Mockito.when(req.getParameter("replenish_sum")).thenReturn("10");
        Mockito.when(accountService.findByID(1)).thenReturn(account);

        String result = command.execute(req, resp);
        Mockito.verify(accountService, Mockito.times(1)).findByID(1);
        Mockito.verify(accountService, Mockito.times(1)).update(any());
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }

    @Test
    public void testUpdateAccountError() throws AppException {

        Mockito.when(req.getParameter("account_id")).thenReturn("1");
        Mockito.when(req.getParameter("replenish_sum")).thenReturn("0");

        String result = command.execute(req, resp);
        Mockito.verify(accountService, Mockito.never()).findByID(1);
        Mockito.verify(accountService, Mockito.never()).update(any());
        assertEquals(PagePath.COMMAND_ACCOUNT, result);
    }
}
