package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.ReportBuilder;
import com.epam.bolotin.periodicals.model.service.ReportService;
import com.epam.bolotin.periodicals.model.service.TopicService;
import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 01.03.2023
 */
public class ExportToPdfCommand implements Command {

    private TopicService topicService = AppServices.getInstance().getTopicService();
    private ReportService reportService = AppServices.getInstance().getReportService();
    private ReportBuilder reportBuilder = AppServices.getInstance().getReportBuilder();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forward;

        PaginationSort ps = new PaginationSort();
        ps.setSortType(RequestUtils.getStringParameter(request, PagePath.SORT_PARAMETER));
        ps.setCurrentPage(RequestUtils.getIntParameter(request, PagePath.PAGE_PARAMETER));
        ps.setTitleFilter(RequestUtils.getStringParameter(request, PagePath.TITLE_FILTER_PARAMETER));
        ps.setTopicFilter(RequestUtils.getLongParameter(request, PagePath.TOPIC_FILTER_PARAMETER));
        ps.setDateBegin(RequestUtils.getDateTimeParameter(request, PagePath.DATE_BEGIN_FILTER_PARAMETER));
        ps.setDateEnd(RequestUtils.getDateTimeParameter(request, PagePath.DATE_END_FILTER_PARAMETER));
        ps.setDateBegin(LocalDate.now().minusYears(1));
        ps.setDateEnd(LocalDate.now());

        List<RecordReportPublicationDto> reportPublications =
                reportService.ReportPublication(0, 1000,
                        ps.getDateBegin(), ps.getDateEnd(),
                        ps.getSortType(), ps.getTopicFilter(), ps.getTitleFilter());

        if (reportBuilder.reportPublicationPdf(response, reportPublications)) {
            forward = PagePath.COMMAND_REPORTS;
        } else {
            forward = PagePath.PAGE_ERROR;
        }

        return forward;
    }
}