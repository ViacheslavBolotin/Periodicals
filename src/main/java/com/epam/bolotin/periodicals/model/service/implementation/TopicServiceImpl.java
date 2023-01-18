package com.epam.bolotin.periodicals.model.service.implementation;

import com.epam.bolotin.periodicals.exception.AppException;
import com.epam.bolotin.periodicals.model.Validator;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.db.repository.TopicRepository;
import com.epam.bolotin.periodicals.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 30.12.2022
 */
public class TopicServiceImpl implements TopicService {

    TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public void save(Topic topic) throws AppException {
        this.topicRepository.create(topic);
    }

    @Override
    public void update(Topic topic) throws AppException {
        this.topicRepository.update(topic);
    }

    @Override
    public void delete(long id) throws AppException {
        this.topicRepository.delete(id);
    }

    @Override
    public List<Topic> findAll() {
        return this.topicRepository.getAll();
    }

    @Override
    public Topic findByID(long id) {
        return this.topicRepository.getById(id);
    }

    @Override
    public Topic findByName(String topicName) {
        return this.topicRepository.getByName(topicName);
    }

    @Override
    public boolean validateAndFillTopic(Topic topic, HttpServletRequest request) {
        String errorMessage;
        String tempString = request.getParameter("topic_name").trim();

        errorMessage = Validator.validateSentences(tempString, "topic_name", 255);
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            return false;
        }
        topic.setName(tempString);

        return true;
    }
}
