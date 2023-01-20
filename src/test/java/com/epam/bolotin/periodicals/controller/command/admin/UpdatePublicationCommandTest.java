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
import static org.mockito.ArgumentMatchers.any;

/**
 * @author: Viacheslav Bolotin
 * @date: 20.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class UpdatePublicationCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    PublicationService publicationService;
    @InjectMocks
    UpdatePublicationCommand command;

    @Test
    public void testUpdatePublication() throws AppException {

        Mockito.when(req.getParameter("publication_id")).thenReturn("2");
        Mockito.when(publicationService.validateAndFillPublication(any(), any())).thenReturn(true);

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.times(1)).update(any());
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }

    @Test
    public void testUpdatePublicationError() throws AppException {

        Mockito.when(req.getParameter("publication_id")).thenReturn("2");
        Mockito.when(publicationService.validateAndFillPublication(any(), any())).thenReturn(false);

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.never()).update(any());
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }
}
