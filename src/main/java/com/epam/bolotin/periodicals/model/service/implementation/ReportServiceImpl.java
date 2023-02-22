package com.epam.bolotin.periodicals.model.service.implementation;

import com.epam.bolotin.periodicals.model.builder.DtoBilder;
import com.epam.bolotin.periodicals.model.db.entity.RecordReportPublication;
import com.epam.bolotin.periodicals.model.db.repository.ReportPublicationRepository;
import com.epam.bolotin.periodicals.model.service.ReportService;
import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;

import java.time.LocalDate;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    private final ReportPublicationRepository reportPublicationRepository;

    public ReportServiceImpl(ReportPublicationRepository reportPublicationRepository) {
        this.reportPublicationRepository = reportPublicationRepository;
    }

    @Override
    public long findSize(LocalDate dateBegin, LocalDate dateEnd, long topicFilter, String titleFilter) {
        return reportPublicationRepository.getSize(dateBegin, dateEnd, topicFilter, titleFilter);
    }

    @Override
    public List<RecordReportPublicationDto> ReportPublication(long from, long size, LocalDate dateBegin, LocalDate dateEnd,
                                                              String sortType, long topicFilter, String titleFilter) {

        List<RecordReportPublication> recordReportPublication =
            reportPublicationRepository.ReportPublication(from, size, dateBegin, dateEnd, sortType, topicFilter, titleFilter);

        return DtoBilder.getRecordReportPublicationDtoList(recordReportPublication);
    }
}
