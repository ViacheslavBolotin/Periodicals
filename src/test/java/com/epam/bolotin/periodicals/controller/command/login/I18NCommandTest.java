package com.epam.bolotin.periodicals.controller.command.login;

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
 * @date: 10.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class I18NCommandTest {
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private HttpSession session;
    @InjectMocks
    private I18NCommand command;

    @Test
    public void testI18NCommand() {

        Mockito.when(req.getParameter("uk")).thenReturn("uk");
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("current_action")).thenReturn("login");

        String result = command.execute(req, resp);
        assertEquals("login", result);
    }

}
