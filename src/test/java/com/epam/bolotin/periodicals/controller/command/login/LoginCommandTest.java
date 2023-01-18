package com.epam.bolotin.periodicals.controller.command.login;

import com.epam.bolotin.periodicals.Crypt;
import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.UserService;

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
 * @date: 10.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class LoginCommandTest {
    @Mock
    private HttpServletRequest req;
    @Mock
    private HttpServletResponse resp;
    @Mock
    private UserService userService;
    @Mock
    private HttpSession session;
    @InjectMocks
    private LoginCommand cut;

    private User user = new User();
    private User admin = new User();

    @BeforeEach
    public void setUp() {
        user.setUserName("user");
        user.setPassword(Crypt.hashPassword("pass"));
        user.setBlocked(false);
        user.setUserRoleId(2);

        admin.setUserName("admin");
        admin.setPassword(Crypt.hashPassword("pass"));
        admin.setBlocked(false);
        admin.setUserRoleId(1);
//        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void ifUserIsUser() {

        Mockito.when(req.getParameter("main_menu")).thenReturn("0");
        Mockito.when(req.getParameter("user_name")).thenReturn("user");
        Mockito.when(req.getParameter("password")).thenReturn("pass");
        Mockito.when(userService.findByName("user")).thenReturn(user);
        Mockito.when(req.getSession()).thenReturn(session);

        String result = cut.execute(req, resp);
        assertEquals(PagePath.COMMAND_PERSONAL_CABINET, result);
    }

    @Test
    public void ifUserIsAdmin() {

        Mockito.when(req.getParameter("main_menu")).thenReturn("0");
        Mockito.when(req.getParameter("user_name")).thenReturn("admin");
        Mockito.when(req.getParameter("password")).thenReturn("pass");
        Mockito.when(userService.findByName("admin")).thenReturn(admin);
        Mockito.when(req.getSession()).thenReturn(session);

        String result = cut.execute(req, resp);
        assertEquals(PagePath.COMMAND_SHOW_USERS, result);
    }

    @Test
    public void ifUserIsBlank() {
        Mockito.when(req.getParameter("main_menu")).thenReturn("0");
        Mockito.when(req.getParameter("user_name")).thenReturn(null);
        Mockito.when(req.getParameter("password")).thenReturn(null);
        Mockito.when(req.getSession()).thenReturn(session);
        String result = cut.execute(req, resp);
        assertEquals(PagePath.PAGE_LOGIN, result);
    }

    @Test
    public void ifPasswordIsIncorrect() {
        Mockito.when(req.getParameter("main_menu")).thenReturn("0");
        Mockito.when(req.getParameter("user_name")).thenReturn("admin");
        Mockito.when(req.getParameter("password")).thenReturn("pass__");
        Mockito.when(userService.findByName("admin")).thenReturn(admin);
        Mockito.when(req.getSession()).thenReturn(session);

        String result = cut.execute(req, resp);
        assertEquals(PagePath.PAGE_LOGIN, result);
    }
}
