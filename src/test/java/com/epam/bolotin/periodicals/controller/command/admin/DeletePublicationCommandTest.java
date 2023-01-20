package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.service.PublicationService;
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
 * @date: 19.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class DeletePublicationCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    PublicationService publicationService;
    @InjectMocks
    DeletePublicationCommand command;

    @Test
    public void testDeletePublication() throws AppException {

        Mockito.when(req.getParameter("publicationId")).thenReturn("2");

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.times(1)).delete(2);
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }
}
