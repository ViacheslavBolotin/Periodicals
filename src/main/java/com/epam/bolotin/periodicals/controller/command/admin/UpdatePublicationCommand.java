package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.db.entity.Publication;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Viacheslav Bolotin
 * @date: 10.01.2023
 */
public class UpdatePublicationCommand implements Command {
    private PublicationService publicationService = AppServices.getInstance().getPublicationService();
    private static final Logger LOG = Logger.getLogger(UpdatePublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String resp;
        Publication publication = new Publication();
        long id = Long.parseLong(request.getParameter("publication_id"));
        publication.setId(id);

        try {

            if (publicationService.validateAndFillPublication(publication, request)) {

                publicationService.update(publication);
                resp = PagePath.COMMAND_PUBLICATIONS;

                LOG.info("Publication update (id = " + publication.getId() + ")");
            } else {
                resp = PagePath.PAGE_ERROR;
                LOG.info("Publication (id = " + publication.getId() + ") cannot update");
            }

            response.sendRedirect(resp);
            resp = PagePath.COMMAND_REDIRECT;
            LOG.debug("Publication update (COMMAND_REDIRECT)");
        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
            LOG.error("Publication (id = " + publication.getId() + ") update error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
