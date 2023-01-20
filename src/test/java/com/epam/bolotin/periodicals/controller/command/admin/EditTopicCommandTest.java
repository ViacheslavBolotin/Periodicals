package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
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
public class EditTopicCommandTest {

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    TopicService topicService;
    @InjectMocks
    EditTopicCommand command;
    Topic topic = new Topic();

    @BeforeEach
    public void setUp() {
        topic.setId(1);
        topic.setName("Topic One");
    }

    @Test
    public void testEditTopicSuccessful() {

        Mockito.when(req.getParameter("topicId")).thenReturn(""+topic.getId());

        String result = command.execute(req, resp);
        Mockito.verify(topicService, Mockito.times(1)).findByID(1);
        assertEquals(PagePath.PAGE_EDIT_TOPIC, result);
    }

    @Test
    public void testEditTopicNotSuccessful() {

        Mockito.when(req.getParameter("topicId")).thenReturn("0");

        String result = command.execute(req, resp);
        Mockito.verify(topicService, Mockito.never()).findByID(1);
        assertEquals(PagePath.COMMAND_TOPICS, result);
    }
}
