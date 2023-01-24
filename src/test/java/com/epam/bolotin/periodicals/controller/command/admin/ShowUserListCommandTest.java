package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.service.UserService;
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
public class ShowUserListCommandTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    UserService userService;
    @InjectMocks
    ShowUserListCommand command;

    @Test
    public void testShowUserListCommand() {

        String result = command.execute(req, resp);
        Mockito.verify(userService, Mockito.times(1)).findAll();
        assertEquals(PagePath.PAGE_USER_LIST, result);
    }

}
