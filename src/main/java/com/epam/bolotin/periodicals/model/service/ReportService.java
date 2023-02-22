package com.epam.bolotin.periodicals.model.service;

import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    long findSize(LocalDate dateBegin, LocalDate dateEnd, long topicFilter, String titleFilter);

    List<RecordReportPublicationDto> ReportPublication(long from, long size, LocalDate dateBegin, LocalDate dateEnd,
                                                       String sortType, long topicFilter, String titleFilter);
}
