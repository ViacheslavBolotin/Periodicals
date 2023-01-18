package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.TopicService;
import com.epam.bolotin.periodicals.model.service.implementation.TopicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    HttpSession session;
    @Mock
    TopicServiceImpl topicService;

    @InjectMocks
    AddTopicCommand cut;

    Topic topic = new Topic();
    User user = new User();

    @BeforeEach
    public void setUp() {

        topic.setId(1);
        topic.setName("Topic One");

    }

    @Test
    void addNewTopicSuccess() throws AppException {

//        Mockito.when(req.getParameter("topic_name")).thenReturn("Topic One");
//        String result = cut.execute(req, resp);
//        Mockito.verify(topicService, Mockito.times(1)).save(any(Topic.class));
//        assertEquals(PagePath.COMMAND_REDIRECT, result);
    }

    @Test
    void TestValidateAndFillTopicSuccess() {

        TopicService topicService2 = AppServices.getInstance().getTopicService();
        Mockito.when(req.getParameter("topic_name")).thenReturn("Topic");
        boolean result = topicService2.validateAndFillTopic(topic, req);
        assertEquals(null, req.getAttribute("errorMessage"));
        assertEquals(true, result);
    }

}
