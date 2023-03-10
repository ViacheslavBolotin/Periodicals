package com.epam.bolotin.periodicals.controller.command.admin;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Viacheslav Bolotin
 * @date: 09.01.2023
 */
public class DeletePublicationCommand implements Command {

    private PublicationService publicationService = AppServices.getInstance().getPublicationService();
    private static final Logger LOG = Logger.getLogger(DeletePublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String errorMessage = "";
        String resp;

        long id = Long.parseLong(request.getParameter("publicationId"));
        try {
            publicationService.delete(id);
            resp = PagePath.COMMAND_PUBLICATIONS;
            LOG.debug("Delete publication (id = " + id + ")");

            response.sendRedirect(resp);
            resp = PagePath.COMMAND_REDIRECT;
            LOG.debug("Delete publication (COMMAND_REDIRECT)");
        } catch (Exception e) {
            errorMessage = "error.publication.delete";
            request.setAttribute("errorMessage", errorMessage);
            resp = PagePath.PAGE_ERROR;
            LOG.error("Delete publication (id = " + id + ") error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
