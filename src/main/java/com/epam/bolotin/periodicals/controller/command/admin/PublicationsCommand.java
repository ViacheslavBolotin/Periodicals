package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import com.epam.bolotin.periodicals.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 13.01.2023
 */
public class PublicationsCommand implements Command {

    private TopicService topicService = AppServices.getInstance().getTopicService();
    private PublicationService publicationService = AppServices.getInstance().getPublicationService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Topic> topics = topicService.findAll();
        request.setAttribute("topics", topics);

        PaginationSort ps = new PaginationSort();
        ps.setSortType(RequestUtils.getStringParameter(request, PagePath.SORT_PARAMETER));
        ps.setCurrentPage(RequestUtils.getIntParameter(request, PagePath.PAGE_PARAMETER));
        ps.setTitleFilter(RequestUtils.getStringParameter(request, PagePath.TITLE_FILTER_PARAMETER));
        ps.setTopicFilter(RequestUtils.getLongParameter(request, PagePath.TOPIC_FILTER_PARAMETER));
        ps.setNumberOfItems(publicationService.findSize(
                ps.getTopicFilter(), ps.getTitleFilter(), ps.getUserFilter()));
        ps.calc();

        List<PublicationDto> publications =
                publicationService.findAllLimitSort(ps.getStartFrom(), ps.MAX_ITEM_ON_PAGE,
                        ps.getSortType(), ps.getTopicFilter(), ps.getTitleFilter(), ps.getUserFilter());
        request.setAttribute("publications", publications);
        request.setAttribute("ps", ps);

        return PagePath.PAGE_PUBLICATIONS;
    }
}
