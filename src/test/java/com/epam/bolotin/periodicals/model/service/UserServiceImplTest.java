package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.db.repository.UserRepository;
import com.epam.bolotin.periodicals.model.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    @Mock
    HttpServletRequest req;
    @Mock
    UserRepository userRepository;
    User user = new User();

    @Test
    void TestValidateAndFillUserSuccess() {

        UserService userService = new UserServiceImpl(userRepository);
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

        UserService userService = new UserServiceImpl(userRepository);
        Mockito.when(req.getParameter("login")).thenReturn("11111111");
        Mockito.when(req.getParameter("password")).thenReturn("11111111");
        Mockito.when(req.getParameter("firstName")).thenReturn("Alex");
        Mockito.when(req.getParameter("lastName")).thenReturn("Big");
        Mockito.when(req.getParameter("email")).thenReturn("1.gmail.com");
        boolean result = userService.validateAndFillUser(user,req);
        assertEquals(false, result);
    }

    @Test
    void testBlockUnblockUser() throws AppException {

        UserService userService = new UserServiceImpl(userRepository);
        user.setBlocked(false);
        userService.blockUnblockUser(user);
        assertEquals(true, user.isBlocked());
    }

}