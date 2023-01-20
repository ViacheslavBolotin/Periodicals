package com.epam.bolotin.periodicals.controller.command.client;

import com.epam.bolotin.periodicals.controller.PagePath;
import com.epam.bolotin.periodicals.controller.command.Command;
import com.epam.bolotin.periodicals.controller.util.RequestUtils;
import com.epam.bolotin.periodicals.model.db.entity.User;
import com.epam.bolotin.periodicals.model.service.AppServices;
import com.epam.bolotin.periodicals.model.service.PublicationService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: Viacheslav Bolotin
 * @date: 16.01.2023
 */
public class SubscribePublicationCommand implements Command {
    private PublicationService publicationService = AppServices.getInstance().getPublicationService();
    private static final Logger LOG = Logger.getLogger(SubscribePublicationCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String resp;
        long publicationId = 0;
        String errorMessage = "Cannot subscribe this publication!";

        try {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            publicationId = RequestUtils.getLongParameter(request,"publication_id");

            int result = publicationService.subscribeToPublication(user.getId(), publicationId);

            if (result == 0) {

                resp = PagePath.COMMAND_PUBLICATIONS;

                LOG.info("Subscribe publication (userId = " + user.getId() + " publicationId = " + publicationId+ ")");

                response.sendRedirect(resp);
                resp = PagePath.COMMAND_REDIRECT;
                LOG.debug("Subscribe publication (COMMAND_REDIRECT)");

            } else {

                if (result == -1) {errorMessage = "You are already subscribed to this publication!";}
                if (result == -2) {errorMessage = "You do not have enough funds in your account to subscribe to this publication!";}

                if (result < 0) {
                    request.setAttribute("errorMessage", errorMessage);
                    resp = PagePath.COMMAND_PUBLICATIONS;
                    LOG.debug("Subscribe publication (COMMAND_REDIRECT)");

                } else {
                    resp = PagePath.PAGE_ERROR;
                    LOG.info("Cannot subscribe publication (id = " + publicationId + ")");
                }
            }

        } catch (Exception e) {
            resp = PagePath.PAGE_ERROR;
            LOG.error("Subscribe publication (id = " + publicationId + ") error (" + e.getMessage() + ")");
        }
        return resp;
    }
}
