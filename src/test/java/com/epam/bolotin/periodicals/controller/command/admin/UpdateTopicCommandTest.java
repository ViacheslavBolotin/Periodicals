package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.exception.AppException;
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
import static org.mockito.ArgumentMatchers.any;

/**
 * @author: Viacheslav Bolotin
 * @date: 20.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class UpdateTopicCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    TopicService topicService;
    @InjectMocks
    UpdateTopicCommand command;

    @Test
    public void testUpdateTopic() throws AppException {

        Mockito.when(req.getParameter("topic_id")).thenReturn("1");
        Mockito.when(topicService.validateAndFillTopic(any(), any())).thenReturn(true);

        String result = command.execute(req, resp);
        Mockito.verify(topicService, Mockito.times(1)).update(any());
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }

    @Test
    public void testUpdateTopicError() throws AppException {

        Mockito.when(req.getParameter("topic_id")).thenReturn("1");
        Mockito.when(topicService.validateAndFillTopic(any(), any())).thenReturn(false);

        String result = command.execute(req, resp);
        Mockito.verify(topicService, Mockito.never()).update(any());
        assertEquals(PagePath.PAGE_ERROR, result);
    }
}
