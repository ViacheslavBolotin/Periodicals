package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.entity.Topic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TopicService {

    void save(Topic topic) throws AppException;
    void update(Topic topic) throws AppException;
    void delete(long id) throws AppException;

    List<Topic> findAll();
    Topic findByID(long id);
    Topic findByName(String topicName);

    boolean validateAndFillTopic(Topic topic, HttpServletRequest request);
}
