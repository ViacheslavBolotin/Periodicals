package com.epam.bolotin.periodicals.model.db.repository.mysql;

import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.model.builder.ReportQueryBuilder;
import com.epam.bolotin.periodicals.model.db.DBManager;
import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.ReportQueryExecuter;
import com.epam.bolotin.periodicals.model.db.entity.RecordReportPublication;
import com.epam.bolotin.periodicals.model.db.repository.ReportPublicationRepository;
import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 06.01.2023
 */
public class ReportPublicationRepositoryMySql implements ReportPublicationRepository {

    private DBManager instance = DBManager.getInstance();
    private ReportQueryExecuter reportQueryExecuter = new ReportQueryBuilder();

    private static final String GET_SIZE_REPORT_PUBLICATION =
            " SELECT count(*) " +
            " FROM (SELECT t."+ Fields.TOPIC_NAME +", p."+ Fields.PUBLICATION_TITLE +
            " FROM reader_publication rp "+
            "    INNER JOIN publication p ON (rp."+ Fields.READER_PUBLICATION_PUBLICATION_ID +" = p." + Fields.PUBLICATION_ID +") "+
            "    INNER JOIN topic t ON (p."+ Fields.PUBLICATION_TOPIC_ID +" = t." + Fields.TOPIC_ID +") ";

    private static final String GET_REPORT_PUBLICATION =
            " SELECT t."+ Fields.TOPIC_NAME +", p."+ Fields.PUBLICATION_TITLE +
            "       , sum(rp."+ Fields.READER_PUBLICATION_PRICE +") amount, sum(1) quantity "+
            " FROM reader_publication rp "+
            "    INNER JOIN publication p ON (rp."+ Fields.READER_PUBLICATION_PUBLICATION_ID +" = p." + Fields.PUBLICATION_ID +") "+
            "    INNER JOIN topic t ON (p."+ Fields.PUBLICATION_TOPIC_ID +" = t." + Fields.TOPIC_ID +") ";

    @Override
    public long getSize(LocalDate dateBegin, LocalDate dateEnd, long topicFilter, String titleFilter) {

        StringBuilder resultQuery = new StringBuilder(GET_SIZE_REPORT_PUBLICATION);
        resultQuery.append(getFilterCondition(dateBegin, dateEnd,topicFilter, titleFilter));
        resultQuery.append(" GROUP BY t.name, p.title) t1 ");

        return reportQueryExecuter.executeGetSize(instance, resultQuery.toString());
    }

    @Override
    public List<RecordReportPublication> ReportPublication(long from, long size, LocalDate dateBegin, LocalDate dateEnd,
                                                           String sortType, long topicFilter, String titleFilter) {

        StringBuilder resultQuery = new StringBuilder(GET_REPORT_PUBLICATION);

        resultQuery.append(getFilterCondition(dateBegin, dateEnd, topicFilter, titleFilter));
        resultQuery.append(" GROUP BY t.name, p.title ");

        switch(sortType) {
            case PaginationSort.NAME_STRAIGHT:
                resultQuery.append(" ORDER BY p."+Fields.PUBLICATION_TITLE+" ASC ");  
                break;
            case PaginationSort.NAME_FORWARD:
                resultQuery.append(" ORDER BY p."+Fields.PUBLICATION_TITLE+" DESC ");
                break;
            case PaginationSort.BALANCE_STRAIGHT:
                resultQuery.append(" ORDER BY "+Fields.REPORT_PUBLICATION_AMOUNT+" ASC ");
                break;
            case PaginationSort.BALANCE_FORWARD:
                resultQuery.append(" ORDER BY "+Fields.REPORT_PUBLICATION_AMOUNT+" DESC ");
                break;
            default:
                resultQuery.append(" ORDER BY "+Fields.REPORT_PUBLICATION_AMOUNT+" DESC ");
        }

        resultQuery.append(" LIMIT ? , ?");
        
        return reportQueryExecuter.executeAndReturnList(instance, resultQuery.toString(), from, size);
    }

    private String getFilterCondition(LocalDate dateBegin, LocalDate dateEnd, long topicFilter, String titleFilter){

        StringBuilder resultQuery = new StringBuilder("");
        String topicCondition = "";
        String titleCondition = "";
        String userCondition = "";
        String beginDateCondition = "";
        String endDateCondition = "";

        if (dateBegin != null) {
            beginDateCondition = " AND rp." + Fields.READER_PUBLICATION_SUBSCRIBE_DATE +" >= '"+dateBegin+"' ";
        }
        if (dateEnd != null) {
            endDateCondition = " AND rp." + Fields.READER_PUBLICATION_SUBSCRIBE_DATE +" <= '"+dateEnd+"' ";
        }
        if (topicFilter != 0) {
            topicCondition = " AND p." + Fields.PUBLICATION_TOPIC_ID +" ="+topicFilter+" ";
        }
        if (!titleFilter.equals("")) {
            titleCondition = " AND p." + Fields.PUBLICATION_TITLE +" like '%"+titleFilter+"%' ";
        }

        if (!topicCondition.equals("") || !titleCondition.equals("") || !userCondition.equals("")  ||
                !beginDateCondition.equals("") || !endDateCondition.equals("")) {

            resultQuery.append(" WHERE 1=1 ");
            resultQuery.append(beginDateCondition);
            resultQuery.append(endDateCondition);
            resultQuery.append(topicCondition);
            resultQuery.append(titleCondition);
            resultQuery.append(userCondition);
        }

        return resultQuery.toString();
    }

}
