package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.db.entity.ReaderPublication;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PublicationBuilder {

    public static void fillPublication(ResultSet rs, Publication publication) throws SQLException {
        publication.setId(rs.getLong(Fields.PUBLICATION_ID));
        publication.setTopicId(rs.getLong(Fields.PUBLICATION_TOPIC_ID));
        publication.setTitle(rs.getString(Fields.PUBLICATION_TITLE));
        publication.setPrice(rs.getBigDecimal(Fields.PUBLICATION_PRICE));
        publication.setText(rs.getString(Fields.PUBLICATION_TEXT));
    }

    public static void fillPublicationDto(ResultSet rs, PublicationDto publication) throws SQLException {
        publication.setId(rs.getLong(Fields.PUBLICATION_ID));
        publication.setTopicId(rs.getLong(Fields.PUBLICATION_TOPIC_ID));
        publication.setTitle(rs.getString(Fields.PUBLICATION_TITLE));
        publication.setPrice(rs.getBigDecimal(Fields.PUBLICATION_PRICE));
        publication.setText(rs.getString(Fields.PUBLICATION_TEXT));
        publication.setTopicName(rs.getString(Fields.TOPIC_NAME));
    }

    public static void fillReaderPublication(ResultSet rs, ReaderPublication readerPublication) throws SQLException {
        readerPublication.setId(rs.getLong(Fields.READER_PUBLICATION_ID));
        readerPublication.setPublicationId(rs.getLong(Fields.READER_PUBLICATION_PUBLICATION_ID));
        readerPublication.setUserId(rs.getLong(Fields.READER_PUBLICATION_USER_ID));
        readerPublication.setPrice(rs.getBigDecimal(Fields.READER_PUBLICATION_PRICE));
        readerPublication.setSubscribeDate(rs.getTimestamp(Fields.READER_PUBLICATION_SUBSCRIBE_DATE));
    }
}
