package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.db.entity.User;
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
 * @date: 19.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class EditUserCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    HttpSession session;
    @InjectMocks
    EditUserCommand command;
    User user = new User();

    @BeforeEach
    public void setUp() {
        user.setId(1);
        user.setUserName("User");
    }

    @Test
    public void testEditUserSuccessful() {

        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when((User) session.getAttribute("user")).thenReturn(user);

        String result = command.execute(req, resp);
        assertEquals(PagePath.PAGE_USER_PROFILE, result);
    }

    @Test
    public void testEditUserNotSuccessful() {

        user.setId(0);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when((User) session.getAttribute("user")).thenReturn(user);

        String result = command.execute(req, resp);
        assertEquals(PagePath.PAGE_ERROR, result);
    }
}
