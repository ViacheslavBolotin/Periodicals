package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.db.entity.Topic;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import com.epam.bolotin.periodicals.model.service.TopicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Viacheslav Bolotin
 * @date: 09.01.2023
 */
public class EditPublicationCommand implements Command {
    private PublicationService publicationService = AppServices.getInstance().getPublicationService();
    private TopicService topicService = AppServices.getInstance().getTopicService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forward;

        if (request.getParameter("publicationId") == null) {
            return PagePath.COMMAND_PUBLICATIONS;
        }

        long id = Long.parseLong(request.getParameter("publicationId"));

        if (id != 0) {

            Publication publication = publicationService.findByID(id);
            request.setAttribute("publication", publication);

            List<Topic> topics = topicService.findAll();
            request.setAttribute("topics", topics);

            forward = PagePath.PAGE_EDIT_PUBLICATION;

        } else {
            forward = PagePath.COMMAND_PUBLICATIONS;
        }
        return forward;
    }
}
