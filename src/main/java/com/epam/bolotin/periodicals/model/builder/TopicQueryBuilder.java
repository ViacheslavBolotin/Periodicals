package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.db.entity.Entity;
import com.epam.bolotin.periodicals.model.db.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class TopicQueryBuilder extends QueryExecuter {
    @Override
    public List<Topic> getListOfResult(ResultSet rs) throws SQLException {
        List<Topic> topics = new ArrayList<>();
        while (rs.next()) {
            Topic topic = new Topic();
            TopicBuilder.fillTopic(rs, topic);
            topics.add(topic);
        }
        return topics;
    }

    @Override
    public Entity getResult(ResultSet rs) throws SQLException {
        Topic topic = new Topic();
        while (rs.next()) {
            TopicBuilder.fillTopic(rs, topic);
        }
        return topic;
    }
}
