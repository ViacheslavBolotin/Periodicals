package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author: Viacheslav Bolotin
 * @date: 10.01.2023
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    HttpServletRequest req;

    User user = new User();

    @BeforeEach
    public void setUp() {

    }

    @Test
    void TestValidateAndFillUserSuccess() {
        Mockito.when(req.getParameter("login")).thenReturn("11111111");
        Mockito.when(req.getParameter("password")).thenReturn("11111111");
        Mockito.when(req.getParameter("firstName")).thenReturn("Alex");
        Mockito.when(req.getParameter("lastName")).thenReturn("Big");
        Mockito.when(req.getParameter("email")).thenReturn("1@gmail.com");
        boolean result = userService.validateAndFillUser(user,req);
        assertEquals(null, req.getAttribute("errorMessage"));
        assertEquals(true, result);
    }

    @Test
    void testValidateAndFillUserFailedEmail() {
        Mockito.when(req.getParameter("login")).thenReturn("11111111");
        Mockito.when(req.getParameter("password")).thenReturn("11111111");
        Mockito.when(req.getParameter("firstName")).thenReturn("Alex");
        Mockito.when(req.getParameter("lastName")).thenReturn("Big");
        Mockito.when(req.getParameter("email")).thenReturn("1.gmail.com");
        boolean result = userService.validateAndFillUser(user,req);
        assertEquals(false, result);
    }
}