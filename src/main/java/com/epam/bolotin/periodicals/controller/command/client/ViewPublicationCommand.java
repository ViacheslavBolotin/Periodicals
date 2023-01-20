package com.epam.bolotin.periodicals.controller.command.client;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.dto.PublicationDto;
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
 * @date: 16.01.2023
 */
public class ViewPublicationCommand implements Command {
    private PublicationService publicationService = AppServices.getInstance().getPublicationService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String forward;

        if (request.getParameter("publication_id") == null) {
            return PagePath.COMMAND_PERSONAL_CABINET;
        }

        long id = Long.parseLong(request.getParameter("publication_id"));

        if (id != 0) {

            PublicationDto publicationDto = publicationService.findFullInfoByID(id);
            request.setAttribute("publication", publicationDto);

            forward = PagePath.PAGE_VIEW_PUBLICATION;

        } else {
            forward = PagePath.COMMAND_PERSONAL_CABINET;
        }
        return forward;
    }
}
