package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author: Viacheslav Bolotin
 * @date: 19.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class AddPublicationCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    PublicationService publicationService;
    @InjectMocks
    AddPublicationCommand command;

    Publication publication = new Publication();

    @BeforeEach
    public void setUp() {

        publication.setId(1);
        publication.setTitle("Publication One");
        publication.setTopicId(1);
        publication.setPrice(BigDecimal.valueOf(10));
        publication.setText("Publication One");
    }

    @Test
    void addNewTopicSuccess() throws AppException {
        Mockito.when(publicationService.validateAndFillPublication(any(), any())).thenReturn(true);
        String result = command.execute(req, resp);
        Mockito.verify(publicationService, Mockito.times(1)).save(any(Publication.class));
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }

    @Test
    void testValidateAndFillTopicSuccess() {

        PublicationService publicationServiceTest = AppServices.getInstance().getPublicationService();
        Mockito.when(req.getParameter("publication_topic")).thenReturn(""+publication.getTopicId());
        Mockito.when(req.getParameter("publication_title")).thenReturn(publication.getTitle());
        Mockito.when(req.getParameter("publication_price")).thenReturn(""+publication.getPrice());
        Mockito.when(req.getParameter("publication_text")).thenReturn(publication.getText());

        boolean result = publicationServiceTest.validateAndFillPublication(publication, req);
        assertEquals(null, req.getAttribute("errorMessage"));
        assertEquals(true, result);
    }
}
