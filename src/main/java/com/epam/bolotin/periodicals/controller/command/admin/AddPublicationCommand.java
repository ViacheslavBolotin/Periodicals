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
 * @date: 06.01.2023
 */
public class AddPublicationCommand implements Command {
    private PublicationService publicationService = AppServices.getInstance().getPublicationService();
    private static final Logger LOG = Logger.getLogger(AddPublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String resp;
        Publication publication = new Publication();

        try {
            if (publicationService.validateAndFillPublication(publication, request)) {

                publicationService.save(publication);
                resp = PagePath.COMMAND_PUBLICATIONS;

                LOG.debug("New publication added");
            } else {
                resp = PagePath.COMMAND_TOPICS;
                LOG.debug("New publication cannot added");
            }

            response.sendRedirect(resp);
            resp = PagePath.COMMAND_REDIRECT;
            LOG.debug("New publication added (COMMAND_REDIRECT)");
        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
            LOG.error("New publication added error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
