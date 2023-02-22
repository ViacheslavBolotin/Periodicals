package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.ReportQueryExecuter;
import com.epam.bolotin.periodicals.model.db.entity.RecordReportPublication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 02.01.2023
 */
public class ReportQueryBuilder extends ReportQueryExecuter {
    @Override
    public List<RecordReportPublication> getListOfResult(ResultSet rs) throws SQLException {
        List<RecordReportPublication> reportPublication = new ArrayList<>();
        while (rs.next()) {
            reportPublication.add(RecordReportPublicationBuilder.fillRecordReportPublication(rs));
        }
        return reportPublication;
    }
}
