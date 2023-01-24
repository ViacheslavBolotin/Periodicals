package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.db.repository.TopicRepository;
import com.epam.bolotin.periodicals.model.service.implementation.TopicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: Viacheslav Bolotin
 * @date: 23.01.2023
 */
@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {
    @Mock
    HttpServletRequest req;
    @Mock
    TopicRepository topicRepository;
    Topic topic = new Topic();

    @Test
    void TestValidateAndFillTopicSuccess() {

        TopicService topicService = new TopicServiceImpl(topicRepository);
        Mockito.when(req.getParameter("topic_name")).thenReturn("Topic One");
        boolean result = topicService.validateAndFillTopic(topic, req);
        assertEquals("Topic One", topic.getName());
        assertEquals(true, result);

        Mockito.when(req.getParameter("topic_name")).thenReturn("Українське Кіно");
        boolean result2 = topicService.validateAndFillTopic(topic, req);
        assertEquals("Українське Кіно", topic.getName());
        assertEquals(true, result2);
    }

    @Test
    void TestValidateAndFillTopicError() {

        TopicService topicService = new TopicServiceImpl(topicRepository);
        Mockito.when(req.getParameter("topic_name")).thenReturn("**11111111");
        boolean result = topicService.validateAndFillTopic(topic, req);
        assertEquals(null, topic.getName());
        assertEquals(false, result);
    }
}