package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.entity.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicBuilder {

    public static void fillTopic(ResultSet rs, Topic topic) throws SQLException {
        topic.setId(rs.getLong(Fields.TOPIC_ID));
        topic.setName(rs.getString(Fields.TOPIC_NAME));
    }
}
