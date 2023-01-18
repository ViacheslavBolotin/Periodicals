package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.Crypt;
import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author: Viacheslav Bolotin
 * @date: 10.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class EditClientCommandTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    UserService userService;
    @InjectMocks
    BlockUnblockUserCommand cut;

    User user = new User();

    @BeforeEach
    public void setUp() {
        user.setUserName("user");
        user.setPassword(Crypt.hashPassword("pass"));
        user.setUserRoleId(2);
    }

    @Test
    public void testBlockingUser() throws AppException {

        Mockito.when(req.getParameter("user_id")).thenReturn("2");
        Mockito.when(userService.findByID(2)).thenReturn(user);
        String result = cut.execute(req, resp);
        Mockito.verify(userService, Mockito.times(1)).blockUnblockUser(any(User.class));
        assertEquals(PagePath.COMMAND_REDIRECT, result);

    }

}
