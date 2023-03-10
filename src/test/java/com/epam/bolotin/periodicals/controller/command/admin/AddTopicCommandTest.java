package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.service.AppServices;
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
import static org.mockito.ArgumentMatchers.any;

/**
 * @author: Viacheslav Bolotin
 * @date: 17.01.2023
 */
@ExtendWith(MockitoExtension.class)
public class AddTopicCommandTest {
    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    TopicService topicService;
    @InjectMocks
    AddTopicCommand command;

    Topic topic = new Topic();

    @BeforeEach
    public void setUp() {

        topic.setId(1);
        topic.setName("Topic One");

    }

    @Test
    void addNewTopicSuccess() throws AppException {
        Mockito.when(topicService.validateAndFillTopic(any(), any())).thenReturn(true);
        String result = command.execute(req, resp);
        Mockito.verify(topicService, Mockito.times(1)).save(any(Topic.class));
        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }

    @Test
    void testValidateAndFillTopicSuccess() {

        TopicService topicServiceTest = AppServices.getInstance().getTopicService();
        Mockito.when(req.getParameter("topic_name")).thenReturn("Topic One");
        boolean result = topicServiceTest.validateAndFillTopic(topic, req);
        assertEquals(null, req.getAttribute("errorMessage"));
        assertEquals(true, result);
    }
}
