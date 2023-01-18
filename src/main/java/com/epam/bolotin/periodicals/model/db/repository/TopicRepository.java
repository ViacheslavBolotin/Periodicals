package com.epam.bolotin.periodicals.model.db.repository;

import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.db.entity.Topic;

import java.util.List;

public interface TopicRepository {

    void create(Topic topic) throws DBException;
    void update(Topic topic) throws DBException;
    void delete(long id) throws DBException;
    Topic getById(long id);
    Topic getByName(String userName);
    List<Topic> getAll();
}
