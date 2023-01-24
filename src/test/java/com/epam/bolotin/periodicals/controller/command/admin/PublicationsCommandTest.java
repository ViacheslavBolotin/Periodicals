package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import com.epam.bolotin.periodicals.model.service.TopicService;
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
public class PublicationsCommandTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    TopicService topicService;
    @Mock
    PublicationService publicationService;
    @InjectMocks
    PublicationsCommand command;

    @Test
    public void testPublicationsCommand() {

        Mockito.when(req.getParameter(PagePath.SORT_PARAMETER)).thenReturn("ws");
        Mockito.when(req.getParameter(PagePath.PAGE_PARAMETER)).thenReturn("1");
        Mockito.when(req.getParameter(PagePath.TITLE_FILTER_PARAMETER)).thenReturn("Publication one");
        Mockito.when(req.getParameter(PagePath.TOPIC_FILTER_PARAMETER)).thenReturn("1");
        Mockito.when(publicationService.findSize(1L, "Publication one", 0L)).thenReturn(10L);

        String result = command.execute(req, resp);
        Mockito.verify(topicService, Mockito.times(1)).findAll();
        Mockito.verify(publicationService, Mockito.times(1)).
                findSize(1L, "Publication one", 0L);
        Mockito.verify(publicationService, Mockito.times(1)).
                findAllLimitSort(0, 5, "ws", 1L, "Publication one", 0L);
        assertEquals(PagePath.PAGE_PUBLICATIONS, result);
    }

}
