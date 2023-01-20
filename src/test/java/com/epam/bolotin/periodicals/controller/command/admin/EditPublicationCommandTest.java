package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import com.epam.bolotin.periodicals.model.service.TopicService;
import org.junit.jupiter.api.BeforeEach;
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
public class EditPublicationCommandTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    PublicationService publicationService;
    @Mock
    TopicService topicService;
    @InjectMocks
    EditPublicationCommand command;

    Publication publication = new Publication();

    @BeforeEach
    public void setUp() {
        publication.setId(2);
        publication.setTitle("Publication One");
    }

    @Test
    public void testEditPublicationSuccessful() {

        Mockito.when(req.getParameter("publicationId")).thenReturn("2");

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.times(1)).findByID(2);
        Mockito.verify(topicService, Mockito.times(1)).findAll();
        assertEquals(PagePath.PAGE_EDIT_PUBLICATION, result);
    }

    @Test
    public void testEditPublicationNotSuccessful() {

        Mockito.when(req.getParameter("publicationId")).thenReturn("0");

        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.never()).findByID(2);
        Mockito.verify(topicService, Mockito.never()).findAll();
        assertEquals(PagePath.COMMAND_PUBLICATIONS, result);
    }
}
