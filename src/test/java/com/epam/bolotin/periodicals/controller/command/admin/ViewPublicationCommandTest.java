package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.client.ViewPublicationCommand;
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
 * @date: 20.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class ViewPublicationCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    PublicationService publicationService;
    @InjectMocks
    ViewPublicationCommand command;

    @Test
    public void testViewPublication() {

        Mockito.when(req.getParameter("publication_id")).thenReturn("2");

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.times(1)).findFullInfoByID(2);
        assertEquals(PagePath.PAGE_VIEW_PUBLICATION, result);
    }

    @Test
    public void testViewPublicationError() {

        Mockito.when(req.getParameter("publication_id")).thenReturn("0");

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.never()).findFullInfoByID(0);
        assertEquals(PagePath.COMMAND_PERSONAL_CABINET, result);
    }
}
