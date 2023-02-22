package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.ReportService;
import com.epam.bolotin.periodicals.model.service.TopicService;
import com.epam.bolotin.periodicals.model.service.dto.RecordReportPublicationDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 27.01.2023
 */
public class ReportsCommand implements Command {

    private TopicService topicService = AppServices.getInstance().getTopicService();
    private ReportService reportService = AppServices.getInstance().getReportService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Topic> topics = topicService.findAll();
        request.setAttribute("topics", topics);

        PaginationSort ps = new PaginationSort();
        ps.setSortType(RequestUtils.getStringParameter(request, PagePath.SORT_PARAMETER));
        ps.setCurrentPage(RequestUtils.getIntParameter(request, PagePath.PAGE_PARAMETER));
        ps.setTitleFilter(RequestUtils.getStringParameter(request, PagePath.TITLE_FILTER_PARAMETER));
        ps.setTopicFilter(RequestUtils.getLongParameter(request, PagePath.TOPIC_FILTER_PARAMETER));

        ps.setDateBegin(RequestUtils.getDateTimeParameter(request, PagePath.DATE_BEGIN_FILTER_PARAMETER));
        ps.setDateEnd(RequestUtils.getDateTimeParameter(request, PagePath.DATE_END_FILTER_PARAMETER));

        ps.setNumberOfItems(reportService.findSize(ps.getDateBegin(), ps.getDateEnd(),
                ps.getTopicFilter(), ps.getTitleFilter()));
        ps.calc();

        List<RecordReportPublicationDto> reportPublications =
                reportService.ReportPublication(ps.getStartFrom(), ps.MAX_ITEM_ON_PAGE,
                        ps.getDateBegin(), ps.getDateEnd(),
                        ps.getSortType(), ps.getTopicFilter(), ps.getTitleFilter());
        request.setAttribute("reportPublications", reportPublications);
        request.setAttribute("ps", ps);

        return PagePath.PAGE_REPORTS;
    }
}
