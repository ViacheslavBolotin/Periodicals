package com.epam.bolotin.periodicals.model.db.repository;

import com.epam.bolotin.periodicals.model.db.entity.RecordReportPublication;

import java.time.LocalDate;
import java.util.List;

public interface ReportPublicationRepository {

    long getSize(LocalDate dateBegin, LocalDate dateEnd, long topicFilter, String titleFilter);

    List<RecordReportPublication> ReportPublication(long from, long size, LocalDate dateBegin, LocalDate dateEnd,
                                                    String sortType, long topicFilter, String titleFilter);
}
