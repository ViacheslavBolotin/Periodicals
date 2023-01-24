package com.epam.bolotin.periodicals.controller.command.client;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Viacheslav Bolotin
 * @date: 24.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class AccountCommandTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    HttpSession session;
    @Mock
    AccountService accountService;
    @InjectMocks
    AccountCommand command;

    @Test
    public void testAccountCommand() {

        User user = new User();
        user.setId(2L);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when((User) session.getAttribute("user")).thenReturn(user);

        String result = command.execute(req, resp);
        Mockito.verify(accountService, Mockito.times(1)).findByUserId(2L);
        assertEquals(PagePath.PAGE_ACCOUNT, result);
    }

}
