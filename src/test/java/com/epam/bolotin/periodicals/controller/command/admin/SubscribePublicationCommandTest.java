package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.Crypt;
import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.client.SubscribePublicationCommand;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import org.junit.jupiter.api.BeforeEach;
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
 * @date: 20.01.2023
 */

@ExtendWith(MockitoExtension.class)
public class SubscribePublicationCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    HttpSession session;
    @Mock
    PublicationService publicationService;
    @InjectMocks
    SubscribePublicationCommand command;

    User user = new User();

    @BeforeEach
    public void setUp() {
        user.setId(2);
        user.setUserName("user");
        user.setPassword(Crypt.hashPassword("pass"));
        user.setUserRoleId(2);
    }

    @Test
    public void testSubscribePublication() throws AppException {

        Mockito.when(req.getParameter("publication_id")).thenReturn("2");
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when((User) session.getAttribute("user")).thenReturn(user);
        Mockito.when(publicationService.subscribeToPublication(2, 2)).thenReturn(0);

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.times(1)).subscribeToPublication(2L, 2L);
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }

    @Test
    public void testSubscribePublicationError() throws AppException {

        Mockito.when(req.getParameter("publication_id")).thenReturn("2");
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when((User) session.getAttribute("user")).thenReturn(user);
        Mockito.when(publicationService.subscribeToPublication(2, 2)).thenReturn(-1);

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.times(1)).subscribeToPublication(2L, 2L);
        assertEquals(PagePath.COMMAND_PUBLICATIONS, result);
    }
}
