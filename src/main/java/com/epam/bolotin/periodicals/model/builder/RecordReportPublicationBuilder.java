package com.epam.bolotin.periodicals.model.builder;

import com.epam.bolotin.periodicals.model.db.Fields;
import com.epam.bolotin.periodicals.model.db.entity.RecordReportPublication;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordReportPublicationBuilder {

    public static RecordReportPublication fillRecordReportPublication(ResultSet rs) throws SQLException  {

        RecordReportPublication recordReportPublication = new RecordReportPublication();
        recordReportPublication.setTopicName(rs.getString(Fields.TOPIC_NAME));
        recordReportPublication.setTitle(rs.getString(Fields.PUBLICATION_TITLE));
        recordReportPublication.setQuantity(rs.getLong(Fields.REPORT_PUBLICATION_QUANTITY));
        recordReportPublication.setAmount(rs.getBigDecimal(Fields.REPORT_PUBLICATION_AMOUNT));
        return recordReportPublication;
    }
}
