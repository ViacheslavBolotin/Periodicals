package com.epam.bolotin.periodicals.controller.command.client;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.PaginationSort;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
import com.epam.bolotin.periodicals.model.db.entity.Account;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import com.epam.bolotin.periodicals.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 29.12.2022
 */
public class PersonalCabinetCommand implements Command {

    private TopicService topicService = AppServices.getInstance().getTopicService();
    private PublicationService publicationService = AppServices.getInstance().getPublicationService();
    //private AccountService accountService =  AppServices.getInstance().getAccountService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Topic> topics = topicService.findAll();
        request.setAttribute("topics", topics);

        PaginationSort ps = new PaginationSort();
        ps.setSortType(RequestUtils.getStringParameter(request, PagePath.SORT_PARAMETER));
        ps.setCurrentPage(RequestUtils.getIntParameter(request, PagePath.PAGE_PARAMETER));
        ps.setTitleFilter(RequestUtils.getStringParameter(request, PagePath.TITLE_FILTER_PARAMETER));
        ps.setTopicFilter(RequestUtils.getLongParameter(request, PagePath.TOPIC_FILTER_PARAMETER));
        ps.setUserFilter(user.getId());
        ps.setNumberOfItems(publicationService.findSize(
                ps.getTopicFilter(), ps.getTitleFilter(), ps.getUserFilter()));
        ps.calc();

        List<PublicationDto> publications =
                publicationService.findAllLimitSort(ps.getStartFrom(), ps.MAX_ITEM_ON_PAGE,
                        ps.getSortType(), ps.getTopicFilter(), ps.getTitleFilter(), ps.getUserFilter());
        request.setAttribute("publications", publications);
        request.setAttribute("ps", ps);

        return PagePath.PAGE_PERSONAL_CABINET;
    }
}
