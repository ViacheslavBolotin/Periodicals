package com.epam.bolotin.periodicals.model.db.repository.mysql;

import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.exception.DBException;
import com.epam.bolotin.periodicals.model.builder.PublicationQueryBuilder;
import com.epam.bolotin.periodicals.model.db.DBManager;
import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.QueryExecuter;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.db.entity.ReaderPublication;
import com.epam.bolotin.periodicals.model.db.repository.AccountRepository;
import com.epam.bolotin.periodicals.model.db.repository.PublicationRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 06.01.2023
 */
public class PublicationRepositoryMySql implements PublicationRepository {

    private DBManager instance = DBManager.getInstance();
    private QueryExecuter queryExecuter = new PublicationQueryBuilder();
    private static final String GET_ALL = "SELECT * FROM publication";
    private static final String GET_ALL_FULL_INFO = "SELECT p.*, t.name FROM publication as p "+
            "INNER JOIN topic as t ON (p." + Fields.PUBLICATION_TOPIC_ID +" = t." + Fields.TOPIC_ID +")";
    private static final String GET_BY_ID = "SELECT * FROM publication WHERE " + Fields.PUBLICATION_ID +" = ?";
    private static final String GET_BY_TOPIC_ID = "SELECT * FROM publication WHERE " + Fields.PUBLICATION_TOPIC_ID +" = ?";
    private static final String GET_FULL_INFO_BY_ID = "SELECT p.*, t.name FROM publication as p "+
            "INNER JOIN topic as t ON (p." + Fields.PUBLICATION_TOPIC_ID +" = t." + Fields.TOPIC_ID +") WHERE p."
            + Fields.PUBLICATION_ID + " = ?";
    private static final String GET_BY_ID_AND_USER_ID = "SELECT * FROM reader_publication WHERE "
            + Fields.READER_PUBLICATION_USER_ID +" = ? AND " + Fields.READER_PUBLICATION_PUBLICATION_ID +" = ?";
    private static final String SUBSCRIBE_TO_PUBLICATION = "INSERT INTO reader_publication ("
            + Fields.READER_PUBLICATION_ID + ", " + Fields.READER_PUBLICATION_PUBLICATION_ID + ", "
            + Fields.READER_PUBLICATION_USER_ID + ", " + Fields.READER_PUBLICATION_PRICE + ", "
            + Fields.READER_PUBLICATION_SUBSCRIBE_DATE +") "
            + " VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
    private static final String CREATE =
            "INSERT INTO publication (" + Fields.PUBLICATION_ID+", "+Fields.PUBLICATION_TOPIC_ID+", "+
                    Fields.PUBLICATION_TITLE+", "+Fields.PUBLICATION_PRICE+", "+Fields.PUBLICATION_TEXT+") "
                    + " VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE =
            "UPDATE publication SET " +Fields.PUBLICATION_TOPIC_ID+" = ?, "+Fields.PUBLICATION_TITLE+" = ?, "
                    +Fields.PUBLICATION_PRICE+" = ?, "+Fields.PUBLICATION_TEXT+" = ? WHERE "+Fields.PUBLICATION_ID+" = ?";
    private static final String DELETE = "DELETE FROM publication WHERE " + Fields.PUBLICATION_ID + " = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX(" + Fields.PUBLICATION_ID + ")+1 from publication";
    private static final String GET_NEXT_AUTO_INCREMENT_READER_PUBLICATION =
            "SELECT MAX(" + Fields.READER_PUBLICATION_ID + ")+1 from reader_publication";

    @Override
    public void create(Publication publication) throws DBException {
        long id = queryExecuter.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryExecuter.execute(instance, CREATE, id, publication.getTopicId(), publication.getTitle(),
                publication.getPrice(), publication.getText());
        publication.setId(id);
    }

    @Override
    public void update(Publication publication) throws DBException {
        queryExecuter.execute(instance, UPDATE, publication.getTopicId(), publication.getTitle(),
                publication.getPrice(), publication.getText(), publication.getId());
    }

    @Override
    public void delete(long id) throws DBException {
        queryExecuter.execute(instance, DELETE, id);
    }

    @Override
    public Publication getById(long id) {
        return (Publication) queryExecuter.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public Publication getByTopicId(long id) {
        return (Publication) queryExecuter.executeAndReturn(instance, GET_BY_TOPIC_ID, id);
    }

    @Override
    public PublicationDto getFullInfoById(long id) {
        return ((PublicationQueryBuilder)queryExecuter).executeAndReturnValueDto(instance, GET_FULL_INFO_BY_ID, id);
    }

    @Override
    public List<Publication> getAll() {
        return queryExecuter.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public List<PublicationDto> getAllFullInfo() {
        return ((PublicationQueryBuilder)queryExecuter).executeAndReturnValuesDto(instance, GET_ALL_FULL_INFO);
    }

    @Override
    public long getSize(long topicFilter, String titleFilter, long userFilter) {

        StringBuilder resultQuery =
                new StringBuilder("SELECT count(*) FROM publication as p "+
                        "INNER JOIN topic as t ON (p." + Fields.PUBLICATION_TOPIC_ID +" = t." + Fields.TOPIC_ID +")");

        resultQuery.append(getFilterCondition(topicFilter, titleFilter, userFilter));

        return queryExecuter.executeGetSize(instance, resultQuery.toString());
    }

    @Override
    public List<PublicationDto> getAllLimitSort(long from, long size, String sortType,
                                                long topicFilter, String titleFilter, long userFilter) {

        StringBuilder resultQuery = 
                new StringBuilder("SELECT p.*, t.name FROM publication as p "+
                        "INNER JOIN topic as t ON (p." + Fields.PUBLICATION_TOPIC_ID +" = t." + Fields.TOPIC_ID +")");

        resultQuery.append(getFilterCondition(topicFilter, titleFilter, userFilter));

        switch(sortType) {
            case PaginationSort.NAME_STRAIGHT:
                resultQuery.append(" ORDER BY p."+Fields.PUBLICATION_TITLE+" ASC ");  
                break;
            case PaginationSort.NAME_FORWARD:
                resultQuery.append(" ORDER BY p."+Fields.PUBLICATION_TITLE+" DESC ");
                break;
            case PaginationSort.BALANCE_STRAIGHT:
                resultQuery.append(" ORDER BY p."+Fields.PUBLICATION_PRICE+" ASC ");
                break;
            case PaginationSort.BALANCE_FORWARD:
                resultQuery.append(" ORDER BY p."+Fields.PUBLICATION_PRICE+" DESC ");
                break;
            default:
        }

        resultQuery.append(" LIMIT ? , ?");
        
        return ((PublicationQueryBuilder)queryExecuter).
                executeAndReturnValuesDto(instance, resultQuery.toString(), from, size);
    }

    private String getFilterCondition(long topicFilter, String titleFilter, long userFilter){

        StringBuilder resultQuery = new StringBuilder("");
        String topicCondition = "";
        String titleCondition = "";
        String userCondition = "";

        if (topicFilter != 0) {
            topicCondition = " AND p." + Fields.PUBLICATION_TOPIC_ID +" ="+topicFilter+" ";
        }
        if (!titleFilter.equals("")) {
            titleCondition = " AND p." + Fields.PUBLICATION_TITLE +" like '%"+titleFilter+"%' ";
        }
        if (userFilter != 0) {
            userCondition = " AND p." + Fields.PUBLICATION_ID +
                    " in (SELECT " + Fields.READER_PUBLICATION_PUBLICATION_ID +
                    " FROM reader_publication WHERE " + Fields.READER_PUBLICATION_USER_ID + " = " +userFilter+") ";
        }

        if (!topicCondition.equals("") || !titleCondition.equals("") || !userCondition.equals("")) {

            resultQuery.append(" WHERE 1=1 ");
            resultQuery.append(topicCondition);
            resultQuery.append(titleCondition);
            resultQuery.append(userCondition);
        }

        return resultQuery.toString();
    }

    @Override
    public boolean isSubscribeToPublication(long userId, long publicationId) {

        ReaderPublication readerPublication =
                ((PublicationQueryBuilder) queryExecuter).
                        executeAndReturnReaderPublication(instance, GET_BY_ID_AND_USER_ID, userId, publicationId);

        return readerPublication.getId() != 0;
    }

    @Override
    public int subscribeToPublication(long userId, long publicationId, BigDecimal sum) throws DBException {

        final AccountRepository accountRepository = new AccountRepositoryMySql();;

        Connection connection = accountRepository.debitedFromAccount(userId, sum);

        long readerPublicationId = queryExecuter.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT_READER_PUBLICATION);

        queryExecuter.executeTransactionEnd(instance, connection, SUBSCRIBE_TO_PUBLICATION,
                readerPublicationId, publicationId, userId, sum);

        return 0;
    }
}
