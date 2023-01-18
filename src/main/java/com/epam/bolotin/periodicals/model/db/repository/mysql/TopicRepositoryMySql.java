package com.epam.bolotin.periodicals.model.db.repository.mysql;

import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.builder.TopicQueryBuilder;
import com.epam.bolotin.periodicals.model.db.DBManager;
import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.db.repository.TopicRepository;

import java.util.List;

public class TopicRepositoryMySql implements TopicRepository {

    private DBManager instance = DBManager.getInstance();
    private QueryExecuter queryExecuter = new TopicQueryBuilder();

    private static final String GET_ALL = "SELECT * FROM topic ORDER BY "+ Fields.TOPIC_NAME;
    private static final String GET_BY_ID = "SELECT * FROM topic WHERE "+ Fields.TOPIC_ID +" = ?";
    private static final String GET_BY_NAME = "SELECT * FROM topic WHERE "+ Fields.TOPIC_NAME +" = ?";
    private static final String CREATE =
            "INSERT INTO topic ("+Fields.TOPIC_ID+", "+Fields.TOPIC_NAME+") VALUES (?, ?)";
    private static final String UPDATE =
            "UPDATE topic SET "+Fields.TOPIC_NAME+" = ? WHERE "+Fields.USER_ID+" = ?";
    private static final String DELETE = "DELETE FROM topic WHERE "+ Fields.TOPIC_ID +" = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX("+ Fields.TOPIC_ID +")+1 from topic";

    @Override
    public void create(Topic topic) throws DBException {
        long id = queryExecuter.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryExecuter.execute(instance, CREATE, id, topic.getName());
        topic.setId(id);
    }

    @Override
    public void update(Topic topic) throws DBException {
        queryExecuter.execute(instance, UPDATE, topic.getName(), topic.getId());
    }

    @Override
    public void delete(long id) throws DBException {
        queryExecuter.execute(instance, DELETE, id);
    }

    @Override
    public Topic getById(long id) {
        return (Topic) queryExecuter.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public Topic getByName(String name) {
        return (Topic) queryExecuter.executeAndReturn(instance, GET_BY_NAME, name);
    }

    @Override
    public List<Topic> getAll() {
        return queryExecuter.executeAndReturnList(instance, GET_ALL);
    }
}
