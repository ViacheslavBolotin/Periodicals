package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.DBManager;
import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.db.entity.ReaderPublication;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 06.01.2023
 */
public class PublicationQueryBuilder extends QueryExecuter {
    private static final Logger LOG = Logger.getLogger(PublicationQueryBuilder.class);
    @Override
    public List<Publication> getListOfResult(ResultSet rs) throws SQLException {
        List<Publication> publications = new ArrayList<>();
        while (rs.next()) {
            Publication publication = new Publication();
            PublicationBuilder.fillPublication(rs, publication);
            publications.add(publication);
        }
        return publications;
    }

    @Override
    public Publication getResult(ResultSet rs) throws SQLException {
        Publication publication = new Publication();
        while (rs.next()) {
            PublicationBuilder.fillPublication(rs, publication);
        }
        return publication;
    }

    public List<PublicationDto> executeAndReturnValuesDto(final DBManager instance, final String query, Object... args) {

        LOG.trace(query);

        List<PublicationDto> models = null;
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            try (ResultSet rs = statement.executeQuery()) {
                models = getListOfResultDto(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return models;
    }

    public List<PublicationDto> getListOfResultDto(ResultSet rs) throws SQLException {
        List<PublicationDto> publications = new ArrayList<>();
        while (rs.next()) {
            PublicationDto publication = new PublicationDto();
            PublicationBuilder.fillPublicationDto(rs, publication);
            publications.add(publication);
        }
        return publications;
    }

    public PublicationDto executeAndReturnValueDto(final DBManager instance, final String query, Object... args) {

        LOG.trace(query);

        PublicationDto publication = new PublicationDto();
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    PublicationBuilder.fillPublicationDto(rs, publication);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return publication;
    }

    public ReaderPublication executeAndReturnReaderPublication(final DBManager instance, final String query, Object... args) {

        LOG.trace(query);

        ReaderPublication models = new ReaderPublication();
        Connection conn = instance.getConnection();
        try (PreparedStatement statement = conn.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    PublicationBuilder.fillReaderPublication(rs, models);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        instance.closeConnection(conn);
        return models;
    }
}
