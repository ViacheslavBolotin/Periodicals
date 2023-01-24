package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Viacheslav Bolotin
 * @date: 24.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class AccountsCommandTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    AccountService accountService;
    @InjectMocks
    AccountsCommand command;

    @Test
    public void testAccountsCommand() {

        Mockito.when(req.getParameter(PagePath.SORT_PARAMETER)).thenReturn("ws");
        Mockito.when(req.getParameter(PagePath.PAGE_PARAMETER)).thenReturn("1");
        Mockito.when(accountService.findSize()).thenReturn(10L);

        String result = command.execute(req, resp);
        Mockito.verify(accountService, Mockito.times(1)).findSize();
        Mockito.verify(accountService, Mockito.times(1)).findAllLimitSort(0, 5, "ws");
        assertEquals(PagePath.PAGE_ACCOUNTS, result);
    }

}
